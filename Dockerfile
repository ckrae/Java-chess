FROM maven:3.8.3-jdk-11-slim
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/src/app/target/chess-0.0.1-SNAPSHOT.jar"]