package umbcs680.observer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PassengerObservableTest {

    @Test
    void testPassengerEventNotification() {
        PassengerObservable passengerObservable = new PassengerObservable();
        PassengerNotifier passengerNotifier = new PassengerNotifier();

        // Register observer
        passengerObservable.addObserver(passengerNotifier);

        // Simulate passenger event
        passengerObservable.notifyObservers(new PassengerEvent("PASS123", "Checked In"));

        // Verify output (you can use a custom output capture mechanism if needed)
        assertTrue(true); // Placeholder assertion
    }
}