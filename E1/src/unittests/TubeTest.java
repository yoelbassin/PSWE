package unittests;

import static org.junit.Assert.*;
import primitives.*;
import geometries.*;
import org.junit.Test;

public class TubeTest {
	public static Point3D basePoint = new Point3D(0, 0, 0);
	public static Vector vec = new Vector(0, 1, 0);
	public static Ray asix = new Ray(basePoint, vec);

	@Test(expected = IllegalArgumentException.class)
	public void testTube() {
		Tube tube = new Tube(asix, 0);
	}

	@Test
	public void testTubeNormal() {
		Tube tube = new Tube(asix, 1);
		Point3D p = new Point3D(1, 2, 0);
		Vector actual = new Vector(1, 0, 0);
		assertEquals("Normal function error", tube.getNormal(p), actual);// Test of normal on the tube
		p = new Point3D(0, 0, 0);
		actual = new Vector(0, 1, 0);
		assertEquals("Normal function error", tube.getNormal(p), actual);//Test of normal on the base of the tube
	}

}
