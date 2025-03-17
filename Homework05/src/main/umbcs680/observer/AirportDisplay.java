package umbcs680.observer;
import java.util.List;
import java.util.LinkedList;

public class AirportDisplay implements FlightObserver, ReservationObserver {
    @Override
    public void updateFlight(FlightEvent event) {
        System.out.println("Airport display updated for Flight " + event.getFlightNumber() + ": Status is now " + event.getStatus());
    }

    @Override
    public void updateReservation(ReservationEvent event) {
        System.out.println("Airport display updated for Reservation " + event.getReservationId() + ": Event is " + event.getEventType());
    }
}