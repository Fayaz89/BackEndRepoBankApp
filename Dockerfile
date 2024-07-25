# Use a lightweight OpenJDK image as the base
FROM openjdk:11-slim-buster

# Set the working directory in the container
WORKDIR /app

# Copy the project dependencies to the app directory
COPY pom.xml /app

# Download dependencies
RUN apt-get update && apt-get install -y maven && \
    mvn dependency:copy-dependencies

# Copy the rest of the application code
COPY src /app/src

# Build the application
RUN mvn clean package

# Copy the final JAR file to the app directory
COPY target/*.jar /app

# Expose the port your application will listen on (adjust as needed)
EXPOSE 8080

# Set the command to run your application
ENTRYPOINT ["java", "-jar", "app/*.jar"]
