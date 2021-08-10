# Sistema de gerenciamento de pessoas em API-REST

Desenvolvi um pequeno sistema de gerenciamento de uma empresa através de uma API REST, criada com Spring boot.

Ferramentas utilizadas para para o desenvolvimento desse projeto:

- Java Versão 11.
- Maven 3.8.1.
- Inteliji IDEA Comunity.
- Git para controle de versão.
- Heroku para deploy do projeto na nuvem.
- Postman para testes.
- Banco de dados H2.

Dependencias utilizadas no projeto:

- Mapstruct.
- Spring-Boot Actuator.
- Spring-Boot JPA.
- Spring-Boot DevTools.
- Spring-Boot Bean Validation.
- Spring Web.
- H2 Database.
- Lombok.

Comandos para executar e fazer o build do projeto através do terminal:

- Build do projeto:     
<code>mvn compile</code>
- Comando para rodar o projeto no terminal:            
<code>mvn spring-boot:run</code>
- Após execução do comando acima, basta apenas entrar no seguinte endereço para ver o projeto sendo executado:    
<code>http://localhost:8080/api/v1/people</code>