package unittests;

import geometries.*;
import static org.junit.Assert.*;
import primitives.*;
import org.junit.Test;

public class PlaneTest {

	@Test(expected = IllegalArgumentException.class)
	public void testPlaneZero() {
		Point3D p1 = new Point3D(0, 0, 0);
		Point3D p2 = new Point3D(0, 0, 0);
		Point3D p3 = new Point3D(0, 0, 0);
		Plane plane = new Plane(p1, p2, p3);
	}

	@Test
	public void testGetNormal() {
		Point3D p = new Point3D(0, 0, 0);
		Vector vec = new Vector(0, 1, 0);
		Plane plane = new Plane(p, vec);
		assertEquals(vec, plane.getNormal());
	}

}
