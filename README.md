# WalkWay — Microservices-Based E-Commerce Backend

**Note:** This is a dummy/learning project for personal practice.   
WalkWay  is a **containerized, secure, and scalable microservices backend** for an e-commerce platform, built with **Spring Boot**, **Spring Cloud**, **Keycloak**, **Kafka**, and **MySQL**.
It implements **service-to-service security, RBAC, OAuth2 PKCE authentication**, and real-time async communication, with full observability via the **Grafana stack**.

---

## 🏗 Architecture Overview

All services run in isolated Docker containers and communicate over a private network for security.  
Key highlights:
- **Service Discovery:** Eureka Service Registry
- **API Gateway:** Spring Cloud Gateway (stateful, OAuth2 PKCE)
- **Security:** OAuth2 + Keycloak (IdP), Realm Roles, Client Roles, and RBAC at service & route level
- **Sync + Async Communication:** OpenFeign for sync calls, Kafka + Avro schema for async
- **Observability:** Alloy + Loki + Prometheus + Grafana
- **Swagger UI Gateway Aggregation:** Centralized documentation served via the Gateway, with per-service OAuth2 secured API docs

---

## 📦 Microservices

### 1️⃣ Account Service
- Stores **user account metadata** (profile, addresses, etc.)
- Registers user credentials in **Keycloak Realm** (roles: `BUYER`, `USER`, `ADMIN` and maps the Client Roles accordingly)
- Meta data stored in `accountdb` MySQL
- Communicates synchronously with Keycloak using **OpenFeign**
- **Current feature:** Create user (update/delete yet to be implemented)

---

### 2️⃣ Product Service
- Handles **CRUD operations** for product catalog
- Stores product details, categories, sizes, colors, and images in `productdb` MySQL
- Publishes product snapshots to **Inventory Service** via Kafka when products are created/deleted

---

### 3️⃣ Inventory Service
- Tracks **warehouses, locations, product stock**, and stock transactions
- CRUD operations on inventory
- Receives product snapshots asynchronously from Product Service
- `inventorydb` MySQL backend

---

### 4️⃣ Service Registry
- **Eureka Service Registry** for service discovery and load balancing

---

### 5️⃣ Edge Service (Gateway)
- Reactive **Spring Cloud Gateway**
- Stateful OAuth2 PKCE login via Keycloak
- Maps `JSESSIONID` to JWT and relays it to downstream services
- Hosts Swagger UI that fetches docs from downstream services

---

## 🔐 Security Model
- **OAuth2 PKCE** login flow via Gateway
- Role-based access control (RBAC) with **minimum privilege** principle
- Service-to-service calls secured within Docker network
- Per-service OAuth2 clients with their own roles mapped to realm roles

---

## 📡 Communication Pattern
- **Sync:** Account Service ↔ Keycloak (OpenFeign with Error Handling)
- **Async:** Product Service → Inventory Service (Kafka + Avro schemas from `kafka-events` module with Error Handling for Serialization and configured Dead Letter Topic)

---

## 📊 Monitoring
- **Alloy** scrapes logs & metrics → **Loki** & **Prometheus** → **Grafana Dashboards**
- Fully containerized for easy deployment & scaling

---