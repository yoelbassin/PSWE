package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class represents a spot light in a scene
 */
public class SpotLight extends PointLight implements LightSource {
    Vector _direction;
    // ***************** Constructors ********************** //

    /**
     * constructs a spot light in a scene
     *
     * @param color     - color of the light
     * @param position  - position of the light in the space
     * @param direction - direction of the spot light
     * @param kC
     * @param kL
     * @param kQ
     */
    public SpotLight(Color color, Point3D position, Vector direction, double kC, double kL, double kQ) {
        super(color, position, kC, kL, kQ);
        this._direction = direction.normalize();
    }
    // ***************** Getters/Setters ********************** //

    @Override
    public Color getIntensity(Point3D p) {
        double cos = _direction.dotProduct(getL(p));
        if (cos <= 0) //if the point is behind the light (90 deg or more)
            return Color.BLACK;
        return super.getIntensity(p).scale(cos);
    }
}
