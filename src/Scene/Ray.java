package Scene;

import MatrixVector.Point3;
import MatrixVector.Vector3;

/**
 * Created by Jonathan on 05.06.15.
 */
public class Ray {
    public final Point3 origin;
    public final Vector3 direction;

    public Ray(Point3 origin, Vector3 direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Point3 at(double t) {
        Point3 p = this.origin.add(this.direction.mul(t));
        return p;
    }

    public double tOf(Point3 p) {
        return 0.0;
    }
}
