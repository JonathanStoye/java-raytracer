package Light;

import MatrixVector.Point3;
import MatrixVector.Vector3;
import Scene.*;

/**
 * Created by Jonathan on 09.06.15.
 */
public class SpotLight extends Light {

    public final Point3 position;
    public final Vector3 direction;
    public final double halfAngle;

    /**
     * Initiates the Light-Object using the given Color
     *  @param color Color of the Light
     * @param position
     * @param direction
     * @param halfAngle
     */
    public SpotLight(Color color, Point3 position, Vector3 direction, double halfAngle) {
        super(color);
        this.position = position;
        this.direction = direction;
        this.halfAngle = halfAngle;
    }

    public SpotLight(Color color, Point3 position, Vector3 direction, double halfAngle, boolean castShadows) {
        super(color, castShadows);
        this.position = position;
        this.direction = direction;
        this.halfAngle = halfAngle;
    }

    @Override
    public boolean illuminates(Point3 point) {

        final Vector3 diffVector = this.directionFrom(point).mul(-1.0);

        double angle = (diffVector.dot(this.direction)) / (diffVector.magnitude * this.direction.magnitude);
        angle = Math.acos(angle);

        if (angle <= this.halfAngle){
            return true;
        }
        else {
            return true;
        }
    }

    /**
     * Determines whether the given Point is illuminated by this light. (including calculation of shadows)
     * @param point Point, which is potentially illuminated by this light.
     * @param world World is needed to check if point is laying in a shadow
     * @return Returns a boolean value: true, if the Point is illuminated. false if the Point is not illuminated.
     */
    @Override
    public boolean illuminates(Point3 point, World world) {
        final Vector3 diffVector = this.directionFrom(point).mul(-1.0);
        double angle = (diffVector.dot(this.direction)) / (diffVector.magnitude * this.direction.magnitude);
        angle = Math.acos(angle);

        if (!this.castsShadows) {
            if (angle > this.halfAngle) {
                return false;
            } else return true;
        } else {
            Hit hit = world.hit(new Ray(point, diffVector));
            double t = diffVector.magnitude/1;
            if (angle > this.halfAngle || (hit != null && hit.t < t)){
                return false;
            } else return true;
        }
    }

    @Override
    public Vector3 directionFrom(Point3 point) {
        return this.position.sub(point);
    }
}
