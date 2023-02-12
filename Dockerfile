FROM amazoncorretto:19

LABEL maintainer="mehmetccm@gmail.com"

VOLUME /tmp

COPY /target/chatty-0.0.1-SNAPSHOT.jar chatty-0.0.1-SNAPSHOT.jar
COPY src/main/resources/application.yaml application.yaml

EXPOSE 8080

ENTRYPOINT ["java","-jar","chatty-0.0.1-SNAPSHOT.jar", "--spring.config.location=application.yaml"]
