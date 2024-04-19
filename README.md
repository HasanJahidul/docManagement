# DocManagement

## Description

This project is a Spring Boot Maven application designed for document management. It utilizes PostgreSQL as the database and includes JWT token authentication for security.

## Setup Instructions

1. **Clone Repository:**
   ```bash
   git clone <repository_url>
   cd docManagement
   ```

2. **Configure Database:**
   - Ensure PostgreSQL is installed and running.
   - Open `application.properties` file located in `src/main/resources`.
   - Modify the following properties to match your PostgreSQL setup:
     ```properties
     spring.datasource.driver-class-name=org.postgresql.Driver
     spring.jpa.hibernate.ddl-auto=update
     spring.datasource.url=jdbc:postgresql://<host>:<port>/<database_name>
     spring.datasource.username=<username>
     spring.datasource.password=<password>
     ```
     Replace `<host>`, `<port>`, `<database_name>`, `<username>`, and `<password>` with your PostgreSQL configuration.

3. **Logging Configuration:**
   - To display SQL statements, ensure the following properties are set in `application.properties`:
     ```properties
     spring.jpa.show-sql=true
     spring.jpa.properties.hibernate.format_sql=true
     ```

4. **JWT Configuration:**
   - Set the JWT secret and expiration time in `application.properties`:
     ```properties
     jwt.secret=<your_jwt_secret>
     jwt.expiration=<token_expiration_time_in_milliseconds>
     ```
     Replace `<your_jwt_secret>` with your desired JWT secret key and `<token_expiration_time_in_milliseconds>` with the desired token expiration time.

5. **Run Application:**
   - Navigate to the root directory of the project.
   - Run the following Maven command:
     ```bash
     mvn spring-boot:run
     ```

6. **Access Application:**
   - Once the application is running, it will be accessible at `http://localhost:8080`.
   - You can now interact with the DocManagement application.

## Notes

- Ensure you have Java and Maven installed on your system.
- Make sure the necessary dependencies are included in your `pom.xml` file.
- For production deployment, consider configuring additional security measures and optimizing application performance.
- Remember to keep sensitive information such as database credentials and JWT secrets secure. If you need to change these credentials later, modify them in the `application.properties` file accordingly.
