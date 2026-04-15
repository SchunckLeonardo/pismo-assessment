FROM gradle:8.14.4-jdk21 AS builder

WORKDIR /workspace

COPY . .

RUN gradle --no-daemon clean test :application:bootJar

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /workspace/application/build/libs/customerassessment.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
