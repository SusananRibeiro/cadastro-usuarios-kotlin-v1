# Aplicação de Validação de Usuário e Cadastro de Pessoa

Este é um projeto que visa realizar a validação do usuário e senha 
de um usuário, além de permitir o cadastro de uma nova pessoa.

#### Obs: 


## Tecnologia
* Kotlin
* Java FX
* PostgreSQL
* IntelliJ IDEA Community Edition 2022.3.2

## Requisitos do Projeto
### Validar usuário e senha:
* Verificar se o usuário e a senha fornecidos estão corretos.
* Caso estejam corretos, exibir uma mensagem de sucesso.
* Caso estejam incorretos, exibir uma mensagem de erro.
### Cadastro de nova pessoa:
* Solicitar o código, nome e CPF da pessoa.
* O código, nome e CPF são campos obrigatórios.
* Permitir que o endereço e o telefone sejam preenchidos, mas não são obrigatórios.
* Após o preenchimento correto de todos os campos obrigatórios, exibir um alerta informando que o cadastro foi realizado com sucesso.

## Passos para Configuração do Projeto
### Configurar o ambiente Kotlin no IntelliJ:
* Instale o IntelliJ IDE em sua máquina, se ainda não o tiver.
* Crie um novo projeto Kotlin no IntelliJ.
* Certifique-se de que as dependências do Kotlin estão corretamente configuradas.
### Configurar o banco de dados PostgreSQL:
* Instale o PostgreSQL em sua máquina, se ainda não o tiver.
* Crie um novo banco de dados para o projeto.
* Anote as informações de conexão do banco de dados, como o nome do banco de dados, nome de usuário e senha.

## Implementação
### Implemente a validação do usuário e senha:
* Crie uma função que receba o usuário e senha como parâmetros.
* Conecte-se ao banco de dados PostgreSQL utilizando as informações de conexão.
* Execute uma consulta SQL para verificar se o usuário e senha fornecidos correspondem a um registro no banco de dados.
* Retorne true se a consulta retornar algum resultado e false caso contrário.
* Exiba uma mensagem de sucesso ou erro com base no retorno da função.
### Implemente o cadastro de nova pessoa:
* Crie uma classe Pessoa com os campos código, nome, CPF, endereço e telefone.
* Implemente uma função que receba os dados da pessoa como parâmetros.
* Verifique se os campos obrigatórios (código, nome e CPF) estão preenchidos.
* Conecte-se ao banco de dados PostgreSQL utilizando as informações de conexão.
* Execute uma consulta SQL para inserir os dados da pessoa no banco de dados.
* Exiba um alerta informando que o cadastro foi realizado com sucesso.

## Considerações Finais
Este é um exemplo básico de implementação para validar usuário e senha, além de permitir o cadastro de uma nova pessoa com campos obrigatórios e opcionais. Certifique-se de adaptar o código conforme as necessidades do seu projeto.



