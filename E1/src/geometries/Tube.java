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
		return null;
	}

}
