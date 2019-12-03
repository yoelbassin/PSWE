package primitives;

public class Ray {
	private Vector dir;
	private Point3D p;

	// ***************** Constructors ********************** //
	/*
	 * 
	 * @param vec , direction Vector.
	 * 
	 * @param p , a point on the Ray
	 */
	public Ray(Point3D p, Vector vec) {

		this.dir = vec.normalized();
		this.p = p;
	}

	// ***************** Getters ********************** //
	public Vector getDir() {
		return dir;
	}

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
