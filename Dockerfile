FROM openjdk:17-jdk-oracle

LABEL maintainer="lorenspace <cxyphp@gmail.com>"

# VOLUME 指定了临时文件目录为 /tmp。
# 其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp
VOLUME /tmp

WORKDIR /app

COPY . .

COPY target/*.jar /app/app.jar

# 设定正确的时区
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
    echo 'Asia/Shanghai' > /etc/timezone

EXPOSE 8080

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]