package umbcs680.hw12;

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

            boolean notWorseInAll = other.price <= this.price &&
                    other.year >= this.year &&
                    other.mileage <= this.mileage;

            boolean betterInAtLeastOne = other.price < this.price ||
                    other.year > this.year ||
                    other.mileage > this.mileage;

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


