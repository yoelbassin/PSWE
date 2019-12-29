package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.*;
import primitives.*;

import java.util.Arrays;
import java.util.List;

public class CylinderTest {
    public static Point3D basePoint = new Point3D(0, 0, 0);
    public static Vector vec = new Vector(0, 1, 0);
    public static Ray asix = new Ray(basePoint, vec);
    public static Cylinder cylinder = new Cylinder(1, 5, asix);

    /**
     * test Method for {@link geomtries.Cylinder#getNormal(geomtries.Cylinder)}
     */
    @Test
    public void testCylinderNormal() {
        // Test of normal on the tube
        Point3D p = new Point3D(1, 2, 0);
        Vector expected = new Vector(1, 0, 0);
        assertEquals(expected, cylinder.getNormal(p));
        // Test of normal on the base of the cylinder
        p = new Point3D(0, 0, 0);
        Vector actual = new Vector(0, 1, 0);
        assertEquals(cylinder.getNormal(p), actual);
        // Test of normal on the top base of the cylinder
        p = new Point3D(0, 5, 0);
        assertEquals(cylinder.getNormal(p), actual);
    }
/**
 @Test public void testFindIntersections() {
 Ray axis = new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 1));
 Cylinder cylinder = new Cylinder(2, 5, axis);
 Ray ray = new Ray(new Point3D(0, 5, 0), new Vector(0, -5, 3));
 List<Point3D> intersections = Arrays.asList(new Point3D(0, 2, 1.8));
 assertEquals("find intersection function error", intersections, cylinder.findIntersections(ray));//ep
 ray = new Ray(new Point3D(0, 5, 3), new Vector(0, -5, 3));
 intersections = Arrays.asList(new Point3D(0, 2, 3));
 assertEquals("find intersection function error", intersections, cylinder.findIntersections(ray));//BVA orthogonal to axis
 }
 **/
}
