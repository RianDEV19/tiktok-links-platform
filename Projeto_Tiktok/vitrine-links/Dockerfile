# 1. Estágio de Construção (Build)
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .
# Dá permissão para o Maven rodar
RUN chmod +x mvnw
# Compila o projeto ignorando os testes para ser mais rápido
RUN ./mvnw clean package -DskipTests

# 2. Estágio de Execução (Run)
FROM eclipse-temurin:21-jre
WORKDIR /app
# Copia apenas o arquivo .jar compilado do passo anterior
COPY --from=build /app/target/*.jar app.jar
# Libera a porta 8080
EXPOSE 8080
# Comando para ligar o servidor
ENTRYPOINT ["java", "-jar", "app.jar"]