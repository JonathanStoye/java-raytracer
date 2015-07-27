package Material;

import MatrixVector.Normal3;
import MatrixVector.Point3;
import MatrixVector.Vector3;
import Scene.*;

/**
 * Created by dave_digger on 27.07.2015.
 */
public class DavidsTransparentMaterial extends Material{

    final double refractionIndex;

    public DavidsTransparentMaterial(double refractionIndex) {
        this.refractionIndex = refractionIndex;
    }

    @Override
    public Color colorFor(Hit hit, World world, RecursiveTracer recursiveTracer) {
//      REFLECTION:
//      d = Direction of the Ray
//      n = Normal on the surface
//      r_d = Reflected Vector d, Reflected as usual, like in the reflective Material
//      |d| = |n| = |r_d| = 1 meaning that all these Vectors have length 1
//      alpha_1 = angle between d and n as well as r_d and

        Vector3 vec_d = hit.ray.direction.normalized();
        Normal3 normal_n = hit.n;
        Vector3 vec_r_d = vec_d.reflectedOn(normal_n).normalized();

//      the angle between normal and Direction is calculated like this
//      cos(alpha) = (-vec_d) \cdot normal_n
//      -> alpha = arccos [ ( -vec_d \cdot normal_n ) ]

        double alpha_1 = vec_d.mul(-1.0).dot(normal_n);


//      REFRACTION
//      There are Two Indexes which set how much light is reflected and how much light is refracted
//      They are called n_1 and n_2
//      n_1 = Reflection Index is 1 in this scenario, because reflection is simply in and out with the same angle
//      n_2 = Refraction Index
        double n_1 = world.refractionIndex;
        double n_2 = this.refractionIndex;
//      alpha_2 = This is the angle between the refracted vector d, which will be called vec_t
//      alpha_2 is calculated like this
        double alpha_2 = Math.sqrt(1.0 - (n_1 / n_2) * (1 - (1 / 2 + 1 / 2 * Math.cos(2 * alpha_1))));

//      Finally there is a new Vector called t.
//      It is the refracted Vector which is the cue to this Method. His "way" will be traced again (reverse)
//      The Vector t is calculated like this
        Vector3 vec_t = vec_d.mul(n_1 / n_2).sub(normal_n.mul(alpha_2 - (n_1 / n_2) * alpha_2));

        Point3 calc_Point = hit.ray.at(hit.t);

        if(n_1 > n_2){
//          big_R = Part of the Reflexion
//          big_T = Part of the Tramsmission
//          R and T have both a value between 0 and 1 and there sum is always 1
//          bit_R_zero = Is used to calculate big_R
            double big_R_zero = Math.pow(((n_1-n_2) / (n_1 + n_2)),(2));

//          Calculation of R:
            double big_R = big_R_zero + (1.0 - big_R_zero) * (1.0 - alpha_1);

//          Calculation of T
            double big_T = 1.0 - big_R;

            Ray reflection_Ray = new Ray(calc_Point, vec_r_d);
            Ray refraction_Ray = new Ray(calc_Point, vec_t);

            Hit reflection_Hit = world.hit(reflection_Ray);
            Hit refraction_Hit = world.hit(refraction_Ray);

            Color reflection_Color = reflection_Hit.geo.material.colorFor(reflection_Hit, world, recursiveTracer).mul(big_R);
            Color refraction_Color = refraction_Hit.geo.material.colorFor(refraction_Hit, world, recursiveTracer).mul(big_R);

//          The following formula is used to calculate the right Color
            Color c = reflection_Color.add(refraction_Color);

            return c;
        } else return null;
    }
}
