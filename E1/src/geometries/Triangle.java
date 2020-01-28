package geometries;

import primitives.*;

/**
 * @author bassi class triangle represents a triangle via 3 points on the same
 * plane
 */
public class Triangle extends Polygon {
    // ***************** Constructor ********************** //

    /**
     * constructs a triangle from three points
     *
     * @param p1, first point
     * @param p2, second point
     * @param p3, third point
     */
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1, p2, p3);
    }

    /**
     * constructs a triangle from three points and a color
     *
     * @param emission the color of the triangle
     * @param p1,      first point
     * @param p2,      second point
     * @param p3,      third point
     */
    public Triangle(Color emission, Point3D p1, Point3D p2, Point3D p3) {
        super(emission, p1, p2, p3);
    }

    /**
     * constructs a triangle from three points and a color
     *
     * @param emission the color of the triangle
     * @param material the material of the triangle
     * @param p1,      first point
     * @param p2,      second point
     * @param p3,      third point
     */
    public Triangle(Color emission, Material material, Point3D p1, Point3D p2, Point3D p3) {
        super(emission, material, p1, p2, p3);
    }
}
