FROM --platform=$BUILDPLATFORM maven:3.9.8-eclipse-temurin-21 AS development

WORKDIR /app
COPY pom.xml /app/pom.xml
RUN mvn dependency:go-offline
COPY src /app/src


FROM development AS builder
RUN mvn clean install -DskipTests

FROM eclipse-temurin:21-jdk-alpine AS finalStage
WORKDIR /app

RUN apk add --no-cache curl && \
    addgroup -g 10001 -S javaappgroup && \
    adduser -u 10000 -S -G javaappgroup javaappuser && \
    mkdir -p /app/logs && \
    chown -R 10000:10001 /app/logs

USER javaappuser:javaappgroup

COPY --from=builder /app/target/*jar java-app.jar
ENV SERVER_PORT=8080
EXPOSE ${SERVER_PORT}

HEALTHCHECK --interval=30s --timeout=10s --start-period=30s --retries=3 CMD curl -f http://localhost:${SERVER_PORT}/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "java-app.jar", "--server.port=${SERVER_PORT}"]