package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class represents directional light in a scene
 */
public class DirectionalLight extends Light implements LightSource {
    Vector _direction;
    // ***************** Constructors ********************** //

    /**
     * constructs a directional light
     *
     * @param color - color of the light
     * @param dir   - direction of the light
     */
    public DirectionalLight(Color color, Vector dir) {
        intensity = color;
        _direction = dir.normalize();
    }

    // ***************** Getters/Setters ********************** //
    @Override
    public Color getIntensity(Point3D p) {
        return intensity;
    }

    @Override
    public Vector getL(Point3D p) {
        return _direction;
    }

    public double getDist(Point3D p){
        return 10000000;
    }
}
