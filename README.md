# 👨‍💻 Employee Management REST API

This is a Spring Boot-based backend application that provides a RESTful API for managing employees. It supports CRUD operations, role-based access control, Docker containerization, PostgreSQL integration, and deployment to Render.

---

##  🚀 Features

- Create, read, update, delete employee records
- Role-Based Authorization using Spring Security
  - Admin: Full access (create/update/delete/view)
  - User: Read-only access
- Input validation and centralized exception handling
- RESTful endpoints documented using Swagger UI
- Dockerized using multi-stage build
- PostgreSQL database hosted on Render
- Deployed to Render Web Service
- Unit and integration tests with JUnit, Mockito, and Spring Test

---

## 📦 Technologies Used

- Java 17
- Spring Boot 3
- Spring Data JPA
- Spring Security
- PostgreSQL
- Docker & Docker Compose
- JUnit 5
- Mockito
- Swagger (springdoc-openapi)
- Render.com (deployment)

---

## 🔧 Getting Started Locally

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/your-username/employeemanagement.git
cd employeemanagement
```

### 2️⃣ Run with Docker

Ensure Docker is running on your system.

```bash
docker-compose up --build
```

---

### API Documentation
Swagger UI will be available at:

```bash
http://localhost:8080/swagger-ui.html
```
You can test all endpoints including role-based access directly from there.

---

### 🔐 Role-Based Authentication

👤 Admin Role:
Create, Update, Delete, View Employees

👥 User Role:
View Employees only

---

### 🧪 Testing
Run all tests using:

```bash
mvn test
```

🔍 Test Frameworks and Tools:

- @SpringBootTest
- @WebMvcTest
- @MockBean
- @WithMockUser

---

### 🚢 Deployment
The app is deployed to Render using:

- Docker multi-stage build
- PostgreSQL managed via Render’s dashboard

### 🔧 Files used:
- Dockerfile
- docker-compose.yml

---

### Author
Priyanshu Bagasi - https://github.com/Priyanshu-Bagasi
