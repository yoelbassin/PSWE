package geometries;

import static primitives.Util.isZero;

import primitives.*;

public class Cylinder extends Tube {
	double _height;

	// ***************** Constructors ********************** //
	/**
	 * Constructs a cylinder with radius, height and axis ray
	 *
	 * @param double _radius, radius of the cylinder
	 * @param double _height, height of the cylinder
	 * @param Ray    _axis, axis of the cylinder
	 * @throws new IllegalException when radius is smaller than zero
	 */
	public Cylinder(double _radius, double _height, Ray _axis) {
		super(_axis, _radius);
		if (_height > 0)
			this._height = _height;
		else {
			throw new IllegalArgumentException("Radius is smaller than zero");
		}
	}

	// ***************** Operations ******************** //
	/**
	 * Gets the direction of the normal vector of the cylinder at a certain point
	 *
	 * @param p, the point on the cylinder
	 * @return the direction of the normal vector
	 */
	@Override
	public Vector getNormal(Point3D p) {
		Vector v = _axisRay.getDir();
		Point3D p0 = _axisRay.getBasePoint();
		if (p.equals(p0))
			return v;
		Vector u = p.subtract(p0); // vector from p0 to p
		double t = v.dotProduct(u); // size of projection of vector u on the ray
		// point on the ray and plane crossing P and orthogonal to the ray
		if (isZero(t) || isZero(t - this._height)) {
			return v;
		}
		return p.subtract(p0.add(v.scale(t))).normalize();
	}

	/**
	 * findIntersection with a 3D space cylinder and a ray.
	 * @param ray which we want to find intersections with it
	 * @return intersection list between zero point and two points 
	 */
	/**
	 * findIntersection with a 3D space cylinder and a ray.
	 * 
	 * @param ray which we want to find intersections with it
	 * @return intersection list between zero point and two points
	 */
	public List<Point3D> findIntersections(Ray ray) {
		Point3D p0 = ray.getBasePoint();
		Vector v = ray.getDir();

		double p0x = p0.getX().get();
		double p0y = p0.getY().get();
		double p0z = p0.getZ().get();

		double vz = v.getHead().getZ().get();

		List<Point3D> intersections = new ArrayList<Point3D>();

		// testing collision against the end caps .
		// find where it intersects the xy plane
		// skip the end caps test if vz=0.
		// ray is parallel to the xy plane
		if (vz != 0) {
			double tbase = 0;
			double tupper = 0;

			Point3D pbase;// intersect point with base
			Point3D pupper;// intersect point with upper cap

			// z=0 end cap
			tbase = -(p0z / vz);
			if (tbase == 0)
				pbase = p0;
			else
				pbase = p0.add(v.scale(tbase));
			// t of z=height end cap

			tupper = -((_height - p0z) / vz);
			if (tupper == 0)
				pupper = p0;
			else
				pupper = p0.add(v.scale(tupper));

			double x1 = pbase.getX().get();// pBase x coordinate
			double y1 = pbase.getY().get();// pBase y coordinate

			double x2 = pupper.getX().get();// pUpper x coordinate
			double y2 = pupper.getY().get();// pUpper y coordinate

			// whether this points is within the disc or not
			// px^2 + py^2 =< R^2
			if (x1 * x1 + y1 * y1 <= _radius * _radius)
				intersections.add(pbase);

			if (x2 * x2 + y2 * y2 <= _radius * _radius)
				intersections.add(pupper);
		}

		// testing collision against the body of Cylinder.
		double vx = v.getHead().getY().get();
		double vy = v.getHead().getY().get();

		double A = vx * vx + vy * vy;
		double B = 2 * (vx * p0x + vy * p0y);
		double C = p0x * p0x + p0y * p0y - _radius * _radius;

		// Solving quadratic equation
		double t1 = 0;
		double t2 = 0;
		try {
			t1 = (-B + Math.sqrt(B * B - 4 * A * C)) / (2 * A);
			t2 = (-B - Math.sqrt(B * B - 4 * A * C)) / (2 * A);
		} catch (Exception e) {
			// if delta is smaller than zero.
			return intersections;
		}

		Point3D p1;
		if (t1 == 0)
			p1 = p0;
		else
			p1 = p0.add(v.scale(t1));
		double p1z = p1.getZ().get();
		if (p1z > 0 && p1z < 15)
			intersections.add(p1);

		Point3D p2;
		if (t2 == 0)
			p2 = p0;
		else
			p2 = p0.add(v.scale(t2));		
		double p2z = p2.getZ().get();
		if (p2z > 0 && p2z < 15)
			intersections.add(p2);

		// Finally we will have four distinct t values: the two possible intersections
		// with the end caps and the two possible intersections with the sides of the
		// cylinder .
		return intersections;
	}
}
