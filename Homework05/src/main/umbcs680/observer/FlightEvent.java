package umbcs680.observer;
import java.util.List;
import java.util.LinkedList;

public class FlightEvent {
    private String flightNumber;
    private String status;

    public FlightEvent(String flightNumber, String status) {
        this.flightNumber = flightNumber;
        this.status = status;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getStatus() {
        return status;
    }
}