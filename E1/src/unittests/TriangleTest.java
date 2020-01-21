package unittests;

import static org.junit.Assert.*;

import primitives.*;
import geometries.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import static geometries.Intersectable.GeoPoint;

/**
 * Test class of Triangle {@link geometries.Triangle}}
 */
public class TriangleTest {
	static Point3D p1 = new Point3D(0, 0, 0);
	static Point3D p2 = new Point3D(0, 3, 0);
	static Point3D p3 = new Point3D(4, 0, 0);
	static Triangle triangle = new Triangle(p1, p2, p3);

	/**
	 * test Method for {@link geometries.Polygon#getNormal (geomtries.Polygon)}
	 */
	@Test
	public void testGetNormal() {
		Vector normal = new Vector(0, 0, 1);
		assertEquals("Get normal function error", normal, triangle.getNormal(new Point3D(1, 1, 0)));
	}

	/**
	 * test Method for
	 * {@link geometries.Triangle#findIntersections (geomtries.Triangle)}
	 */
	@Test
	public void testFindIntersections() {
		/**
		 * EP case
		 */
		Ray ray = new Ray(new Point3D(2, 2, -1), new Vector(-1, -1, 1));
		List<GeoPoint> intersections = Arrays.asList(new GeoPoint(triangle, new Point3D(1, 1, 0)));
		assertEquals("Find intersection function error", intersections, triangle.findIntersections(ray));// EP ray to a
		// point
		// inside
		// the
		// triangle
		ray = new Ray(new Point3D(1, -2, -1), new Vector(0, 1, 2));
		assertEquals("Find intersection function error", null, triangle.findIntersections(ray));// EP ray to a point
		// outside the triangle
		// between axis's of two
		// sides near the side
		ray = new Ray(new Point3D(-2, -2, -1), new Vector(2, 2, 2));
		assertEquals("Find intersection function error", null, triangle.findIntersections(ray));// EP ray to a point
		// outside the triangle
		// between axis's of two
		// sides near the vertex
		/**
		 * BVA case 1
		 */
		ray = new Ray(new Point3D(1, 1, 0), new Vector(1, -1, -1));
		intersections = Arrays.asList(new GeoPoint(triangle, new Point3D(1, 1, 0)));
		assertEquals("Find intersection function error", null, triangle.findIntersections(ray));// BVA ray from a point
		// inside the triangle
		ray = new Ray(new Point3D(1, -2, 0), new Vector(0, 2, 1));
		assertEquals("Find intersection function error", null, triangle.findIntersections(ray));//// BVA ray from a
		//// point outside the
		//// triangle between
		//// axis's of two sides
		//// near the side
		ray = new Ray(new Point3D(-1, -1, 0), new Vector(1, 1, 1));
		assertEquals("Find intersection function error", null, triangle.findIntersections(ray));//// BVA ray from a
		//// point outside the
		//// triangle between
		//// axis's of two sides
		//// near the vertex
		/**
		 * BVA case 2
		 */
		ray = new Ray(new Point3D(1, 1, -1), new Vector(-1, -1, 1));
		intersections = Arrays.asList(new GeoPoint(triangle, new Point3D(0, 0, 0)));
		assertEquals("Find intersection function error", null, triangle.findIntersections(ray));// BVA ray to the vertex
		// of the triangle
		ray = new Ray(new Point3D(-2, 0, -2), new Vector(1, 0, 3));
		assertEquals("Find intersection function error", null, triangle.findIntersections(ray));// BVA ray to a point
		// outside the triangle
		// on the side axis of
		// the triangle
		ray = new Ray(new Point3D(2, 0, -1), new Vector(-1, 0, 1));
		intersections = Arrays.asList(new GeoPoint(triangle, new Point3D(1, 0, 0)));
		assertEquals("Find intersection function error", null, triangle.findIntersections(ray));// BVA ray to a point on
		// the side of the
		// triangle
		/**
		 * BVA case 3
		 */
		ray = new Ray(new Point3D(0, 0, 0), new Vector(2, 1, 1));
		intersections = Arrays.asList(new GeoPoint(triangle, new Point3D(0, 0, 0)));
		assertEquals("Find intersection function error", null, triangle.findIntersections(ray));// BVA ray from the
		// vertex of the
		// triangle
		ray = new Ray(new Point3D(0, -1, 0), new Vector(0, -1, 2));
		assertEquals("Find intersection function error", null, triangle.findIntersections(ray));// BVA ray from a point
		// outside the triangle
		// on the side axis of
		// the triangle
		ray = new Ray(new Point3D(0, 2, 0), new Vector(0, 2, 1));
		intersections = Arrays.asList(new GeoPoint(triangle, new Point3D(0, 2, 0)));
		assertEquals("Find intersection function error", null, triangle.findIntersections(ray));// BVA ray from a point
		// on the side of the
		// triangle
	}
}
