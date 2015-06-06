package Geometry;

import MatrixVector.*;
import Scene.*;

/**
 * Created by Jonathan on 05.06.15.
 * Implemented by David on 06.06.15
 * Class Triangle is used to describe a triangle in the 3-dimensional coordinate System.
 * @author d.derichs
 */
public class Triangle extends Geometry{
    public Point3 a;
    public Point3 b;
    public Point3 c;

    /**
     * Initializes the triangle with the given parameters
     * @param a Point a of the triangle
     * @param b Point b of the triangle
     * @param c Point c of the triangle     *
     * @param color Color of the Geometric form triangle
     */
    public Triangle(Point3 a, Point3 b, Point3 c, Color color){
        super(color);
        this.a=a;
        this.b=b;
        this.c=c;
    }

    /**
     * Determines if the triangle is hit by the viewers ray.
     *
     * @param ray specific ray, which is potentially hitting the current object.
     * @return Hit object which describes the t-value
     */
    @Override
    public Hit hit(Ray ray) {

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
        if (linearEquationSystem.determinant == 0)return null;

        // The linear system of equotations is solvable using "The Rule of Cramer"
        // it looks like this:
        //
        //      det(A1)            det(A2)            det(A3)
        // ? = --------   and ? = --------   and t = --------
        //      det(A)             det(A)             det(A)
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

        // Now we have to check, if the values correspond to the following rule.
        // If so, then they are definitely intercepted by the ray
        if (beta < 0 || gamma < 0 || beta + gamma > 1 || t < 0.00000001) {
            return null;
        }

        // As we now know that the ray is hitting the triangle, we can return a Hit-object giving the necessary information.
        return new Hit(t, ray, this);
    }
}
