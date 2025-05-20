package umbcs680.hw15.observer;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class FlightStatusTrackerTest {

    @Test
    public void testPassengerNotificationUsingLambda() {
        FlightStatusTracker tracker = new FlightStatusTracker("AA123");

        tracker.addObserver((sender, event) -> {
            FlightStatusTracker t = (FlightStatusTracker) sender;
            System.out.println("Notification sent to passengers of Flight " + t.getFlightNumber() + ": Status is now " + event);
        });

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        tracker.setStatus("Boarding");

        System.setOut(originalOut);
        assertTrue(outContent.toString().trim().contains("Notification sent to passengers of Flight AA123: Status is now Boarding"));
    }

    @Test
    public void testAirportDisplayUsingLambda() {
        FlightStatusTracker tracker = new FlightStatusTracker("AA123");

        tracker.addObserver((sender, event) -> {
            FlightStatusTracker t = (FlightStatusTracker) sender;
            System.out.println("Airport display updated for Flight " + t.getFlightNumber() + ": Status is now " + event);
        });

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        tracker.setStatus("On Time");

        System.setOut(originalOut);
        assertTrue(outContent.toString().trim().contains("Airport display updated for Flight AA123: Status is now On Time"));
    }

    @Test
    public void testBothObserversUsingLambdas() {
        FlightStatusTracker tracker = new FlightStatusTracker("AA123");

        tracker.addObserver((sender, event) -> {
            FlightStatusTracker t = (FlightStatusTracker) sender;
            System.out.println("Notification sent to passengers of Flight " + t.getFlightNumber() + ": Status is now " + event);
        });

        tracker.addObserver((sender, event) -> {
            FlightStatusTracker t = (FlightStatusTracker) sender;
            System.out.println("Airport display updated for Flight " + t.getFlightNumber() + ": Status is now " + event);
        });

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        tracker.setStatus("Delayed");

        System.setOut(originalOut);

        String output = outContent.toString().trim();
        assertTrue(output.contains("Notification sent to passengers of Flight AA123: Status is now Delayed"));
        assertTrue(output.contains("Airport display updated for Flight AA123: Status is now Delayed"));
    }
}

