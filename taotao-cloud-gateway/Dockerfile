# 此处不能用openjdk 验证码接口会报空指针异常
#FROM anapsix/alpine-java:8_server-jre_unlimited
FROM java:8

MAINTAINER 981376577@qq.com

ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /taotao-cloud-gateway

WORKDIR /taotao-cloud-gateway

EXPOSE 9900

ADD ./target/taotao-cloud-gateway-1.0.jar ./

CMD sleep 30; java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=dev -jar taotao-cloud-gateway-1.0.jar

