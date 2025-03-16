# Build Stage
FROM maven:3.8.5-openjdk-21 AS build
COPY . .
RUN mvn clean package -DskipTests

# Run Stage
FROM openjdk:21-jdk-slim
COPY --from=build /target/todo-app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
