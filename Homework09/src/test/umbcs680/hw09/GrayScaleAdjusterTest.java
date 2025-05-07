package umbcs680.hw09;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GrayScaleAdjusterTest {

    @Test
    public void testAdjustToGrayScale() {
        Color original = new Color(30, 90, 150); // avg = 90
        GrayScaleAdjuster adjuster = new GrayScaleAdjuster();
        Color gray = adjuster.adjust(original);

        assertEquals(90, gray.getRedScale());
        assertEquals(90, gray.getGreenScale());
        assertEquals(90, gray.getBlueScale());
    }
}
