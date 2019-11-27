package geometries;

import java.util.ArrayList;
import java.util.List;
import static primitives.Util.*;

import primitives.*;

public class Polygon implements Geometry {
	List<Point3D> _points;
	Plane _plane;

	public Polygon(Point3D... points) {
		_points = new ArrayList<>();
		_plane  = new Plane(points[0],points[1],points[2]);
		Vector normal = _plane.getNormal(null);
		for (int i = 0; i < points.length; ++i) {
			_points.add(points[i]);
			if (i > 2 && !isZero(points[0].subtract(points[i]).dotProduct(normal)))
				throw new IllegalArgumentException();
		}	
	}

	public Vector getNormal(Point3D p) {
		return _plane.getNormal(p);
	}
}