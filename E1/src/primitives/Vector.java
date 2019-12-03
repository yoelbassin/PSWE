package primitives;

import java.lang.Math;

/**
 * Class representing Cartesian 3D space vector
 * 
 * @author bassi
 *
 */
public class Vector {

	Point3D head;

	// ***************** Constructors ********************** //
	/**
	 * Constructs a vector by head point
	 * 
	 * @param head point of the vector
	 * @throws newIllegalException when head is (0,0,0)
	 */
	public Vector(Point3D head) {
		if (!head.equals(Point3D.ZERO))
			this.head = head;
		else {
			throw new IllegalArgumentException("Zero Vector");
		}
	}

	public Vector(double x, double y, double z) {
		Point3D HeadPoint = new Point3D(x, y, z);
		if (!HeadPoint.equals(Point3D.ZERO))
			HeadPoint = new Point3D(x, y, z);
		this.head = HeadPoint;
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
		return head.toString();
	}

	// ***************** Operations ******************** //
	/**
	 * add function : Vector.add(Vector) sum of two vectors equals to the equivalent
	 * vector.
	 * 
	 * @param other
	 * @return equivalent vector
	 */
	public Vector add(Vector other) {
		Point3D temp;
		Vector newVec;
		temp = this.head.add(other);
		newVec = new Vector(temp);
		return newVec;
	}

	/**
	 * subtract function : Vector.sub(Vector) Subtract define as the sum of vector +
	 * -(other).
	 * 
	 * @param other , type Vector
	 * @return equivalent vector.
	 */
	public Vector subtract(Vector other) {
		Vector newVec;
		newVec = this.head.subtract(other.getHead());
		return newVec;
	}

	/**
	 * dot product multiplication (a,b,c) dotProduct (h,y,k) = a*h+b*y+c*k
	 * 
	 * @param other , type Vector
	 * @throws an exception will be thrown , if Vectors are orthogonal.
	 * @return outcome of the formula below.
	 */
	public double dotProduct(Vector other) {
		double temp;
		temp = (this.head.getX().multiply(other.getHead().getX()).get()
				+ this.head.getY().multiply(other.getHead().getY()).get()
				+ this.head.getZ().multiply(other.getHead().getZ()).get());
		return temp;
	}

	/**
	 * cross product multiplication define as the result of (a,b,c) crossProduct
	 * (h,y,f) = (b*f-c*y,c*h-a*f,a*y-b*h).
	 * 
	 * @param other , type Vector .
	 * @return orthogonal Vector.
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
	 * squared length of the Vector
	 * 
	 * @return |vec| squared.
	 */
	public double length2() {
		double x = head.getX().get();
		double y = head.getY().get();
		double z = head.getZ().get();

		return x * x + y * y + z * z;
	}

	/**
	 * length of the Vector define as size of Vector |v|= sqrt(x*x + y*y + z*z)
	 * 
	 * @return size of Vector
	 */
	public double length() {
		return Math.sqrt(length2());
	}

	/**
	 * change size of the Vector (Scalar) * (a Vector)
	 * 
	 * @param num , type double
	 * @return a Vector multiply by a scale number.
	 */
	public Vector scale(double num) {
		double x = head.getX().get();
		double y = head.getY().get();
		double z = head.getZ().get();

		return new Vector(num * x, num * y, num * z);
	}

	/**
	 * normalize Vector Receiving a normal direction Vector without change head.
	 * 
	 * @return new vector of size 1 in the same direction
	 */
	public Vector normalized() {
		return scale(1 / length());
	}

	public Vector normalize() {
		double x = head.getX().get();
		double y = head.getY().get();
		double z = head.getZ().get();
		double l = length();
		head = new Point3D(x / l, y / l, z / l );
		return this;
	}

	
}
