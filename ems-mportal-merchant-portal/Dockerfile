FROM docker.io/library/amazoncorretto:17-alpine-jdk
CMD mvn clean package
ARG JAR_FILE
COPY "archref-java-ms.jar" app.jar
COPY "/config/" /config

RUN apk --no-cache add  \
    && adduser -D corretto \
    && chown corretto:corretto app.jar \
    && chown -R corretto:corretto /config

RUN mkdir -p /vault \
    && chown corretto:corretto /vault \
    && chmod 755 /vault

USER corretto

ENTRYPOINT ["java", "-jar", "app.jar"]