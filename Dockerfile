# Use an official Maven image to build the application
FROM maven:3.8.4-openjdk-11 AS build
COPY . /app
WORKDIR /app
RUN mvn clean package -DskipTests

# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/appartmentreserveapp-*.jar /app/appartmentreserveapp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/appartmentreserveapp.jar"]
