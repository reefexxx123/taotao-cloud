FROM jiuxi.harbor.org/stable/java:8

MAINTAINER "dengtao"<981376577@qq.com>

ADD ./target/application.jar application.jar

EXPOSE 8080

CMD ["/bin/sh", "-c", "java -jar application.jar"]
