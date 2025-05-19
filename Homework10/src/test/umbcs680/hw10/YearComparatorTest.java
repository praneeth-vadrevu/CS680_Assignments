package umbcs680.hw10;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class YearComparatorTest {

    @Test
    public void testCompareNewerFirst() {
        Car newer = new Car("Model X", 2022, 1000, 30000.0);
        Car older = new Car("Model Y", 2019, 15000, 20000.0);
        YearComparator comparator = new YearComparator();
        assertTrue(comparator.compare(newer, older) < 0); // Expect negative, newer is "smaller"
    }

    @Test
    public void testCompareOlderFirst() {
        Car newer = new Car("Model X", 2022, 1000, 30000.0);
        Car older = new Car("Model Y", 2019, 15000, 20000.0);
        YearComparator comparator = new YearComparator();
        assertTrue(comparator.compare(older, newer) > 0); // Expect positive, older is "bigger"
    }

    @Test
    public void testCompareEqualYears() {
        Car car1 = new Car("Model A", 2020, 5000, 25000.0);
        Car car2 = new Car("Model B", 2020, 3000, 27000.0);
        YearComparator comparator = new YearComparator();
        assertEquals(0, comparator.compare(car1, car2));
    }
}

