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
    public Hit hit(Ray ray) {
        Hit hit = null;
        for (Geometry geo : objects){
            Hit currentHit = geo.hit(ray);

            if ( currentHit != null && hit != null) {
                if (currentHit.t < hit.t) {
                    hit = currentHit;
                }
            }
            else {
                hit = currentHit;
            }
        }
        return hit;
    }

    public static void test() {

    }
}
