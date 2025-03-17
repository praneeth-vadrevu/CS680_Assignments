Project Description

This project extends an Airline Reservation System** to support **many-to-many event notifications** using the **Multicast Design Pattern**. It allows multiple observables (e.g., flight status, reservations) to notify multiple observers (e.g., airport displays, passenger notifiers) when events occur. Observers can handle multiple types of events by registering with multiple observables.

Key Features:
- Event Types: Flight status changes, reservation updates, and passenger updates.
- Custom Observables and Observers: Each event type has its own observable and observer interfaces.
- Multicast Design Pattern: Observers can handle multiple event types by implementing multiple observer interfaces.

 How It Works:
- Observables (e.g., FlightStatusTracker, ReservationObservable, PassengerObservable) notify observers when events occur.
- Observers (e.g., AirportDisplay, PassengerNotifier) process events and perform actions like updating displays or sending notifications.

Classes:
- Event Classes: FlightEvent, ReservationEvent, PassengerEvent.
- Observables: FlightObservable, ReservationObservable, PassengerObservable.
- Observers: FlightObserver, ReservationObserver, PassengerObserver.
- Observer Implementations: AirportDisplay, PassengerNotifier.
- Main Class: FlightStatusTracker.

Technologies:
- *ava: Core implementation.
- JUnit: For testing.
- Ant: For building and running the project.

This project demonstrates a flexible and extensible event notification system for an airline reservation system.