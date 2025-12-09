# mySpringBoot
Online food ordering system and employee management.
This project has multiple modules, each module is an end to end system with its own data store (Cassandra, MongoDb, H2). 
This application is developed with all spring boot functionality which includes SpringMVC, Spring data, Spring security

## Modules

### sbemployee
Employee Management System with H2 in-memory database. Provides RESTful API for managing employee details including save, get, and update operations.

**Technologies:** Spring Boot 3.2.2, Spring Data JPA, H2 Database, Lombok

**API Endpoints:**
- `POST /api/employees` - Save new employee
- `GET /api/employees/{id}` - Get employee by ID
- `GET /api/employees` - Get all employees
- `PUT /api/employees/{id}` - Update employee

See [sbemployee/README.md](sbemployee/README.md) for more details.

### sbmongo
Taco Cloud application with MongoDB data store.

### sbcassandra
Module with Cassandra data store.

### sbtacocloud
Taco Cloud application module.

