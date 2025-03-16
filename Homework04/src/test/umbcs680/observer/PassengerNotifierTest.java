package umbcs680.observer;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class PassengerNotifierTest {

    @Test
    public void testUpdate() {
        FlightStatusTracker tracker = new FlightStatusTracker("AA123");
        PassengerNotifier passengerNotifier = new PassengerNotifier();
        tracker.addObserver(passengerNotifier); // Crucial: Register the observer

        // Redirect standard output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));


        // Simulate a status change
        tracker.setStatus("On Time");

        // Restore standard output
        System.setOut(originalOut);

        // Verify that the notification was sent (assert the output)
        String expectedOutput = "Notification sent to passengers of Flight AA123: Status is now On Time";
        assertTrue(outContent.toString().trim().contains(expectedOutput));
    }
}