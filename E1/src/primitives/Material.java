package primitives;

/**
 * class represents a material of a geometry
 */
public class Material {
    double _kD;
    double _kS;
    int _nShininess;

    // ***************** Constructors ********************** //
    /**
     * creates a material for a geometry
     *
     * @param kd        - diffusion attenuation coefficient
     * @param ks        - specular attenuation coefficient
     * @param shininess - shininess power
     */
    public Material(double kd, double ks, int shininess) {
        _kD = kd;
        _kS = ks;
        _nShininess = shininess;
    }
    // ***************** Getters/Setters ********************** //

    /**
     * getter of diffusion attenuation coefficient
     *
     * @return diffusion attenuation coefficient
     */
    public double getKd() {
        return _kD;
    }

    /**
     * getter of specular attenuation coefficient
     *
     * @return specular attenuation coefficient
     */
    public double getKs() {
        return _kS;
    }

    /**
     * getter of shininess power
     *
     * @return shininess power
     */
    public int getShininess() {
        return _nShininess;
    }
}
