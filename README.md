# Event Management Application

## 🚀 Project Overview

This Spring Boot RESTful API manages events, supporting full **CRUD operations**.  
It showcases practical use of **Spring Boot**, **DTOs**, **custom exceptions**, **validation**, and **logging** for a maintainable and scalable backend service.

---

## 🔑 Key Features

- Create, Read, Update, Delete (CRUD) events  
- Fetch events by location  
- Retrieve upcoming events (events with date after today)  
- Input validation with clear error messages  
- Custom exception handling with global centralized handler  
- Comprehensive logging for API monitoring  

---

## 🛠 Technology Stack

| Technology        | Usage                       |
|-------------------|-----------------------------|
| Java 17+          | Programming language         |
| Spring Boot 3.x   | Application framework        |
| Spring Data JPA   | Data persistence             |
| Hibernate         | ORM                         |
| Maven             | Build automation             |
| Jakarta Validation| Input validation             |
| SLF4J + Logback   | Logging framework            |
| H2 / MySQL        | Database                    |

---

## 📂 Project Structure

src/main/java/com/example/eventmanagement
│
├── controller # REST controllers handling HTTP requests
├── dto # Data Transfer Objects for API input/output
├── entity # JPA entities mapping to database tables
├── exception # Custom exceptions & global handler
├── repository # Spring Data JPA repositories
└── service # Business logic and validation


---

## 🗃 Database Model: Event Entity

| Field       | Type       | Constraints               | Description                      |
|-------------|------------|---------------------------|--------------------------------|
| id          | Long       | Primary key, auto-generated | Unique event identifier          |
| title       | String     | Not null                  | Event title                     |
| description | String     | Nullable                  | Detailed event description      |
| location    | String     | Not null                  | Event location                  |
| eventDate   | LocalDate  | Not null                  | Date of the event (yyyy-MM-dd) |

---

## 📡 API Endpoints

| Method | URL                           | Description                          |                            
|--------|-------------------------------|------------------------------------|
| POST   | `/api/events/create`           | Create new event                   |
| GET    | `/api/events`                  | Get all events                    | 
| GET    | `/api/events/{id}`             | Get event by ID                  | 
| PUT    | `/api/events/{id}`             | Update event by ID               | 
| DELETE | `/api/events/{id}`             | Delete event by ID               |
| GET    | `/api/events/location/{location}` | Get events filtered by location | 
| GET    | `/api/events/upcoming`         | Get upcoming events              | 

---

## ⚙️ Application Flow

1. Client sends an HTTP request to the API.  
2. Controller validates input (`@Valid`) and logs request details.  
3. Controller calls Service for business logic and validation.  
4. Service interacts with Repository to perform database operations.  
5. Entities map to DTOs before response.  
6. Exceptions (not found, invalid data) trigger custom exceptions.  
7. Global exception handler formats error responses with proper HTTP codes.  
8. Controller returns success or error response to client.

---

## 🛡 Exception Handling

| Exception              | When Triggered                       | HTTP Status Code | Description                               |
|------------------------|------------------------------------|------------------|-------------------------------------------|
| `EventNotFoundException` | Requested event does not exist      | 404 NOT FOUND    | Event not found in the database            |
| `InvalidEventException`  | Business validation failure         | 400 BAD REQUEST  | Invalid data, such as event date in past  |

---

## ✅ Validation

- Input DTOs validated using Jakarta Bean Validation annotations (`@NotBlank`, `@NotNull`, etc.).  
- Additional business validations performed in service layer (e.g., event date cannot be in the past).

---

## 📝 Logging

- SLF4J used for logging at the controller level.  
- Logs key actions and important data points (IDs, titles, counts).  
- Helps trace requests and debug issues efficiently.

---

## 💻 How to Run

1. Clone the repository:  
   ```bash
   git clone <repo-url>
   cd eventmanagement
2.Configure your database in src/main/resources/application.properties .

3.Build and start the app:

mvn clean install
mvn spring-boot:run


4.Access API at: http://localhost:8080/api/events

🧪 Testing the API

Use Postman or any REST client to test endpoints.
Set Content-Type header to application/json for POST and PUT requests.

Example POST request body to create event:

{ 
"title": "Concert", 
"description": "Singing event",
"location": "Hyderabad", 
"eventDate": "2026-02-14"
}


🙌 Contribution

Feel free to fork the project, open issues, and submit pull requests!
For questions or feedback, please reach out.

Thank you for checking out this project! 🌟

