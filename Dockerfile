FROM openjdk:17-alpine

# Set the working directory
WORKDIR /app

# Copy the pom.xml and target/ dependencies to the container
COPY pom.xml .
COPY target/JavaProject-0.0.1-SNAPSHOT.jar app.jar
COPY target/dependency/ /app/lib/

# Run the application
CMD ["java", "-jar", "app.jar"]
