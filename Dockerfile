FROM maven:3.6.3-jdk-8 AS build-stage
WORKDIR .
COPY . .
RUN mvn -T 1C -DskipTests=true clean package

FROM adoptopenjdk/openjdk11:alpine-slim AS production-stage
COPY --from=build-stage ./target/*.jar ./worksample.jar
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
EXPOSE 8080
ENTRYPOINT ["java","-jar","/worksample.jar"]