##SETUP

#### Run MySSQL Database in a container:
``` 
docker-compose up
or docker-compose up -d
```

##
#### Prepare Environment:
```
mvn clean compile
```

##
#### CREATE database credentials using:
```
/src/main/resources/db/rollout/ro-initial-grants.sql
```

##
#### Prepare Jooq - Database Repository Classes:
```
mvn liquibase:clearCheckSums
mvn liquibase:dropAll
mvn liquibase:migrate
```

##
#### Run Application:
```
mvn spring-boot:run -Dspring-boot.run.profiles=debug
mvn spring-boot:run -Dspring-boot.run.profiles=stage
```

## REST API
#### Create Job:
```
http://localhost:9002/v1/job/create/{Type}
e.g. http://localhost:9002/v1/job/create/TypeA
```

#### Update Job for Transition:
```
http://localhost:9002/v1/job/update/{jobId}
e.g. http://localhost:9002/v1/job/update/1001

Using your browser or Postman:
> http://localhost:9002/v1/job/update/1
Result: New record will be created, State: Unallocated
> http://localhost:9002/v1/job/update/1
Result: Updated existing record with, State: Allocated
> http://localhost:9002/v1/job/update/1
Result: Updated existing record with, State: StateA
> http://localhost:9002/v1/job/update/1
Result: Updated existing record with, State: StateB
> http://localhost:9002/v1/job/update/1
Result: Updated existing record with, State: Completed
```

#### Update Job for Transition, Delete: TypeA and TypeB
```
Given above details.
>http://localhost:9002/v1/job/update/{jobid}/{state}
>http://localhost:9002/v1/job/update/1/Deleted
Result: Updated existing record with, State: Deleted
```

### Update Job for Transition, StateB to StateA: TypeB
```
Given above details.
Assuming, current state is StateB
>http://localhost:9002/v1/job/update/{jobid}/{state}
>http://localhost:9002/v1/job/update/1/StateA
Result: Updated existing record with, State: StateA
```
