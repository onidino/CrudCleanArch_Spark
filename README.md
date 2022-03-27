# CrudCleanArch_Spark

SPARK + CLEAN ARCHITECTURE + mySQL DATABASE:
------------------------------------------------------------------------------------------------------------------------
A simple REST-API with SPARK using the Clean Architecture assure the implementation of the SOLID principles and with mySQL database with DOCKER.

*SPARK:*
https://sparkjava.com/

*SOLID PRINCIPLES:*
https://www.baeldung.com/solid-principles

*CLEAN ARCHITECTURE:*
https://medium.com/slalom-build/clean-architecture-with-java-11-f78bba431041

*DATABASE:*
The SQL2O library was used to manage sql transactions.
https://www.baeldung.com/java-sql2o


BASIC FUNCTIONING
------------------------------------------------------------------------------------------------------------------------
The project has CRUD operations implemented in 4 entrypoints for POST, GET, PUT, DELETE.
This allows to create and modify records with a numeric "id" (autoincremental) and "record_data" as text information,
wich are saved in a mySQL database.

curl --request POST \
  --url http://localhost:8080/clean/spark/create \
  --header 'Content-Type: application/json' \
  --data '{
	"data" : null
}'

curl --request GET \
  --url http://localhost:8080/clean/spark/read/ID \
  --header 'Content-Type: application/json'
  
curl --request PUT \
  --url http://localhost:8080/clean/spark/update/ID \
  --header 'Content-Type: application/json' \
  --data '{
	"data" : null
}'

curl --request DELETE \
  --url http://localhost:8080/clean/spark/delete/ID


TESTING
------------------------------------------------------------------------------------------------------------------------
Jupiter (Junit) was used for making the tests, and HSQL for database tests.

HSQL: https://www.baeldung.com/java-in-memory-databases