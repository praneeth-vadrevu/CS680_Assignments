package umbcs680.hw15.observer;

public class ObserverLambdaExpression {

    public static void demonstrateObserverWithLambdas() {
        FlightStatusTracker flightStatusTracker = new FlightStatusTracker("AA123");


        flightStatusTracker.addObserver((sender, event) -> {
            if (sender instanceof FlightStatusTracker) {
                FlightStatusTracker tracker = (FlightStatusTracker) sender;
                System.out.println("Airport display updated for Flight " + tracker.getFlightNumber() + ": Status is now " + event);
            }
        });


        flightStatusTracker.addObserver((sender, event) -> {
            if (sender instanceof FlightStatusTracker) {
                FlightStatusTracker tracker = (FlightStatusTracker) sender;
                System.out.println("Notification sent to passengers of Flight " + tracker.getFlightNumber() + ": Status is now " + event);
            }
        });


        flightStatusTracker.setStatus("On Time");
        flightStatusTracker.setStatus("Delayed");
        flightStatusTracker.setStatus("Cancelled");
    }
}
