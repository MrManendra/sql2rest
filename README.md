# sql to rest
This tool is written in java hence its a platform independent. Anyone can use this application by running the jar file


### Scope:
 - read sql query from sql.properties file and expose the api endpoint name same as the query key mentioned in sql.properties file.
 - Rest-full Service as json output
 - it can expose multiple apis based on how many query have been added in sql.properties file
 - zero downtime for adding any new query to expose new api, or any modification in existing query can reflect without the restart of application, just by hitting /api/reloadProperties
 - Open for any relational database management system(RDBMS)
 
### Tech Stack:
- Java-9
- Spring
- Spark

### TODO:
 - Logger

### Build the application:
- mvn clean install
 
### Run the application:
- download the distribution folder(google.drive.link here) add the db configuration, sql query and run the jar using below command
- java -jar sql2rest-1.0-jar-with-dependencies.jar
- check the application is running correctly by hitting this url(http://localhost:8080/api/health/check)
- to access the api for written query is very simple, for e.g - if the query is written as below
- fetchAllEmployee=select * from employee;
- then the api would be- http://localhost:8080/api/fetchAllEmployee

