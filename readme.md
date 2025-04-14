# Appointment Service – HMS Microservices

This is the `appointment-service` of the Hospital Management System (HMS), responsible for managing doctor appointments.

---

## 🧰 Prerequisites

Before running the project, ensure you have the following installed:

- **Java**: Version 17 or higher
- **Maven**: Version 3.5+
- **PostgreSQL**
- **Git**: For cloning the repository
- **IDE**: IntelliJ IDEA, Eclipse, STS, or Visual Studio Code

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/tantruongg23/apppointment-service
cd apppointment-service
```

### 2. Set Up PostgreSQL Database
Run the following SQL files in order using your preferred SQL tool (like `psql`, `DBeaver`, or `PgAdmin`):

   - **Schema Definition**: `ddl.sql`  
     This file creates the necessary tables and structure.
   - **Initial Data**: `init_data_202504141815.sql`  
     This file inserts some sample data into the tables.

### 3. Configure `application.yml` (Optional)

Ensure your PostgreSQL connection settings are correct in `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/postgres
    username: your_postgres_user
    password: your_postgres_password
```

### 4. Build and Run the Project

#### Using Maven Wrapper

```bash
./mvnw clean install
./mvnw spring-boot:run
```

#### Or if Maven is Installed Globally

```bash
mvn clean install
mvn spring-boot:run
```

The service will start on [http://localhost:8091](http://localhost:8091) by default.

---

## 📦 API Overview

Here are some endpoints available in this service (check the controller for exact details):

[localhost:8091/swagger-ui/index.html](localhost:8091/swagger-ui/index.html)

You can test these APIs using tools like **Postman**, **Insomnia**, or any HTTP client.

---

## 🧪 Testing

Run unit and integration tests using:

```bash
mvn test
```

---

## 📁 Project Structure

```plaintext
appointment-service/
├── src/
│   ├── main/
│   │   ├── java/vn/tayjava/
│   │   └── resources/
│   │       ├── application.yml
│   │       └── ...
├── ddl.sql
├── init_data_202504141815.sql
├── pom.xml
└── README.md
```

---

## 📌 Notes

- This service is part of a larger HMS microservices architecture.
- You may need to configure service discovery, gateway, and other services separately for full functionality.
- Ensure proper security configurations (e.g., authentication, authorization) are in place for production environments.

---

## 🛠️ Troubleshooting

### Common Issues

1. **Database Connection Error**  
   Ensure PostgreSQL is running and the connection details in `application.yml` are correct.

2. **Port Conflict**  
   If port `8080` is already in use, update the `server.port` property in `application.yml`:

   ```yaml
   server:
     port: 8091
   ```

3. **Maven Build Issues**  
   Ensure you have the correct Maven version installed and dependencies are resolved.

---

## 📚 References

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
- [Maven Documentation](https://maven.apache.org/)

---

Feel free to contribute to this project by submitting issues or pull requests on the [GitHub repository](https://github.com/tantruongg23/appointment-service).
