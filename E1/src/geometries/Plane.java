package geometries;

import primitives.*;

public class Plane implements Geometry {

	Point3D _p;
	Vector _normal;

	public Plane(Point3D _p, Vector _normal) {
		this._p = _p;
		this._normal = _normal.normalize();
	}

	public Plane(Point3D _p1, Point3D _p2, Point3D _p3) {
		this._p = _p1;
		this._normal = _p2.subtract(_p1).crossProduct(_p3.subtract(_p1)).normalize();
	}

	public Vector getNormal(Point3D p) {
		return _normal;
	}

	public Vector getNormal() {
		return _normal;
	}

}