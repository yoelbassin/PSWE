package geometries;

import primitives.*;

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
