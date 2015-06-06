package Geometry;

import MatrixVector.*;
import Scene.*;
import java.util.ArrayList;

/**
 * Created by Jonathan on 05.06.15.
 * implemented by David on 06.06.15
 * @author d.derichs
 */
public class AxisAlignedBox extends Geometry{
    public static Point3 lbf;
    public static Point3 run;

    /**
     * Initializes an AxisAlignedBox who's lines are always orthogonal to the axes of the current coordinate system.
     * In order to describe the Box, we need to create a few Planes that can be used to describe the box.
     * @param lbf LEFT BELOW FAR edge of the box (linke, untere, ferne Ecke)
     * @param run RIGT UPPER NEAR edge of the box (rechte, obere, nahe Ecke)
     * @param color Color of the Box
     */
    public AxisAlignedBox(Point3 lbf, Point3 run, Color color){
        super(color);
        this.lbf=lbf;
        this.run=run;
    }

    @Override
    public Hit hit(Ray ray){
        // A box consists of six planes (dices , ger: Würfel).
        // Therefore six planes have to be declared.
        // in order to describe these Planes we just need a known Point and a normal vector.
        // the Points lbf and run are given
        // the normal-vector are defined by basic mathematics
        Plane[] axisAlignedBoxPlanes = new Plane[]{
                new Plane(run, new Normal3(0.0,1.0,0.0), this.color),
                new Plane(run, new Normal3(1.0,0.0,0.0), this.color),
                new Plane(run, new Normal3(0.0,0.0,1.0), this.color),
                new Plane(lbf, new Normal3(-1.0,0.0,0.0), this.color),
                new Plane(lbf, new Normal3(0.0,-1.0,0.0), this.color),
                new Plane(lbf, new Normal3(0.0,0.0,-1.0), this.color)
        };

        // Now we need to find out which planes are hit by the given Ray.
        // Therefore an ArrayList is declared in order to find out all hits of the planes
        ArrayList<Hit> foundHits = new ArrayList<Hit>();

        // now all the planes are analysed using the given Ray.
        // every hit is stored in foundHits
        for (Plane plane : axisAlignedBoxPlanes){
            if (plane.hit(ray)!=null) foundHits.add(plane.hit(ray));
        }

        // We need to store all visible hits (intersections)
        ArrayList<Hit> visibleHits = new ArrayList<>();

        // Now we have to analyse the found hits
        for (Hit hit : foundHits){

            // The Geometric informatioon of the hit is used to create a new Plane, which represents a potential visible
            // part of the Box
            // this temporary plane is used to calculate visible intersections
            final Plane temporaryPlane = (Plane)hit.geo;

            //This is the Point, where the current Plane is hit by the viewers ray.
            Point3 intersectionPoint = ray.at(hit.t);

            // Now we check if the currently analysed Plane is parallel to the z-axis
            // Or if the normal of the current Plane is equal to the x-axis-normal-vector
            // We also Check that for the x-axis-normal-vector pointing "to the left"
            if(temporaryPlane.n.equals(new Normal3(1.0, 0.0, 0.0)) || temporaryPlane.n.equals(new Normal3(-1.0, 0.0, 0.0))){
                // Now we have to check, if the current intersectionPoint is actually visible for the viewer.
                // This is necessary because there are always two Planes parralel to the z-axis
                // We use the given Box-Points-values to check this.
                if (lbf.y <= intersectionPoint.y && intersectionPoint.y <= run.y && lbf.z <= intersectionPoint.z && intersectionPoint.z <= run.z) {
                    visibleHits.add(hit);
                }
            }

            // Now we check if the currently analysed Plane is parallel to the x-axis
            // Or if the normal of the current Plane is equal to the y-axis-normal-vector
            // We also Check that for the y-axis-normal-vector pointing "down"
            if (temporaryPlane.n.equals(new Normal3(0.0, 1.0, 0.0)) || temporaryPlane.n.equals(new Normal3(0.0, -1.0, 0.0))){
                // Now we have to check, if the current intersectionPoint is actually visible for the viewer.
                // This is necessary because there are always two Planes parralel to the x-axis.
                // We use the given Box-Points-values to check this
                if (lbf.x <= intersectionPoint.x && intersectionPoint.x <= run.x && lbf.z <= intersectionPoint.z && intersectionPoint.z <= run.z){
                    visibleHits.add(hit);
                }
            }

            // Now we check if the currently analysed Plane is parallel to the y-axis
            // Or if the normal of the current Plane is equal to the z-axis-normal-vector
            // We also Check that for the z-axis-normal-vector pointing "backwards"
            if (temporaryPlane.n.equals(new Normal3(0.0, 0.0, 1.0)) || temporaryPlane.n.equals(new Normal3(0.0, 0.0, -1.0))){
                // Now we have to check, if the current intersectionPoint is actually visible for the viewer.
                // This is necessary because there are always two Planes parralel to the y-axis
                // We use the given Box-Points-values to check this
                if (lbf.x <= intersectionPoint.x && intersectionPoint.x <= run.x && lbf.y <= intersectionPoint.y && intersectionPoint.y <= run.y){
                    visibleHits.add(hit);
                }
            }
        }

        // Finally we just have to check if there is enough space between the Viewers location and the Box.
        Hit returnValue = null;
        for (Hit hit : visibleHits) {
            if (returnValue == null && hit.t > 0.00000001) {
                returnValue = hit;
                continue;
            }
            if (hit.t > 0.00000001 && hit.t < returnValue.t) {
                returnValue = hit;
            }
        }
        return returnValue;
    }

}
