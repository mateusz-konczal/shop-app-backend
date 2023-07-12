FROM eclipse-temurin:17-jre-alpine
COPY /target/shop.jar /shop.jar
ENTRYPOINT ["java","-jar","/shop.jar"]