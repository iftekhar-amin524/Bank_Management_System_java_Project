# 🏦 Bank Management System

A Java-based desktop **Bank Management System** developed as a group project for **ENGI 9838 – Software Engineering Practice** at Memorial University of Newfoundland.

The application was designed to demonstrate common banking operations such as user authentication, customer and employee management, deposits, withdrawals, password management, and role-based access control.

## 📌 Project Overview

The Bank Management System replaces basic manual banking operations with a digital desktop application.

The system supports three main types of users:

- **Manager** – manages employee records and has administrative access
- **Employee** – manages customers and processes deposits and withdrawals
- **Customer** – views account information, balance, and manages account access

The application uses a Java Swing graphical interface connected to a MySQL database.

## ✨ Features

### Authentication
- User login
- User registration
- Password masking
- Password change functionality
- Logout functionality
- Role-based redirection after login
- Basic protection against SQL injection attempts

### Customer Management
- Add new customers
- Search customers
- Update customer information
- Delete customer accounts
- View account balance
- Deposit money
- Withdraw money
- Real-time balance updates

### Employee Management
- Add employees
- Search employee records
- Update employee information
- Delete employees
- View employee details
- Store designation and salary information

### Role-Based Access Control

Different users are provided with different levels of system access.

For example:

- Managers can access employee management functions
- Employees can manage customers and transactions
- Customers have access only to their own account-related functionality

## 🛠️ Technologies Used

| Technology | Purpose |
|------------|---------|
| Java | Core application development |
| Java Swing | Desktop graphical user interface |
| MySQL | Database management |
| XAMPP | Local MySQL database environment |
| JDBC | Java-to-database connectivity |
| JUnit | Backend/unit testing |
| GitHub | Version control and collaboration |
| Jira | Task and sprint tracking |
| VS Code / Notepad++ | Development |

## 🏗️ Architecture

The project follows a monolithic desktop application architecture.

### Frontend

The graphical interface was developed using **Java Swing**, including screens for:

- Login
- Registration
- Customer dashboard
- Employee dashboard
- Customer management
- Employee management
- Password management

### Backend

Repository classes were used to handle database operations.

Examples include:

- `UserRepo.java`
- `CustomerRepo.java`
- `EmployeeRepo.java`

These components handle authentication, CRUD operations, employee information, customer information, and financial transactions.

### Database

The MySQL database contains tables for:

- Login credentials
- Customers
- Employees

Example structure:

```text
login
├── userId
├── password
└── status

customers
├── customerId
├── customerName
└── balance

employees
├── empId
├── employeeName
├── designation
└── salary
