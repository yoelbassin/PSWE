package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.*;
import primitives.*;

/**
 * Test class of Cylinder {@link geometries.Cylinder}}
 */
public class CylinderTest {
    public static Point3D basePoint = new Point3D(0, 0, 0);
    public static Vector vec = new Vector(0, 1, 0);
    public static Ray axis = new Ray(basePoint, vec);
    public static Cylinder cylinder = new Cylinder(1, 5, axis);

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
}
