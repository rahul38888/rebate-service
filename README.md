# Rebate Service
Rebate management system that handles rebate program data, calculates rebates, and provides endpoints for basic reporting.

## Setup and how to run
There are few option you can choose from to run this application. The application uses MongoDb as its underlying database engine. This engine could be running locally, as a container or in cloud.
Follow steps as per your requirements.

Clone the repo
```sh
   git clone git@github.com:rahul38888/rebate-service.git
```
### Option 1. Run with local MongoDb Server
1. Set database connection string in environments
```sh
    export CONNECTION_STRING=mongodb://localhost:27017/
```
2. Install dependencies and build jar
```sh
    mvn clean install -DskipTests
```
3. Start application
```sh
  java -jar target/rebate-service-1.0-SNAPSHOT.jar
```

### Option 2. Run application in Docker with MongoDb Server running in container
1. Make sure your docker engine is running before moving forward
2. Build container and run docker service
```sh
    make run-all
```

### Option 3. Run application with cloud mongo server
I am using Atlas's free shared MongoDb Cluster for this part. AWS provides DocumentDb as their own version of MongoDb, which can be used by just replacing connection string in later steps.

1. Make sure that proper Network access rules has been set so that the local instance of your application can connect to the cluster. 
With free account you can whitelist your current IP address or Allow Access From Everywhere (Which is not advised).
2. Set database connection string in environments
```sh
    export CONNECTION_STRING=mongodb+srv://<username>:<password>@<cluster_name>.zzzzz.mongodb.net/?retryWrites=true&w=majority&appName=<appName>
```
You need not build this connection string. Just go to Connect -> Java on Atlas cluster home page.
3. Install dependencies and build jar
```sh
    mvn clean install -DskipTests
```
4. Start application
```sh
  java -jar target/rebate-service-1.0-SNAPSHOT.jar
```

## Class Diagram
[![Class digram](documentation/class_diagram.svg)](documentation/class_diagram.svg)