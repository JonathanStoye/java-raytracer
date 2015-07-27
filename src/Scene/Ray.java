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

    @Override
    public String toString() {
        return "Ray{" +
                "origin=" + origin +
                ", direction=" + direction +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ray ray = (Ray) o;

        if (origin != null ? !origin.equals(ray.origin) : ray.origin != null) return false;
        return !(direction != null ? !direction.equals(ray.direction) : ray.direction != null);

    }

    @Override
    public int hashCode() {
        int result = origin != null ? origin.hashCode() : 0;
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;
    }
}
