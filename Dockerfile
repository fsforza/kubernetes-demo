FROM openjdk:8-jre-alpine

RUN adduser -D -s /bin/sh kube-demo

WORKDIR /home/kube-demo

ADD target/kube-demo*.jar kube-demo.jar

ENTRYPOINT [ "java", "-jar", "/home/kube-demo/kube-demo.jar"]

EXPOSE 8080
