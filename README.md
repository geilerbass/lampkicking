#Lampkicking Hoover Simulator

## Prerequisites
- Requires Java 11 and Maven

## Run Instructions

- Clone the project and install locally
- From the root of the project, build the project with `mvn clean install`
- This should create an executable Jar file in the target directory of the project
- Use the command `java -jar target/lampkicking-0.0.1-SNAPSHOT.jar` to run the web service on your machine's local host

## Use the Service

You can check that the service works by using a REST client, for example, with cUrl, the following command: 

>`curl -X POST http://localhost:8080/hoover -H 'Content-Type: application/json' -d '{"roomSize":[5,5],"coords":[1,2],"patches":[[1,0],[2,2],[2,3]],"instructions":"NNESEESWNWSNEWNNWWWSEEE"}'`

should return the response: 
>`{"coords":[3,3],"patches":2}`