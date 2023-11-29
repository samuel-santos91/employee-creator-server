# employee-creator-server

## Description
* This Spring Boot backend application serves as the backend for the employer-creator frontend. 
* It provides RESTful APIs to manage the employees' information.
### Purpose
* The purpose of this project was to:
    * practice building a backend with Spring Boot
    * integrate a server with a frontend
    * integrate a database with a server
### Tech Stack
* Spring Boot

## Features 
 - RESTful API for employees management (CRUD operations);
 - Database integration (<strong>MySQL</strong>) for persistent data storage;
 - Error handling.

## Getting Started
### Prerequisites
 - Java Development Kit (JDK) 8 or higher installed;
 - Eclipse IDE or any Java development environment;
 - MySQL database.

### Installation
 - Clone this repository;
 - Import project into your IDE;
 - Configure the database connection application.properties;
 - Configure cors in WebConfig.java;
 - Run the application.

### Frontend repository
Refer to https://github.com/samuel-santos91/employee-creator

### API Endpoints
 - GET /url/employees: Retrieve all employees.
 - GET /url/employees/{employeeId}: Retrieve a specific employee by ID.
 - POST /url/employees: Create a new employee.
 - PATCH /url/employees/{taskId}: Update an employee.
 - DELETE /url/employees/{taskId}: Delete an employee.

