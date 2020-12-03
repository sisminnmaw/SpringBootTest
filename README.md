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

## List of Contents
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

No | Method | Path | Description | Responses Code
------------ | ------------ | ------------- | ------------- | -------------
1 | GET | /records | retrieve all player records | 200 (OK)
2 | GET | /record/{id} | retrieve player record | 200 (OK), 404 (Not Found)
3 | POST | /record | create player record | 201 (Created)
4 | DELETE | /record/{id} | delete player record | 200 (OK), 404 (Not Found)
5 | GET | /history | retrieve history and records of player | 200 (OK), 404 (Not Found)
6 | GET | /filter | filter records | 200 (OK), 404 (Not Found)

### 1. retrieve all player records - [GET] /records

Parameters
Name | type | Description | Required | Default value
------------ | ------------ | ------------ | ------------ | ------------
page | query | number of current page, first page value is 0 (n -1) | No | 0
size | query | item size of page | No | 3

responses data sample
```
{
    "content": [
        {
            "id": 1,
            "player": "Edo",
            "score": 30,
            "time": "2020-11-30 15:00:00"
        }
    ],
    "pageable": {
      ...
    },
    ....
}
```

**Postman** request sample

### 2. retrieve player record - [Get] /record/{id}

Parameters
Name | type | Description | Required
------------ | ------------ | ------------ | ------------
id | path | id of records | Yes

**Postman** request sample

### 3. create player record - [POST] /record

Parameters
Name | type | Description | Required
------------ | ------------ | ------------ | ------------
\- | body (json) | player record data | Yes

post parameter sample
```
{
    "player": "EDO",
    "score": 100,
    "time": "2021-01-30 00:00:00"
}
```

**Postman** request sample

### 4. delete player record - [DELETE] /record/{id}

Parameters
Name | type | Description | Required
------------ | ------------ | ------------ | ------------
id | path | id of records | Yes

**Postman** request sample


### 5. retrieve history and records of player - [GET] /history

Parameters
Name | type | Description | Required
------------ | ------------ | ------------ | ------------
player | query | target player name | Yes

responses data sample
```
{
    "topScore": 50,
    "lowScore": 30,
    "avgScore": 40.0,
    "recordList": [
        {
            "id": 1,
            "player": "edo",
            "score": 30,
            "time": "2020-11-30 15:00:00"
        },
        ...
    ]
}
```

**Postman** request sample

### 6. filter records - [GET] /filter

Parameters
Name | type | Description | Required | Default value | Multiple times
------------ | ------------ | ------------ | ------------ | ------------ | ------------
page | query | number of current page, first page value is 0 (n -1) | No | 0 | No 
size | query | item size of page | No | 3 | No 
after | query | date value for filter (yyyy-MM-dd HH:mm:ss) | No | Null | No 
before | query | date value for filter (yyyy-MM-dd HH:mm:ss) | No | Null | No 
player | query | player name | No | Null | Yes 

!! after, before and player filters can't use at the same time. only one filter per request. we will be upgraded in the next version for efficiency.

responses data sample
```
{
    "content": [
        {
            "id": 1,
            "player": "Edo",
            "score": 30,
            "time": "2020-11-30 15:00:00"
        }
    ],
    "pageable": {
      ...
    },
    ....
}
```

**Postman** request sample


## Extra

### initial Data

initial record data crated below. you can change initial data in */topScoreRestful/src/main/resources/data.sql* . Data from this file will be created when project is start.

id | player | score | time
------------ | ------------ | ------------ | ------------
1 | edo | 30 | 2020-12-01 00:00:00
2 | smith | 50 | 2020-11-01 00:00:00
3 | john | 60 | 2020-10-01 00:00:00
4 | edo | 50 | 2021-01-01 00:00:00

### Swagger API doc

Also provided generated API documentations with Swagger. You can access from this links after project started.
- http://localhost:8080/swagger-ui.html for API documentations UI view
- http://localhost:8080/v2/api-docs for API documentations JSON format view


### H2 UI

When you use H2 console from UI view. H2 JDBC connection is changed in new version. In the old version default JDBC URL is *jdbc:h2:~/test* or *jdbc:h2:mem:test_mem* . For the new version, you need to look dbname from project console for access.

- http://localhost:8080/h2-console/ 

### quick API test patterns for your convenient

## Conclusion


