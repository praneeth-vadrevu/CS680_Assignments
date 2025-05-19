package umbcs680.hw12;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CarSortingTest {

    private List<Car> cars;

    @BeforeEach
    public void setUp() {
        cars = new ArrayList<>();
        cars.add(new Car("Toyota", 2020, 15000, 25000));
        cars.add(new Car("Honda", 2018, 12000, 30000));
        cars.add(new Car("Ford", 2022, 18000, 20000));
    }

    @Test
    public void sortByYearTest() {
        Collections.sort(cars, Comparator.comparing(Car::getYear));
        assertEquals(2018, cars.get(0).getYear());
        assertEquals(2022, cars.get(2).getYear());
    }

    @Test
    public void sortByPriceTest() {
        Collections.sort(cars, Comparator.comparing(Car::getPrice));
        assertEquals(20000, cars.get(0).getPrice());
        assertEquals(30000, cars.get(2).getPrice());
    }

    @Test
    public void sortByMileageTest() {
        Collections.sort(cars, Comparator.comparing(Car::getMileage));
        assertEquals(12000, cars.get(0).getMileage());
        assertEquals(18000, cars.get(2).getMileage());
    }

    @Test
    public void sortByModelTest() {
        Collections.sort(cars, Comparator.comparing(Car::getModel));
        assertEquals("Ford", cars.get(0).getModel());
        assertEquals("Toyota", cars.get(2).getModel());
    }

    @Test
    public void sortByParetoDominanceTest() {
        for (Car car : cars) {
            car.setDominationCount(cars);
        }
        Collections.sort(cars, Comparator.comparing(Car::getDominationCount));

        int first = cars.get(0).getDominationCount();
        int second = cars.get(1).getDominationCount();
        int third = cars.get(2).getDominationCount();

        assertTrue(first <= second && second <= third);
    }
}
