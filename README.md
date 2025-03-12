# Simple Blog App

## Overview
This is a Spring Boot-based web application designed as a monolithic architecture. It implements server-side rendering (SSR) using Thymeleaf templates and Bootstrap 5 for styling. The app will support Spring Security, Spring Actuator, and may include a rate limiter for better performance and security.

## Technologies Used

### Backend
- **Java** - Primary programming language
- **Spring Boot** - Framework for creating standalone, production-grade Spring-based applications
- **Spring Data JPA** - Simplifies data access layer implementation
- **Thymeleaf** - Server-side Java template engine for web applications, enabling dynamic content rendering in HTML

### Frontend
- **HTML** - Structure of the web pages
- **CSS** - Styling and layout of the application
- **Bootstrap** - Frontend framework for responsive design

## Features
- Create, read, update, and delete blog posts
- Responsive design for desktop and mobile devices
- User authentication and authorization **(coming soon)**
- Comment system for blog posts **(coming soon)**

## Getting Started

### Prerequisites
- Java 17
- Maven

### Installation
1. Clone the repository
```bash
git clone https://github.com/ErikV121/Simple-Blog-App.git
```

2. Navigate to the project directory
```bash
cd Simple-Blog-App
```

3. Build the project
```bash
mvn clean install
```

4. Run the application
```bash
mvn spring-boot:run
```

5. Open your browser and visit `http://localhost:8080`

## Project Structure
- `src/main/java` - Java source files
- `src/main/resources/templates` - Thymeleaf templates
- `src/main/resources/static` - Static resources (CSS)
