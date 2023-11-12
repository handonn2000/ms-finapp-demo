FROM openjdk:17-jdk-slim

MAINTAINER github.com/handonn2000

ENV SRC_JAR="/target/accounts-1.0.0.jar"
ENV DES_JAR="accounts-1.0.0.jar"

COPY $SRC_JAR $DES_JAR

ENTRYPOINT java -jar $DES_JAR