package MatrixVector;

/**
 * Created by Jonathan on 01.05.15.
 * Point3 represents a point in a 3 dimensional coordinate system
 *
 */
public final class Point3 {

    public final double x;
    public final double y;
    public final double z;

    public Point3 (double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3 sub(Point3 p) {
        Double x = this.x - p.x;
        Double y = this.y - p.y;
        Double z = this.z - p.z;
        return new Vector3(x, y, z);
    }

    public Point3 sub(Vector3 v) {
        Double x = this.x - v.x;
        Double y = this.y - v.y;
        Double z = this.z - v.z;
        return new Point3(x, y, z);
    }

    public Point3 add(Vector3 v) {
        Double x = this.x - v.x;
        Double y = this.y - v.y;
        Double z = this.z - v.z;
        return new Point3(x, y, z);
    }
}
