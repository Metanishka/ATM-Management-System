# ATM Management System

## Overview

ATM Management System is a Java-based web application that simulates basic ATM operations. The project is developed using Advanced Java technologies such as Servlet, JSP, JDBC, and MySQL.

## Features

* User Authentication (Login)
* Balance Inquiry
* Cash Deposit
* Cash Withdrawal
* Transaction History
* Session Management
* Database Connectivity using JDBC

## Technologies Used

* Java
* Servlet
* JSP
* JDBC
* MySQL
* Apache Tomcat
* Eclipse IDE
* HTML

## Project Structure

```text
src/main/java
│
├── DBConnection.java
├── LoginServlet.java
├── BalanceServlet.java
├── DepositServlet.java
├── WithdrawServlet.java
├── HistoryServlet.java
└── LogoutServlet.java

src/main/webapp
│
├── login.jsp
├── menu.jsp
├── balance.jsp
├── deposit.jsp
├── withdraw.jsp
├── history.jsp
```

## Database Configuration

Before running the project, update the database credentials in `DBConnection.java`:

```java
private static final String USER = "YOUR_USERNAME";
private static final String PASSWORD = "YOUR_PASSWORD";
```

## How to Run

1. Clone the repository.
2. Import the project into Eclipse IDE.
3. Configure Apache Tomcat Server.
4. Create the MySQL database.
5. Update database credentials in `DBConnection.java`.
6. Run the project on Tomcat Server.
7. Open the application in your browser.

## Key Concepts Implemented

* Object-Oriented Programming (OOP)
* MVC-style Architecture
* JDBC Connectivity
* Session Handling
* Exception Handling
* Database Operations

## Author

Tanishka Yadav
