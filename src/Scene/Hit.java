package Scene;

import Geometry.Geometry;

/**
 * Created by Jonathan on 05.06.15.
 */
public class Hit {
    double t;
    Ray ray;
    Geometry geo;

    public Hit(double t, Ray ray, Geometry geo) {
        this.t = t;
        this.ray = ray;
        this.geo = geo;
    }
}
