package umbcs680.observer;
import java.util.List;
import java.util.LinkedList;

public class FlightStatusTracker {
    private String flightNumber;
    private String status;
    private FlightObservable flightObservable = new FlightObservable();

    public FlightStatusTracker(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setStatus(String newStatus) {
        this.status = newStatus;
        System.out.println("Flight " + flightNumber + " status changed to: " + newStatus);
        flightObservable.notifyObservers(new FlightEvent(flightNumber, newStatus));
    }

    public void addFlightObserver(FlightObserver observer) {
        flightObservable.addObserver(observer);
    }

    public void removeFlightObserver(FlightObserver observer) {
        flightObservable.removeObserver(observer);
    }
}