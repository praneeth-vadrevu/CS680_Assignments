HW4: Observer Design Pattern in Airplane Reservation System

Overview
Demonstrates the Observer Design Pattern in an Airplane Reservation System, enabling objects to react to changes without tight coupling.

Key Components
1. Observable.java: Manages observers and notifies them of changes.
2. Observer.java: Interface for observers to implement the update method.
3. FlightStatusTracker.java: Tracks flight status and notifies observers on changes.
4. AirportDisplay.java: Updates airport displays with flight status changes.
5. PassengerNotifier.java: Sends notifications to passengers about flight updates.

How It Works
1. Registration: Observers (AirportDisplay, PassengerNotifier) register with FlightStatusTracker.
2. Notification: FlightStatusTracker calls notifyObservers when flight status changes.
3. Update: Observers receive updates and act accordingly (e.g., update displays or notify passengers).

Example Workflow
1. Flight status changes (e.g., "Delayed").
2. FlightStatusTracker notifies all registered observers.
3. AirportDisplay updates the display, and PassengerNotifier sends passenger notifications.

Test Files
- AirportDisplayTest.java: Ensures AirportDisplay updates correctly.
- FlightStatusTrackerTest.java: Verifies FlightStatusTracker notifies observers.
- PassengerNotifierTest.java: Checks PassengerNotifier sends notifications.

Conclusion
The Observer Pattern decouples the subject (FlightStatusTracker) from observers, ensuring scalable and maintainable code with automatic updates.