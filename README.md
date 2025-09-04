# ⚡ Electricity Bill- Management System

A **Java Swing-based GUI application** for managing electricity billing operations.  
This project allows users to create accounts, manage customers, calculate bills, pay bills, and generate reports.  
The application uses **JDBC** to connect with a **MySQL database** for secure data handling.  

---

## ✨ Features

- 🔐 **User Authentication** – Users can create personal logins for security.  
- 🧾 **Customer Management** – Add and store customer details in the database.  
- ⚡ **Bill Calculation** – Automatically calculate electricity bills based on units consumed.  
- 💳 **Bill Payment** – Users can securely pay their electricity bills.  
- 📑 **Bill Generation** – Generate printable bills for customers.  
- 📋 **View Details** – Show customer details and past bills.  

---

## 🛠️ Technologies Used

- **Java (Swing for GUI)**
- **MySQL (Database)**
- **JDBC (Java Database Connectivity)**
- **IntelliJ IDEA** (Development Environment)

---

## 📂 Project Structure

This project contains **9 main classes**:

- `SplashScreen` – Application splash/loading screen  
- `Login` – User login functionality  
- `MainSystem` – Core system dashboard  
- `AddCustomer` – Add and manage customer details  
- `PayBill` – Handle electricity bill payments  
- `GenerateBill` – Generate electricity bills  
- `ShowDetails` – Display customer information  
- `LastBill` – View last paid bills  
- `Conn` – Database connection setup (JDBC – MySQL)  

---

## 🗄️ Database Design (MySQL)

The system uses **4 main tables**:

1. **Login Table**  
   ```sql
   CREATE TABLE Login (
       UserName VARCHAR(50) PRIMARY KEY,
       Password VARCHAR(50) NOT NULL
   );

2. **Bill Table**
  ```sql
  CREATE TABLE Bill (
      MeterNumber VARCHAR(20),
      Units INT,
      Month VARCHAR(20),
      Amount DECIMAL(10,2)
  );
  ```
3. **Emp Table** (Customer Information)
  ```sql
  CREATE TABLE Emp (
      Name VARCHAR(100),
      MeterNumber VARCHAR(20) PRIMARY KEY,
      Address VARCHAR(255),
      State VARCHAR(50),
      City VARCHAR(50),
      Email VARCHAR(100),
      Phone VARCHAR(15)
  );
  ```
4. **Tax Table**
  ```sql
  CREATE TABLE Tax (
      MeterLocation VARCHAR(50),
      MeterType VARCHAR(50),
      PhaseCode VARCHAR(20),
      BillType VARCHAR(20),
      Days INT,
      MeterRent DECIMAL(10,2),
      MCB_Rent DECIMAL(10,2),
      ServiceRent DECIMAL(10,2),
      GST DECIMAL(5,2)
  );
  ```

---

## 🚀 How to Run the Project

1. Install **Java JDK** (>= 8) and **MySQL Server**.  
2. Clone this repository:  
   ```bash
   git clone https://github.com/Anurag-3112/Electricity-Bill--Management-System.git
   ```
3. Open the project in IntelliJ IDEA (or Eclipse/NetBeans).
4. Import the MySQL JDBC Driver into your project libraries.
5. Configure the database:
  - Create a new MySQL database.
  - Import the provided SQL script (if available).
  - Update database credentials in Conn.java.
6. Run the SplashScreen or MainSystem class to start the application.

## 📸 Screenshots

Login Screen
Main Dashboard
Add Customer
Generate Bill

## 🔮 Future Improvements

Add role-based authentication (Admin/User).
Provide email notifications for bill payments.
Implement data export to PDF/Excel.
Improve UI with JavaFX or modern UI frameworks.
Add online payment integration.

## 👨‍💻 Author

- **Name:** Anurag Kumar  
- **GitHub:** [Anurag-3112](https://github.com/Anurag-3112)  
- **LinkedIn:** ([https://linkedin.com/in/your-linkedin](https://www.linkedin.com/in/anurag-kumar-work/))  
- **Email:** kumar.anurag.connect@gmail.com  

🚀 Developed using **Java Swing + MySQL**  
💻 Created in **IntelliJ IDEA**
