package geometries;

import primitives.*;

public class Plane implements Geometry {

	private Point3D _p;
	private Vector _normal;

	public Plane(Point3D _p, Vector _normal) {
		this._p = _p;
		this._normal = _normal.normalized();
	}

	public Plane(Point3D p1, Point3D p2, Point3D p3) {
		this._p = p1;
		this._normal = ((p3.subtract(p1)).crossProduct(p2.subtract(p1))).normalize();
	}

	public Vector getNormal(Point3D p) {
		return _normal;
	}

	public Vector getNormal() {
		return _normal;
	}

}