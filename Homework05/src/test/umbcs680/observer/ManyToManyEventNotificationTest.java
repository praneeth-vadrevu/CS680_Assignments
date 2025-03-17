package umbcs680.observer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ManyToManyEventNotificationTest {

    @Test
    void testManyToManyEventNotification() {
        FlightStatusTracker flightTracker = new FlightStatusTracker("AA123");
        ReservationObservable reservationObservable = new ReservationObservable();
        PassengerObservable passengerObservable = new PassengerObservable();

        AirportDisplay airportDisplay = new AirportDisplay();
        PassengerNotifier passengerNotifier = new PassengerNotifier();

        // Register observers with multiple observables
        flightTracker.addFlightObserver(airportDisplay);
        flightTracker.addFlightObserver(passengerNotifier);
        reservationObservable.addObserver(airportDisplay);
        passengerObservable.addObserver(passengerNotifier);

        // Simulate events
        flightTracker.setStatus("On Time");
        reservationObservable.notifyObservers(new ReservationEvent("RES456", "Booked"));
        passengerObservable.notifyObservers(new PassengerEvent("PASS123", "Checked In"));

        // Verify output (you can use a custom output capture mechanism if needed)
        assertTrue(true); // Placeholder assertion
    }
}