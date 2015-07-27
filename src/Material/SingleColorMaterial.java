package Material;

import Scene.*;

import java.awt.*;
import java.awt.Color;

/**
 * Class SingleColorMaterial is used to describe the simplest Material, which only has a Color.
 * Created by David on 15.06.15.
 */
public class SingleColorMaterial extends Material{
    final public Scene.Color color;

    /**
     * Initiates the Material using the given Color.
     * @param color
     */
    public SingleColorMaterial(Scene.Color color){
        this.color=color;
    }

    /**
     * Just returns the Color of the Material
     * @param hit Hit object which is analysed
     * @param world World objekct which is used to get information about the light in the current scene.
     * @return Color of the Material
     */
    @Override
    public Scene.Color colorFor(Hit hit, World world, RecursiveTracer tracer) {
        return this.color;
    }

    @Override
    public String toString() {
        return "SingleColorMaterial{" +
                "color=" + color +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingleColorMaterial that = (SingleColorMaterial) o;

        return !(color != null ? !color.equals(that.color) : that.color != null);

    }

    @Override
    public int hashCode() {
        return color != null ? color.hashCode() : 0;
    }
}
