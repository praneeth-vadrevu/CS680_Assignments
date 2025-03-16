package umbcs680.observer;

import java.util.ArrayList;
import java.util.List;

public class FlightStatusTracker extends Observable<FlightStatusEvent> {
    private String flightNumber;

    public FlightStatusTracker(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setStatus(String newStatus) {
        System.out.println("Flight " + flightNumber + " status changed to: " + newStatus);
        notifyObservers(new FlightStatusEvent(flightNumber, newStatus));
    }

    public String getFlightNumber() {
        return flightNumber;
    }
}