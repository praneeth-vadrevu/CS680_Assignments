package umbcs680.hw15.observer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ObserverLambdaExpressionTest {

    @Test
    public void testObserverLambdaExecution() {
        FlightStatusTracker tracker = new FlightStatusTracker("AA123");
        tracker.addObserver((sender, event) -> {});
        tracker.addObserver((sender, event) -> {});
        tracker.setStatus("Boarding");

        assertEquals("Boarding", tracker.getStatus());
        assertEquals(2, tracker.countObservers());
    }

    @Test
    public void testObserverLambdaRemoval() {
        FlightStatusTracker tracker = new FlightStatusTracker("AA123");
        Observer<String> observer = (sender, event) -> {};
        tracker.addObserver(observer);
        assertEquals(1, tracker.countObservers());

        tracker.removeObserver(observer);
        assertEquals(0, tracker.countObservers());
    }

    @Test
    public void testObserverLambdaClearAll() {
        FlightStatusTracker tracker = new FlightStatusTracker("AA123");
        tracker.addObserver((sender, event) -> {});
        tracker.addObserver((sender, event) -> {});
        assertEquals(2, tracker.countObservers());

        tracker.clearObservers();
        assertEquals(0, tracker.countObservers());
    }
}

