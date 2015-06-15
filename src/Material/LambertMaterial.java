package Material;

import Scene.Hit;
import Scene.World;
import Scene.Color;

/**
 * Created by Jonathan on 09.06.15.
 *
 * LambertMaterial is the Material for a perfect diffuse reflecting material.
 */
public class LambertMaterial extends Material {

    public final Color color;

    public LambertMaterial(Color color) {
        this.color = color;
    }

    /**
     * calculates the color for given hit with
     *
     * (formula)
     *
     * @param hit Hit object which is analysed
     * @param world World object which is used to get information about the light in the current scene.
     * @return the calculated Colr
     */
    @Override
    public Color colorFor(Hit hit, World world) {
        Color returnColor;

        returnColor = this.color.mul(world.ambientLight);

        return returnColor;
    }
}
