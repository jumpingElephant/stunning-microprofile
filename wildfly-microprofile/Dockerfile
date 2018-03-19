FROM sgrio/java-oracle:jre_9

RUN mkdir -p /opt/service

WORKDIR /opt/service

EXPOSE 8080

# Add the files
ADD "target/wildfly-microprofile-swarm.jar" wildfly-microprofile-swarm.jar

#ADD "target/wildfly-microprofile-hollow-swarm.jar" wildfly-microprofile-hollow-swarm.jar
#ADD "target/wildfly-microprofile.war" wildfly-microprofile.war

CMD java -jar wildfly-microprofile-swarm.jar
#CMD java -jar wildfly-microprofile-hollow-swarm.jar wildfly-microprofile.war
