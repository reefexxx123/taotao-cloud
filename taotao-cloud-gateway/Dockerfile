FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER 981376577@qq.com

ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /taotao-cloud-gateway

WORKDIR /taotao-cloud-gateway

EXPOSE 9800

ADD ./target/taotao-cloud-gateway-1.0.jar ./

CMD sleep 30; java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=pre -jar taotao-cloud-gateway-1.0.jar

##  基础镜像
#FROM java:openjdk-8-jre-alpine
## 维护者信息
#MAINTAINER im.lihaodong@gmail.com
##Default to UTF-8 file.encoding
#ENV LANG C.UTF-8
##设置alpine时区
#ENV TIMEZONE Asia/Shanghai
##alpine自带的包含dl-cdn的域名非常慢，需要修改后才能下载数据。
#RUN apk add -U tzdata && ln -snf /usr/share/zoneinfo/${TIMEZONE} /etc/localtime && echo "${TIMEZONE}" > /etc/timezone
#RUN sed -i -e 's/dl-cdn/dl-4/g' /etc/apk/repositories && apk add -U tzdata && ln -snf /usr/share/zoneinfo/${TIMEZONE} /etc/localtime && echo "${TIMEZONE}" > /etc/timezone
##添加应用
#ADD prex-auth.jar prex-auth.jar
##参数
##ENV PARAMS=""
##端口
#EXPOSE 8001
##执行操作 默认启动线上环境
#ENTRYPOINT [ "sh", "-c", "java -Xmx50m -Djava.security.egd=file:/dev/./urandom -jar prex-auth.jar -Dspring.cloud.nacos.config.namespace=9b3a8ff9-4abf-4045-af45-128aaa775e4f" ]


