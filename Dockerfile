# Usamos JDK 17 ligero
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copiamos todo el proyecto
COPY . .

# Le damos permisos de ejecución a mvnw
RUN chmod +x mvnw

# Build del proyecto
RUN ./mvnw clean package -DskipTests

# Exponemos el puerto que usará Spring Boot
EXPOSE 8080

# Comando para arrancar la app
CMD ["java", "-jar", "target/migestion-0.0.1-SNAPSHOT.jar"]
