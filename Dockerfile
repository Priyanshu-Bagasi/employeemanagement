# Using official OpenJDK 17 image as base
FROM openjdk:17-jdk-slim

# Setting working directory inside container
WORKDIR /app

# Copying built JAR file into container
COPY target/employeemanagement-0.0.1-SNAPSHOT.jar app.jar

# Exposing the port on which the app runs
EXPOSE 8080

# Running the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
