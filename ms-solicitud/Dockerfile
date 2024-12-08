FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} ms-solicitud.jar
ENTRYPOINT ["java","-jar","/ms-solicitud.jar"]