FROM openjdk:17-jdk-slim
RUN groupadd walkway && useradd -r -s /bin/false -g walkway spring
USER spring:walkway
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} account-service.jar
ENTRYPOINT ["java", "-jar", "account-service.jar"]