package primitives;

/**
 * class represents a material of a geometry
 */
public class Material {
    double _kD;
    double _kS;
    int _nShininess;
    double _kR;
    double _kT;
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
    /**
     * creates a material for a geometry
     *
     * @param kd        - diffusion attenuation coefficient
     * @param ks        - specular attenuation coefficient
     * @param shininess - shininess power
     * @param
     */
    public Material(double kd, double ks, int shininess,double kR,double kT) {
        _kD = kd;
        _kS = ks;
        _nShininess = shininess;
        _kR=kR;
        _kT=kT;
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
     * Getter of the material's reflection coefficient 
     * @return reflection coefficient 
     */
    public double getKr() {
        return _kR;
    }
    /**
     * Getter of material's reffraction coefficient
     * @return reffraction coefficient
     */
    public double getKt() {
        return _kT;
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
