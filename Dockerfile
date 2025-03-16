# Build Stage
FROM maven:3.9.6-eclipse-temurin-23 AS build
COPY . .
RUN mvn clean package -DskipTests

# Run Stage
FROM eclipse-temurin:23-jdk-slim
COPY --from=build /target/todo-app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
