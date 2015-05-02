package MatrixVector;

/**
 * Created by Jonathan on 01.05.15.
 */
public class Mat3x3 {

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

        return new Mat3x3(m11, m12, m12, m21, m22, m23, m31, m32, m33);
    }

    public Vector3 mul(Vector3 v) {
        Double x = this.m11 * v.x + this.m12 * v.y + this.m13 * v.z;
        Double y = this.m21 * v.x + this.m22 * v.y + this.m23 * v.z;
        Double z = this.m31 * v.x + this.m32 * v.y + this.m33 * v.z;
        return new Vector3(x, y, z);
    }

    public Point3 mul(Point3 p) {
        Double x = this.m11 * p.x + this.m12 * p.y + this.m13 * p.z;
        Double y = this.m21 * p.x + this.m22 * p.y + this.m23 * p.z;
        Double z = this.m31 * p.x + this.m32 * p.y + this.m33 * p.z;
        return new Point3(x, y, z);
    }

    public Mat3x3 changeCol1(Vector3 v) {
        return new Mat3x3(v.x, v.y, v.z, this.m21, this.m22, this.m23, this.m31, this.m32, this.m33);
    }

    public Mat3x3 changeCol2(Vector3 v) {
        return new Mat3x3(v.x, v.y, v.z, this.m21, this.m22, this.m23, this.m31, this.m32, this.m33);
    }

    public Mat3x3 changeCol3(Vector3 v) {
        return new Mat3x3(v.x, v.y, v.z, this.m21, this.m22, this.m23, this.m31, this.m32, this.m33);
    }

    public static void main (String[] args) {

    }
}
