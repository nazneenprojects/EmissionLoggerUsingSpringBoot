# EmissionLoggerUsingSpringBoot
# Overview
This Project is to demonstrate "Carbon emission Logger" . The carbon emission logger represents a web server that is accepting emission records. These events log carbon emissions for different kind of products.   They use co2tonnes as unit, include a recoding date and reference each product by an id: POST /emissions {  id: String,  productId: String,  emission: Float,  recordedAt: Date }   In addition it is possible to query statistics for each product: GET /stats/&lt;productId>   This route returns an object with the following data: The day with highest recorded emissions An average of emissions per day and the total amount of emissions since recording.

This project designed internally in below layers:
a) Controller
b) Services
c) Repository
d) Error

# Steps to follow in order to use "EmissionLogger" Project

## 1) Initial set up steps 
Either you can download code files or clone this repository on your local machine. 
Import 'EmiisionTrack' project into Eclipse as existing maven project.
Make sure your project should download all required maven repo.

You will require Postman tool to play with REST API services.

## Note:
This is a springBoot project with dependency : i) spring-boot-starter-web ii) spring-boot-starter-data-jpa iii) spring-boot-devtools iv)h2 v) spring-boot-starter-test vi) spring-boot-maven-plugin

Java Version : 1.8
SpringBoot Version : 2.5.4

This project will run on by default on embedded tomcat server at port 8080.
This project is using H2 in memory Database to store / retrive data using REST API.

## 2)How to Run?
Select '/EmissionTrack/src/main/java/com/app/emission/EmissionTrack/EmissionTrackApplication.java'
and do right click : Run As -> Java Appliction. This will start 'EmissionTrack' Service

This project will run on by default on embedded tomcat server at port 8080.
This project is using H2 in memory Database to store / retrive data using REST API.

Follow below steps to check REST API services provided by this EmissionTrack Project.

## 3)Create Emission Resords using POST
Launch Postman tool.
Select POST method 
### Add URL as http://localhost:8080/api/emissions
### Add raw json data as below:
[
    {"id": "P1", "productId": "Heavy-Duty-Truck", "emission": 6.64,"recordedAt": "25-08-2021"},
    {"id": "P2", "productId": "Heavy-Duty-Truck", "emission": 5.80,"recordedAt": "26-08-2021"},
    {"id": "P3", "productId": "Heavy-Duty-Truck", "emission": 9.20,"recordedAt": "27-08-2021"},
    {"id": "P4", "productId": "Medium-Duty-Truck", "emission": 5.40,"recordedAt": "25-08-2021"},
    {"id": "P5", "productId": "Medium-Duty-Truck", "emission": 4.80,"recordedAt": "26-08-2021"},
    {"id": "P6", "productId": "Medium-Duty-Truck", "emission": 7.63,"recordedAt": "27-08-2021"},
    {"id": "P7", "productId": "Light-Duty-Truck", "emission": 3.24,"recordedAt": "25-08-2021"},
    {"id": "P8", "productId": "Light-Duty-Truck", "emission": 2.89,"recordedAt": "26-08-2021"},
    {"id": "P9", "productId": "Light-Duty-Truck", "emission": 2.25,"recordedAt": "27-08-2021"},
    {"id": "P10", "productId": "Passenger", "emission": 0.86,"recordedAt": "25-08-2021"},
    {"id": "P11", "productId": "Passenger", "emission": 1.34,"recordedAt": "26-08-2021"},
    {"id": "P12", "productId": "Passenger", "emission": 2.23,"recordedAt": "27-08-2021"}
    
]

Click on send button.
In the below Response section, you will see status 200 OK along with json data which is inserted.

### To check the same data in DB, hit http://localhost:8080/h2-console in browser.
Enter below details:
JDBC URL -> jdbc:h2:mem:testdb
User Name -> sa
Password -> (not need to enter anything)
Click on Connect

You will see 'EMISSIONTBL' created under DS. 
Fire query 'SELECT * FROM EMISSIONTBL ' to get all records.

## 4) Get stats of Emission on the available records using GET
Launch Postman tool
Select GET method 
### Add URL as http://localhost:8080/api/stats/Passenger
Click on Send button.
In the below Response section, you will see status 200 OK along with stats of Emission records as below output.

### Output:

| Highest recorded emissions Day: 2021-08-27|--------------|| Average of emissions per day: 1.4766666889190674  co2tonnes|--------------|| Total amount of emissions since recording: 4.430000066757202  co2tonnes


## 5) Run BDD Test Cases to validate this project
Got to '/EmissionTrack/src/test/java/com/app/emission/EmissionTrack/EmissionTrackApplicationTests.java'
Do right click : Run As -> JUnit Test
Refer Junit Section under Eclipse perspective to check Test Results.
All 7 test cases shall pass.


## 6) Additional Info on Error Handling
This Project has capability to handle few false scearios where it will show custom Error messages based on use cases.
### a) Trying to find the stats of productId which is not present in DB
### input URL:  http://localhost:8080/api/stats/6
### output:
{"statusCode":404,"timestamp":"2021-08-28T17:36:40.931+00:00","message":"6 Not found","description":"uri=/api/stats/6"}

There are few more scenarios which is handled using this way.



