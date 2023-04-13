# Shop App (backend)
The backend part of the web application of the online shop in a layered architecture. 
The application provides a REST API for the frontend part built in Angular. You can 
now manage products, categories, reviews and shopping cart. Work in progress on the 
sales process (order and payment processing).
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
* [localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs) (JSON)
* [localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) (User Interface)

## Simplified EER Diagram
![EER Diagram](https://github.com/mateusz-konczal/shop-app-backend/blob/master/readme/EER_Diagram.png?raw=true)
