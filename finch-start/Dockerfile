FROM  java:8

MAINTAINER leon "songliu0608@qq.com"

VOLUME /tmp \
    /root/logs

COPY target/finch-start-0.0.1-SNAPSHOT.jar app.jar

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]


