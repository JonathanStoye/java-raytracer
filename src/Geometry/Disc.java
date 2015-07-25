package Geometry;

import Material.Material;
import MatrixVector.Normal3;
import MatrixVector.Point3;
import MatrixVector.Vector3;
import Scene.Hit;
import Scene.Ray;

/**
 * Created by Vickarl on 25.07.15.
 */
public class Disc extends Plane
{
    final Double radius;
    /**
     * Initializes the plane with given parameters
     *
     * @param a        basic vector of the plane
     * @param n        normal vector of the plane
     * @param material Material of the plane
     */
    public Disc(Point3 a, Normal3 n, Material material, Double radius)
    {
        super(a, n, material);
        this.radius = radius;
    }

    @Override
    public Hit hit(Ray ray)
    {
        // if the dot product of the direction vector and the normal vector of the plane is null.
        // then they are orthognale to each other. Meaning the ray is not hitting the plane (parallel to it)
        // or it is part of it (infinite number of intersections).
        if (ray.direction.dot(n) == 0) {
            return null;
        }

        //The disc is a round Plane, so we use the Planes calculations and later put these in the Sphere calculations
        //
        // formular for the calculation (Plane):      (c - o) DOT n
        //                                        t=  --------------
        //                                              d DOT n
        // DOT stands for vector multiplication
        // c is the center on the planedisc
        // o ist the origin-vector of the ray
        // d ist the direction-vector of the ray

        Vector3 v = a.sub(ray.origin);     // (c-o)
        Double b = v.dot(n);               //(c-o)*n
        Double c = ray.direction.dot(n);   // d*n
        Double t = b/c;

        Point3 p = ray.origin.add(ray.direction.mul(t));    //p = o + td

        //Here we put the Plane in the Sphere caluclation ((p-c)*(p-c) < r^2)
        if(Math.pow(a.sub(p).magnitude, 2) < Math.pow(radius, 2)) {
            if (t > 0) {
                return new Hit(t, ray, this, n);
            }
        }
        return null;
    }
}
