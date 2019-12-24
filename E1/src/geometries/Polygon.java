package geometries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static primitives.Util.*;

import primitives.*;

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

    public List<Point3D> findIntersections(Ray ray) {
        for (int i = 0; i < _points.size(); ++i) {
            Triangle polygon = new Triangle(_points.get(0), _points.get(i + 1), _points.get(i + 2));
            if(polygon.findIntersections(ray) != null)
                return polygon.findIntersections(ray);
        }
        return null;
    }
}
