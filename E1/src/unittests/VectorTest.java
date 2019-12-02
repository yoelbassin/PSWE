package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Point3D;
import primitives.Vector;

public class VectorTest {
	@Test(expected = IllegalArgumentException.class)
	public void zeroVecTest() {
		Vector ZeroVector = new Vector(Vector.Zero);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addTestToZero() {
		Vector vec = new Vector(1, 1, 1);
		vec.add(vec.scale(-1));
	}

	@Test
	public void addTest() {
		Vector vec = new Vector(1, 1, 1);
		Vector vec2 = new Vector(2, 2, 2);
		assertEquals(vec.add(vec), vec2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void subtractTestToZero() {
		Vector vec = new Vector(1, 1, 1);
		vec.subtract(vec);
	}

	@Test
	public void subtractTest() {
		Vector vec = new Vector(1, 1, 1);
		Vector vec2 = new Vector(2, 2, 2);
		Vector vec3 = new Vector(3, 3, 3);
		assertEquals(vec, vec2.subtract(vec3));
	}

	@Test
	public void dotProductTest() {
		Vector vec1 = new Vector(2, 1, -1);
		Vector vec2 = new Vector(1, -2, 0);
		double dot = vec1.dotProduct(vec2);
		System.out.println(vec1.dotProduct(vec2));
		assertEquals(0, dot, 0);
	}

	@Test
	public void equalTest() {
		Vector vec2 = new Vector(2, 0, 0);
		Vector vec1 = new Vector(2, 0, 0);
		assertTrue(vec1.equals(vec2));
	}

}
