package umbcs680.observer;
import java.util.List;
import java.util.LinkedList;

// Reservation Observable and Observer
public class ReservationObservable {
    private List<ReservationObserver> observers = new LinkedList<>();

    public void addObserver(ReservationObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ReservationObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(ReservationEvent event) {
        for (ReservationObserver observer : observers) {
            observer.updateReservation(event);
        }
    }
}