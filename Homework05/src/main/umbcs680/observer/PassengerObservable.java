package umbcs680.observer;
import java.util.List;
import java.util.LinkedList;


// Passenger Observable and Observer
public class PassengerObservable {
    private List<PassengerObserver> observers = new LinkedList<>();

    public void addObserver(PassengerObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(PassengerObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(PassengerEvent event) {
        for (PassengerObserver observer : observers) {
            observer.updatePassenger(event);
        }
    }
}