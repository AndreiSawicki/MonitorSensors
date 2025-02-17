FROM gradle:jdk17 AS builder
WORKDIR /app
COPY . .
RUN ./gradlew clean build

FROM openjdk:17-oracle
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar /app/*.jar
EXPOSE 8181
ENTRYPOINT ["java", "-jar", "/app/*.jar"]