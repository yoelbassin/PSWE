package unittests;

import static org.junit.Assert.*;

import java.util.Arrays;

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
    public static Tube tube = new Tube(asix, 1);

    /**
     * test Method for {@link geometries.Tube#getNormal (geomtries.Tube)}
     */
    @Test
    public void testGetNormal() {
        // Test of normal on the tube
        Point3D p = new Point3D(1, 2, 0);
        Vector actual = new Vector(1, 0, 0);
        assertEquals("Normal function error", tube.getNormal(p), actual);
    }
    /**
     * test Method for {@link geometries.Tube#findIntersection (geomtries.Tube)}
     */  
    @Test
    public void testFindIntersection() {
    	Vector zAxis = new Vector(0,0,1);
    	assertEquals(tube.findIntersections(new Ray(new Point3D(0,1,-2),zAxis)),Arrays.asList(new Point3D(0,1,-1),new Point3D(0,1,1)));
    	assertEquals(tube.findIntersections(new Ray(new Point3D(2,0,0),new Vector(-0.5,0,0.5))),Arrays.asList());
    	Vector yAxis = new Vector(0,1,0);
    	assertEquals(tube.findIntersections(new Ray(new Point3D(0,-1,0),yAxis)),Arrays.asList(new Point3D(0,0,0)));
    	Vector directionOfARay = new Vector(-1.7,0,0.6);
    	assertEquals(tube.findIntersections(new Ray(new Point3D(2,0,0),directionOfARay)),Arrays.asList(new Point3D(0.9,0,0.43),new Point3D(-0.38,0,0.93)));
    	assertEquals(tube.findIntersections(new Ray(new Point3D(0.9,0,0.43),directionOfARay)),Arrays.asList(new Point3D(0.9,0,0.43),new Point3D(-0.38,0,0.93)));
    	assertEquals(tube.findIntersections(new Ray(new Point3D(-0.38,0,0.93),directionOfARay)),Arrays.asList(new Point3D(-0.38,0,0.93)));
    	assertEquals(tube.findIntersections(new Ray(new Point3D(-1,0,1.7),directionOfARay)),Arrays.asList());
    
    }
}
