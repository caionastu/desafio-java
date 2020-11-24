# 01 - Setup
Configuração inicial do projeto que será utilizado como base para as outras histórias do desafio.

# O que foi configurado?
- Conexão com banco de dados PostgreSql
- Swagger
- Lombok
- Flyway
- TJF (Framework de Java utilizado pela empresa TOTVS)
- Testes unitários (JUnit 5)
- Testes integrados com TestContainers, subindo um container docker com uma instância do PostgreSql

# Dificuldades encontradas
A maior dificuldade foi configurar os testes integrados no JUnit 5, pois, a biblioteca de testes do Spring contém JUnit 4 e Junit 5, havendo confusão
na hora de importar as anotações do Junit 5. Ou seja, os testes integrados utiliza Junit 5 e estava importando do JUnit 4.

O caminho de importação do JUnit 5 é _org.junit.jupiter_, enquanto que o JUnit 4 é _org.junit_.
