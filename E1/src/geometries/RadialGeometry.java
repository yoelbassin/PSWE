package geometries;

public abstract class RadialGeometry implements Geometry {
	protected double _radius;

	// ***************** Constructors ********************** //
	public RadialGeometry(double _radius) {
		if (_radius > 0)
			this._radius = _radius;
		else {
			throw new IllegalArgumentException("radius cant be smaller then zero");
		}
	}

	// ***************** Getters/Setters ********************** //
	/**
	 * Gets the radius of the geometry
	 * 
	 * @return the radius
	 */
	public double getRadius() {
		return _radius;
	}

}
