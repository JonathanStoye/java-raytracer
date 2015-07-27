package Light;

import MatrixVector.Point3;
import MatrixVector.Vector3;

import Scene.*;

/**
 * Created by Jonathan on 09.06.15.
 */
public class DirectionalLight extends Light {

    public final Vector3 direction;

    /**
     * Initiates the Light-Object using the given Color
     *
     * @param color Color of the Light
     * @param direction
     */
    public DirectionalLight(Color color, Vector3 direction) {
        super(color);
        this.direction = direction;
    }

    public DirectionalLight(Color color, Vector3 direction, boolean castShadows){
        super(color, castShadows);
        this.direction = direction;
    }

    /**
     * Determines whether the given Point is illuminated by this light.
     * @param point Point, which is potentially illuminated by this light.
     * @return Returns a boolean value: true, if the Point is illuminated. false if the Point is not illuminated.
     */
    @Override
    public boolean illuminates(Point3 point) {
        return true;
    }

    /**
     * Determines whether the given Point is illuminated by this light. (including calculation of shadows)
     * @param point Point, which is potentially illuminated by this light.
     * @param world World is needed to check if point is laying in a shadow
     * @return Returns a boolean value: true, if the Point is illuminated. false if the Point is not illuminated.
     */
    @Override
    public boolean illuminates(Point3 point, World world) {
        if (!this.castsShadows) {
            return true;
        }

        Ray ray = new Ray(point, directionFrom(point));
        Hit hit = world.hit(ray);
        if(hit == null){
            return true;
        }
        else {
            return false;
        }
    }

    // The DirectionFROM an illuminated Point is always the same.
    // It is Pointing against the lignt's direction.
    @Override
    public Vector3 directionFrom(Point3 point) {
        return direction.mul(-1.0).normalized();
    }

    @Override
    public String toString() {
        return "DirectionalLight{" +
                "direction=" + direction +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DirectionalLight that = (DirectionalLight) o;

        return !(direction != null ? !direction.equals(that.direction) : that.direction != null);

    }

    @Override
    public int hashCode() {
        return direction != null ? direction.hashCode() : 0;
    }
}
