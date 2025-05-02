FROM openjdk:21-jdk
WORKDIR /app
COPY . .
Run ./mvn clean package -DskipTests
EXPOSE 8080
CMD ["java", "-jar","main.jar"]