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
}
