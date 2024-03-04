FROM adoptopenjdk/openjdk:17-alpine
RUN mkdir /app
COPY target/taskManager-0.0.1-SNAPSHOT.jar /app/taskManager-0.0.1-SNAPSHOT.jar
WORKDIR /app
CMD ["java","-jar","taskManager-0.0.1-SNAPSHOT.jar"]