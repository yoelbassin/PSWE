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
     * Default constructor constructs the list as an empty ArrayList
     */
    public Geometries() {
        shapes = new ArrayList<Intersectable>();
    }

    /**
     * constructs a list with geometries (one or more)
     *
     * @param geometries - list of geometry shapes.
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
        for (Intersectable shape : shapes) {
            List<GeoPoint> ints = shape.findIntersections(ray);
            if (ints != null)
                intersections.addAll(ints);
        }
        // if no intersections were found, return null
        if (intersections.size() == 0)
            return null;
        return intersections;
    }
}
