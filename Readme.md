How to start application
-------
Required:
* Java 8
* Maven
* NodeJs

Build and run spring boot application
>mvn -Dspring-boot.run.profiles=local -Dmaven.test.skip=true package spring-boot:run

Check swagger ui availability:
>http://localhost:4545/swagger-ui.html

Run webpack dev server:
>cd frontEnd

>npm i

>npm run devServer

Check ui:
>localhost:3001
