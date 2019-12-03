package primitives;

public class Ray {
	private Vector vec;
	private Point3D p;

	// ***************** Constructors ********************** //
	/*
	 * 
	 * @param vec , direction Vector.
	 * 
	 * @param p , a point on the Ray
	 */
	public Ray(Vector vec, Point3D p) {

		this.vec = vec.normalize();
		this.p = p;
	}

	// ***************** Getters ********************** //
	public Vector getVector() {
		return vec;
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
			if (vec.equals(other)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "from " + p + " to " + vec;
	}
}