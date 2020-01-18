package elements;

import primitives.Color;

/**
 * class represents an abstract base class for lights in a scene
 */
public abstract class Light {
    Color intensity;
    // ***************** Getters/Setters ********************** //

    /**
     * getter for original intensity of a color
     *
     * @return intensity color
     */
    public Color getIntensity() {
        return intensity;
    }
}
