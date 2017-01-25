# AMT Labo 2 : Gamification Platform

## Introduction

This project is the result of the second lab session for Thibaut Loiseau, Ioannis Noukakis, Antoine Drabble and Guillaume Serneels in the AMT Course 2016-2017 at HEIG-VD. The goal of this session was to develop and deploy a gamification platform allowing user to improve their application by adding gamification features such as Badges unlocking.


## The web application

The **/spring-server** folder contains the source code of our platform 


## REST API Documentation

The **/documentation** folder at the root of our repository contains a pdf version of the api documentation generated with the swagger editor. 

When you run the server, an interactive version of the documentation is generated and served on the **/api** route. To do this we used the bottum-up approach to swagger proposed by Spring Fox.

## Deployment

### Pre-requisite

To run the project, you must install the following prerequisite:

-Maven version 3.3.9 

-Java version 1.8 

-MySQL: 10.1.20-MariaDB

And create an empty MySQL Schema named gamification.

To allow access to the database, you must have a root@localhost user on your MySQL instance set with no password or fill in your personnal password in the /spring-server/src/main/resources/application.properties file.

### Quick start

Go to **/spring-server** folder at the root of our repository and run:

```
mvn compile spring-boot:run

```

## Testing

### Cucumber JVM

The **Executable_Specification** folder located at the root of our repository contains an automated test project develloped with the CucumberJVM framework allowing us to test each endpoint of our API.

### Sample front-end application

The **gh_pages** branch contains a basic Clicker application that we used to test a sample Gamification process using our platform. 
If you want to try it out, you must first start the REST API and clone the gh-pages branch.
Then you must insert data in the database. There is a list of Postman requests in the `AMT-Présentation.postman_collection.json` file at the root of the gh-pages branch.
Finally you must configure the token in the `js/script.js` file. On the second line, you must edit the token variable to match your application's token which is received when you login or register.
Then you can open the `index.html` file. 



