FROM openjdk:21-jdk
WORKDIR /app
Run ./mvn clean package -DskipTests
COPY target/main-0.0.1-SNAPSHOT.jar /app/main.jar
EXPOSE 8080
CMD ["java", "-jar","/app/main.jar"]