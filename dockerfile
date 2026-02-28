# ======== 1.Build =========
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
# Tải trước các thư viện
# Docker cache lại, chỉ thay đổi thư viện khi pom.xml thay đổi.
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -U -DskipTests

# ======== 2.Run ========
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "app.jar" ]