package primitives;

import java.lang.Math;

import static primitives.Util.*;

/**
 * Class represents point in 3D space.
 */
public class Point3D {
    static public final Point3D ZERO = new Point3D(0, 0, 0);

    private Coordinate x;
    private Coordinate y;
    private Coordinate z;

    // ***************** Constructors ********************** //
    /*
     * Construct Cartesian 3D space point by three decimal numbers.
     *
     * @param x coordinate by decimal number.
     *
     * @param y coordinate by decimal number.
     *
     * @param z coordinate by decimal number.
     */
    public Point3D(double x, double y, double z) {
        this.x = new Coordinate(x);
        this.y = new Coordinate(y);
        this.z = new Coordinate(z);
    }

    /*
     * Construct Cartesian 3D point by three Coordinates.
     *
     * @param x coordinate x of point.
     *
     * @param y coordinate y of point.
     *
     * @param z coordinate z of point.
     */

    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    // ***************** Getters ********************** //

    /**
     * Getter of coordinate x of point.
     *
     * @return coordinate x.
     */
    public Coordinate getX() {
        return x;
    }

    /**
     * Getter of coordinate y of point.
     *
     * @return coordinate y.
     */
    public Coordinate getY() {
        return y;
    }

    /**
     * Getter of coordinate z of point.
     *
     * @return coordinate z.
     */
    public Coordinate getZ() {
        return z;
    }

    // ***************** Administration ******************** //
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Point3D))
            return false;
        Point3D other = (Point3D) obj;
        return x.equals(other.x) && y.equals(other.y) && z.equals(other.z);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + "," + z + ")";
    }
    // ***************** Operations ******************** //

    /**
     * 3D point subtraction operation by coordinates .
     *
     * @param other initial 3D space point.
     * @return the vector which ends in the point and begins in other .
     */
    public Vector subtract(Point3D other) {
        double x1 = getX().get();
        double y1 = getY().get();
        double z1 = getZ().get();
        double x2 = other.getX().get();
        double y2 = other.getY().get();
        double z2 = other.getZ().get();
        return new Vector(new Point3D(x1 - x2, y1 - y2, z1 - z2));
    }

    /**
     * 3D point addition operation by coordinates .
     *
     * @param other Vector
     * @return the head point .
     */
    public Point3D add(Vector other) {
        double x1 = getX().get();
        double y1 = getY().get();
        double z1 = getZ().get();
        double x2 = other.head.getX().get();
        double y2 = other.head.getY().get();
        double z2 = other.head.getZ().get();
        return new Point3D(x1 + x2, y1 + y2, z1 + z2);
    }

    /**
     * Calculates the squared distance between the points
     *
     * @param other 3D point.
     * @return the squared distance
     */
    public double distance2(Point3D other) {
        double x1 = getX().get();
        double y1 = getY().get();
        double z1 = getZ().get();
        double x2 = other.getX().get();
        double y2 = other.getY().get();
        double z2 = other.getZ().get();
        double a = x1 - x2;
        double b = y1 - y2;
        double c = z1 - z2;
        return alignZero(a * a + b * b + c * c);
    }

    /**
     * Calculates the actual distance between points.
     *
     * @param other 3D point.
     * @return the distance
     */
    public double distance(Point3D other) {
        return Math.sqrt(distance2(other));
    }
}
