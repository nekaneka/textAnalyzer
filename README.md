# Text Analyzer Application

This repository contains a full-stack application for text analysis, consisting of an Angular-based frontend and a Java Spring Boot backend. The application allows users to input text, analyze it using a custom logic, and view the analysis results. It supports both online and offline modes of operation.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java JDK 17
- Maven
- Node.js
- Angular CLI 17.2.2

### Installing

1. **Backend Setup:**
   - Navigate to the backend directory:
     ```bash
     cd backend
     ```
   - Build the project using Maven:
     ```bash
     mvn clean install
     ```
   - Run the Spring Boot application:
     ```bash
     mvn spring-boot:run
     ```

2. **Frontend Setup:**
   - Navigate to the frontend directory:
     ```bash
     cd frontend
     ```
   - Install dependencies:
     ```bash
     npm install
     ```
   - Run the development server:
     ```bash
     ng serve
     ```
   - Access the application at `http://localhost:4200/`.

### Testing

- **Backend Tests:**
  Run the backend tests using Maven:
  ```bash
  mvn test

- **Frontend Unit Tests:**
  Run the Angular unit tests:
  ```bash
  ng test

- **End-to-End Tests:**
  Ensure that the backend and frontend servers are running. Install Playwright Test if not already installed:
  ```bash
  npm i -D @playwright/test

- **Run the end-to-end tests:**
  
  ```bash
  npx playwright test

## Features

- Text analysis functionality in both frontend (TypeScript) and backend (Java)
- Toggle switch for online and offline analysis modes
- Display of analysis results in a user-friendly format
- Persistence of previous analysis outputs
- RESTful API for text analysis provided by the Java backend

## Built With

- [Angular](https://angular.io/) - Frontend framework
- [Spring Boot](https://spring.io/projects/spring-boot) - Backend framework
- [Maven](https://maven.apache.org/) - Dependency Management for Java
- [Playwright](https://playwright.dev/) - End-to-end testing framework

## Authors

- Mehremic Adem

