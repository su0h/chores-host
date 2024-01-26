# Stage 1: Build the application
FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu as build
WORKDIR /app

# Copy the Gradle files and dependencies
COPY build.gradle settings.gradle /app/
COPY gradle /app/gradle
COPY src /app/src

# Copy the Gradle wrapper script
COPY gradlew /app/

# Copy the Gradle wrapper properties
COPY ./gradle/wrapper/gradle-wrapper.properties /app/

# Make the Gradle wrapper executable
RUN chmod +x /app/gradlew

# Download and install the Gradle dependencies
RUN /app/gradlew build -x test

# Stage 2: Create the final image
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/Chores-1.0.jar /app/

# Environment Variables
ENV DB_USER_NAME=postgres
ENV DB_PASSWORD=P@ssw0rd123
ENV DB_URL="jdbc:postgresql://localhost:5432/chores"

# Expose the port the app runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "Chores-1.0.jar"]