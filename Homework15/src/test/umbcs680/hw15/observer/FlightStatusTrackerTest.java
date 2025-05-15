package umbcs680.hw15.observer;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FlightStatusTrackerTest {

    @Test
    public void testGetFlightNumber() {
        FlightStatusTracker tracker = new FlightStatusTracker("AA123");
        assertEquals("AA123", tracker.getFlightNumber());
    }

    @Test
    public void testSetAndGetStatus() {
        FlightStatusTracker tracker = new FlightStatusTracker("AA123");
        tracker.setStatus("Delayed");
        assertEquals("Delayed", tracker.getStatus());
    }

    @Test
    public void testAddObserver() {
        FlightStatusTracker tracker = new FlightStatusTracker("AA123");
        Observer<String> observer = (sender, event) -> {};
        tracker.addObserver(observer);
        List<Observer<String>> observers = tracker.getObservers();
        assertEquals(1, observers.size());
        assertTrue(observers.contains(observer));
    }

    @Test
    public void testRemoveObserver() {
        FlightStatusTracker tracker = new FlightStatusTracker("AA123");
        Observer<String> observer = (sender, event) -> {};
        tracker.addObserver(observer);
        tracker.removeObserver(observer);
        List<Observer<String>> observers = tracker.getObservers();
        assertEquals(0, observers.size());
    }

    @Test
    public void testClearObservers() {
        FlightStatusTracker tracker = new FlightStatusTracker("AA123");
        tracker.addObserver((sender, event) -> {});
        tracker.addObserver((sender, event) -> {});
        tracker.clearObservers();
        assertEquals(0, tracker.countObservers());
    }

    @Test
    public void testCountObservers() {
        FlightStatusTracker tracker = new FlightStatusTracker("AA123");
        assertEquals(0, tracker.countObservers());
        tracker.addObserver((sender, event) -> {});
        assertEquals(1, tracker.countObservers());
    }
}
