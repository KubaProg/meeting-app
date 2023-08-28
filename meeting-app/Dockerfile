FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/workshop-management-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","workshop-management-0.0.1-SNAPSHOT.jar"]