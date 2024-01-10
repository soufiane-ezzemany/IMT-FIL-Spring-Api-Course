# imt-202311-tp-spring-ezzemany

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

## Usage

There are 4 endpoints in this project:

connect to <localhost:8080/swagger-ui.html> to see the documentation of the endpoints

## Authors

- Ezzemany Soufiane 
- Joly--Jehenne Léo
- Le Goff Quentin
- Alaoui Anas
