package geometries;

import primitives.*;

public class Cylinder extends Tube {
	double _height;

	public Cylinder(double _radius, double _height, Ray _axis) {
		super(_axis, _radius);
		this._height = _height;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Vector getNormal(Point3D p) {
		return null;
	}

}
