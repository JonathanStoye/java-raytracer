package Scene;

import Geometry.*;

/**
 * Created by Jonathan on 05.06.15.
 */
public class World {
    private final Geometry[] objects;
    private final Color backgroundColor;

    public World(Geometry[] objects, Color backgroundColor) {
        this.objects = objects;
        this.backgroundColor = backgroundColor;
    }

    /**
     * checks each object in this.objects if ray hits it
     * @param ray ray to compare if this.object got hit by it
     * @return the smallest hit value
     */
    public double hit(Ray ray) {
        Double t = null;
        for (Geometry geo : objects){
            Hit currentHit = geo.hit(ray);

            if ( currentHit != null && t != null) {
                if (currentHit.t < t) {
                    t = currentHit.t;
                }
            }
            else {
                t = currentHit.t;
            }
        }
        return t;
    }

    public static void test() {

    }
}
