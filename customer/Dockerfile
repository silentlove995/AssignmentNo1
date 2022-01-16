FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/customer-1.0.jar customer.jar
ENTRYPOINT ["java","-jar", "/customer.jar"]