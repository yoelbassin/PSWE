package unittests;

import static org.junit.Assert.*;
import static geometries.Intersectable.GeoPoint;

import primitives.*;
import geometries.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Test class of Polygon {@link geometries.Polygon}}
 */
public class PolygonTest {
	/**
	 * test Method for {@link geometries.Polygon (geomtries.Polygon)}
	 */
	@Test
	public void testConstructor() {
		Point3D p1 = new Point3D(0, 0, 0);
		Point3D p2 = new Point3D(1, 0, 0);
		Point3D p3 = new Point3D(1, 1, 0);
		// ****** EP ******//
		try {
			// Test of the polygon constructor
			Point3D p4 = new Point3D(0, 1, 0);
			new Polygon(p1, p2, p3, p4);
			assertTrue(true);
		} catch (IllegalArgumentException e) {
			fail("threw an unwanted exception");
		}
		// ****** BVA ******//
		try {
			Point3D p4 = new Point3D(0, 1, 3);
			new Polygon(p1, p2, p3, p4);
			fail("didn't throw exception"); // BVA constructing a polygon with a point not on the same plane as the
			// others
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
		try {
			Point3D p4 = new Point3D(0, 1, -3);
			new Polygon(p1, p2, p3, p4);
			fail("didn't throw exception"); // BVA constructing a polygon with a point not on the same plane as the
			// others
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	/**
	 * test Method for
	 * {@link geometries.Polygon#findIntersections (geomtries.Polygon)}
	 */
	@Test
	public void testFindIntersections() {
		Point3D p1 = new Point3D(0, 0, 0);
		Point3D p2 = new Point3D(1, 0, 0);
		Point3D p3 = new Point3D(1, 1, 0);
		Point3D p4 = new Point3D(0, 1, 0);
		Polygon square = new Polygon(p1, p2, p3, p4);
		Ray ray = new Ray(new Point3D(0.25, 0.25, -1), new Vector(0, 0, 1));
		List<GeoPoint> intersections = Arrays.asList(new GeoPoint( square, new Point3D(0.25, 0.25, 0)));
		assertEquals("Polygon findIntersection error ", intersections, square.findIntersections(ray)); // inside square
		ray = new Ray(new Point3D(-1, -1, -1), new Vector(0, 0, 1));
		assertEquals("Polygon findIntersection error ", null, square.findIntersections(ray)); // outside square
		p1 = new Point3D(-2, 0, 0);
		p2 = new Point3D(-1, 2, 0);
		p3 = new Point3D(1, 2, 0);
		p4 = new Point3D(2, 0, 0);
		Point3D p5 = new Point3D(0, -2, 0);
		Polygon pentagon = new Polygon(p1, p2, p3, p4, p5);
		ray = new Ray(new Point3D(0, 0, -1), new Vector(0, 0, 1));
		intersections = Arrays.asList(new GeoPoint( square, new Point3D(0, 0, 0)));
		assertEquals("Polygon findIntersection error ", intersections, pentagon.findIntersections(ray)); // inside
		// pentagon
		ray = new Ray(new Point3D(-3, 3, -1), new Vector(0, 0, 1));
		assertEquals("Polygon findIntersection error ", null, pentagon.findIntersections(ray)); // outside pentagon
	}
}
