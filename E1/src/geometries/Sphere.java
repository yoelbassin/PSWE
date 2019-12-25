package geometries;

import java.lang.Math;
import java.util.List;

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
	public Vector getNormal(Point3D p) {
		return p.subtract(_center).normalized();
	}

	/**
	 * Find the intersection points of a ray and a sphere 
	 * 
	 * @param a ray 
	 * @return list of intersection points
	 */
	public List<Point3D> findIntersections(Ray ray) {
		List<point3D> Intersections = null;
		//The name of the variables are taken   
	    Vector u = _center.subtract(ray.p) ; // O - P0
		tm = ray.dir.dotProduct(u); // tm = v*u
		double d = sqrt(u.length2() - t1 * t1); // (|u|^2 - tm^2)^(0.5)	
		
		if (d >_radius)
			return Intersections;
		
		th = sqrt(_radius * _radius -  d * d);
	  
		t1= t3 + t4; // tm + th
		if(tm>0)
		{
			// P = p0 + t1 * v
			Point3D p = ray.p.add(ray.dir.scale(t1));
		    Intersections.add(p)
		}
		t2 = t3 -t4; // tm - th
		if(t2>0)
		{
			 // P = p0 + t2 * v
			Point3D p = ray.p.add(ray.dir.scale(t2));
			Intersections.add(p)
		}
		
		return Intersections;
	}
}
