FROM aadoptopenjdk/openjdk15:ubi
MAINTAINER Kon Bi
VOLUME /tmp
ADD ./target/rtls.api.manager-1.0.0.jar  app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
EXPOSE 7001