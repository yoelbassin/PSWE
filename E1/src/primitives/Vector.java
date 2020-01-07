package primitives;

import java.lang.Math;

/**
 * Class representing Cartesian 3D space vector
 *
 */
public class Vector {

	Point3D head;

	// ***************** Constructors ********************** //
	/**
	 * Constructs a vector with head point
	 *
	 * @param head point of the vector
	 * @throws newIllegalException when head is (0,0,0)
	 */
	public Vector(Point3D head) {
		if (head.equals(Point3D.ZERO))
			throw new IllegalArgumentException("Zero Vector");
		this.head = head;
	}

	/**
	 * Constructs a vector by values of three coordinates of vector's head
	 *
	 * @param x coordinate value
	 * @param y coordinate value
	 * @param z coordinate value
	 * @throws newIllegalException when head is (0,0,0)
	 */
	public Vector(double x, double y, double z) {
		this.head = new Point3D(x, y, z);
		if (this.head.equals(Point3D.ZERO))
			throw new IllegalArgumentException("Zero Vector");
	}

	/**
	 * Constructs a vector with three coordinates.
	 *
	 * @param x coordinate of head point
	 * @param y coordinate of head point
	 * @param z coordinate of head point
	 * @throws newIllegalException when head is (0,0,0)
	 */
	public Vector(Coordinate x, Coordinate y, Coordinate z) {
		Point3D headPoint = new Point3D(x, y, z);
		if (headPoint.equals(Point3D.ZERO))
			throw new IllegalArgumentException("Zero Vector");
		this.head = headPoint;
	}

	/**
	 * Construct head point of vector class with a vector
	 *
	 * @param other vector
	 * @throws newIllegalException when head is (0,0,0)
	 */
	public Vector(Vector other) {
		this.head = other.head;
	}

	// ***************** Getters/Setters ********************** //
	/**
	 * Getter of vector's head
	 *
	 * @return point of the head
	 */
	public Point3D getHead() {
		return head;
	}

	// ***************** Administration ******************** //
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vector))
			return false;

		Vector other = (Vector) obj;

		return head.equals(other.head);
	}

	@Override
	public String toString() {
		return "->" + head;
	}

	// ***************** Operations ******************** //
	/**
	 * Vector addition operation.
	 *
	 * @param other vector
	 * @return equivalent vector
	 */
	public Vector add(Vector other) {
		return new Vector(this.head.add(other));
	}

	/**
	 * Vector subtraction operation.
	 *
	 * @param other Vector
	 * @return equivalent vector.
	 */
	public Vector subtract(Vector other) {
		return this.head.subtract(other.getHead());
	}

	/**
	 * The dot-product function takes two 3D space vectors and return a number as
	 * the formula (a,b,c) * (h,y,k) = a*h+b*y+c*k
	 *
	 * @param other Vector
	 * @return Outcome of the formula below.
	 */
	public double dotProduct(Vector other) {
		double x1 = head.getX().get();
		double y1 = head.getY().get();
		double z1 = head.getZ().get();
		double x2 = other.head.getX().get();
		double y2 = other.head.getY().get();
		double z2 = other.head.getZ().get();
		return (x1 * x2 + y1 * y2 + z1 * z2);
	}

	/**
	 * cross product multiplication define as the result of (a,b,c) cross(h,y,f) =
	 * (b*f-c*y,c*h-a*f,a*y-b*h).
	 *
	 * @param other vector .
	 * @return vector orthogonal to each one of the two vectors.
	 */
	public Vector crossProduct(Vector other) {
		double x1 = head.getX().get();
		double y1 = head.getY().get();
		double z1 = head.getZ().get();
		double x2 = other.head.getX().get();
		double y2 = other.head.getY().get();
		double z2 = other.head.getZ().get();

		return new Vector(y1 * z2 - y2 * z1, z1 * x2 - z2 * x1, x1 * y2 - x2 * y1);
	}

	/**
	 * Calculates the length of vector squared
	 *
	 * @return squared length of vector
	 */
	public double length2() {
		double x = head.getX().get();
		double y = head.getY().get();
		double z = head.getZ().get();

		return x * x + y * y + z * z;
	}

	/**
	 * Length of vector
	 *
	 * @return size of Vector
	 */
	public double length() {
		return Math.sqrt(length2());
	}

	/**
	 * Scale vector by num.
	 *
	 * @param num scale size
	 * @return scaled vector by num
	 */
	public Vector scale(double num) {
		double x = head.getX().get();
		double y = head.getY().get();
		double z = head.getZ().get();

		return new Vector(num * x, num * y, num * z);
	}

	/**
	 * Builds new vector of length 1 in the same direction
	 *
	 * @return normalized vector
	 */
	public Vector normalized() {
		return scale(1 / length());
	}

	/**
	 * Makes the vector to be of length 1 keeping the direction
	 *
	 * @return normalized vector itself
	 */
	public Vector normalize() {
		double x = head.getX().get();
		double y = head.getY().get();
		double z = head.getZ().get();
		double l = length();
		head = new Point3D(x / l, y / l, z / l);
		return this;
	}
}
