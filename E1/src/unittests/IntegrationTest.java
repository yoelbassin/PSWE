package unittests;

import static org.junit.Assert.*;

import elements.Camera;
import org.junit.Test;

import geometries.*;
import primitives.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * class represents integration tests
 */
public class IntegrationTest {
    Point3D p0 = new Point3D(0, 0, 0);
    Vector vUp = new Vector(0, -1, 0);
    Vector vTo = new Vector(0, 0, -1);

    Camera camera = new Camera(p0, vUp, vTo);
    private int countIntersections(Intersectable shape) {
        int p = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                List<Point3D> temp = shape.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
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
        Sphere sphere = new Sphere(1, new Point3D(0, 0, -3)); //sphere first test case - 2 intersection points
        assertEquals(2, countIntersections(sphere), 0);
        sphere = new Sphere(2.5, new Point3D(0, 0, -3)); //sphere second test case - 18 intersection points
        assertEquals(18, countIntersections(sphere), 0);
        sphere = new Sphere(1.5, new Point3D(0, 0, -2)); //sphere third test case - 10 intersection points
        assertEquals(10, countIntersections(sphere), 0);
        sphere = new Sphere(5, new Point3D(0, 0, -1)); //sphere fourth test case - 9 intersection points
        assertEquals(9, countIntersections(sphere), 0);
        sphere = new Sphere(1, new Point3D(0, 0, 1)); //sphere fifth test case - 0 intersection points
        assertEquals(0, countIntersections(sphere), 0);
    }

    /**
     * integration test for plane and camera
     */
    @Test
    public void PlaneIntegrationTest() {
        Plane plane = new Plane(new Point3D(0, 0, -5), new Vector(0, 0, -1)); //plane first test case - 9 intersection points
        assertEquals(9, countIntersections(plane), 0);
        plane = new Plane(new Point3D(0, 0, -5), new Vector(0, -0.5, -1)); //plane second test case - 9 intersection points
        assertEquals(9, countIntersections(plane), 0);
        plane = new Plane(new Point3D(0, 0, -5), new Vector(0, -1, -0.5)); //plane third test case - 6 intersection points
        assertEquals(6, countIntersections(plane), 0);
    }

    /**
     * integration test for triangle and camera
     */
    @Test
    public void TriangleIntegrationTest() {
        Triangle triangle = new Triangle(new Point3D(0, -1, -2), new Point3D(-1, 1, -2), new Point3D(1, 1, -2)); //triangle first test case - 1 intersection point
        assertEquals(1, countIntersections(triangle), 0);
        triangle = new Triangle(new Point3D(0, -20, -2), new Point3D(-1, 1, -2), new Point3D(1, 1, -2)); //triangle second test case - 2 intersection points
        assertEquals(2, countIntersections(triangle), 0);
    }
}
