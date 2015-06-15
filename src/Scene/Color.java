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
        this.g = g;
        this.b = b;

        if(this.r < 0)
            this.r = 0;

        if(this.g < 0)
            this.g = 0;

        if(this.b < 0)
            this.b = 0;


        if(this.r > 1)
            this.r = 1;

        if(this.g > 1)
            this.g = 1;

        if(this.b > 1)
            this.b = 1;
    }

    /**
     * adds a given color to this by adding each channel of the given color to the according channel of this
     * @param color color to add to this
     */
    public Color add(Color color) {
        final Color finalColor = null;
        finalColor.r = this.r + color.r;
        finalColor.g = this.g + color.g;
        finalColor.b = this.b + color.b;
        return finalColor;
    }

    /**
     * subtracts a given color to this by subtracting each channel of the given color to the according channel of this
     * @param color color to subtract from this
     */
    public Color sub(Color color) {
        final Color finalColor = null;
        finalColor.r = this.r - color.r;
        finalColor.g = this.g - color.g;
        finalColor.b = this.b - color.b;
        return finalColor;
    }

    /**
     * mulitplies given color with this by multiplying each channel of the given to with the according channel of this
     * @param color color to multiply this with
     */
    public Color mul(Color color) {

       // Old (was not working because of NullPointerException):
//        final Color finalColor = null;
//        finalColor.r = this.r * color.r;
//        finalColor.g = this.g * color.g;
//        finalColor.b = this.b * color.b;

        // new (is working)
        final Color finalColor = new Color((this.r * color.r),(this.g * color.g),(this.b * color.b));

        return finalColor;
    }

    /**
     * mulitplies given skalar with this by multiplying each channel of the given to with the given skalar
     * @param skalar given skalar to multiply this with
     */
    public Color mul(double skalar) {
        final Color finalColor = null;
        finalColor.r = this.r * skalar;
        finalColor.g = this.g * skalar;
        finalColor.b = this.b * skalar;
        return finalColor;
    }

    /**
     * return the color as a hexadecimal value
     * @return the hexadecimal color value
     */
    public int asHex() {
        int red = (int) (r * 0xff);
        int green = (int) (g * 0xff);
        int blue = (int) (b * 0xff);
        int rgb = ((red & 0xff) << 16) | ((green & 0xff) << 8) | (blue & 0xff);
        return rgb;
    }

    private static void testAsHex() {
        final Color color = new Color(1.0, 0, 0);
        Debugging.log("color: " + color.asHex());
    }

    /**
     * test the add() function
     */
    private static void testAdd() {
        final Color color = new Color(0.0, 0.0, 0.0);
        final Color addColor = new Color(0.3, 0.3, 0.3);
        final Color sumColor = color.add(addColor);
        if (sumColor.equals(addColor)) {
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
        final Color color = new Color(0.6, 0.6, 0.6);
        final Color subColor = new Color(0.3, 0.3, 0.3);
        final Color sumColor = color.sub(subColor);
        if (sumColor.equals(subColor)) {
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
        final Color color = new Color(0.2, 0.2, 0.2);
        final Color mulColor = new Color(0.3, 0.3, 0.3);
        final Color expectedSumColor = new Color(0.06, 0.06, 0.06);
        final Color sumColor = color.mul(mulColor);
        if (sumColor.equals(expectedSumColor)) {
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
        final Color color = new Color(0.2, 0.2, 0.2);
        final double skalar = 0.3;
        final Color expectedSumColor = new Color(0.06, 0.06, 0.06);
        final Color sumColor = color.mul(skalar);
        if (sumColor.equals(expectedSumColor)) {
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
        testAsHex();
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
