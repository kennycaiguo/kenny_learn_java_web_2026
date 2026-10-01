# 课程内容：

- 初始Maven
- Maven概述
  - Maven模型
  - Maven仓库介绍
  - Maven安装与配置
- IDEA集成Maven
- 依赖管理
- 单元测试

## 初始Maven

### 介绍

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NjQyN2I5MjVlOWNlZTI1YWQ2ZjU0YjM4MTlmZDFjOTlfTGlpc0U4ckZQSmZUS3M5a0ZEMHRUTzViU3l5RU9OTGdfVG9rZW46U3NGUGJSZFdKb282YUZ4UkJ3V2NKUWtPbjNYXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

Maven 是一款用于管理和构建Java项目的工具，是Apache旗下的一个开源项目 。

> Apache 软件基金会，成立于1999年7月，是目前世界上最大的最受欢迎的开源软件基金会，也是一个专门为支持开源项目而生的非盈利性组织。
>
> 开源项目：https://www.apache.org/index.html#projects-list

那我们之前在JavaSE阶段，没有使用Maven，依然可以构建Java项目。 我们为什么现在还要学习Maven呢 ? 那接下来，我们就来聊聊Maven的作用。

### Maven的作用

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZmUzNmY5M2MxMjNhYjdlNGQxOTg0NTIzZjUzNDU0YWRfQ3pCY3o0RnUxd1JmWWY0eUNwbUxyaVp0d2w2R1piWXZfVG9rZW46V0pzUmI2dkNsb3lQOXJ4UVk1eGNJVDg4bkZkXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

#### 依赖管理

方便快捷的管理项目依赖的资源(jar包)，避免版本冲突问题。

**1). 使用maven前**

我们项目中要想使用某一个jar包，就需要把这个jar包从官方网站下载下来，然后再导入到项目中。然后在这个项目中，就可以使用这个jar包了。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MjZhMDE4ZDIxNWVhOGJjZjhiMDk0YTIwYTAwYWE0MzlfaHNjTVk4S2VmMzlmT09veGFMWklXRmw3NnhFWVA1Y1pfVG9rZW46RGFhYmJsbHVUbzhxYjB4M2NjQmNObXFybkhoXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

**2). 使用maven后**

当使用maven进行项目依赖(jar包)管理，则很方便的可以解决这个问题。 我们只需要在maven项目的pom.xml文件中，添加一段如下图所示的配置即可实现。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YTJkNGU5MWYwNjEyZWU5ZGRmZWU5ZjViMDI3MDY3YzZfYVJOVFJFUnpacWtRMWd3ck5BNGhlY0N3d3pNOTMyZlhfVG9rZW46TktXVWJ2c29Zb1k5NWR4U1dLQmM5bjNXbmVlXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

在maven项目的配置文件中，加入上面这么一段配置信息之后，maven会自动的根据配置信息的描述，去下载对应的依赖。 然后在项目中，就可以直接使用了。 

#### 项目构建

Maven还提供了标准化的跨平台的自动化构建方式。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NmQ0OGQ2OWJkMjRjYTNmMTljYTNjZGVmN2RkOTI4YzNfUzBVa3hBQ0hHbklkekhpZ05VT1Z6elZjbkExRE9BZW5fVG9rZW46U3h3a2JBcEU3b0JOV214QVNvTGNCY2MzblNoXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

如上图所示我们开发了一套系统，代码需要进行编译、测试、打包、发布等过程，这些操作是所有项目中都需要做的，如果需要反复进行就显得特别麻烦，而Maven提供了一套简单的命令来完成项目构建。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MTZmZjkzOTYzNzdjMTU5M2UyZTFiODYyNGU1MjA4NDhfSTNJTFRiVXBKYXdsR0ZpcWZZZzRIUGFkU1RRQm95QXZfVG9rZW46TlZBRGJMaml3b2dnUEd4VUVSaWNmbHNIblFoXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

通过Maven中的命令，就可以很方便的完成项目的编译(compile)、测试(test)、打包(package)、发布(deploy) 等操作。 

而且这些操作都是跨平台的，也就是说无论你是Windows系统，还是Linux系统，还是Mac系统，这些命令都是支持的。

#### 统一项目结构

Maven 还提供了标准、统一的项目结构 。

**1). 未使用Maven**

由于java的开发工具呢，有很多，除了大家熟悉的IDEA以外，还有像早期的Eclipse、MyEclipse。而不同的开发工具，创建出来的java项目的目录结构是存在差异的，那这就会出现一个问题。

Eclipse创建的java项目，并不能直接导入IDEA中。 IDEA创建的java项目，也没有办法直接导入到Eclipse中。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NWQyN2E3NmMxOTQ4ZGMwMzM2MjEyMzlkNDQwNDM3MTdfNHlEd2RLV0NrengxOVBQS3ZQM3pMcjdCRnNOZ3NGdFlfVG9rZW46WEZYdGJaVEFSb1VKY1V4bm1VeGNzSWVhbmNkXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

**2). 使用Maven**

而如果我们使用了Maven这一款项目构建工具，它给我们提供了一套标准的java项目目录。如下所示：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NjJjYjUyODViYzYyZTBmZDIwMzlkMGNlNjQzMzM0ZDJfTWNuNUFOVkJxN0VramcwdU5mWEltdk5YUXk1MXdpSFdfVG9rZW46VnVTaGJOb3VCb2h3TE14dlhsemNtczREbmxNXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

也就意味着，无论我们使用的是什么开发工具，只要是基于maven构建的java项目，最终的目录结构都是相同的，如图所示。 那这样呢，我们使用Eclipse、MyEclipse、IDEA创建的maven项目，就可以在各个开发工具直接直接导入使用了，更加方便、快捷。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NDljOTMzODIyYTg3NzIwNDJmNmU4Nzg3NjA3NzI3ZTFfbkFhRm5xTWFaZWNPVjZXWnpUeVJhZU42bzNZYWEzaGNfVG9rZW46RjJ6cGJnSVJCb1UyNGh4ZU02ZWNlelkybnpjXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

而在上面的maven项目的目录结构中，main目录下存放的是项目的源代码，test目录下存放的是项目的测试代码。 而无论是在main还是在test下，都有两个目录，一个是java，用来存放源代码文件；另一个是resources，用来存放配置文件。

最后呢，一句话总结一下什么是Maven。 **Maven就是一款管理和构建java项目的工具。**

Maven的内容，我们进行了分层的设计和讲解，分为两个部分：Maven核心和Maven进阶。 那今天，我们先来讲解Maven核心部分的内容，在Web开发的最后，我们再来讲解Maven进阶部分的内容。

## Maven概述

### Maven介绍

Apache Maven是一个项目管理和构建工具，它基于项目对象模型(Project Object Model , 简称: POM)的概念，通过一小段描述信息来管理项目的构建、报告和文档。

官网：https://maven.apache.org/

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MTE0MGUyZTQ4Mzc3ZWVjODFkMTQwMjI1NzIxYjFhYjlfSXZpNmZNU3pld2tBdVlXTVM3M0ltZmljd2ZkTEZwc3dfVG9rZW46U285RWJKdzJyb2F2cWZ4Y1NZUmNhVXdqbnNlXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

Maven的作用： 

1. 方便的依赖管理
2. 统一的项目结构
3. 标准的项目构建流程

### Maven模型

- 项目对象模型 (Project Object Model)
- 依赖管理模型(Dependency)
- 构建生命周期/阶段(Build lifecycle & phases)

1). 构建生命周期/阶段(Build lifecycle & phases)

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZmQ1MWM1NzA1N2I1ODEzYjlmZGEyYzk5ZTJhZWU4OWRfRzhwbXhxTXBteGpYYUhaWlRpMzNQdzVMVzZKOHliTmJfVG9rZW46Tld1dWJvcWFHb1RmMDV4ekFWSGN4WEd2bjBlXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

以上图中紫色框起来的部分，就是用来完成标准化构建流程 。当我们需要编译，Maven提供了一个编译插件供我们使用；当我们需要打包，Maven就提供了一个打包插件供我们使用等。 

2). 项目对象模型 (Project Object Model)

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YTUwNjRmNjZlN2M3ODVmYmRiNDI4OGVlMzU0ZGJhMzNfaHdHdXI0ZGk5QVJBT1dGMk1nR1VHTTl5UjRZS2ExUlFfVG9rZW46QUdtYWJndGdrb0J4WDl4clM2bWNESjV3bmxjXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

以上图中紫色框起来的部分属于项目对象模型，就是将我们自己的项目抽象成一个对象模型，有自己专属的坐标，如下图所示是一个Maven项目：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=M2QyZDkzMWYzNDYxZGVhMjc1YTA4N2Y0ZmM0NjhmMmNfRmVBT0M5S1JGbUhCNHpXMEpQcDFmZDVycm8yYWEzTXpfVG9rZW46Q3FxVmI5V1lDb2MyRmR4blVicmNOR2tubm9jXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

> 坐标，就是资源(jar包)的唯一标识，通过坐标可以定位到所需资源(jar包)位置。
>
> 坐标的组成部分：
>
> - groupId: 组织名
> - arfitactId: 模块名
> - Version: 版本号

3). 依赖管理模型(Dependency)

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NzA0YjA3ZjIxOWM2ZDkwZjE2OWYwMzBjYzljOGQ5YzJfVm1YT3BSclpmVmZLekNHNkxNYXRmNUdwczZ1bmdPUU5fVG9rZW46REVQTmJ5NzYwbzFwNW54Z3lWY2NwSjVTbldMXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

以上图中紫色框起来的部分属于依赖管理模型，是使用坐标来描述当前项目依赖哪些第三方jar包。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MzcyODNjNWJlZGM3NmU1MWRjMzg5YzE3MDg1NGFhNDBfUHJIME9uRmNaWFNGOElIUzBicnZlcVU1SUlKZXQ1SVZfVG9rZW46VnlNSGIwcU1abzVqYTl4ZFFWWWNYSklmbmtaXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

之前我们项目中需要jar包时，直接就把jar包复制到项目下的lib目录，而现在我们只需要在pom.xml中配置依赖的配置文件即可。 而这个依赖对应的jar包其实就在我们本地电脑上的maven仓库中。 

如下图，就是老师本地的maven仓库中的jar文件：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NGQ3MTE4YmE4MjVjZDQ0MWI5Yzc5YTQyZjQwODY4NTFfT3ZxM0t2Qkt1VE1wRnJ6VjEwSVNzUjU5NkJjdDBOUmtfVG9rZW46Qll0Q2JnZmlQb0h0b094bXFXMmM5YnlybmhoXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

### Maven仓库

仓库：用于存储资源，管理各种jar包

> **仓库的本质**就是一个目录(文件夹)，这个目录被用来存储开发中所有依赖(就是jar包)和插件

Maven仓库分为：

- 本地仓库：自己计算机上的一个目录(用来存储jar包)
- 中央仓库：由Maven团队维护的全球唯一的。仓库地址：https://repo1.maven.org/maven2/
- 远程仓库(私服)：一般由公司团队搭建的私有仓库

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NTBkYWVjMmE5NmI1Y2I3MThlOWU2MTEyM2U1NTdlMzVfc0tsbTRBVWg0R2VBSzA1dlVsWGNuNUR0V1dEU3dRbXFfVG9rZW46VTZMbWJFN1I5b3ZOT214dElIbGNFQTFCbkhkXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

当项目中使用坐标引入对应依赖jar包后，

- 首先会查找本地仓库中是否有对应的jar包
  - 如果有，则在项目直接引用
  - 如果没有，则去中央仓库中下载对应的jar包到本地仓库
- 如果还可以搭建远程仓库(私服)，将来jar包的查找顺序则变为： 本地仓库 --> 远程仓库--> 中央仓库

### Maven安装

认识了Maven后，我们就要开始使用Maven了，那么首先我们要进行Maven的下载与安装。

#### 下载

- 下载地址：https://maven.apache.org/download.cgi
- 在提供的资料中，已经提供了下载好的安装包。如下： 

暂时无法在飞书文档外展示此内容

#### 安装步骤

Maven安装配置步骤：

1. 解压安装
2. 配置仓库
3. 配置阿里云私服
4. 配置Maven环境变量

**1). 解压 apache-maven-3.9.4-bin.zip（解压即安装）**

建议解压到没有中文、特殊字符的路径下。如课程中解压到 `E:\develop` 下。

解压缩后的目录结构如下：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YmYzNGUxZTJiNTY0YTQxY2Y3ZThjOTY5NDg2ZmVjZjRfRk1MZDBLTkEyTWlkdW9zVW5ZWlc2dTVaa3NkenZoYXVfVG9rZW46R2M3d2JpbGU4b25TVXh4aG5pemNIQ0d2bnpmXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

- bin目录 ： 存放的是可执行命令。（mvn 命令重点关注）
- conf目录 ：存放Maven的配置文件。（settings.xml配置文件后期需要修改）
- lib目录 ：存放Maven依赖的jar包。（Maven也是使用java开发的，所以它也依赖其他的jar包）

**2). 配置本地仓库**

1. 在自己计算机上新一个目录（本地仓库，用来存储jar包）

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZmYwZjlhNDg1ZWJmZTUxMGRlYmNlNjI5ZWUyMDg4ZGNfeFQzU1RFc3hIcDlUS21adkFqeVJWeU5DT1dsM3Y0cGZfVG9rZW46WGVUb2JyaDRXb0hxSkt4eXdsQ2NqUnBjbkRiXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

 

1. 进入到conf目录下修改`settings.xml`配置文件 
   1. 使用超级记事本软件，打开settings.xml文件，定位到53行
   2. 复制`<localRepository>`标签，粘贴到注释的外面（55行）
   3. 复制之前新建的用来存储jar包的路径，替换掉`<localRepository>`标签体内容 

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=OTFiNWY4NWQzMTJhOGRkMDI5YzE0N2ZkNTRjNDE1ZmJfNjBGNHVRM1dsWTQ3MkNVMVNQN3ZRU0ZubE9CaTFlbUNfVG9rZW46TnMyc2JNajdOb0syOVh4cktiT2Nmc1cxblZmXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

**3). 配置阿里云私服**

由于中央仓库在国外，所以下载jar包速度可能比较慢，而阿里公司提供了一个远程仓库，里面基本也都有开源项目的jar包。

进入到conf目录下修改settings.xml配置文件：

1. 使用超级记事本软件，打开settings.xml文件，定位到160行左右
2. 在`<mirrors>`标签下为其添加子标签`<mirror>`，内容如下：

```XML
<mirror>
    <id>alimaven</id>
    <name>aliyun maven</name>
    <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
    <mirrorOf>central</mirrorOf>
</mirror>
```

注意配置的位置，在`<mirrors> ... </mirrors>`中间添加配置。如下图所示：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=OGM0ZjU5YWQzYjhkYjQ1OGJjNDY5NDRlNDllNTkxNjlfQW1XQ2E5VGZZclo5YnBTRXFuV2FWZ0lDWHYyYWg4WEtfVG9rZW46RGdRNWJNNU5rb2RsVkJ4dE5DSGNmVVl3bkRRXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

**4). 配置环境变量**

Maven环境变量的配置类似于JDK环境变量配置一样

1. 在系统变量处新建一个变量MAVEN_HOME。 MAVEN_HOME环境变量的值，设置为maven的解压安装目录

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZWZiY2UyYTNmNGYyZTZmNjA0Nzk2NmI5Yjc4Mzk4NzJfV0pZaHJDOXpDaGdkQkV3b0I4dlhseEZLRGJXQ3pZT0tfVG9rZW46RmdOMGJ6NVl6b1hDRGJ4OWp4bmNPNDJ2bkNkXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

1. 在Path中进行配置。 PATH环境变量的值，设置为：%MAVEN_HOME%\bin

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NzNlNzY0ZDI1ZWM3NzQ4MDEyZDU2ZTc4Y2JjMGUwYTBfY2VibHJnSlQ1eEIwelF3MVJSMWRpaDRFaVFXNUhoRXRfVG9rZW46S0lKbWJSdWd4b1pMRUx4NmdkSWNRU1ZqbmdmXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

1. 打开DOS命令提示符进行验证，出现如图所示表示安装成功 。

命令为：**`mvn -v`**

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MTM4OGI0M2NjZGUxMmEwNmMyYzg4NDA1YjcxOGEwYzlfeUxvcTNmYVVTdElHeXp5S21QMkJYVFpISUZxQTd3MXlfVG9rZW46SE1rM2JPQVJwb1I2bVR4Q3RsQWNNNGJZbnVkXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

**5). 配置关联的JDK版本(可选)**

进入到conf目录下修改settings.xml配置文件，在 `<profiles> </profiles>`中增加如下配置:

```SQL
<profile>
        <id>jdk-17</id>
        <activation>
                <activeByDefault>true</activeByDefault>
                <jdk>17</jdk>
        </activation>
        <properties>
                <maven.compiler.source>17</maven.compiler.source>
                <maven.compiler.target>17</maven.compiler.target>
                <maven.compiler.compilerVersion>17</maven.compiler.compilerVersion>
        </properties>
</profile>
```

## IDEA集成Maven

我们要想在IDEA中使用Maven进行项目构建，就需要在IDEA中集成Maven，那么就需要在IDEA中配置与maven的关联。

### 创建Maven项目

#### 全局设置

1. 进入IDEA的欢迎页面
   1.  选择 IDEA中 File  =>  `close project` => `Customize` => `All settings`

   2. ![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZWYxMDcyOTY0Y2ExNThhOTNiYzViNzQ3ZGUwNGQ2ZTdfUkY3RFphTTN0Skdmbm1reEdGVEJGTTluOG96b0tnaTBfVG9rZW46TFEwTmJpaFNvb3BReG94YklsdGNWRHVhbjFnXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MDUxYWMyZWVkZDE2MWJkNDk5MzI0NGE0NjYyNGRiMWRfMmVONkY4OVlzWU5zNVFGTWVGUzhGTkhLSkY3T1ZBNnFfVG9rZW46RW9XY2JZZ0gwbzVGNzB4ZGNOWWNZajB2bnBoXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

1. 打开 All settings , 选择 `Build,Execution,Deployment`  =>  `Build Tools`  =>  `Maven`

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NTkyYjIxNWQ4YzZjMjEyOGFlMzU5NjU0MjA5MjVmZDJfdVlITmRkSElYS2xwa2MzalVVc21rd1lsZUV3WEtHQ1hfVG9rZW46UG5tYmIzeTdhb1hwSGZ4bDhaemM3aUhIbndRXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

1. 配置工程的编译版本为17

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZGYwNWIyNDgxODNhZjNjYzNiMzU0Zjc4MTZlMTU4MWRfTW5LZ3lBbWt6bkNXQzBoYk1Wa0RIQ3BVWDJWZ0lvUmdfVG9rZW46SnUwNWJXazNzb2o5bmp4ak90dWNsdk1qbm9oXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

这里所设置的maven的环境信息，并未指定任何一个project，此时设置的信息就属于全局配置信息。 以后，我们再创建project，默认就是使用我们全局配置的信息。

#### 创建项目

1. 创建一个空项目，命名为 web-project01

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NTI3ZWM1Nzg4YzMxMzYzZTRhY2NhY2RhYTQzMjZiNTlfWjZEM1FiVWJMbnp4ZXNWU1I4VllVc3JVdzhYZWluUDJfVG9rZW46SXBrWmJDVXQwb2pYRWh4MWhjRmNsOEJybnllXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

2.创建好项目之后，进入项目中，要设置JDK的版本号。选择小齿轮，选择 **`Project Structure`**

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=OTAyY2VlMGZlY2VhYjA3Mzk4ZTY3MzgwOTI5YTkzYmFfQU0zUk02ek9ncG5PMzFNWmc3dDBaaDBlMHh4cjFLcHNfVG9rZW46RFljbGJLbHRvb3dObFR4ZUI5WmNNSEl2bmRiXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

3.创建模块，选择Java语言，选择Maven。 填写模块的基本信息

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MmMyZTg1M2MyMDVlZWUyZWJlMDc5ZjFhNzgyYTE4MWZfS3BtNDFuV2pNMmt5NWVpTzhNb001Z0NlRmM2bnBwaEFfVG9rZW46VW40U2J1QTZhb0EyMk54dW9MNmNIc1BobmFjXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NDRmZDQ0Y2VhMTM4ODA5NmZkNGQ1ZGU0Y2NiNzY4M2FfNVRoeEtvYWU0d2pvUXNTaGdxRUp2ODRGUU85M2dWTWFfVG9rZW46WEQ4dGJVWFB6b1hEYnl4eWp4TWNMTGVSbkY2XzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

4.在maven项目中，创建HelloWorld类，并运行

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ODJjNGI1YjhiMjUyMzU1NjRkMzIyZmQxZWI3MmJlZTVfR1BXaWVsZkxNRmFxbEJySlRKenplVTYwWTRxcVpGU2NfVG9rZW46QW5PeWJncXFvb1NMRFB4QXhqZWNmUGFpbkZ3XzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

**Maven项目的目录结构:**

**maven-project01**

​        |---  src  (源代码目录和测试代码目录)

​               |---  main (源代码目录)

​                        |--- java (源代码java文件目录)

​                        |--- resources (源代码配置文件目录)

​              |---  test (测试代码目录)

​                        |--- java (测试代码java目录)

​                        |--- resources (测试代码配置文件目录)

​        |--- target (编译、打包生成文件存放目录)

#### pom文件详解

POM (Project Object Model) ：指的是项目对象模型，用来描述当前的maven项目。

- 使用pom.xml文件来描述当前项目。 pom.xml文件如下：

```XML
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <!-- POM模型版本 -->
    <modelVersion>4.0.0</modelVersion>

    <!-- 当前项目坐标 -->
    <groupId>com.itheima</groupId>
    <artifactId>maven-project01</artifactId>
    <version>1.0-SNAPSHOT</version>
    
    <!-- 项目的JDK版本及编码 -->
    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

</project>
```

**pom文件详解：**

- `<project>`` `：pom文件的根标签，表示当前maven项目
- `<modelVersion>`：声明项目描述遵循哪一个POM模型版本
  - 虽然模型本身的版本很少改变，但它仍然是必不可少的。目前POM模型版本是4.0.0
- 坐标 ：
  - `<groupId>` `<artifactId>` `<version>`
  - 定位项目在本地仓库中的位置，由以上三个标签组成一个坐标
- `<maven.compiler.source>` ：编译JDK的版本
- `<maven.compiler.target>` ：运行JDK的版本
- `<project.build.sourceEncoding>` : 设置项目的字符集

### Maven坐标

什么是坐标？

- Maven中的坐标是**资源的唯一标识** , 通过该坐标可以唯一定位资源位置
- 使用坐标来定义项目或引入项目中需要的依赖

Maven坐标主要组成：

- groupId：定义当前Maven项目隶属组织名称（通常是域名反写，例如：com.itheima）
- artifactId：定义当前Maven项目名称（通常是模块名称，例如 order-service、goods-service）
- version：定义当前项目版本号
  - SNAPSHOT: 功能不稳定、尚处于开发中的版本，即快照版本
  - RELEASE: 功能趋于稳定、当前更新停止，可以用于发行的版本

如下图就是使用坐标表示一个项目：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MTViNDhmYzkzMGUyZjY4ZGY4YzY5OGJlMWZjMDhiOTRfWVM1TFRCNWc3SXcxeXdZUmtzb1NiZnZva2duck9IcUtfVG9rZW46SUtqVWJ3QzZ6b1NKVmR4SWJZQ2NpVkdnbjFiXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

> **注意：**
>
> - 上面所说的资源可以是插件、依赖、当前项目。
> - 我们的项目如果被其他的项目依赖时，也是需要坐标来引入的。

### 导入Maven项目

在IDEA中导入Maven项目，有两种方式。

- 方式一：`File` -> `Project Structure` -> `Modules` -> `Import Module` -> `选择maven项目的pom.xml`。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZTQyMzRkOGFhZmZiZTFlNDE2MmEwMDc2ZTNkZjkyMjRfTkdHMUFPUFkwZnQ5NTlCZWNpTXRma0hFNTlNU0VnZVFfVG9rZW46RzNZbWIzdTh5b2pPVGd4VHE4cmNKMWxRbkFoXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

- 方式二：`Maven面板` -> `+（Add Maven Projects）` -> `选择maven项目的pom.xml`。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YjExNzM0OTI1YjVhN2I5YTRlMDhiNDg3YTg1ODk0ZWJfeW1iWlMwWWlCbmpzTGRHMUUyNHhFWjBiS0tNT2hBUVdfVG9rZW46UVFjcGJwWm8wb0VVc2V4U01UdWNDem5sbnBjXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

## 依赖管理

### 依赖配置

#### 基本配置

依赖：指当前项目运行所需要的jar包。一个项目中可以引入多个依赖：

例如：在当前工程中，我们需要用到logback来记录日志，此时就可以在maven工程的pom.xml文件中，引入logback的依赖。具体步骤如下：

1. 在pom.xml中编写`<dependencies>`标签
2. 在`<dependencies>`标签中使用`<dependency>`引入坐标
3. 定义坐标的 `groupId`、`artifactId`、`version`

```XML
<dependencies>
    <!-- 依赖 : spring-context -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>6.1.4</version>
    </dependency>
</dependencies>
```

4.点击刷新按钮，引入最新加入的坐标

刷新依赖：保证每一次引入新的依赖，或者修改现有的依赖配置，都可以加入最新的坐标

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=OWM2MTJjYWM1OGMxMTEyMjNhNTNkNDQwNmJkODM1OGRfdnhmZ0RxMXBFdDc2UklZWENFQUVJM3JSMlhxUGFhTFhfVG9rZW46UkhFbmJqS3hWb0U0Skd4RFRVbWN0bU9DbkhkXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

**注意事项：**

1. 如果引入的依赖，在本地仓库中不存在，将会连接远程仓库 / 中央仓库，然后下载依赖（这个过程会比较耗时，耐心等待）
2. 如果不知道依赖的坐标信息，可以到mvn的中央仓库（https://mvnrepository.com/）中搜索

#### **查找依赖**

1. 利用中央仓库搜索的依赖坐标，以常见的logback-classic为例。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZGY3NjA2ODM5N2YxMzEzNTQ0M2VmOTZmYTUzNjI5ZDJfdXB2a21LNUJmb21nemI5NERyWktMczdmeUJjVndTSTRfVG9rZW46QjFOYmJBRnlsbzJZTVV4NGp4Z2NicFc0bjNIXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

1. 利用IDEA工具搜索依赖，以常见的logback-classic为例。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=Yjc2YzcxMTE4NmUxYThiMDhlYmQ2ZTFjODdiMDYxZTlfcWNWQU5HYnR6QzhEWnk2RUYxS2F2alg5T3FJSFJhRU1fVG9rZW46Tk5sWmJzQnpNb1RyS0J4TnBBdmN5M0pqbnVkXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

2.熟练上手maven后，快速导入依赖，以常见的logback-classic为例。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MDU3MGI0ODYyZWQxMjZiZTFiNDA1M2VjMTIxNDU4MGJfaHhySzFuRmpabmJHWFVwaHNybEJ1UldjenYzMHR1cDJfVG9rZW46Q09UQ2JLbnlrbzlkSVZ4cGx6U2NiQjY0bnZkXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

#### 依赖传递

我们上面在pom.xml中配置了一项依赖，就是spring-context，但是我们通过右侧的maven面板可以看到，其实引入进来的依赖，并不是这一项，有非常多的依赖，都引入进来了。我们可以看到如下图所示：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MzI3N2UxOTA5ZGZhZGYyNGM4MWNhYWJiZDk3YjdmZGRfYnBCbm9OVWYxY2xPQTl0a2VVeTcySUl6a0IwMDdIdlVfVG9rZW46VkRLSmJMQm8zb1ZlMTh4V2Q1R2M2ZElDbmFkXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

为什么会出现这样的现象呢? 那这里呢，就涉及到maven中非常重要的一个特性，那就是Maven中的**依赖传递**。

所谓maven的依赖传递，指的就是如果在maven项目中，A 依赖了B，B依赖了C，C依赖了D，那么在A项目中，也会有C、D依赖，因为依赖会传递。

那如果，传递下来的依赖，在项目开发中，我们确实不需要，此时，我们可以通过Maven中的排除依赖功能，来将这个依赖排除掉。

#### 排除依赖

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZDRhMzA4NzExZGUwN2Q4NzNhNGM1MDVkOTcwNGZiZmJfSWVYZWhxZWxSaW1PWUxkMG1vWlZTTEQzb3ZQSUNyaUpfVG9rZW46UUduMWJzOUMzb0lneEJ4TG5kVGNobnFibmlkXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

-  排除依赖：指主动断开依赖的资源，被排除的资源无需指定版本。
- 配置形式如下：

```XML
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>6.1.4</version>

    <!--排除依赖, 主动断开依赖的资源-->
    <exclusions>
        <exclusion>
            <groupId>io.micrometer</groupId>
            <artifactId>micrometer-observation</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

**依赖排除示例：**

1. 默认通过maven的依赖传递，传递下来了 `micrometer-observation` 的依赖。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MWEzNWIzN2U4MDAxNWQzMGIxMDc0OTAwYWY0OGViM2ZfbWEwdU1Jdm53RmtFamdJdTFvZlpja3FOU05XdjFycVlfVG9rZW46UEhJZmJFYUZEb0JXWGx4aHdOcWNsNGVhbnVmXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

2.加入排除依赖的配置之后，该依赖就被排除掉了。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YjBhN2Q2MTVkY2MxZjNmMzAwZDU2MTVmZDEyNDljZTNfQm9GcEFUWTlxOHJLd0tOc2JxcnhPMHFzOTI3ZVphR2NfVG9rZW46WFJVbGJsMUNUb1AxdVN4UkppdGN1NVVvbkxmXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

### 生命周期

#### 介绍

Maven的生命周期就是为了对所有的构建过程进行抽象和统一。 描述了一次项目构建，经历哪些阶段。

在Maven出现之前，项目构建的生命周期就已经存在，软件开发人员每天都在对项目进行清理，编译，测试及部署。虽然大家都在不停地做构建工作，但公司和公司间、项目和项目间，往往使用不同的方式做类似的工作。

Maven从大量项目和构建工具中学习和反思，然后总结了一套高度完美的，易扩展的项目构建生命周期。这个生命周期包含了项目的清理，初始化，编译，测试，打包，集成测试，验证，部署和站点生成等几乎所有构建步骤。

Maven对项目构建的生命周期划分为3套（相互独立）：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MGE5MGFkZjVjZDIyN2E5YmE4YzIwZjQ4NDNmODMyNDdfVGUxTFRvVlBtd0I0c3FHQ2xHbndKS0Fzekk5WmllU21fVG9rZW46RGVDYmJzS04xb3d5bUZ4d0FNN2NyUE02bkVlXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

- clean：清理工作。
- default：核心工作。如：编译、测试、打包、安装、部署等。
- site：生成报告、发布站点等。

三套生命周期又包含哪些具体的阶段呢, 我们来看下面这幅图:

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YmMxNzY0NTBlYWZiZWY0Zjk3ZTViZjJjMWVlOGIzYTdfcXR6b013Z0k3b05XcXh4MXE1V0haS2hzWmNGMmJMSkZfVG9rZW46QTlHMmIySlZKb3BLN1B4M3N2VWNSaklZbkplXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

每套生命周期包含一些阶段（phase），阶段是有顺序的，后面的阶段依赖于前面的阶段。

我们看到这三套生命周期，里面有很多很多的阶段，这么多生命周期阶段，其实我们常用的并不多，主要关注以下几个：

- clean：移除上一次构建生成的文件
- compile：编译项目源代码
- test：使用合适的单元测试框架运行测试(junit)
- package：将编译后的文件打包，如：jar、war等
- install：安装项目到本地仓库

Maven的生命周期是抽象的，这意味着生命周期本身不做任何实际工作。**在Maven的设计中，实际任务（如源代码编译）都交由插件来完成。**

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MjFiNWM2MGFhMDRiMDYwYTdlYTQ3NTk3YTUwZjU1NzdfRzlWblpxUXNRdUJ0blJUZjhYcHNPa1ltZDI4Snd3UHdfVG9rZW46WHgwOWJaRG16bzBrSm54Q0pVVmNySll1bk9lXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

IDEA工具为了方便程序员使用maven生命周期，在右侧的maven工具栏中，已给出快速访问通道。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MTI1ZjgyNjNmNmUxYTkxM2I4ZWMyNjdlNzJmOGYwYzFfUHdmRThzTmtURml5cXJvUXd2THNPUHppSUtXelo3ODRfVG9rZW46VHlBRWI2Z2lEb3h4dXl4eFBkUmNDNHVYblNxXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

- 生命周期的顺序是：`clean` --> `validate` --> `compile` --> `test` --> `package` --> `verify` --> `install` --> `site` --> `deploy`
- 我们需要关注的就是：`clean` -->  `compile` --> `test` --> `package`  --> `install`

**说明：**在同一套生命周期中，我们在执行后面的生命周期时，前面的生命周期都会执行。

**思考：**当运行package生命周期时，clean、compile生命周期会不会运行？

​    clean不会运行，compile会运行。  因为compile与package属于同一套生命周期，而clean与package不属于同一套生命周期。

#### 执行

在日常开发中，当我们要执行指定的生命周期时，有两种执行方式：

1. 在idea工具右侧的maven工具栏中，选择对应的生命周期，双击执行
2. 在DOS命令行中，通过maven命令执行

**方式一：在idea中执行生命周期**

- 选择对应的生命周期，双击执行

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NWMxNWQxZGZmMjdkNDhkOWZkZDI1OTY1OGRlMTYwYzRfbWRjU3VTRmJWQjJMRUNBSWtDS3RTNEJVVlg2WkVRTzFfVG9rZW46Q3lzMGJSMVJrb1p1dDJ4OVh4SGN6djBTbkFkXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

其他的生命周期都是类似的道理，双击运行即可。

**方式二：在命令行中执行生命周期**

1. 打开maven项目对应的磁盘目录

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=Zjc5Nzc1Zjg5ZTlkNDI5MDRlZjdlNjhjNGFiMWE1YjJfeEZ5RjFXVjlWZGc2TWxTa1FtRmtLcDVYV2hKdm9UQzlfVG9rZW46QVpzR2JCS2tobzdlY1R4SEJDVWNoQkVRblFiXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

1. 在当前目录下打开CMD

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NTI4ZDkxZjM1MTRlMmE3NGQ0MjJiODVhMzZmMmIwNzdfdmJ6WGVXOXdhRUIzTXIyUlIzNHhpVkhGTTNDUkdIM0xfVG9rZW46V1RKU2JObEJjb2Y3VXh4Z1E4ZGNsS3Q3bm1jXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

类似的道理，我们也可以在命令执行：

- mvn compile
- mvn test
- mvn package
- mvn install    

## 单元测试

### 介绍

**测试：**是一种用来促进鉴定软件的正确性、完整性、安全性和质量的过程。

**阶段划分：**单元测试、集成测试、系统测试、验收测试。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ODU5YTdlZTBhMTQxZDhkZGViMmVlYjNkNDYyM2ZjMTVfNktoUWxETENaMUdKTWVCVU1iY1VFcDA1c01pM2pyRVdfVG9rZW46TUJCZWJYT0R6b3VCcUh4dmlXWWNUUnVIbjdTXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

1). 单元测试

- 介绍：对软件的基本组成单位进行测试，最小测试单位。
- 目的：检验软件基本组成单位的正确性。
- 测试人员：开发人员

2). 集成测试

- 介绍：将已分别通过测试的单元，按设计要求组合成系统或子系统，再进行的测试。
- 目的：检查单元之间的协作是否正确。
- 测试人员：开发人员

3). 系统测试

- 介绍：对已经集成好的软件系统进行彻底的测试。
- 目的：验证软件系统的正确性、性能是否满足指定的要求。
- 测试人员：测试人员

4). 验收测试

- 介绍：交付测试，是针对用户需求、业务流程进行的正式的测试。
- 目的：验证软件系统是否满足验收标准。
- 测试人员：客户/需求方

**测试方法：**白盒测试、黑盒测试 及 灰盒测试。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NGM2NWY0OThiN2Y5MjFjMzYxODVkZmNkYzhiMDM1MzhfaGlVZTJValJ6M2tOeWhNZFR6dThTRUlQWW5VS0ZoTmdfVG9rZW46UTVWN2JpcDhwb2ptR1V4TVo0ZmNLNGd0bmNmXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

1). 白盒测试

清楚软件内部结构、代码逻辑。

用于验证代码、逻辑正确性。

2). 黑盒测试

不清楚软件内部结构、代码逻辑。

用于验证软件的功能、兼容性、验收测试等方面。

3). 灰盒测试

结合了白盒测试和黑盒测试的特点，既关注软件的内部结构又考虑外部表现（功能）。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NGZmMDBkNjZhZDMyZWQ3YWQzNjIyZThlNTJmY2QyNWRfbVFsdTdwU2dQTDJnN0NPY1hsTkwwbXdFT25EN0ZuUktfVG9rZW46RjRVWWJnNU9ub0xjbjR4cTFlZ2Mwa0hKbmVVXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

### Junit入门

#### 单元测试

- 单元测试：就是针对最小的功能单元(方法)，编写测试代码对其正确性进行测试。
- JUnit：最流行的Java测试框架之一，提供了一些功能，方便程序进行单元测试（第三方公司提供）。

在之前的课程中，我们进行程序的测试 ，都是main方法中进行测试 。如下图所示：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZDhhZTM4Yzg3NWFjZDNmNWI2NzEyY2Q3NmJhZGIyNzFfeW9qaEwxUm5rUUZCcU52a1JSazZOTUdyd2cxWEgxT25fVG9rZW46RUVyNWJIUHE5b1RmeHp4bnI5SmNSWEtpblNiXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

通过**main方法**是可以进行测试的，可以测试程序是否正常运行。但是main方法进行测试时，会存在如下问题：

1. 测试代码与源代码未分开，难维护。
2. 一个方法测试失败，影响后面方法。
3. 无法自动化测试，得到测试报告。

而如果我们使用了**JUnit单元测试**框架进行测试，将会有以下优势：

1. 测试代码与源代码分开，便于维护。
2. 可根据需要进行自动化测试。
3. 可自动分析测试结果，产出测试报告。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=Y2I5MDM1OThhMWZkZmM4OTU5MWEzZTdiODQ1ODI0NDJfYTZnb1dLWXZHZHhBNEtERjNScHJTMmhCVmtLdnhPa3VfVG9rZW46WkhVRmJSbFkwb0lQcGZ4Z1ZuTGNIa2dpbm9nXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

#### 入门程序

需求：使用JUnit，对UserService中的业务方法进行单元测试，测试其正确性。

1. 在`pom.xml`中，引入JUnit的依赖。

```XML
<!--Junit单元测试依赖-->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.9.1</version>
    <scope>test</scope>
</dependency>
```

1. 在test/java目录下，创建测试类，并编写对应的测试方法，并在方法上声明@Test注解。

```Java
@Test
public void testGetAge(){
    Integer age = new UserService().getAge("110002200505091218");
    System.out.println(age);
}
```

1. 运行单元测试 (测试通过：绿色；测试失败：红色)。

- 测试通过显示绿色

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MjA1NDUyYmZjZjEyYmYxNjlkZjk4ZWI2ODU4NWUzMjlfZ0VUUENhZW9TZ2dLcTBZOTlrSm5ZWWhNUkJ4d0RHT09fVG9rZW46SktqNGIzMHJZb240MjZ4cDNtS2NwSW1XbmllXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

- 测试失败显示红色

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MzBjMTZiMTIwODg5MzdlNGNmNDU0M2MzYzIyNjNkMDNfSDhBN0ozaHdPZ3cwQmE3cm11UjlUWjFJWWgxdzFRSDFfVG9rZW46TmUxZ2J6RTE2b2hGSlF4YTRPNmNjV2tkbkdjXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

​                        

 **注意：**

- 测试类的命名规范为：XxxxTest
- 测试方法的命名规定为：public void xxx(){...}

### 断言

JUnit提供了一些辅助方法，用来帮我们确定被测试的方法是否按照预期的效果正常工作，这种方式称为**断言**。

| 断言方法                                              | 描述                                       |
| ----------------------------------------------------- | ------------------------------------------ |
| assertEquals(Object exp, Object act, String msg)      | 检查两个值是否相等，不相等就报错。         |
| assertNotEquals(Object unexp, Object act, String msg) | 检查两个值是否不相等，相等就报错。         |
| assertNull(Object act, String msg)                    | 检查对象是否为null，不为null，就报错。     |
| assertNotNull(Object act, String msg)                 | 检查对象是否不为null，为null，就报错。     |
| assertTrue(boolean condition, String msg)             | 检查条件是否为true，不为true，就报错。     |
| assertFalse(boolean condition, String msg)            | 检查条件是否为false，不为false，就报错。   |
| assertSame(Object exp, Object act, String msg)        | 检查两个对象引用是否相等，不相等，就报错。 |

示例演示：

```Java
package com.itheima;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class UserServiceTest {
    
    @Test
    public void testGetAge2(){
        Integer age = new UserService().getAge("110002200505091218");
        Assertions.assertNotEquals(18, age, "两个值相等");
//        String s1 = new String("Hello");
//        String s2 = "Hello";
//        Assertions.assertSame(s1, s2, "不是同一个对象引用");
    }

    @Test
    public void testGetGender2(){
        String gender = new UserService().getGender("612429198904201611");
        Assertions.assertEquals("男", gender);
    }
}
```

测试结果输出：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MGVmOGUyMmRjM2FlZWY2OGUxOTY5N2NmZTE5ZTNhNmVfM2cwRk1lNXQ2ckVLSHJRaDM5aEZ0aFgxS3JiRzRHTm1fVG9rZW46Q0FIeWJLY0xyb0NhN3Z4RXQ4dmNKdHpKblJoXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

### 常见注解

在JUnit中还提供了一些注解，还增强其功能，常见的注解有以下几个：

| 注解               | 说明                                                         | 备注                            |
| ------------------ | ------------------------------------------------------------ | ------------------------------- |
| @Test              | 测试类中的方法用它修饰才能成为测试方法，才能启动执行         | 单元测试                        |
| @BeforeEach        | 用来修饰一个实例方法，该方法会在每一个测试方法执行之前执行一次。 | 初始化资源(准备工作)            |
| @AfterEach         | 用来修饰一个实例方法，该方法会在每一个测试方法执行之后执行一次。 | 释放资源(清理工作)              |
| @BeforeAll         | 用来修饰一个静态方法，该方法会在所有测试方法之前只执行一次。 | 初始化资源(准备工作)            |
| @AfterAll          | 用来修饰一个静态方法，该方法会在所有测试方法之后只执行一次。 | 释放资源(清理工作)              |
| @ParameterizedTest | 参数化测试的注解 (可以让单个测试运行多次，每次运行时仅参数不同) | 用了该注解，就不需要@Test注解了 |
| @ValueSource       | 参数化测试的参数来源，赋予测试方法参数                       | 与参数化测试注解配合使用        |
| @DisplayName       | 指定测试类、测试方法显示的名称 （默认为类名、方法名）        |                                 |

演示 `@BeforeEach`，`@AfterEach`，`@BeforeAll`，`@AfterAll` 注解：

```Java
public class UserServiceTest {

    @BeforeEach
    public void testBefore(){
        System.out.println("before...");
    }

    @AfterEach
    public void testAfter(){
        System.out.println("after...");
    }

    @BeforeAll //该方法必须被static修饰
    public static void testBeforeAll(){ 
        System.out.println("before all ...");
    }

    @AfterAll //该方法必须被static修饰
    public static void testAfterAll(){
        System.out.println("after all...");
    }

    @Test
    public void testGetAge(){
        Integer age = new UserService().getAge("110002200505091218");
        System.out.println(age);
    }
    
    @Test
    public void testGetGender(){
        String gender = new UserService().getGender("612429198904201611");
        System.out.println(gender);
    }
 }   
```

输出结果如下：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ODg1MmRmOGIyMjE2NDJjZmQ4NDExNGRlZGY0MzgyZDBfaVA0VzhZSzRYMVJEb1Q1cEhnbUJxN1pGVEJBMUtTa2VfVG9rZW46UExqeGJ5TXV3b2ZLYzZ4b3FyTWNWUW9WbkljXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

演示 `@ParameterizedTest  `，`@ValueSource` ，`@DisplayName` 注解：

```Java
package com.itheima;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("测试-学生业务操作")
public class UserServiceTest {

    @DisplayName("测试-获取年龄")
    @Test
    public void testGetAge(){
        Integer age = new UserService().getAge("110002200505091218");
        System.out.println(age);
    }

    @DisplayName("测试-获取性别")
    @Test
    public void testGetGender(){
        String gender = new UserService().getGender("612429198904201611");
        System.out.println(gender);
    }

    @DisplayName("测试-获取性别3")
    @ParameterizedTest
    @ValueSource(strings = {"612429198904201611","612429198904201631","612429198904201626"})
    public void testGetGender3(String idcard){
        String gender = new UserService().getGender(idcard);
         System.out.println(gender);
    }
}
```

输出结果如下：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=N2M4N2YxYjY0N2NjYmZhOGUzNmU5NDkyYmIzNmUwNTZfeFdWQnhqQnpVNGZyaTRCTkJiajJmeDI3Sld2bDJiVHNfVG9rZW46RUlUOGJYN29ub0hLSDV4SkFkVGNmWFdxbjBmXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

**思考: 在maven项目中，test目录存放单元测试的代码，是否可以在main目录中编写单元测试呢 ？** **可以，但是不规范**

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=OTI4ZjdhOGQwYmM4ZWUxZGZkZDBjOTI4ZTI2YmViMGVfQXlzNWNUTE9YTGVNa0hhenMyOUxRNnZZU05oOUhscEdfVG9rZW46Undab2J3Mk5Ub29INnJ4WWFIMmNHdUVlbnFiXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

 

### 依赖范围

依赖的jar包，默认情况下，可以在任何地方使用，在main目录下，可以使用；在test目录下，也可以使用。 

在maven中，如果希望限制依赖的使用范围，可以通过 `<scope>…</scope>` 设置其作用范围。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YjA4MWRhZGQzYjJjZTQwNjY5Y2FiZjg2NjA2ZjUxM2ZfQ1pSWmx3NDJjZTcyQzlPc0lPaUR3UVJ0S0VEZ1Q1MU9fVG9rZW46T1laa2JxeUY0b3ZuaFB4d0J4a2Njb21jbk5iXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

**作用范围：**

- 主程序范围有效。（main文件夹范围内）
- 测试程序范围有效。（test文件夹范围内）
- 是否参与打包运行。（package指令范围内）

可以在pom.xml中配置 `<scope></scope>` 属性来控制依赖范围。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NTEzNDQ5NzRjZTI2ZDFhNjQ5MmI3MzAzYTIzMTQwZjFfd1lrRTlUTUZXNFJyM1pDcWlyOUFiVDF2NGJMNG9BRHZfVG9rZW46TTNpRWJYS2l3b2VSZVp4TmZsNWNPQ1RsbkhjXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

如果对Junit单元测试的依赖，设置了scope为 test，就代表，该依赖，只是在测试程序中可以使用，在主程序中是无法使用的。所以我们会看到如下现象：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NDJjMjAwMGJiN2M3ZmUzYmUzZWE2MTJmMTgyOGQ5MTBfQ29wSm9kcWxCR1JpcDRqcWEwdXJseGl2M0tKV3F5dWJfVG9rZW46T3V4ZGJVVVZQb1R2Sjl4VWJSdGNZZzlKblFjXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

如上图所示，给junit依赖通过scope标签指定依赖的作用范围。 那么这个依赖就只能作用在测试环境，其他环境下不能使用。

scope的取值常见的如下：

| scope值         | 主程序 | 测试程序 | 打包（运行） | 范例        |
| --------------- | ------ | -------- | ------------ | ----------- |
| compile（默认） | Y      | Y        | Y            | log4j       |
| test            | -      | Y        | -            | junit       |
| provided        | Y      | Y        | -            | servlet-api |
| runtime         | -      | Y        | Y            | jdbc驱动    |

## Maven常见问题

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZGZhYWE5MmFmODljZGI5MmVjOThmNzVjNDczYTVlNWRfaW81RnRuMzNqVmNlN0pqNGJURnVmRXFVc01rSXdiaExfVG9rZW46RDA3RWI0cFYxbzYxUXZ4aUx0aGNPRWVIblljXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

- **问题现象：**Maven项目中添加的依赖，未正确下载，造成右侧Maven面板中的依赖报红，再次reload重新加载也不会再下载。
- **产生原因：**由于网络原因，依赖没有下载完整导致的，在maven仓库中生成了**xxx.lastUpdated**文件，该文件不删除，不会再重新下载。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZWRiZTA0Yjg1NmQyMWY1NDk2MTBiY2FkMzFiYzM0NzdfOWhCREJTY29yencwbjZ5ZGFyUDlHR1l5YXY2ZzZzQjZfVG9rZW46T1dxaWJ0aXVvb2RHcUx4NXdTS2NHcllVbmtkXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

**解决方案：**

1. 根据maven依赖的坐标，找到仓库中对应的 xxx.lastUpdated 文件，删除，删除之后重新加载项目即可。
2. 通过命令 (del /s *.lastUpdated) 批量递归删除指定目录下的 xxx.lastUpdated 文件，删除之后重新加载项目即可。
3. 重新加载依赖，依赖下载了之后，maven面板可能还会报红，此时可以关闭IDEA，重新打开IDEA加载此项目即可。

为了使大家能够方便的解决这个问题，大家可以将资料中提供的 del.bat 批处理脚本，拷贝到maven的安装目录下。 双击这个文件，就可以递归删除该目录下所有的 xxx.lastUpdated 文件。 放置目录如下所示：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MTExZTc4MjgyMDVkNmQyYzhkMzVlZTdlNmM4MzkwOTBfSDhjMXpsc2t5R1JjY0RDUUJwS3BRcXJsN2RqYUNpODdfVG9rZW46SlFHSmJ3WVZHb09OTjF4TGM5T2NROUJ3bnVlXzE3OTA4NjcwMTc6MTc5MDg3MDYxN19WNA&add_watermark=true&scene_type=CCM)

- **附件（上述提到的del.bat批处理文件， 也可以直接点此下载）：**

del.bat

```
del /s *.lastUpdated
```

