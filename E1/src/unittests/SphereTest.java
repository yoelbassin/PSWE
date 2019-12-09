package unittests;

import static org.junit.Assert.*;

import geometries.*;
import primitives.*;
import org.junit.Test;

public class SphereTest {

	@Test
	public void testSphere() {
		Point3D center = new Point3D(0, 0, 0);
		Sphere sphere = new Sphere(2, center);
		Point3D p = new Point3D(0, 2, 0);
		Vector actual = new Vector(0, 1, 0);
		assertEquals("Get normal function error", sphere.getNormal(p), actual);
	}

}
