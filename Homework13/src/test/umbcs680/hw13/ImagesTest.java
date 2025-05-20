package umbcs680.hw13;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ImagesTest {

    @Test
    public void testTransformWithGrayScalerLambda() {
        Image original = new Image(1, 1);
        original.setColor(0, 0, new Color(120, 150, 180)); // avg = 150

        ColorAdjuster grayScaler = color -> {
            int avg = (int)(color.getRedScale() + color.getGreenScale() + color.getBlueScale()) / 3;
            return new Color(avg, avg, avg);
        };

        Image result = Images.transform(original, grayScaler);
        Color c = result.getColor(0, 0);
        assertEquals(150, c.getRedScale());
        assertEquals(150, c.getGreenScale());
        assertEquals(150, c.getBlueScale());
    }

    @Test
    public void testTransformWithBinarizerLambdaBlack() {
        Image original = new Image(1, 1);
        original.setColor(0, 0, new Color(20, 30, 40)); // avg = 30

        ColorAdjuster binarizer = color -> {
            int avg = (int)(color.getRedScale() + color.getGreenScale() + color.getBlueScale()) / 3;
            int value = (avg <= 125) ? 0 : 255;
            return new Color(value, value, value);
        };

        Image result = Images.transform(original, binarizer);
        Color c = result.getColor(0, 0);
        assertEquals(0, c.getRedScale());
        assertEquals(0, c.getGreenScale());
        assertEquals(0, c.getBlueScale());
    }

    @Test
    public void testTransformWithBinarizerLambdaWhite() {
        Image original = new Image(1, 1);
        original.setColor(0, 0, new Color(200, 220, 240)); // avg = 220

        ColorAdjuster binarizer = color -> {
            int avg = (int) (color.getRedScale() + color.getGreenScale() + color.getBlueScale()) / 3;
            int value = (avg <= 125) ? 0 : 255;
            return new Color(value, value, value);
        };

        Image result = Images.transform(original, binarizer);
        Color c = result.getColor(0, 0);
        assertEquals(255, c.getRedScale());
        assertEquals(255, c.getGreenScale());
        assertEquals(255, c.getBlueScale());
    }

    @Test
    public void testGrayScalerWithPureBlack() {
        Color black = new Color(0, 0, 0);
        ColorAdjuster grayScaler = color -> {
            int avg = (int) (color.getRedScale() + color.getGreenScale() + color.getBlueScale()) / 3;
            return new Color(avg, avg, avg);
        };
        Color result = grayScaler.adjust(black);
        assertEquals(0, result.getRedScale());
        assertEquals(0, result.getGreenScale());
        assertEquals(0, result.getBlueScale());
    }

    @Test
    public void testGrayScalerWithPureWhite() {
        Color white = new Color(255, 255, 255);
        ColorAdjuster grayScaler = color -> {
            int avg = (int) (color.getRedScale() + color.getGreenScale() + color.getBlueScale()) / 3;
            return new Color(avg, avg, avg);
        };
        Color result = grayScaler.adjust(white);
        assertEquals(255, result.getRedScale());
        assertEquals(255, result.getGreenScale());
        assertEquals(255, result.getBlueScale());
    }

    @Test
    public void testGrayScalerWithMixedColor() {
        Color color = new Color(60, 120, 180);  // avg = 120
        ColorAdjuster grayScaler = c -> {
            int avg = (int) (c.getRedScale() + c.getGreenScale() + c.getBlueScale()) / 3;
            return new Color(avg, avg, avg);
        };
        Color result = grayScaler.adjust(color);
        assertEquals(120, result.getRedScale());
        assertEquals(120, result.getGreenScale());
        assertEquals(120, result.getBlueScale());
    }

    @Test
    public void testBinarizerWithLowGray() {
        Color darkColor = new Color(50, 50, 50);
        ColorAdjuster binarizer = color -> {
            int avg = (int) (color.getRedScale() + color.getGreenScale() + color.getBlueScale()) / 3;
            int value = (avg <= 125) ? 0 : 255;
            return new Color(value, value, value);
        };
        Color result = binarizer.adjust(darkColor);
        assertEquals(0, result.getRedScale());
        assertEquals(0, result.getGreenScale());
        assertEquals(0, result.getBlueScale());
    }

    @Test
    public void testBinarizerWithHighGray() {
        Color brightColor = new Color(200, 200, 200);
        ColorAdjuster binarizer = color -> {
            int avg = (int) (color.getRedScale() + color.getGreenScale() + color.getBlueScale()) / 3;
            int value = (avg <= 125) ? 0 : 255;
            return new Color(value, value, value);
        };
        Color result = binarizer.adjust(brightColor);
        assertEquals(255, result.getRedScale());
        assertEquals(255, result.getGreenScale());
        assertEquals(255, result.getBlueScale());
    }

    @Test
    public void testBinarizerWithEdgeGrayExactly125() {
        Color edgeColor = new Color(125, 125, 125);
        ColorAdjuster binarizer = color -> {
            int avg = (int) (color.getRedScale() + color.getGreenScale() + color.getBlueScale()) / 3;
            int value = (avg <= 125) ? 0 : 255;
            return new Color(value, value, value);
        };
        Color result = binarizer.adjust(edgeColor);
        assertEquals(0, result.getRedScale());
        assertEquals(0, result.getGreenScale());
        assertEquals(0, result.getBlueScale());
    }

    @Test
    public void testBinarizerWithEdgeGray126() {
        Color edgeColor = new Color(126, 126, 126);
        ColorAdjuster binarizer = color -> {
            int avg = (int) (color.getRedScale() + color.getGreenScale() + color.getBlueScale()) / 3;
            int value = (avg <= 125) ? 0 : 255;
            return new Color(value, value, value);
        };
        Color result = binarizer.adjust(edgeColor);
        assertEquals(255, result.getRedScale());
        assertEquals(255, result.getGreenScale());
        assertEquals(255, result.getBlueScale());
    }
}
