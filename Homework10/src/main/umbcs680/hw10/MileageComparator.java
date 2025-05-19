package umbcs680.hw10;

import java.util.Comparator;


public class MileageComparator implements Comparator<Car> {
    @Override
    public int compare(Car car1, Car car2) {
        return Integer.compare(car1.getMileage(), car2.getMileage());
    }
}
