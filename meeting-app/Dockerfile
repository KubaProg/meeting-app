FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/meeting-app-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","meeting-app-0.0.1-SNAPSHOT.jar"]