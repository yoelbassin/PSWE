package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

public class Geometries implements Intersectable {
    List<Intersectable> shapes = new ArrayList<Intersectable>(); //get function is O(1) and used more frequently than add that is O(n)

    public Geometries(Intersectable... geometries) {
        for (int i = 0; i < geometries.length; ++i) {
            shapes.add(geometries[i]);
        }
    }

    public void add(Intersectable... geometries) {
        for (int i = 0; i < geometries.length; ++i) {
            shapes.add(geometries[i]);
        }
    }

    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersections = new ArrayList<Point3D>();
        for (int i = 0; i < shapes.size(); ++i) {
            if(shapes.get(i).findIntersections(ray) != null)
                intersections.add(shapes.get(i).findIntersections(ray).get(0));
        }
        return intersections;
    }
}
