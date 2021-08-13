# rasith-events Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## SQL Script and Postgres Database for initial data
You can run below command to start `postgres` database in docker engine

```
docker run --rm=true --name quarkus_test -e POSTGRES_USER=quarkus_test -e POSTGRES_PASSWORD=quarkus_test -e POSTGRES_DB=quarkus_test -p 5432:5432 postgres:13.3
```

You can create a database as `quarkus_test` using username as `quarkus_test` and password as `quarkus_test`

```
    create table Event (
       id int8 not null,
        addr_number varchar(255),
        client_id varchar(255),
        event_count int4,
        location_code varchar(255),
        location_id1 varchar(255),
        location_id2 varchar(255),
        rc_number varchar(255),
        trans_id uuid,
        trans_time varchar(255),
        primary key (id)
    )
```

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
`./mvnw compile quarkus:dev` or `mvn compile quarkus:dev`

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw -DskipTests package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw -DskipTests package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

## Swagger Urls

Local Swagger Url [http://localhost:8080/q/swagger-ui/](http://localhost:8080/q/swagger-ui/)

* http://localhost:8080/events/createevents

Sample Request :

```json
{
"batchId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
"records": [
{
"clientId": "123",
"events": [
{
"addrNbr": "Street",
"eventCnt": 1,
"locationCd": "Street 1",
"locationId1": "Street 2",
"locationId2": "Street 3"
}
],
"rcNum": "001",
"transId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
"transTms": "09/09/2021"
}
]
}
```

Curl Command :

```
curl -X 'POST' \
 'http://localhost:8080/events/createevents' \
 -H 'accept: _/_' \
 -H 'Content-Type: application/json' \
 -d '{
"batchId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
"records": [
{
"clientId": "123",
"events": [
{
"addrNbr": "Street",
"eventCnt": 1,
"locationCd": "Street 1",
"locationId1": "Street 2",
"locationId2": "Street 3"
}
],
"rcNum": "001",
"transId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
"transTms": "09/09/2021"
}
]
}'
```

* http://localhost:8080/events/getallevents

Curl Command :

```
curl -X 'GET' \
  'http://localhost:8080/events/getallevents' \
  -H 'accept: */*'
```

* http://localhost:8080/events/getevent/1

Curl Command :

```
curl -X 'GET' \
  'http://localhost:8080/events/getevent/1' \
  -H 'accept: */*'
```

* http://localhost:8080/events/updateevent

Sample Request :

```json
{
"batchId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
"records": [
{
"clientId": "09876",
"events": [
{
"addrNbr": "Colombo",
"eventCnt": 5,
"eventId": 1,
"locationCd": "Colombo",
"locationId1": "Colombo",
"locationId2": "Colombo"
}
],
"rcNum": "0987",
"transId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
"transTms": "12345"
}
]
}
```

Curl Command :

```
curl -X 'PUT' \
  'http://localhost:8080/events/updateevent' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "batchId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "records": [
    {
      "clientId": "09876",
      "events": [
        {
          "addrNbr": "Colombo",
          "eventCnt": 5,
          "eventId": 1,
          "locationCd": "Colombo",
          "locationId1": "Colombo",
          "locationId2": "Colombo"
        }
      ],
      "rcNum": "0987",
      "transId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
      "transTms": "12345"
    }
  ]
}'
```
* http://localhost:8080/events/deleteevent/1

Curl Command :

```
curl -X 'DELETE' \
  'http://localhost:8080/events/deleteevent/10' \
  -H 'accept: */*'
```
## Metrics 

Local Url [http://localhost:8080/q/metrics](http://localhost:8080/q/metrics)

## Source Code Reference

All the services expose via `EventProvider.java` class