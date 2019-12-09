package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Point3D;
import primitives.Vector;

/**
 * Class tests vector class operations.
 * 
 * @author bassi
 * @author asaf0
 *
 */
public class VectorTest {
	/**
	 * Test succeed when IllegalArgumentException is thrown and fail when zero
	 * vector is successfully created.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testVectorZero() {
		Vector ZeroVector = new Vector(Point3D.ZERO);
	}

	/**
	 * @test Vector plus its negative equals to zero vector and an
	 *       IllegalArgumentException must be thrown
	 *
	 * @test succeeded when addition operation results are correct
	 */
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

	/**
	 * @test succeeded when throw IllegalArgumentException when subtract the same
	 *       vector
	 * 
	 * @test succeeded when subtraction operation result is correct
	 */
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

	/**
	 * @test succeeded when dot product operation result is correct.
	 * 
	 *       Reporting an error when vectors are orthogonal
	 */
	@Test
	public void testDotProduct() {
		Vector vec1 = new Vector(2, 1, -1);
		Vector vec2 = new Vector(1, -2, 0);
		double dot = vec1.dotProduct(vec2);
		assertEquals("Dot product function error", 0, dot, 0);
	}

	/**
	 * @test succeeded when equals return true
	 */
	@Test
	public void testEqual() {
		Vector vec1 = new Vector(2, 0, 0);
		Vector vec2 = new Vector(2, 0, 0);
		assertTrue("Equal function error", vec1.equals(vec2));
	}

	/**
	 * @test succeeded when normalize operates correctly
	 */
	@Test
	public void testNormal() {
		Vector vec1 = new Vector(0, 9, 0);
		Vector vec2 = new Vector(0, 1, 0);
		assertEquals("Normal function error", vec2, vec1.normalized());
	}

	/**
	 * @test succeeded when length operates equals the size of vector
	 */
	@Test
	public void testLength() {
		Vector vec = new Vector(0, 10, 0);
		assertEquals("Length function error", 10, vec.length(), 0);
	}

	/**
	 * @test succeeded when cross product operate result works correctly
	 */
	@Test
	public void testCrossProduct() {
		Vector vec1 = new Vector(1, 2, 3);
		Vector vec2 = new Vector(3, 4, 5);
		Vector vec3 = new Vector(-2, 4, -2);
		assertEquals("Cross product function error", vec3, vec1.crossProduct(vec2));
	}

	/**
	 * @test succeeded when scale operate result is correct
	 */
	@Test
	public void testScale() {
		Vector vec1 = new Vector(1, 2, 3);
		Vector vec2 = new Vector(2, 4, 6);
		assertEquals("Scale function error", vec2, vec1.scale(2));
	}
}
