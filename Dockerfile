# Use an official OpenJDK 21 image as the base
FROM openjdk:21-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the application JAR file to the working directory
COPY target/*.jar app.jar

# Expose the port your application runs on (replace with your port)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
