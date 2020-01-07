package scene;

import elements.AmbientLight;
import elements.Camera;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

public class Scene {
    String sceneName;
    Color background;
    AmbientLight ambient;
    Geometries geometries;
    Camera camera;
    double distance;

    // ***************** Constructors ********************** //
    public Scene(String name) {
        sceneName = name;
    }
    // ***************** Getters/Setters ********************** //


    public String getSceneName() {
        return sceneName;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public AmbientLight getAmbient() {
        return ambient;
    }

    public void setAmbient(AmbientLight ambient) {
        this.ambient = ambient;
    }

    public Geometries getGeometries() {
        return geometries;
    }

    public void setGeometries(Geometries geometries) {
        this.geometries = geometries;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    // ***************** Operations ******************** //

    public void updateCamera(Camera cam, double dist) {
        camera = cam;
        distance = dist;
    }

    public void updateBackground(Color color) {
        background = color;
    }

    public void updateAmbient(AmbientLight amb) {
        ambient = amb;
    }

    public void addIntersectable(Intersectable... shapes) {
        geometries.add(shapes);
    }
}
