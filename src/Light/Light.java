package Light;

import MatrixVector.Point3;
import MatrixVector.Vector3;
import Scene.*;

/**
 * Created by David on 14.06.15.
 * Class Light is used to describe the basic parameters that light has in this Raytracer
 * @author d.derichs
 */
public abstract class Light {

    /**
     * Color of the Light
     */
    public final Scene.Color color;
    /**
     * tells us whether or not ths light casts shadows
     */
    public final boolean castsShadows;

    /**
     * Initiates the Light-Object using the given Color
     * @param color Color of the Light
     */
    public Light(Color color, boolean castsShadows){
        this.color = color;
        this.castsShadows = castsShadows;
    }

    /**
     * Initiates the Light-Object using the given Color
     * @param color Color of the Light
     */
    public Light(Color color){
        this.color = color;
        this.castsShadows = false;
    }

    /**
     * Determines whether the given Point is illuminated by this light.
     * @param point Point, which is potentially illuminated by this light.
     * @return Returns a boolean value: true, if the Point is illuminated. false if the Point is not illuminated.
     */
    public abstract boolean illuminates(Point3 point);

    /**
     * Determines whether the given Point is illuminated by this light. (including calculation of shadows)
     * @param point Point, which is potentially illuminated by this light.
     * @param world World is needed to check if point is laying in a shadow
     * @return Returns a boolean value: true, if the Point is illuminated. false if the Point is not illuminated.
     */
    public abstract boolean illuminates(Point3 point, World world);

    /**
     * Returns a Vector, representing the Direction from the given Point to the source of light.
     * @param point Point, which is the Origin of the Vector that is pointing to the source of light.
     * @return A Vector pointing to the source of light.
     */
    public abstract Vector3 directionFrom(Point3 point);

    @Override
    public String toString() {
        return "Light{" +
                "color=" + color +
                ", castsShadows=" + castsShadows +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Light light = (Light) o;

        if (castsShadows != light.castsShadows) return false;
        return !(color != null ? !color.equals(light.color) : light.color != null);

    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + (castsShadows ? 1 : 0);
        return result;
    }
}
