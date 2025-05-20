package umbcs680.hw15.observer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PassengerNotifierTest {

    @Test
    public void testPassengerNotifierLambdaBehavior() {
        FlightStatusTracker tracker = new FlightStatusTracker("AA123");

        // Exact behavior from PassengerNotifier class implemented as a lambda


        tracker.addObserver( (sender, event) -> {
            if (sender instanceof FlightStatusTracker) {
                FlightStatusTracker t = (FlightStatusTracker) sender;
                System.out.println("Notification sent to passengers of Flight " + t.getFlightNumber() + ": Status is now " + event);
            }
        });

        // Trigger a status update
        tracker.setStatus("On Time");

        // You can assert on observer count or status if not verifying console output
        assertEquals(1, tracker.countObservers());
        assertEquals("On Time", tracker.getStatus());
    }
}
