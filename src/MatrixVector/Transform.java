package MatrixVector;
import Scene.Ray;

/**
 * Created by David Derichs on 13.07.2015.
 * @author d.derichs
 * Class is Tramsform is used to tramsform geometric objects in the scene
 */
public class Transform {

    /**
     * Transformation Matrix
     */
    public final Mat4x4 m;

    /**
     * Inverse Matrix of the transformation.
     */
    public final Mat4x4 i;

    /**
     * Public constructor which is used to initialize with standard-values.
     * They can be used for further transformations.
     */
    public Transform(){
        this.i = new Mat4x4(1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0);
        this.m = new Mat4x4(1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0);
    }

    /**
     * Private constructor, in order to reinitialize the Tramsform object.
     * @param m Tramsformation matrix of the transformation
     * @param i Inverse Matrix of the transformation
     */
    private Transform(Mat4x4 m, Mat4x4 i) {
        this.m = m;
        this.i = i;
    }

    /**
     * Translates the current object to the location at the given Point
     * @param point The coordinates of this points are used to translate the object.
     * @return returns the current object translated to the new location
     */
    private Transform translation(final Point3 point){
        Mat4x4 transformationMatrix = new Mat4x4(1.0, 0.0, 0.0, point.x, 0.0, 1.0, 0.0, point.y, 0.0, 0.0, 1.0, point.z, 0.0, 0.0, 0.0, 1.0);
        Mat4x4 transformationInverse = new Mat4x4(1.0, 0.0, 0.0, -point.x, 0.0, 1.0, 0.0, -point.y, 0.0, 0.0, 1.0, -point.z, 0.0, 0.0, 0.0, 1.0);
        return new Transform(m.mul(transformationMatrix), i.mul(transformationInverse));
    }

    /**
     * Scales the vurrent object and gives back a new tramsform-object based on the three given factors
     * @param x x-Factor of the scale
     * @param y y-factor of the scale
     * @param z z-factor of the scale
     * @return returns a rescaled object
     */
    public Transform rescale(final double x, final double y, final double z) {
        Mat4x4 tm = new Mat4x4(x, 0.0, 0.0, 0.0, 0.0, y, 0.0, 0.0, 0.0, 0.0, z, 0.0, 0.0, 0.0, 0.0, 1.0);
        Mat4x4 ti = new Mat4x4(1.0 / x, 0.0, 0.0, 0.0, 0.0, 1.0 / y, 0.0, 0.0, 0.0, 0.0, 1.0 / z, 0.0, 0.0, 0.0, 0.0, 1.0);
        return new Transform(m.mul(tm), i.mul(ti));
    }

    /**
     * rotates the current object around the x-axis
     * @param phi Angle of the rotation
     * @return returns the current object rotated around the x-axis
     */
    public Transform rotationOnX(final double phi) {
        /**
         * Using the standard rotation matrix for rotations around the x-axis and its defined inverse matrix
         *
         *      | 1   0    0   0 |
         * rm = | 0  cos -sin  0 |
         *      | 0  sin  cos  0 |
         *      | 0   0    0   1 |
         */
        Mat4x4 tm = new Mat4x4(1.0, 0.0, 0.0, 0.0, 0.0, Math.cos(phi), -Math.sin(phi), 0.0, 0.0, Math.sin(phi), Math.cos(phi), 0.0, 0.0, 0.0, 0.0, 1.0);
        Mat4x4 ti = new Mat4x4(1.0, 0.0, 0.0, 0.0, 0.0, Math.cos(phi), Math.sin(phi), 0.0, 0.0, -Math.sin(phi), Math.cos(phi), 0.0, 0.0, 0.0, 0.0, 1.0);
        return new Transform(m.mul(tm), i.mul(ti));
    }

    /**
     * rotates the current object around the y-axis
     * @param phi Angle of the rotation
     * @return returns the current object rotated around the y-axis
     */
    public Transform rotateY(final double phi) {
        /**
         * Using the standard rotation matrix for rotations around the y-axis and its defined inverse matrix
         *
         *      |  cos  0    sin  0 |
         * rm = |   0   1     0   0 |
         *      | -sin  0    cos  0 |
         *      |  0    0     0   1 |
         */
        Mat4x4 tm = new Mat4x4(Math.cos(phi), 0.0, Math.sin(phi), 0.0, 0.0, 1.0, 0.0, 0.0, -Math.sin(phi), 0.0, Math.cos(phi), 0.0, 0.0, 0.0, 0.0, 1.0);
        Mat4x4 ti = new Mat4x4(Math.cos(phi), 0.0, -Math.sin(phi), 0.0, 0.0, 1.0, 0.0, 0.0, Math.sin(phi), 0.0, Math.cos(phi), 0.0, 0.0, 0.0, 0.0, 1.0);
        return new Transform(m.mul(tm), i.mul(ti));
    }

    /**
     * rotates the current object around the z-axis
     * @param phi Angle of the rotation
     * @return returns the current object rotated around the z-axis
     */
    public Transform rotateZ(final double phi) {
        /**
         * Using the standard rotation matrix for rotations around the y-axis and its defined inverse matrix
         *
         *      |  cos  -sin   0   0 |
         * rm = |  sin   cos   0   0 |
         *      |  0      0    1   0 |
         *      |  0      0    0   1 |
         */
        Mat4x4 tm = new Mat4x4(Math.cos(phi), -Math.sin(phi), 0.0, 0.0, Math.sin(phi), Math.cos(phi), 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0);
        Mat4x4 ti = new Mat4x4(Math.cos(phi), Math.sin(phi), 0.0, 0.0, -Math.sin(phi), Math.cos(phi), 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0);
        return new Transform(m.mul(tm), i.mul(ti));
    }

    /**
     * Multiplies the given ray with the matrix of the tramsform-object
     * @param ray Ray which is supposed to be multiplied with the transform-object.
     * @return new Ray with its vectors for origin and direction multiplied with the transformation-object
     */
    public Ray multiplicate(Ray ray){
        return new Ray(i.mul(ray.origin), i.mul(ray.direction));
    }

    /**
     * Multiplies the given ray with the matrix of the tramsform-object
     * @param normal Normal vector which is supposed to be multiplied with the transform-object.
     * @return new Normal vector with its values multiplied with the transformation-object
     */
    public Normal3 multiplicate(Normal3 normal){
        Vector3 tempVector = new Vector3 (normal.x, normal.y, normal.z);
        return i.transpose().mul(tempVector).normalized().asNormal();
    }

    /**
     * Methods for hashCode, equals and toString are being overwritten
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((i == null) ? 0 : i.hashCode());
        result = prime * result + ((m == null) ? 0 : m.hashCode());
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
        Transform other = (Transform) obj;
        if (i == null) {
            if (other.i != null)
                return false;
        } else if (!i.equals(other.i))
            return false;
        if (m == null) {
            if (other.m != null)
                return false;
        } else if (!m.equals(other.m))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Transform [m = " + m + ", i = " + i + "]";
    }

}
