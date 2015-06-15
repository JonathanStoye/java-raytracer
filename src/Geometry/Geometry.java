package Geometry;

import Material.Material;
import Scene.*;

/**
 * Created by Jonathan on 05.06.15.
 * Implemented by David on 06.06.15.
 * Abstract Class, used to describe geometric forms in a scene.
 */
public abstract class Geometry {
    /**
     * Material of the form
     */
    public final Material material;

    /**
     * Initializes the geometric form with the given parameter.
     * @param material Specifies the material of a certain geometric form
     */
    Geometry (Material material){
        this.material = material;
    }

    /**
     * Determines wether the given ray is hitting the current object.
     * @param ray if the form is hit, the method returns a Hit object giving information of the t-value, ray and hit Geometry-object.
     */
    public abstract Hit hit(final Ray ray);
}
