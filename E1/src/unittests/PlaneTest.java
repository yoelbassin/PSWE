package unittests;

import geometries.*;

import static org.junit.Assert.*;

import primitives.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import static geometries.Intersectable.GeoPoint;
/**
 * Test class of Polygon {@link geometries.Plane}}
 */
public class PlaneTest {

    /**
     * test Method for {@link geometries.Plane#getNormal (geomtries.Plane)}
     */
    @Test
    public void testGetNormal() {
        Point3D p = new Point3D(0, 0, 0);
        Vector vec = new Vector(0, 1, 0);
        Plane plane = new Plane(p, vec);
        assertEquals("Get normal function error", vec, plane.getNormal());
    }

    static Point3D p = new Point3D(1, 1, 0);
    static Vector dir = new Vector(0, 0, 1);

    /**
     * test Method for {@link geometries.Plane#findIntersections (geomtries.Plane)}
     */
    @Test
    public void testFindIntersections() {
        Plane plane = new Plane(p, dir);
        Ray ray = new Ray(new Point3D(0, 0, 1), new Vector(1, 1, -1));
        List<GeoPoint> intersections = Arrays.asList(new GeoPoint(plane, new Point3D(1, 1, 0)));
        assertEquals("Find intersections function error", intersections, plane.findIntersections(ray)); // EP ray
        // intersects
        // with the
        // plane
        ray = new Ray(new Point3D(0, 0, 1), new Vector(1, 1, 1));
        assertEquals("Find intersections function error", null, plane.findIntersections(ray)); // EP ray does not
        // intersect with the
        // plane
        ray = new Ray(new Point3D(0, 0, 0), new Vector(1, 1, 0));
        assertEquals("Find intersections function error", null, plane.findIntersections(ray)); // BVA ray is parallel
        // and included in the
        // plane
        ray = new Ray(new Point3D(0, 0, 1), new Vector(1, 1, 0));
        assertEquals("Find intersections function error", null, plane.findIntersections(ray)); // BVA ray is parallel
        // and not included in
        // the plane
        ray = new Ray(new Point3D(0, 0, 1), new Vector(0, 0, 1));
        assertEquals("Find intersections function error", null, plane.findIntersections(ray)); // BVA ray is orthogonal
        // and after p0
        ray = new Ray(new Point3D(0, 0, -1), new Vector(0, 0, 1));
        intersections = Arrays.asList(new GeoPoint(plane,new Point3D(0, 0, 0)));
        assertEquals("Find intersections function error", intersections, plane.findIntersections(ray)); // BVA ray is
        // orthogonal
        // and before p0
        ray = new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 1));
        assertEquals("Find intersections function error", null, plane.findIntersections(ray)); // BVA ray is orthogonal
        // and in p0
        ray = new Ray(new Point3D(0, 0, 0), new Vector(1, 1, 1));
        assertEquals("Find intersections function error", null, plane.findIntersections(ray)); // BVA ray begins in the
        // plane
        ray = new Ray(new Point3D(1, 1, 0), new Vector(1, 1, 1));
        intersections = Arrays.asList(new GeoPoint(plane,new Point3D(1, 1, 0)));
        assertEquals("Find intersections function error", null, plane.findIntersections(ray)); // BVA ray begins in the
        // reference point of
        // the plane
    }
}
