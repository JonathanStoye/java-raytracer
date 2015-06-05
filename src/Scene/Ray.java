package Scene;

import MatrixVector.Point3;
import MatrixVector.Vector3;

/**
 * Created by Jonathan on 05.06.15.
 */
public class Ray {
    private static Point3 origin;
    private static Vector3 direction;

    public void Ray(Point3 origin, Vector3 direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Point3 at(double t) {
        return null;
    }

    public double tOf(Point3 p) {
        return 0.0;
    }
}
