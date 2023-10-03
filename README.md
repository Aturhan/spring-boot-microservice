# Starting

This Spring-Boot Microservice project includes customer-service, travel-service, api-gateway, discovery-server and taxi-service modules. API-gateway performs loadbalancing and authentication by sending incoming requests to the relevant services. Discovery-server takes on the task of finding out through which IP addresses and port numbers other services can be accessed. Other services can register themselves with the Discovery Server and thus learn about the existence and access information of other services.

## Uploading 
   
    Clone the project's GitHub repository: 
    git clone https://github.com/Aturhan/spring-boot-microservice.git
    
    
    Run the following Maven command to install the dependencies:
    mvn install
    
    Run the following command to start the application:
    mvn spring-boot:run
    
## Licence

[MIT](https://choosealicense.com/licenses/mit/)

## Dependencies
- Spring Boot
- Maven
- Lombok
- Spring Cloud Gateway
- Spring CLoud Eureka-Server,Client
- Spring Web
- Spring WebFlux
- Keycloak
- Circuit Breaker
- Actuator
- PostgreSQL
- Mysql
