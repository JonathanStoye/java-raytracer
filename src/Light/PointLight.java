package Light;

import MatrixVector.Point3;
import MatrixVector.Vector3;

import java.awt.*;

/**
 * Class PointLight represents an instance of a PointLight in the Scene
 * Created by David on 14.06.15.
 * @author David Derichs
 */
public class PointLight extends Light{

    final public Point3 position;

    /**
     * Initialises the PointLight using the given values
     * @param color Color of the light
     * @param position Position of the PointLight. Origin of the PointLight.
     */
    public PointLight(Color color, Point3 position){
        super(color);
        this.position = position;
    }

    /**
     * Determines wheter the given Point is illuminated by this light.
     * @param point Point, which is potentially illuminated by this light.
     * @return Returns a boolean value: true, if the Point is illuminated. false if the Point is not illuminated.
     */
    @Override
    public boolean illuminates(Point3 point) {
        return true;
    }

    /**
     * Returns a Vector, representing the Direction from the given Point to the source of light.
     * @param point Point, which is the Origin of the Vector that is pointing to the source of light.
     * @return A Vector pointing to the source of light.
     */
    @Override
    public Vector3 directionFrom(Point3 point) {
        return this.position.sub(point);
    }
}
