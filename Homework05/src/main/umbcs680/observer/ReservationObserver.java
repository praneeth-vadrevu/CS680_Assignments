package umbcs680.observer;
import java.util.List;
import java.util.LinkedList;

public interface ReservationObserver {
    void updateReservation(ReservationEvent event);
}

