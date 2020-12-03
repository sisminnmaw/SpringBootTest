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
- Run as Java Application *(topScoreRestful/src/main/java/smm/topScoreRestful/)* TopScoreRestfulApplication.java from Eclipse

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
<img width="797" alt="Screen Shot 2020-12-03 at 10 05 38 AM" src="https://user-images.githubusercontent.com/5299365/100954962-fd6b6480-3558-11eb-8290-b0500c420a62.png">

### 2. retrieve player record - [Get] /record/{id}

Parameters
Name | type | Description | Required
------------ | ------------ | ------------ | ------------
id | path | id of records | Yes

responses data sample
```
{
    "id": 1,
    "player": "edo",
    "score": 30,
    "time": "2020-11-30 15:00:00"
}
```

**Postman** request sample
<img width="798" alt="Screen Shot 2020-12-03 at 10 07 37 AM" src="https://user-images.githubusercontent.com/5299365/100954959-fd6b6480-3558-11eb-948a-e4c7a1ff54d4.png">

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
<img width="801" alt="Screen Shot 2020-12-03 at 11 14 50 AM" src="https://user-images.githubusercontent.com/5299365/100954953-f9d7dd80-3558-11eb-804e-92bd9eb6d199.png">

### 4. delete player record - [DELETE] /record/{id}

Parameters
Name | type | Description | Required
------------ | ------------ | ------------ | ------------
id | path | id of records | Yes

**Postman** request sample
<img width="801" alt="Screen Shot 2020-12-03 at 10 32 00 AM" src="https://user-images.githubusercontent.com/5299365/100954958-fcd2ce00-3558-11eb-94b5-5011e5589d27.png">

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
<img width="800" alt="Screen Shot 2020-12-03 at 10 33 22 AM" src="https://user-images.githubusercontent.com/5299365/100954956-fc3a3780-3558-11eb-9f94-7974652b9192.png">

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
<img width="801" alt="Screen Shot 2020-12-03 at 10 36 13 AM" src="https://user-images.githubusercontent.com/5299365/100954955-fba1a100-3558-11eb-9a37-d8169ee66c35.png">

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

When you use H2 console from UI view. H2 JDBC connection is changed in new version. In the old version default JDBC URL is *jdbc:h2:~/test* or *jdbc:h2:mem:test_mem* . For the new version, you need to find dbname from project console for access.
- http://localhost:8080/h2-console/ 
<img width="852" alt="Screen Shot 2020-12-03 at 11 17 12 AM" src="https://user-images.githubusercontent.com/5299365/100955102-3efc0f80-3559-11eb-9745-a92e44134583.png">

### quick API test patterns for your convenient
- retrieve all player records
  - [GET] http://localhost:8080/records
  - [GET] http://localhost:8080/records?page=0&size=3
  - [GET] http://localhost:8080/records?page=1&size=2
- retrieve player record
  - [GET] http://localhost:8080/record/1
- create player record
  - [POST] http://localhost:8080/record
```
    {
        "player": "EDO",
        "score": 100,
        "time": "2021-01-30 00:00:00"
    }
```
- create player record
  - [POST] http://localhost:8080/record
```
    {
        "player": "smith",
        "score": 70,
        "time": "2021-02-30 00:00:00"
    }
```
- retrieve player records for created check
  - [GET] http://localhost:8080/records?page=0&size=6
- delete player record
  - [DELETE] http://localhost:8080/record/2
- retrieve player records for deleted check
  - [GET] http://localhost:8080/records?page=0&size=6
- retrieve history and records of player
  - [GET] http://localhost:8080/history?player=EDO
- filter records
  - [GET] http://localhost:8080/filter
  - [GET] http://localhost:8080/filter?player=EDO&player=JoHn
  - [GET] http://localhost:8080/filter?player=EDO&player=JoHn&page=0&size=4
  - [GET] http://localhost:8080/filter?after=2020-12-01%2012:00:00
  - [GET] http://localhost:8080/filter?before=2020-12-01%2012:00:00


## Conclusion

Thank you for reading documentation. The project is not completed, needs a lot of validations and features. You can ask me via mail or create issues for any trouble in the installation or any bug found in the project.


