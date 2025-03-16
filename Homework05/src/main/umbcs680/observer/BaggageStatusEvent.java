package umbcs680.observer;

public class BaggageStatusEvent {
    private String baggageId;
    private String status;

    public BaggageStatusEvent(String baggageId, String status) {
        this.baggageId = baggageId;
        this.status = status;
    }

    public String getBaggageId() {
        return baggageId;
    }

    public String getStatus() {
        return status;
    }
}