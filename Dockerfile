FROM openjdk:17-jdk-alpine
ADD build/libs/*.jar order-service.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/order-service.jar"]