package Scene;

/**
 * Class RecursiveTracer is used to describe rays that are reflected multiple Times.
 * The Value of recursion sets a limit to the recursive function trace().
 * Created by David Derichs on 25.06.2015.
 * @author David Derichs
 */
public class RecursiveTracer {
    public final World world;
    public int recursion;
    /**
     * Initiates the Rekursion object RecursiveTracer.
     * @param world The world which is analysed.
     * @param recursion Some kind of limit. Determindes how many reflections are calculated and when the recursion shall stop.
     */
    public RecursiveTracer(World world, int recursion){
        this.world=world;
        this.recursion=recursion;
    }
    // the Method trace() is called in the ReflectiveMaterial class in the Method colorFor().
    // As long as recursion is not zero. More Rays will be traced.
    // Otherwise the background color of the world is returned.
    // The method colorFor is reinitiated here again to find out the color for the current Ray.
    public Color trace(Ray r) {
        if (recursion < 1) {
            return world.backgroundColor;
        }
        if (world.hit(r) == null) {
            return world.backgroundColor;
        }
        this.recursion=this.recursion-1;
        return world.hit(r).geo.material.colorFor(world.hit(r), world, this);
    }

    /**
     * Overriding HasCode(), equals() and toString() methods.
     * @return
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + recursion;
        result = prime * result + ((world == null) ? 0 : world.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RecursiveTracer other = (RecursiveTracer) obj;
        if (recursion != other.recursion)
            return false;
        if (world == null) {
            if (other.world != null)
                return false;
        } else if (!world.equals(other.world))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Tracer [world = " + world + ", recursion = " + recursion + "]";
    }

}
