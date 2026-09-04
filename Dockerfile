# ---------- Build stage ----------
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests


# ---------- Runtime stage ----------
FROM tomcat:10.1-jdk21-temurin

# Remove Tomcat's default applications
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy our WAR and deploy it as ROOT
COPY --from=builder /app/target/SmartExpenseAnalyzerWeb.war \
    /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["sh", "-c", "sed -i \"s/port=\\\"8080\\\"/port=\\\"${PORT:-8080}\\\"/\" /usr/local/tomcat/conf/server.xml && catalina.sh run"]
