 Tasks Microservices Project

Student Name: Mostafa Asem Aljbali  
Student ID: [120181596]  
Course: Advanced Software Engineering  
Chapter: 5 – Microservices Homework & Project  

---

## Overview

هذا المشروع يحتوي على **خدمتين مصغرتين (Microservices)**:

1. **User Service** – لإدارة المستخدمين.  
2. **Task Service** – لإدارة المهام وربطها بالمستخدمين.  

كل خدمة تعمل بشكل مستقل ويمكن تشغيلها باستخدام Spring Boot وMaven.

---

## Folder Structure
tasks-microservices/
├── user-service/ # Service لإدارة المستخدمين
├── task-service/ # Service لإدارة المهام
└── README.md
---

## Prerequisites

- Java 17 أو أحدث  
- Maven  
- Git (لرفع المشروع على GitHub)  

---

## Running the Services

### 1️⃣ User Service

```bash
cd user-service
mvn clean install
mvn spring-boot:run

| Method | Endpoint    | Description         |
| ------ | ----------- | ------------------- |
| POST   | /users      | إنشاء مستخدم جديد   |
| GET    | /users      | جلب جميع المستخدمين |
| GET    | /users/{id} | جلب مستخدم واحد     |

```
##Task Service
cd task-service
mvn clean install
mvn spring-boot:run
| Method | Endpoint    | Description      |
| ------ | ----------- | ---------------- |
| POST   | /tasks      | إنشاء مهمة جديدة |
| GET    | /tasks      | جلب جميع المهام  |
| GET    | /tasks/{id} | جلب مهمة واحدة   |

Example Requests
إنشاء مستخدمcurl -X POST http://localhost:8081/users \
-H "Content-Type: application/json" \
-d '{
  "id": "u123",
  "username": "mostafa",
  "email": "m@m.com"
}'
إنشاء مهمة مرتبطة بالمستخدم
curl -X POST http://localhost:8082/tasks \
-H "Content-Type: application/json" \
-d '{
  "title": "Finish homework",
  "ownerId": "u123"
}'



