package unittests;

import static org.junit.Assert.*;
import primitives.*;
import geometries.*;
import org.junit.Test;

public class TubeTest {
	public static Point3D basePoint = new Point3D(0, 0, 0);
	public static Vector vec = new Vector(0, 1, 0);
	public static Ray asix = new Ray(basePoint, vec);

	/**
	 * @exception Creates tube with radius smaller (or equal) than 0
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testTube() {
		Tube tube = new Tube(asix, 0);
	}

	/**
	 * Get normal function of tube test
	 */
	@Test
	public void testTubeNormal() {
		Tube tube = new Tube(asix, 1);
		Point3D p = new Point3D(1, 2, 0);
		Vector actual = new Vector(1, 0, 0);
		assertEquals(tube.getNormal(p), actual);
	}

}
