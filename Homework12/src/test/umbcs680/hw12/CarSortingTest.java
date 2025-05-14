package  umbcs680.hw12;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CarSortingTest{

    private ArrayList<Car> cars;

    @BeforeEach
    public void setUp() {
        cars = new ArrayList<>();
        cars.add(new Car("Toyota", 2020, 15000, 25000));
        cars.add(new Car("Honda", 2018, 12000, 30000));
        cars.add(new Car("Ford", 2022, 18000, 20000));
        cars.add(new Car("Maruti", 2016, 15000, 9000));
        cars.add(new Car("Mahindra", 2024, 20000, 30000));
    }

    @Test
    public void sortByYearAscendingTest() {
        Collections.sort(cars, Comparator.comparing(Car::getYear));
        assertEquals(2016, cars.get(0).getYear());
        assertEquals(2018, cars.get(1).getYear());
        assertEquals(2020, cars.get(2).getYear());
        assertEquals(2022, cars.get(3).getYear());
        assertEquals(2024, cars.get(4).getYear());
    }

    @Test
    public void SortByPriceAscendingTest() {
        Collections.sort(cars, Comparator.comparing(Car::getPrice));
        assertEquals(9000, cars.get(0).getPrice());
        assertEquals(20000, cars.get(1).getPrice());
        assertEquals(25000, cars.get(2).getPrice());
        assertEquals(30000, cars.get(3).getPrice());
        assertEquals(30000, cars.get(4).getPrice());
    }

    @Test
    public void SortByMileageAscendingTest() {
        Collections.sort(cars, Comparator.comparing(Car::getMileage));
        assertEquals(12000, cars.get(0).getMileage());
        assertEquals(15000, cars.get(1).getMileage());
        assertEquals(15000, cars.get(2).getMileage());
        assertEquals(18000, cars.get(3).getMileage());
        assertEquals(20000, cars.get(4).getMileage());
    }

    @Test
    public void SortByModelAscendingTest() {
        Collections.sort(cars, Comparator.comparing(Car::getModel));
        assertEquals("Ford", cars.get(0).getModel());
        assertEquals("Honda", cars.get(1).getModel());
        assertEquals("Mahindra", cars.get(2).getModel());
        assertEquals("Maruti", cars.get(3).getModel());
        assertEquals("Toyota", cars.get(4).getModel());
    }
}
