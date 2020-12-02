# Spring Boot Test : Top Score Ranking Project

## About project
This API keeps score records for a certain game for a group of players and provide necessary functions like add record, delete record, retrieve records, retrieve player history, etc.

development stack
- JAVA 11, 
- Gradle 6.7, 
- Spring 5.3.1, 
- Spring Boot 2.4, 
- JPA, 
- H2
- JUnit 5

## Document Tour
- [To build and run app](https://github.com/sisminnmaw/SpringBootTest#to-build-and-run-app)
- [Run unit and integration tests](https://github.com/sisminnmaw/SpringBootTest#run-unit-and-integration-tests)
- [API documentation](https://github.com/sisminnmaw/SpringBootTest#api-documentation)
- [Extra](https://github.com/sisminnmaw/SpringBootTest#extra)

## To build and run app

### Pre-requisite
1. [Eclipse IDE](https://www.eclipse.org/downloads/)
2. [JAVA 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
3. [Gradle 6.7](https://gradle.org/install/)
4. Gradle Eclipse plugins (check **Gradle Integration** installed or not in *Eclipse>Help>Eclipse Marketplace*)

!! when you install JAVA and Gradle, don't forget to setting in system path. And also check version in command line *java --version* and *gradle -v*.

### Project installation
- git clone or download from current git repository
- go to *downloaded/file/path/topScoreRestful/* from commend line, build gradle with commend and wait for finish
```
$ gradle build
```
- open Eclipse IDE and Import Project. From **File>Import** and then Import widget will appear, choose **Existing Gradle Project** and choose *downloaded/file/path/topScoreRestful/* to **Project root directory**

### Run App
- Run *(topScoreRestful/src/main/java/smm/topScoreRestful/)* TopScoreRestfulApplication.java from Eclipse

initial data already created in project, so you can run and test from **Postman** immediately.

## Run unit and integration tests
- Run as Junit Test *(topScoreRestful/src/test/java/smm/topScoreRestful/controller/)* PlayerRecordControllerTest.java from Eclipse
- Run as Junit Test *(topScoreRestful/src/test/java/smm/topScoreRestful/util)* commonUtilityTest.java from Eclipse

## API documentation

base URL: http://localhost:8080 or http://localhost: + user defined port

No | Method | Path | Description
------------ | ------------ | ------------- | -------------
1 | GET | /records | retrieve all players records
2 | GET | /record/{id} | retrieve player record
3 | POST | /record | create player record
4 | DELETE | /record | delete player record
5 | GET | /history | retrieve player records
6 | GET | /filter | filter records


## Extra
