package unittests;

import static org.junit.Assert.*;
import primitives.*;
import geometries.*;
import org.junit.Test;

public class PolygonTest {

	@Test(expected = ArithmeticException.class)
	public void testPolygon() {
		Point3D p1 = new Point3D(1, 1, 1);
		Point3D p2 = new Point3D(3, 4, 5);
		Polygon polygon = new Polygon(p1, p2);
	}

	@Test
	public void testGetNormalPoint3D() {
		Point3D p1 = new Point3D(0, 0, 0);
		Point3D p2 = new Point3D(0, 3, 0);
		Point3D p3 = new Point3D(4, 0, 0);
		Polygon polygon = new Polygon(p1, p2, p3);
		Vector normal = new Vector(0, 0, 1);
		assertEquals(normal, polygon.getNormal());
	}


}
