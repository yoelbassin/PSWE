package geometries;

import primitives.*;

/**
 * Interface of the whole geometry shapes which gives the the ability to find a
 * normal and intersect with rays.
 *
 * @author bassi
 * @author asaf0
 *
 */
public interface Geometry extends Intersectable {
	/**
	 * Calculates unit vector orthogonal to the tangent plane at the point (which is
	 * called - normal)
	 *
	 * @param p point of tangent...
	 * @return normal vector
	 */
	Vector getNormal(Point3D p);
}
