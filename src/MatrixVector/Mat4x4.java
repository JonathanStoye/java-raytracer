package MatrixVector;

/**
 * Created by Viktoria de Koning on 11.07.15.
 */
public class Mat4x4
{
    public final Double m11;
    public final Double m12;
    public final Double m13;
    public final Double m14;
    public final Double m21;
    public final Double m22;
    public final Double m23;
    public final Double m24;
    public final Double m31;
    public final Double m32;
    public final Double m33;
    public final Double m34;
    public final Double m41;
    public final Double m42;
    public final Double m43;
    public final Double m44;
    public final Double det;

    /**
     * Initializes a new Matrix with the given cell values an calculates the determinant
     * @param m11 cell of the Matrix
     * @param m12 cell of the Matrix
     * @param m13 cell of the Matrix
     * @param m14 cell of the Matrix
     * @param m21 cell of the Matrix
     * @param m22 cell of the Matrix
     * @param m23 cell of the Matrix
     * @param m24 cell of the Matrix
     * @param m31 cell of the Matrix
     * @param m32 cell of the Matrix
     * @param m33 cell of the Matrix
     * @param m34 cell of the Matrix
     * @param m41 cell of the Matrix
     * @param m42 cell of the Matrix
     * @param m43 cell of the Matrix
     * @param m44 cell of the Matrix
     */
    public Mat4x4(Double m11, Double m12, Double m13, Double m14, Double m21, Double m22, Double m23, Double m24,
                  Double m31, Double m32, Double m33, Double m34, Double m41, Double m42, Double m43, Double m44)
    {
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
        this.m14 = m14;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;
        this.m24 = m24;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
        this.m34 = m34;
        this.m41 = m41;
        this.m42 = m42;
        this.m43 = m43;
        this.m44 = m44;
        this.det = (this.m11 * this.m22 * this.m33 * this.m44 +
                    this.m12 * this.m23 * this.m34 * this.m41 +
                    this.m13 * this.m24 * this.m31 * this.m42 +
                    this.m14 * this.m21 * this.m32 * this.m43 -
                    this.m41 * this.m32 * this.m23 * this.m14 -
                    this.m42 * this.m33 * this.m24 * this.m11 -
                    this.m43 * this.m34 * this.m21 * this.m12 -
                    this.m44 * this.m31 * this.m22 * this.m13);
    }

    /**
     * multiplies this with Mat4x4 m
     * @param m Mat4x4 which gets multiplied with this
     * @return a Mat4x4 containing the values of this multiplied with Mat3x3 m
     */
    public Mat4x4 mul(Mat4x4 m)
    {
        Double m11 = this.m11 * m.m11 + this.m12 * m.m21 + this.m13 * m.m31 + this.m14 * m.m41;
        Double m12 = this.m11 * m.m12 + this.m12 * m.m22 + this.m13 * m.m23 + this.m14 * m.m24;
        Double m13 = this.m11 * m.m13 + this.m12 * m.m23 + this.m13 * m.m33 + this.m14 * m.m34;
        Double m14 = this.m11 * m.m14 + this.m12 * m.m24 + this.m13 * m.m34 + this.m14 * m.m44;

        Double m21 = this.m21 * m.m11 + this.m22 * m.m21 + this.m23 * m.m31 + this.m24 * m.m41;
        Double m22 = this.m21 * m.m12 + this.m22 * m.m22 + this.m23 * m.m32 + this.m24 * m.m42;
        Double m23 = this.m21 * m.m13 + this.m22 * m.m23 + this.m23 * m.m33 + this.m24 * m.m43;
        Double m24 = this.m21 * m.m14 + this.m22 * m.m24 + this.m23 * m.m34 + this.m24 * m.m44;

        Double m31 = this.m31 * m.m11 + this.m32 * m.m21 + this.m33 * m.m31 + this.m34 * m.m41;
        Double m32 = this.m31 * m.m12 + this.m32 * m.m22 + this.m33 * m.m32 + this.m34 * m.m42;
        Double m33 = this.m31 * m.m13 + this.m32 * m.m23 + this.m33 * m.m33 + this.m34 * m.m43;
        Double m34 = this.m31 * m.m14 + this.m32 * m.m24 + this.m33 * m.m34 + this.m34 * m.m44;

        Double m41 = this.m41 * m.m11 + this.m42 * m.m21 + this.m43 * m.m31 + this.m44 * m.m41;
        Double m42 = this.m41 * m.m12 + this.m42 * m.m22 + this.m43 * m.m32 + this.m44 * m.m42;
        Double m43 = this.m41 * m.m13 + this.m42 * m.m23 + this.m43 * m.m33 + this.m44 * m.m43;
        Double m44 = this.m41 * m.m14 + this.m42 * m.m24 + this.m43 * m.m34 + this.m44 * m.m44;

        return new Mat4x4(m11, m12, m13, m14,
                          m21, m22, m23, m24,
                          m31, m32, m33, m34,
                          m41, m42, m43, m44);
    }

    /**
     * transposes the matrix with itself
     * @return a transposed Mat4x4
     */
    public Mat4x4 transpose()
    {
        return new Mat4x4(this.m11, this.m21, this.m31, this.m41,
                          this.m12, this.m22, this.m32, this.m42,
                          this.m13, this.m23, this.m33, this.m43,
                          this.m14, this.m24, this.m34, this.m44);
    }

    /**
     * multiplies this with Vector3 v
     * @param v Vector3 which gets multiplied with this
     * @return a Vector3 containing the values of this multiplied with Vector3 v
     * for the sake of completeness w = 0.0 was multiplied, too, even though m41, m21 and m31 aren't neccessary
     */
    public Vector3 mul(Vector3 v)
    {
        Double w = 0.0;
        Double x = this.m11 * v.x + this.m12 * v.y + this.m13 * v.z + this.m41 * w;
        Double y = this.m21 * v.x + this.m22 * v.y + this.m23 * v.z + this.m21 * w;
        Double z = this.m31 * v.x + this.m32 * v.y + this.m33 * v.z + this.m31 * w;

        return new Vector3(x, y, z);
    }

    /**
     * multiplies this with Point3 p
     * @param p Point3 which gets multiplied with this
     * @return Point3 containing the values of this multiplied with Point3 p
     * for the sake of completeness w = 1.0 was multiplied, too, even though we could have left the original m41, m21 and m31
     */
    public Point3 mul(Point3 p)
    {
        Double w = 1.0;
        Double x = this.m11 * p.x + this.m12 * p.y + this.m13 * p.z + this.m41 * w;
        Double y = this.m21 * p.x + this.m22 * p.y + this.m23 * p.z + this.m21 * w;
        Double z = this.m31 * p.x + this.m32 * p.y + this.m33 * p.z + this.m31 * w;

        return new Point3(x, y, z);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mat4x4 mat4x4= (Mat4x4) o;

        if (!m11.equals(mat4x4.m11)) return false;
        if (!m12.equals(mat4x4.m12)) return false;
        if (!m13.equals(mat4x4.m13)) return false;
        if (!m14.equals(mat4x4.m14)) return false;
        if (!m21.equals(mat4x4.m21)) return false;
        if (!m22.equals(mat4x4.m22)) return false;
        if (!m23.equals(mat4x4.m23)) return false;
        if (!m24.equals(mat4x4.m24)) return false;
        if (!m31.equals(mat4x4.m31)) return false;
        if (!m32.equals(mat4x4.m32)) return false;
        if (!m33.equals(mat4x4.m33)) return false;
        if (!m34.equals(mat4x4.m34)) return false;
        if (!m41.equals(mat4x4.m41)) return false;
        if (!m42.equals(mat4x4.m42)) return false;
        if (!m43.equals(mat4x4.m43)) return false;
        if (!m44.equals(mat4x4.m44)) return false;
        return det.equals(mat4x4.det);
    }

    @Override
    public int hashCode() {
        int result = m11.hashCode();
        result = 31 * result + m12.hashCode();
        result = 31 * result + m13.hashCode();
        result = 31 * result + m14.hashCode();
        result = 31 * result + m21.hashCode();
        result = 31 * result + m22.hashCode();
        result = 31 * result + m23.hashCode();
        result = 31 * result + m24.hashCode();
        result = 31 * result + m31.hashCode();
        result = 31 * result + m32.hashCode();
        result = 31 * result + m33.hashCode();
        result = 31 * result + m34.hashCode();
        result = 31 * result + m41.hashCode();
        result = 31 * result + m42.hashCode();
        result = 31 * result + m43.hashCode();
        result = 31 * result + m44.hashCode();
        result = 31 * result + det.hashCode();
        return result;
    }
}
