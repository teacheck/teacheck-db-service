FROM openjdk:8-jre-slim

ENV VERTICLE_FILE target/db-microservice-1.0-SNAPSHOT-fat.jar
ENV VERTICLE_HOME /usr/verticles

COPY $VERTICLE_FILE $VERTICLE_HOME/teacheck-db-microservice-1.0-fat.jar

WORKDIR $VERTICLE_HOME
CMD ["java", \
  "-XshowSettings:vm", \
  "-XX:+UnlockExperimentalVMOptions", \
  "-XX:+UseCGroupMemoryLimitForHeap", \
  "-XX:MaxRAMFraction=2", \
  "-server", \
  "-jar", "teacheck-db-microservice-1.0-fat.jar"]

EXPOSE 8080
