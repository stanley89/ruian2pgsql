FROM debian:buster
RUN apt-get update && apt-get install -y default-jdk maven git
RUN mkdir /app 
RUN adduser --disabled-login --quiet --gecos Ruian ruian
RUN chown ruian.ruian /app
USER ruian
run git clone https://github.com/stanley89/ruian2pgsql /app
RUN cd /app; mvn install
CMD ./app/import.sh
