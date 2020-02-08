package elements;

import primitives.Color;

/**
 * class represents an ambient light in a scene
 */
public class AmbientLight extends Light {
    // ***************** Constructors ********************** //

    /**
     * sets the ambient light of the picture by color and Attenuation coefficient
     *
     * @param i - Light color
     * @param k - Attenuation coefficient
     */
    public AmbientLight(Color i, double k) {
        intensity = i.scale(k);
    }
    // ***************** Getters/Setters ********************** //

    /**
     * getter for the ambient lighting
     *
     * @return light intensity
     */
    public Color getIntensity() {
        return intensity;
    }
}
