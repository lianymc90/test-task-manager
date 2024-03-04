# Spring Boot, Angular, PostgreSQL Application
<p align="center">
  <img src="task manager video.gif" width="683" />
</p>

This repository contains a complete web application consisting of a backend developed with Spring Boot, a frontend 
developed with Angular, and a PostgreSQL database. The application runs in Docker containers and is 
orchestrated with Docker Compose.

## Requirements

Make sure you have the following installed on your system:

- Docker: [Docker Installation](https://docs.docker.com/get-docker/)
- Docker Compose: [Docker Compose Installation](https://docs.docker.com/compose/install/)

## Configuration

1. Clone this repository:

```
git clone <https://github.com/lianymc90/test-task-manager>
```

2. Open the `docker-compose.yml`.

## Execution

1. Run Docker Compose to start the containers:

```
docker-compose up -d
```

This will create and run the application containers in the background.

2. Once all containers are up and running, you can access the application from your web browser:

- Spring Boot Backend: [http://localhost:8080](http://localhost:8080)
- Angular Frontend: [http://localhost:80](http://localhost:80)

## Shutdown

To stop and remove the application containers, run the following command:

```
docker-compose down
```

This will stop and remove the containers, but will not delete the PostgreSQL data as it is stored in a Docker volume.
