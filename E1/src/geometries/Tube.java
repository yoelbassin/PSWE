package geometries;

import primitives.*;
import static primitives.Util.*;

import java.util.List;

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
		if (p.equals(p0))
			return v;
		Vector u = p.subtract(p0); // vector from p0 to p
		double t = v.dotProduct(u); // size of projection of vector u on the ray
		// point on the ray and plane crossing P and orthogonal to the ray
		Point3D o;
		if (isZero(t)) // if the point is on the base of the tube
			o = p0;
		else
			o = p0.add(v.scale(t));

		return p.subtract(o).normalize();
	}

	public List<Point3D> findIntersections(Ray ray) {
		return null;
	}
}
