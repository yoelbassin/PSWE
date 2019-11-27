package geometries;

public abstract class RadialGeometry implements Geometry {
	protected double _radius;

//c'ctor
	public RadialGeometry(double _radius) {
		this._radius = _radius;
	}

//copy c'ctor
	public RadialGeometry(RadialGeometry copy) {
		_radius = copy._radius;
	}

	// get radius.
	public double getRadius() {
		return _radius;
	}

}