package umbcs680.hw10;

public class ParetoComparator implements Comparator<Car> {

    @Override
    public int compare(Car c1, Car c2) {
        int betterCount = 0;
        int worseCount = 0;

        if (c1.getPrice() < c2.getPrice()) betterCount++;
        else if (c1.getPrice() > c2.getPrice()) worseCount++;

        if (c1.getYear() > c2.getYear()) betterCount++;
        else if (c1.getYear() < c2.getYear()) worseCount++;

        if (c1.getMileage() < c2.getMileage()) betterCount++;
        else if (c1.getMileage() > c2.getMileage()) worseCount++;

        if (betterCount > worseCount) return 1;      // c1 dominates c2
        if (worseCount > betterCount) return -1;     // c2 dominates c1
        return 0;                                    // Neither dominates
    }
}
