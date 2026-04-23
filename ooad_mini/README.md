# Emergency Room Queue & Priority Management System

This is a simple Spring Boot MVC mini-project for OOAD based on an Emergency Room Queue and Priority Management domain.

## Team-friendly feature split

Major use cases:
1. Admin / Ambulance handler registers a patient.
2. Triage nurse assesses severity and assigns priority.
3. ER doctor reviews queue, overrides priority, and starts treatment.
4. Caretaker views patient status and notifications.

Minor use cases:
1. View the complete patient list.
2. Re-order the queue automatically after triage.
3. Discharge a treated patient.
4. Persist notifications for dashboard review.

## MVC mapping

- Model: `Patient`, `NotificationLog`, enums, repositories
- View: Thymeleaf templates in `src/main/resources/templates`
- Controller: `AdminController`, `TriageController`, `DoctorController`, `CaretakerController`, `HomeController`

## Design patterns used

1. Factory Method: `PatientFactory` creates patient objects during registration.
2. Strategy: `PriorityStrategy` allows priority calculation logic to vary without changing queue logic.
3. Observer: `PatientEventPublisher` notifies observers when patient state changes.
4. Adapter: `QueueNotificationAdapter` converts internal patient events into caretaker-friendly notifications.

## Design principles used

1. Single Responsibility Principle: controllers, services, repositories, and pattern classes have focused responsibilities.
2. Open/Closed Principle: new priority strategies or observers can be added without changing the queue service.
3. Dependency Inversion Principle: services depend on interfaces such as `PriorityStrategy` and `CaretakerMessageAdapter`.
4. Interface Segregation Principle: small interfaces are used for event observation and priority calculation.

## Tech stack

- Java 17
- Spring Boot MVC
- Spring Data JPA
- Thymeleaf
- H2 in-memory database

## Run

```bash
mvn spring-boot:run
```

Open `http://localhost:8080`

## Suggested report content

- Problem statement: Emergency room patient flow needs faster queueing and better visibility.
- Architecture pattern: MVC with layered service and repository structure.
- Screenshots to include: home dashboard, registration form, triage form, doctor queue, caretaker dashboard.
- Individual contribution suggestion:
  - Member 1: Patient registration
  - Member 2: Triage and priority assignment
  - Member 3: Doctor queue and treatment
  - Member 4: Caretaker dashboard and notifications
