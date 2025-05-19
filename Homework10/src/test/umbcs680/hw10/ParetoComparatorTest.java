package umbcs680.hw10;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ParetoComparatorTest {

    @Test
    public void testParetoComparison() {
        Car car1 = new Car("Corolla", 2015, 50000, 12000.0);
        Car car2 = new Car("Civic", 2018, 40000, 14000.0);
        Car car3 = new Car("Focus", 2016, 60000, 11000.0);

        List<Car> usedCars = Arrays.asList(car1, car2, car3);

        for (Car car : usedCars) {
            car.setDominationCount(usedCars);
        }

        Collections.sort(usedCars, new ParetoComparator());

        int first = usedCars.get(0).getDominationCount();
        int second = usedCars.get(1).getDominationCount();
        int third = usedCars.get(2).getDominationCount();

        assertTrue(first <= second);
        assertTrue(second <= third);
    }
}
