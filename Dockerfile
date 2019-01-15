FROM openjdk:8-jre-alpine

ENV JAVA_OPTS=""

RUN adduser -D -s /bin/sh kube-demo

WORKDIR /home/kube-demo

ADD entrypoint.sh entrypoint.sh

RUN chmod 755 entrypoint.sh && chown kube-demo:kube-demo entrypoint.sh

USER kube-demo

ENTRYPOINT [ "./entrypoint.sh"]

EXPOSE 8080

ADD target/kube-demo*.jar kube-demo.jar
