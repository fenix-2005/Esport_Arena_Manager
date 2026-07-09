##### Etapa 1: build #####
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copiamos primero solo el pom.xml para aprovechar el cache de capas de Docker.
# Si la descarga offline falla parcialmente no rompe el build (se reintenta en el package).
COPY pom.xml .
RUN mvn -B dependency:go-offline || true

# Copiamos el código fuente y compilamos
COPY src ./src
RUN mvn -B clean package -DskipTests

# Localizamos el jar ejecutable de forma robusta (evita coincidencias múltiples, vacías o de test)
RUN set -eux; \
    JAR_FILE=$(find target -maxdepth 1 -name "*.jar" ! -name "*-plain.jar" ! -name "*-sources.jar" ! -name "*-tests.jar" | head -n 1); \
    if [ -z "$JAR_FILE" ]; then echo "ERROR: no se encontró un jar ejecutable en target/"; exit 1; fi; \
    cp "$JAR_FILE" app.jar

##### Etapa 2: runtime #####
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Usuario no root: mismo comportamiento en cualquier equipo, compatible con políticas de seguridad
RUN groupadd -r app && useradd -r -g app app

COPY --from=build /app/app.jar app.jar

# La JVM respeta los límites de memoria del contenedor, sin importar la RAM del host
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0"

USER app
EXPOSE 8080

# "exec" asegura que java reciba SIGTERM directamente (apagado correcto en cualquier orquestador)
ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar app.jar"]