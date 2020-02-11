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
	double _matte;
	double _diffusion;

	// ***************** Constructors ********************** //

	/**
	 * creates a material for a geometry
	 *
	 * @param kd        - diffusion attenuation coefficient
	 * @param ks        - specular attenuation coefficient
	 * @param shininess - shininess power
	 */
	public Material(double kd, double ks, int shininess) {
		this(kd, ks, shininess, 0, 0);
	}

	/**
	 * creates a material for a geometry
	 *
	 * @param kd        - diffusion attenuation coefficient
	 * @param ks        - specular attenuation coefficient
	 * @param shininess - shininess power
	 * @param kr        - reflection coefficient
	 * @param kt        - transparency coefficient
	 */
	public Material(double kd, double ks, int shininess, double kr, double kt) {
		this(kd, ks, shininess, kr, kt, 0, 0);
	}

	/**
	 * creates a material for a geometry
	 *
	 * @param kd        - diffusion attenuation coefficient
	 * @param ks        - specular attenuation coefficient
	 * @param shininess - shininess power
	 * @param kr        - reflection coefficient
	 * @param kt        - transparency coefficient
	 * @param matte     - how much the object is materialistic
	 * @param diff      - how much the object is diffusive
	 */
	public Material(double kd, double ks, int shininess, double kr, double kt, double matte, double diff) {
		_kD = kd;
		_kS = ks;
		_nShininess = shininess;
		_kR = kr;
		_kT = kt;
		_matte = matte;
		_diffusion = diff;
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

	/**
	 * getter of reflection coefficient
	 *
	 * @return reflection coefficient
	 */
	public double getKr() {
		return _kR;
	}

	/**
	 * getter of transparency coefficient
	 *
	 * @return transparency coefficient
	 */
	public double getKt() {
		return _kT;
	}

	/**
	 * getter of matte variable
	 * 
	 * @return material coefficient
	 */
	public double getMatte() {
		return _matte;
	}

	/**
	 * getter of diffusion variable
	 * 
	 * @return diffusion coefficient
	 */
	public double getDiffusion() {
		return _diffusion;
	}
}
