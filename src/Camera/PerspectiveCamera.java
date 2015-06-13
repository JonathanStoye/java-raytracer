package Camera;

import MatrixVector.Point3;
import MatrixVector.Vector3;
import Scene.Ray;

/**
 * Class is used to describe a perspective Camera used by the scene
 * Created by Jonathan on 05.06.15.
 * Implemented by David on 08.06.2015.
 * @author d.derichs
 */
public class PerspectiveCamera extends Camera{

    // Angle of the base vector of the perspective camera
    public final double angle;

    /**
     * Initiates the perspective camera using the given values
     * @param e point of view - vector of the camera
     * @param g viewers direction - vector of the camera
     * @param t up-vector of the camera
     * @param angle angle of the base vector of the perspective camera
     */
    public PerspectiveCamera(final Point3 e, final Vector3 g, final Vector3 t, double angle) {
        super(e, g, t);
        this.angle = angle;
    }

    /**
     * Returns a Ray, based on the camera's values and the given values of the method.
     * @param w Width value of the picture
     * @param h Height value of the picture
     * @param x x-coordinate value of a pixel, for which the ray is calculated
     * @param y y-coordinate value of a pixel, for which the ray is calculated
     * @return ray, based on the camera's values
     */
    public Ray rayFor(int w, int h, int x, int y) {
        // All rays of a perspective camera have the same origin.
        // Therefore these are all based on this formular:
        // o = e;
        final Point3 o = this.e;

        // The direction of the rays is based on the given angle of the camera
        // This is the formular used to calculate the vector
        //
        //               h
        //              ---
        //               2             w-1                w-1
        // r = -w * ( ------ ) + (x - ----- ) * u + (y - ----- ) * v
        //            tan a             2                  2
        //
        // In order to ensure better readability we declare three variables standing for the big brackets in the formular
        final double firstBracket = (double) h/2.0/Math.tan(this.angle/2.0);
        final double xBracket = (double)x-(((double)w-1.0)/2.0);
        final double yBracket = (double)y-(((double)h-1.0)/2.0);

        // Finally the calculation for r is made.
        final Vector3 r = this.w.mul(-1.0).mul(firstBracket).add(this.u.mul(xBracket).add(v.mul(yBracket)));

        // The Direction is based on the vector r
        // But it has zo be normalised
        final Vector3 d = r.normalized();

        final double space = ((double) h  / 2.0) / Math.tan(this.angle/2.0);

        // Nor returning the Ray
        return new Ray(o, d);
    }
}
