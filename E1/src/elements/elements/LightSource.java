package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class represents an interface for lights in a scene
 */
public interface LightSource {
    // ***************** Getters/Setters ********************** //

    /**
     * getter of a color intensity in a certain point
     *
     * @param p - the point
     * @return the color at the point
     */
    Color getIntensity(Point3D p);

    /**
     * getter of vector between light position and point
     *
     * @param p - the point
     * @return the distance
     */
    Vector getL(Point3D p);

    /**
     * getter of distance between light position and point
     *
     * @param p - the point
     * @return the distance
     */
    double getDist(Point3D p);
}
