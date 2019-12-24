package unittests;

import static org.junit.Assert.*;
import primitives.*;
import geometries.*;
import org.junit.Test;

public class TriangleTest {
    /**
     * test Method for {@link geomtries.Polygon#getNormal(geomtries.Polygon)}
     */
    @Test
    public void testGetNormal() {
        // Test of normal on the tube
        Point3D p1 = new Point3D(0, 0, 0);
        Point3D p2 = new Point3D(0, 3, 0);
        Point3D p3 = new Point3D(4, 0, 0);
        Triangle triangle = new Triangle(p1, p2, p3);
        Vector normal = new Vector(0, 0, 1);
        assertEquals("Get normal function error", normal, triangle.getNormal(new Point3D(1, 1, 0)));
    }
}
