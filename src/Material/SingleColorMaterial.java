package Material;

import Scene.*;

/**
 * Created by Jonathan on 09.06.15.
 */
public class SingleColorMaterial extends Material {

    public final Color color;

    public SingleColorMaterial(Color color) {
        this.color = color;
    }

    /**
     * simply return this.color for every hit
     * @param hit Hit object which is analysed
     * @param world World object which is used to get information about the light in the current scene.
     * @return this.color
     */
    @Override
    public Scene.Color colorFor(Hit hit, World world) {
        return this.color;
    }
}
