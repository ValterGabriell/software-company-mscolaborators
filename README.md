<h1 align="center">Microserviços de avaliação de criação de colaborador - Empresa de Software</h1>
<p align="center"><i>Repositório responsável por criar os colaboradores das squads.</i></p>

##  Sobre esse projeto
Este é um projeto que deve ser rodado após o Eureka Server estar rodando, para que se registre no discovery server.


## Indíce
<!--ts-->
   * [Como usar?](#como-usar)
   * [Endpoints](#endpoints)
   * [Creditos](#creditos)
<!--te-->
  
<h1>Como usar?</h1>
<p>O Eureka Server deve estar rodando, acesse-o <a href="https://github.com/ValterGabriell/bank-system-eureka-server">aqui</a>.</br>
<p>Clone ou baixe o repositório e start ele através de sua IDE de preferência rodando o método main da classe principal na pasta raíz da aplicação, feito isso, basta começar a usar :). O ideal é startar todos os outros microserviços antes de testar a aplicação.</p>
<p>Além disso, é fundamental ter um container do RabbitMQ no Docker rodando com usuario e senha padrao (guest, guest) para o microserviço poder enviar o código para a fila.</p>

1 -> <a href="https://github.com/ValterGabriell/bank-system-eureka-server">Eureka Server</a></br>
2 -> <a href="https://github.com/ValterGabriell/bank-system-mscards">Microserviço responsável por criar cartões para os usuários</a></br>
3 -> <a href="https://github.com/ValterGabriell/bank-system-msaccount">Microserviço responsável por criar contas dos usuários</a></br>
4 -> <a href="https://github.com/ValterGabriell/software-company-mslead">Microserviço responsável pela criação dos líderes das squads</a></br>
5 -> <a href="https://github.com/ValterGabriell/software-company-mscolaborators">Microserviço responsável pela criação dos colaboradores das squads</a></br>
6 -> <a href="https://github.com/ValterGabriell/bank-system-gateway">Gateway para fazer o loadbalancer dos microserviços</a></br>
7 -> <a href="https://github.com/ValterGabriell/software-company-msjobs">Microserviço responsável pela criação dos trabalhos dos colaboradores</a></br>

  
<h1>Endpoints</h1>
<h3>BASE URL</h3>

```bash
http://localhost:8080/colaborator/
``` 
<h1>POST</h1></br>

<h2>Criar conta</h2>

<table>
  <tr>
    <th>Request</th>
    <th>Query</th>
    <th>Query</th>
  </tr>
  <tr>
    <td>/create</td>
    <td>/cnpj</td>
    <td>/income</td>
  </tr>
</table>


<h3>Request esperada</h3>

```bash

{
	"id":62856573232,
	"name":"Lucas Brito",
	"email":"email@hotmail.com",
	"phone":"+5585631135657",
	"gender":0,
	"bornDay":"2000-12-31",
	"password":"123",
	"typePerson": "PHYSIC_PERSON"
}	


```

<h3>Resposta esperada</h3></br>

```bash
{
	"id": 62856573232,
	"name": "Lucas Brito",
	"email": "email@hotmail.com",
	"phone": "+5585631135657",
	"gender": "MALE",
	"bornDay": "2000-12-31",
	"typePerson": "PHYSIC_PERSON",
	"hireDate": "2023-05-08",
	"password": "123",
	"lead": 93856573232123,
	"active": true
}
```

<h1>GET</h1></br>


<h2>Recuperar um colaborador por ID</h2>
<table>
  <tr>
    <th>Request</th>
    <th>Query</th>
  </tr>
  <tr>
    <td>/</td>
    <td>cpf</td>
  </tr>
</table>



<h3>Resposta esperada</h3></br>

```bash
{
	"data": {
		"id": 63856573232,
		"name": "Lucas Brito",
		"email": "email@hotmail.com",
		"phone": "+5585631135657",
		"gender": "MALE",
		"bornDay": "2000-12-31",
		"typePerson": "PHYSIC_PERSON",
		"hireDate": "2023-05-03",
		"password": "123",
		"lead": null,
		"active": true
	},
	"message": "Sucesso!"
}
```


<h2>Recuperar líder do colaborador</h2>
<table>
  <tr>
    <th>Request</th>
    <th>Query</th>
  </tr>
  <tr>
    <td>/get-lead</td>
    <td>cpf</td>
  </tr>
</table>



<h3>Resposta esperada</h3></br>

```bash
Líder do colaborador
```


<h1>Créditos</h1>

<a href="https://www.linkedin.com/in/valter-gabriel">
  <img style="border-radius: 50%;" src="https://user-images.githubusercontent.com/63808405/171045850-84caf881-ee10-4782-9016-ea1682c4731d.jpeg" width="100px;" alt=""/>
  <br />
  <sub><b>Valter Gabriel</b></sub></a> <a href="https://www.linkedin.com/in/valter-gabriel" title="Linkedin">🚀</ a>
 
Made by Valter Gabriel 👋🏽 Get in touch!

[![Linkedin Badge](https://img.shields.io/badge/-Gabriel-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/valter-gabriel/ )](https://www.linkedin.com/in/valter-gabriel/)

