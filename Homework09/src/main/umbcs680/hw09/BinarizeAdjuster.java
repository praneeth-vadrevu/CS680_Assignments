package umbcs680.hw09;

public class BinarizeAdjuster implements ColorAdjuster {

    @Override
    public Color adjust(Color color) {
        int red = (int) color.getRedScale();
        int green = (int) color.getGreenScale();
        int blue =(int) color.getBlueScale();

        // Convert to grayscale using simple average
        int gray = (red + green + blue) / 3;

        if (gray <= 125) {
            return new Color(0, 0, 0); // Black
        } else {
            return new Color(255, 255, 255); // White
        }
    }
}