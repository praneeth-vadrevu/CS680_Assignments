package umbcs680.hw13;

public class ImageTransformationsUsingLE {

    // Lambda for Grayscale transformation
    public static ColorAdjuster grayScaler = color -> {
        int r = color.getRedScale();
        int g = (int) color.getGreenScale();
        int b = (int) color.getBlueScale();
        int avg = (r + g + b) / 3;
        return new Color(avg, avg, avg);
    };

    // Lambda for Binarization transformation
    public static ColorAdjuster binarizer = color -> {
        int r = color.getRedScale();
        int g = (int) color.getGreenScale();
        int b = (int) color.getBlueScale();
        int avg = (r + g + b) / 3;
        int value = (avg <= 125) ? 0 : 255;
        return new Color(value, value, value);
    };
}
