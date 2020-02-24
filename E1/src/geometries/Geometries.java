package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

/**
 * class represents a list 3D space shapes which could intersect a ray.
 *
 * @author bassi
 * @author asaf0
 */
public class Geometries implements Intersectable {
	List<Intersectable> shapes = new ArrayList<Intersectable>();

	/**
	 * Default constructor constructs the list as an empty ArrayList
	 */
	public Geometries() {
		shapes = new ArrayList<Intersectable>();
	}

	/**
	 * constructs a list with geometries (one or more)
	 *
	 * @param geometries - list of geometry shapes.
	 */
	public Geometries(Intersectable... geometries) {
		for (int i = 0; i < geometries.length; ++i) {
			shapes.add(geometries[i]);
		}
	}
	// ***************** Operations ******************** //

	/**
	 * adds geometries to the list
	 *
	 * @param geometries
	 */
	public void add(Intersectable... geometries) {
		for (int i = 0; i < geometries.length; ++i) {
			shapes.add(geometries[i]);
		}
	}

	/**
	 * finds intersections of the ray with the geometries that are in the list
	 *
	 * @param ray in space.
	 * @return intersection points
	 */
	public List<GeoPoint> findIntersections(Ray ray) {
		List<GeoPoint> intersections = new ArrayList<>();
		for (Intersectable shape : shapes) {
			List<GeoPoint> ints = shape.findIntersections(ray);
			if (ints != null)
				intersections.addAll(ints);
		}
		// if no intersections were found, return null
		if (intersections.size() == 0)
			return null;
		return intersections;
	}

	/**
	 * calculate the boundaries of the geometries
	 * @return boundary of geometries
	 */
	public BoundaryVolume boundaryVolume()
    {
    	BoundaryVolume boundary ;
    	double minX = Double.POSITIVE_INFINITY,minY = Double.POSITIVE_INFINITY,minZ = Double.POSITIVE_INFINITY;
    	double maxX = Double.NEGATIVE_INFINITY ,maxY = Double.NEGATIVE_INFINITY ,maxZ = Double.NEGATIVE_INFINITY;
    	double x=0 ,y=0 ,z =  0;
    	
    	for(Intersectable shape : shapes)
    	{
    		if(!(shape instanceof Plane)) {
    		boundary = shape.boundaryVolume();
    		x = boundary.min.getX().get();
    		y = boundary.min.getY().get();
    		z = boundary.min.getZ().get();
    		if(x<minX)minX=x;  if(y<minY)
    	    minY=y; if(z<minZ) minZ=z;
    	    x = boundary.max.getX().get();
    		y = boundary.max.getY().get();
    		z = boundary.max.getZ().get();
    		if(x>maxX) maxX=x;if(y>maxY)
        	maxY=y;if(z>maxZ) maxZ=z;
    		}
    		
    	}
    	
        return new BoundaryVolume(new Point3D(minX,minY,minZ),//
        		new Point3D(maxX,maxY,maxZ),new Geometries(this));
    	

}
	/**
	 * Gets the amount of shapes
	 * @return amount of shapes in geometries
	 */
	public int getAmount() {
		return shapes.size();
	}
    
	/**
	 * getter of shapes
	 * @return geometries' shapes 
	 */
	public List<Intersectable> getShapes() {
		// TODO Auto-generated method stub
		return shapes;
	}
}
