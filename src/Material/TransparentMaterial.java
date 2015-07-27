package Material;

import MatrixVector.Normal3;
import MatrixVector.Point3;
import MatrixVector.Vector3;
import Scene.*;

/**
 * Created by Viktoria de Koning on 26.07.15.
 * Snell's law is used to determine the direction of light rays through refractive media with varying indices of refraction.
 * As light passes the border between media, depending upon the relative refractive indices of the two media,
 * the light will either be refracted to a lesser angle, or a greater one.
 * These angles are measured with respect to the normal line, represented perpendicular to the boundary.
 * Snell's law is generally true only for isotropic or specular media (such as glass).
 */
public class TransparentMaterial extends Material
{
    final Double indexOfRefraction;

    public TransparentMaterial(Double indexOfRefraction)
    {
        this.indexOfRefraction = indexOfRefraction;
    }

    @Override
    public Color colorFor(Hit hit, World world, RecursiveTracer recursiveTracer)
    {
        // Normal of the hit object
        Normal3 normal = hit.n;
        // Vector pointing in the negative direction of d (-d)
        Vector3 d = hit.ray.direction.mul(-1.0);
        // nSnell = n1/n2 or n2/n1, depends on Snell's Law and on the angle
        double nSnell;
        Normal3 nTemp;

        // boundaries, where the light it refracted --> Snell's Law | < 0 obtuse angle, = 0 right Winkel, > 0 acute Winkel
        // if n > 0, it points from the surface toward the side where the light is coming from --> into the geometry
        // if n < 0, it points to the side without the light --> out of the geometry
        if(d.dot(normal) < 0) //
        {
            // n = (n2 / n1)
            nSnell = indexOfRefraction / world.refractionIndex;
            nTemp = normal.mul(- 1.0); //n > 0
        }
        else // d.dot(normal) >= 0
        {
            // n = (n1 / n2)
            nSnell = world.refractionIndex / indexOfRefraction;
            nTemp = normal; // n < 0
        }

        // Angle cosTheta_1 = (-d) * n
        Double cosTheta1 = d.dot(nTemp);

        // Angle cosTheta_2 = Math.sqrt(1 - (n_1 / n_2)^2)*(1 - cosTheta_1^2))
        Double cosThetaTemp = 1 - (Math.pow(nSnell, 2.0)) * 1 - (Math.pow(cosTheta1, 2.0));
        Double cosTheta2 = Math.sqrt(cosThetaTemp);

        // fr[(pr, rd)]
        Vector3 rd = d.reflectedOn(normal);
        Point3 pointR = hit.ray.at(hit.t - 0.001);
        Ray frR = new Ray(pointR, rd);

        if(cosThetaTemp < 0)
        {
            return recursiveTracer.trace(frR);
        }
        else //cosThetaTemp >= 0
        {
            // t = (n1/n2)*d - (cosTheta_2 - (n1/n2)*cosTheta_1))*normal
            Vector3 t = d.mul(-1.0).mul(nSnell).sub(nTemp.mul(cosTheta2 - (nSnell * cosTheta1)));
            //// fr[(pr, rt)]
            Ray frT = new Ray(pointR, t);

            // Schlicksche Approximation
            Double r0;
            // if n > 0, it points from the surface toward the side where the light is coming from --> into the geometry
            // if n < 0, it points to the side without the light --> out of the geometry
            if(d.dot(normal) < 0)
            {
                // R0 = ((n2 -n1)/(n2 + n1))^2
                r0 = Math.pow(((indexOfRefraction - world.refractionIndex) / (indexOfRefraction + world.refractionIndex)), 2.0);
            }
            else // e.dot(normal) >= 0
            {
                // R0 = ((n1 -n2)/(n1 + n2))^2
                r0 = Math.pow(((world.refractionIndex - indexOfRefraction) / (world.refractionIndex + indexOfRefraction)), 2.0);
            }
            // calculate R (Reflexion) R = R0 + (1 - R0)(1 - cosTheta_1)^5
            Double rR = Math.pow((r0 + (1 - r0) * (1 - cosTheta1)), 5.0);
            // calculate T (Transmission) T = 1 - R
            Double tT = 1- rR;

            // c = R * fr[(pr, rd)] + T * fr[(pr, rt)]
            // Reflexion added to the color
            Color c = recursiveTracer.trace(frR).mul(rR);
            // Transmission added to the color
            return c.add(recursiveTracer.trace(frT).mul(tT));
        }
    }

    @Override
    public String toString() {
        return "TransparentMaterial{" +
                "indexOfRefraction=" + indexOfRefraction +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransparentMaterial that = (TransparentMaterial) o;

        return !(indexOfRefraction != null ? !indexOfRefraction.equals(that.indexOfRefraction) : that.indexOfRefraction != null);

    }

    @Override
    public int hashCode() {
        return indexOfRefraction != null ? indexOfRefraction.hashCode() : 0;
    }
}
