# EasyTrip-APP

RESTful API for EasyTrip Travel Booking Application using Spring Boot.  
This project implements a backend service to handle customer bookings, cancellations and trip completion with email notifications.

---

## 🔍 About

EasyTrip-APP is a Java Spring Boot application that provides REST endpoints for a travel/cab booking system. It supports operations such as booking confirmation, booking cancellation, trip completion, and integrates email notifications for customers. The API returns JSON responses and is ready to be consumed by mobile or web clients. :contentReference[oaicite:3]{index=3}

---

## 🚀 Features

✔ Book a cab trip  
✔ Cancel a booking  
✔ Complete a trip  
✔ Dynamic email notifications  
✔ Role-based access (Customer, Driver, Admin)  
✔ Clean REST APIs  
✔ Uses Spring Data JPA for persistence  
✔ Exception handling and validation

---

## 🛠 Tech Stack

- **Java**  
- **Spring Boot**  
- **Spring MVC / Spring Data JPA**  
- **Hibernate**  
- **MySQL / H2 (optional)**  
- **JavaMailSender (email)**  
- **Maven**  
- **Postman / Swagger (for testing)**

---

## 📂 Project Structure

```text
src/
├── main/
│   ├── java/
│   │   └── com/easetrip/
│   │       ├── controller/
│   │       ├── service/
│   │       ├── repository/
│   │       ├── model/
│   │       └── exception/
│   └── resources/
│       └── application.properties
```````

## 📌 API Endpoints

Booking APIs
Method	Endpoint	Description
POST	/bookings	Create new booking
GET	/bookings/{id}	Get booking by ID
DELETE	/bookings/{id}	Cancel booking
PUT	/bookings/{id}/complete	Complete trip

## 📧 Email Notification

The application sends email notifications to customers for:

- Booking Confirmation

- Booking Completion

- Booking Cancellation

Email templates are generated dynamically based on booking status.

## 🗄 Database Configuration

Configure database in  ```application.properties```:
```
spring.datasource.url=jdbc:mysql://localhost:3306/easetripdb
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## ⚙ How to Run the Project

### 1️⃣ Clone repository

git clone``` https://github.com/mohdsajid9600/easetrip-app.git```


### 2️⃣ Go to project directory

```cd easetrip-app```


### 3️⃣ Build project

```mvn clean install```


### 4️⃣ Run application

```mvn spring-boot:run```


## Application will run on:

```http://localhost:8080```

## 🧪 API Testing

Use Postman or Swagger UI to test APIs.

Example:

POST /bookings

## 📈 Future Enhancements

**✔ JWT Authentication**
**✔ Swagger Documentation**
**✔ Payment Gateway Integration**
**✔ Spring Security**
**✔ Frontend (Angular/React)**

## 👨‍💻 Developer

**Mohd Sajid
Java Backend Developer (Spring Boot)**

## 📄 License

This project is developed for learning and practice purposes.


