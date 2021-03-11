FROM openjdk:11
WORKDIR /srv/
ADD target/Backend-*.jar evoting-backend.jar
EXPOSE 8080
CMD java  $JAVA_OPTS -jar evoting-backend.jar