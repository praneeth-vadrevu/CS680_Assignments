package umbcs680.observer;

public class AirportDisplay implements Observer<String> {
    @Override
    public void update(Observable<String> sender, String event) {
        if (sender instanceof FlightStatusTracker) {
            FlightStatusTracker tracker = (FlightStatusTracker) sender;
            System.out.println("Airport display updated for Flight " + tracker.getFlightNumber() + ": Status is now " + event);
        }
    }
}