package Camera;

import MatrixVector.Point3;
import MatrixVector.Vector3;
import Scene.Ray;

/**
 * Created by Jonathan on 05.06.15.
 */


public class OrthographicCamera {

    Point3 e = this.e;
    Vector3 g = this.g;
    Vector3 t = this.t;
    double s = this.s;


    /**
     * Constructor
     */
    public OrthographicCamera(Point3 e, Vector3 g, Vector3 t, double s) {

    }

    public Ray rayFor(int w, int h, int x, int y) {

    }

}
