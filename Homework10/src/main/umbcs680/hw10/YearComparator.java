package umbcs680.hw10;

import java.util.Comparator;

// Comparator to compare cars based on year
public class YearComparator implements Comparator<Car> {

    @Override
    public int compare(Car car1, Car car2) {
        return Integer.compare(car1.getYear(), car2.getYear());
    }
}
