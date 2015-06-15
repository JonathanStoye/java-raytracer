package Material;

import MatrixVector.Normal3;
import MatrixVector.Vector3;
import Scene.*;
import Light.*;


/**
 * Class LambertMaterial describes a perfect diffus reflective object.
 * Created by David on 15.06.15
 * @author David Derichs
 */
public class LambertMaterial extends Material{

    public final Color color;

    /**
     * Initiates the LambertMaterial using the given basic Color.
     * @param color
     */
    public LambertMaterial(Color color){
        this.color=color;
    }

    /**
     * Determines which color a certain hit interception point has concerning the light sources in the room.
     * @param hit Hit object which is analysed
     * @param world World object which is used to get information about the light in the current scene.
     * @return Color of the interception point.
     */
    @Override
    public Color colorFor(Hit hit, World world) {

        // Normal of the hit object
        Normal3 n = hit.n;
        // Setting up Variables cd (color diffus), ca (color ambient) and c (return value "Color").
        Color cd = this.color;
        Color ca = world.ambientLight;
        Color c = cd.mul(ca);

        // Every source of light is now checked.
        // Whenever the light illuminates the interception point of the hit object the color is calculated and added to c.
        for (int i=0; i<world.lights.size(); i++){
            Light currentLight = world.lights.get(i);
            // if the Point is illuminated by the current light source, then the color is added.
            if(currentLight.illuminates(hit.ray.at(hit.t))){
                // Vector pointing to the light source
                Vector3 l = currentLight.directionFrom(hit.ray.at(hit.t)).normalized();
                // Color of the current Light (cl)
                Color cl = currentLight.color;
                // Sum of all the light generated colors
                Color lambertColor = (cd.mul(cl)).mul(Math.max(0, n.dot(l)));
                // Color is added to the return value.
                c = c.add(lambertColor);
            }
        }
        return c;
    }
}
