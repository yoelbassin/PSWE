package geometries;

import primitives.*;

public class Tube extends RadialGeometry {

	Ray _axisRay;

	public Tube(Ray axis, double _radius) {
		super(_radius);
		_axisRay = axis;
	}

	@Override
	public Vector getNormal(Point3D p) {
		Point3D basePoint = _axisRay.getHead();
		double a = basePoint.distance2(p);
		double centerLength = a - _radius * _radius;
		Ray scaledRay = _axisRay.scale(centerLength);
		Point3D scaledHead = scaledRay.getHead();
		Point3D center = new Point3D(scaledHead.getX().add(basePoint.getX()).get(),
				scaledHead.getY().add(basePoint.getY()).get(), scaledHead.getZ().add(basePoint.getZ()).get());
		return p.subtract(center).normalize();
	}

}
