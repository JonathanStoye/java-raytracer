package MatrixVector;

/**
 * Created by Jonathan on 01.05.15.
 * Point3 represents a point in a 3 dimensional coordinate system
 * it provides methods to subtract a point or a vector or add a vector
 */
public final class Point3 {

    /**
     * x,y,z coordinates of this
     */
    public final double x;
    public final double y;
    public final double z;

    public Point3 (double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * @param p Point to subtract from this
     * @return returns the values of this subtracted by Point3 p as a new Vector3
     */
    public Vector3 sub(Point3 p) {
        Double x = this.x - p.x;
        Double y = this.y - p.y;
        Double z = this.z - p.z;
        return new Vector3(x, y, z);
    }

    /**
     * @param v vector to subtract from this
     * @return returns the values of this subtracted by Vector3 v as a new Point3
     */
    public Point3 sub(Vector3 v) {
        Double x = this.x - v.x;
        Double y = this.y - v.y;
        Double z = this.z - v.z;
        return new Point3(x, y, z);
    }

    /**
     * @param v vector to add to this
     * @return returns the values of this plus Vector3 v as a new Point3
     */
    public Point3 add(Vector3 v) {
        Double x = this.x + v.x;
        Double y = this.y + v.y;
        Double z = this.z + v.z;
        return new Point3(x, y, z);
    }

    /**
     * test the functionality of the class
     * @param args
     */
    public static void main(String[] args) {
        final Point3 p = new Point3(2.0, 3.0, 1.0);
        final Vector3 v = new Vector3(1.0, 1.0, 1.0);
        final Point3 p2 = new Point3(1.0, 1.0, 1.0);
        final Point3 addGoalP = new Point3(3.0, 4.0, 2.0);
        final Point3 subGoalP = new Point3(1.0, 2.0, 0.0);
        final Vector3 goalV = new Vector3(1.0, 2.0, 0.0);

        /**
         * tests adding vectors
         */
        final Point3 sum1 = p.add(v);
        if (sum1.equals(addGoalP)) System.out.println("Vector3 add() successful");
        else System.out.println("Vector3 add() not successful");

        /**
         * tests subtracting vectors
         */
        final Point3 sum2 = p.sub(v);
        if (sum2.equals(subGoalP)) System.out.println("Vector3 sub() successful");
        else System.out.println("Vector3 sub() not successful");

        /**
         * tests subtracting points
         */
        final Vector3 sum3 = p.sub(p2);
        if (sum3.equals(goalV)) System.out.println("Point3 sub() successful");
        else System.out.println("Point3 sub() not successful");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point3 point3 = (Point3) o;

        if (Double.compare(point3.x, x) != 0) return false;
        if (Double.compare(point3.y, y) != 0) return false;
        return Double.compare(point3.z, z) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
