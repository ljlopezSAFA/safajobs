# Usa una imagen oficial de OpenJDK con Maven
# Usa una imagen oficial de OpenJDK con Maven
FROM amazoncorretto:21-alpine-jdk


# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo pom.xml y las dependencias antes de copiar el código fuente
COPY pom.xml mvnw .mvn/ ./
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

# Copia todo el código fuente al contenedor
COPY . .

# Da permisos de ejecución a Maven Wrapper
RUN chmod +x mvnw

# Compila el proyecto con Maven y salta los tests para acelerar el build
RUN ./mvnw clean package -DskipTests

# Verifica que el archivo JAR se haya generado correctamente
RUN ls -l target/

# Expone el puerto en el que corre la aplicación (ajústalo si es diferente)
EXPOSE 8080

# Comando para ejecutar la aplicación con el nombre correcto del JAR
CMD ["java", "-jar", "target/safajobs-0.0.1-SNAPSHOT.jar"]
