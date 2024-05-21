ARG DB_URI_ARG
ARG DB_USER_ARG
ARG DB_PASSWORD_ARG

FROM maven:latest as builder
ARG DB_URI_ARG
ARG DB_USER_ARG
ARG DB_PASSWORD_ARG
WORKDIR /app

COPY . .

ENV DB_URI=${DB_URI_ARG:-bolt://neo4j:7687}
ENV DB_USER=${DB_USER_ARG:-neo4j}
ENV DB_PASSWORD=${DB_PASSWORD_ARG:-neo4j}

RUN ["mvn", "package"]

FROM openjdk:21-jdk
ARG DB_URI_ARG
ARG DB_USER_ARG
ARG DB_PASSWORD_ARG
ENV DB_URI=${DB_URI_ARG:-bolt://neo4j:7687}
ENV DB_USER=${DB_USER_ARG:-neo4j}
ENV DB_PASSWORD=${DB_PASSWORD_ARG:-neo4j}

WORKDIR /app

COPY --from=builder /app/target/*.jar /app/app.jar

CMD ["java", "-jar", "app.jar"]

