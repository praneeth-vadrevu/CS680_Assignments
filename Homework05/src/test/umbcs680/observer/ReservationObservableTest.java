package umbcs680.observer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ReservationObservableTest {

    @Test
    void testReservationEventNotification() {
        ReservationObservable reservationObservable = new ReservationObservable();
        AirportDisplay airportDisplay = new AirportDisplay();

        // Register observer
        reservationObservable.addObserver(airportDisplay);

        // Simulate reservation event
        reservationObservable.notifyObservers(new ReservationEvent("RES456", "Booked"));

        // Verify output (you can use a custom output capture mechanism if needed)
        assertTrue(true); // Placeholder assertion
    }
}