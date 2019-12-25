package unittests;

import static org.junit.Assert.*;

import geometries.*;
import primitives.*;
import org.junit.Test;

public class SphereTest {
	/**
	 * test Method for {@link geomtries.Sphere#getNormal(geomtries.Sphere)}
	 */
	@Test
	public void testGetNormal() {
		Point3D center = new Point3D(0, 0, 0);
		Sphere sphere = new Sphere(2, center);
		Point3D p = new Point3D(0, 2, 0);
		Vector actual = new Vector(0, 1, 0);
		assertEquals("Get normal function error", sphere.getNormal(p), actual);
	}

	@Test
	public void testfindIntersections() {
		Sphere sphere = new Sphere(2,new Point3D(0,0,0)); // x^2 + y^2 + z^2 = 4
		
		/****************** EP ******************/
		
		// There is no intersection with the ray or with the another half line.
		assertEquals("Sphere findIntersection error : error shouldn't intersec ",sphere.findIntersections(new Ray( new Point3D(-4,0,0), new Ray(-4,-4,0)) , null );
		
		//There is two intersection points.
		
		double x = 1.4142135623731 ;
		List<Point3D> intersections = Arrays.asList(new Point3D (-x,-x,0),new Point3D (x,x,0));
		assertEquals("Sphere findIntersection error " , sphere.findIntersection(new Ray( new Point3D(-2,-2,0), new Vector(-4,-4,0))), intersections);
		
		// P0 in the sphere , one intersection point .
		x = 1.0696938456699;
		double y = 1.6898979485566;
		List<Point3D> intersections = Arrays.asList(new Point3D (x,y,0));
		assertEquals("Sphere findIntersection error " , sphere.findIntersection(new Ray( new Point3D(-1,1,0), new Vector(3,1,0))), intersections);
		
		// P0 is outside and the another half line has two intersects points.
		assertEquals("Sphere findIntersection error : error shouldn't intersec ",sphere.findIntersections(new Ray( new Point3D(-3,-1,0), new vector(2,1,0)) , null );
		
		/****************** BVA ******************/
		
		//P0 is on the sphere and the ray points to the outside.
		List<Point3D> intersections = Arrays.asList(new Point3D (-2,0,0));
		assertEquals("Sphere findIntersection error " , sphere.findIntersection(new Ray( new Point3D(-2,0,0), new Vector(-1,0,0))), intersections);
		
		//P0 is outside and there is no intersection.
		assertEquals("Sphere findIntersection error " , sphere.findIntersection(new Ray( new Point3D(-3,0,0), new Vector(-1,0,0))), null);
		
		//P0 is O and the direction of ray orthogonal the y axis 
		List<Point3D> intersections = Arrays.asList(new Point(-2,0,0));
		assertEquals("Sphere findIntersection error " , sphere.findIntersection(new Ray( new Point3D(0,0,0), new Vector(-1,0,0))) );
		
		//P0 is O and ray is not parllel the x axis 
		List<Point3D> intersections = Arrays.asList(new Point3D (x,x,0));
		assertEquals("Sphere findIntersection error " , sphere.findIntersection(new Ray( new Point3D(0,0,0), new Vector(2,2,0))), intersections);
		
		//P0 is on the sphere and the ray parllel the x axis and cross O.
		List<Point3D> intersections = Arrays.asList(new Point3D (2,0,0));
		assertEquals("Sphere findIntersection error " , sphere.findIntersection(new Ray( new Point3D(0,0,0), new Vector(-1,0,0))), intersections);
		
		// P0 is outside and the ray cross O
		List<Point3D> intersections = Arrays.asList(new Point3D (2,0,0),new Point3D (-2,0,0));
		assertEquals("Sphere findIntersection error " , sphere.findIntersection(new Ray( new Point3D(3,0,0), new Vector(-1,0,0))), intersections);
		
	}
}
