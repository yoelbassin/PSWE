package elements;

import primitives.*;

/**
 * class Camera represents a view point in the 3D space
 */
public class Camera {
    Point3D _p0; //location of the camera in the space
    Vector _vUp; //Y axis vector
    Vector _vTo; //Z axis vector
    Vector _vRight; //X axis vector
    // ***************** Constructors ********************** //

    /**
     * Construct Camera location and axis's
     *
     * @param p0  location of the camera
     * @param vUp Y axis of the camera
     * @param vTo Z axis of the camera
     * @throws newIllegalException if vTo is not orthogonal to vUp
     */
    public Camera(Point3D p0, Vector vUp, Vector vTo) {
        vUp.normalize();
        vTo.normalize();
        if (vUp.dotProduct(vTo) != 0)
            throw new IllegalArgumentException();
        _p0 = p0;
        _vTo = vTo;
        _vUp = vUp;
        _vRight = _vTo.crossProduct(_vUp).normalize();
    }
    // ***************** Getters/Setters ********************** //

    /**
     * Getter of camera's location
     *
     * @return point of location
     */
    public Point3D get_p0() {
        return _p0;
    }

    /**
     * Getter of Y axis in relation to the camera
     *
     * @return Y axis vector
     */
    public Vector get_vUp() {
        return _vUp;
    }

    /**
     * Getter of Z axis in relation to the camera
     *
     * @return Z axis vector
     */
    public Vector get_vTo() {
        return _vTo;
    }

    /**
     * Getter of X axis in relation to the camera
     *
     * @return X axis vector
     */
    public Vector get_vRight() {
        return _vRight;
    }
    // ***************** Operations ******************** //

    /**
     * Creates a ray though a certain pixel in the view plane
     *
     * @param nX             number of X pixels
     * @param nY             number of Y pixels
     * @param j              index of the X pixel
     * @param i              index of the Y pixel
     * @param screenDistance the distance from the camera to the view plane
     * @param screenWidth    view plane length (Y axis)
     * @param screenHeight   view plane length (X axis)
     * @return the ray through the pixel
     */
    public Ray constructRayThroughPixel(double nX, double nY, int j, int i, double screenDistance, double screenWidth, double screenHeight) {
        Point3D pC = _p0.add(_vTo.scale(screenDistance));
        double Ny = nY;
        double Nx = nX;
        double rX = screenWidth / nX;
        double rY = screenHeight / nY;
        double yi = (i - (Ny / 2)) * rY + (rY / 2);
        double xj = (j - (Nx / 2)) * rX + (rX / 2);
        Point3D pIJ = pC;
        Point3D temp = pC;
        if (xj != 0) pIJ = temp.add(_vRight.scale(xj));
        temp = pIJ;
        if (yi != 0) pIJ = temp.add(_vUp.scale(-yi));
        Vector vij = pIJ.subtract(_p0);
        return new Ray(_p0, vij.normalize());
    }
}
