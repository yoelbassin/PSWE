package unittests;

import static org.junit.Assert.*;

import elements.Camera;
import org.junit.Test;

import geometries.*;
import geometries.Intersectable.GeoPoint;
import primitives.*;

import java.util.List;

/**
 * class represents integration tests
 */
public class IntegrationTest {
    Point3D p0 = new Point3D(0, 0, 0);
    Vector vUp = new Vector(0, -1, 0);
    Vector vTo = new Vector(0, 0, -1);

    Camera camera = new Camera(p0, vUp, vTo);

    /**
     * counts the number of intersections between the shape and the ray's from the
     * camera
     *
     * @param Intersectable shape
     * @return number of intersections
     */
    private int countIntersections(Intersectable shape) {
        int p = 0;
        for (int i = 0; i < 3; i++) { // a view plane with length of 3 pixels
            for (int j = 0; j < 3; j++) { // a view plane with width of 3 pixels
                // find the intersection of the ray through pixel[j,i] and the shape
                List<GeoPoint> temp = shape.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
                if (temp != null) {
                    p = p + temp.size();
                }

            }
        }
        return p;
    }

    /**
     * integration test for sphere and camera
     */
    @Test
    public void SphereIntegrationTest() {
        // sphere first test case - 2 intersection points
        Sphere sphere = new Sphere(1, new Point3D(0, 0, -3));
        assertEquals(2, countIntersections(sphere), 0);

        // sphere second test case - 18 intersection points
        sphere = new Sphere(2.5, new Point3D(0, 0, -3));
        assertEquals(18, countIntersections(sphere), 0);

        // sphere third test case - 10 intersection points
        sphere = new Sphere(1.5, new Point3D(0, 0, -2));
        assertEquals(10, countIntersections(sphere), 0);

        // sphere fourth test case - 9 intersection points
        sphere = new Sphere(5, new Point3D(0, 0, -1));
        assertEquals(9, countIntersections(sphere), 0);

        // sphere fifth test case - 0 intersection points
        sphere = new Sphere(1, new Point3D(0, 0, 1));
        assertEquals(0, countIntersections(sphere), 0);
    }

    /**
     * integration test for plane and camera
     */
    @Test
    public void PlaneIntegrationTest() {
        // plane first test case - 9 intersection points
        Plane plane = new Plane(new Point3D(0, 0, -5), new Vector(0, 0, -1));
        assertEquals(9, countIntersections(plane), 0);

        // plane second test case - 9 intersection points
        plane = new Plane(new Point3D(0, 0, -5), new Vector(0, -0.5, -1));
        assertEquals(9, countIntersections(plane), 0);

        // plane third test case - 6 intersection points
        plane = new Plane(new Point3D(0, 0, -5), new Vector(0, -1, -0.5));
        assertEquals(6, countIntersections(plane), 0);
    }

    /**
     * integration test for triangle and camera
     */
    @Test
    public void TriangleIntegrationTest() {
        // triangle first test case - 1 intersection point
        Triangle triangle = new Triangle(new Point3D(0, -1, -2), new Point3D(-1, 1, -2), new Point3D(1, 1, -2));
        assertEquals(1, countIntersections(triangle), 0);

        // triangle second test case - 2 intersection points
        triangle = new Triangle(new Point3D(0, -20, -2), new Point3D(-1, 1, -2), new Point3D(1, 1, -2));
        assertEquals(2, countIntersections(triangle), 0);
    }
}
