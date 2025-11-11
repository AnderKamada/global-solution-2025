# ===== Build =====
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -q -DskipTests clean package

# ===== Run =====
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
# variáveis de ambiente lidas pelo Spring
ENV SPRING_DATASOURCE_USERNAME=rmXXXXXX \
    SPRING_DATASOURCE_PASSWORD=changeme \
    APP_JWT_SECRET=troque-por-um-segredo-forte
ENTRYPOINT ["java","-jar","/app/app.jar"]
