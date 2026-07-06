# Week 2 - Spring Core, Maven, Spring Data JPA & Hibernate
## DN 5.0 Deep Skilling - Java FSE

---

## Project Structure

```
Week2_Spring_Core_Maven_JPA_Hibernate/
│
├── Exercise1-8_LibraryManagement/          ← Spring Core + Maven (Plain Spring)
│   ├── pom.xml                             ← Maven config with Spring dependencies
│   └── src/main/java/com/library/
│       ├── LibraryManagementApplication.java  ← Main class
│       ├── repository/BookRepository.java     ← Data access layer
│       ├── service/BookService.java           ← Business logic + DI
│       └── aspect/LoggingAspect.java          ← AOP logging
│
├── Exercise9_LibraryManagement_SpringBoot/ ← Spring Boot version
│   ├── pom.xml
│   └── src/main/java/com/library/
│       ├── LibraryManagementApplication.java
│       ├── model/Book.java                    ← JPA Entity
│       ├── repository/BookRepository.java     ← Spring Data JPA
│       └── controller/BookController.java     ← REST API
│
└── Exercise1-10_EmployeeManagementSystem/  ← Spring Data JPA + Hibernate
    ├── pom.xml
    └── src/main/java/com/employee/
        ├── EmployeeManagementSystemApplication.java
        ├── model/
        │   ├── Employee.java                  ← JPA Entity + Auditing
        │   └── Department.java                ← One-to-Many relationship
        ├── repository/
        │   ├── EmployeeRepository.java        ← Query methods, @Query, pagination
        │   └── DepartmentRepository.java
        ├── service/
        │   ├── EmployeeService.java
        │   └── DepartmentService.java
        ├── controller/
        │   ├── EmployeeController.java        ← REST CRUD + pagination + projections
        │   └── DepartmentController.java
        ├── projection/
        │   ├── EmployeeNameEmail.java         ← Interface projection
        │   └── EmployeeSummary.java           ← Class projection (DTO)
        └── config/
            ├── AuditConfig.java               ← Auditing configuration
            └── DataSourceConfig.java          ← Data source customization
```

---

## Exercise-to-File Mapping

### Spring Core and Maven (MANDATORY: Ex 1, 2, 4)

| Exercise | Topic | Key Files |
|----------|-------|-----------|
| 1 | Configuring a Basic Spring Application | `pom.xml`, `applicationContext.xml`, `BookService.java`, `BookRepository.java`, `LibraryManagementApplication.java` |
| 2 | Implementing Dependency Injection | `BookService.java` (setter method), `applicationContext.xml` (wiring) |
| 3 | Implementing Logging with Spring AOP | `LoggingAspect.java`, `pom.xml` (AOP dep) |
| 4 | Creating and Configuring a Maven Project | `pom.xml` (compiler plugin, dependencies) |
| 5 | Configuring the Spring IoC Container | `applicationContext.xml`, `BookService.java` |
| 6 | Configuring Beans with Annotations | `@Service`, `@Repository`, component-scan in XML |
| 7 | Constructor and Setter Injection | `BookService.java` (both constructors) |
| 8 | Basic AOP with Spring | `LoggingAspect.java` (Before, After, Around advice) |
| 9 | Creating a Spring Boot Application | Entire `Exercise9_LibraryManagement_SpringBoot/` project |

### Spring Data JPA with Spring Boot, Hibernate (MANDATORY: Ex 1-2 from handson docs)

| Exercise | Topic | Key Files |
|----------|-------|-----------|
| 1 | Overview and Setup | `pom.xml`, `application.properties` |
| 2 | Creating Entities | `Employee.java`, `Department.java` (entity mapping, relationships) |
| 3 | Creating Repositories | `EmployeeRepository.java`, `DepartmentRepository.java` |
| 4 | CRUD Operations | `EmployeeController.java`, `DepartmentController.java` |
| 5 | Query Methods & Named Queries | `EmployeeRepository.java` (@Query, @NamedQuery) |
| 6 | Pagination and Sorting | `EmployeeController.java` (`/paginated` endpoint) |
| 7 | Entity Auditing | `Employee.java` (@CreatedDate etc), `AuditConfig.java` |
| 8 | Projections | `EmployeeNameEmail.java`, `EmployeeSummary.java` |
| 9 | Data Source Configuration | `DataSourceConfig.java`, `application.properties` |
| 10 | Hibernate-Specific Features | `application.properties` (batch, dialect), `Employee.java` |

---

## How to Push to GitHub

```bash
# 1. Open Terminal / Command Prompt

# 2. Navigate to your project folder
cd Week2_Spring_Core_Maven_JPA_Hibernate

# 3. Initialize Git (if not already done)
git init

# 4. Add all files
git add .

# 5. Commit
git commit -m "Week 2: Spring Core, Maven, Spring Data JPA, Hibernate - All Exercises"

# 6. Add your GitHub remote (replace with your repo URL)
git remote add origin https://github.com/YOUR_USERNAME/Digital-Nurture-DeepSkilling.git

# 7. Push
git push -u origin main
```

---

## How to Run in IntelliJ IDEA

### Exercise 1-8 (Plain Spring):
1. Open IntelliJ → File → Open → Select `Exercise1-8_LibraryManagement` folder
2. Wait for Maven to download dependencies
3. Run `LibraryManagementApplication.java`

### Exercise 9 (Spring Boot):
1. Open `Exercise9_LibraryManagement_SpringBoot` folder in IntelliJ
2. Run `LibraryManagementApplication.java`
3. Test endpoints at `http://localhost:8080/api/books`

### EmployeeManagementSystem (Spring Data JPA + Hibernate):
1. Open `Exercise1-10_EmployeeManagementSystem` folder in IntelliJ
2. Run `EmployeeManagementSystemApplication.java`
3. H2 Console: `http://localhost:8080/h2-console`
4. REST API: `http://localhost:8080/api/employees`

---

## REST API Endpoints (EmployeeManagementSystem)

| Method | URL | Description |
|--------|-----|-------------|
| GET | `/api/employees` | Get all employees |
| GET | `/api/employees/{id}` | Get employee by ID |
| POST | `/api/employees` | Create employee |
| PUT | `/api/employees/{id}` | Update employee |
| DELETE | `/api/employees/{id}` | Delete employee |
| GET | `/api/employees/search?keyword=` | Search employees |
| GET | `/api/employees/paginated?page=0&size=5&sortBy=name` | Paginated list |
| GET | `/api/employees/names-emails` | Projections |
| GET | `/api/employees/summaries` | DTO projections |
| POST | `/api/employees/batch` | Batch insert |
| GET | `/api/departments` | Get all departments |
| POST | `/api/departments` | Create department |
