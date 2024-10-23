FROM openjdk:17-jdk

WORKDIR /app

COPY build/libs/*.jar app.jar

COPY src/main/resources/data.csv /app/data.csv

ENTRYPOINT ["java", "-jar", "app.jar"]