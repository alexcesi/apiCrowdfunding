FROM maven:3.6.4-jdk-14 as build


COPY src /app/src

COPY pom.xml /app

RUN mvn -f /app/pom.xml clean package



FROM openjdk:14-jre-alpine

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
