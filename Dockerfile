FROM jamesdbloom/docker-java8-maven
MAINTAINER jamescross91
# ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64

# # Update
# RUN apt-get update

# RUN apt-get install -y  maven

RUN mkdir sqlrest

ADD . /sqlrest

ENTRYPOINT mvn -f /sqlrest/pom.xml clean compile assembly:single && /usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/bin/java -classpath .:/sqlrest/target/jdbc-sql-rest-1.0-jar-with-dependencies.jar:/sqlrest/src/RedshiftJDBC41-1.1.7.1007.jar Bootstrap
#ENTRYPOINT /bin/sh -c bash