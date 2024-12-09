FROM openjdk:17
ARG JAR_FILE=./target/demo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} ms-simulation.jar
ENTRYPOINT ["java","-jar","/ms-simulation.jar"]
