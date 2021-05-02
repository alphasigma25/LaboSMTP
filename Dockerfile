FROM openjdk:8-jre

RUN apt-get update && \
  apt-get -y install netcat && \
  apt-get clean

COPY MockMock.jar /MockMock.jar

EXPOSE 8282
EXPOSE 2525

CMD ["java", "-jar", "MockMock.jar", "-p", "2525"]