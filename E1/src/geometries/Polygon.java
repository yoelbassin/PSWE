package geometries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static primitives.Util.*;

import primitives.*;

/**
 * Class represents 3D space polygon with more than three vertexes.
 *
 * @author bassi
 * @author asaf0
 */
public class Polygon implements Geometry {
    List<Point3D> _points;
    Plane _plane;
    // ***************** Constructors ********************** //

    /**
     * Constructs a polygon from set of points - polygon's vertices. NB: the points
     * must be in the same plane
     *
     * @param points vertices
     * @throws IllegalArgumentException if less than 3 points or points are not in
     *                                  the same plane
     */
    public Polygon(Point3D... points) {
        if (points.length < 3)
            throw new IllegalArgumentException("Polygon must have at least 3 vertices");
        Point3D p1 = points[0];
        Point3D p2 = points[1];
        Point3D p3 = points[2];
        _plane = new Plane(p1, p2, p3);
        Vector n = _plane.getNormal();
        for (int i = 3; i < points.length; ++i)
            if (!isZero(p1.subtract(points[i]).dotProduct(n)))
                throw new IllegalArgumentException("Polygon's vertices must resize in the same plane");
        _points = Arrays.asList(points);
    }
    // ***************** Operations ******************** //

    /**
     * Gets the normal of the polygon at a certain point
     *
     * @return the normal of the plane of the polygon
     */
    public Vector getNormal(Point3D p) {
        return _plane.getNormal(p);
    }

    /**
     * finds intersections of the ray with the polygon
     *
     * @param ray
     * @return intersection point
     */
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersections = this._plane.findIntersections(ray);
        if (intersections == null) //if there are no intersections with the plane
            return null;
        Point3D intersectionPoint = intersections.get(0);
        for (int i = 1; i < _points.size() - 1; ++i) {
            Point3D p1 = this._points.get(0);
            Point3D p2 = this._points.get(i);
            Point3D p3 = this._points.get(i + 1);
            if (intersectionPoint.equals(p1) || intersectionPoint.equals(p2) || intersectionPoint.equals(p3)) //if the intersection point is one of the vertexes, return the vertex
                return intersections;
            Vector s1 = p1.subtract(intersectionPoint).normalize();
            Vector s2 = p2.subtract(intersectionPoint).normalize();
            Vector s3 = p3.subtract(intersectionPoint).normalize();
            if (s1.equals(s2) || s2.equals(s3) || s3.equals(s1)) //if the intersection point is on the axis of a side outside the triangle return null
                return null;
            if (s1.equals(s2.scale(-1)) || s2.equals(s3.scale(-1)) || s3.equals(s1.scale(-1))) //if the intersection point is on the side, return the intersection point
                return intersections;
            double area = 0.5 * (p1.subtract(p2).crossProduct(p1.subtract(p3))).length();
            double area1 = 0.5 * (intersectionPoint.subtract(p1).crossProduct(intersectionPoint.subtract(p2))).length();
            double area2 = 0.5 * (intersectionPoint.subtract(p2).crossProduct(intersectionPoint.subtract(p3))).length();
            double area3 = 0.5 * (intersectionPoint.subtract(p3).crossProduct(intersectionPoint.subtract(p1))).length();
            if (area1 + area2 + area3 == area) //if the area created by the three vertexes and the intersection point is equal to the original area of the triangle
                return intersections;
        }
        return null;
    }
}

