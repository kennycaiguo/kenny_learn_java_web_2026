# Tomcat服务器

## 1.web服务器应用服务器应用

### 1.1web server

![image-20260919201324152](./note04-tomcat服务器.assets/image-20260919201324152.png)

![image-20260919195905644](./note04-tomcat服务器.assets/image-20260919195905644.png)

### 1.2 Application Server,包含web server

![image-20260919201344308](./note04-tomcat服务器.assets/image-20260919201344308.png)

![image-20260919201524934](./note04-tomcat服务器.assets/image-20260919201524934.png)

### 1.3 总结

![image-20260919201723118](./note04-tomcat服务器.assets/image-20260919201723118.png)

## 2.tomcat简介

### 2.1开发与维护

![image-20260919201928377](./note04-tomcat服务器.assets/image-20260919201928377.png)

### 2.2 名字和logo

![image-20260919202020599](./note04-tomcat服务器.assets/image-20260919202020599.png)

### 2.3 核心功能

![image-20260919202110807](./note04-tomcat服务器.assets/image-20260919202110807.png)

### 2.4 核心组件

![image-20260919202312140](./note04-tomcat服务器.assets/image-20260919202312140.png)

### 2.5 历史版本，现在企业使用的都是tomcat10以上的版本

![image-20260919203009164](./note04-tomcat服务器.assets/image-20260919203009164.png)

![image-20260919203248529](./note04-tomcat服务器.assets/image-20260919203248529.png)

### 2.6 Tomcat10.1.x下载，网址：https://tomcat.apache.org/download-10.cgi

#### 我们下载 Tomcat10.1.60的64位的zip文件和它的源码

![image-20260919211826694](./note04-tomcat服务器.assets/image-20260919211826694.png)

### 2.7 tomcat的安装，我们把这两个文件解压缩然后粘贴到d:\programs\里面

![image-20260920113508026](./note04-tomcat服务器.assets/image-20260920113508026.png)

### 然后进入bin目录可以使用了

### 2.8 tomcat的目录的用途

#### bin目录

![image-20260920114214985](./note04-tomcat服务器.assets/image-20260920114214985.png)

#### conf目录，可以在里面配置tomcat的一些参数

![image-20260920114325532](./note04-tomcat服务器.assets/image-20260920114325532.png)

#### lib（tomcat的主要功能库文件）

![image-20260920120504293](./note04-tomcat服务器.assets/image-20260920120504293.png)

![image-20260920120905470](./note04-tomcat服务器.assets/image-20260920120905470.png)

#### logs，tomcat保存日志的文件夹

![image-20260920120719760](./note04-tomcat服务器.assets/image-20260920120719760.png)

![image-20260920120943331](./note04-tomcat服务器.assets/image-20260920120943331.png)

#### temp

![image-20260920121028887](./note04-tomcat服务器.assets/image-20260920121028887.png)

#### webapps

![image-20260920121054209](./note04-tomcat服务器.assets/image-20260920121054209.png)

#### work

是专门为jsp准备的。

### 2.9 tomcat的配置

#### 1》配置哪些环境变量

![image-20260920121757956](./note04-tomcat服务器.assets/image-20260920121757956.png)



#### 如果我们需要在任何地方都可以启动tomcat，需要新建一个系统环境变量CATALINA_HOME

![image-20260920125615658](./note04-tomcat服务器.assets/image-20260920125615658.png)

#### 然后我们需要配置一下Path环境变量，添加下面的配置

![image-20260920125732251](./note04-tomcat服务器.assets/image-20260920125732251.png)

#### 其实，还需要配置JAVA_HOME的只不过在安装elippse的jdk的时候，它帮我们设置好了

![image-20260920130556694](./note04-tomcat服务器.assets/image-20260920130556694.png)

##### 然后端口一个cmd窗口，输入startup.bat,就可以启动tomcat

![image-20260920125912012](./note04-tomcat服务器.assets/image-20260920125912012.png)

![image-20260920125932568](./note04-tomcat服务器.assets/image-20260920125932568.png)

##### 可以在浏览器中访问：http://localhost:8080/

![image-20260920130024056](./note04-tomcat服务器.assets/image-20260920130024056.png)

### 3.0 解决tomcat控制台的乱码问题

打开conf里面的logging.properties,把java.util.logging.ConsoleHandler.encoding的值改为GBK

![image-20260920131938031](./note04-tomcat服务器.assets/image-20260920131938031.png)