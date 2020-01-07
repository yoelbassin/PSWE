package primitives;

/**
 * Class represents 3D space ray.
 */
public class Ray {
    private Vector dir;
    private Point3D p;

    // ***************** Constructors ********************** //
    /*
     * Construct a ray with a initial point and direction vector.
     *
     * @param vec of direction .
     *
     * @param p initial point
     */
    public Ray(Point3D p, Vector vec) {
        this.dir = vec.normalized();
        this.p = p;
    }
    // ***************** Getters ********************** //

    /**
     * Getter of direction vector
     *
     * @return direction vector
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * Getter of initial point
     *
     * @return initial point
     */
    public Point3D getBasePoint() {
        return p;
    }

    // ***************** Administration ******************** //
    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null)
            return false;
        if (!(other instanceof Ray))
            return false;
        Ray obj = (Ray) other;
        return dir.equals(obj.getDir()) && p.equals(obj.p);
    }

    @Override
    public String toString() {
        return "[" + p + dir + ")";
    }
}
