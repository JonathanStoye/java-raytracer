package Geometry;

import MatrixVector.*;
import Scene.*;

/**
 * Class sphere is used to describe a sphere in the 3-dimensional coordinate System
 * ger: Sphere ist eine Kugel
 * Created by Jonathan on 05.06.15.
 * Implemented by David on 06.06.15
 * @author d.derichs
 */
public class Sphere extends Geometry{

    public Point3 c;
    public double r;

    /**
     * Initializes a sphere with the given Values
     * @param c central Point of the sphere
     * @param r radius of the sphere
     * @param color Color of the Geometric object sphere
     */
    public Sphere( Point3 c, double r, Color color){
        super(color);
        this.c=c;
        this.r=r;
    }

    /**
     * Uses the given ray-parameter in order to find interceptions with the sphere.
     * If there are interceptions, it returns the one that is closest to the rays origin.
     * @param ray if the form is hit, the method returns a Hit object giving information of the t-value, ray and hit Geometry-object.
     * @return returns the Hit-object which is describing the t-value, ray-object and hit Geometric form sphere
     */
    @Override
    public Hit hit(Ray ray) {

        // We use these rules in order to calculate the necessary values
        double a = ray.direction.dot(ray.direction);

        // Later on we have to divide through a
        // So we have to check a's value first
        if (a==0)return null;

        // The formular for b looks like this:
        double b = ray.direction.dot(ray.origin.sub(this.c).mul(2.0));

        // The formular for c looks like this:
        double c = ((ray.origin.sub(this.c)).dot(ray.origin.sub(this.c))) - (this.r * this.r) ;

        // We need a, b and c to get the value of t, which is necessary in order to define a Hit-object.
        // The formular for t looks like this:
        //
        //      -b +- SQRT{ (b*b) - -4*a*c }
        // t = ---------------------------
        //                  2*a
        //
        // There is a known rule which says that d = b*b - 4*a*c;
        // Therefore we can insert the directional-vector of the ray into the formular.
        final double d = (b*b) - (4*a*c);
        // But first we have to check if we can extract a root out of this expression
        // If d < = there are no interceptions.
        if (d<0) return null;

        // The forumlar now looks like this:
        //
        //      -b +- SQRT{d}
        // t = --------------
        //          2*a
        //
        // The formular for t can have two results
        // Therefore we declare two variables fot that
        final double tNegative = ( (b*-1.0) - Math.sqrt(d) ) / (2.0*a);
        final double tPositive = ( (b*-1.0) + Math.sqrt(d) ) / (2.0*a);

        // Now there are two last rules to check
        // if d = 0 there is one intersection
        // if d > 0 there are thwo intersections
        //
        // We don't have to check if the t < 0 because in this case the sphere is not visible to the viewer.
        //
        // We check tNegative first, because it is obviously smaller than tPositive.
        if (tNegative > 0.0000001){
            return new Hit(tNegative, ray, this);
        } else if (tPositive > 0.0000001){
            return new Hit(tPositive, ray, this);
        }

        // But there is one possible result left, when there is only one intersection:
        if (d==0 && (tPositive>0.0000001)){
            return new Hit(tPositive, ray, this);
        }

        return null;

    }
}
