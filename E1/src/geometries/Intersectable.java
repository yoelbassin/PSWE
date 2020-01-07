package geometries;

import java.util.List;

import primitives.*;

/**
 * Shapes that implements this interface could find intersect points with rays.
 *
 * @author bassi
 * @author asaf0
 *
 */
public interface Intersectable {
	/**
	 * finds intersections of the ray with the Geometry
	 *
	 * @param ray
	 * @return intersection point
	 */
	List<Point3D> findIntersections(Ray ray);

}
