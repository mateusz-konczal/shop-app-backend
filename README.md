# Shop App (backend)
The backend part of the online shop's web application in a layered architecture. 
The application provides a REST API for the frontend part built in Angular. You can 
now manage products, categories, reviews, shopping cart, orders, payments and shipments. 
As orders are processed, events are logged and emails are sent to customers. Handling for 
administrator accounts and shop customer accounts was provided using Spring Security and 
JSON Web Token. The online shop was integrated with the Przelewy24 online payment system.
Using Ehcache, a cache for REST services related to products and categories was provided.
The application was containerized and deployed to the AWS cloud.
- --
## Technologies
* Java 17
* Maven 3.8.7
* Spring Framework 6.0.6
* Spring Boot 3.0.4
* Spring Boot Actuator
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
* Docker 24.0.2
* Docker Compose 2.19.1
* Checkstyle 10.12.1
* JaCoCo 0.8.10
* SonarQube 9.9.1 (Community Edition)
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

## Ngrok's configuration (after app containerization)
```
version: "2"
authtoken: yourTokenHere
region: eu
tunnels:
  backend:
    addr: 8000
    proto: http    
  frontend:
    addr: 80
    proto: http
```

## Usage
```
git clone https://github.com/mateusz-konczal/shop-app-backend.git
cd shop-app-backend
mvn spring-boot:run
```

To properly build the project, you need an `.env` file in the root folder that contains
filled values for the fields below. In your IDE, install the plugin to support the env
file and enable it when starting the application.
```
MYSQL_HOST=string
MYSQL_PORT=integer
MYSQL_DATABASE=string
MYSQL_USERNAME=string
MYSQL_PASSWORD=string
MYSQL_ROOT_PASSWORD=string
MAIL_HOST=string
MAIL_PORT=integer
MAIL_USERNAME=string
MAIL_PASSWORD=string
API_DOCS_ENABLED=boolean
UPLOAD_DIR=string
MAIL_SENDER_ADDRESS=string
MAIL_FAKE_SERVICE_ENABLED=boolean
P24_TEST_MODE=boolean
P24_MERCHANT_ID=integer
P24_POS_ID=integer
P24_CRC=string
P24_TEST_CRC=string
P24_SECRET_ID=string
P24_TEST_SECRET_ID=string
P24_URL_RETURN=string
P24_URL_STATUS=string
FRONTEND_ADDRESS=string
JWT_EXPIRATION_TIME=long
JWT_SECRET=string
TIMEZONE=string
```

The REST API documentation is available at:
* [localhost:8080/api/v1/api-docs](http://localhost:8080/api/v1/api-docs) (JSON)
* [localhost:8080/api/v1/swagger-ui/index.html](http://localhost:8080/api/v1/swagger-ui/index.html) (User Interface)

## Deployment
To build the `.jar` file, run the command:
```
mvn clean install
```

To create and run the application in Docker, you must first create an image from
the [frontend](https://github.com/mateusz-konczal/shop-app-frontend) using the command:
```
docker build . -t shop-frontend:v1
```

Run the command below to start MySQL database, phpMyAdmin, backend and frontend:
```
docker-compose -p shop up
```

The REST API documentation is available at:
* [localhost:8000/api/v1/api-docs](http://localhost:8000/api/v1/api-docs) (JSON)
* [localhost:8000/api/v1/swagger-ui/index.html](http://localhost:8000/api/v1/swagger-ui/index.html) (User Interface)

The REST API documentation is also available on the AWS cloud platform:
* [api-docs](http://ec2-3-121-239-240.eu-central-1.compute.amazonaws.com:8000/api/v1/api-docs) (JSON)
* [swagger-ui](http://ec2-3-121-239-240.eu-central-1.compute.amazonaws.com:8000/api/v1/swagger-ui/index.html) (User Interface)

## EER Diagram
![EER Diagram](https://github.com/mateusz-konczal/shop-app-backend/blob/master/readme/EER_Diagram.png?raw=true)

## Payment transaction process with Przelewy24
![Transaction process](https://github.com/mateusz-konczal/shop-app-backend/blob/master/readme/Transaction_process.png?raw=true)
* Przelewy24 REST API Documentation: [developers.przelewy24.pl](https://developers.przelewy24.pl)