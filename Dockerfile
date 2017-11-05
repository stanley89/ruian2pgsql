FROM debian:wheezy
RUN apt-get update && apt-get install -y default-jdk maven git
run git clone https://github.com/stanley89/ruian2pgsql /app
RUN cd /app; mvn install
RUN mkdir /ruian
VOLUME /ruian
CMD ./app/import.sh
