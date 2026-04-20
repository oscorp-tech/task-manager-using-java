FROM openjdk:17-jdk-slim
# We assume the jar was already built by the CI pipeline and placed in target/
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]