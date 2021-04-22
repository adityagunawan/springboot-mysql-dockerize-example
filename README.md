## Springboot & Mysql Dockerize

**springboot application whit mysql database, & log archive.**
before build docker image you need to pull mysql 8 image & create connection network for container integration

## prepare database & connection

pull mysql from docker hub :
`docker pull mysql:latest`

create mysql container :
`docker container create --name mysql -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 mysql:latest`

create network :
`docker network create local-network`

connect mysql to network :
`docker network connect local-network mysql`

start mysql container :
`docker container start mysql`

## prepare springboot application

build package : `mvn clean package -DskipTests`

build image : `docker build -t springboot-mysql .`

create container from image : `docker container create --name springboot-mysql -p 8081:8081 -e datasource=jdbc:mysql://mysql:3306/employee springboot-mysql`

connect container to network : `docker network connect local-network springboot-mysql`

start application container : `docker container start springboot-mysql`

## test application on browser

insert data : http://localhost:8081/employee/add/adityagunawan/backend-developer

get all data : http://localhost:8081/employee/get-all

## check realtime log

`docker exec -i -t springboot-mysql bash`

`tail -500f logs/springboot-mysql.log`