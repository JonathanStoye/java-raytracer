package Camera;

import MatrixVector.Point3;
import MatrixVector.Vector3;
import Scene.Ray;

/**
 * Class Camera is used to to describe the basic parameters of a Camera in the szene
 * Created by Jonathan on 05.06.15.
 * Implemented by David on 08.06.15.
 * @author d.derichs
 */
public abstract class Camera {

    public final Point3 e;
    public final Vector3 g;
    public final Vector3 t;
    public final Vector3 u;
    public final Vector3 v;
    public final Vector3 w;

    /**
     * Initiates the Camera using the three vector parameters.
     * Also calculates a new coordinate system based on this information.
     * @param e point of view - vector of the camera
     * @param g viewers direction - vector of the camera
     * @param t up-vector of the camera
     */
    public Camera(Point3 e, Vector3 g, Vector3 t) {
        this.e = e;
        this.g = g;
        this.t = t;

        // Now Calculating vector w
        // The formular for this vector looks like this
        //          g
        //  w = - -----
        //         |g|
        this.w = g.mul(1.0/g.magnitude).mul(-1.0);

        // Now calculationg vector u
        // The formuar for this vector looks like this
        //      t x w
        // u = ---------
        //      |t x w|
        this.u = t.x(w).mul(1.0/t.x(w).magnitude);

        // Now calculating vector v
        // The formular for this vector looks like this
        //
        // v = w x u
        //
        this.v = w.x(u);
    }

    /**
     * rayFor returns a ray, which is calculated using the given parameters
     * Based on how a camera works. All ray's vector's origins are based on the camera's values.
     * @param w Width value of the picture
     * @param h Height value of the picture
     * @param x x-coordinate value of a pixel, for which the ray is calculated
     * @param y y-coordinate value of a pixel, for which the ray is calculated
     * @return Calculated Ray based on a certain pixel
     */
    public abstract Ray rayFor(int w, int h, int x, int y);
}
