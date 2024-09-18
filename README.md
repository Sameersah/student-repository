# SJSU Student Information System

## Description
This project is a Student Information System for San Jose State University. It provides student information of the students data present in the CSV file.

## Prerequisites
- Java JDK 22
- Maven
- Docker (for containerization)

## Building the Project
To build the project, run:

bash
./mvnw clean package


## Running the Application
### Using Java
To run the application directly using Java:
bash
java -jar target/sjsu-student-information-0.0.1-SNAPSHOT.jar


### Using Docker
To build and run the application using Docker:

1. Build the Docker image:
   ```bash
   docker build -t sjsu-student-information:latest .
   ```

2. Run the Docker container:
   ```bash
   docker run -p 3000:3000 sjsu-student-information:latest
   ```

## Running Tests
### Using Maven
To run tests using Maven:
bash
./mvnw test



### Using Docker
To run tests using the Docker container:
bash
docker run --rm sjsu-student-information:latest ./mvnw test



## Pushing to Amazon ECR
1. Authenticate Docker to your Amazon ECR registry:
   ```bash
   aws ecr get-login-password --region us-east-2 | docker login --username AWS --password-stdin 682033493477.dkr.ecr.us-east-2.amazonaws.com
   ```

2. Push the image to ECR:
   ```bash
   docker push 682033493477.dkr.ecr.us-east-2.amazonaws.com/sjsu-student-information:latest
   ```

