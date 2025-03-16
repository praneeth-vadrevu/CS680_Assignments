package umbcs680.observer;

public class FlightStatusEvent {
    private String flightNumber;
    private String status;

    public FlightStatusEvent(String flightNumber, String status) {
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
