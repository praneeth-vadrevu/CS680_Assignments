package umbcs680.hw12;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Sample car instances
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(new Car("Toyota", 2020, 15000, 25000));
        cars.add(new Car("Honda", 2018, 12000, 30000));
        cars.add(new Car("Ford", 2022, 18000, 20000));
        cars.add(new Car("BMW", 2019, 10000, 35000));

        // Original List
        System.out.println("Original List:");
        cars.forEach(System.out::println);

        // Sort by Year (Ascending)
        System.out.println("\nSorted by Year (Ascending):");
        Collections.sort(cars, (car1, car2) -> car1.getYear() - car2.getYear());
        cars.forEach(System.out::println);

        // Sort by Year (Descending)
        System.out.println("\nSorted by Year (Descending):");
        Collections.sort(cars, (car1, car2) -> car2.getYear() - car1.getYear());
        cars.forEach(System.out::println);

        // Sort by Price (Ascending)
        System.out.println("\nSorted by Price (Ascending):");
        Collections.sort(cars, (car1, car2) -> Double.compare(car1.getPrice(), car2.getPrice()));
        cars.forEach(System.out::println);

        // Sort by Price (Descending)
        System.out.println("\nSorted by Price (Descending):");
        Collections.sort(cars, (car1, car2) -> Double.compare(car2.getPrice(), car1.getPrice()));
        cars.forEach(System.out::println);

        // Sort by Mileage (Ascending)
        System.out.println("\nSorted by Mileage (Ascending):");
        Collections.sort(cars, (car1, car2) -> car1.getMileage() - car2.getMileage());
        cars.forEach(System.out::println);

        // Sort by Mileage (Descending)
        System.out.println("\nSorted by Mileage (Descending):");
        Collections.sort(cars, (car1, car2) -> car2.getMileage() - car1.getMileage());
        cars.forEach(System.out::println);

        // Sort by Model (Make) (Ascending)
        System.out.println("\nSorted by Model (Ascending):");
        Collections.sort(cars, (car1, car2) -> car1.getModel().compareTo(car2.getModel()));
        cars.forEach(System.out::println);

        // Sort by Model (Make) (Descending)
        System.out.println("\nSorted by Model (Descending):");
        Collections.sort(cars, (car1, car2) -> car2.getModel().compareTo(car1.getModel()));
        cars.forEach(System.out::println);
    }
}
