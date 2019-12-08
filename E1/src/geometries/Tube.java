package geometries;

import primitives.*;
import static primitives.Util.*;

public class Tube extends RadialGeometry {

	Ray _axisRay;

	public Tube(Ray axis, double radius) {
		super(radius);
		_axisRay = axis;
	}

	public Ray getAxisRay() {
		return _axisRay;
	}

	/*
	 * 1. getting the length from the point to the base point of the ray. 2. getting
	 * the distance from the base point to the variable 'center' - the center of the
	 * tube in the plane of our new normal 3. creating a vector with the length of
	 * the distance we got 4. moving the vector to the base point of the ray and
	 * getting the head (the 'center') 5. creating a normal from the 'center' to the
	 * point
	 */
	@Override
	public Vector getNormal(Point3D p) {
		Point3D p0 = _axisRay.getBasePoint();
		Vector v = _axisRay.getDir();
		if (p.equals(p0))
			return v;
		Vector u = p.subtract(p0); // vector from p0 to p
		double t = v.dotProduct(u); // size of projection of vector u on the ray
		// point on the ray and plane crossing P and orthogonal to the ray
		Point3D o;
		if (isZero(t))
			o = p0;
		else
			o = p0.add(v.scale(t));

		return p.subtract(o).normalize();
	}

}
