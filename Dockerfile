FROM openjdk:8-jdk-alpine
WORKDIR /usr/src/app
ARG JAR_FILE=target/mortgage-check-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/usr/src/app/app.jar"]
