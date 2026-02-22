FROM eclipse-temurin:23-jdk

WORKDIR /app

COPY . .

RUN chmod +x ./mvnw

RUN ./mvnw clean package -DskipTests

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -jar target/*.jar"]