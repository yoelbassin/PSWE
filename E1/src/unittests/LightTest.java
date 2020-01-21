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
	@Test
	public void lightTest1() {
		Scene scene = new Scene("Test scene 1");
		scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
		scene.setDistance(200);
		scene.setAmbient(new AmbientLight(new Color(0, 0, 20), 0));
		scene.setBackground(new Color(java.awt.Color.black));
		Geometries geometries = new Geometries();
		scene.setGeometries(geometries);
		Triangle triangle1 = new Triangle(new Color(java.awt.Color.blue), new Point3D(50, 50, 101),
				new Point3D(-50, 50, 100), new Point3D(-50, -50, 100));
		Triangle triangle2 = new Triangle(new Color(java.awt.Color.blue), new Point3D(-50, -50, 101),
				new Point3D(50, -50, 100), new Point3D(50, 50, 100));
		triangle1.material = new Material(0.5, 0.5, 20);
		triangle2.material = new Material(0.5, 0.5, 20);
		geometries.add(triangle1, triangle2);
		ImageWriter imageWriter = new ImageWriter("test1", 500, 500, 1000, 1000);
		List<LightSource> lights = new ArrayList<LightSource>();
		PointLight light1 = new PointLight(new Color(255, 100, 100), new Point3D(0, 0, 90), 1, 0.00005, 0.0000005);
		lights.add(light1);
		scene.setLights(lights);
		Render render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();
		imageWriter = new ImageWriter("test2", 500, 500, 1000, 1000);
		lights.clear();
		SpotLight light2 = new SpotLight(new Color(255, 100, 100), new Point3D(0, 0, 90), new Vector(0, 0, 1), 1,
				0.00005, 0.0000005);
		lights.add(light2);
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
		sphere.material = new Material(0.5, 0.5, 300);
		geometries.add(sphere);
		
		Color color = new Color(400, 300, 300);
		Point3D pos = new Point3D(-50, 50, 0);
		
		ImageWriter imageWriter = new ImageWriter("sphere-point", 500, 500, 600, 600);
		List<LightSource> lights = new ArrayList<LightSource>();
		PointLight light1 = new PointLight(color, pos, 1, 0.0005, 0.000005);
		lights.add(light1);
		scene.setLights(lights);
		Render render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();
		
		imageWriter = new ImageWriter("sphere-spot", 500, 500, 600, 600);
		lights.clear();
		SpotLight light2 = new SpotLight(color, pos, new Vector(1, -1, 2), 1, 0.0005, 0.000005);
		lights.add(light2);
		scene.setLights(lights);
		render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();
	}
}
