package renderer;

import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.awt.*;
import java.util.List;

public class Render {
    ImageWriter image;
    Scene scene;

    // ***************** Constructors ********************** //
    public Render(ImageWriter image, Scene scene) {
        this.image = image;
        this.scene = scene;
    }

    // ***************** Operations ******************** //
    public void renderImage() {
        for (int i = 0; i < image.getNy(); ++i)
            for (int j = 0; j < image.getNx(); ++j) {
                Ray ray = scene.getCamera().constructRayThroughPixel(image.getNx(), image.getNy(), j, i, scene.getDistance(), image.getWidth(), image.getHeight());
                List<Point3D> intersectionPoints = scene.getGeometries().findIntersections(ray);
                if (intersectionPoints == null)
                    image.writePixel(j, i, scene.getBackground().getColor());
                else {
                    Point3D closestPoint = getClosestPoint(intersectionPoints);
                    image.writePixel(j, i, calcColor(closestPoint).getColor());
                }
            }
    }

    private primitives.Color calcColor(Point3D p) {
        return scene.getAmbient().getIntensity();
    }

    public Point3D getClosestPoint(List<Point3D> points) {
        Point3D a = points.get(0);

        if(points.size() == 1)
            return a;
        Point3D b = points.get(1);
        Point3D p0 =scene.getCamera().getP0();
        if(p0.distance(a) < p0.distance(b))
            return a;
        return b;
    }

    public void printGrid(int interval, Color color) {
        for (int i = 0; i < image.getNx(); i++) {
            for (int j = 0; j < image.getNy(); j++)
                if (i % interval == 0 || j % interval == 0)
                    image.writePixel(j, i, color);
        }
    }

    public void writeToimage()
    {
        image.writeToimage();
    }
}
