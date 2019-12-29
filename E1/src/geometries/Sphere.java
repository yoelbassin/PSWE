package geometries;

import java.lang.Math;
import java.util.ArrayList;
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
     * @param ray
     * @return list of intersection points
     */
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> Intersections = new ArrayList<Point3D>();
        //The name of the variables are taken
        Point3D basePoint = ray.getBasePoint();
        Vector v = ray.getDir();
        if(basePoint.equals(_center)) {
            Point3D p = basePoint.add(v.scale(this._radius));
            Intersections.add(p);
            return Intersections;
        }
        Vector u = _center.subtract(ray.getBasePoint()); // O - P0
        double tm = v.dotProduct(u); // tm = v*u
        double d = Math.sqrt(u.length2() - tm * tm); // (|u|^2 - tm^2)^(0.5)
        if (d >_radius)
            return null;
        double th = Math.sqrt(_radius * _radius - d * d);
        double t1 = tm + th; // tm + th
        if (t1 > 0) {
            // P = p0 + t1 * v
            Point3D p = basePoint.add(v.scale(t1));
            Intersections.add(p);
        }
        if (t1 == 0)
            Intersections.add(basePoint);
        double t2 = tm - th; // tm - th
        if (t2 > 0) {
            // P = p0 + t2 * v
            Point3D p = basePoint.add(v.scale(t2));
            Intersections.add(p);
        }
        if (t2 == 0)
            Intersections.add(basePoint);
		if(t1 >= 0 || t2 >= 0)
			return Intersections;
		return null;
    }
}
