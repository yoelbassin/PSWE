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
		Point3D basePoint = super.get_axisRay().getBasePoint();
		Point3D topPoint = basePoint.add(super.get_axisRay().getVector().scale(_height));
		if(p.distance(basePoint) < super.getRadius() || p.distance(topPoint) < super.getRadius())
		{
			return super.get_axisRay().getVector();
		}
		return super.getNormal(p);
	}

}
