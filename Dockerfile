FROM docker.artifactory.csx.com/adoptopenjdk/openjdk11:ubi-minimal
VOLUME /tmp
COPY  target/litmus-util.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]