FROM maven:latest

WORKDIR /usr/src/app

COPY . /usr/src/app
RUN mvn package

ENV PORT 8080
EXPOSE $PORT
CMD [ "sh", "-c", "mvn spring-boot:run" ]
