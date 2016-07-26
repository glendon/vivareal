# vivareal
Spotippos Challenge

Ambiente de desenvolvimento:
- Mongodb
- Grails Version: 3.1.9
- Groovy Version: 2.4.7
- JVM Version: 1.8.0_102

### INSTRUÇOES PARA RODAR "local" ou num Container Docker.

LOCAL >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 
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
                
3 - Executar na raiz do projeto: grails run-app

OBSERVAÇÃO ->>>
No arquivo /grails-app/conf/application.yml as propriedades abaixo são usadas para dar carga de dados no MongoDB, portanto, deve ser utilizada apenas na primera execução (ou quando houver necessidade - por exemplo: se o banco for deletado).

Na segunda execução, trocar os valores true para false


    bootstrap:
                insert:
                    properties : true
                    provinces : true


DOCKER >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

1 - docker pull glendonml/vivareal-challenge
2 - docker run -it -p 8080:8080 glendonml/vivareal-challenge
