package Geometry;
import MatrixVector.*;
import Scene.*;
import Utillities.Debugging;
import Material.*;

/**
 * Created by Jonathan on 05.06.15.
 * Implemented by David on 06.06.15.
 * @author d.derichs
 */
public class Plane extends Geometry {

    /**
     * vector of the plane
     */
    public final Point3 a;

    /**
     * normal vector of the plane
     */
    public final Normal3 n;

    /**
     * Initializes the plane with given parameters
     * @param a basic vector of the plane
     * @param n normal vector of the plane
     * @param material Material of the plane
     */
    public Plane(final Point3 a, final Normal3 n, final Material material, final double threshold) {
        super(material, threshold);
        this.a=a;
        this.n=n;
    }

    /**
     * Determines whether the current plane is hit by the given ray.
     * @param ray specific ray, which is potentially hitting the current object.
     * @return Hit object, which references the intersection value t, the current ray and the hit plane
     */
    @Override
    public Hit hit(final Ray ray){
        // if the dot product of the direction vector and the normal vector of the plane is null.
        // then they are orthognale to each other. Meaning the ray is not hitting the plane (parallel to it)
        // or it is part of it (infinite number of intersections).
        if (ray.direction.dot(n) == 0) {
            return null;
        }

        // formular for the calculation:      (a- o) DOT n
        //                               t=  --------------
        //                                     d DOT n
        // DOT stands for vector multiplication
        // a is the point-vector on the plane
        // o ist the origin-vector of the ray
        // d ist the direction-vector of the ray
        final double t = (a.sub(ray.origin).dot(n)) / (ray.direction.dot(n));
        if (t > super.threshold){
            return new Hit(t, ray, this, this.n);
        } else {
            return null;
        }
    }

    public static void testHit(){
        Plane testPlane = new Plane(new Point3(50.0,50.0,0.0), new Normal3(0.0, 1.0, 0.0), new SingleColorMaterial(new Color(0,1,0)), 0.0001);

        //Testvector, that is not hitting the Plane:
        Ray testRay1 = new Ray(new Point3(0.0, 60.0, 0), new Vector3(100.0, 0.0, 0.0));

        //Testvector, that is hitting the Plane:
        Ray testRay2 = new Ray(new Point3(50.0, 60.0, 0), new Vector3(0.0, -20.0, 0.0));

        //Testvector, which is pointing away from the plane:
        Ray testRay3 = new Ray(new Point3(0.0, 60.0, 0), new Vector3(10.0, 10.0, 0.0));

        if (testPlane.hit(testRay1) == null){
            Debugging.log("Successful: testRay1 is parallel to the Plane and therefor not hitting the Plane");
        }else {
            Debugging.log("Unsuccesssful: testRay1 is hitting the Plane at t=" + testPlane.hit(testRay1).t );
        }
        if(testPlane.hit(testRay2) != null){
            Debugging.log("Successful: restRay2 is hitting the Plane at t="+ testPlane.hit(testRay2).t);
        } else {
            Debugging.log("Unsuccessful: restRay2 is not the Plane.");
        }
        if (testPlane.hit(testRay3) == null){
            Debugging.log("Successful: testRay3 is pointing away from the Plane and therefore is not hitting the Plane");
        }else {
            Debugging.log("Unsuccesssful: testRay3 is hitting the Plane at t=" + testPlane.hit(testRay3).t );
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Plane plane = (Plane) o;

        if (!a.equals(plane.a)) return false;
        return n.equals(plane.n);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + a.hashCode();
        result = 31 * result + n.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "a=" + a +
                ", n=" + n +
                '}';
    }
}
