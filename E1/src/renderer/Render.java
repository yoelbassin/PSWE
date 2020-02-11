package renderer;

import elements.LightSource;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import static geometries.Intersectable.GeoPoint;
import static primitives.Util.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * class represents a renderer for a scene
 */
public class Render {
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;
	private static final double EPS = 0.1;
	private ImageWriter image;
	private Scene scene;
	private int threads = 1;
    int _sampleCount;
	// ***************** Constructors ********************** //

	/**
	 * creates a renderer by the scene (3D model) and a view plane size
	 *
	 * @param image
	 * @param scene
	 */
	public Render(ImageWriter image, Scene scene) {
		this.image = image;
		this.scene = scene;
	}
	
/**
	 * setter of numberof threads
	 *
	 * @param n - the number of threads
	 */
	public void setThreads(int n) {
		threads = n;
	}


    /**
     * setter of the ray samples count
     *
     * @param sampleCount
     */
	public void setSampleCount(int sampleCount)
	{
		sampleCount = _sampleCount;
	}
	public int getSampleCount ()
	{
	    return _sampleCount;
	}
	private volatile int progr = 0;
	private volatile int percentage = 0;
	private int pixels;
	private synchronized void progress() {
		progr += 100;
		int temp = (int)((double)progr / pixels);
		if (temp != percentage) {
			percentage = temp;
			System.out.println("" + percentage + "%");
		}
	}
	// ***************** Operations ******************** //

	/**
	 * renders the scene to a 2D picture
	 */
	public void renderImage() {
		long start = System.currentTimeMillis();
		int nx = image.getNx();
		int ny = image.getNy();
		pixels = nx * ny;
		int cores = Runtime.getRuntime().availableProcessors();
		System.out.println("Cores: " + cores + ", threads: " + threads + ", rows: " + ny + ", pixels: " + pixels);
		ExecutorService exec = threads == 1 ? null : Executors.newFixedThreadPool(threads);
		double distance = scene.getDistance();
		double width = image.getWidth();
		double height = image.getHeight();
		java.awt.Color bg = scene.getBackground().getColor();
		// for all the Y (height) pixels in the view plane
		for (int i = ny - 1; i >= 0; --i) {
			int fi = i;
			if (threads > 1)
				exec.execute(() -> {
					// for all the X (width) pixels in the view plane
					for (int j = nx - 1; j >= 0; --j) {
						// construct ray trough pixel [j,i]
						Ray ray = scene.getCamera().constructRayThroughPixel(nx, ny, //
								j, fi, distance, width, height);
						GeoPoint closestPoint = findClosestIntersection(ray);
						image.writePixel(j, fi, //
								closestPoint == null ? bg : calcColor(closestPoint, ray).getColor());
						//progress();
					}
					//System.out.println("Row " + fi + ", thread# " + Thread.currentThread().getId());
				});
			else {
				for (int j = nx - 1; j >= 0; --j) {
					// construct ray trough pixel [j,i]
					Ray ray = scene.getCamera().constructRayThroughPixel(nx, ny, //
							j, fi, distance, width, height);
					GeoPoint closestPoint = findClosestIntersection(ray);
					image.writePixel(j, fi, //
							closestPoint == null ? bg : calcColor(closestPoint, ray).getColor());
					//progress();
				}
				//System.out.println("Row " + fi + ", thread# " + Thread.currentThread().getId());
			}
		}

		if (threads > 1) {
			exec.shutdown();
			boolean stopped = false;
			int minutes = 0;
			do {
				try {
					stopped = exec.awaitTermination(1, TimeUnit.MINUTES);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//System.out.println("Still working... " + ++minutes + " minutes");
			} while (!stopped);
		}
		long finish = System.currentTimeMillis();
		long elapsed = finish - start;
		
		double seconds = (double)elapsed / 1000;
		int minutes = (int)(seconds / 60);
		double secs = seconds - minutes * 60;
		int hours = minutes / 60;
		minutes %= 60;
		System.out.println("Done in " + hours + "h " + minutes + "m " + secs + "s (total " + seconds + "s)");
	}

	/**
	 * prints a 2D grid on the rendered picture
	 *
	 * @param interval - the number of pixels between each line
	 * @param color    - the color of the grid
	 */
	public void printGrid(int interval, primitives.Color color) {
		// for all the Y (height) pixels in the view plane
		for (int i = 1; i < image.getNy(); i++) {
			// for all the X (width) pixels in the view plane
			for (int j = 1; j < image.getNx(); j++)
				// paint the pixel after the interval with the wanted color
				if (i % interval == 0 || j % interval == 0)
					image.writePixel(j, i, color.getColor());
		}
	}

	/**
	 * saves a 2D image of the rendered scene
	 */
	public void writeToImage() {
		image.writeToImage();
	}

	/*********** CalcColor function *************/

	/**
	 * calculates the color at a certain point (pixel) by calling calculator
	 * function
	 *
	 * @param - GeoPoint in the space (pixel on the view plane)
	 * @return the color of the point
	 */
	private Color calcColor(GeoPoint geopoint, Ray inRay) {
		return calcColor(geopoint, inRay, MAX_CALC_COLOR_LEVEL, 1.0).add(scene.getAmbient().getIntensity());
	}

	/**
	 * calculates the color at a certain point (pixel)
	 *
	 * @param - GeoPoint in the space (pixel on the view plane)
	 * @return the color of the point
	 */
	private Color calcColor(GeoPoint geopoint, Ray inRay, int level, double k) {
		if (level == 0 || k < MIN_CALC_COLOR_K)
			return Color.BLACK;
		Color color = geopoint.geometry.getEmission(); // remove Ambient Light

		Vector v = geopoint.point.subtract(scene.getCamera().getP0()).normalize();
		Material mat = geopoint.geometry.getMaterial();
		Vector n = geopoint.geometry.getNormal(geopoint.point);
		double kd = mat.getKd();
		double ks = mat.getKs();
		int nShininess = mat.getShininess();
		for (LightSource lightSource : scene.getLights()) {
			Vector l = lightSource.getL(geopoint.point);
			double e1 = n.dotProduct(l);
			double e2 = n.dotProduct(v);
			if ((e1 > 0 && e2 > 0) || (e1 < 0 && e2 < 0)) {
				double ktr = transparency(l, n, geopoint, lightSource.getDist(geopoint.point));
				if (ktr * k > MIN_CALC_COLOR_K) {
					Color lightIntensity = lightSource.getIntensity(geopoint.point).scale(ktr);
					color = color.add(calcDiffusive(kd, l, n, lightIntensity),
							calcSpecular(ks, l, n, v, nShininess, lightIntensity));
				}
			}
		}
		double kr = mat.getKr();
		double kkr = k * kr;
		if (kkr > MIN_CALC_COLOR_K) {
			Ray reflectedRay = constructReflectedRay(n, geopoint.point, inRay);
			color = color.add(calcSampledColor(mat.getMatte(), //
					level, reflectedRay, n, kkr).scale(kr));
		}
		double kt = geopoint.geometry.getMaterial().getKt();
		double kkt = k * kt;
		if (kkt > MIN_CALC_COLOR_K) {
			Ray refractedRay = constructRefractedRay(n, geopoint.point, inRay);
			color = color.add(calcSampledColor(mat.getDiffusion(), //
					level, refractedRay, n, kkt).scale(kt));
		}
		return color;
	}

	//make d can be changed
	private int  d = 10 ;
	/**
	 * setter of parameter d
	 * @param distance - p0pc the distance in which 
	 * we make create cycle of random rays
	 */
	public void setterD(int distance)
	{
		d = distance;
	}
	private static final Random RANDOM = new Random(System.currentTimeMillis());

	/**
	 * calculates the matte effect by an average of the perturbed rays
	 *
	 * @return color of a pixel
	 */
	private Color calcSampledColor(double radius, int level, Ray refRay, Vector n, double k) {
		Point3D p0 = refRay.getBasePoint();
		Vector dir = refRay.getDir();
		Point3D head = dir.getHead();
		double ndir = alignZero(n.dotProduct(dir));
		if (ndir == 0)
			return Color.BLACK;

		GeoPoint gp = findClosestIntersection(refRay);
		Color bg = scene.getBackground();
		Color color = gp == null ? bg : calcColor(gp, refRay, level - 1, k);

		if (radius == 0)
			return color;
		int samples = this.getSampleCount();
		if (samples == 0)
			return color;

		double x = head.getX().get();
		double y = head.getY().get();
		double z = head.getZ().get();

		Vector v = null;
		if (x <= y) {
			if (x <= z)
				v = new Vector(0, z, -y).normalize();
			else
				v = new Vector(-y, x, 0).normalize();
		} else {
			if (y <= z)
				v = new Vector(-z, 0, x).normalize();
			else
				v = new Vector(-y, x, 0).normalize();
		}
		Vector u = v.crossProduct(dir).normalize();

		 // HARDCODED ?!?!?!
		Point3D pc = p0.add(dir.scale(d));
		for (int i = this.getSampleCount() - 1; i > 0; --i) {
			Point3D p;
			Vector w;
			double nw;
			do {
				p = pc;
				x = RANDOM.nextDouble() * 2 - 1;
				y = Math.sqrt(1 - x * x);
				if (!isZero(x))
					p = p.add(v.scale(x));
				if (!isZero(y))
					p = p.add(u.scale(y));
				z = radius * (RANDOM.nextDouble() * 2 - 1);
				p = pc.add(p.subtract(pc).scale(z));
				w = p.subtract(p0);
				nw = alignZero(n.dotProduct(w));
			} while (ndir > 0 && nw < 0 || ndir < 0 && nw > 0);

			Ray ray = new Ray(p0, w);
			gp = findClosestIntersection(ray);
			color = color.add(gp == null ? bg : calcColor(gp, ray, level - 1, k));
		}
		return color.reduce(samples + 1);
	}

	/**
	 * the function calculates the diffusion of the light on the geometry
	 *
	 * @param kd             - the diffusive coefficient index
	 * @param l              - the direction of the light source
	 * @param n              - the normal to the geometry
	 * @param lightIntensity - the intensity of the light
	 * @return the diffused intensity of the light
	 */
	private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
		double diffusive = kd * Math.abs(l.dotProduct(n));
		return lightIntensity.scale(diffusive);
	}

	/**
	 * the function calculates the specular reflection of the light on the geometry
	 *
	 * @param ks             - the specular coefficient index
	 * @param l              - the direction of the light
	 * @param n              - the normal to the geometry
	 * @param v              - the direction from the camera to the geometry
	 * @param shininess      - the shininess index
	 * @param lightIntensity - the intensity of the light
	 * @return the specular reflection intensity
	 */
	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int shininess, Color lightIntensity) {
		Vector r = l.subtract(n.scale(l.dotProduct(n) * 2)).normalize();
		double temp = v.scale(-1).dotProduct(r);
		if (temp <= 0)
			return new Color(0, 0, 0);
		return lightIntensity.scale(ks * Math.pow(temp, shininess));
	}

	/**
	 * the function calculates the transparency of the geometry ang the point
	 *
	 * @param l        - the direction of the light source
	 * @param n        - the normal to the geometry
	 * @param geopoint - the transparency in the point
	 * @return the transparency intense
	 */
	private double transparency(Vector l, Vector n, GeoPoint geopoint, double d) {
		Vector lightDirection = l.scale(-1); // from point to light source
		Vector epsVector = n.scale(n.dotProduct(lightDirection) > 0 ? EPS : -EPS);
		Point3D point = geopoint.point.add(epsVector);
		Ray lightRay = new Ray(point, lightDirection);
		List<GeoPoint> intersections = scene.getGeometries().findIntersections(lightRay);
		if (intersections == null)
			return 1;
		double ktr = 1;
		for (GeoPoint gp : intersections)
			if (gp.point.distance(geopoint.point) <= d)
				ktr *= gp.geometry.getMaterial().getKt();
		return ktr;
	}

	/**
	 * the function calculates the reflected ray from the geometry
	 *
	 * @param n     - the normal to the geometry
	 * @param point - the intersection point
	 * @param inRay - the ray to the point
	 * @return the reflected ray
	 */
	private Ray constructReflectedRay(Vector n, Point3D point, Ray inRay) {
		Vector v = inRay.getDir();
		Vector r = v.subtract(n.scale(v.dotProduct(n) * 2));
		Point3D newPoint = point.add(n.scale(n.dotProduct(r) > 0 ? EPS : -EPS));
		Ray ray = new Ray(newPoint, r);
		if (ray.getDir().dotProduct(n) > 0)
		 return ray;
		return null;
	}

	/**
	 * the function refracts the ray by Snell's law
	 *
	 * @param point - the intersection point
	 * @param inRay - the ray to the point
	 * @return the refracted ray
	 */
	private Ray constructRefractedRay(Vector n, Point3D point, Ray inRay) {
		Vector v = inRay.getDir();
		Point3D newPoint = point.add(n.scale(n.dotProduct(v) > 0 ? EPS : -EPS));
		return new Ray(newPoint, v);
	}

	/**
	 * finds closest intersection of the ray with geometry
	 *
	 * @param points - the intersection point
	 * @return the closest point
	 */
	/*
	 * private GeoPoint getClosestPoint(List<GeoPoint> points) { Point3D p0 =
	 * scene.getCamera().getP0(); return pointClosestTo(points, p0); }
	 */

	/**
	 * the function finds the closest intersection point using the getClosestPoint
	 * that take a intersection list of geopoints and return the closest.
	 *
	 * @param ray in space
	 * @return the closest geometry intersection point to the camera
	 */
	private GeoPoint findClosestIntersection(Ray ray) {
		List<GeoPoint> points = scene.getGeometries().findIntersections(ray);
		if (points == null)
			return null;
		Point3D p0 = ray.getBasePoint();
		double minValue = Double.POSITIVE_INFINITY;
		GeoPoint minPoint = null;
		for (GeoPoint p : points) {
			double d0 = p.point.distance(p0);
			if (d0 < minValue) {
				minValue = d0;
				minPoint = p;
			}
		}
		return minPoint;
	}
}
