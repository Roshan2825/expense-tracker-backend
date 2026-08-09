# Expense Tracker Backend

A secure and scalable backend application for tracking personal expenses, built using Spring Boot and JWT authentication.

##  Features
- User registration & login with JWT authentication
- Secure REST APIs with Spring Security
- Expense CRUD operations
- Auditing enabled using Spring Data JPA (createdAt, updatedAt)
- Pagination & sorting for expense listing
- Swagger UI for testing endpoints
- Role-based access control
- Global exception handling
- Clean layered architecture (Controller, Dto, Service, Repository)

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
3. JWT must be sent in Authorization header

## API Highlights
- `POST /auth/register` — register a new user
- `POST /auth/login` — login, returns JWT in `ApiResponse.data.token`
- `GET /api/users/me` — get current user profile (requires Bearer token)
- `POST /api/expense/my` — create an expense
- `GET /api/expense/my?page=0&size=10&sortBy=createdAt` — list my expenses (paged & sorted)
- `PATCH /api/expense/{id}` — update an expense
- `DELETE /api/expense/{id}` — delete an expense

## Run Locally
1. Clone repo
2. Configure MySQL via environment variables (or set defaults in `application.properties`):
   - `DB_URL` — e.g. `jdbc:mysql://localhost:3306/expense_tracker`
   - `DB_USERNAME` — database user
   - `DB_PASSWORD` — database password
   - `JWT_SECRET` — secret key used to sign JWT tokens (min 32 bytes for HS256)
   - `JWT_EXPIRATION` — token lifetime in milliseconds (default `86400000` = 1 day)
3. Run:
```bash
mvn spring-boot:run
```

