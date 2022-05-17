FROM amazoncorretto:11-alpine-jdk
MAINTAINER ia.com
COPY target/assignment-1.0.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]