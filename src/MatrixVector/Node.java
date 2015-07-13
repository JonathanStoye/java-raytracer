package MatrixVector;

import Geometry.Geometry;
import Material.LambertMaterial;
import Material.SingleColorMaterial;
import MatrixVector.Transform;
import Scene.Color;
import Scene.Hit;
import Scene.Ray;
import java.util.List;

/**
 * Class is used to simulate a scene-graph
 * Created by dave_digger on 13.07.2015.
 */
public class Node extends Geometry{

    public final Transform transformationObject;
    public final List<Geometry> geometryList;

    /**
     * Initiales a so called scenegraph
     * @param transformationObject This object is used to transformate rays
     * @param geometryList List of geometric objects
     */
    public Node (Transform transformationObject, List<Geometry> geometryList){
        super (new LambertMaterial(new Color(1.0, 1.0, 1.0)));
        this.transformationObject=transformationObject;
        this.geometryList=geometryList;
    }


    @Override
    public Hit hit(Ray ray) {

        Hit cachedHit = null;
        Hit currentHit = null;
        Ray transformedRay = transformationObject.multiplicate(ray);

        /**
         * Now calling hit methods of all containing geometrical objects.
         */
        for (Geometry geometry : geometryList) {
            currentHit = geometry.hit(transformedRay);

            /**
             * This is in case that the current hit is smaller than the previous one.
             */
            if (currentHit != null && cachedHit != null) {
                if (currentHit.t < cachedHit.t) {
                    cachedHit = currentHit;
                    continue;
                }
            }
            /**
             * This is in case, this is the first hit recognised
             */
            if (currentHit != null) {
                cachedHit = currentHit;
            }
        }

        /**
         * Creating new Hit-object with the same t but with untransformated Ray.
         * I Hobe that the calculation of the Normal is right. Was not sure :)
         */
        if (cachedHit != null) {
            return new Hit(cachedHit.t, ray, cachedHit.geo, transformationObject.multiplicate(cachedHit.n));
        }

        return null;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((geometryList == null) ? 0 : geometryList.hashCode());
        result = prime * result + ((transformationObject == null) ? 0 : transformationObject.hashCode());
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
        Node other = (Node) obj;
        if (geometryList == null) {
            if (other.geometryList != null)
                return false;
        } else if (!geometryList.equals(other.geometryList))
            return false;
        if (transformationObject == null) {
            if (other.transformationObject != null)
                return false;
        } else if (!transformationObject.equals(other.transformationObject))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Node [geolist = " + geometryList + ", trans = " + transformationObject + "]";
    }


}
