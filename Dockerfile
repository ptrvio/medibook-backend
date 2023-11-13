FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/medibook-0.0.1-SNAPSHOT.jar medibook-0.0.1-SNAPSHOT.jar
EXPOSE 8090
CMD ["java", "-jar", "medibook-0.0.1-SNAPSHOT.jar"]