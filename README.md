# Shop App (backend)
The backend part of the online shop's web application in a layered architecture. 
The application provides a REST API for the frontend part built in Angular. You can 
now manage products, categories, reviews, shopping cart and orders. As orders are 
processed, events are logged and emails are sent to customers. Handling for administrator 
accounts and shop customer accounts was provided using Spring Security and JSON Web Token.
Work in progress on integration with a payment gateway.
- --
## Technologies
* Java 17
* Maven
* Spring Framework 6.0.6
* Spring Boot 3.0.4
* Hibernate
* Liquibase 4.17.2
* MySQL 8.0.32
* Lombok
* JUnit 5
* Mockito
* AssertJ
* Git

## Usage
```
git clone https://github.com/mateusz-konczal/shop-app-backend.git
cd shop-app-backend
mvn spring-boot:run
```
The REST API documentation is available at:
* [localhost:8080/api/v1/api-docs](http://localhost:8080/api/v1/api-docs) (JSON)
* [localhost:8080/api/v1/swagger-ui/index.html](http://localhost:8080/api/v1/swagger-ui/index.html) (User Interface)

## Simplified EER Diagram
![EER Diagram](https://github.com/mateusz-konczal/shop-app-backend/blob/master/readme/EER_Diagram.png?raw=true)
