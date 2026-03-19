# 🎬 BookMyShow Backend (Spring Boot)

🚀 A complete backend system for a movie ticket booking platform built using **Spring Boot, JWT Authentication, and MySQL**.

---

## 🔗 Live Resources

* 📂 GitHub Repository:
  https://github.com/Jaishu07/BookMyShowBackend.git

* 📬 Postman API Collection:
  https://.postman.co/workspace/My-Workspace~b5d922e3-93c9-49af-8596-6d122495ec71/collection/40004693-9cd82cbd-57bc-4f18-a685-43b93aaa382d?action=share&creator=40004693

---

## 🚀 Features

* 🔐 JWT Authentication (Signup/Login)
* 👑 Role-Based Authorization (ADMIN / USER)
* 🎬 Movie Management
* 🏢 Theater Management
* 🎭 Show Scheduling
* 💺 Seat Management (Show-wise)
* 🎟️ Ticket Booking System
* 💳 Payment Simulation
* ❌ Booking Cancellation with Seat Restore

---

## 🛠️ Tech Stack

* Java 21
* Spring Boot 3
* Spring Security
* JWT (JSON Web Token)
* Spring Data JPA (Hibernate)
* MySQL
* Maven
* Lombok

---

## 📁 Project Structure

```plaintext
com.cfs.BookMyShow
│
├── controller
├── service
├── repository
├── model
├── dto
├── security
├── config
└── exception
```

---

## 🔐 Authentication Flow

```plaintext
Signup → Login → JWT Token → Secure APIs
```

### 🔑 Authorization Header

```http
Authorization: Bearer <TOKEN>
```

---

## 📌 API Endpoints

### 🔐 Auth APIs

| Method | Endpoint         | Description     |
| ------ | ---------------- | --------------- |
| POST   | /api/auth/signup | Register user   |
| POST   | /api/auth/login  | Login & get JWT |

---

### 🎬 Movie APIs

| Method | Endpoint         |
| ------ | ---------------- |
| POST   | /api/movies      |
| GET    | /api/movies      |
| GET    | /api/movies/{id} |

---

### 🏢 Theater APIs

| Method | Endpoint                  |
| ------ | ------------------------- |
| POST   | /api/theaters             |
| GET    | /api/theaters             |
| GET    | /api/theaters/city/{city} |

---

### 🎭 Show APIs

| Method | Endpoint                   |
| ------ | -------------------------- |
| POST   | /api/shows                 |
| GET    | /api/shows/movie/{movieId} |

---

### 💺 Seat APIs

| Method | Endpoint                 |
| ------ | ------------------------ |
| GET    | /api/seats/show/{showId} |

---

### 🎟️ Booking APIs

| Method | Endpoint                  |
| ------ | ------------------------- |
| POST   | /api/bookings             |
| GET    | /api/bookings/{id}        |
| PUT    | /api/bookings/{id}/cancel |

---

### 👤 User APIs

| Method | Endpoint        |
| ------ | --------------- |
| GET    | /api/users      |
| GET    | /api/users/{id} |

---

## 🔥 System Workflow

```plaintext
User Signup →
Login →
Generate JWT →
Create Movie →
Create Theater →
Create Show →
Seats Auto Generated →
Book Ticket →
Cancel Ticket →
Seats Restored
```

---

## ⚠️ Error Codes

| Code | Meaning                      |
| ---- | ---------------------------- |
| 401  | Unauthorized (Invalid Token) |
| 403  | Forbidden (Role Issue)       |
| 404  | Resource Not Found           |
| 500  | Server Error                 |

---

## 🧠 Key Concepts Used

* Layered Architecture (Controller → Service → Repository)
* DTO Mapping
* JWT Authentication
* Role-Based Access Control
* Transaction Management
* Seat Locking Concept

---

## 🚀 How to Run

1️⃣ Clone the repo

```bash
git clone https://github.com/Jaishu07/BookMyShowBackend.git
```

2️⃣ Configure MySQL in `application.properties`

3️⃣ Run project

```bash
mvn spring-boot:run
```

---

## 💀 Project Status

👉 Fully functional backend with authentication + booking system
👉 Can be extended with frontend (React)

---

## 👨‍💻 Author

**Yashwant Sharma**

---

## ⭐ Support

If you like this project, give it a ⭐ on GitHub!
