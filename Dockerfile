# Usa una imagen oficial de OpenJDK con Maven
# Usa una imagen oficial de OpenJDK con Maven
FROM amazoncorretto:21-alpine-jdk

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia todos los archivos del proyecto al contenedor
COPY . .

# Da permisos de ejecución a Maven Wrapper
RUN chmod +x mvnw

# Compila el proyecto con Maven
RUN ./mvnw clean package -DskipTests

# Expone el puerto en el que corre la aplicación (cámbialo si es diferente)
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "target/*.jar"]