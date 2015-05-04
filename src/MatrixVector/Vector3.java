package MatrixVector;

import java.util.Vector;

/**
 * Created by David on 04.05.2015
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
     * Subtracts the given Normal3 from the current Vector3.
     * @param n Normal3, supposed to be subtracted from the given Vector3.
     * @return Substraction Normal3 from Vector3.
     * */
    public Vector3 sub(Normal3 n){
        Vector3 result = new Vector3((this.x-n.x), (this.y-n.y), (this.z-n.z));
        return result;
    }

    /**
     * Multiplies the given Value with the current Vector3.
     * @param c Value of the scalar
     * @return result of the scalar multiplication
     * */
    public Vector3 mul(Double c){
        Vector3 result = new Vector3((this.x*c), (this.y*c), (this.z*c));
        return result;
    }

    /**
     * Multiplies the given Vector3 with the current Vector3.
     * @param v Vector supposed to be multiplied with the current.
     * @return result of the vector multiplication
     * */
    public Double dot(Vector3 v){
        Double result = (this.x*v.x) + (this.y*v.y) + (this.z*v.z);
        return result;
    }

    /**
     * Multiplies the given Normal3 with the current Vector3
     * @param n Normal, supposed to be multiplied with the current Vector3.
     * @return result of the vector multiplication.
     * */
    public Double dot(Normal3 n){
        Double result = (this.x*n.x) + (this.y*n.y) + (this.z*n.z);
        return result;
    }

    /**
     * Normalizes the current Vector3 using his length (norm)
     * @return normalized Vector3
     * */
    public Vector3 normalized(){
        Double norm = Math.sqrt(this.x*this.x) + (this.y*this.y) + (this.z*this.z);
        Vector3 result = new Vector3(this.x*(1/norm), this.y*(1/norm), this.z*(1/norm));
        return result;
    }

    /**
     * Converts the current Vector into a Normal3
     * @return Normal3 based on the current Vector3
     * */
    public Normal3 asNormal(){
        Normal3 result = new Normal3 (this.y*this.z-(this.z*this.y), (this.z*this.x)-(this.x*this.z), (this.x*this.y)-(this.y*this.x));
        return result;
    }

    /**
     * Reflects the current Vector3 using the given Normal3
     * @param n Normal3, used to reflect the current Vector
     * @return reflected Vector3
     * */
    public Vector3 reflectedOn (Normal3 n){
        Vector3 result = this.add(n.mul(n.dot(this.mul(2.0))));
        return result;
    }

    /**
     * Calculates the cross product between the current and a given Vector3
     * @param v given Vector3, used to be the right part of the cross product
     * @result the cross product as a new Vector3.
     * */
    public Vector3 x(Vector3 v){
        Double a = this.y*v.z - this.z*v.y;
        Double b = this.z*v.x - this.x*v.z;
        Double c = this.x*v.y - this.y*v.x;
        Vector3 result = new Vector3(a, b, c);
        return result;
    }

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
