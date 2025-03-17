package umbcs680.observer;
import java.util.List;
import java.util.LinkedList;

public interface PassengerObserver {
    void updatePassenger(PassengerEvent event);
}