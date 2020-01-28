package geometries;

import primitives.Color;
import primitives.Material;

/**
 * class represents an abstract base class of radial geometries
 */
public abstract class RadialGeometry extends Geometry {
    protected double _radius;

    // ***************** Constructors ********************** //
    public RadialGeometry(double _radius) {
        if (_radius > 0)
            this._radius = _radius;
        else {
            throw new IllegalArgumentException("radius cant be smaller then zero");
        }
    }

    public RadialGeometry(Color emission, Material material, double _radius) {
        this(_radius);
        this.emission = emission;
        this.material = material;
    }
    // ***************** Getters/Setters ********************** //

    /**
     * Gets the radius of the geometry
     *
     * @return the radius
     */
    public double getRadius() {
        return _radius;
    }
}
