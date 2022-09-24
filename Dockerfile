FROM openjdk:8
EXPOSE 8080
ADD target/springcdci.jar springcdci.jar
ENTRYPOINT ["java", "-jar", "/springcdci.jar"]