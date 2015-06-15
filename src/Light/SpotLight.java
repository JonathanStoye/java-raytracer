package Light;

import MatrixVector.Point3;
import MatrixVector.Vector3;

import java.awt.*;

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

    @Override
    public boolean illuminates(Point3 point) {
        final Vector3 diffVector = this.directionFrom(point);
        double angle = diffVector.dot(this.direction) / diffVector.magnitude * this.direction.magnitude;

        if (angle <= this.halfAngle)
            return true;
        else
            return false;
    }

    @Override
    public Vector3 directionFrom(Point3 point) {
        return this.position.sub(point);
    }
}
