# 1 MS-ORDER  
Serviço para criação de ordem. Publica a mensagem de criação no broker kafka e gavar registro de criação em banco local H2.  
> Utilizado a Arquitetura Hexagonal na implementação.  

# 2 Operações   
`create` cria uma ordem e prublica um evento num tópico  
`delete` deleta uma ordem  
`findById` localiza uma orderm por id  
`list` lista todas as ordens  
`search` pocura ordems pelos atributos  
`update` atualiza uma ordem.  

# 3 Detalhamento da operação `create`
## 3.1 Diagramas classes  
![nome](src/main/resources/img/class1.png)  


## 3.2 Diagramas sequencia  
![nome](src/main/resources/img/sequence1.png)  

## 3.3 urls
* [http://localhost:9999/orders](http://localhost:9999/orders)  metodo: POST
 
##### 3.3.1 Request 
```bash 
curl -X POST "http://localhost:9999/orders" -H "accept: */*" -H "Content-Type: application/json" -d  
 "{ \"description\": \"string\", \"id\": 0, \"name\": \"string\", \"status\": \"NOT_PROCESSED\", \"total\": 0}" 
```

##### 3.3.2 Response sucesso
* Sucesso: `http.status=200`  
 
Response body  

```json  
{
  "description": "string",
  "id": 0,
  "name": "string",
  "total": 0,
  "status": "NOT_PROCESSED"
}
```

##### 3.3.3 Response erro
* Erro: `http.status=400`, `http.status=404`, `http.status=403`, `http.status=401` 
 
Response body  

```json  
{
  "status_code": 0,
  "message": "string"
}
```

# 4 healt-check  
* [http://localhost:9999/actuator](http://localhost:9999/actuator)  
* [http://localhost:9999/h2-console](http://localhost:9999/h2-console)  

# 5 Swagguer-ui  
* [http://localhost:9999/swagger-ui/index.html](http://localhost:9999/swagger-ui/index.html)  

# 5 Swagguer
* [http://localhost:9999/v2/api-docs](http://localhost:9999/v2/api-docs)  

 
