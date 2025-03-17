package umbcs680.observer;

import java.util.LinkedList;
import java.util.List;

// Flight Observable and Observer
public class FlightObservable {
    private List<FlightObserver> observers = new LinkedList<>();

    public void addObserver(FlightObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(FlightObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(FlightEvent event) {
        for (FlightObserver observer : observers) {
            observer.updateFlight(event);
        }
    }
}
