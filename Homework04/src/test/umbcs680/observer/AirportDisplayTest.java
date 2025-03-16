package umbcs680.observer;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class AirportDisplayTest {

    @Test
    public void testUpdate() {
        FlightStatusTracker tracker = new FlightStatusTracker("AA123");
        AirportDisplay airportDisplay = new AirportDisplay();
        tracker.addObserver(airportDisplay); //  Crucial: Register the observer!

        // Redirect standard output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Simulate a status change
        tracker.setStatus("Delayed");

        // Restore standard output
        System.setOut(originalOut);

        // Verify that the display was updated (assert the output)
        String expectedOutput = "Airport display updated for Flight AA123: Status is now Delayed";
        assertTrue(outContent.toString().trim().contains(expectedOutput));
    }
}