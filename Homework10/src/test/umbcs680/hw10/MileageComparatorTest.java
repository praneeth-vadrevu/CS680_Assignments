package umbcs680.hw10;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MileageComparatorTest {

    @Test
    public void testCompareDifferentMileage() {
        Car car1 = new Car("Car A", 2020, 30000, 15000.0);
        Car car2 = new Car("Car B", 2021, 40000, 16000.0);
        MileageComparator comparator = new MileageComparator();
        assertTrue(comparator.compare(car1, car2) < 0);
    }

    @Test
    public void testCompareSameMileage() {
        Car car1 = new Car("Car A", 2020, 30000, 15000.0);
        Car car2 = new Car("Car B", 2021, 30000, 16000.0);
        MileageComparator comparator = new MileageComparator();
        assertEquals(0, comparator.compare(car1, car2));
    }

    @Test
    public void testCompareHigherMileage() {
        Car car1 = new Car("Car A", 2020, 50000, 15000.0);
        Car car2 = new Car("Car B", 2021, 30000, 16000.0);
        MileageComparator comparator = new MileageComparator();
        assertTrue(comparator.compare(car1, car2) > 0);
    }

    // --- Additional Getter and Setter Coverage ---

    @Test
    public void testMileageGetter() {
        Car car = new Car("Car A", 2020, 30000, 15000.0);
        assertEquals(30000, car.getMileage());
    }

    @Test
    public void testSetAndGetDominationCount() {
        Car car = new Car("Car A", 2020, 30000, 15000.0);
        car.setDominationCount(2);
        assertEquals(2, car.getDominationCount());
    }
}
