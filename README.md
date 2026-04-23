# Emergency Room Queue & PriorityManagement

Emergency Rooms in hospitals are high-pressure environments where multiple patients arrive simultaneously with varying levels of medical urgency. The traditional, paper-based first-come-first-served approach to patient management fails to account for medical severity and often results in critical patients waiting behind less urgent cases, leading to adverse patient outcomes and inefficient use of medical resources.

Modern healthcare demands a systematic, software-supported approach to triaging patients, maintaining a dynamic priority queue, and ensuring that caretakers and family members are kept informed of patient status at every stage of the ER workflow.

The Emergency Room Queue and Priority Management System addresses the need for a structured, software-based approach to managing patient flow from initial registration through to discharge. The core problems this system solves are:
 1. There is no automated mechanism to assign a medical priority score to incoming patients based on objective criteria such as severity, age, and mode of arrival.
 2. Doctors have no real-time, sorted view of which patient to attend to next, making clinical decision-making dependent on manual tracking.
 3. Caretakers and family members receive no structured updates when a patient's status changes, causing anxiety and information gaps.
 4. Any change in priority logic requires modifications across multiple parts of the system if not designed with flexibility in mind.

Steps to Run
Prerequisites:\
Java 17 or higher - check with java -version\
Maven 3.6+ - check with mvn -version\
No database setup needed - the app uses an in-memory H2 database that auto-creates on startup\

1. Clone the Repository\
   `git clone https://github.com/[your-username]/ooad_mini.git`\
   `cd ooad_mini`

2. Build the Project\
   `mvn clean install`

3. Run the Application\
   `mvn spring-boot:run`

5. Open in Browser\
   `http://localhost:8080`
