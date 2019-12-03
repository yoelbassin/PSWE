package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Point3D;
import primitives.Vector;

public class VectorTest {
	/**
	 * @exception Creating Vector with arguments (0,0,0)
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testVectorZero() {
		Vector ZeroVector = new Vector(Vector.Zero);
	}

	/**
	 * @exception Adding a vector by (-) of itself creates vector(0,0,0)
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAddToZero() {
		Vector vec = new Vector(1, 1, 1);
		vec.add(vec.scale(-1));
	}

	/**
	 * Add function test
	 */
	@Test
	public void testAdd() {
		Vector vec = new Vector(1, 1, 1);
		Vector vec2 = new Vector(2, 2, 2);
		assertEquals(vec.add(vec), vec2);
	}

	/**
	 * @exception Subtracting a vector from itself creates vector (0,0,0)
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSubtractToZero() {
		Vector vec = new Vector(1, 1, 1);
		vec.subtract(vec);
	}

	/**
	 * Subtraction function test
	 */
	@Test
	public void testSubtract() {
		Vector vec = new Vector(1, 1, 1);
		Vector vec2 = new Vector(2, 2, 2);
		Vector vec3 = new Vector(3, 3, 3);
		assertEquals(vec, vec3.subtract(vec2));

	}

	/**
	 * Dot product multiplication function test
	 */
	@Test
	public void testDotProduct() {
		Vector vec1 = new Vector(2, 1, -1);
		Vector vec2 = new Vector(1, -2, 0);
		double dot = vec1.dotProduct(vec2);
		assertEquals(0, dot, 0);
	}

	/**
	 * Equal function test
	 */
	@Test
	public void testEqual() {
		Vector vec1 = new Vector(2, 0, 0);
		Vector vec2 = new Vector(2, 0, 0);
		assertTrue(vec1.equals(vec2));
	}

	/**
	 * Get normal function test
	 */
	@Test
	public void testNormal() {
		Vector vec1 = new Vector(0, 9, 0);
		Vector vec2 = new Vector(0, 1, 0);
		assertEquals(vec2, vec1.normalize());
	}

	/**
	 * Length function test
	 */
	@Test
	public void testLength() {
		Vector vec = new Vector(0, 10, 0);
		assertEquals(10, vec.length(), 0);
	}

	/**
	 * Cross product function test
	 */
	@Test
	public void testCrossProduct() {
		Vector vec1 = new Vector(1, 2, 3);
		Vector vec2 = new Vector(3, 4, 5);
		Vector vec3 = new Vector(-2, 4, -2);
		assertEquals(vec3, vec1.crossProduct(vec2));
	}

	/**
	 * Scale function test
	 */
	@Test
	public void testScale() {
		Vector vec1 = new Vector(1, 2, 3);
		Vector vec2 = new Vector(2, 4, 6);
		assertEquals(vec2, vec1.scale(2));
	}
}
