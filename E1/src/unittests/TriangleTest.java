package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.*;
import geometries.*;

public class TriangleTest {

	@Test
	public void testTriangle() {
		Point3D p1 = new Point3D(0, 0, 0);
		Point3D p2 = new Point3D(0, 3, 0);
		Point3D p3 = new Point3D(4, 0, 0);
		Triangle triangle = new Triangle(p1, p2, p3);
	}

}
