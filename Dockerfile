FROM openjdk:21-jdk
WORKDIR /app
COPY . .
RUN apt-get update && apt-get install -y maven
RUN mvn clean package -DskipTestsEXPOSE 8080
CMD ["java", "-jar","main.jar"]