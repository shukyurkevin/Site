FROM maven:3.8-openjdk-18 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk
COPY --from=build /target/Site-0.0.1-SNAPSHOT.jar site.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]
