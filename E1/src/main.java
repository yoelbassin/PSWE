
/**
 * Yoel Bassin
 * 212431886 
 * Asaf Hillel 
 * 323824730
 * bassin.yoel@gmail.com
 * asafdavi@g.jct.ac.il
 * aaaaa
 */

import primitives.*;

public class main {
	public static void main(String args[]) {
		VectorTest();
		Point3dTest();
	}

	public static void VectorTest() {
		/**
		 * Constructor for vector 0
		 */
		Vector ZeroVector = new Vector(Vector.Zero);
		Point3D testP1 = new Point3D(1, 2, 3);
		Vector vec1 = new Vector(testP1);
		Vector vec2 = new Vector(testP1);
		Vector vec3 = new Vector(testP1);
		System.out.println(vec1.toString());
		/**
		 * Add to vector 0
		 */
		vec1 = (vec1.add(vec1.scale(-1)));
		/**
		 * subtract to vector 0
		 */

		/**
		 * orthogonal vectors dot product
		 */
		Point3D testP2 = new Point3D(2, 1, -1);
		Point3D testP3 = new Point3D(1, -2, 0);
		vec1 = new Vector(testP2);
		vec2 = new Vector(testP3);
		double dotproduct = vec1.dotProduct(vec2);
		System.out.println(dotproduct);
	}

	public static void Point3dTest() {
		Point3D testP1 = new Point3D(1, 2, 3);
		Point3D testP2 = new Point3D(1, 1, 1);
		Point3D testP3 = new Point3D(0, 0, 0);
		/**
		 * distance from a point to itself
		 */
		double a = testP1.distance(testP1);
		System.out.println(a);
		System.out.println(testP2.distance(testP3));
	}

}
