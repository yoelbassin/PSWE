package geometries;

import primitives.*;

public class Tube extends RadialGeometry {

	Ray _axisRay;

	public Tube(Ray axis, double _radius) {
		super(_radius);
		_axisRay = axis;
	}

	public Ray get_axisRay() {
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
		Point3D basePoint = _axisRay.getBasePoint();
		double a = basePoint.distance2(p);
		double centerLength = Math.sqrt(a - _radius * _radius);
		Vector newVec = _axisRay.getVector().scale(centerLength);
		Point3D center = basePoint.add(newVec);
		return p.subtract(center).normalize();
	}

}
