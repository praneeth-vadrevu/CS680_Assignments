package umbcs680.observer;

public class PassengerNotifier implements Observer<String> {
    @Override
    public void update(Observable<String> sender, String event) {
        if (sender instanceof FlightStatusTracker) {
            FlightStatusTracker tracker = (FlightStatusTracker) sender;
            System.out.println("Notification sent to passengers of Flight " + tracker.getFlightNumber() + ": Status is now " + event);
        }
    }
}