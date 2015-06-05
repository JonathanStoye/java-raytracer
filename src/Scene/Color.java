package Scene;

import Utilities.Debugging;

/**
 * Created by Jonathan on 05.06.15.
 */
public class Color {
    double r;
    double g;
    double b;

    public Color (double r, double g, double b) {
        this.r = r;
        this.g = r;
        this.b = r;
    }

    /**
     * adds a given color to this by adding each channel of the given color to the according channel of this
     * @param color color to add to this
     */
    public void add(Color color) {
        this.r += color.r;
        this.g += color.g;
        this.b += color.b;
    }

    /**
     * subtracts a given color to this by subtracting each channel of the given color to the according channel of this
     * @param color color to subtract from this
     */
    public void sub(Color color) {
        this.r -= color.r;
        this.g -= color.g;
        this.b -= color.b;
    }

    /**
     * mulitplies given color with this by multiplying each channel of the given to with the according channel of this
     * @param color color to multiply this with
     */
    public void mul(Color color) {
        this.r = this.r * color.r;
        this.g = this.g * color.g;
        this.b = this.b * color.b;
    }

    /**
     * mulitplies given skalar with this by multiplying each channel of the given to with the given skalar
     * @param skalar given skalar to multiply this with
     */
    public void mul(double skalar) {
        this.r = this.r * skalar;
        this.g = this.g * skalar;
        this.b = this.b * skalar;
    }

    /**
     * test the add() function
     */
    private static void testAdd() {
        Color color = new Color(0.0, 0.0, 0.0);
        Color addColor = new Color(0.3, 0.3, 0.3);
        color.add(addColor);
        if (color.equals(addColor)) {
            Debugging.log("test Color.add() successful");
        }
        else {
            Debugging.log("Error in Color.add()");
        }
    }

    /**
     * test the sub() function
     */
    private static void testSub() {
        Color color = new Color(0.6, 0.6, 0.6);
        Color subColor = new Color(0.3, 0.3, 0.3);
        color.sub(subColor);
        if (color.equals(subColor)) {
            Debugging.log("test Color.sub() successful");
        }
        else {
            Debugging.log("Error in Color.sub()");
        }
    }

    /**
     * test the mul() function with a color
     */
    private static void testMul() {
        Color color = new Color(0.2, 0.2, 0.2);
        Color mulColor = new Color(0.3, 0.3, 0.3);
        Color sumColor = new Color(0.06, 0.06, 0.06);

        color.mul(mulColor);
        if (color.equals(sumColor)) {
            Debugging.log("test Color.mul() with color successful");
        }
        else {
            Debugging.log("Error in Color.mul() with color");
        }
    }

    /**
     * test the mul() function with a skalar
     */
    private static void testMulS() {
        Color color = new Color(0.2, 0.2, 0.2);
        double skalar = 0.3;
        Color sumColor = new Color(0.06, 0.06, 0.06);

        color.mul(skalar);
        if (color.equals(sumColor)) {
            Debugging.log("test Color.mul() with skalar successful");
        }
        else {
            Debugging.log("Error in Color.mul() with skalar");
        }
    }

    /**
     * tests funcionality of Color class
     */
    public static void test() {
        testAdd();
        testSub();
        testMul();
        testMulS();
    }

    @Override
    public boolean equals(Object obj) {
        final Color color = (Color)obj;
        if (this.r == color.r && this.g == color.g && this.b == color.b)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        final String s = "color("+this.r+", "+this.g+", "+this.b+")";
        return s;
    }
}
