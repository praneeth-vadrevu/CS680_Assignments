package umbcs680.hw10;

import java.util.Comparator;


public class ParetoComparator implements Comparator<Car> {
    @Override
    public int compare(Car c1, Car c2) {
        return Integer.compare(c1.getDominationCount(), c2.getDominationCount());
    }
}
