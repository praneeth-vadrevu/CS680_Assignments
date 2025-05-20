package umbcs680.hw15.observer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AirportDisplayTest {
    @Test
    public void testAirportDisplayLambdaBehavior() {
        FlightStatusTracker tracker = new FlightStatusTracker("AA123");

        tracker.addObserver((sender, event) -> {
                    if (sender instanceof FlightStatusTracker) {
                        FlightStatusTracker t = (FlightStatusTracker) sender;
                        System.out.println("Airport display updated for Flight " + t.getFlightNumber() + ": Status is now " + event);
                    }
                });

        // Trigger a status update
        tracker.setStatus("Delayed");

        // You can assert on internal state or observer count if not checking console output
        assertEquals(1, tracker.countObservers());
        assertEquals("Delayed", tracker.getStatus());
    }


}
