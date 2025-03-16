package umbcs680.observer;

import java.util.ArrayList;
import java.util.List;

public class BaggageStatusTracker extends Observable<BaggageStatusEvent> {
    private String baggageId;

    public BaggageStatusTracker(String baggageId) {
        this.baggageId = baggageId;
    }

    public void setStatus(String newStatus) {
        System.out.println("Baggage " + baggageId + " status changed to: " + newStatus);
        notifyObservers(new BaggageStatusEvent(baggageId, newStatus));
    }

    public String getBaggageId() {
        return baggageId;
    }
}