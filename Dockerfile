FROM openjdk:21-jdk
WORKDIR /app
COPY target/main-0.0.1-SNAPSHOT.jar /app/main.jar
Run ./mvn clean package -DskipTests
EXPOSE 8080
CMD ["java", "-jar","/app/main.jar"]