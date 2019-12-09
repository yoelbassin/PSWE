package primitives;

import java.lang.Math;

public class Point3D {
	static public final Point3D ZERO = new Point3D(0, 0, 0);

	private Coordinate x;
	private Coordinate y;
	private Coordinate z;

	// ***************** Constructors ********************** //
	/*
	 * Convenient double c'ctor.
	 * 
	 * @param x
	 * 
	 * @param y
	 * 
	 * @param z
	 */
	public Point3D(double x, double y, double z) {
		this.x = new Coordinate(x);
		this.y = new Coordinate(y);
		this.z = new Coordinate(z);
	}

	/*
	 * build a point3D from three Coordinates.
	 * 
	 * @param x
	 * 
	 * @param y
	 * 
	 * @param z
	 */

	public Point3D(Coordinate x, Coordinate y, Coordinate z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	// ***************** Getters ********************** //

	public Coordinate getX() {
		return x;
	}

	public Coordinate getY() {
		return y;
	}

	public Coordinate getZ() {
		return z;
	}

	// ***************** Administration ******************** //

	/**
	 * equal function
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point3D))
			return false;
		Point3D other = (Point3D) obj;
		return x.equals(other.x) && y.equals(other.y) && z.equals(other.z);
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + "," + z + ")";
	}

	// ***************** Operations ******************** //

	/**
	 * Subtract function Vector a ; a.subtract(other) result a-other (Point3D)
	 * 
	 * @param other , type Vector
	 * @return the equivalent Vector
	 */
	public Vector subtract(Point3D other) {
		Point3D temp;
		Vector vec;
		double x1 = getX().get();
		double y1 = getY().get();
		double z1 = getZ().get();
		double x2 = other.getX().get();
		double y2 = other.getY().get();
		double z2 = other.getZ().get();
		temp = new Point3D(x1 - x2, y1 - y2, z1 - z2);
		vec = new Vector(temp);
		return vec;
	}

	/**
	 * add Vector function .
	 * 
	 * @param other , type Vector
	 * @return the equivalent point.
	 */
	public Point3D add(Vector other) {
		Point3D temp;
		double x1 = getX().get();
		double y1 = getY().get();
		double z1 = getZ().get();
		double x2 = other.head.getX().get();
		double y2 = other.head.getY().get();
		double z2 = other.head.getZ().get();
		temp = new Point3D(x1 + x2, y1 + y2, z1 + z2);
		return temp;
	}

	/**
	 * Squared distance
	 * 
	 * @param other , type Point3D
	 * @return the square distance between points.
	 */
	public double distance2(Point3D other) {
		if (this.equals(other))
			return 0;
		double x1 = getX().get();
		double y1 = getY().get();
		double z1 = getZ().get();
		double x2 = other.getX().get();
		double y2 = other.getY().get();
		double z2 = other.getZ().get();
		double a = x1 - x2;
		double b = y1 - y2;
		double c = z1 - z2;
		double temp = a * a + b * b + c * c;
		return temp;
	}

	/**
	 * actual distance between points.
	 * 
	 * @param other , type Point3D
	 * @return the
	 */
	public double distance(Point3D other) {
		return Math.sqrt(distance2(other));
	}

}
