# Smart Attendance API — Spring Boot

## Tech Stack
- Java 21 + Spring Boot 3.5
- PostgreSQL
- Spring Data JPA
- Lombok + Validation

## Features
- Full CRUD for Users and Attendance
- Index-based queries (by ID, department, userId, status)
- DTO pattern — request/response separation
- Global exception handling

## Run Locally
1. Create PostgreSQL DB: attendance_db (port 5433)
2. Run: ./mvnw.cmd spring-boot:run
3. API runs at http://localhost:8080

## API Endpoints
### Users
- POST /api/users — Create user
- GET /api/users — Get all users
- GET /api/users/{id} — Get by ID
- GET /api/users/department/{dept} — Get by department
- PUT /api/users/{id} — Update user
- DELETE /api/users/{id} — Delete user

### Attendance
- POST /api/attendance — Mark attendance
- GET /api/attendance — Get all
- GET /api/attendance/{id} — Get by ID
- GET /api/attendance/user/{userId} — Get by user
- GET /api/attendance/status/{status} — Get by status
- GET /api/attendance/user/{userId}/status/{status} — Filter by user + status
- DELETE /api/attendance/{id} — Delete record
