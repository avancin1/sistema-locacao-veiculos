# Sistema de Locação de Veículos

Disciplina: Banco de Dados | Professor: Howard Roatti | Autora: Ana Julia, Dayane

## O que é

Sistema desenvolvido em Java para gerenciamento de uma locadora de veículos.

O sistema permite cadastrar, consultar, atualizar e remover clientes, veículos e locações, além de gerar relatórios.

## Requisitos (Linux)

- Java 17
- Maven
- PostgreSQL

## Como executar

1. Instalar as dependências (exemplo Ubuntu/Debian):

   sudo apt install openjdk-17-jdk maven postgresql

2. Criar o banco de dados `locadora` no PostgreSQL.

3. Executar o script de criação das tabelas:

   sql/script_locadora_tabelas.sql

4. Opcionalmente, inserir os dados de exemplo:

   sql/dados_exemplo.sql

5. Configurar a variável de ambiente `DB_PASSWORD` com a senha do usuário do PostgreSQL.

6. Executar o projeto a partir da raiz:

   mvn compile exec:java

## Menus

O sistema possui os seguintes menus:

- Relatórios
- Inserção de registros
- Remoção de registros
- Atualização de registros
- Encerramento do sistema

## Relatórios

O sistema possui dois relatórios:

- Locações por modelo
- Locações detalhadas

Os arquivos SQL dos relatórios estão em:

   sql/relatorios/

## Diagrama

O diagrama relacional do banco está disponível na pasta:

   diagrama/

## Vídeo

O vídeo de demonstração do sistema será disponibilizado conforme solicitado pela disciplina.
