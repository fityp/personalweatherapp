# PWA: Personal Weather Application

##Create Docker MariaDB
```
docker pull mariadb

docker run -p 3306:3306 --name weatherdb -e MYSQL_ROOT_PASSWORD=rootpassword -d mariadb
<some string>


docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' weatherdb
<some url>
```
##Create Database
In Docker Terminal (CLI)
```mariadb
mysql -u root -p
(Type in password)

CREATE DATABASE pwsdb;

SHOW DATABASES;
```
##Create Database User
```mariadb
CREATE USER 'dbdev'@localhost IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* TO 'dbdev'@'%' IDENTIFIED by 'password';
```


##Edit Application Properties
 
Location: personalweatherapplication\src\main\resources\application.properties
```properties
spring.datasource.url = ${MYSQL_DB_HOST}:${MYSQL_DB_PORT}/pwsdb
spring.datasource.username = ${MYSQL_DB_USERNAME}
spring.datasource.password = ${MYSQL_DB_PASSWORD}
```


##Set Environment Variables
* MYSQL_DB_HOST=jdbc:mysql://127.17.0.2
* MYSQL_DB_PORT=3306
* MYSQL_DB_USERNAME=dbdev
* MYSQL_DB_PASSWORD=password
