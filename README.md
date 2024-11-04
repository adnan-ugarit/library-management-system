# Library Management System Backend API

This is a Library Management System API built with Spring Boot, providing endpoints for managing books, patrons, and borrowing records.

* Features:

    - CRUD operations for books, patrons, and borrowing records
    - Basic authentication using JWT (if implemented)
    - Logging using AOP for key service methods
    - Caching for frequently accessed data
    - Transaction management for data consistency
    - Unit and integration tests.

* Prerequisites

    - Java 17 or higher
    - Maven for dependency management and building

* Setup Instructions

    Clone the repository:

    git clone https://github.com/adnan-ugarit/library-management-system.git
    then cd library-management-system


* Build the project:

bash> mvn clean install

Run the application:

bash> mvn spring-boot:run

The API will be available at http://localhost:8080/maids/swagger-ui/index.html

* API Endpoints:
Authentication
JWT Authentication requires a valid token for accessing endpoints. First, obtain a token:
- Endpoint:

    POST [Login](http://localhost:8080/maids/api/v1/auth/authenticate)

Request Body:

json

{
  "username": "admin",
  "password": "password123"
}
or
{
  "username": "user",
  "password": "password"
}

JsonResponse:

{
  "token": "your-jwt-token"
}

Use this token in the Authorization header (e.g., Authorization: Bearer <token>) to access secured endpoints.

- Optional:
you can access to H2 Database via:
- http://localhost:8080/maids/h2-console/
- username: user
- password: user

Good Luck!
