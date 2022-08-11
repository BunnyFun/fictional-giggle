# 应用持久化管理平台

##  快速开始

`第一步`: 编辑文件:'redis.properties' :

**注意**: redis.server.num 必须设置

```
redis.server.num=1
redis.language=English

#must set a default redis
redis.host.1=127.0.0.1
redis.name.1=127.0.0.1
redis.port.1=6379
redis.password.1=root

redis.host.2=yours
redis.name.2=yours
redis.port.2=yours
redis.password.2=yours
```

`第二步`: 编辑文件:'application.properties' :

```
####Security Manager
manager.username=admin
manager.password=admin
```

`第三步`: 打包项目

run maven command : mvn clean package
you will found war in 'target/memory-admin.war'
move war to '../tomcat/webapps/.' and start tomcat 

`最后`: 浏览应用持久化管理平台

open your brower and visit: http://localhost:8080/memory-admin/

then, enter username and password what you set in file 'application.properties'

##  FAQ

![img-source-from-https://github.com/docker/dockercraft](https://github.com/docker/dockercraft/raw/master/docs/img/contribute.png?raw=true)
