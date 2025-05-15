package umbcs680.hw13;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ImageTransformationsUsingLETest {

    @Test
    public void testGrayScalerWithPureBlack() {
        Color black = new Color(0, 0, 0);
        Color result = ImageTransformationsUsingLE.grayScaler.adjust(black);

        assertEquals(0, result.getRedScale(), "Expected grayscale of black to be black");
        assertEquals(0, result.getGreenScale());
        assertEquals(0, result.getBlueScale());
    }

    @Test
    public void testGrayScalerWithPureWhite() {
        Color white = new Color(255, 255, 255);
        Color result = ImageTransformationsUsingLE.grayScaler.adjust(white);

        assertEquals(255, result.getRedScale(), "Expected grayscale of white to be white");
        assertEquals(255, result.getGreenScale());
        assertEquals(255, result.getBlueScale());
    }

    @Test
    public void testGrayScalerWithMixedColor() {
        Color color = new Color(60, 120, 180);  // Average should be 120
        Color result = ImageTransformationsUsingLE.grayScaler.adjust(color);

        assertEquals(120, result.getRedScale(), "Expected average of 60, 120, 180 to be 120");
        assertEquals(120, result.getGreenScale());
        assertEquals(120, result.getBlueScale());
    }



    @Test
    public void testBinarizerWithLowGray() {
        Color darkColor = new Color(50, 50, 50);  // Average is 50 < 125
        Color result = ImageTransformationsUsingLE.binarizer.adjust(darkColor);

        assertEquals(0, result.getRedScale(), "Expected dark color to be binarized to black");
        assertEquals(0, result.getGreenScale());
        assertEquals(0, result.getBlueScale());
    }

    @Test
    public void testBinarizerWithHighGray() {
        Color brightColor = new Color(200, 200, 200);  // Average is 200 > 125
        Color result = ImageTransformationsUsingLE.binarizer.adjust(brightColor);

        assertEquals(255, result.getRedScale(), "Expected bright color to be binarized to white");
        assertEquals(255, result.getGreenScale());
        assertEquals(255, result.getBlueScale());
    }

    @Test
    public void testBinarizerWithEdgeGrayExactly125() {
        Color edgeColor = new Color(125, 125, 125);  // Exactly 125, should be black as per <= rule
        Color result = ImageTransformationsUsingLE.binarizer.adjust(edgeColor);

        assertEquals(0, result.getRedScale(), "Expected edge color 125 to be binarized to black");
        assertEquals(0, result.getGreenScale());
        assertEquals(0, result.getBlueScale());
    }

    @Test
    public void testBinarizerWithEdgeGray126() {
        Color edgeColor = new Color(126, 126, 126);  // Just above 125, should be white
        Color result = ImageTransformationsUsingLE.binarizer.adjust(edgeColor);

        assertEquals(255, result.getRedScale(), "Expected edge color 126 to be binarized to white");
        assertEquals(255, result.getGreenScale());
        assertEquals(255, result.getBlueScale());
    }
}
