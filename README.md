# imt-202311-tp-spring-ezzemany

## Requirements

For building and running the application you need:

- [JDK 21]
- [Maven 3]

## Getting started

To get started with this project, clone this repository and run the following commands:

Start docker service :
```bash
sudo systemctl start docker 
```
Install and start project :
```bash
cd imt-202311-tp-spring-ezzemany
docker compose up -d
mvn clean install
mvn spring-boot:run
```

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `de.codecentric.springbootsample.Application` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Usage

There are 5 endpoints in this project:

connect to <localhost:8080/swagger-ui.html> to see the documentation of the endpoints

## Authors

- Ezzemany Soufiane 
- Joly--Jehenne Léo
- Le Goff Quentin
- Alaoui Anas
