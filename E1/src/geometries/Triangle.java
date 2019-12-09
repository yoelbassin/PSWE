package geometries;

import java.util.List;

import primitives.*;

public class Triangle extends Polygon {
	// ***************** Constructor ********************** //
	/**
	 * constructs a triangle from three points
	 * 
	 * @param p1, first point
	 * @param p2, second point
	 * @param p3, third point
	 */
	public Triangle(Point3D p1, Point3D p2, Point3D p3) {
		super(p1, p2, p3);
	}

}
