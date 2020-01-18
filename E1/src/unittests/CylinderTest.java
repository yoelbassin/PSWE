package unittests;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.Arrays;
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
    /**
     * test Method for {@link geomtries.Cylinder#findIntersection(geomtries.Cylinder)}
     */
    @Test
    public void testCylinderFindIntersection() {
    	//Ray is parallel to the bases
    	Vector zAxis = new Vector(0,0,1);
    	assertEquals(cylinder.findIntersections(new Ray(new Point3D(0,1,-2),zAxis)),Arrays.asList(new Point3D(0,1,-1),new Point3D(0,1,1)));
    	assertEquals(cylinder.findIntersections(new Ray(new Point3D(0,-1,1),zAxis)),Arrays.asList(new Point3D(0,1,-1),new Point3D(0,1,1)));
    	assertEquals(cylinder.findIntersections(new Ray(new Point3D(0,1,0),zAxis)),Arrays.asList(new Point3D(0,1,1)));
    	assertEquals(cylinder.findIntersections(new Ray(new Point3D(0,1,1),zAxis)),Arrays.asList(new Point3D(0,1,1)));
    	assertEquals(cylinder.findIntersections(new Ray(new Point3D(0,1,2),zAxis)),Arrays.asList());
    	//Ray is on the direction of ray axis 
    	Vector yAxis = new Vector(0,1,0);
    	assertEquals(cylinder.findIntersections(new Ray(new Point3D(0,-1,0),yAxis)),Arrays.asList(new Point3D(0,0,0),new Point3D(0,5,0)));
    	assertEquals(cylinder.findIntersections(new Ray(new Point3D(0,0,0),yAxis)),Arrays.asList(new Point3D(0,0,0),new Point3D(0,5,0)));
    	assertEquals(cylinder.findIntersections(new Ray(new Point3D(0,0,1),yAxis)),Arrays.asList(new Point3D(0,5,0)));
    	assertEquals(cylinder.findIntersections(new Ray(new Point3D(0,6,0),yAxis)),Arrays.asList());
    	//Ray is on the bases
    	assertEquals(cylinder.findIntersections(new Ray(new Point3D(0,0,0),zAxis)),Arrays.asList());
    	assertEquals(cylinder.findIntersections(new Ray(new Point3D(0,5,0),zAxis)),Arrays.asList());
    	//Ray is not parallel to bases or it is on the same direction of axis 
    	Vector directionOfARay = new Vector(-1.7,0,0.6);
    	assertEquals(cylinder.findIntersections(new Ray(new Point3D(2,0,0),directionOfARay)),Arrays.asList(new Point3D(0.9,0,0.43),new Point3D(-0.38,0,0.93)));
    	assertEquals(cylinder.findIntersections(new Ray(new Point3D(0.9,0,0.43),directionOfARay)),Arrays.asList(new Point3D(0.9,0,0.43),new Point3D(-0.38,0,0.93)));
    	assertEquals(cylinder.findIntersections(new Ray(new Point3D(-0.38,0,0.93),directionOfARay)),Arrays.asList(new Point3D(-0.38,0,0.93)));
    	assertEquals(cylinder.findIntersections(new Ray(new Point3D(-1,0,1.7),directionOfARay)),Arrays.asList());
    	//There is no intersection anyway
    	assertEquals(cylinder.findIntersections(new Ray(new Point3D(2,0,0),new Vector(-0.5,0,0.5))),Arrays.asList());
    	
Cylinder testcylinder = new Cylinder(1,1 ,new Ray(new Point3D(0,0,0),new Vector(0,0,1)));
    	
    	Ray ray = new Ray(new Point3D(0,1,0),new Vector(0,-2,0));
    	assertEquals(Arrays.asList(),testcylinder.findIntersections(ray));//intersect infinity point therefore we define it to be null.
    	ray = new Ray(new Point3D(0,1,0.5),new Vector(0,-2,0));
    	assertEquals(Arrays.asList(new Point3D(0,1,0.5),new Point3D(0,-1,0.5)),testcylinder.findIntersections(ray));
    	 ray = new Ray(new Point3D(-1,0,0),new Vector(1,0,1));
    	assertEquals(Arrays.asList(new Point3D(-1,0,0) ,new Point3D(0,0,1)),testcylinder.findIntersections(ray));
    	
    	//The form of whole tests 
    	//assertEquals(cylinder.findIntersections(new Ray(new Point3D(,,),new Vector(,,))),Arrays.asList(new Point3D(,,),new Point3D(,,)));

    }
    
}
