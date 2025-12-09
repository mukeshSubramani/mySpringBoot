# Employee Management System (sbemployee)

Spring Boot module for managing employee details with H2 in-memory database.

## Features

- RESTful API for employee management
- H2 in-memory database for data persistence
- CRUD operations (Create, Read, Update)
- Spring Data JPA for database operations
- Lombok for reducing boilerplate code

## Technologies

- Spring Boot 3.2.2
- Spring Data JPA
- H2 Database
- Lombok
- Java 17
- Maven

## API Endpoints

### Save Employee
```bash
POST /api/employees
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "department": "Engineering",
  "salary": 75000.00
}
```

### Get Employee by ID
```bash
GET /api/employees/{id}
```

### Get All Employees
```bash
GET /api/employees
```

### Update Employee
```bash
PUT /api/employees/{id}
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "department": "Engineering",
  "salary": 85000.00
}
```

## Running the Application

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Steps to Run

1. Navigate to the module directory:
```bash
cd sbemployee
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

The application will start on port 8080.

## H2 Console

You can access the H2 database console at:
- URL: http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:employeedb
- Username: sa
- Password: (leave blank)

## Testing the API

Example using curl:

```bash
# Create an employee
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "department": "Engineering",
    "salary": 75000.00
  }'

# Get all employees
curl -X GET http://localhost:8080/api/employees

# Get employee by ID
curl -X GET http://localhost:8080/api/employees/1

# Update employee
curl -X PUT http://localhost:8080/api/employees/1 \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "department": "Engineering",
    "salary": 85000.00
  }'
```

## Database Schema

### Employee Table
- id (BIGINT, Primary Key, Auto-generated)
- firstName (VARCHAR, Not Null)
- lastName (VARCHAR, Not Null)
- email (VARCHAR, Not Null, Unique)
- department (VARCHAR)
- salary (DOUBLE)
