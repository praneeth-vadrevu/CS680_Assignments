HW5: Multicast Design Pattern in Airplane Reservation System

Overview
In this assignment, I extended the Observer Design Pattern from HW4 to implement a many-to-many event notification system** using the Multicast Design Pattern**. This enhancement allows multiple subjects (like flight and baggage trackers) to notify multiple observers (such as airport displays and passenger notifiers) simultaneously. This setup is particularly useful in an Airplane Reservation System, where various components need to stay updated in real-time.

Key Components
1. Observable.java: This class manages a list of observers and provides methods to add, remove, and notify them. It’s the backbone of the notification system.
2. Observer.java: This interface defines the update method, which all observers must implement to receive notifications.
3. FlightStatusTracker.java: This class tracks flight status changes (like delays or cancellations) and notifies all registered observers when a change occurs.
4. BaggageStatusTracker.java: Similar to the flight tracker, this class monitors baggage status changes and notifies observers accordingly.
5. AirportDisplay.java: This observer updates the airport display screens with the latest flight and baggage status information.
6. PassengerNotifier.java: This observer sends notifications to passengers about any changes in flight or baggage status, ensuring they are informed in real-time.

How It Works
1. Registration: Observers (like AirportDisplay and PassengerNotifier) register with multiple subjects (such as FlightStatusTracker and BaggageStatusTracker) using the addObserver method. This allows them to receive updates from multiple sources.
2. Notification: When a subject’s state changes (e.g., a flight is delayed or baggage status changes), it calls the notifyObservers method. This method iterates through all registered observers and invokes their update method.
3. Update: Each observer implements the update method to handle notifications. For example:
    - AirportDisplay updates the display with the latest flight and baggage status.
    - PassengerNotifier sends notifications to passengers about the changes.

Example Workflow
1. A flight status changes from "On Time" to "Delayed".
2. The FlightStatusTracker detects this change and notifies all registered observers.
3. At the same time, a baggage status changes from "Loaded" to "Delayed".
4. The BaggageStatusTracker detects this change and notifies all registered observers.
5. Observers like AirportDisplay and PassengerNotifier receive updates from both subjects and take appropriate actions:
    - AirportDisplay updates the display with the new flight and baggage status.
    - PassengerNotifier sends notifications to passengers about the delay and baggage status.

Test Files
- AirplaneSystemTest.java: This test file checks the overall system, ensuring that multiple subjects and observers work together correctly. It verifies that changes in flight and baggage status are properly communicated to all registered observers.
- AirportDisplayTest.java: This test ensures that AirportDisplay correctly updates flight and baggage information when notified by multiple subjects. It checks that the display reflects the latest status changes.
- FlightStatusTrackerTest.java: This test verifies that FlightStatusTracker notifies all observers when flight status changes. It ensures that the notification mechanism works as expected.
- PassengerNotifierTest.java: This test ensures that PassengerNotifier sends notifications to passengers based on updates from multiple subjects. It validates that passengers receive timely and accurate notifications.

#Conclusion
By implementing the Multicast Design Pattern, I enhanced the Observer Pattern to support **many-to-many event notifications. This allows multiple subjects to notify multiple observers, making the system more scalable and adaptable to new requirements. This approach ensures that all relevant components are updated in real-time, maintaining consistency and improving the overall user experience. The implementation demonstrates how different parts of the system can work together seamlessly to provide real-time updates and notifications, enhancing the efficiency and reliability of the reservation system. This project has been a great learning experience in understanding how design patterns can be extended and applied to solve complex real-world problems.