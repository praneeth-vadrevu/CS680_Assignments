package umbcs680.observer;
import java.util.List;
import java.util.LinkedList;

public class PassengerEvent {
    private String passengerId;
    private String eventType;

    public PassengerEvent(String passengerId, String eventType) {
        this.passengerId = passengerId;
        this.eventType = eventType;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public String getEventType() {
        return eventType;
    }
}