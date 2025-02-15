FROM maven:3.9.4-eclipse-temurin-21-alpine AS maven-builder

COPY src /app/src
COPY pom.xml /app
COPY ./entrypoint.sh /app

RUN mvn -f /app/pom.xml clean package -DskipTests && chmod +x /app/entrypoint.sh

FROM eclipse-temurin:21-jre-alpine

COPY --from=maven-builder /app/target/twitterBackend-0.0.1-SNAPSHOT.jar /app-service/twitterBackend-0.0.1-SNAPSHOT.jar
COPY --from=maven-builder /app/entrypoint.sh /app-service

WORKDIR /app-service

RUN chmod +x /app-service/entrypoint.sh

EXPOSE 8080
ENTRYPOINT ["/app-service/entrypoint.sh", "java", "-jar", "twitterBackend-0.0.1-SNAPSHOT.jar"]