package Material;

import Scene.*;


/**
 * Class LambertMaterial describes a perfect diffus reflective object.
 * Created by David on 15.06.15
 * @author David Derichs
 */
public class LambertMaterial extends Material{

    public final Color color;

    /**
     * Initiates the LambertMaterial using the given basic Color.
     * @param color
     */
    LambertMaterial(Color color){
        this.color=color;
    }

    /**
     * Determines which color a certain hit interception point has concerning the light sources in the room.
     * @param hit Hit object which is analysed
     * @param world World object which is used to get information about the light in the current scene.
     * @return Color of the interception point.
     */
    @Override
    public Color colorFor(Hit hit, World world) {

        Color c;
        // this is the interception point.
        hit.ray.at(hit.t);
        return null;
    }
}
