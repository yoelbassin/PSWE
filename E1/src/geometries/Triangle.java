package geometries;

import java.util.List;

import primitives.*;

public class Triangle extends Polygon {
    // ***************** Constructor ********************** //

    /**
     * constructs a triangle from three points
     *
     * @param p1, first point
     * @param p2, second point
     * @param p3, third point
     */
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1, p2, p3);
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        if (this._plane.findIntersections(ray) == null) //if there are no intersections with the plane
            return null;
        Point3D p1 = this._points.get(0);
        Point3D p2 = this._points.get(1);
        Point3D p3 = this._points.get(2);
        List<Point3D> intersections = this._plane.findIntersections(ray);
        Point3D intersectionPoint = intersections.get(0);
        Point3D p0 = ray.getBasePoint();
        if (p0.equals(intersectionPoint))
            return null;
        Vector v1 = p1.subtract(p0);
        Vector v2 = p2.subtract(p0);
        Vector v3 = p3.subtract(p0);
        Vector N1 = v1.crossProduct(v2).normalize();
        Vector N2 = v2.crossProduct(v3).normalize();
        Vector N3 = v3.crossProduct(v1).normalize();
        Vector p = intersectionPoint.subtract(p0);
        if (p.dotProduct(N1) > 0 && p.dotProduct(N2) > 0 && p.dotProduct(N3) > 0)
            return intersections;
        if (p.dotProduct(N1) < 0 && p.dotProduct(N2) < 0 && p.dotProduct(N3) < 0)
            return intersections;
        return null;
    }
}
