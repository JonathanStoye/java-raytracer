package Geometry;
import MatrixVector.*;
import Scene.*;

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
     * @param color color of the plane
     */
    public Plane(final Point3 a, final Normal3 n, final Color color) {
        super(color);
        this.a=a;
        this.n=n;
    }

    /**
     * Determines wether the current plane is hit by the given ray.
     * @param ray specific ray, which is potentially hitting the current object.
     * @return Hit object, which references the intersection value t, the current ray and the hit plane
     */
    @Override
    public Hit hit(final Ray ray){
        // if the dot produkt of the direction vector and the normal vector of the plane is null.
        // then they are orthognally to each other. Meaning the ray is not hitting the plane (paralel to it)
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
        if (t > 0){
            return new Hit(t, ray, this);
        } else {
            return null;
        }
    }
}
