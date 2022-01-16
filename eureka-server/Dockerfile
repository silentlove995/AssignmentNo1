FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/eureka-server-1.0.jar eureka.jar
ENTRYPOINT ["java","-jar", "/eureka.jar"]