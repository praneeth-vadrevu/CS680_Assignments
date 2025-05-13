package umbcs680.hw10;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class YearComparatorTest {

    // Test newer car is considered greater
    @Test
    public void testCompareNewerFirst() {
        Car newer = new Car("Model X", 2022, 1000, 30000.0);
        Car older = new Car("Model Y", 2019, 15000, 20000.0);
        YearComparator comparator = new YearComparator();

        assertTrue(comparator.compare(newer, older) > 0);
    }

    // Test older car is considered lesser
    @Test
    public void testCompareOlderFirst() {
        Car newer = new Car("Model X", 2022, 1000, 30000.0);
        Car older = new Car("Model Y", 2019, 15000, 20000.0);
        YearComparator comparator = new YearComparator();

        assertTrue(comparator.compare(older, newer) < 0);
    }

    // Test cars with equal years return 0
    @Test
    public void testCompareEqualYears() {
        Car car1 = new Car("Model A", 2020, 5000, 25000.0);
        Car car2 = new Car("Model B", 2020, 3000, 27000.0);
        YearComparator comparator = new YearComparator();

        assertEquals(0, comparator.compare(car1, car2));
    }

    // Getter and Setter Tests for Car
    @Test
    public void testGetModel() {
        Car car = new Car("TestModel", 2020, 10000, 20000.0);
        assertEquals("TestModel", car.getModel());
    }

    @Test
    public void testGetYear() {
        Car car = new Car("TestModel", 2020, 10000, 20000.0);
        assertEquals(2020, car.getYear());
    }

    @Test
    public void testGetMileage() {
        Car car = new Car("TestModel", 2020, 10000, 20000.0);
        assertEquals(10000, car.getMileage());
    }

    @Test
    public void testGetPrice() {
        Car car = new Car("TestModel", 2020, 10000, 20000.0);
        assertEquals(20000.0, car.getPrice());
    }

    @Test
    public void testDominationCountSetterGetter() {
        Car car = new Car("TestModel", 2020, 10000, 20000.0);
        car.setDominationCount(3);
        assertEquals(3, car.getDominationCount());
    }
}
