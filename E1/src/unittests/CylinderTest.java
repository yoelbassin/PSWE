package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.*;
import primitives.*;

public class CylinderTest {
	public static Point3D basePoint = new Point3D(0, 0, 0);
	public static Vector vec = new Vector(0, 1, 0);
	public static Ray asix = new Ray(vec, basePoint);

	@Test(expected = IllegalArgumentException.class)
	public void testCylinder() {
		Cylinder cylinder = new Cylinder(1, 0, asix);
	}

	@Test
	public void testCylinderNormal() {
		Cylinder cylinder = new Cylinder(1, 5, asix);
		Point3D p = new Point3D(1, 2, 0);
		Vector expected = new Vector(1, 0, 0);
		assertEquals(expected, cylinder.getNormal(p));
	}

	@Test
	public void testCylinderBaseNormal() {
		Cylinder cylinder = new Cylinder(1, 5, asix);
		Point3D p = new Point3D(0, 0, 0);
		Vector actual = new Vector(0, 1, 0);
		assertEquals(cylinder.getNormal(p), actual);
	}

	@Test(expected = ArithmeticException.class)
	public void testCylinderBaseRadiusNormal() {
		Cylinder cylinder = new Cylinder(1, 5, asix);
		Point3D p = new Point3D(1, 0, 0);
		cylinder.getNormal(p);
	}

}
