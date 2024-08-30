FROM eclipse-temurin:21-jdk-alpine

LABEL maintainer="sehmusaydogdu47@gmail.com"

ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]