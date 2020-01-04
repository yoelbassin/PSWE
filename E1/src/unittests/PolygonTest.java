package unittests;

import static org.junit.Assert.*;

import primitives.*;
import geometries.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class PolygonTest {
    static Point3D p1 = new Point3D(0, 0, 0);
    static Point3D p2 = new Point3D(0, 1, 0);
    static Point3D p3 = new Point3D(1, 0, 0);

    /**
     * test Method for {@link geometries.Polygon (geomtries.Polygon)}
     */
    @Test
    public void testConstructor() {
        // ****** EP ******//
        try {
            // Test of the polygon constructor
            Point3D p4 = new Point3D(1, 1, 0);
            Polygon polygon = new Polygon(p1, p2, p3, p4);
            assertTrue(true);
        } catch (IllegalArgumentException e) {
            fail("threw an unwanted exception");
        }
        // ****** BVA ******//
        try {
            Point3D p4 = new Point3D(1, 1, 3);
            Polygon polygon = new Polygon(p1, p2, p3, p4);
            fail("didn't throw exception"); // BVA constructing a polygon with a point not on the same plane as the
            // others
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        try {
            Point3D p4 = new Point3D(1, 1, -3);
            Polygon polygon = new Polygon(p1, p2, p3, p4);
            fail("didn't throw exception"); // BVA constructing a polygon with a point not on the same plane as the
            // others
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    /**
     * test Method for {@link geometries.Polygon (geomtries.Polygon)}
     */
    @Test
    public void testFindIntersections() {
        Point3D p4 = new Point3D(1, 1, 0);
        Polygon polygon = new Polygon(p1, p2, p3, p4);
        Ray ray = new Ray(new Point3D(0.25, 0.25, -1), new Vector(0, 0, 1));
        List<Point3D> intersections = Arrays.asList(new Point3D(0.25, 0.25, 0));
        polygon.findIntersections(ray).get(0);
        assertEquals("Polygon findIntersection error ", intersections, polygon.findIntersections(ray));
        ray = new Ray(new Point3D(0.5, 0.5, -1), new Vector(0, 0, 1));
        intersections = Arrays.asList(new Point3D(0.5, 0.5, 0));
        assertEquals("Polygon findIntersection error ", intersections, polygon.findIntersections(ray));
        ray = new Ray(new Point3D(-1, -1, -1), new Vector(0, 0, 1));
        assertEquals("Polygon findIntersection error ", null, polygon.findIntersections(ray));
    }

}
