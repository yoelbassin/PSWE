package renderer;

import elements.LightSource;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import static geometries.Intersectable.GeoPoint;
import java.util.List;

/**
 * class represents a renderer for a scene one of it responsibility is to
 * calculate intensity of each pixel.
 */
public class Render {
	ImageWriter _image;
	Scene _scene;
	// ***************** Constructors ********************** //

	/**
	 * creates a renderer by the scene (3D model) and a view plane size composite of
	 * class image writer which write scene to image or .jpg file this class using
	 * renderer to calculate intensity and color in each pixel
	 * 
	 * @param image - an instance of image writer that write the
	 * @param scene - an instance of scene , contain scene detail like lights and
	 *              light's properties and geometries and their position and which
	 *              their materials there are built from
	 */
	public Render(ImageWriter image, Scene scene) {
		this._image = image;
		this._scene = scene;
	}

	// ***************** Operations ******************** //

	/**
	 * renders the scene to a 2D picture
	 */
	public void renderImage() {
		// for all the Y (height) pixels in the view plane
		for (int i = 0; i < _image.getNy(); ++i)
			// for all the X (width) pixels in the view plane
			for (int j = 0; j < _image.getNx(); ++j) {
				// construct ray trough pixel [j,i]
				Ray ray = _scene.getCamera().constructRayThroughPixel(_image.getNx(), _image.getNy(), j, i,
						_scene.getDistance(), _image.getWidth(), _image.getHeight());
				// find the intersections of the geometries with the ray
				List<GeoPoint> intersectionPoints = _scene.getGeometries().findIntersections(ray);
				// if there are no intersections - show background
				if (intersectionPoints == null)
					_image.writePixel(j, i, _scene.getBackground().getColor());
				// else, find the closest intersection to the camera and and paint the pixel
				// with the geometry's color
				else {
					GeoPoint closestPoint = getClosestPoint(intersectionPoints);
					// the user has not to give the initial values for just auxiliary parameters.
					_image.writePixel(j, i, calcColor(closestPoint, ray).getColor());
				}
			}
	}

//assuming that the effective of the original point , with all the conffiedece
	private static final int MAX_CALC_COLOR_LEVEL = 10;

	/**
	 * overloading the function the not recursive calcColor function for saving the
	 * design in render image
	 * 
	 * @param geopoint
	 * @param inRay
	 * @return
	 */
	private Color calcColor(GeoPoint geopoint, Ray inRay) {
		Vector v = inRay.getDir();
		GeoPoint closestPoint = findClosestIntersection(inRay);
		Ray ray = new Ray(closestPoint.point, v);
		return calcColor(closestPoint, ray, MAX_CALC_COLOR_LEVEL, 1.0).add(_scene.getAmbient().getIntensity());
	}

//minimum index , color intensity to align it as zero 
	private static final double MIN_CALC_COLOR_K = 0.001;

	/**
	 * 
	 * @param geopoint - for the intensity calculating geopoint
	 * @param inRay    - the ray that the color calculation be on
	 * @param level    - recursive level,degree
	 * @param k        - total intensity coefficient , save the result of the former
	 *                 recursion iteration and then multiple by k
	 * @return the intensity of light at point
	 */
	private Color calcColor(GeoPoint geopoint, Ray inRay, int level, double k) {
		// two recursion stopping conditions !!!
		// the first condition : recursion depth is equals to zero
		// the second condition : if k is less than minimum intensity
		// easy to realize that kkr will be less , because k between 0 and 1
		if (level == 0 || k < MIN_CALC_COLOR_K)
			return Color.BLACK;

		Color color = geopoint.geometry.getEmission();//
		color = color.add(geopoint.geometry.getEmission());
		// get viewer direction and contain that in variable vector v
		Vector v = geopoint.point.subtract(_scene.getCamera().getP0()).normalize();
		Vector n = geopoint.geometry.getNormal(geopoint.point);// normal to geometry
		double kd = geopoint.geometry.getMaterial().getKd();// specular index
		double ks = geopoint.geometry.getMaterial().getKs();// diffusive light 
		int nShininess = geopoint.geometry.getMaterial().getShininess();

		for (LightSource lightSource : _scene.getLights()) {
			Vector l = lightSource.getL(geopoint.point);

			if ((n.dotProduct(l) > 0 && n.dotProduct(v) > 0) || (n.dotProduct(l) < 0 && n.dotProduct(v) < 0)) {
				double ktr = transparency(l, n, geopoint);

				if (ktr * k < MIN_CALC_COLOR_K) {
					Color lightIntensity = lightSource.getIntensity(geopoint.point).scale(ktr);// result partial shading
					color = color.add((calcDiffusive(kd, l, n, lightIntensity)),
							calcSpecular(ks, l, n, v, nShininess, lightIntensity));
				}

			}
		}

		double kR = geopoint.geometry.getMaterial().getKr();// for the refraction
		double kkR = k * kR;
		if (kkR > MIN_CALC_COLOR_K) {
			Ray reflectedRay = constructReflectedRay(n, geopoint.point, inRay);
			GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);// watch out man

			if (reflectedPoint != null) {
				color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkR).scale(kR));
			}

		}
		double kT = geopoint.geometry.getMaterial().getKt();// for the reflection , transparent
		double kkT = k * kT;
		if (kkT > MIN_CALC_COLOR_K) {
			Ray refractedRay = constructRefractedRay(geopoint.point, inRay);
			GeoPoint refractedPoint = findClosestIntersection(refractedRay);
			if (refractedPoint != null)
				// double recursion , calls herself more than twice of each iteration
				color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkT).scale(kT));

		}
		return color;
	}

	private static final double EPS = 1.0;

	/**
	 * transparency means reflection light , this means the power of the reflected
	 * light , by the way ,if there are no object the light will fully intense
	 * 
	 * @param l -        - 
	 * @param n - 
	 * @param geopoint - geopoint 
	 * @return the transparency intense
	 */
	private double transparency(Vector l, Vector n, GeoPoint geopoint) {
		Vector lightDirection = l.scale(-1);// from point to light source

		Vector epsVector = n.scale(EPS);
		Point3D point = geopoint.point.add(epsVector);
		Ray lightRay = new Ray(point, lightDirection);

		List<GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay);

		double ktr = 1;
		if (intersections == null)
			return ktr;
		for (GeoPoint gp : intersections) {
			ktr *= gp.geometry.material.getKt();
		}
		return ktr;
	}
//introduction to the next two function
//according to the energy reserve law the energy turn to many form 
// , in this case it is turned to diffusive and specular lights

	/**
	 * calculates the intensity of the existing ray how much light which is coming
	 * across the geometry actually exit the material the energy is always reserves
	 * that why the energy of the light is dived to several different forms the
	 * specular light is among of them
	 * 
	 * @param kd - diffuse coefficient index
	 * @param l - direction of light source
	 * @param n - geometry's normal direction 
	 * @param lightIntensity -
	 * @return diffusive intensity 
	 */
	public Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
		double diffusive = kd * Math.abs(l.dotProduct(n));
		return lightIntensity.scale(diffusive);
	}

	/**
	 * the energy is always reserves that why the energy of the light is dived to
	 * several different forms the specular light is among of them
	 * 
	 * @param ks - specular coefficient index
	 * @param l - direction of light source
	 * @param n - geometry's normal direction
	 * @param v - the direction of ray from the viewer 
	 * @param shininess - 
	 * @param lightIntensity
	 * @return specular light intensity 
	 */
	public Color calcSpecular(double ks, Vector l, Vector n, Vector v, int shininess, Color lightIntensity) {
		Vector r = l.subtract(n.scale(l.dotProduct(n) * 2)).normalize();
		double temp = v.scale(-1).dotProduct(r);
		if (temp <= 0)
			return new Color(0, 0, 0);
		return lightIntensity.scale(ks * Math.pow(temp, shininess));
	}

	/**
	 * finds to closest intersection of the ray with geometry
	 *
	 * @param points - the intersection point
	 * @return the closest point
	 */
	private GeoPoint getClosestPoint(List<GeoPoint> points) {
		Point3D p0 = _scene.getCamera().getP0();
		double minValue = points.get(0).getPoint().distance(p0);
		GeoPoint minPoint = points.get(0);
		for (int i = 0; i < points.size(); ++i) {
			Point3D p = points.get(i).getPoint();
			double d0 = p.distance(p0);
			if (d0 < minValue) {
				minValue = d0;
				minPoint.point = p;
			}
		}
		return minPoint;
	}

	/**
	 * the function finds the closest intersection point using the getClosestPoint
	 * that take a intersection list of geopoints and return the closest.
	 * 
	 * @param ray in space
	 * @return the closest intersection point in space
	 */
	public GeoPoint findClosestIntersection(Ray ray) {
		return getClosestPoint(_scene.getGeometries().findIntersections(ray));

	}

	/**
	 * calculate the reflected ray by the following formula in the function's body
	 * 
	 * 
	 * @param n     - the normal to the geometry
	 * @param point - intersection point 
	 * @param inRay - a coming ray that contain the point and came across a geometry at point  
	 * @return a ray with the same angle of the ray that start in point that equals
	 *         to the parameter point
	 */
	public Ray constructReflectedRay(Vector n, Point3D point, Ray inRay) {
		Vector v = inRay.getDir();
		// return ray is calculated by the formula
		// r = v -2 *(v dot n)* n
		return new Ray(point, v.add((n.scale(v.dotProduct(n))).scale(-2)));
	}

	/**
	 * according to the Snell law when assume that all geometry have the same
	 * refraction index of 1
	 * 
	 * @param p0    - intersection point with geometry
	 * @param inRay - the coming ray to the object
	 * @return a new ray that start at p0 and has the same direction like inRay
	 */
	public Ray constructRefractedRay(Point3D p0, Ray inRay) {
		Vector v = inRay.getDir();
		return new Ray(p0, v);
	}

	/**
	 * prints a 2D grid on the rendered picture
	 *
	 * @param interval - the number of pixels between each line
	 * @param color    - the color of the grid
	 */
	public void printGrid(int interval, primitives.Color color) {
		// for all the Y (height) pixels in the view plane
		for (int i = 1; i < _image.getNy(); i++) {
			// for all the X (width) pixels in the view plane
			for (int j = 1; j < _image.getNx(); j++)
				// paint the pixel after the interval with the wanted color
				if (i % interval == 0 || j % interval == 0)
					_image.writePixel(j, i, color.getColor());
		}
	}

	/**
	 * saves a 2D image of the rendered scene
	 */
	public void writeToImage() {
		_image.writeToImage();
	}

}

