package Scene;

import Geometry.Geometry;
import MatrixVector.Normal3;

/**
 * Created by Jonathan on 05.06.15.
 */
public class Hit {
    public final double t;
    public final Ray ray;
    public final Geometry geo;
    public final Normal3 n;

    public Hit(final double t, final Ray ray, final Geometry geo, final Normal3 n) {
        this.t = t;
        this.ray = ray;
        this.geo = geo;
        this.n = n;
    }

    @Override
    public String toString() {
        return "Hit{" +
                "t=" + t +
                ", ray=" + ray +
                ", geo=" + geo +
                ", n=" + n +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hit hit = (Hit) o;

        if (Double.compare(hit.t, t) != 0) return false;
        if (ray != null ? !ray.equals(hit.ray) : hit.ray != null) return false;
        if (geo != null ? !geo.equals(hit.geo) : hit.geo != null) return false;
        return !(n != null ? !n.equals(hit.n) : hit.n != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(t);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (ray != null ? ray.hashCode() : 0);
        result = 31 * result + (geo != null ? geo.hashCode() : 0);
        result = 31 * result + (n != null ? n.hashCode() : 0);
        return result;
    }
}
