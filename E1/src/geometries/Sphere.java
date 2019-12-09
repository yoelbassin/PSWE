package geometries;

import primitives.*;

public class Sphere extends RadialGeometry {
	Point3D _center;

	// ***************** Constructors ********************** //
	/**
	 * constructs a sphere with a radius and center point
	 * 
	 * @param _radius, the radius of the sphere
	 * @param _center, the center point of the sphere
	 */
	public Sphere(double _radius, Point3D _center) {
		super(_radius);
		this._center = _center;
	}

	// ***************** Operations ******************** //
	/**
	 * Gets the normal of the sphere at a certain point
	 * 
	 * @param p, the point of the normal on the sphere
	 * @return the normal of the sphere at p
	 */
	@Override
	public Vector getNormal(Point3D p) {
		return p.subtract(_center).normalized();
	}

}
