# Shop App (backend)
The backend part of the online shop's web application in a layered architecture. 
The application provides a REST API for the frontend part built in Angular. You can 
now manage products, categories, reviews, shopping cart, orders, payments and shipments. 
As orders are processed, events are logged and emails are sent to customers. Handling for 
administrator accounts and shop customer accounts was provided using Spring Security and 
JSON Web Token. The online shop was integrated with the Przelewy24 online payment system.
Using Ehcache, a cache for REST services related to products and categories was provided.
Currently, the project is being refined and then it will be deployed on a cloud platform.
- --
## Technologies
* Java 17
* Maven
* Spring Framework 6.0.6
* Spring Boot 3.0.4
* Spring Security and JWT
* Hibernate
* Liquibase 4.17.2
* MySQL 8.0.32
* Lombok
* Ehcache 3.10.8
* JUnit 5
* Mockito
* AssertJ
* Git
* commons libraries (e.g. io, codec, validator, csv)
* ngrok 3.3.1

## Ngrok's configuration (exposing multiple localhost ports to the Internet)
```
version: "2"
authtoken: yourTokenHere
region: eu
tunnels:
  backend:
    addr: 8080
    proto: http    
  frontend:
    addr: 4200
    proto: http
    host_header: rewrite
```

## Usage
```
git clone https://github.com/mateusz-konczal/shop-app-backend.git
cd shop-app-backend
mvn spring-boot:run
```
The REST API documentation is available at:
* [localhost:8080/api/v1/api-docs](http://localhost:8080/api/v1/api-docs) (JSON)
* [localhost:8080/api/v1/swagger-ui/index.html](http://localhost:8080/api/v1/swagger-ui/index.html) (User Interface)

## EER Diagram
![EER Diagram](https://github.com/mateusz-konczal/shop-app-backend/blob/master/readme/EER_Diagram.png?raw=true)

## Payment transaction process with Przelewy24
![Transaction process](https://github.com/mateusz-konczal/shop-app-backend/blob/master/readme/Transaction_process.png?raw=true)
* Przelewy24 REST API Documentation: [developers.przelewy24.pl](https://developers.przelewy24.pl)