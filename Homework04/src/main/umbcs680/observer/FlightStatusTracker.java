package umbcs680.observer;

public class FlightStatusTracker extends Observable<String> {
    private String flightNumber;
    private String status;

    public FlightStatusTracker(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setStatus(String newStatus) {
        this.status = newStatus;
        System.out.println("Flight " + flightNumber + " status changed to: " + newStatus);
        notifyObservers(newStatus); // Notify all observers
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getStatus() {
        return status;
    }

    // Main method to run the application
    public static void main(String[] args) {
        // Create the observable (FlightStatusTracker)
        FlightStatusTracker flightStatusTracker = new FlightStatusTracker("AA123");

        // Create observers
        PassengerNotifier passengerNotifier = new PassengerNotifier();
        AirportDisplay airportDisplay = new AirportDisplay();

        // Register observers with the observable
        flightStatusTracker.addObserver(passengerNotifier);
        flightStatusTracker.addObserver(airportDisplay);

        // Simulate flight status changes
        flightStatusTracker.setStatus("On Time");
        flightStatusTracker.setStatus("Delayed");
        flightStatusTracker.setStatus("Cancelled");
    }
}