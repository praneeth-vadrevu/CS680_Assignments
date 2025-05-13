package umbcs680.hw10;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class ParetoComparatorTest {

    @Test
    public void testParetoComparison() {
        Car car1 = new Car ("Corolla", 2015, 50000, 12000.0);
        Car car2 = new Car( "Civic", 2018, 40000, 14000.0);
        Car car3 = new Car( "Focus", 2016, 60000, 11000.0);

        List<Car> usedCars = Arrays.asList(car1, car2, car3);

        // Set domination counts before sorting
        for (Car car : usedCars) {
            int dominationCount = 0;
            for (Car other : usedCars) {
                if (car == other) continue;
                if (new ParetoComparator().compare(other, car) > 0) {
                    dominationCount++;
                }
            }
            car.setDominationCount(dominationCount);
        }

        // Sort by domination count ascending (lower is better)
        Collections.sort(usedCars, (c1, c2) -> Integer.compare(c1.getDominationCount(), c2.getDominationCount()));

        // Assert the order after sorting
        assertTrue(usedCars.get(0).getDominationCount() <= usedCars.get(1).getDominationCount());
        assertTrue(usedCars.get(1).getDominationCount() <= usedCars.get(2).getDominationCount());
    }
}
