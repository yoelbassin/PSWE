package unittests;

import static org.junit.Assert.*;

import primitives.*;
import geometries.*;
import org.junit.Test;

/**
 * Test class of Tube {@link geometries.Tube}}
 */
public class TubeTest {
    public static Point3D basePoint = new Point3D(0, 0, 0);
    public static Vector vec = new Vector(0, 1, 0);
    public static Ray asix = new Ray(basePoint, vec);

    /**
     * test Method for {@link geometries.Tube#getNormal (geomtries.Tube)}
     */
    @Test
    public void testGetNormal() {
        // Test of normal on the tube
        Tube tube = new Tube(asix, 1);
        Point3D p = new Point3D(1, 2, 0);
        Vector actual = new Vector(1, 0, 0);
        assertEquals("Normal function error", tube.getNormal(p), actual);
    }
}
