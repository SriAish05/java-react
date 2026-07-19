# Week 3 - Spring REST using Spring Boot 3
## DN 5.0 Deep Skilling - Java FSE

---

## Project Structure

```
Week3_Spring_REST_SpringBoot3/
│
├── 1_spring-rest-handson/               ← Spring Core XML-based Country Loading
│   ├── pom.xml
│   └── src/main/
│       ├── java/com/cognizant/springlearn/
│       │   ├── SpringLearnApplication.java   ← Main class
│       │   └── model/Country.java             ← Country POJO
│       └── resources/
│           ├── country.xml                    ← Spring XML config with Country beans
│           └── application.properties
│
├── 2_spring-rest-handson/               ← Hello World + Country REST Web Service
│   ├── pom.xml
│   └── src/main/
│       ├── java/com/cognizant/springlearn/
│       │   ├── SpringRestHandsonApplication.java
│       │   ├── model/Country.java
│       │   ├── controller/
│       │   │   ├── HelloWorldController.java  ← /hello
│       │   │   └── CountryController.java     ← /country, /countries, /country/{code}
│       │   └── service/CountryService.java
│       └── resources/
│           ├── country.xml
│           └── application.properties
│
├── 3_spring-rest-handson/               ← Advanced REST (DTOs, Validation, HATEOAS, Actuator)
│   ├── pom.xml
│   └── src/main/
│       ├── java/com/cognizant/springlearn/
│       │   ├── SpringRestAdvancedApplication.java
│       │   ├── model/Country.java             ← With @NotBlank, @Size validation
│       │   ├── dto/CountryDTO.java             ← Data Transfer Object
│       │   ├── controller/CountryController.java  ← Full CRUD + HATEOAS
│       │   ├── service/CountryService.java
│       │   └── exception/
│       │       ├── CountryNotFoundException.java
│       │       └── GlobalExceptionHandler.java
│       └── resources/application.properties
│
└── 5_JWT-handson/                       ← JWT Authentication with Spring Security
    ├── pom.xml
    └── src/main/
        ├── java/com/cognizant/jwt/
        │   ├── JwtHandsonApplication.java
        │   ├── model/
        │   │   ├── AuthRequest.java            ← Login request
        │   │   └── AuthResponse.java           ← JWT response
        │   ├── controller/
        │   │   ├── AuthController.java         ← /authenticate
        │   │   └── ProtectedController.java    ← /hello, /profile
        │   ├── service/
        │   │   ├── JwtService.java             ← Creates & validates JWTs
        │   │   └── CustomUserDetailsService.java
        │   ├── config/SecurityConfig.java       ← Spring Security setup
        │   └── filter/JwtAuthFilter.java        ← Intercepts every request
        └── resources/application.properties
```

---

## Mandatory Hands-On Coverage

| Hands-On | Requirement | Project |
|----------|-------------|---------|
| 1 | Create a Spring Web Project using Maven | `1_spring-rest-handson` |
| 1 | Spring Core – Load Country from Spring Configuration XML | `1_spring-rest-handson` |
| 2 | Hello World RESTful Web Service | `2_spring-rest-handson` (/hello) |
| 2 | REST - Country Web Service | `2_spring-rest-handson` (/country, /countries) |
| 2 | REST - Get country based on country code | `2_spring-rest-handson` (/country/{code}) |
| 3 | All additional hands-on (DTOs, HATEOAS, Actuator, Validation) | `3_spring-rest-handson` |
| 5 | Create authentication service that returns JWT | `5_JWT-handson` |

---

## How to Run Each Project

### Project 1: XML-based Country Loading (Port 8081)
```bash
cd 1_spring-rest-handson
mvn clean spring-boot:run
```
Expected console output:
```
Country: Country [code=IN, name=India]
Country: Country [code=IN, name=India]
Country: Country [code=US, name=United States of America]
Country: Country [code=UK, name=United Kingdom]
...
```

### Project 2: Hello World + Country REST (Port 8080)
```bash
cd 2_spring-rest-handson
mvn clean spring-boot:run
```

Then open in browser:
| URL | Response |
|-----|----------|
| `http://localhost:8080/hello` | `Hello World!!` |
| `http://localhost:8080/country` | `{"code":"IN","name":"India"}` |
| `http://localhost:8080/countries` | List of 5 countries |
| `http://localhost:8080/country/IN` | India |
| `http://localhost:8080/country/US` | USA |

### Project 3: Advanced REST with CRUD (Port 8082)
```bash
cd 3_spring-rest-handson
mvn clean spring-boot:run
```

REST Endpoints:
| Method | URL | Purpose |
|--------|-----|---------|
| GET | `http://localhost:8082/api/countries` | Get all |
| GET | `http://localhost:8082/api/countries/IN` | Get one (with HATEOAS) |
| GET | `http://localhost:8082/api/countries/dto` | Get as DTOs |
| GET | `http://localhost:8082/api/countries/search?name=India` | Search by name |
| POST | `http://localhost:8082/api/countries` | Create (JSON body) |
| PUT | `http://localhost:8082/api/countries/IN` | Update |
| DELETE | `http://localhost:8082/api/countries/IN` | Delete |
| GET | `http://localhost:8082/actuator/health` | Health check |
| GET | `http://localhost:8082/actuator/info` | App info |
| GET | `http://localhost:8082/actuator/metrics` | Metrics |

Test POST with Postman/curl:
```bash
curl -X POST http://localhost:8082/api/countries \
  -H "Content-Type: application/json" \
  -d '{"code":"FR","name":"France"}'
```

Test validation (should return 400):
```bash
curl -X POST http://localhost:8082/api/countries \
  -H "Content-Type: application/json" \
  -d '{"code":"","name":""}'
```

### Project 4: JWT Authentication (Port 8083)
```bash
cd 5_JWT-handson
mvn clean spring-boot:run
```

**Step 1: Login to get a JWT**
```bash
curl -X POST http://localhost:8083/authenticate \
  -H "Content-Type: application/json" \
  -d '{"username":"aish","password":"cognizant"}'
```

Response:
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9....",
  "username": "aish",
  "type": "Bearer"
}
```

**Step 2: Use the JWT to call a protected endpoint**
```bash
curl http://localhost:8083/hello \
  -H "Authorization: Bearer <PASTE_TOKEN_HERE>"
```

Response: `Hello, aish! You are authenticated.`

**Without token:** Returns HTTP 403 Forbidden.

Available users:
| Username | Password |
|----------|----------|
| admin | admin123 |
| user | user123 |
| aish | cognizant |

---

## Running in IntelliJ

For each project:
1. **File → Open** → select the specific project folder (e.g. `2_spring-rest-handson`)
2. Wait for Maven to sync
3. Right-click the `*Application.java` main class → **Run**
4. Open browser or Postman to test the URLs

---

## Testing with Postman (Recommended)

Since Project 3 and Project 4 need POST/PUT/DELETE requests with JSON bodies and headers, use Postman:

1. Download Postman: https://www.postman.com/downloads/
2. Create a new request
3. Set method (GET/POST/PUT/DELETE)
4. Enter URL
5. For JSON body: Body tab → raw → JSON
6. For JWT: Headers tab → Key: `Authorization`, Value: `Bearer <token>`

---

## Push to GitHub

```bash
# From your local repo root
git add "Java FSE/Deepskilling/Week 3"
git commit -m "feat: Week 3 - Spring REST using Spring Boot 3"
git push origin main
```
