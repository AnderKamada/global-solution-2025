📌 Global Solution 2025 – DevOps Tools & Cloud Computing
Arquitetura com VMs + API Java + PostgreSQL

---------------------------------------------
📖 Descrição do Projeto

O projeto consiste em uma API Java Spring Boot hospedada em uma VM Linux no Azure, integrada a um banco PostgreSQL instalado em uma VM Windows.
Esse ambiente simula um cenário real de deploy em nuvem, aplicando práticas essenciais de DevOps:

Infraestrutura distribuída

Comunicação entre serviços

Configuração de rede

Deploy manual da aplicação

Testes de conectividade

A aplicação expõe endpoints REST para operações CRUD relacionadas à entidade Usuários.

----------------------------------------------------
🏗 Arquitetura da Solução
 
```
                       ┌────────────────────────────┐
                       │          Azure VNet         │
                       │   (rede que conecta tudo)   │
                       └──────────────┬──────────────┘
                                      │
                ┌─────────────────────┴──────────────────────┐
                │                                            │
     ┌──────────────────────────┐                ┌──────────────────────────┐
     │        VM Linux          │                │        VM Windows        │
     │      (Ubuntu Server)     │                │     (Windows Server)     │
     │--------------------------│                │--------------------------│
     │ - Java 17                │                │ - PostgreSQL instalado   │
     │ - API Spring Boot        │ <────────────> │ - Porta 5432 liberada    │
     │ - Porta 8080             │                │ - Banco: gs2025          │
     └──────────────────────────┘                └──────────────────────────┘
```


✔ A VM Linux acessa o banco da VM Windows via IP interno
✔ A API sobe usando java -jar
✔ A aplicação responde nas rotas REST
✔ Testes via curl confirmam funcionamento

------------------------------------
🔧 Tecnologias Utilizadas

Azure Virtual Machines

Azure Network Security Groups (NSG)

Java 17

Spring Boot 3.3.5

Maven

PostgreSQL

pgAdmin

Linux (Ubuntu Server)

Windows Server

--------------------------------------
🗂 Configuração da VM Windows (Banco de Dados)
1. Instale o PostgreSQL (via instalador .exe)

Selecionado:

PostgreSQL server

pgAdmin

StackBuilder (opcional)

-------------------------------------
2. Crie o banco:

No pgAdmin ou terminal:

CREATE DATABASE gs2025;

------------------------------------------
3. Libere a porta 5432 no NSG da VM Windows:
   
Inbound rule

Port: 5432

Source: VirtualNetwork

Protocol: TCP

Allow

-------------------------------------
🗂 Configuração da VM Linux (API)
1. Instale o Java e Maven
   
sudo apt update

sudo apt install openjdk-17-jdk -y

sudo apt install maven -y

-------------------------------------
2. Envie o JAR para a VM Linux via VSCode SSH

-------------------------------------
3. Execute a aplicação:
java -jar api-0.0.1-SNAPSHOT.jar

-------------------------------------
4. Teste se a API está ativa:
curl http://localhost:8080/actuator/health
Retorno esperado:

{"status":"UP"}

--------------------------------------
5. Teste o endpoint:
curl http://localhost:8080/usuarios

--------------------------------------
🔗 Configuração do ambiente Spring Boot

application.properties:

spring.datasource.url=jdbc:postgresql://IP-DA-VM-WINDOWS:5432/gs2025

spring.datasource.username=postgres

spring.datasource.password=SENHA

spring.jpa.hibernate.ddl-auto=update

spring.jpa.open-in-view=true

-------------------------------------
🧪 Testes Realizados

Conexão API → Banco ✔
Endpoint /actuator/health ✔
CRUD de usuários ✔
Logs confirmam conexão via HikariCP ✔
Queries executadas no banco ✔

-------------------------------------

✔ Status Final

Entrega 100% funcional, cumprindo os requisitos de:

Infraestrutura em nuvem

Banco separado da aplicação

VMs isoladas e seguras

Deploy manual

Documentação completa
