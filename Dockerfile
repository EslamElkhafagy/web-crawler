FROM openjdk:8-jdk-alpine
LABEL maintainer="eslamelkhafagy@gmail.com"
COPY ./target/web-crawler-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/app.jar"]