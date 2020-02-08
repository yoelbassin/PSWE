package scene;

import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    String sceneName;
    Color background;
    AmbientLight ambient;
    Geometries geometries;
    List<LightSource> _lights = new ArrayList<>();
    Camera camera;
    double distance;
    int sampleCount;
    // ***************** Constructors ********************** //

    /**
     * creates an empty scene with a name
     *
     * @param name
     */
    public Scene(String name) {
        sceneName = name;
    }
    // ***************** Getters/Setters ********************** //

    /**
     * setter of the scene ray samples count
     *
     * @param sampleCount
     */
    public void setSampleCount(int sampleCount) {
        this.sampleCount = sampleCount;
    }

    /**
     * getter of the scene ray samples count
     *
     * @return sceneName
     */
    public int getSampleCount() {
        return sampleCount;
    }

    /**
     * getter of the scene name
     *
     * @return sceneName
     */
    public String getSceneName() {
        return sceneName;
    }

    /**
     * getter of the background color
     *
     * @return background
     */
    public Color getBackground() {
        return background;
    }

    /**
     * setter of the background color
     *
     * @param background
     */
    public void setBackground(Color background) {
        this.background = background;
    }

    /**
     * getter of the ambient light
     *
     * @return ambient
     */
    public AmbientLight getAmbient() {
        return ambient;
    }

    /**
     * setter of the ambient light
     *
     * @param ambient
     */
    public void setAmbient(AmbientLight ambient) {
        this.ambient = ambient;
    }

    /**
     * getter of geometries list
     *
     * @return geometries
     */
    public Geometries getGeometries() {
        return geometries;
    }

    /**
     * setter of the 3D model with the geometries list
     *
     * @param geometries
     */
    public void setGeometries(Geometries geometries) {
        this.geometries = geometries;
    }

    /**
     * getter of lights in the scene
     *
     * @return lights
     */
    public List<LightSource> getLights() {
        return _lights;
    }

    /**
     * setter of the lights in the scene
     *
     * @param _lights
     */
    public void setLights(List<LightSource> _lights) {
        this._lights = _lights;
    }

    /**
     * getter of camera
     *
     * @return camera
     */
    public Camera getCamera() {
        return camera;
    }

    /**
     * setter of camera
     *
     * @param camera
     */
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    /**
     * getter of distance
     *
     * @return distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * setter of distance
     *
     * @param distance
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }
    // ***************** Operations ******************** //

    /**
     * changes the camera location and distance from view plane
     *
     * @param cam
     * @param dist
     */
    public void updateCamera(Camera cam, double dist) {
        camera = cam;
        distance = dist;
    }

    /**
     * changes the background color
     *
     * @param color
     */
    public void updateBackground(Color color) {
        background = color;
    }

    /**
     * changes the ambient light
     *
     * @param amb
     */
    public void updateAmbient(AmbientLight amb) {
        ambient = amb;
    }

    /**
     * adds shapes to the 3D model
     *
     * @param shapes
     */
    public void addIntersectable(Intersectable... shapes) {
        geometries.add(shapes);
    }

    /**
     * adds lights to the scene
     *
     * @param lights
     */
    public void addLight(LightSource... lights) {
        for (int i = 0; i < lights.length; ++i)
            _lights.add(lights[i]);
    }
}
