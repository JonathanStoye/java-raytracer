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

    @Override
    public boolean illuminates(Point3 point) {
        return true;
    }

    @Override
    public Vector3 directionFrom(Point3 point) {
        return null;
    }
}
