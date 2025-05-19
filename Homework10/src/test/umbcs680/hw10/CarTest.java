package umbcs680.hw10;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    @Test
    public void testModelGetter() {
        Car car = new Car("Corolla", 2015, 50000, 15000.0);
        assertEquals("Corolla", car.getModel());
    }

    @Test
    public void testYearGetter() {
        Car car = new Car("Corolla", 2015, 50000, 15000.0);
        assertEquals(2015, car.getYear());
    }

    @Test
    public void testMileageGetter() {
        Car car = new Car("Corolla", 2015, 50000, 15000.0);
        assertEquals(50000, car.getMileage());
    }

    @Test
    public void testPriceGetter() {
        Car car = new Car("Corolla", 2015, 50000, 15000.0);
        assertEquals(15000.0, car.getPrice());
    }

    @Test
    public void testToString() {
        Car car = new Car("Corolla", 2015, 50000, 15000.0);
        assertEquals("Corolla - Year: 2015, Mileage: 50000, Price: $15000.0", car.toString());
    }
}
