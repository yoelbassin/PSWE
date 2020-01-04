package geometries;

import java.util.Arrays;
import java.util.List;

import primitives.*;

/**
 * Class represents 3D space plane with a normal and a 3D space point in the plane.
 *
 * @author bassi
 * @author asaf0
 */
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
     * @param p1 is first point
     * @param p2 is second point
     * @param p3 is third point
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        this._p = p1;
        this._normal = ((p3.subtract(p1)).crossProduct(p2.subtract(p1))).normalize();
    }
    // ***************** Getters/Setters ********************** //


    /**
     * Gets the normal of the plane at a certain point
     *
     * @param p is the point of the normal
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
    // ***************** Operations ******************** //

    /**
     * finds intersections of the ray with the plane
     *
     * @param ray
     * @return intersection point
     */
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersections;
        Point3D basePoint = ray.getBasePoint();
        Vector dir = ray.getDir();
        if (this._normal.dotProduct(dir) == 0) // if the ray is ray in parallel to the plane (orthogonal to the normal) return null
            return null;
        if (basePoint.equals(this._p)) // if the ray's base is equal to the origin point of the plane return null
            return null;
        double t = this._normal.dotProduct(this._p.subtract(basePoint)) / this._normal.dotProduct(dir);
        if (t <= 0) // if the plane is behind the ray, or the ray's base is on the plane return null
            return null;
        return intersections = Arrays.asList(basePoint.add(dir.scale(t))); //return the intersection
    }
}
