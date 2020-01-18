package geometries;

import primitives.Ray;
import java.util.ArrayList;
import java.util.List;

/**
 * class represents a list 3D space shapes which could intersect a ray.
 *
 * @author bassi
 * @author asaf0
 */
public class Geometries implements Intersectable {
	List<Intersectable> shapes = new ArrayList<Intersectable>();

	/**
	 * Default constructor constructs the list as an empty ArrayList ArrayList - get
	 * function is O(1) and add function is O(n) we have chosen to use it because
	 * get function used more
	 */
	public Geometries() {
		shapes = new ArrayList<Intersectable>();
	}

	/**
	 * constructs a list with geometries (one or more)
	 *
	 * @param list of geometry shapes.
	 */
	public Geometries(Intersectable... geometries) {
		for (int i = 0; i < geometries.length; ++i) {
			shapes.add(geometries[i]);
		}
	}
	// ***************** Operations ******************** //

	/**
	 * adds geometries to the list
	 *
	 * @param geometries
	 */
	public void add(Intersectable... geometries) {
		for (int i = 0; i < geometries.length; ++i) {
			shapes.add(geometries[i]);
		}
	}

	/**
	 * finds intersections of the ray with the geometries that are in the list
	 *
	 * @param ray in space.
	 * @return intersection points
	 */
	public List<GeoPoint> findIntersections(Ray ray) {
		List<GeoPoint> intersections = new ArrayList<>();
		for (int i = 0; i < shapes.size(); ++i) {
			// if an intersection is found, add it to the list
			if (shapes.get(i).findIntersections(ray) != null)
				intersections.add(shapes.get(i).findIntersections(ray).get(0));
		}
		// if no intersections were found, return null
		if (intersections.size() == 0)
			return null;
		return intersections;
	}

}
