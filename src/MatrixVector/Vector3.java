package MatrixVector;

/**
 * Created by Jonathan on 01.05.15.
 */
public final class Vector3 {

    public final Double x;
    public final Double y;
    public final Double z;
    public Double magnitude;

    /**
     * Initiates the Vektor with the given Values.
     * @param x X-Value of the Vector
     * @param y Y-Value of the Vector
     * @param z Z-Value of the Vector
     * */
    public Vector3(Double x, Double y, Double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Adds the given Vector3 to the current Vector3.
     * @param v Vector3, supposed to be added to the current Vector3
     * @return Total of the current and given Vector values.
     * */
    public Vector3 add(Vector3 v){
        Vector3 result = new Vector3( (this.x + v.x), (this.y + v.y), (this.z + v.z));
        return result;
    }

    /**
     * Adds the given Normal3 to the current Vector3
     * @param n Normal3, supposed to be added to the current Vector3
     * @return Total of the current Vector3 and given Normal3.
     * */
    public Vector3 add(Normal3 n){
        Vector3 result = new Vector3( (this.x + n.x), (this.y + n.y), (this.z + n.z));
        return result;
    }

    /**
     *
     * */
    public Vector3 sub(Normal3 n){
        Vector3 result = new Vector3((this.x-n.x), (this.y-n.y), (this.z-n.z));
        return result;
    }

    /**
     *
     * */
    public Vector3 mul(Double c){
        Vector3 result = new Vector3((this.x*c), (this.y*c), (this.z*c));
        return result;
    }

    /**
     *
     * */
    public Double dot(){

    }

//    +sub(eing. n : Normal3) : Vector3
//    +mul(eing. c : double) : Vector3
//    +dot(eing. v : Vector3) : double
//    +dot(eing. n : Normal3) : double
//    +normalized() : Vector3
//    +asNormal() : Normal3
//    +reflectedOn(eing. n : Normal3) : Vector3
//    +x(eing. v : Vector3) : Vector3

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

}
