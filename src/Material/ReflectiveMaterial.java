package Material;

import Light.Light;
import MatrixVector.Normal3;
import MatrixVector.Point3;
import MatrixVector.Vector3;
import Scene.Color;
import Scene.Hit;
import Scene.World;

/**
 * The class ReflectiveMaterial is used to describe PhongMaterial which is also reflecting light.
 * Created by David Derichs on 25.06.2015.
 * @author David Derichs
 */
public class ReflectiveMaterial extends Material{

    public Color diffuse;
    public Color specular;
    public int exponent;
    public Color reflection;

    /**
     * Initiates the Reflective Material using the given values.
     * @param diffuse Color of the diffuse reflection
     * @param specular Color of the specular reflection
     * @param exponent Determines how small the glare point actually is (the bigger the exponent the smaller the point)
     * @param reflection Color of the reflection of the current Hit point
     */
    public ReflectiveMaterial(Color diffuse, Color specular, int exponent, Color reflection){
        this.diffuse=diffuse;
        this.specular=specular;
        this.exponent=exponent;
        this.reflection=reflection;
    }
    @Override
    public Color colorFor(Hit hit, World world) {

        // Normal of the hit object
        Normal3 n = hit.n;
        // Vector pointing to the viewers Position from the intersection point given by the hit object.
        Vector3 e = hit.ray.origin.sub(hit.ray.at(hit.t)).normalized();
        // Setting up Variables cd (color diffus), ca (color ambient), cs (color specular) and c (return value "Color").
        Color cd = this.diffuse;
        Color ca = world.ambientLight;
        Color cs = this.specular;
        Color c = cd.mul(ca);

        // Every source of light is now checked.
        // Whenever the light illuminates the interception point of the hit object the color is calculated and added to c.
        for (int i=0; i<world.lights.size(); i++){
            Light currentLight = world.lights.get(i);
            // if the Point is illuminated by the current light source, then the color is added.
            if(currentLight.illuminates(hit.ray.at(hit.t))){
                // Vector pointing to the light source
                Point3 bla = hit.ray.at(hit.t);
                Vector3 l = currentLight.directionFrom(hit.ray.at(hit.t)).normalized();
                // Vector which is reflected by the Material using the Normal n
                Vector3 rn = l.reflectedOn(n);
                // Color of the current Light (cl)
                Color cl = currentLight.color;
                // Sum of all the light generated colors
                Color phongColor = ((cd.mul(cl)).mul(Math.max(0, n.dot(l)))).add(cs.mul(cl).mul(Math.pow((Math.max(0,e.dot(rn))), exponent)));
                // Color is added to the return value
                c = c.add(phongColor);
            }
        }
        return c;
    }
}
