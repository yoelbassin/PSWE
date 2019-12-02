package geometries;

import java.util.List;

import primitives.*;

public class Triangle extends Polygon {

	public Triangle(Point3D p1, Point3D p2, Point3D p3) {
		super(p1, p2, p3);
		double a = p1.distance(p2);
		double b = p2.distance(p3);
		double c = p3.distance(p1);
		if (!(a + b > c) && (b + c > a) && (a + c > b))
			throw new IllegalArgumentException();
	}

}