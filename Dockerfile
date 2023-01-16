FROM openjdk

# Set the working directory
WORKDIR /app

# Copy the pom.xml and target/ dependencies to the container
COPY pom.xml .
COPY target/dependency/BOOT-INF/lib /app/lib
COPY target/*.jar app.jar

# Run the application
CMD ["java", "-jar", "app.jar"]