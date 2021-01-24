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

