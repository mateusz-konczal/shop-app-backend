FROM eclipse-temurin:17-jre-alpine
VOLUME /app/data/upload_dir/
COPY /target/shop.jar /shop.jar
ENTRYPOINT ["java","-jar","/shop.jar"]