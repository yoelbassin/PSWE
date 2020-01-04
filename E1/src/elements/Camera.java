package elements;

import primitives.*;

public class Camera {
    Point3D _p0;
    Vector _vUp;
    Vector _vTo;
    Vector _vRight;

    public Point3D get_p0() {
        return _p0;
    }

    public Vector get_vUp() {
        return _vUp;
    }

    public Vector get_vTo() {
        return _vTo;
    }

    public Vector get_vRight() {
        return _vRight;
    }

    public Camera(Point3D p0, Vector vUp, Vector vTo) {
        vUp.normalize();
        vTo.normalize();
        if (vUp.dotProduct(vTo) != 0)
            throw new IllegalArgumentException();
        _p0 = p0;
        _vTo = vTo;
        _vUp = vUp;
    }

    public Ray constructRayThroughPixel (int nX, int nY, int j, int i, double screenDistance,double screenWidth, double screenHeight)
    {
        return null;
    }
}
