package Geometry;

import MatrixVector.*;
import Scene.*;
import Utilities.Debugging;

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
        //      -b +- SQRT{ (b*b) - (4*a*c) }
        // t = -------------------------------
        //                  2*a
        //
        // There is a known rule which says that d = b*b - 4*a*c;
        // Therefore we can insert the directional-vector of the ray into the formular.
        final double d = (b*b) - (4*a*c);
        // But first we have to check if we can extract a root out of this expression
        // If d < = there are no interceptions.
        if (d<0) return null;

        // The formula now looks like this:
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
        // If t < 0 because in this case the sphere is not visible to the viewer.
        //
        // We check tNegative first, because it is obviously smaller than tPositive.
        if (tNegative > 0.0000001){
            return new Hit(tNegative, ray, this);
        } else if (tPositive > 0.0000001){
            return new Hit(tPositive, ray, this);
        }

        // But there is one possible result left, when there is only one intersection:
        if (d==0){
            final double tValue = -b / (2*a);
            if (tValue < 0.0000001){
                return null;
            }
            return new Hit(tValue, ray, this);
        }
        return null;
    }

    public static void testHit(){
        // Test-Vectors are all pointing to the Sphere in the direction of the x-axis
        // All vectors have a length of 100;
        // Pointing directly at the center of the Sphere with z=0:
        Ray testRayMiddle = new Ray(new Point3(0.0, 50.0, 0), new Vector3(100.0, 0.0, 0.0));

        // Pointing at a point between Center and Border:
        Ray testRayBetweenMiddleAndUpperBorder = new Ray(new Point3(0.0, 62.5, 0), new Vector3(100.0, 0.0, 0.0));

        // Pointing at the Upper Border of the Sphere with z=0:
        Ray testRayUpperBorder = new Ray(new Point3(0.0, 75.0, 0), new Vector3(100.0, 0.0, 0.0));

        // Pointing at the Lower Border of the Sphere with z=0:
        Ray testRayLowerBorder = new Ray(new Point3(0.0, 25.0, 0), new Vector3(100.0, 0.0, 0.0));

        // Pointing at the Lower Border of the Sphere with z=0:
        Ray testRayOutsideUpperBorder = new Ray(new Point3(0.0, 76.0, 0), new Vector3(100.0, 0.0, 0.0));

        // Pointing one Pixel below the Border of the Sphere with z=0: (not visible)
        Ray testRayOutsideLowerBorder = new Ray(new Point3(0.0, 24.0, 0), new Vector3(100.0, 0.0, 0.0));

        // Pointing at the Border of the sphere but on the positive z-axis: (visible)
        Ray TestRay3DimensionalPositiveZAtSphereBorder = new Ray(new Point3(0.0, 50.0, 25.0), new Vector3(100.0, 0.0, 0.0));

        // Pointing at a Point between the Center and Border but with z=12.5: (visible with 0.25<t>0.5)
        Ray TestRay3DimensionalPositiveZBetweenMiddleAndBorder = new Ray(new Point3(0.0, 50.0, 12.5), new Vector3(100.0, 0.0, 0.0));

        // Pointing at the Border of the sphere but on the negative z-axis: (visible)
        Ray TestRay3DimensionalNegativeZAtSphereBorder  = new Ray(new Point3(0.0, 50.0, -25.0), new Vector3(100.0, 0.0, 0.0));

        // Pointing at the Border of the sphrere but on the negative z-axis
        Ray TestRay3DimensionalPositiveZOutsideSphereBorder = new Ray(new Point3(0.0, 50.0, 26.0), new Vector3(100.0, 0.0, 0.0));
        Ray TestRay3DimensionalNegativeZOutsideSphereBorder  = new Ray(new Point3(0.0, 50.0, -26.0), new Vector3(100.0, 0.0, 0.0));
        Sphere testSphere = new Sphere(new Point3(50.0,50.0,0.0), 25.0, new Color(1,0,0));
        if (testSphere.hit(testRayMiddle) != null) {
            Debugging.log("Successful: Vector testRayMiddle is hitting Sphere at t=" + testSphere.hit(testRayMiddle).t);
        }else {
            Debugging.log("Unsuccessful: Vector tet RayMiddle is not hitting the Sphere.");
        }
        if (testSphere.hit(testRayBetweenMiddleAndUpperBorder) != null){
            Debugging.log("Successful: Vector testRayBetweenMiddleAndUpperBorder is hitting Sphere at t=" + testSphere.hit(testRayBetweenMiddleAndUpperBorder).t );
        }else {
            Debugging.log("Unsuccessful: Vector testRayBetweenMiddleAndUpperBorder ist not hitting Sphere.");
        }

        if(testSphere.hit(testRayUpperBorder) != null){
            Debugging.log("Successful: Vector testRayUpperBorder is hitting Sphere at t=" + testSphere.hit(testRayUpperBorder).t);
        }else{
            Debugging.log("Unsuccessful: Vector testRayUpperBorder is not hitting Sphere.");
        }
        if(testSphere.hit(testRayLowerBorder) != null){
            Debugging.log("Successful: Vector testRayLowerBorder is hitting Sphere at t=" + testSphere.hit(testRayLowerBorder).t);
        }else{
            Debugging.log("Unsuccessful: Vector testRayLowerBorder is not hitting Sphere.");
        }
        if(testSphere.hit(testRayOutsideUpperBorder) == null){
            Debugging.log("Successful: Vector testRayOutsideUpperBorder is not hitting the Sphere.");
        } else{
            Debugging.log("Unsuccessful: Vector testRayOutsideUpperBorder is hitting the Sphere at t= " + testSphere.hit(testRayOutsideUpperBorder).t);
        }
        if(testSphere.hit(testRayOutsideLowerBorder) == null){
            Debugging.log("Successful: Vector testRayOutsideLowerBorder is not hitting the sphere.");
        } else{
            Debugging.log("Unsuccessful: Vector testRayOutsideLowerBorder is hitting the sphere at t=" + testSphere.hit(testRayOutsideLowerBorder).t);
        }
        if (testSphere.hit(TestRay3DimensionalPositiveZAtSphereBorder) != null){
            Debugging.log("Successful: Vector TestRay3DimensionalPositiveZAtSphereBorder is hitting the Sphere at t= " + testSphere.hit(TestRay3DimensionalPositiveZAtSphereBorder).t );
        }else{
            Debugging.log("Unsuccessful: Vector TestRay3DimensionalPositiveZAtSphereBorder is not hitting the Sphere.");
        }
        if(testSphere.hit(TestRay3DimensionalPositiveZBetweenMiddleAndBorder) != null){
            Debugging.log("Successful: Vector TestRay3DimensionalPositiveZBetweenMiddleAndBorder is hitting the Sphere at t= " + testSphere.hit(TestRay3DimensionalPositiveZBetweenMiddleAndBorder).t);
        }else {
            Debugging.log("Unsuccessful: Vector TestRay3DimensionalPositiveZBetweenMiddleAndBorder is not hitting the Sphere.");
        }
        if(testSphere.hit(TestRay3DimensionalNegativeZAtSphereBorder) != null){
            Debugging.log("Successful: Vector TestRay3DimensionalNegativeZAtSphereBorder is hitting the Sphere at t= " + testSphere.hit(TestRay3DimensionalNegativeZAtSphereBorder).t);
        } else{
            Debugging.log("Unsuccessful: Vector TestRay3DimensionalNegativeZAtSphereBorder is not hitting the Sphere.");
        }
        if(testSphere.hit(TestRay3DimensionalPositiveZOutsideSphereBorder) == null){
            Debugging.log("Successful: Vector TestRay3DimensionalPositiveZOutsideSphereBorder is not hitting the Sphere");
        } else {
            Debugging.log("Unsuccessful: Vector TestRay3DimensionalPositiveZOutsideSphereBorder is hitting the Sphere at t=" + testSphere.hit(TestRay3DimensionalPositiveZOutsideSphereBorder).t);
        }
        if(testSphere.hit(TestRay3DimensionalNegativeZOutsideSphereBorder) == null){
            Debugging.log("Successful: Vector TestRay3DimensionalNegativeZOutsideSphereBorder is not hitting the Sphere");
        }else {
            Debugging.log("Unsuccessful: Vector TestRay3DimensionalNegativeZOutsideSphereBorder is hitting the Sphere at t=" + testSphere.hit(TestRay3DimensionalNegativeZOutsideSphereBorder).t);
        }
    }
}
