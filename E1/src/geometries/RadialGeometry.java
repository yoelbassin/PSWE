package geometries;

public abstract class RadialGeometry implements Geometry {
	protected double _radius;
	
//c'ctor
	public RadialGeometry(double _radius) {
		super();
		this._radius = _radius;
	}
	
//copy c'ctor
	public RadialGeometry(RadialGeometry copy) {
		_radius = copy._radius;
	}
	
	//get radius.
	public double get_radius() {
		return _radius;
	}

	
}