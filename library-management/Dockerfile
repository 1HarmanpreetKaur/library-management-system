FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY library-management/mvnw .
COPY library-management/.mvn .mvn
COPY library-management/pom.xml .
COPY library-management/src src
RUN chmod +x mvnw && ./mvnw clean package -DskipTests
EXPOSE 8080
CMD ["java", "-jar", "target/library-management-0.0.1-SNAPSHOT.jar"]