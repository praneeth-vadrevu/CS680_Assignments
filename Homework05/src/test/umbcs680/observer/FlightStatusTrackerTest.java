package umbcs680.observer;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FlightStatusTrackerTest {

    @Test
    public void testAddObserver() {
        FlightStatusTracker tracker = new FlightStatusTracker("AA123");
        PassengerNotifier passengerNotifier = new PassengerNotifier();

        tracker.addObserver(passengerNotifier);
        List<Observer<FlightStatusEvent>> observers = tracker.getObservers();

        assertEquals(1, observers.size());
        assertTrue(observers.contains(passengerNotifier));
    }

    @Test
    public void testNotifyObservers() {
        FlightStatusTracker tracker = new FlightStatusTracker("AA123");
        PassengerNotifier passengerNotifier = new PassengerNotifier();
        AirportDisplay airportDisplay = new AirportDisplay();

        tracker.addObserver(passengerNotifier);
        tracker.addObserver(airportDisplay);

        // Redirect standard output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Simulate a status change
        tracker.setStatus("Delayed");

        // Restore standard output
        System.setOut(originalOut);

        String output = outContent.toString().trim();

        // Verify that observers were notified
        String expectedPassengerOutput = "Notification sent to passengers of Flight AA123: Status is now Delayed";
        String expectedAirportOutput = "Airport display updated for Flight AA123: Status is now Delayed";

        assertTrue(output.contains(expectedPassengerOutput));
        assertTrue(output.contains(expectedAirportOutput));
    }
}