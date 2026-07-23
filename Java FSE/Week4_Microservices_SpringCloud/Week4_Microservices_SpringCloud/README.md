# Week 4 - Microservices with Spring Boot 3 & Spring Cloud
## DN 5.0 Deep Skilling - Java FSE

---

## What This Project Demonstrates

A complete microservices system with 4 services showing every core concept:

```
                    ┌─────────────────┐
   Client  ───────> │  API Gateway    │  (Port 8080)
                    │  Single entry   │
                    └────────┬────────┘
                             │ routes & load-balances
              ┌──────────────┴──────────────┐
              ▼                              ▼
    ┌──────────────────┐         ┌──────────────────┐
    │ Account Service  │ <────── │  Loan Service    │
    │  (Port 8081)     │  Feign  │  (Port 8082)     │
    └────────┬─────────┘         └────────┬─────────┘
             │                            │
             │  both register with        │
             └──────────┬─────────────────┘
                        ▼
              ┌──────────────────┐
              │  Eureka Server   │  (Port 8761)
              │  Service Registry│
              └──────────────────┘
```

---

## The 4 Services

| Service | Port | Role |
|---------|------|------|
| `1_eureka-server` | 8761 | Service Registry - the "phone book" where all services register |
| `2_account-service` | 8081 | Manages bank accounts. Registers with Eureka. |
| `3_loan-service` | 8082 | Manages loans. Calls account-service via Feign. Has Circuit Breaker. |
| `4_api-gateway` | 8080 | Single entry point. Routes requests to services via Eureka. |

---

## Core Concepts Covered

| Concept | Where |
|---------|-------|
| Service Discovery (Eureka) | `1_eureka-server` + `@EnableEurekaServer` |
| Service Registration | Account & Loan services (eureka-client dependency) |
| Inter-Service Communication | `AccountClient` Feign interface in loan-service |
| Feign Declarative REST Client | `@FeignClient(name="account-service")` |
| Circuit Breaker (Resilience4j) | `@CircuitBreaker` in `LoanService` |
| Fallback Method | `fallbackGetLoan()` in `LoanService` |
| API Gateway | `4_api-gateway` with Spring Cloud Gateway |
| Load Balancing | `lb://account-service` in gateway routes |
| Actuator Monitoring | All services expose `/actuator` |

---

## HOW TO RUN (ORDER MATTERS!)

You MUST start them in this exact order. Wait for each to fully start before the next.

### 1. Start Eureka Server FIRST
```bash
cd 1_eureka-server
mvn spring-boot:run
```
Wait, then open **http://localhost:8761** — you'll see the Eureka dashboard (empty for now).

### 2. Start Account Service
```bash
cd 2_account-service
mvn spring-boot:run
```
Refresh the Eureka dashboard — `ACCOUNT-SERVICE` now appears under "Instances".

### 3. Start Loan Service
```bash
cd 3_loan-service
mvn spring-boot:run
```
Refresh Eureka — `LOAN-SERVICE` appears too.

### 4. Start API Gateway LAST
```bash
cd 4_api-gateway
mvn spring-boot:run
```

---

## Testing the System

### Direct service calls (bypass gateway):
```
http://localhost:8081/accounts/178849
http://localhost:8082/loans/178849
```

### Through the API Gateway (the real microservices way):
```
http://localhost:8080/accounts/178849
http://localhost:8080/loans/178849
```

Expected response for `/loans/178849`:
```json
{
  "number": "178849",
  "loanType": "car",
  "loanAmount": 500000.0,
  "account": {
    "number": "178849",
    "type": "savings",
    "balance": 25000.0
  }
}
```
The `account` part proves inter-service communication worked (loan-service called account-service via Feign).

### Test the Circuit Breaker:
1. Stop the account-service (Ctrl+C)
2. Call `http://localhost:8082/loans/178849` again
3. Instead of crashing, you get the fallback response with `"type": "UNAVAILABLE"`

---

## Monitoring Endpoints
```
http://localhost:8761              -> Eureka dashboard (all registered services)
http://localhost:8082/actuator/health -> Loan service health + circuit breaker state
```

---

## Sample Account Numbers (preloaded)
| Number | Type | Balance |
|--------|------|---------|
| 178849 | savings | 25000 |
| 178850 | current | 150000 |
| 178851 | savings | 8500 |

---

## Push to GitHub
```bash
git add "Java FSE/Week4_Microservices_SpringCloud"
git commit -m "feat: Week 4 - Microservices with Spring Boot 3 and Spring Cloud"
git push origin main
```
