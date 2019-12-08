package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Point3D;
import primitives.Vector;

public class VectorTest {
	@Test(expected = IllegalArgumentException.class)
	public void testVectorZero() {
		Vector ZeroVector = new Vector(Point3D.ZERO);
	}

	@Test
	public void testAdd() {
		Vector vec = new Vector(1, 1, 1);
		Vector vec2 = new Vector(2, 2, 2);
		try {
			vec.add(vec.scale(-1));
			fail("didn't throw exception for Vector 0");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
		assertEquals("Add function error", vec2, vec.add(vec));
	}

	@Test
	public void testSubtract() {
		Vector vec = new Vector(1, 1, 1);
		Vector vec2 = new Vector(2, 2, 2);
		try {
			vec.subtract(vec);
			fail("didn't throw exception for Vector 0");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
		assertEquals("Subtract function error", vec, vec2.subtract(vec));
	}

	@Test
	public void testDotProduct() {
		Vector vec1 = new Vector(2, 1, -1);
		Vector vec2 = new Vector(1, -2, 0);
		double dot = vec1.dotProduct(vec2);
		assertEquals("Dot product function error", 0, dot, 0);
	}

	@Test
	public void testEqual() {
		Vector vec1 = new Vector(2, 0, 0);
		Vector vec2 = new Vector(2, 0, 0);
		assertTrue("Equal function error", vec1.equals(vec2));
	}


	@Test
	public void testNormal() {
		Vector vec1 = new Vector(0, 9, 0);
		Vector vec2 = new Vector(0, 1, 0);
		assertEquals("Normal function error", vec2, vec1.normalized());
	}

	@Test
	public void testLength() {
		Vector vec = new Vector(0, 10, 0);
		assertEquals("Length function error", 10, vec.length(), 0);
	}

	@Test
	public void testCrossProduct() {
		Vector vec1 = new Vector(1, 2, 3);
		Vector vec2 = new Vector(3, 4, 5);
		Vector vec3 = new Vector(-2, 4, -2);
		assertEquals("Cross product function error", vec3, vec1.crossProduct(vec2));
	}

	@Test
	public void testScale() {
		Vector vec1 = new Vector(1, 2, 3);
		Vector vec2 = new Vector(2, 4, 6);
		assertEquals("Scale function error", vec2, vec1.scale(2));
	}
}
