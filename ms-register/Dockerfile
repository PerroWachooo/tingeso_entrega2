FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} ms-register.jar
ENTRYPOINT ["java","-jar","/ms-register.jar"]