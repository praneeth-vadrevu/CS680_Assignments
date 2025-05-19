package umbcs680.hw10;

import java.util.List;

public class Car {
    private String model;
    private int year;
    private int mileage;
    private double price;
    private int dominationCount;

    public Car(String model, int year, int mileage, double price) {
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public int getMileage() {
        return mileage;
    }

    public double getPrice() {
        return price;
    }

    public int getDominationCount() {
        return dominationCount;
    }

    public void setDominationCount(List<Car> cars) {
        int count = 0;
        for (Car other : cars) {
            if (this == other) continue;

            boolean betterInAtLeastOne = false;
            boolean notWorseInAll = true;

            if (other.getPrice() < this.price) betterInAtLeastOne = true;
            else if (other.getPrice() > this.price) notWorseInAll = false;

            if (other.getYear() > this.year) betterInAtLeastOne = true;
            else if (other.getYear() < this.year) notWorseInAll = false;

            if (other.getMileage() < this.mileage) betterInAtLeastOne = true;
            else if (other.getMileage() > this.mileage) notWorseInAll = false;

            if (notWorseInAll && betterInAtLeastOne) {
                count++;
            }
        }
        this.dominationCount = count;
    }

    @Override
    public String toString() {
        return model + " - Year: " + year + ", Mileage: " + mileage + ", Price: $" + price;
    }
}

