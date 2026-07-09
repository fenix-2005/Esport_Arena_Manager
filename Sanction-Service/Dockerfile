# syntax=docker/dockerfile:1

FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn -B dependency:go-offline || true

COPY src ./src
RUN mvn -B clean package -DskipTests

RUN set -eux; \
    JAR_FILE=$(find target -maxdepth 1 -name "*.jar" ! -name "*-plain.jar" ! -name "*-sources.jar" ! -name "*-tests.jar" | head -n 1); \
    if [ -z "$JAR_FILE" ]; then echo "ERROR: no se encontró un jar ejecutable en target/"; exit 1; fi; \
    cp "$JAR_FILE" app.jar


FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

RUN apt-get update && apt-get install -y --no-install-recommends curl && rm -rf /var/lib/apt/lists/* \
    && groupadd -r app && useradd -r -g app app \
    && mkdir -p /app/data \
    && chown -R app:app /app



COPY --from=build /app/app.jar app.jar

ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0"

USER app
EXPOSE 8080

ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar app.jar"]