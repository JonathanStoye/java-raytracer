package MatrixVector;

/**
 * Class Normal3 represents an Instance of a (surface)normal
 * Created by David on 03.05.2015.
 */
public class Normal3 {

    public final Double x;
    public final Double y;
    public final Double z;

    /**
     * Initializes the normal vektor with the given values.
     * @param x X-Value of the normal vector
     * @param y y-Value of the normal vector
     * @param z z-Value of the normal vector
     * */
    public Normal3(Double x, Double y, Double z){
        this.x=x;
        this.y=y;
        this.z=z;
    }

    /**
     * Takes a Double variable and returns the normal-vector multiplied with this value.
     * @param n Value which is used to multiply with the given normal vector.
     * */
    public Normal3 mul(Double n){
        Normal3 result = new Normal3(this.x*n, this.y*n, this.z*n);
        return result;
    }

    /**
     * Adds all values of the given Normal3 and adds them to the current Normal3
     * @param n Normal3 supposed to be added to the current Normal3
     * @return Normal3 containing the total of the x,y and z Values of the given and current Normal3.
     * */
    public Normal3 add(Normal3 n){
        Normal3 result = new Normal3(this.x+n.x, this.y+n.y, this.z+n.z);
        return result;
    }

    /**
     * Multiplies the current Normal3 with the given Vector3
     * @param v Vector3 being multiplied with the current Normal3
     * @return result of the vector-multiplication
     * */
    public Double dot(Vector3 v) {
        Double result = (this.x * v.x) + (this.y * v.y) + (this.z * v.z);
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
        Normal3 otherNormal3 = (Normal3) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(otherNormal3.x))
            return false;
        if (Double.doubleToLongBits(y) != Double.doubleToLongBits(otherNormal3.y))
            return false;
        if (Double.doubleToLongBits(z) != Double.doubleToLongBits(otherNormal3.z))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Normal3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    @Override
    public int hashCode() {
        int result = x != null ? x.hashCode() : 0;
        result = 31 * result + (y != null ? y.hashCode() : 0);
        result = 31 * result + (z != null ? z.hashCode() : 0);
        return result;
    }
}
