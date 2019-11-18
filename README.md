# sql to rest
This tool is written in java hence its a platform independent. Anyone can use this application by running the jar file


### Scope:
 - read sql query from sql.properties file and expose the api endpoint name same as the query key mentioned in sql.properties file.
 - Rest-full Service as json output
 - it can expose multiple apis based on how many query have been added in sql.properties file
 - zero downtime for adding any new query to expose new api, or any modification in existing query can reflect without the restart of application, just by hitting /api/reloadProperties
 - Open for any relational database management system(RDBMS)
 
### Tech Stack:
- Java-1.8
- Spring-5.1.3.RELEASE
- Spark-2.7.2

### Build the application:
- mvn clean install
 
### Run the application:
- download the sample-distribution folder add the db configuration, sql query and run the jar using below command
- java -jar sql2rest-1.0-jar-with-dependencies.jar
- check the application is running correctly by hitting this url(http://localhost:8080/api/health/check)
- to access the api for written query is very simple, for e.g - if the query is written as below
- fetchAllEmployee=select * from employee;
- then the api would be- http://localhost:8080/api/fetchAllEmployee

- health check api can be fount at - http://localhost:8080/api/health/check

- if any new query to be added, just hit the below reload api which will enable new changes without any downtime of your application
- http://localhost:8080/api/reloadProperties

## EXAMPLES - 
## case-1
- write the below query, to get rest api like this - http://localhost:8080/api/fetchEmployeeByJoiningDate?fromDate=2019-09-15&toDate=2019-09-16

fetchEmployeeByJoiningDate=select * from employee where joining_date between ':fromDate' and ':toDate';

## case-2

- like wise we can access this api - http://localhost:8080/api/fetchEmployeeByIds?empIds='ABC-123','ABC-456'

fetchEmployeeByIds=select * from employee where emp_id in(:empIds);

## case-3

- http://localhost:8080/api/fetchEmployeeByEmail?email=test@gmail.com

fetchEmployeeByEmail=select * from employee where email=':email';
