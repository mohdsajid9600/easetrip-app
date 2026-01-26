# EasyTrip-APP

EasyTrip is a RESTful backend application built using Spring Boot for a cab booking system.  
It provides APIs to manage customers, drivers, cabs, and bookings with support for booking creation, cancellation, and trip completion along with email notifications.

## 🔍 About Project

EasyTrip is a backend RESTful web application developed using Spring Boot for managing a cab booking system.
This project is designed to handle complete cab booking operations including customer management, driver management, cab management, booking process and admin controls.

The system allows customers to book cabs, drivers to complete rides, and admins to manage customers, cabs, and bookings efficiently.
It follows a clean layered architecture with Controller, Service, Repository, DTO and Transformer layers to maintain scalability and readability.

The application also **supports email notifications** to inform customers about booking **confirmation**, **cancellation**, and **completion**.
**Swagger UI is integrated** for easy API testing and documentation.

**This project is suitable for demonstrating real-world backend development concepts such as:**

- REST API design
- Entity–DTO transformation
- Status based filtering
- Exception handling globally
- Role based operations (Admin, Driver, Customer)

## 🚀 Features

### ✔ 👤 Customer Management

- Customer registration
- Update customer profile
- Fetch customer details
- Search customers by different criteria
- View customer bookings (all, active, completed, cancelled)

### ✔ 🚗 Driver Management

- Driver registration
- Update driver details
- Fetch driver details
- Search drivers
- View driver bookings (all, in-progress, completed, cancelled)

### ✔ 🚕 Cab Management

- Register cab for a driver
- Update cab details
- Fetch available cabs for booking
- View all cabs (active, inactive, available, unavailable)
- Search cabs by different parameters

### ✔ 📖 Booking Management

- Create new booking by customer
- Update booking details
- Cancel booking by customer
- Complete booking by driver
- Fetch bookings by customer
- Fetch bookings by driver
- Fetch bookings by status (active, completed, cancelled)

### ✔ 🛡️ Admin Panel Features

- Activate / deactivate drivers
- Activate / deactivate customers
- View all drivers (active & inactive)
- View all customers (active & inactive)
- View all cabs (active, inactive, available, unavailable)
- View all bookings
- Filter bookings by driver or customer
- Filter bookings by status (active, completed, cancelled)
- Search drivers, customers, cabs and bookings

### ✔ 📧 Email Notification System

- Send email on booking confirmation
- Send email on booking completion
- Send email on booking cancellation

### ✔ ⚙️ Technical Features

- RESTful API design
- DTO based architecture
- Entity to DTO transformation
- Global exception handling
- Status-based filtering using Enums
- Clean layered architecture (Controller, Service, Repository, Model)
- Swagger UI for API documentation

## 🛠 Tech Stack

- **Java**  
- **Spring Boot**  
- **Spring MVC / Spring Data JPA**  
- **Hibernate ORM**  
- **MySQL Database / H2 (optional)**  
- **JavaMailSender (email)**
- **RESTful APIs**
- **Maven**
- **Lombok**
- **Postman / Swagger (for testing)**

## 📂 Project Structure

```
easetrip
│
├── .idea
├── .mvn
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.sajidtech.easytrip
│   │   │       ├── controller
│   │   │       ├── dto
│   │   │       ├── emails
│   │   │       ├── enums
│   │   │       ├── exception
│   │   │       ├── model
│   │   │       ├── repository
│   │   │       ├── service
│   │   │       ├── transformer
│   │   │       └── EasytripApplication.java
│   │   │
│   │   └── resources
│   │       ├── static
│   │       ├── templates
│   │       └── application.properties
│   │
│   └── test
│
├── target
│
├── .gitattributes
├── .gitignore
├── HELP.md
├── mvnw
├── mvnw.cmd
└── pom.xml
```````
## 📂 Package Description

### 🔹 controller
Contains all REST APIs.
Handles incoming HTTP requests and sends responses.

### 🔹 dto
Data Transfer Objects used to transfer data between client and server.
Used for request and response payloads.

### 🔹 emails
Contains email templates and email related logic (booking confirmation, cancellation, etc.).

### 🔹 enums
Contains all enums like:
BookingStatus, UserRole, PaymentStatus, etc.

### 🔹 exception
Custom exception classes and global exception handling logic.

### 🔹 model
Contains all JPA entity classes which map with database tables.

### 🔹 repository
Contains Spring Data JPA repository interfaces for database operations.

### 🔹 service
Contains business logic of the application.

### 🔹 transformer
Used to convert Entity → DTO and DTO → Entity.

## 📂 Resources Folder

### 🔹 application.properties
Contains database configuration and application settings.

## ⚙️ Other Important Files
### 🔹 pom.xml
Maven configuration file containing dependencies and plugins.

## 📧 Email Notification

The application sends email notifications to customers for:

- Booking Confirmation

- Booking Completion

- Booking Cancellation

Email templates are generated dynamically based on booking status.

## 🗄 Database Configuration

Configure database in  ```application.properties```:
```
spring.datasource.url=jdbc:mysql://localhost:3306/easytrip_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

```

## ⚙ How to Run the Project

 **1️. Clone repository**
```  git clone https://github.com/mohdsajid9600/easetrip-app.git  ```

**2️. Open project in IntelliJ / Eclipse**

**3. Configure database in application.properties**

**4. Run the application**

**5. Go to project directory**
```  cd easetrip-app  ```

**6. Build project**
```  mvn clean install  ```

**7. Run application**
```  mvn spring-boot:run  ```

## Application will run on:

```  http://localhost:8080  ```

## 🧪 API Testing

Use Postman or Swagger UI to test APIs.

## 👤 Customer APIs

| Method | Endpoint                                        | Description                                                                      |
| ------ | ----------------------------------------------- | -------------------------------------------------------------------------------- |
| PUT    | `/customers/customer/{id}/update`               | Update an existing customer’s details using customer ID.                         |
| POST   | `/customers/register/customer`                  | Register a new customer in the system.                                           |
| GET    | `/customers/search`                             | Search customers based on given parameters (e.g. gender, age, etc.).             |
| GET    | `/customers/search/greater`                     | Search customers whose age is greater than a given value. (e.g. ?age=25)         |
| GET    | `/customers/customer`                           | Get registered customer by id.                                                   |
| GET    | `/customers/customer/{id}/bookings`             | Get all bookings of a specific customer.                                         |
| GET    | `/customers/customer/{id}/bookings/in-progress` | Get all **in-progress bookings** of a specific customer.                         |
| GET    | `/customers/customer/{id}/bookings/completed`   | Get all **completed bookings** of a specific customer.                           |
| GET    | `/customers/customer/{id}/bookings/cancelled`   | Get all **cancelled bookings** of a specific customer.                           |
| DELETE | `/customers/customer/{id}/delete`               | Delete a customer from the system using customer ID.                             |

## 🚗 Driver APIs

| Method | Endpoint                                    | Description                                             |
| ------ | ------------------------------------------- | ------------------------------------------------------- |
| PUT    | `/drivers/driver/{id}/update`               | Update an existing driver’s details using driver ID.    |
| POST   | `/drivers/register/driver`                  | Register a new driver in the system.                    |
| GET    | `/drivers/driver`                           | Get registered drivers by its id (?id=6453).            |
| GET    | `/drivers/driver/{id}/bookings`             | Get all bookings assigned to a specific driver.         |
| GET    | `/drivers/driver/{id}/bookings/in-progress` | Get all **in-progress bookings** for a specific driver. |
| GET    | `/drivers/driver/{id}/bookings/completed`   | Get all **completed bookings** for a specific driver.   |
| GET    | `/drivers/driver/{id}/bookings/cancelled`   | Get all **cancelled bookings** for a specific driver.   |
| DELETE | `/drivers/driver/{id}`                      | Delete a driver from the system using driver ID.        |

## 🚕 Cab APIs

| Method | Endpoint                    | Description                                                       |
| ------ | --------------------------- | ----------------------------------------------------------------- |
| PUT    | `/cab/driver/{id}/update`   | Update cab details assigned to a specific driver using driver ID. |
| POST   | `/cab/driver/{id}/register` | Register a new cab for a specific driver.                         |
| GET    | `/cab/search/available`     | Get list of all available cabs for booking.                       |

## 📖 Booking APIs

| Method | Endpoint                        | Description                                                |
| ------ | ------------------------------- | ---------------------------------------------------------- |
| PUT    | `/booking/driver/{id}/complete` | Mark a booking as **completed** by driver using driver ID. |
| PUT    | `/booking/customer/{id}/update` | Update booking details by customer using booking ID.       |
| PUT    | `/booking/customer/{id}/cancel` | Cancel an existing booking by customer using booking ID.   |
| POST   | `/booking/customer/{id}/booked` | Create a new booking for a customer using customer ID.     |

## 🛡️ Admin APIs

| Method | Endpoint                        | Description                                        |
| ------ | ------------------------------- | -------------------------------------------------- |
| PUT    | `/admin/driver/{id}/inactive`   | Mark a driver as **inactive** using driver ID.     |
| PUT    | `/admin/driver/{id}/active`     | Mark a driver as **active** using driver ID.       |
| PUT    | `/admin/customer/{id}/inactive` | Mark a customer as **inactive** using customer ID. |
| PUT    | `/admin/customer/{id}/active`   | Mark a customer as **active** using customer ID.   |

## 🛡️ Admin – Driver Fetch APIs

| Method | Endpoint                  | Description                                                          |
| ------ | ------------------------- | -------------------------------------------------------------------- |
| GET    | `/admin/drivers`          | Fetch list of **all drivers** (active + inactive).                   |
| GET    | `/admin/drivers/inactive` | Fetch list of **all inactive drivers**.                              |
| GET    | `/admin/drivers/active`   | Fetch list of **all active drivers**.                                |
| GET    | `/admin/driver/search`    | Search drivers based on ID (?id=24521).                              |

## 🛡️ Admin – Customer Fetch APIs

| Method | Endpoint                    | Description                                                            |
| ------ | --------------------------- | ---------------------------------------------------------------------- |
| GET    | `/admin/customers`          | Fetch list of **all customers** (active + inactive).                   |
| GET    | `/admin/customers/inactive` | Fetch list of **all inactive customers**.                              |
| GET    | `/admin/customers/active`   | Fetch list of **all active customers**.                                |
| GET    | `/admin/customer/search`    | Search customers based on ID (?id=264721).                             |

## 🛡️ Admin – Cab Fetch APIs

| Method | Endpoint                  | Description                                                                   |
| ------ | ------------------------- | ----------------------------------------------------------------------------- |
| GET    | `/admin/cabs`             | Fetch list of **all cabs** (active, inactive, available, unavailable).        |
| GET    | `/admin/cabs/unavailable` | Fetch list of **all unavailable cabs** (currently not available for booking). |
| GET    | `/admin/cabs/inactive`    | Fetch list of **all inactive cabs**.                                          |
| GET    | `/admin/cabs/available`   | Fetch list of **all available cabs** for booking.                             |
| GET    | `/admin/cabs/active`      | Fetch list of **all active cabs**.                                            |
| GET    | `/admin/cab/search`       | Search cabs based on ID (?id=163411).                                         |

## 🛡️ Admin – Booking Fetch APIs

| Method | Endpoint                   | Description                                                                           |
| ------ | -------------------------- | ------------------------------------------------------------------------------------- |
| GET    | `/admin/bookings`          | Fetch list of **all bookings** in the system.                                         |
| GET    | `/admin/bookings/driver`   | Fetch bookings filtered by **driver**.                                                |
| GET    | `/admin/bookings/customer` | Fetch bookings filtered by **customer**.                                              |
| GET    | `/admin/bookings/complete` | Fetch list of **completed bookings**.                                                 |
| GET    | `/admin/bookings/cancel`   | Fetch list of **cancelled bookings**.                                                 |
| GET    | `/admin/bookings/active`   | Fetch list of **active (in-progress) bookings**.                                      |
| GET    | `/admin/booking/search`    | Search bookings based on given parameters (booking id).                               |


## 📈 Future Enhancements

**✔ JWT Authentication**

**✔ Swagger Documentation**

**✔ Payment Gateway Integration**

**✔ Spring Security**

**✔ Frontend (Angular/React)**

## 👨‍💻 Developer

**Er. Mohd Sajid**

**Java Backend Developer**

## 📄 License

This project is developed for learning and practice purposes.


