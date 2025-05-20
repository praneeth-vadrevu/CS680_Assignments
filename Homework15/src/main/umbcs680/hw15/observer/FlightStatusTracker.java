package umbcs680.hw15.observer;

public class FlightStatusTracker extends Observable<String> {
    private String flightNumber;
    private String status;

    public FlightStatusTracker(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setStatus(String newStatus) {
        this.status = newStatus;
        System.out.println("Flight " + flightNumber + " status changed to: " + newStatus);
        notifyObservers(newStatus);
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getStatus() {
        return status;
    }
}
