FROM openjdk

WORKDIR /app

COPY target/auth-0.0.1-SNAPSHOT.jar /app/auth.jar

ENTRYPOINT ["java", "-jar", "auth.jar"]