### GraphQL with SpringBOOT and React JS

This is a reference implementation to highlight the advantages of GraphQL. 

### Tech Stack[Backend]
- SpringBoot
- SpringBoot Starter Web
- SpringBoot Starter GraphQL
- Postgres

### Tech Stack[Client]
- ReactJS
- Apollo GraphQL Client
- Pico CSS

### Tech Stack[Build and Test]
- Maven
- Test Container Postgres
- Frontend Maven Plugin[to build node modules]
- Docker Compose


### Getting Started
Step 1:
```agsl
mvn clean package
```

Step 2:
```agsl
docker compose up -d
```

Application URL: http://localhost:8080

GraphQL Console URL: http://localhost:8080/graphiql



