package umbcs680.hw10;

import java.util.Comparator;

public class YearComparator implements Comparator<Car> {
    @Override
    public int compare(Car car1, Car car2) {
        return Integer.compare(car2.getYear(), car1.getYear());
    }
}
