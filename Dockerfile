# Estágio 1: Build da aplicação
FROM maven:3.8.5-openjdk-17-slim AS build

# Define o diretório de trabalho
WORKDIR /workspace

# Copia o arquivo pom.xml e a pasta src para o diretório de trabalho
COPY pom.xml .
COPY src ./src

# Compila o projeto
RUN mvn clean package -DskipTests

# Estágio 2: Criação da imagem final
FROM openjdk:17-alpine

# Define o diretório de trabalho
WORKDIR /work/

# Copia o arquivo jar gerado para o diretório de trabalho da nova imagem
COPY --from=build /workspace/target/*-runner.jar /work/application.jar

# Expõe a porta 8080
EXPOSE 8080

# Comando para iniciar a aplicação
CMD ["java", "-jar", "/work/application.jar"]