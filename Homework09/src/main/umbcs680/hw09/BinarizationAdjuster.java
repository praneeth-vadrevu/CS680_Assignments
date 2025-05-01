public class BinarizeAdjuster implements ColorAdjuster {

    @Override
    public Color adjust(Color color) {
        int red = color.getRedScale();
        int green = color.getGreenScale();
        int blue = color.getBlueScale();

        // Convert to grayscale using simple average
        int gray = (red + green + blue) / 3;

        if (gray <= 125) {
            return new Color(0, 0, 0); // Black
        } else {
            return new Color(255, 255, 255); // White
        }
    }
}
