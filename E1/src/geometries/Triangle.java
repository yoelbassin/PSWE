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
        if (area1 + area2 + area3 == area)
            return intersections;
        return null;
    }
}
