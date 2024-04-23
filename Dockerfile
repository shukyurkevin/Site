FROM openjdk:21-jdk
COPY Site-0.0.1-SNAPSHOT.jar site.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","site.jar"]
