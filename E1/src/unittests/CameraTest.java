package unittests;

import static org.junit.Assert.*;

import elements.Camera;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * test class for Camera {@link elements.Camera}
 */
public class CameraTest {
    Point3D p0 = new Point3D(0, 0, 0);
    Vector vUp = new Vector(0, -1, 0);
    Vector vTo = new Vector(0, 0, -1);

    /**
     * test Method for {@link elements.Camera#Camera (elements.Camera)}
     */
    @Test
    public void cameraTest() {
        try {
            new Camera(p0, vUp, vTo);
            assertTrue(true);
        } catch (Exception e) {
            fail("Unwanted exception");
        }
        try {
            Vector vUpWrong = new Vector(0, -1, 0);
            Vector vToWrong = new Vector(0, 1, -1);
            new Camera(p0, vUpWrong, vToWrong);
            fail("Must throw an exception");
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    /**
     * test Method for
     * {@link elements.Camera#constructRayThroughPixel (elements.Camera)}
     */
    @Test
    public void constructRayThroughPixelTest() {
        Camera camera = new Camera(p0, vUp, vTo);

        // ********** 3x3 view plane **********//
        Ray ray = camera.constructRayThroughPixel(3, 3, 0, 0, 1, 3, 3);
        assertEquals(new Ray(new Point3D(0, 0, 0), new Vector(1, -1, -1)), ray); // Corner pixel case
        ray = camera.constructRayThroughPixel(3, 3, 0, 1, 1, 3, 3);
        assertEquals(new Ray(new Point3D(0, 0, 0), new Vector(1, 0, -1)), ray); // Side pixel case
        ray = camera.constructRayThroughPixel(3, 3, 1, 1, 1, 3, 3);
        assertEquals(new Ray(new Point3D(0, 0, 0), new Vector(0, 0, -1)), ray); // Inside pixel case

        // ********** 4x4 view plane **********//
        ray = camera.constructRayThroughPixel(4, 4, 0, 0, 1, 4, 4);
        assertEquals(new Ray(new Point3D(0, 0, 0), new Vector(1.5, -1.5, -1)), ray); // Corner pixel case
        ray = camera.constructRayThroughPixel(4, 4, 0, 1, 1, 4, 4);
        assertEquals(new Ray(new Point3D(0, 0, 0), new Vector(1.5, -0.5, -1)), ray); // Inside pixel case
        ray = camera.constructRayThroughPixel(4, 4, 1, 1, 1, 4, 4);
        assertEquals(new Ray(new Point3D(0, 0, 0), new Vector(0.5, -0.5, -1)), ray); // Side pixel case
    }
}
