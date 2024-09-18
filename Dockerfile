FROM openjdk:22-jdk-slim

WORKDIR /app

COPY target/sjsu-student-information-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 3000

COPY src/main/resources/data.csv /app/data.csv

ENTRYPOINT ["java", "-jar", "app.jar"]