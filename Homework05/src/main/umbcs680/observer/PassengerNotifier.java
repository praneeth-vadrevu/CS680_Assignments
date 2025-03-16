package umbcs680.observer;

public class PassengerNotifier implements FlightStatusObserver, BaggageStatusObserver {
    @Override
    public void updateFlightStatus(FlightStatusEvent event) {
        System.out.println("Notification sent to passengers of Flight " + event.getFlightNumber() + ": Status is now " + event.getStatus());
    }

    @Override
    public void updateBaggageStatus(BaggageStatusEvent event) {
        System.out.println("Notification sent to passengers about Baggage " + event.getBaggageId() + ": Status is now " + event.getStatus());
    }
}