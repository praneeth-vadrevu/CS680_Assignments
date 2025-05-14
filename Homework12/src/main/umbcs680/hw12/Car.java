package umbcs680.hw12;

// Car class representing a car with price, year, and mileage
public class Car {
    private String model;
    private int year;
    private int mileage;
    private double price;
    private int dominationCount;

    // Constructor
    public Car(String model, int year, int mileage, double price) {
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
    }

    // Getters
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

    public void setDominationCount(int count) {
        this.dominationCount = count;
    }


    // toString for readable output
    @Override
    public String toString() {
        return model + " - Year: " + year + ", Mileage: " + mileage + ", Price: $" + price;
    }
}
