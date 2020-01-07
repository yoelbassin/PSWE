package geometries;

import primitives.*;
/**
 * class Tube represents a tube in the space
 */

import static primitives.Util.*;

import java.util.List;

public class Tube extends RadialGeometry {

    Ray _axisRay;
    // ***************** Constructors ********************** //

    /**
     * constructs a tube from axis and radius
     *
     * @param axis,   the axis of the tube
     * @param radius, the radius of the tube
     */
    public Tube(Ray axis, double radius) {
        super(radius);
        _axisRay = axis;
    }
    // ***************** Getters/Setters ********************** //

    /**
     * Gets the axis of the tube
     *
     * @return axis of the tube
     */
    public Ray getAxisRay() {
        return _axisRay;
    }
    // ***************** Operations ******************** //

    /**
     * Gets the normal of the tube at a certain point
     *
     * @param p, the point of the normal
     * @return the normal of the tube
     */
    public Vector getNormal(Point3D p) {
        Point3D p0 = _axisRay.getBasePoint();
        Vector v = _axisRay.getDir();

        Vector u = null;
        try {
            u = p.subtract(p0); // vector from p0 to p
        } catch (Exception e) {
            return v;
        }

        double t = alignZero(v.dotProduct(u)); // size of projection of vector u on the ray
        if (t == 0)
            return p.subtract(p0).normalize();

        // point on the ray and plane crossing P and orthogonal to the ray
        Point3D o = p0.add(v.scale(t));
        return p.subtract(o).normalize();
    }

   /**find intersection with a tube .
	 * @param ray which we want to find intersections with it
	 * @return intersection list between zero point and two points 
	 * */
	public List<Point3D> findIntersections(Ray ray) {
		Point3D p0 = ray.getBasePoint();
		Vector v = ray.getDir();

		double p0x = p0.getX().get();
		double p0y = p0.getY().get();

		List<Point3D> intersections = new ArrayList<Point3D>();
		
		// testing collision against the body of Cylinder.
		double vx = v.getHead().getY().get();
		double vy = v.getHead().getY().get();
		double A = vx * vx + vy * vy;
		double B = 2 * (vx * p0x + vy * p0y);
		double C = p0x * p0x + p0y * p0y - _radius * _radius;

		// Solving quadratic equation
		double t1 =0 ;
		double t2 =0; 
		try {
		 t1 = (-B + Math.sqrt(B * B - 4 * A * C)) / (2 * A);
		 t2 = (-B - Math.sqrt(B * B - 4 * A * C)) / (2 * A);
		}
		catch(Exception e)
		{
			//if delta is smaller than zero.
			return null ;
		}
		
		Point3D p1 = p0.add(v.scale(t1));	
		intersections.add(p1);

		Point3D p2 = p0.add(v.scale(t2));
		intersections.add(p2);//then add it to list.
		
		// Finally we will have four distinct t values: the two possible intersections
		// with the end caps and the two possible intersections with the sides of the
		// cylinder .
		return intersections;
}
