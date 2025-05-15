package umbcs680.hw13;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ImagesTest {

    @Test
    public void testTransformWithGrayScalerLambda() {
        Image original = new Image(1, 1);
        original.setColor(0, 0, new Color(120, 150, 180)); // avg = 150

        // Apply grayscale using lambda
        Image result = Images.transform(original, ImageTransformationsUsingLE.grayScaler);

        Color c = result.getColor(0, 0);
        assertEquals(150, c.getRedScale());
        assertEquals(150, c.getGreenScale());
        assertEquals(150, c.getBlueScale());
    }

    @Test
    public void testTransformWithBinarizerLambdaBlack() {
        Image original = new Image(1, 1);
        original.setColor(0, 0, new Color(20, 30, 40)); // avg = 30

        // Apply binarization using lambda
        Image result = Images.transform(original, ImageTransformationsUsingLE.binarizer);

        Color c = result.getColor(0, 0);
        assertEquals(0, c.getRedScale());
        assertEquals(0, c.getGreenScale());
        assertEquals(0, c.getBlueScale());
    }

    @Test
    public void testTransformWithBinarizerLambdaWhite() {
        Image original = new Image(1, 1);
        original.setColor(0, 0, new Color(200, 220, 240)); // avg = 220

        // Apply binarization using lambda
        Image result = Images.transform(original, ImageTransformationsUsingLE.binarizer);

        Color c = result.getColor(0, 0);
        assertEquals(255, c.getRedScale());
        assertEquals(255, c.getGreenScale());
        assertEquals(255, c.getBlueScale());
    }
}
