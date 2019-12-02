package geometries;

public abstract class RadialGeometry implements Geometry {
	protected double _radius;

	// c'ctor
	public RadialGeometry(double _radius) {
		if (_radius > 0)
			this._radius = _radius;
		else {
			throw new IllegalArgumentException("radius cant be smaller then zero");
		}
	}

	// get radius.
	public double getRadius() {
		return _radius;
	}

}