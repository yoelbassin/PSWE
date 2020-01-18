package geometries;

import static primitives.Util.isZero;

import primitives.*;

public class Cylinder extends Tube {
	double _height;

	// ***************** Constructors ********************** //
	/**
	 * Constructs a cylinder with radius, height and axis ray
	 *
	 * @param double _radius, radius of the cylinder
	 * @param double _height, height of the cylinder
	 * @param Ray    _axis, axis of the cylinder
	 * @throws new IllegalException when radius is smaller than zero
	 */
	public Cylinder(double _radius, double _height, Ray _axis) {
		super(_axis, _radius);
		if (_height > 0)
			this._height = _height;
		else {
			throw new IllegalArgumentException("Radius is smaller than zero");
		}
	}

	// ***************** Operations ******************** //
	/**
	 * Gets the direction of the normal vector of the cylinder at a certain point
	 *
	 * @param p, the point on the cylinder
	 * @return the direction of the normal vector
	 */
	@Override
	public Vector getNormal(Point3D p) {
		Vector v = _axisRay.getDir();
		Point3D p0 = _axisRay.getBasePoint();
		if (p.equals(p0))
			return v;
		Vector u = p.subtract(p0); // vector from p0 to p
		double t = v.dotProduct(u); // size of projection of vector u on the ray
		// point on the ray and plane crossing P and orthogonal to the ray
		if (isZero(t) || isZero(t - this._height)) {
			return v;
		}
		return p.subtract(p0.add(v.scale(t))).normalize();
	}
}
