package Camera;

import MatrixVector.*;
import Scene.Ray;

import java.util.Vector;

/**
 * Created by Jonathan on 05.06.15.
 * Implemented by David on 08.06.2015
 * @author d.derichs
 */


public class OrthographicCamera extends Camera{

    //scale factor value of the scene
    public final double s;

    /**
     * Initiates an orthographic camera using the three vector parameters and a scale factor value
     * Also calculates a new coordinate system based on this information.
     * @param e point of view - vector of the camera
     * @param g viewers direction - vector of the camera
     * @param t up-vector of the camera
     * @param s Scale factor value of the scene
     */
    public OrthographicCamera(final Point3 e, final Vector3 g, final Vector3 t, double s) {
        super(e, g, t);
        this.s = s;
    }

    /**
     * Returns a Ray, based on the camera's values and the given values of the method.
     * @param w Width value of the picture
     * @param h Height value of the picture
     * @param x x-coordinate value of a pixel, for which the ray is calculated
     * @param y y-coordinate value of a pixel, for which the ray is calculated
     * @return ray, based on the camera's values
     */
    @Override
    public Ray rayFor(int w, int h, int x, int y) {
        // In order to calculate an orthographic ray, we need this formula:
        //
        //                        (w-1)                          (h-1)
        //                     x- -----                    y-    -----
        //                          2                              2
        // o = e + a * s * ( -------------- ) * u + s * ( --------------- ) * v
        //                      (w-1)                        (h-1)

        // a is the aspectratio given by width and height
        final double a = (double) w / (double) h;

        // In order to ensure better readability we declare two variables standing for the two big brackets in the formular
        final double xBracket = ( ( x - ( (double)w-1.0) / 2.0 ) / ( (double) w - 1.0 ) );
        final double yBracket = ( ( y - ( (double)h-1.0) / 2.0 ) / ( (double) h - 1.0 ) );

        final double xScalar = a*s*xBracket;
        final double yScalar = s*yBracket;

        // Now the orthographic vector is calculated.
        final Point3 orthographicPosition = (this.e.add(this.u.mul(xScalar).add(this.v.mul(yScalar))));

        // The direction of all rays of the orthographic camera are based on this formular
        // d = - w;
        Vector3 direction = this.w.mul(-1.0);

        return new Ray (orthographicPosition, direction);
    }

}
