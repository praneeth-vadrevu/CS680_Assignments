package umbcs680.observer;

public class AirportDisplay implements FlightStatusObserver, BaggageStatusObserver {
    @Override
    public void updateFlightStatus(FlightStatusEvent event) {
        System.out.println("Airport display updated for Flight " + event.getFlightNumber() + ": Status is now " + event.getStatus());
    }

    @Override
    public void updateBaggageStatus(BaggageStatusEvent event) {
        System.out.println("Airport display updated for Baggage " + event.getBaggageId() + ": Status is now " + event.getStatus());
    }
}