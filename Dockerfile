FROM maven:3.9.1-amazoncorretto-19 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:19-ea-19-jdk-slim
COPY --from=build /target/MovesenseWebApp-0.0.1-SNAPSHOT.jar movesenseapp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","movesenseapp.jar"]