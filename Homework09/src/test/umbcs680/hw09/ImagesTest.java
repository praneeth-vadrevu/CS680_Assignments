package umbcs680.hw09;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ImagesTest {

    @Test
    public void testTransformWithGrayScaleAdjuster() {
        Image original = new Image(1, 1);
        original.setColor(0, 0, new Color(120, 150, 180)); // avg = 150

        Image result = Images.transform(original, new GrayScaleAdjuster());

        Color c = result.getColor(0, 0);
        assertEquals(150, c.getRedScale());
        assertEquals(150, c.getGreenScale());
        assertEquals(150, c.getBlueScale());
    }

    @Test
    public void testTransformWithBinarizeAdjusterBlack() {
        Image original = new Image(1, 1);
        original.setColor(0, 0, new Color(20, 30, 40)); // avg = 30

        Image result = Images.transform(original, new BinarizeAdjuster());

        Color c = result.getColor(0, 0);
        assertEquals(0, c.getRedScale());
        assertEquals(0, c.getGreenScale());
        assertEquals(0, c.getBlueScale());
    }

    @Test
    public void testTransformWithBinarizeAdjusterWhite() {
        Image original = new Image(1, 1);
        original.setColor(0, 0, new Color(200, 220, 240)); // avg = 220

        Image result = Images.transform(original, new BinarizeAdjuster());

        Color c = result.getColor(0, 0);
        assertEquals(255, c.getRedScale());
        assertEquals(255, c.getGreenScale());
        assertEquals(255, c.getBlueScale());
    }
}
