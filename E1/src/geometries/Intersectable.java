package geometries;

import java.util.List;

import primitives.*;

public interface Intersectable {
	/**
	 * finds intersections of the ray with the Geometry
	 *
	 * @param ray
	 * @return intersection point
	 */
	List<Point3D> findIntersections(Ray ray);

}
