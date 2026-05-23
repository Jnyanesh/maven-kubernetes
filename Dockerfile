# Use a lightweight JRE base image suitable for Java 17
FROM eclipse-temurin:17-jre-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the executable Spring Boot JAR into the container
COPY target/demo-app-1.0-SNAPSHOT.jar app.jar

# Expose the port that the Spring Boot web app runs on
EXPOSE 5000

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
