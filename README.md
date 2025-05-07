# Simple Blog App

## Overview
Simple Blog App is a monolithic web application built with Java 21 and Spring Boot. It uses server-side rendering with Thymeleaf and integrates with Keycloak for secure user authentication and authorization. The application supports full blog post management and includes a built-in commenting system. The UI is styled using Bootstrap 5 to ensure a responsive experience across devices.

## Features
- Secure authentication and authorization using Keycloak (OAuth2/OpenID Connect)
- Create, read, update, and delete blog posts
- Commenting system on individual blog posts
- Server-side rendered HTML pages using Thymeleaf
- Responsive UI with Bootstrap 5

## Technologies Used

### Backend
- **Java 21** – Primary programming language
- **Spring Boot** – Web application framework
- **Spring Security** – Authentication and authorization
- **Spring Data JPA** – Database access and ORM
- **Thymeleaf** – Template engine for rendering dynamic views

### Frontend
- **HTML & CSS** – Page structure and styling
- **Bootstrap 5** – Responsive and mobile-first design framework

## Authentication
This app uses **Keycloak** for OAuth2-based authentication. When users access secured endpoints, they are redirected to the Keycloak login page.

## Getting Started

### Prerequisites
- Java 21
- Maven
- A running Keycloak server with:
  - Realm (e.g., `Blogified-Realm`)
  - Client configured for this application (e.g., `simple-blog-app`)
  - Valid redirect URIs set in the client settings

### Installation Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/ErikV121/Simple-Blog-App.git
   ```

2. **Navigate to the project directory**

   ```bash
   cd Simple-Blog-App
   ```

3. **Configure Keycloak Integration**
   Open  `application.properties` and configure:
# H2 DB Configuration
- spring.datasource.url=jdbc:h2:~/test
- spring.datasource.driverClassName=org.h2.Driver
- spring.jpa.hibernate.ddl-auto=update
- spring.datasource.username=sa
- spring.datasource.password=

# Thymeleaf Configuration
- spring.thymeleaf.prefix=classpath:/templates/
- spring.thymeleaf.suffix=.html

# MySQL DB Configuration
- spring.jpa.hibernate.ddl-auto=update
- spring.datasource.url=${DB_URL}
- spring.datasource.username=${DB_USERNAME}
- spring.datasource.password=${DB_PASSWORD}
- spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
- spring.jpa.show-sql=false

# Keycloak Configuration
- spring.security.oauth2.resourceserver.jwt.issuer-uri=${KEYCLOAK_ISSUER_URI}
- spring.security.oauth2.resourceserver.jwt.jwk-set-uri=${KEYCLOAK_JWK_SET_URI}
- spring.security.oauth2.client.provider.keycloak.issuer-uri=http://localhost:8081/realms/Blogified-Realm
- spring.security.oauth2.client.registration.keycloak.client-name=Blogified Client
- spring.security.oauth2.client.registration.keycloak.client-id=Blogified
- spring.security.oauth2.client.registration.keycloak.client-secret=${KEYCLOAK_CLIENT_SECRET}
- spring.security.oauth2.client.registration.keycloak.scope=openid, offline_access, profile
  

4. **Build the project**

   ```bash
   mvn clean install
   ```

5. **Run the application**

   ```bash
   mvn spring-boot:run
   ```

6. **Access the app**
   Open your browser and go to:

   ```
   http://localhost:8080
   ```

