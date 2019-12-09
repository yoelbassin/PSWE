package primitives;
/**
 * Class represents 3D space ray.
 * @author bassi
 * @author asaf0
 */
public class Ray {
	private Vector dir;
	private Point3D p;

	// ***************** Constructors ********************** //
	/*
	 * Construct a ray with a initial point and direction vector.
	 * @param vec of direction .
	 * 
	 * @param p initial point 
	 */
	public Ray(Point3D p, Vector vec) {

		this.dir = vec.normalized();
		this.p = p;
	}

	// ***************** Getters ********************** //
	/**
	 * Getter of direction vector
	 * @return direction vector
	 */
	public Vector getDir() {
		return dir;
	}
	/**
	 * Getter of initial point
	 * @return initial point
	 */
	public Point3D getBasePoint() {
		return p;
	}

	// ***************** Administration ******************** //
	/**
	 * equal function
	 */
	@Override
	public boolean equals(Object other) {
		if (p.equals(other)) {
			if (dir.equals(other)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "from " + p + " to " + dir;
	}
}
package primitives;
/**
 * Class represents 3D space ray.
 * @author bassi
 * @author asaf0
 */
public class Ray {
	private Vector dir;
	private Point3D p;

	// ***************** Constructors ********************** //
	/*
	 * Construct a ray with a initial point and direction vector.
	 * @param vec of direction .
	 * 
	 * @param p initial point 
	 */
	public Ray(Point3D p, Vector vec) {

		this.dir = vec.normalized();
		this.p = p;
	}

	// ***************** Getters ********************** //
	/**
	 * Getter of direction vector
	 * @return direction vector
	 */
	public Vector getDir() {
		return dir;
	}
	/**
	 * Getter of initial point
	 * @return initial point
	 */
	public Point3D getBasePoint() {
		return p;
	}

	// ***************** Administration ******************** //
	/**
	 * equal function
	 */
	@Override
	public boolean equals(Object other) {
		if (p.equals(other)) {
			if (dir.equals(other)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "from " + p + " to " + dir;
	}
}
package primitives;
/**
 * Class represents 3D space ray.
 * @author bassi
 * @author asaf0
 */
public class Ray {
	private Vector dir;
	private Point3D p;

	// ***************** Constructors ********************** //
	/*
	 * Construct a ray with a initial point and direction vector.
	 * @param vec of direction .
	 * 
	 * @param p initial point 
	 */
	public Ray(Point3D p, Vector vec) {

		this.dir = vec.normalized();
		this.p = p;
	}

	// ***************** Getters ********************** //
	/**
	 * Getter of direction vector
	 * @return direction vector
	 */
	public Vector getDir() {
		return dir;
	}
	/**
	 * Getter of initial point
	 * @return initial point
	 */
	public Point3D getBasePoint() {
		return p;
	}

	// ***************** Administration ******************** //
	/**
	 * equal function
	 */
	@Override
	public boolean equals(Object other) {
		if (p.equals(other)) {
			if (dir.equals(other)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "from " + p + " to " + dir;
	}
}
package primitives;
/**
 * Class represents 3D space ray.
 * @author bassi
 * @author asaf0
 */
public class Ray {
	private Vector dir;
	private Point3D p;

	// ***************** Constructors ********************** //
	/*
	 * Construct a ray with a initial point and direction vector.
	 * @param vec of direction .
	 * 
	 * @param p initial point 
	 */
	public Ray(Point3D p, Vector vec) {

		this.dir = vec.normalized();
		this.p = p;
	}

	// ***************** Getters ********************** //
	/**
	 * Getter of direction vector
	 * @return direction vector
	 */
	public Vector getDir() {
		return dir;
	}
	/**
	 * Getter of initial point
	 * @return initial point
	 */
	public Point3D getBasePoint() {
		return p;
	}

	// ***************** Administration ******************** //
	/**
	 * equal function
	 */
	@Override
	public boolean equals(Object other) {
		if (p.equals(other)) {
			if (dir.equals(other)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "from " + p + " to " + dir;
	}
}
