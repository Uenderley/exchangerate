# exchangerate

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Docker
Para gerar uma tag do projeto use o comando abaixo:
docker build -t exchangerate:1.0.0 .

OBS: Lembre-se de incrementar a versão de acordo com o valor referido no POM.XML

Para executar o projeto execute o comando abaixo:
docker run -i --rm -p 8080:8080 exchangerate:1.0.0

OBS: Lembre-se de atualizar a versão do projeto de acordo com a ultima TAG gerada

