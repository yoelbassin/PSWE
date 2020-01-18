package geometries;
import static primitives.Util.*;

import java.util.List;
import primitives.*;
/**
 * class Tube represents a tube in the space
 */


public class Tube extends RadialGeometry {

	Ray _axisRay;
	// ***************** Constructors ********************** //

	/**
	 * constructs a tube from axis and radius
	 *
	 * @param axis,   the axis of the tube
	 * @param radius, the radius of the tube
	 */
	public Tube(Ray axis, double radius) {
		super(radius);
		_axisRay = axis;
	}
	// ***************** Getters/Setters ********************** //

	/**
	 * Gets the axis of the tube
	 *
	 * @return axis of the tube
	 */
	public Ray getAxisRay() {
		return _axisRay;
	}
	// ***************** Operations ******************** //

	/**
	 * Gets the normal of the tube at a certain point
	 *
	 * @param p, the point of the normal
	 * @return the normal of the tube
	 */
	public Vector getNormal(Point3D p) {
		Point3D p0 = _axisRay.getBasePoint();
		Vector v = _axisRay.getDir();

		Vector u = null;
		try {
			u = p.subtract(p0); // vector from p0 to p
		} catch (Exception e) {
			return v;
		}

		double t = alignZero(v.dotProduct(u)); // size of projection of vector u on the ray
		if (t == 0)
			return p.subtract(p0).normalize();

		// point on the ray and plane crossing P and orthogonal to the ray
		Point3D o = p0.add(v.scale(t));
		return p.subtract(o).normalize();
	}

	public List<GeoPoint> findIntersections(Ray ray) {
		return null;
	}
}
