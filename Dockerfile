FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=target/*.jar

WORKDIR /opt/app

COPY ${JAR_FILE} app.jar

LABEL authors="Abdullah Turhan"

ENTRYPOINT ["java", "-jar","app.jar"]