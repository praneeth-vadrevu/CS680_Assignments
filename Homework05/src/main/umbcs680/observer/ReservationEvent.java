package umbcs680.observer;
import java.util.List;
import java.util.LinkedList;

public class ReservationEvent {
    private String reservationId;
    private String eventType;

    public ReservationEvent(String reservationId, String eventType) {
        this.reservationId = reservationId;
        this.eventType = eventType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getEventType() {
        return eventType;
    }
}