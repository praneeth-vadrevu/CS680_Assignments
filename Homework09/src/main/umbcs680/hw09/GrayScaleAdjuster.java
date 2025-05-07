package umbcs680.hw09;

public class GrayScaleAdjuster implements ColorAdjuster {

    @Override
    public Color adjust(Color color) {
        int r, g, b;
        r = (int) color.getRedScale();
        g = (int) color.getGreenScale();
        b = (int) color.getBlueScale();

        int avg = (r + g + b) / 3;

        return new Color(avg, avg, avg);
    }
}
