# Build stage
FROM maven:3.8-jdk-17-alpine AS build
WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN mvn package -DskipTests

# Runtime stage
FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
