# Use Maven with OpenJDK 17 as the base image
FROM maven:3.8.5-openjdk-17-slim AS build

# Set the working directory
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Build the application and package it as a JAR
RUN mvn clean package -DskipTests

# Use a lightweight OpenJDK 17 image for running the application
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar appointment-service.jar

# Set the active Spring profile to 'prod'
ENV SPRING_PROFILES_ACTIVE=prod

# Expose the application port
EXPOSE 8091

# Define the entry point for the container
ENTRYPOINT ["java", "-jar", "appointment-service.jar"]