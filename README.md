Spring Boot Coding Dojo
---

Welcome to the Spring Boot Coding Dojo!

### Introduction

This is a simple application that requests its data to [OpenWeather](https://openweathermap.org/) and store its result 
in a database. The current implementation has quite a few problems making it a non-production ready product.

### The task

As the new engineer leading this project your first task is to make it production ready, feel free to refactor any piece
necessary to achieve the goal.

### How to deliver the code

Please send an email containing your solution with a link to a public repository.

>**DO NOT create a Pull Request with your solution** 

### Footnote
It's possible to generate the API key going to the [OpenWeather Sign up](https://openweathermap.org/appid) page.

### How to compile the application

Execute: 

./mvnw clean package 

or

mvnw.cmd clean package (windows)

### How to execute the application

Run with Java version >= 11

java -jar target\coding-dojo-spring-boot-0.0.1.jar --spring.datasource.url=jdbc:h2:mem:testdb --open-weather-api.app-id=<YOUR API KEY>

This will run the application using in memory database. Also Postgres can be used just changing the URL 
and specifying the username and password of the database

Example:

java -jar target\coding-dojo-spring-boot-0.0.1.jar --spring.datasource.url=jdbc:postgresql://localhost:5432/weather
 --spring.datasource.username=user --spring.datasource.password=pass --open-weather-api.app-id=<YOUR API KEY>

Note that in all cases you need your own Opean Weather API Key. Check the link above.

### How to use the application

Call the following endpoint

http://localhost:8080/weather?city=<City>

Example

http://localhost:8080/weather?city=Amsterdam

This return a Json result with the entity saved in the database providing the Current temperature.







