# BackEnd-System-For-Autodealership
**A Spring Boot application that uses a microservices architecture to create a back end system for an auto dealership.**

![](https://placehold.it/1000x40/ff0000/000000?text=IMPORTANT!)

**In order to run the application, start Discovery-service, Map and Price first!**



##  **Components** 
**Maps Service**

Given a latitude and a longitude it provides us a random address

- [x] To check the maps service: navigate into your browswer and check port ```9191```

**Price Service**

Simulates a backend that would store and retrieve the price of a vehicle given a vehicle id as input.

**Discovery Service**

Discovery service that registers the Price Service and the Vehicle Service

- [x] To check the discovery service: navigate into your browswer and check port ```8761```


##  **Features** 
- REST API exploring the main HTTP verbs and features
- Custom API Error handling using ControllerAdvice
- HTTP WebClient
- Automatic model mapping


##  **To Run...**
- [x] The project can be cloned(or download zip file) and open in IntelliJ. 
- [x] Navigate to each module -> go in ```pom.xml``` file -> Add as Maven project
- [x] Run ```mvn clean package``` from inside each package
- [x] Start each package by running the main method



**@Constantin Irimia :+1:**


