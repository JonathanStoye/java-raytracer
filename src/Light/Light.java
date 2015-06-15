package Light;

import MatrixVector.Point3;
import MatrixVector.Vector3;
import Scene.Color;

/**
 * Created by David on 14.06.15.
 * Class Light is used to describe the basic parameters that light has in this Raytracer
 * @author d.derichs
 */
public abstract class Light {

    // Color of the Light
    public final Color color;

    /**
     * Initiates the Light-Object using the given Color
     * @param color Color of the Light
     */
    public Light(Color color){
        this.color = color;
    }

    /**
     * Determines wheter the given Point is illuminated by this light.
     * @param point Point, which is potentially illuminated by this light.
     * @return Returns a boolean value: true, if the Point is illuminated. false if the Point is not illuminated.
     */
    public abstract boolean illuminates(Point3 point);

    /**
     * Returns a Vector, representing the Direction from the given Point to the source of light.
     * @param point Point, which is the Origin of the Vector that is pointing to the source of light.
     * @return A Vector pointing to the source of light.
     */
    public abstract Vector3 directionFrom(Point3 point);

}
