package MatrixVector;
import utilities.Debugging;

/**
 * Created by Jonathan on 01.05.15.
 * the representation of a 3x3 Matrix
 * provides the methods to multiply it with a Matrix, a Vector or a Point
 * implements tests to assure its functionality
 */
public final class Mat3x3 {

    public final Double m11;
    public final Double m12;
    public final Double m13;
    public final Double m21;
    public final Double m22;
    public final Double m23;
    public final Double m31;
    public final Double m32;
    public final Double m33;
    public final Double determinant;

    /**
     * Initializes a new Matrix with the given cell values an calculates the determinant
     * @param m11 cell of the Matrix
     * @param m12 cell of the Matrix
     * @param m13 cell of the Matrix
     * @param m21 cell of the Matrix
     * @param m22 cell of the Matrix
     * @param m23 cell of the Matrix
     * @param m31 cell of the Matrix
     * @param m32 cell of the Matrix
     * @param m33 cell of the Matrix
     */
    public Mat3x3(Double m11, Double m12, Double m13, Double m21, Double m22, Double m23, Double m31, Double m32, Double m33) {
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
        this.determinant = m11 * m22 * m33 + m12 * m23 * m31 + m13 * m21 * m32 - m31 * m22 * m13 - m32 * m23 * m11 - m33 * m21 * m12;
    }

    /**
     * multiplies this with Mat3x3 m
     * @param m Mat3x3 which gets multiplied with this
     * @return a Mat3x3 containing the values of this multiplied with Mat3x3 m
     */
    public Mat3x3 mul(Mat3x3 m) {
        Double m11 = this.m11 * m.m11 + this.m12 * m.m21 + this.m13 * m.m31;
        Double m12 = this.m11 * m.m12 + this.m12 * m.m22 + this.m13 * m.m32;
        Double m13 = this.m11 * m.m13 + this.m12 * m.m23 + this.m13 * m.m33;

        Double m21 = this.m21 * m.m11 + this.m22 * m.m21 + this.m23 * m.m31;
        Double m22 = this.m21 * m.m12 + this.m22 * m.m22 + this.m23 * m.m32;
        Double m23 = this.m21 * m.m13 + this.m22 * m.m23 + this.m23 * m.m33;

        Double m31 = this.m31 * m.m11 + this.m32 * m.m21 + this.m33 * m.m31;
        Double m32 = this.m31 * m.m12 + this.m32 * m.m22 + this.m33 * m.m32;
        Double m33 = this.m31 * m.m13 + this.m32 * m.m23 + this.m33 * m.m33;

        return new Mat3x3(m11, m12, m13, m21, m22, m23, m31, m32, m33);
    }

    /**
     * multiplies this with Vector3 v
     * @param v Vector3 which gets multiplied with this
     * @return a Vector3 containing the values of this multiplied with Vector3 v
     */
    public Vector3 mul(Vector3 v) {
        Double x = this.m11 * v.x + this.m12 * v.y + this.m13 * v.z;
        Double y = this.m21 * v.x + this.m22 * v.y + this.m23 * v.z;
        Double z = this.m31 * v.x + this.m32 * v.y + this.m33 * v.z;
        return new Vector3(x, y, z);
    }

    /**
     * multiplies this with Point3 p
     * @param p Point3 which gets multiplied with this
     * @return a Point3 containing the values of this multiplied with Point3 p
     */
    public Point3 mul(Point3 p) {
        Double x = this.m11 * p.x + this.m12 * p.y + this.m13 * p.z;
        Double y = this.m21 * p.x + this.m22 * p.y + this.m23 * p.z;
        Double z = this.m31 * p.x + this.m32 * p.y + this.m33 * p.z;
        return new Point3(x, y, z);
    }

    /**
     * changes the first column of this and returns it as a new Mat3x3 (this stays unchanged)
     * @param v the vector to replace the first column of this
     * @return a new Mat3x3 with the first column of v and the other columns of this
     */
    public Mat3x3 changeCol1(Vector3 v) {
        return new Mat3x3(v.x, v.y, v.z, this.m21, this.m22, this.m23, this.m31, this.m32, this.m33);
    }

    /**
     * changes the second column of this and returns it as a new Mat3x3 (this stays unchanged)
     * @param v the vector to replace the second column of this
     * @return a new Mat3x3 with the second column of v and the other columns of this
     */
    public Mat3x3 changeCol2(Vector3 v) {
        return new Mat3x3(this.m11, this.m12, this.m13, v.x, v.y, v.z, this.m31, this.m32, this.m33);
    }

    /**
     * changes the third column of this and returns it as a new Mat3x3 (this stays unchanged)
     * @param v the vector to replace the third column of this
     * @return a new Mat3x3 with the third column of v and the other columns of this
     */
    public Mat3x3 changeCol3(Vector3 v) {
        return new Mat3x3(this.m11, this.m12, this.m13, this.m21, this.m22, this.m23, v.x, v.y, v.z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mat3x3 mat3x3 = (Mat3x3) o;

        if (!m11.equals(mat3x3.m11)) return false;
        if (!m12.equals(mat3x3.m12)) return false;
        if (!m13.equals(mat3x3.m13)) return false;
        if (!m21.equals(mat3x3.m21)) return false;
        if (!m22.equals(mat3x3.m22)) return false;
        if (!m23.equals(mat3x3.m23)) return false;
        if (!m31.equals(mat3x3.m31)) return false;
        if (!m32.equals(mat3x3.m32)) return false;
        if (!m33.equals(mat3x3.m33)) return false;
        return determinant.equals(mat3x3.determinant);

    }

    @Override
    public int hashCode() {
        int result = m11.hashCode();
        result = 31 * result + m12.hashCode();
        result = 31 * result + m13.hashCode();
        result = 31 * result + m21.hashCode();
        result = 31 * result + m22.hashCode();
        result = 31 * result + m23.hashCode();
        result = 31 * result + m31.hashCode();
        result = 31 * result + m32.hashCode();
        result = 31 * result + m33.hashCode();
        result = 31 * result + determinant.hashCode();
        return result;
    }

    /**
     * this function is used to test the functionality of this class
     */
    public static void test() {
        testUnitMatrixMultiplication();
        testMatrixMultiplication();
        testColumnChange();
        testMatrixVectorMultiplication();
    }

    /**
     * test whether the multiplication of a matrix with the unit matrix fails
     */
    private static void testUnitMatrixMultiplication() {
        final Mat3x3 m = new Mat3x3(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0);
        final Mat3x3 e = new Mat3x3(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0);
        final Mat3x3 prod = m.mul(e);

        if (prod.equals(m)) {
            Debugging.log("testUnitMatrixMultiplication successful");
        }
        else {
            Debugging.log("testUnitMatrixMultiplication not successful");
        }
    }

    /**
     * test whether the multiplication of a matrix with a Vector fails
     */
    private static void testMatrixVectorMultiplication() {
        final Mat3x3 e = new Mat3x3(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0);
        final Vector3 v = new Vector3(3.0, 2.0, 1.0);
        final Vector3 prod = e.mul(v);

        if (prod.equals(v)) {
            Debugging.log("testMatrixVectorMultiplication successful");
        }
        else {
            Debugging.log("testMatrixVectorMultiplication not successful");
        }
    }

    /**
     * test whether the multiplication of a matrix with another matrix fails
     */
    private static void testMatrixMultiplication() {
        final Mat3x3 m1 = new Mat3x3(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0);
        final Mat3x3 m2 = new Mat3x3(0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0);
        final Mat3x3 prod = m1.mul(m2);
        final Mat3x3 goal = new Mat3x3(3.0, 2.0, 1.0, 6.0, 5.0, 4.0, 9.0, 8.0, 7.0);

        if (prod.equals(goal)) {
            Debugging.log("testMatrixMultiplication successful");
        }
        else {
            Debugging.log("testMatrixMultiplication not successful");
        }
    }

    /**
     * test whether the change of the columns fails
     */
    private static void testColumnChange() {
        final Mat3x3 m1 = new Mat3x3(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0);
        final Vector3 v = new Vector3(8.0, 8.0, 8.0);
        Mat3x3 prod = m1.changeCol1(v);
        prod = prod.changeCol2(v);
        prod = prod.changeCol3(v);
        final Mat3x3 goal = new Mat3x3(8.0, 8.0, 8.0, 8.0, 8.0, 8.0, 8.0, 8.0, 8.0);

        if (prod.equals(goal)) {
            Debugging.log("testColumnChange successful");
        }
        else {
            Debugging.log("testColumnChange not successful");
        }
    }
}
