package umbcs680.observer;
import java.util.List;
import java.util.LinkedList;

public class PassengerNotifier implements FlightObserver, PassengerObserver {
    @Override
    public void updateFlight(FlightEvent event) {
        System.out.println("Notification sent to passengers of Flight " + event.getFlightNumber() + ": Status is now " + event.getStatus());
    }

    @Override
    public void updatePassenger(PassengerEvent event) {
        System.out.println("Notification sent to passenger " + event.getPassengerId() + ": Event is " + event.getEventType());
    }
}