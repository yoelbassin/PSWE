package unittests;

import elements.*;
import geometries.*;
import org.junit.Test;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

import java.util.ArrayList;
import java.util.List;

public class LightTest {
	@Test
	public void lightTest1() {
		Scene scene = new Scene("Test scene 1");
		scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
		scene.setDistance(500);
		scene.setAmbient(new AmbientLight(new Color(0, 0, 20), 0));
		scene.setBackground(new Color(java.awt.Color.black));
		Geometries geometries = new Geometries();
		scene.setGeometries(geometries);
		Triangle triangle1 = new Triangle(new Color(java.awt.Color.black), new Point3D(50, 50, 100),
				new Point3D(-50, 50, 100), new Point3D(-50, -50, 150));
		Triangle triangle2 = new Triangle(new Color(java.awt.Color.black), new Point3D(-50, -50, 150),
				new Point3D(50, -50, 150), new Point3D(50, 50, 100));
		triangle1.setMaterial(0.5, 0.5, 20);
		triangle2.setMaterial(0.5, 0.5, 20);
		geometries.add(triangle1, triangle2);
		Point3D pos = new Point3D(0, -30, 90);
		Color color = new Color(java.awt.Color.pink);

		// test 1 - 2 triangles with point light
		ImageWriter imageWriter = new ImageWriter("triangle-point", 500, 500, 600, 600);
		List<LightSource> lights = new ArrayList<LightSource>();
		PointLight light1 = new PointLight(color, pos, 1, 0.00005, 0.0000005);
		lights.add(light1);
		scene.setLights(lights);
		Render render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();

		// test 2 - 2 triangles with spot light
		imageWriter = new ImageWriter("triangle-spot", 500, 500, 600, 600);
		lights.clear();
		SpotLight light2 = new SpotLight(color, pos, new Vector(0, 0.7, 0.3), 1, 0.00005, 0.0000005);
		lights.add(light2);
		scene.setLights(lights);
		render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();

		// test 3 - 2 triangles with directional light
		imageWriter = new ImageWriter("triangle-direction", 500, 500, 600, 600);
		lights.clear();
		DirectionalLight light3 = new DirectionalLight(new Color(400, 300, 300), new Vector(0, 0, 1));
		lights.add(light3);
		scene.setLights(lights);
		render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();
	}

	@Test
	public void lightTest2() {
		Scene scene = new Scene("Test scene 2");
		scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
		scene.setDistance(200);
		scene.setAmbient(new AmbientLight(new Color(0, 0, 20), 0));
		scene.setBackground(Color.BLACK);
		Geometries geometries = new Geometries();
		scene.setGeometries(geometries);
		Sphere sphere = new Sphere(new Color(0, 0, 100), 50, new Point3D(0, 0, 100));
		sphere.setMaterial(0.5, 0.5, 300);
		geometries.add(sphere);
		Color color = new Color(400, 300, 300);
		Point3D pos = new Point3D(-50, 50, 0);

		// test 1 - sphere with point light
		ImageWriter imageWriter = new ImageWriter("sphere-point", 500, 500, 600, 600);
		List<LightSource> lights = new ArrayList<LightSource>();
		PointLight light1 = new PointLight(color, pos, 1, 0.0005, 0.000005);
		lights.add(light1);
		scene.setLights(lights);
		Render render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();

		// test 2 - sphere with spot light
		imageWriter = new ImageWriter("sphere-spot", 500, 500, 600, 600);
		lights.clear();
		SpotLight light2 = new SpotLight(color, pos, new Vector(1, -1, 2), 1, 0.0005, 0.000005);
		lights.add(light2);
		scene.setLights(lights);
		render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();

		// test 3 - sphere with directional light
		imageWriter = new ImageWriter("sphere-directional", 500, 500, 600, 600);
		lights.clear();
		DirectionalLight light3 = new DirectionalLight(new Color(400, 300, 300), new Vector(1, -1, 2));
		lights.add(light3);
		scene.setLights(lights);
		render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();
	}
}
