# vivareal
Spotippos Challenge

Ambiente de desenvolvimento:
- Mongodb
- Grails Version: 3.1.9
- Groovy Version: 2.4.7
- JVM Version: 1.8.0_102

Como executar o projeto:

1 - Faça o clone desse projeto.

2 - Verifique a conexão com mongodb no arquivo: /grails-app/conf/application.yml

    Ambiente de desenvolvimento:
      mongodb:
                host: "localhost"
                port: 27017
                databaseName: "vivareal"
                
    Ambiente de testes:
      mongodb:
                host: "localhost"
                port: 27017
                databaseName: "vivarealtest"
                
3 - grails run-app.


** A classe BootStrap popula os dados necessários da aplicação, pegando os jsons diretamente do github da VivaReal, por isso não é necessário nenhum passo a mais.

Url mappings:


 |   POST   | /properties            | Action: save             |
 
 |   GET    | /properties            | Action: index            |
 
 |   GET    | /properties/${id}      | Action: show             |



O GET /properties aceita os parâmetros: 


  /properties?ax={integer}&ay={integer}&bx={integer}&by={integer}
