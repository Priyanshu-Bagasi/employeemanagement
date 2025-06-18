# 🧑‍💼 Employee Management System (Spring Boot)

A RESTful API for managing employee records, built with **Spring Boot**, **Spring Data JPA**, **Spring Security**, and integrated with a **PostgreSQL** database. It also features Swagger UI documentation and is deployed on **Render** using Docker.

## 🚀 Live Demo

👉 [Deployed App on Render](https://employeemanagement-yqgb.onrender.com/)  
👉 Redirects to [Swagger UI](https://employeemanagement-yqgb.onrender.com/swagger-ui.html)

---

## 📌 Features

- ✅ Create, Read, Update, Delete (CRUD) for employees
- 🔐 Role-based authentication using Spring Security
- 🔄 Global exception handling
- 🧪 Unit & integration tests (JUnit + Mockito + MockMvc)
- 🌐 API documentation using Swagger (OpenAPI)
- 🐘 PostgreSQL integration with Docker
- 📦 Containerized with Docker & Docker Compose
- ☁️ Deployment on [Render](https://render.com/)

---

## 📁 Tech Stack

- **Backend:** Java, Spring Boot
- **Database:** PostgreSQL (Dockerized)
- **ORM:** Spring Data JPA, Hibernate
- **Security:** Spring Security
- **Testing:** JUnit 5, Mockito, MockMvc
- **Documentation:** SpringDoc Swagger UI
- **Deployment:** Docker, Docker Compose, Render

---

## ⚙️ API Endpoints

Visit [Swagger UI](https://employeemanagement-yqgb.onrender.com/swagger-ui.html) for full API documentation.

Example endpoints:
- `GET /api/employees`
- `GET /api/employees/{id}`
- `POST /api/employees`
- `PUT /api/employees/{id}`
- `DELETE /api/employees/{id}`

---

## 📦 Running Locally

### 1. Clone the repository
```bash
git clone https://github.com/your-username/employeemanagement.git
cd employeemanagement
2. Start using Docker
Make sure Docker is running on your system.

bash
Copy
Edit
docker-compose up --build
The backend will be available at http://localhost:8080/swagger-ui.html.

🔐 Role-Based Authentication
Admin can create/update/delete employees.

User can only view employee details.

Sample security config is set using WebSecurityConfigurerAdapter.

---

🧪 Testing
Run all tests:

mvn test
Technologies used:

@SpringBootTest

@WebMvcTest

@MockBean, @WithMockUser

🌍 Deployment
The app is deployed to Render using a multi-stage Docker build with PostgreSQL DB hosted via Render's managed PostgreSQL service.

Dockerized using:

Dockerfile

docker-compose.yml
---

🙋‍♂️ Author
Priyanshu Bagasi
GitHub • LinkedIn
