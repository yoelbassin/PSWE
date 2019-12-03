
/*
 * Yoel Bassin
 * 212431886 
 * Asaf Hillel 
 * 323824730
 * bassin.yoel@gmail.com
 * asafdavi@g.jct.ac.il
 */

import primitives.*;
import geometries.*;

public class main {
	public static void main(String args[]) {
			Tube tube = new Tube(new Ray(new Vector(0,1,0), new Point3D(0,0,0)), 1);
			System.out.println("dfsf " + tube.getNormal(new Point3D(1,1,0)));
	}

}
