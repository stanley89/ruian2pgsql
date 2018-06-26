create database ovk;
create extension postgis;

docker build -t ruian2pgsql .
docker run  --net host -e PSQL_URL=jdbc:postgresql://localhost/ovk?user=ovk\&password=xxx  -v /home/stanley/ruian:/ruian ruian2pgsql
