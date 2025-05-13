package umbcs680.hw10;

import java.util.Comparator;

// Comparator to compare cars based on price
public class PriceComparator implements Comparator<Car> {

    @Override
    public int compare(Car car1, Car car2) {
        return Float.compare(car1.getPrice(), car2.getPrice());
    }
}
