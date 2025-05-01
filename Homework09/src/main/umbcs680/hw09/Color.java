public class Color {
    private int redScale;
    private int greenScale;
    private int blueScale;

    public Color(int redScale, int greenScale, int blueScale) {
        this.redScale = redScale;
        this.blueScale = blueScale;
        this.greenScale = greenScale;
    }

    public int getRedScale() {
        return redScale;
    }

    public double getGreenScale() {
        return greenScale;
    }

    public double getBlueScale() {
        return blueScale;
    }


}
