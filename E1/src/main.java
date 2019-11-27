
/**
 * Yoel Bassin
 * 212431886 
 * Asaf Hillel 
 * 323824730
 * bassin.yoel@gmail.com
 * asafdavi@g.jct.ac.il
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
		Coordinate x = new Coordinate(1);
		Coordinate y = new Coordinate(2);
		Coordinate z = new Coordinate(3);
		Point3D testP1 = new Point3D(x, y, z);
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
		vec2 = (vec2.sub(vec2));

		/**
		 * orthogonal vectors dot product
		 */
		Coordinate a = new Coordinate(2);
		Coordinate b = new Coordinate(1);
		Coordinate c = new Coordinate(-1);
		Coordinate d = new Coordinate(1);
		Coordinate e = new Coordinate(-2);
		Coordinate f = new Coordinate(0);
		Point3D testP2 = new Point3D(a, b, c);
		Point3D testP3 = new Point3D(d, e, f);
		vec1 = new Vector(testP2);
		vec2 = new Vector(testP3);
		double dotproduct = vec1.dotProduct(vec2);
		System.out.println(dotproduct);
	}

	public static void Point3dTest() {
		Coordinate x = new Coordinate(1);
		Coordinate y = new Coordinate(2);
		Coordinate z = new Coordinate(3);
		Point3D testP1 = new Point3D(x, y, z);
		/**
		 * distance from a point to itself
		 */
		double a = testP1.distance(testP1);
		System.out.println(a);
	}

}
