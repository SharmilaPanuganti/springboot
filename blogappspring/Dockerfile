#maven image
FROM maven:3.9.9-eclipse-temurin-21 AS build

#working directory
WORKDIR /app

#copy pom.xml and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

#copy source code and build application
COPY src ./src
RUN mvn clean package -DskipTests

#use open jdk
FROM eclipse-temurin:21-jdk-jammy

#working directory
WORKDIR /app

#copy jar file
COPY --from=build /app/target/blogappspring-0.0.1-SNAPSHOT.jar .

#expose port
EXPOSE 8096

#specify entrypoint
ENTRYPOINT ["java","-jar","/app/blogappspring-0.0.1-SNAPSHOT.jar"]
