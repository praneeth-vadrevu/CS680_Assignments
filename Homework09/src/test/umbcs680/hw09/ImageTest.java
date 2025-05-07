package umbcs680.hw09;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ImageTest {

    @Test
    public void testConstructorAndGetters() {
        Image image = new Image(4, 3);
        assertEquals(4, image.getWidth());
        assertEquals(3, image.getHeight());
    }

    @Test
    public void testSetAndGetColor() {
        Image image = new Image(2, 2);
        Color color = new Color(10, 20, 30);
        image.setColor(0, 1, color);
        Color result = image.getColor(0, 1);
        assertSame(color, result);
    }
}
