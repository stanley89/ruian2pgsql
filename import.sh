#/bin/sh
java -cp /app/target/ruian2pgsql-1.7.0-jar-with-dependencies.jar com.fordfrog.ruian2pgsql.App --convert-to-ewkt --input-dir /ruian --create-tables --db-connection-url $PSQL_URL 
