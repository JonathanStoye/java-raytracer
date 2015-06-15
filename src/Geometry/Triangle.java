package Geometry;

import MatrixVector.*;
import Scene.*;
import Material.*;

/**
 * Created by Jonathan on 05.06.15.
 * Implemented by David on 06.06.15
 * Class Triangle is used to describe a triangle in the 3-dimensional coordinate System.
 * @author d.derichs
 */
public class Triangle extends Geometry{
    public final Point3 a;
    public final Point3 b;
    public final Point3 c;

    public final Normal3 normalOnA;
    public final Normal3 normalOnB;
    public final Normal3 normalOnC;

    /**
     * Initializes the triangle with the given parameters
     * @param a Point a of the triangle
     * @param b Point b of the triangle
     * @param c Point c of the triangle
     * @param material material of the Geometric form triangle
     */
    public Triangle(Point3 a, Point3 b, Point3 c, Material material){
        super(material);
        this.a=a;
        this.b=b;
        this.c=c;
        // Between the two calculated Vectors for example b-a and c-a is a plane.
        // the cross-prodct "method.x()" is basically the normal vector for this plane
        // we normalize this vector, because then it has a length of 1.
        // We also declare it asNormal in order to make sure, that we can use it as a normal-vector.
        // This is important, for example when we calculate a Hit-object during interceptions.
        this.normalOnA = this.b.sub(this.a).x(this.c.sub(this.a)).normalized().asNormal();
        this.normalOnB = this.c.sub(this.b).x(this.a.sub(this.b)).normalized().asNormal();
        this.normalOnC = this.a.sub(this.c).x(this.b.sub(this.c)).normalized().asNormal();
    }

    /**
     * Determines if the triangle is hit by the viewers ray.
     *
     * @param ray specific ray, which is potentially hitting the current object.
     * @return Hit object which describes the t-value
     */
    @Override
    public Hit hit(final Ray ray) {

        // This Matrix is used at first to store the x, y and z values of the Points
        // In order to find out the intersection, we need to use a linear system of equations
        // The Formular look like this:
        // Matrix:
        // |Xa - Xb    Xa - Xc     Xd|      |?|
        // |Ya - Yb    Ya - Yc     Yd|      |?|
        // |Za - Zb    Za - Zc     Zd|      |t|
        //
        Mat3x3 linearEquationSystem = new Mat3x3(
            //x-Values
            this.a.x - this.b.x, this.a.x - this.c.x, ray.direction.x,
            //y-Values
            this.a.y - this.b.y, this.a.y - this.c.y, ray.direction.y,
            //z-Values
            this.a.z - this.b.z, this.a.z - this.c.z, ray.direction.z
        );

        // First we have to check if the determinant of the given Matrix is null
        // if so the linear system of equotations is not solvable.
        if (linearEquationSystem.determinant == 0){
            return null;
        }

        // The linear system of equotations is solvable using "The Rule of Cramer"
        // it looks like this:
        //
        //         det(A1)                det(A2)            det(A3)
        // beta = --------   and gamma = --------   and t = --------
        //         det(A)                 det(A)             det(A)
        //
        // A1, A2 and A3 are standing for the columns of the matrix

        Vector3 changeVector = (this.a.sub(ray.origin));
        Mat3x3 matrixA1 = linearEquationSystem.changeCol1(changeVector);
        Mat3x3 matrixA2 = linearEquationSystem.changeCol2(changeVector);
        Mat3x3 matrixA3 = linearEquationSystem.changeCol3(changeVector);

        // Now the three unkown Variables are calculated
        final double beta = matrixA1.determinant / linearEquationSystem.determinant;
        final double gamma = matrixA2.determinant / linearEquationSystem.determinant;
        final double t = matrixA3.determinant / linearEquationSystem.determinant;

        // Now we have to check, if the values do correspond to the following rule.
        // If so, then they are definitely NOT intercepted by the ray
        if (beta < 0 || gamma < 0 || Math.ceil(beta+gamma) != 1.0 || t<0.00000001) {
            return null;
        }

        // In order to use PhongLightning, we need to calculate a Normal for the hit point.
        // This is why we calculate it here.
        final Normal3 hitNormal = normalOnA;

        //Not sure if this is working:
        //found it in the internet, but did not understand unfortunately.
//        final Normal3 hitNormal = normalOnA.mul(1 - beta - gamma).add(normalOnB.mul(beta).add(normalOnC.mul(gamma)));

        // As we now know that the ray is hitting the triangle, we can return a Hit-object giving the necessary information.
        return new Hit(t, ray, this, hitNormal);
    }

    /*
    Hashcode(), equals() and toString() are overwritten:
   */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((a == null) ? 0 : a.hashCode());
        result = prime * result + ((b == null) ? 0 : b.hashCode());
        result = prime * result + ((c == null) ? 0 : c.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Triangle other = (Triangle) obj;
        if (a == null) {
            if (other.a != null)
                return false;
        } else if (!a.equals(other.a))
            return false;
        if (b == null) {
            if (other.b != null)
                return false;
        } else if (!b.equals(other.b))
            return false;
        if (c == null) {
            if (other.c != null)
                return false;
        } else if (!c.equals(other.c))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Triangle [a = " + a + ", b = " + b + ", c = " + c + "]";
    }
}
