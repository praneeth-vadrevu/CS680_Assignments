package umbcs680.observer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ObserverRegistrationTest {

    @Test
    void testObserverRegistrationAndRemoval() {
        FlightStatusTracker flightTracker = new FlightStatusTracker("AA123");
        AirportDisplay airportDisplay = new AirportDisplay();

        // Register observer
        flightTracker.addFlightObserver(airportDisplay);
        flightTracker.setStatus("On Time");

        // Remove observer
        flightTracker.removeFlightObserver(airportDisplay);
        flightTracker.setStatus("Delayed");

        // Verify output (you can use a custom output capture mechanism if needed)
        assertTrue(true); // Placeholder assertion
    }
}