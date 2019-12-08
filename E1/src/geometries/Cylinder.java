package geometries;

import static primitives.Util.isZero;

import primitives.*;

public class Cylinder extends Tube {
	double _height;

	public Cylinder(double _radius, double _height, Ray _axis) {
		super(_axis, _radius);
		if (_height > 0)
			this._height = _height;
		else {
			throw new IllegalArgumentException("Radius is smaller than zero");
		}
		// TODO Auto-generated constructor stub
	}

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
