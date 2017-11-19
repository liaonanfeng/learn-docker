FROM hub.c.163.com/wuxukun/maven-aliyun:3-jdk-8

add pom.xml /tmp/build/

add src /tmp/build/src

# 构建应用
run cd /tmp/build && mvn clean package \
# 拷贝编译结果到指定目录
&& mv target/learn-docker.jar /learn-docker.jar \
# 清理编译痕迹
&& cd / && rm -rf /tmp/build

volume /tmp

expose 8010
entrypoint ["java","-jar",/learn-docker.jar""]