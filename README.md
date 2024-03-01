# Microservice Usuario
 

# Descrição

O microserviço permite a criação de um usuário, fazendo as devidas validações dos campos solicitados, após válidados, os dadaos sâo salvos em um banco de dados.


## Desenvolvido por
Luiz Henrique Brandâo Dias
 
## O que é necessário para o funcionamento? 
 
- Java 17 
- Banco de dados MySQL
- Spring Boot
 
## Como utilizar?
 
1. Primeiro clone o repositório:

git clone https://github.com/Luizh97/MS-USERS-COMPASS-CHALLENGE-3.git

2. O banco de dados deve ser configurado na pasta `application.properties`.
 
3. Aplicação pronta para ser executada
 
 
Documentação: localhost:8080/docs-msuser.html
 
## Endpoints disponíveis no microserviço
 

 
### `GET /users/{id}`
 
Busca um usuário através de seu ID.
 
**Resposta esperada quando id válido:**
```json
{
  "firstName": "Luiz Henrique",
  "lastName": "Dias",
  "email": "teste250106125@email.com",
  "password": "123456",
  "cpf": "137.724.810-00",
  "cep": "00000-000",
  "birthDate": "25-02-1997",
  "status": true
}
```
 
### `POST /user`
 
Criar um novo usuário.
 
**Corpo da solicitação:**
```json
{   
    "firstName":"Luiz Henrique",
    "lastName":"Dias",
    "email": "teste250106125@email.com",
    "password":"123456",
    "cpf":"137.724.810-00",
    "cep":"00000-000",
    "birthDate":"1997-02-25",
    "status": true
}
```

### `PUT /users/{id}`

Atualizar informações de um usuário baseado em seu id.

**Corpo da solicitação:**
```json
{
    "firstName":"Teste de update",
    "lastName":"teste",
    "email":"teste10@email.com",
    "cpf":"01550665600",
    "birthDate":"1997-02-23"
}
```
### `PUT /users/{id}/password`

Atualizar senha do usuário pelo ID.

**Corpo da solicitação:**
```json
{
  "password":"102030"
}
```


### `Dificuldades:`

Minha maior dificuldade durante o desenvolvimento deste microserviço foi a implementação do SpringSecurity, preferi fazer a sua remoção para que fosse possível entregar uma aplicação funcional. Irei terminar todas as features que ficaram faltando como o Security e os testes.


