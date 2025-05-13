package umbcs680.hw10;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PriceComparatorTest {

    @Test
    public void testCompareCheaperFirst() {
        Car cheaper = new Car("Model A", 2020, 5000, 20000.0);
        Car expensive = new Car("Model B", 2021, 3000, 30000.0);
        PriceComparator comparator = new PriceComparator();

        assertTrue(comparator.compare(cheaper, expensive) < 0);
    }

    @Test
    public void testCompareMoreExpensiveFirst() {
        Car cheaper = new Car("Model A", 2020, 5000, 20000.0);
        Car expensive = new Car("Model B", 2021, 3000, 30000.0);
        PriceComparator comparator = new PriceComparator();

        assertTrue(comparator.compare(expensive, cheaper) > 0);
    }

    @Test
    public void testCompareEqualPrices() {
        Car car1 = new Car("Model A", 2020, 5000, 25000.0);
        Car car2 = new Car("Model B", 2021, 3000, 25000.0);
        PriceComparator comparator = new PriceComparator();

        assertEquals(0, comparator.compare(car1, car2));
    }
}
