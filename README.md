# Expense Tracker Backend

Expense Tracker Backend is a Spring Boot 3 REST API for managing expense categories, tracking expenses, and retrieving monthly spending totals.

## Tech Stack

- Java 21
- Spring Boot 3.5.3
- Spring Web
- Spring Data JPA
- Spring Validation
- H2 Database
- SpringDoc OpenAPI Swagger UI
- Maven

## Project Structure

```text
src/main/java/com/expensetracker
|-- config
|-- controller
|-- dto
|-- entity
|-- exception
|-- repository
|-- service
```

## Implemented Tasks

1. Project structure created with Maven and Spring Boot.
2. Entity classes created for `Category` and `Expense`.
3. DTOs created for API requests and responses.
4. Repository interfaces created using Spring Data JPA.
5. Service layer implemented with business validations.
6. REST controllers created for category and expense APIs.
7. Swagger/OpenAPI configured for live API documentation.
8. README generated with setup and usage instructions.

## Features

- CRUD operations for categories
- CRUD operations for expenses
- Expense-to-category relationship validation
- Monthly total expense summary by year and month
- Global exception handling with structured API errors
- Swagger UI for interactive API exploration

## Prerequisites

- Java 21
- Maven 3.9+

## Run Locally

```bash
mvn clean install
mvn spring-boot:run
```

The application starts on:

```text
http://localhost:8080
```

## API Documentation

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/api-docs`
- H2 Console: `http://localhost:8080/h2-console`

Use the following H2 connection details if needed:

- JDBC URL: `jdbc:h2:mem:expensetrackerdb`
- Username: `sa`
- Password: leave blank

## Main Endpoints

### Category APIs

- `POST /api/v1/categories`
- `GET /api/v1/categories`
- `GET /api/v1/categories/{id}`
- `PUT /api/v1/categories/{id}`
- `DELETE /api/v1/categories/{id}`

### Expense APIs

- `POST /api/v1/expenses`
- `GET /api/v1/expenses`
- `GET /api/v1/expenses/{id}`
- `PUT /api/v1/expenses/{id}`
- `DELETE /api/v1/expenses/{id}`
- `GET /api/v1/expenses/summary/monthly?year=2026&month=7`

## Sample Request Flow

### 1. Create a category

```http
POST /api/v1/categories
Content-Type: application/json

{
  "name": "Food",
  "description": "Daily meals and snacks"
}
```

### 2. Create an expense

```http
POST /api/v1/expenses
Content-Type: application/json

{
  "description": "Lunch",
  "amount": 250.50,
  "expenseDate": "2026-07-02",
  "categoryId": 1
}
```

### 3. Retrieve monthly summary

```http
GET /api/v1/expenses/summary/monthly?year=2026&month=7
```

## Response Behavior

- `201 Created` for successful create operations
- `200 OK` for reads and updates
- `204 No Content` for successful deletes
- `400 Bad Request` for validation failures
- `404 Not Found` for missing categories or expenses
- `409 Conflict` when attempting to delete a category still used by expenses or when a category name already exists

## Notes

- The app uses an in-memory H2 database, so data resets each time the application restarts.
- No authentication is included in this version.
- This version focuses on expenses, categories, and monthly summary totals.