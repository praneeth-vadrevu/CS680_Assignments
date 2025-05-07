package umbcs680.hw09;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BinarizeAdjusterTest {

    @Test
    public void testAdjustToBlack() {
        Color input = new Color(50, 60, 70); // avg = 60
        BinarizeAdjuster adjuster = new BinarizeAdjuster();
        Color output = adjuster.adjust(input);

        assertEquals(0, output.getRedScale());
        assertEquals(0, output.getGreenScale());
        assertEquals(0, output.getBlueScale());
    }

    @Test
    public void testAdjustToWhite() {
        Color input = new Color(200, 210, 220); // avg = 210
        BinarizeAdjuster adjuster = new BinarizeAdjuster();
        Color output = adjuster.adjust(input);

        assertEquals(255, output.getRedScale());
        assertEquals(255, output.getGreenScale());
        assertEquals(255, output.getBlueScale());
    }
}
