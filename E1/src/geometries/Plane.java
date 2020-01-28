package geometries;

import java.util.Arrays;
import java.util.List;

import primitives.*;

import static primitives.Util.*;

/**
 * Class represents 3D space plane with a normal and a 3D space point in the
 * plane.
 *
 * @author bassi
 * @author asaf0
 */
public class Plane extends Geometry {

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

    /**
     * Constructs a plane with a point, normal vector and a color
     *
     * @param emission the color of the plane
     * @param _p,      the base point of the normal
     * @param _normal, the normal vector to the plane
     */
    public Plane(Color emission, Point3D _p, Vector _normal) {
        this(_p, _normal);
        this.emission = emission;
    }

    /**
     * Constructs a plane using three points in the space and a color
     *
     * @param emission the color of the plane
     * @param p1       is first point
     * @param p2       is second point
     * @param p3       is third point
     */
    public Plane(Color emission, Point3D p1, Point3D p2, Point3D p3) {
        this(p1, p2, p3);
        this.emission = emission;
    }

    /**
     * Constructs a plane with a point, normal vector and a color
     *
     * @param emission the color of the plane
     * @param material the material of the plane
     * @param _p,      the base point of the normal
     * @param _normal, the normal vector to the plane
     */
    public Plane(Color emission, Material material, Point3D _p, Vector _normal) {
        this(emission, _p, _normal);
        this.material = material;
    }

    /**
     * Constructs a plane using three points in the space and a color
     *
     * @param emission the color of the plane
     * @param material the material of the plane
     * @param p1       is first point
     * @param p2       is second point
     * @param p3       is third point
     */
    public Plane(Color emission, Material material, Point3D p1, Point3D p2, Point3D p3) {
        this(emission, p1, p2, p3);
        this.material = material;
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
    public List<GeoPoint> findIntersections(Ray ray) {
        Point3D p0 = ray.getBasePoint();
        Vector v = ray.getDir();
        double vn = alignZero(this._normal.dotProduct(v));
        // if the ray is ray in parallel to the plane (orthogonal to the normal) return
        // null
        if (vn == 0)
            return null;
        Vector pp0 = null;
        try {
            pp0 = this._p.subtract(p0);
        } catch (Exception e) {
            // if the ray's base is equal to the origin point of the plane return null
            return null;
        }
        double t = alignZero(this._normal.dotProduct(pp0) / vn);
        // if the plane is behind the ray, or the ray's base is on the plane return null
        return (t <= 0) ? null : Arrays.asList(new GeoPoint(this, p0.add(v.scale(t)))); // return the intersection
    }
}
