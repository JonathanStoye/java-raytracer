package MatrixVector;

/**
 * Created by Jonathan on 01.05.15.
 */
public class Vector3 {

    public final Double x;
    public final Double y;
    public final Double z;

    public Vector3(Double x, Double y, Double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
//    +magnitude : double
//    +add(eing. v : Vector3) : Vector3
//    +add(eing. n : Normal3) : Vector3

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector3 vector3 = (Vector3) o;

        if (!x.equals(vector3.x)) return false;
        if (!y.equals(vector3.y)) return false;
        return z.equals(vector3.z);

    }

    @Override
    public int hashCode() {
        int result = x.hashCode();
        result = 31 * result + y.hashCode();
        result = 31 * result + z.hashCode();
        return result;
    }

//    +sub(eing. n : Normal3) : Vector3
//    +mul(eing. c : double) : Vector3
//    +dot(eing. v : Vector3) : double
//    +dot(eing. n : Normal3) : double
//    +normalized() : Vector3
//    +asNormal() : Normal3
//    +reflectedOn(eing. n : Normal3) : Vector3
//    +x(eing. v : Vector3) : Vector3
}
