package umbcs680.hw13;

public class Images {

    public static Image transform(Image image, ColorAdjuster adjuster) {
        Image adjusted = new Image(image.getHeight(), image.getWidth());

        for (int y = 0; y < adjusted.getHeight(); y++) {
            for (int x = 0; x < adjusted.getWidth(); x++) {
                adjusted.setColor(x, y,
                        adjuster.adjust(
                                image.getColor(x, y)
                        )
                );
            }
        }
        return adjusted;
    }
}
