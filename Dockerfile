FROM adoptopenjdk/openjdk11:latest
MAINTAINER Kon Bi
VOLUME /tmp
ADD ./target/rtls.api.manager-1.0.1.jar  app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
EXPOSE 7010