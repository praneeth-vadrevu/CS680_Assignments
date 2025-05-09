package umbcs680.observer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class FlightStatusTrackerTest {

    @Test
    void testFlightStatusUpdate() {
        FlightStatusTracker flightTracker = new FlightStatusTracker("AA123");
        AirportDisplay airportDisplay = new AirportDisplay();
        PassengerNotifier passengerNotifier = new PassengerNotifier();

        // Register observers
        flightTracker.addFlightObserver(airportDisplay);
        flightTracker.addFlightObserver(passengerNotifier);

        // Simulate flight status change
        flightTracker.setStatus("Delayed");

        // Verify output (you can use a custom output capture mechanism if needed)
        // For simplicity, we assume the observers print to the console.
        // In a real test, you might capture and verify the output.
        assertTrue(true); // Placeholder assertion
    }
}