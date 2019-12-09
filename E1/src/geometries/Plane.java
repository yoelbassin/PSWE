package geometries;

import primitives.*;

public class Plane implements Geometry {

	private Point3D _p;
	private Vector _normal;

	// ***************** Constructors ********************** //
	/**
	 * Constructs a plane with a point and normal vector
	 * 
	 * @param _p,      the base point of the normal
	 * @param _normal, the normal vector to the plane
	 */
	public Plane(Point3D _p, Vector _normal) {
		this._p = _p;
		this._normal = _normal.normalized();
	}

	/**
	 * Constructs a plane using three points in the space
	 * 
	 * @param p1, first point
	 * @param p2, second point
	 * @param p3, third point
	 */
	public Plane(Point3D p1, Point3D p2, Point3D p3) {
		this._p = p1;
		this._normal = ((p3.subtract(p1)).crossProduct(p2.subtract(p1))).normalize();
	}
	// ***************** Getters/Setters ********************** //

	/**
	 * Gets the normal of the plane at a certain point
	 * 
	 * @param p, the point of the normal
	 * @return the normal of the plane
	 */
	public Vector getNormal(Point3D p) {
		return _normal;
	}

	/**
	 * Gets the normal of the plane
	 * 
	 * @return the normal of the plane
	 */
	public Vector getNormal() {
		return _normal;
	}

}
