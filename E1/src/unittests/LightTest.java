package unittests;

import elements.*;
import geometries.*;
import org.junit.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

import java.util.ArrayList;
import java.util.List;

public class LightTest {
    public Scene createScene(String name) {
        Scene scene = new Scene(name);
        scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
        scene.setDistance(200);
        scene.setAmbient(new AmbientLight(new Color(255, 255, 255), 0));
        scene.setBackground(Color.BLACK);
        scene.setSampleCount(80);
        return scene;
    }

    /**
     * test case for lighting
     */
    @Test
    public void lightingTest() {
        Scene scene = createScene("Basic Light Tests");
        Geometries geometries = new Geometries();
        scene.setGeometries(geometries);
        Triangle triangle1 = new Triangle(new Color(0, 0, 0), new Material(0.5, 0.5, 300), new Point3D(-250, 250, 90),
                new Point3D(-250, -250, 120), new Point3D(250, 250, 90));
        Triangle triangle2 = new Triangle(new Color(0, 0, 0), new Material(0.5, 0.5, 300), new Point3D(248, 248, 90),
                new Point3D(248, -248, 120), new Point3D(-248, -248, 120));
        geometries.add(triangle1, triangle2);
        Point3D pos = new Point3D(0, -30, 90);
        Color color = new Color(java.awt.Color.pink);

        // test 1 - 2 triangles with point light
        ImageWriter imageWriter = new ImageWriter("PL triangles", 500, 500, 600, 600);
        List<LightSource> lights = new ArrayList<LightSource>();
        PointLight light1 = new PointLight(color, pos, 1, 0.00005, 0.0000005);
        lights.add(light1);
        scene.setLights(lights);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();

        // test 2 - 2 triangles with spot light
        imageWriter = new ImageWriter("SP triangles", 500, 500, 600, 600);
        lights.clear();
        SpotLight light2 = new SpotLight(color, pos, new Vector(0, 0.7, 0.3), 1, 0.00005, 0.0000005);
        lights.add(light2);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();

        // test 3 - 2 triangles with directional light
        imageWriter = new ImageWriter("DL triangles", 500, 500, 600, 600);
        lights.clear();
        DirectionalLight light3 = new DirectionalLight(new Color(400, 300, 300), new Vector(0, 0, 1));
        lights.add(light3);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();

        geometries = new Geometries();
        scene.setGeometries(geometries);
        Sphere sphere = new Sphere(new Color(0, 0, 100), new Material(0.5, 0.5, 300), 50, new Point3D(0, 0, 100));
        geometries.add(sphere);
        color = new Color(400, 300, 300);
        pos = new Point3D(-50, 50, 0);

        // test 1 - sphere with point light
        imageWriter = new ImageWriter("PL sphere", 500, 500, 600, 600);
        lights = new ArrayList<LightSource>();
        PointLight pointLight2 = new PointLight(color, pos, 1, 0.0005, 0.000005);
        lights.add(pointLight2);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();

        // test 2 - sphere with spot light
        imageWriter = new ImageWriter("SL sphere", 500, 500, 600, 600);
        lights.clear();
        SpotLight spotLight2 = new SpotLight(color, pos, new Vector(1, -1, 2), 1, 0.0005, 0.000005);
        lights.add(spotLight2);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();

        // test 3 - sphere with directional light
        imageWriter = new ImageWriter("DL sphere", 500, 500, 600, 600);
        lights.clear();
        DirectionalLight Directional2 = new DirectionalLight(new Color(400, 300, 300), new Vector(1, -1, 2));
        lights.add(Directional2);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
    }

    /**
     * test case for shadows
     */
    @Test
    public void shadowTest() {
        Scene scene = createScene("Shadow tests");
        Geometries geometries = new Geometries();
        scene.setGeometries(geometries);
        Sphere sphere = new Sphere(new Color(0, 0, 100), new Material(0.5, 0.5, 300), 50, new Point3D(0, 0, 100));
        Triangle triangle = new Triangle(new Color(0, 0, 100), new Material(0.5, 0.5, 300), new Point3D(-30, 25, 40), new Point3D(-30, 15, 40),
                new Point3D(-20, 25, 40));
        geometries.add(sphere);
        geometries.add(triangle);
        Color color = new Color(400, 300, 300);
        Point3D pos = new Point3D(-35, 30, 0);

        ImageWriter imageWriter = new ImageWriter("PL sphere shadow", 500, 500, 600, 600);
        List<LightSource> lights = new ArrayList<LightSource>();
        PointLight point_light = new PointLight(color, pos, 1, 0.0005, 0.000005);
        lights.add(point_light);
        scene.setLights(lights);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();

        imageWriter = new ImageWriter("DL sphere shadow", 500, 500, 600, 600);
        lights.clear();
        DirectionalLight directional_light = new DirectionalLight(new Color(400, 300, 300), new Vector(1, -1, 2));
        lights.add(directional_light);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();

        imageWriter = new ImageWriter("SL sphere shadow", 500, 500, 600, 600);
        lights.clear();
        SpotLight spot_light = new SpotLight(color, pos, new Vector(1, 2, 3), 1, 0.0005, 0.000005);
        lights.add(spot_light);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();

        triangle = new Triangle(new Color(0, 0, 100), new Material(0.5, 0.5, 300), new Point3D(-30, 20, 40), new Point3D(-30, 10, 40),
                new Point3D(-20, 20, 40));
        geometries = new Geometries();
        scene.setGeometries(geometries);
        geometries.add(sphere);
        geometries.add(triangle);

        imageWriter = new ImageWriter("PL sphere shadow pos2", 500, 500, 600, 600);
        lights.clear();
        lights = new ArrayList<LightSource>();
        point_light = new PointLight(color, pos, 1, 0.0005, 0.000005);
        lights.add(point_light);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();

        triangle = new Triangle(new Color(0, 0, 100), new Material(0.5, 0.5, 300), new Point3D(-20, 20, 40), new Point3D(-20, 10, 40),
                new Point3D(-10, 20, 40));
        geometries = new Geometries();
        geometries.add(sphere);
        geometries.add(triangle);
        scene.setGeometries(geometries);

        imageWriter = new ImageWriter("PL sphere shadow pos3", 500, 500, 600, 600);
        lights.clear();
        lights = new ArrayList<LightSource>();
        point_light = new PointLight(color, pos, 1, 0.0005, 0.000005);
        lights.add(point_light);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();

        // test case : changing light position
        Triangle triangle1 = new Triangle(new Color(0, 0, 100), new Material(0.5, 0.5, 300), new Point3D(-30, 20, 40), new Point3D(-30, 10, 40),
                new Point3D(-20, 20, 40));

        // point light
        geometries = new Geometries();
        geometries.add(triangle1);
        scene.setGeometries(geometries);
        geometries.add(sphere);
        geometries.add(triangle1);

        imageWriter = new ImageWriter("PL sphere shadow lightPos2", 500, 500, 600, 600);
        pos = new Point3D(-25, 20, 20);
        lights.clear();
        lights = new ArrayList<LightSource>();
        point_light = new PointLight(color, pos, 1, 0.0005, 0.000005);
        lights.add(point_light);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        lights.clear();
        lights = new ArrayList<LightSource>();
        point_light = new PointLight(color, pos, 1, 0.0005, 0.000005);
        lights.add(point_light);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();

        imageWriter = new ImageWriter("PL sphere shadow lightPos3", 500, 500, 600, 600);
        pos = new Point3D(-35, 30, 20);
        lights.clear();
        lights = new ArrayList<LightSource>();
        point_light = new PointLight(color, pos, 1, 0.0005, 0.000005);
        lights.add(point_light);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        lights.clear();
        lights = new ArrayList<LightSource>();
        point_light = new PointLight(color, pos, 1, 0.0005, 0.000005);
        lights.add(point_light);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
    }

    /**
     * test case for refraction functionality
     */
    @Test
    public void transparencyTest() {
        Scene scene = createScene("Transparency tests");
        Geometries geometries = new Geometries();
        scene.setGeometries(geometries);
        Sphere sphere1 = new Sphere(new Color(0, 0, 100), new Material(0.5, 0.5, 300, 0, 0.3), 30, new Point3D(0, 0, 60));
        Sphere sphere2 = new Sphere(new Color(0, 0, 100), new Material(0.5, 0.5, 300, 0, 1), 10, new Point3D(0, 0, 60));
        geometries.add(sphere1, sphere2);
        Point3D pos = new Point3D(-50, 50, 0);
        Color color = new Color(400, 300, 300);

        ImageWriter imageWriter = new ImageWriter("Sphere within a sphere", 500, 500, 600, 600);
        List<LightSource> lights = new ArrayList<LightSource>();
        PointLight light1 = new PointLight(color, pos, 1, 0.00005, 0.0000005);
        lights.add(light1);
        scene.setLights(lights);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
    }

    /**
     * integration tests for reflection and refraction
     */
    @Test
    public void testReflectionRefracted() {
        Scene scene = createScene("reflection test");
        scene.setDistance(150);
        Geometries geometries = new Geometries();
        scene.setGeometries(geometries);
        Plane plane = new Plane(new Color(0, 0, 0), new Material(0.5, 0.5, 300, 0.8, 0), new Point3D(0, 50, 60), new Vector(0, 1, 0));
        Sphere sphere = new Sphere(new Color(0, 0, 20), new Material(0.5, 0.5, 300, 0.2, 0.5, 1), 20, new Point3D(0, 30, 80));
        Sphere sphere2 = new Sphere(new Color(0, 20, 0), new Material(0.5, 0.5, 300, 0.2, 0), 9, new Point3D(0, 30, 80));
        Sphere sphere3 = new Sphere(new Color(20, 20, 0), new Material(0.5, 0.5, 300, 0.2, 0), 10, new Point3D(0, 0, 80));
        Sphere sphere4 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), 5, new Point3D(0, -15, 80));
        geometries.add(sphere3, sphere4, sphere2, plane, sphere);

        Point3D pos = new Point3D(20, -50, 0);
        Color color = new Color(400, 300, 300);

        PointLight point_light = new PointLight(color, pos, 1, 0.0005, 0.000005);

        ImageWriter imageWriter = new ImageWriter("spheres building", 500, 500, 1200, 1200);
        List<LightSource> lights = new ArrayList<LightSource>();
        lights.add(point_light);
        scene.setLights(lights);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
    }

    /**
     * test case for matte and glossy surfaces
     */
    @Test
    public void testGlossySurface() {
        Scene scene = createScene("Glossy test");
        scene.setDistance(200);
        Geometries geometries = new Geometries();
        scene.setGeometries(geometries);
        Plane plane = new Plane(new Color(0, 0, 0), new Material(0.5, 0.5, 300, 0.4, 0, 0.2), new Point3D(0, 20, 100), new Vector(0, 1, 0)); //ground
        Plane plane2 = new Plane(new Color(0, 30, 30), new Material(0.5, 0.5, 1200, 0, 0, 0), new Point3D(0, 20, 600), new Vector(0, 0, 1)); //background plane
        Sphere sphere = new Sphere(new Color(0, 0, 20), new Material(0.5, 0.5, 1200, 0.5, 0, 0.1), 20, new Point3D(75, -3, 140)); //sphere 1
        Sphere sphere2 = new Sphere(new Color(0, 0, 20), new Material(0.5, 0.5, 1200, 0.5, 0, 0.1), 20, new Point3D(25, -3, 140)); //sphere 2
        Sphere sphere3 = new Sphere(new Color(0, 0, 20), new Material(0.5, 0.5, 1200, 0.5, 0, 0.1), 20, new Point3D(-75, -3, 140)); //sphere 3
        Sphere sphere4 = new Sphere(new Color(0, 0, 20), new Material(0.5, 0.5, 1200, 0.5, 0, 0.1), 20, new Point3D(-25, -3, 140)); //sphere 4
        Polygon square1 = new Polygon(new Color(0, 0, 0), new Material(0.1, 0.1, 300, 0, 0.9, 0.2), new Point3D(75, 20, 100), new Point3D(36, 20, 100), new Point3D(36, -40, 100), new Point3D(75, -40, 100)); //window for sphere 1
        Polygon square2 = new Polygon(new Color(0, 0, 0), new Material(0.1, 0.1, 300, 0, 0.9, 0.5), new Point3D(1, 20, 100), new Point3D(34, 20, 100), new Point3D(34, -40, 100), new Point3D(1, -40, 100)); //window for sphere 2
        Polygon square3 = new Polygon(new Color(0, 0, 0), new Material(0.1, 0.1, 300, 0, 0.9, 1), new Point3D(-75, 20, 100), new Point3D(-36, 20, 100), new Point3D(-36, -40, 100), new Point3D(-75, -40, 100)); //window for sphere 3
        Polygon square4 = new Polygon(new Color(0, 0, 0), new Material(0.1, 0.1, 300, 0, 0.9, 2), new Point3D(-1, 20, 100), new Point3D(-34, 20, 100), new Point3D(-34, -40, 100), new Point3D(-1, -40, 100)); //window for sphere 4

        geometries.add(plane, plane2, sphere, sphere2, sphere3, sphere4, square1, square2, square3, square4);

        Point3D pos = new Point3D(0, -30, 30);
        Point3D pos2 = new Point3D(0, -60, 0);
        Color color = new Color(400, 300, 300);

        PointLight point_light = new PointLight(color, pos, 1, 0.0005, 0.000005);
        DirectionalLight directional = new DirectionalLight(color, new Vector(0, 0, -1));

        ImageWriter imageWriter = new ImageWriter("Glossy test", 500, 500, 600, 600);
        List<LightSource> lights = new ArrayList<LightSource>();
        lights.add(point_light);
        scene.setLights(lights);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
    }

    /**
     * test case for object behind light
     */
    @Test
    public void testShadowLight() {
        Scene scene = createScene("shadow light test");
        scene.setDistance(150);
        Geometries geometries = new Geometries();
        scene.setGeometries(geometries);
        Plane plane = new Plane(new Color(0, 10, 0), new Material(0.5, 0.5, 300, 0, 0), new Point3D(0, 20, 60), new Vector(0, 0, -1));
        Sphere sphere = new Sphere(new Color(0, 0, 20), new Material(0.5, 0, 300, 0, 0), 20, new Point3D(0, 0, -20));
        geometries.add(plane, sphere);

        Point3D pos = new Point3D(0, 0, 20);
        Color color = new Color(400, 300, 300);

        PointLight point_light = new PointLight(color, pos, 1, 0.0005, 0.000005);

        ImageWriter imageWriter = new ImageWriter("Shadow light test", 500, 500, 1200, 1200);
        List<LightSource> lights = new ArrayList<LightSource>();
        lights.add(point_light);
        scene.setLights(lights);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
    }
}
