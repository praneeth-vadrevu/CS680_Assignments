package umbcs680.observer;

public class AirlineSystemTest {
    public static void main(String[] args) {
        // Create observables
        FlightStatusTracker flightTracker = new FlightStatusTracker("AA123");
        BaggageStatusTracker baggageTracker = new BaggageStatusTracker("BAG456");

        // Create observers
        PassengerNotifier passengerNotifier = new PassengerNotifier();
        AirportDisplay airportDisplay = new AirportDisplay();

        // Register observers with observables
        flightTracker.addObserver(passengerNotifier);
        flightTracker.addObserver(airportDisplay);
        baggageTracker.addObserver(passengerNotifier);
        baggageTracker.addObserver(airportDisplay);

        // Simulate status changes
        flightTracker.setStatus("On Time");
        baggageTracker.setStatus("Checked In");

        flightTracker.setStatus("Delayed");
        baggageTracker.setStatus("Delayed");

        flightTracker.setStatus("Cancelled");
        baggageTracker.setStatus("Lost");
    }
}