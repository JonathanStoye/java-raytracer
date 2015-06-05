package Camera;

import MatrixVector.Point3;
import MatrixVector.Vector3;
import Scene.Ray;

/**
 * Created by Jonathan on 05.06.15.
 */
public class PerspectiveCamera {

        /**
         * Constructor
         */
        Point3 e = this.e;
        Vector3 g = this.g;
        Vector3 t = this.t;
        double angle = this.angle;

    public PerspectiveCamera(Point3 e, Vector3 g, Vector3 t, double angle) {

        }

        public Ray rayFor(int w, int h, int x, int y) {

        }
}
