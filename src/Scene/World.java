package Scene;

import Geometry.*;
import Light.Light;
import java.util.List;


/**
 * Created by Jonathan on 05.06.15.
 */
public class World {
    public final Geometry[] objects;
    public final Color backgroundColor;
    public final Color ambientLight;
    public final List<Light> lights;
    public int refractionIndex;     //it won't allow me to set it to "final"

    public World(Geometry[] objects, Color backgroundColor, Color ambientLight, List<Light> lights, int refractionIndex) {
        this.objects = objects;
        this.backgroundColor = backgroundColor;
        this.ambientLight = ambientLight;
        this.lights = lights;
        this.refractionIndex = refractionIndex;
    }

    public World(Geometry[] objects, Color backgroundColor, Color ambientLight, List<Light> lights) {
        this.objects = objects;
        this.backgroundColor = backgroundColor;
        this.ambientLight = ambientLight;
        this.lights = lights;
    }

    public World(Geometry[] objects, Color backgroundColor) {
        this.objects = objects;
        this.backgroundColor = backgroundColor;
        this.ambientLight = null;
        this.lights = null;
    }

    /**
     * checks each object in this.objects if ray hits it
     * @param ray ray to compare if this.object got hit by it
     * @return the smallest hit value
     */
    public Hit hit(Ray ray) {
        Hit hit = null;
        for (Geometry geo : objects) {
            Hit currentHit = geo.hit(ray);
            if (currentHit != null) {
                if (hit == null) {
                    hit = currentHit;
                }
                else if (hit != null) {
                    if (currentHit.t < hit.t) {
                        hit = currentHit;
                    }
                }
            }
        }
        return hit;
    }

    public static void test() {

    }
}
