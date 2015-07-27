package Material;

import Scene.*;

import java.awt.*;

/**
 * Abstract class Material is used to describe the basic method for material objects.
 * Created by David on 14.06.15.
 * @author David Derichs
 */
public abstract class Material {

    /**
     * Returns the color for a specific hit object.
     * It is also using a world object in order to determine which light is used in the scene.
     * @param hit Hit object which is analysed
     * @param world World objekct which is used to get information about the light in the current scene.
     * @return Color object which is describing the color of the analysed hit.
     */
    public abstract Scene.Color colorFor(Hit hit, World world, RecursiveTracer recursiveTracer);

    @Override
    public String toString() {
        return "Material{}";
    }
}
