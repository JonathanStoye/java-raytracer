package MatrixVector;
import utilities.Debugging;

/**
 * Created by David on 04.05.2015
 */
public final class Vector3 {

    public final Double x;
    public final Double y;
    public final Double z;
    public final Double magnitude;

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
        this.magnitude = Math.sqrt(this.x*this.x + this.y*this.y + this.z*this.z);
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
        Vector3 result = new Vector3(this.x*(1/this.magnitude), this.y*(1/this.magnitude), this.z*(1/this.magnitude));
        return result;
    }

    /**
     * Converts the current Vector into a Normal3
     * @return Normal3 based on the current Vector3
     * */
    public Normal3 asNormal(){
        Normal3 result = new Normal3 (this.x, this.y, this.z);
        return result;
    }

    /**
     * Reflects the current Vector3 using the given Normal3
     * @param n Normal3, used to reflect the current Vector
     * @return reflected Vector3
     * */
    public Vector3 reflectedOn (Normal3 n){
//        Vector3 result = this.add(n.mul(n.dot(this.mul(2.0))));
        //(2.0)?(n)?(s)?(v)=r
        Double s = this.dot(n);
        Vector3 no = new Vector3(n.x, n.y, n.z);
        no = no.mul(s).mul(2.0);
        Vector3 result = no.sub(this.asNormal());
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

        if (!(this.x.equals(vector3.x))) return false;
        if (!(this.y.equals(vector3.y))) return false;
        return this.z.equals(vector3.z);
    }

    public boolean equals(Normal3 o) {
        if (this.asNormal() == o) return true;
        if (o == null) return false;

        Normal3 vector3 = o;

        if (!(this.x.equals(vector3.x))) return false;
        if (!(this.y.equals(vector3.y))) return false;
        return this.z.equals(vector3.z);
    }

    @Override
    public int hashCode() {
        int result = x.hashCode();
        result = 31 * result + y.hashCode();
        result = 31 * result + z.hashCode();
        return result;
    }

    private static void testVectorMulequalsNormal(){
        Vector3 a = new Vector3(1.0,2.0,3.0);
        Normal3 b = new Normal3(0.5,1.0,1.5);
        Vector3 r = a.mul(0.5);
        if (r.equals(b)) {
            Debugging.log("test Vector Multiplication equals Normal successful");
        }
        else {
            Debugging.log("test Vector Multiplication equals Normal not successful");
        }
    }

    private static void testNormalAdd(){
        Normal3 a = new Normal3(1.0,2.0,3.0);
        Normal3 b = new Normal3(3.0,2.0,1.0);
        Normal3 c = a.add(b);
        Vector3 v = new Vector3(4.0,4.0,4.0);

        if (c.equals(v)) {
            Debugging.log("Addition of Normals successful");
        }
        else {
            Debugging.log("Addition of Normals not successful");
        }
    }

    private static void testDotNormVec(){
        Normal3 n = new Normal3(1.0, 0.0, 0.0);
        Vector3 v = new Vector3(1.0, 0.0, 0.0);

        if (n.dot(v) == 1.0) {
            Debugging.log("test Norm - Vector Multiplication successful");
        }
        else {
            Debugging.log("test Norm - Vector Multiplication not successful");
        }
    }
    private static void testDotVecNorm(){
        Normal3 n = new Normal3(1.0, 0.0, 0.0);
        Vector3 v = new Vector3(1.0, 0.0, 0.0);

        if (v.dot(n) == 1.0) {
            Debugging.log("test Vector - Norm Multiplication successful");
        }
        else {
            Debugging.log("test Vector - Norm Multiplication not successful");
        }
    }

    private static void testDotVecVec(){
        Vector3 v1 = new Vector3(1.0, 0.0, 0.0);
        Vector3 v2 = new Vector3(1.0, 0.0, 0.0);

        if (v1.dot(v2) == 1.0) {
            Debugging.log("test Vector Multiplication successful");
        }
        else {
            Debugging.log("test Vector Multiplication not successful");
        }
    }

    private static void testMagn(){
        Vector3 v = new Vector3(1.0, 1.0, 1.0);

        if (v.magnitude == Math.sqrt(3)) {
            Debugging.log("test Vector magnitude successful");
        }
        else {
            Debugging.log("test Vector magnitude not successful");
        }
    }

    private static void testSub(){
        Vector3 v = new Vector3(1.0,1.0,1.0);
        Normal3 n = new Normal3(2.0,2.0,0.0);
        Vector3 r = new Vector3(-1.0,-1.0,1.0);

        if (v.sub(n).equals(r)) {
            Debugging.log("test Vector Substraction1 successful");
        }
        else {
            Debugging.log("test Vector Substraction1 not successful");
        }
    }

    private static void testSub2(){
        Vector3 v = new Vector3(1.0,1.0,1.0);
        Normal3 n = new Normal3(4.0,3.0,2.0);
        Vector3 r = new Vector3(-3.0,-2.0,-1.0);

        if (v.sub(n).equals(r)) {
            Debugging.log("test Vector Substraction2 successful");
        }
        else {
            Debugging.log("test Vector Substraction2 not successful");
        }

    }

    private static void testAdd(){
        Vector3 v = new Vector3(1.0,1.0,1.0);
        Normal3 n = new Normal3(4.0,3.0,2.0);
        Vector3 r = new Vector3(5.0,4.0,3.0);

        if (v.add(n).equals(r)) {
            Debugging.log("test Vector Addition successful");
        }
        else {
            Debugging.log("test Vector Addition not successful");
        }

    }

    private static void testMul(){
        Vector3 v = new Vector3(1.0,1.0,1.0);
        Vector3 r = new Vector3(3.0,3.0,3.0);

        if (v.mul(3.0).equals(r)) {
            Debugging.log("test Vector Multiplication with scalar successful");
        }
        else {
            Debugging.log("test Vector Multiplication with scalar not successful");
        }

    }

    private static void testReflectedOn1(){
        Vector3 v = new Vector3(-0.707,0.707,0.0);
        Normal3 n = new Normal3(0.0,1.0,0.0);
        Vector3 check = v.reflectedOn(n);
        Vector3 r= new Vector3(0.707,0.707,0.0);

        if (v.reflectedOn(n).equals(r)) {
            Debugging.log("test Vector Reflection1 successful");
        }
        else {
            Debugging.log("test Vector Reflection1 not successful");
        }
    }

    private static void testReflectedOn2(){
        Vector3 v = new Vector3(0.707,0.707,0.0);
        Normal3 n = new Normal3(1.0,0.0,0.0);
        Vector3 r= new Vector3(0.707,-0.707,0.0);

        if (v.reflectedOn(n).equals(r)) {
            Debugging.log("test Vector Reflection2 successful");
        }
        else {
            Debugging.log("test Vector Reflection2 not successful");
        }
    }

    public static void test() {
        /**
         * Tests zur Aufgabenstellung
         * */
        testVectorMulequalsNormal();
        testNormalAdd();
        testDotNormVec();
        testDotVecNorm();
        testDotVecVec();
        testMagn();
        testAdd();
        testSub();
        testSub2();
        testMul();
        testReflectedOn1();
        testReflectedOn2();
    }

}
