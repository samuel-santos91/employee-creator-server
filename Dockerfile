FROM --platform=linux/amd64 azul/zulu-openjdk:17-latest
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]