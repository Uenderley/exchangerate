# exchangerate
Execute a aplicacao ./mvnw quarkus:dev

Para testar a aplicação pode utilizar o Swagger: http://localhost:8080/swagger-ui/

## A Aplicação tem 2 endpoints
- O Principal que faz a conversão
- O secundario que consulta o banco de dados

## Docker
Para gerar uma tag do projeto use o comando abaixo:
docker build -t exchangerate:1.0.0 .

OBS: Lembre-se de incrementar a versão de acordo com o valor referido no POM.XML

Para executar o projeto execute o comando abaixo:
docker run -i --rm -p 8080:8080 exchangerate:1.0.0

OBS: Lembre-se de atualizar a versão do projeto de acordo com a ultima TAG gerada

