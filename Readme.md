# Expense Tracker Backend

A secure and scalable backend application for tracking personal expenses, built using Spring Boot and JWT authentication.

##  Features
- User registration & login with JWT authentication
- Secure REST APIs with Spring Security
- Expense CRUD operations
- Auditing enabled using Spring Data JPA (createdAt, updatedAt)
- Pagination & sorting for expense listing
- Role-based access control
- Global exception handling
- Clean layered architecture (Controller, Service, Repository)

##  Tech Stack
- Java 21
- Spring Boot
- Spring Security (JWT)
- Spring Data JPA
- MySQL
- Maven
- Git

## Project Structure
- controller – REST APIs
- service – business logic
- repository – database access
- dto – request/response objects
- security – JWT & filters
- exception – global error handling

## Authentication Flow
1. User registers
2. User logs in → receives JWT
3. JWT must be sent in Authorization header:

## 📄 API Highlights
- `POST /api/users/register`
- `POST /auth/login`
- `GET /api/users/me`
- `GET /expenses?page=0&size=10&sort=createdAt,desc`
- `POST /api/expense/my`
- `PATCH /api/expense/{id}`
- `DELETE /api/expense/{id}`

## ⚙️ Run Locally
1. Clone repo
2. Configure MySQL in `application.properties`
3. Run:
```bash
mvn spring-boot:run
