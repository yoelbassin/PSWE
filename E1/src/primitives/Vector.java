package primitives;

import java.lang.Math;

public class Vector {

	Point3D head;

	final public static Point3D Zero = new Point3D(0, 0, 0);

	public Vector(Point3D head) {

		if (!head.equals(Zero))
			this.head = head;
	}

	public Point3D getHead() {
		return head;
	}

	@Override
	public boolean equals(Object other) {
		if (head.equals(other)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return head.toString();
	}

	/**
	 * add function
	 * 
	 * @param other
	 * @return
	 */
	public Vector add(Vector other) {
		Point3D temp;
		Vector Otherhead;
		temp = new Point3D(this.head.getX().add(other.getHead().getX()).get(),
				this.head.getY().add(other.getHead().getY()).get(), this.head.getZ().add(other.getHead().getZ()).get());
		Otherhead = new Vector(temp);
		return Otherhead;
	}

	/**
	 * subtract function
	 * 
	 * @param other
	 * @return
	 */
	public Vector sub(Vector other) {
		Point3D temp;
		Vector Otherhead;
		temp = new Point3D(this.head.getX().subtract(other.getHead().getX()).get(),
				this.head.getY().subtract(other.getHead().getY()).get(),
				this.head.getZ().subtract(other.getHead().getZ()).get());
		Otherhead = new Vector(temp);
		return Otherhead;
	}

	/**
	 * dot product multiplication
	 * 
	 * @param other
	 * @return
	 */
	public double dotProduct(Vector other) {
		double temp;
		temp = (this.head.getX().multiply(other.getHead().getX()).get()
				+ this.head.getY().multiply(other.getHead().getY()).get()
				+ this.head.getZ().multiply(other.getHead().getZ()).get());
		return temp;
	}

	/**
	 * cross product multiplication
	 * 
	 * @param other
	 * @return
	 */
	public Vector crossProduct(Vector other) {

		Point3D temp;
		Vector tempVec;
		double newX;
		double newY;
		double newZ;
		Coordinate thisX = new Coordinate(this.getHead().getX());
		Coordinate thisY = new Coordinate(this.getHead().getY());
		Coordinate thisZ = new Coordinate(this.getHead().getZ());
		Coordinate otherX = new Coordinate(other.getHead().getX());
		Coordinate otherY = new Coordinate(other.getHead().getY());
		Coordinate otherZ = new Coordinate(other.getHead().getZ());

		newX = thisY.multiply(otherZ).subtract(thisZ.multiply(otherY)).get();
		newY = thisZ.multiply(otherX).subtract(thisX.multiply(otherZ)).get();
		newZ = thisX.multiply(otherY).subtract(thisY.multiply(otherZ)).get();
		temp = new Point3D(newX, newY, newZ);
		tempVec = new Vector(temp);
		return tempVec;

	}

	/**
	 * squared length of the Vector
	 * 
	 * @return
	 */
	public double lengthSQ() {
		double length = Math.pow(this.head.getX().get(), 2) + Math.pow(this.head.getY().get(), 2)
				+ Math.pow(this.head.getZ().get(), 2);
		return length;
	}

	/**
	 * length of the Vector
	 * 
	 * @return
	 */
	public double length() {
		return Math.sqrt(lengthSQ());
	}

	/**
	 * change size of the Vector
	 * 
	 * @param num
	 * @return
	 */
	public Vector scale(double num) {
		Point3D temp;
		Vector Otherhead;
		temp = new Point3D(this.head.getX().scale(num).get(), this.head.getY().scale(num).get(),
				this.head.getZ().scale(num).get());
		Otherhead = new Vector(temp);
		return Otherhead;
	}

	/**
	 * normalize vector to new vector
	 */
	public void newNormal() {
		double temp;
		Vector Otherhead;
		temp = 1 / this.length();
		Otherhead = this.scale(temp);
		this.head = Otherhead.head;
	}

	/**
	 * normalize vector
	 * 
	 * @return
	 */
	public Vector Normal() {
		Vector otherHead = new Vector(this.head);
		otherHead.newNormal();
		return otherHead;
	}

}
