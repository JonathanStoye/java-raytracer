package Geometry;

import Scene.*;
import Material.*;

/**
 * Created by Jonathan on 05.06.15.
 * Implemented by David on 06.06.15.
 * Abstract Class, used to describe geometric forms in a scene.
 */
public abstract class Geometry {
    /**
     * Color of the form
     */
    public final Material material;

    /**
     * Initializes the geometric form with the given parameter.
     * @param material Specifies the color of a certain geometric form
     */
    Geometry (Material material){
        this.material = material;
    }

    /**
     * Determines wether the given ray is hitting the current object.
     * @param ray if the form is hit, the method returns a Hit object giving information of the t-value, ray and hit Geometry-object.
     */
    public abstract Hit hit(final Ray ray);


    /**
     * Hashcode, Equals und toString are overwritten:
     */

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((material == null) ? 0 : material.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Geometry other = (Geometry) obj;
        if (material == null) {
            if (other.material != null)
                return false;
        } else if (!material.equals(other.material))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Geometry [mat = " + material + "]";
    }
}
