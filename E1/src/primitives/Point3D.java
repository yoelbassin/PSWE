package primitives;

import java.lang.Math;

public class Point3D {
	static public final Point3D ZERO = new Point3D(0,0,0);
	
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
	public boolean equals(Object other) {
		if (x.equals(((Point3D) other).getX()) && y.equals(((Point3D) other).getY())
				&& z.equals(((Point3D) other).getZ())) {
			return true;
		}
		return false;

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
		temp = new Point3D(this.getX().subtract(other.getX()).get(), this.getY().subtract(other.getY()).get(),
				this.getZ().subtract(other.getZ()).get());
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
		temp = new Point3D(this.getX().add(other.getHead().getX()).get(), this.getY().add(other.getHead().getY()).get(),
				this.getZ().add(other.getHead().getZ()).get());
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
		double a = this.getX().subtract(other.getX()).get();
		double b = this.getY().subtract(other.getY()).get();
		double c = this.getZ().subtract(other.getZ()).get();
		double temp = (a * a) + (b * b) + (c * c);
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