FROM maven:3.9.9-eclipse-temurin-21 as maven-build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=maven-build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]