# 课程内容：

- SpringBootWeb 入门
- Http协议
- SpringBootWeb案例
- 分层解耦

那在前面讲解Web前端开发的时候，我们学习了前端网页开发的三剑客HTML、CSS、JS，通过这三项技术，我们就可以制作前端页面了。 那最终，这些个页面资料，我们就可以部署在服务器上，然后打开浏览器就可以直接访问服务器上部署的前端页面了。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ODA5MGJiNDc3NDc4Zjk3NThlNzQxMzVjODM5OTUwY2RfTm9wbkZVVFNLaDJGVVlxcjJjSnpGTm5YZ296NzZRVVBfVG9rZW46TTc3TGJ3dlZObzd6NnB4OVFhcmNzRFlBblRmXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

而像HTML、CSS、JS 以及图片、音频、视频等这些资源，我们都称为**静态资源**。 所谓静态资源，就是指在服务器上存储的不会改变的数据，通常不会根据用户的请求而变化。

那与静态资源对应的还有一类资源，就是动态资源。那所谓**动态资源**，就是指在服务器端上存储的，会根据用户请求和其他数据动态生成的，内容可能会在每次请求时都发生变化。比如：Servlet、JSP等(负责逻辑处理)。而Servlet、JSP这些技术现在早都被企业淘汰了，现在在企业项目开发中，都是直接基于Spring框架来构建动态资源。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ODk3YzdiYTliZmE0NWU2YzI5NjIzZjg1OGE4NzQ5OTVfam9ySnhScktZeGFZRng0WFlsVXlQQVhraTZIa1k1d2FfVG9rZW46UnNKcGJkQnJTb29vY0l4cVRJWmNpbTRibjVmXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

而对于我们java程序开发的动态资源来说，我们通常会将这些动态资源部署在**Tomcat**，这样的Web服务器中运行。 而浏览器与服务器在通信的时候，基本都是基于HTTP协议的。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=OTI1MjRhOGY4MTc2NDRjYzczMmZjODIwY2JhYzg5M2JfTGs3RnljekN2SWR6d1JTWGplb1J1Y2ZjY0tieFBaaWtfVG9rZW46VzJ2YmJ4YlhGb0tLa3h4N1ZCY2Nra28zbnlmXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

那上述所描述的这种浏览器/服务器的架构模式呢，我们称之为：**BS架构**。

- BS架构：Browser/Server，浏览器/服务器架构模式。客户端只需要浏览器，应用程序的逻辑和数据都存储在服务端。
  - 优点：维护方便
  - 缺点：体验一般
- CS架构：Client/Server，客户端/服务器架构模式。需要单独开发维护客户端。
  - 优点：体验不错
  - 缺点：开发维护麻烦

那前面我们已经学习了静态资源开发技术，包括：HTML、CSS、JS以及JS的高级框架Vue，异步交互技术Axios。 那那接下来呢，我们就要来学习动态资料开发技术，而动态资源开发技术中像早期的Servlet、JSP这些个技术早都被企业淘汰了，现在企业开发主流的就是基于Spring体系中的框架来开发这些动态资源。 所以，我们今天的课程内容内，分为以下四个部分：

- SpringBootWeb入门
- HTTP协议
- SpringBootWeb案例
- 分层解耦

## SpringBootWeb入门

那接下来呢，我们就要来讲解现在企业开发的主流技术 SpringBoot，并基于SpringBoot进行Web程序的开发 。

### 概述

在没有正式的学习SpringBoot之前，我们要先来了解下什么是Spring。

我们可以打开Spring的官网(https://spring.io)，去看一下Spring的简介：Spring makes Java simple。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MmRkNWRhYTg3YTdjMjkxMGExZjI2NzQ0NjRlYzhhYjRfMDN1aHdEcm10MGhuUWpmTDJNdjNRc0dFS2c2Y3NLY0tfVG9rZW46SjBWSmJPN21ubzJLdWh4eGlnTGNaWlRDbnViXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

Spring的官方提供很多开源的项目，我们可以点击上面的projects，看到spring家族旗下的项目，按照流行程度排序为：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=N2U3YjBjNTkxNmYwY2VlZGQ0YWM1NTkwNDk4MTk4ZTRfd29RUWtUMTZPNThQSlpKNjVkSEo1S2xGRkJwdXVhMVdfVG9rZW46TXlKV2I0OWtJb1dBUHZ4ZFg2R2NvUjd0blhkXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

Spring发展到今天已经形成了一种开发生态圈，Spring提供了若干个子项目，每个项目用于完成特定的功能。而我们在项目开发时，一般会偏向于选择这一套spring家族的技术，来解决对应领域的问题，那我们称这一套技术为**spring全家桶**。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NTE1ZGJhYmRkNTg3NDhkNDhmYzgyNTE0MGZlNzkzZjBfaTdnamlIWHNnclkxeUxQUVhweUNnRlNDaWMyWXZ5VXpfVG9rZW46TTJ2UmI5Z2h3b2V5eFp4Q25vSGNUeWdZbk1jXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

而Spring家族旗下这么多的技术，最基础、最核心的是 SpringFramework。其他的spring家族的技术，都是基于SpringFramework的，SpringFramework中提供很多实用功能，如：依赖注入、事务管理、web开发支持、数据访问、消息服务等等。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ODFiYjBmZDg3NzcwYWY2ZDM3Y2ZjMjYyOWE1OWMwOTdfb09qWXdmVzRETHg1Y2g3WU5iNHVXOGw2MGdaUEsxV1hfVG9rZW46VTE2RmJwOUQybzhhclZ4YXJrdWN4RzJLbnlnXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

而如果我们在项目中，直接基于**SpringFramework**进行开发，存在两个问题：

- 配置繁琐
- 入门难度大

所以基于此呢，spring官方推荐我们从另外一个项目开始学习，那就是目前最火爆的SpringBoot。 通过springboot就可以快速的帮我们构建应用程序，所以**springboot**呢，最大的特点有两个 ：

- 简化配置
- 快速开发

**Spring Boot 可以帮助我们非常快速的构建应用程序、简化开发、提高效率 。**

**而直接基于SpringBoot进行项目构建和开发，不仅是Spring官方推荐的方式，也是现在企业开发的主流。**

### 入门程序

#### 需求

需求：基于SpringBoot的方式开发一个web应用，浏览器发起请求/hello后，给浏览器返回字符串 "Hello xxx ~"。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MTE5NzRhNWQ3YzQ2MDM2MjA3NDlmOGY2ODdmNTc4YmZfWkdNeDVQaTREdzdJa1pMRzFYMzV1SGZtUXo5OHRwQkZfVG9rZW46QW1LeWJOU0R5bzJ1YlF4ZWNJN2NoeUJobk1nXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

#### 开发步骤

第1步：创建SpringBoot工程，并勾选Web开发相关依赖

第2步：定义HelloController类，添加方法hello，并添加注解

**1). 创建SpringBoot工程（需要联网）**

基于Spring官方骨架，创建SpringBoot工程。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=OGU5NGM2NmRlMTg1MjE5ZjgzZDJmYTI5NzU1ZjJiZDhfejZwbDBDQTZvTXJZMXNUWHA3VVBoWlVlVFZuNDV4TDBfVG9rZW46RllpcWJqTllab3duVU54c3pPNmNBY1YwblViXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

基本信息描述完毕之后，勾选web开发相关依赖。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YjczYTlhNzQxMWY5YzQzZDI3MGIyYzFlYmViZGZlMTJfWVEwWjhiY0JTV3RDT1phWHZSa2R1OUExaWNEVGRoRjNfVG9rZW46RjF5YmJJWVVubzY3bGt4RVdSOWNFajhhbnhlXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

SpringBoot官方提供的脚手架，里面只能够选择SpringBoot的几个最新的版本，如果要选择其他相对低一点的版本，可以在springboot项目创建完毕之后，修改项目的pom.xml文件中的版本号。

点击Create之后，就会联网创建这个SpringBoot工程，创建好之后，结构如下：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=M2VmZTIxYWFkMDQ3MzBlZWJjNTFmNjVhNGFjMjA4Y2Jfc1p5M0czRkdTa1RCOEdMNjk3THhsOG9sSXdKZUlzVXRfVG9rZW46U2c1eWJCTlVYb0N3ZTZ4OGRTSmNtTXNBbnpiXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

**注意：在联网创建过程中，会下载相关资源(请耐心等待)**

**2). 定义HelloController类，添加方法hello，并添加注解**

在`com.itheima`这个包下新建一个类：`HelloController`

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZTNkMDViNWI0YmYzNDJhZmU1NWJiNjlmMDY2MTE4YjFfckRHdVVyRnFDQlF6YURvd0l6YjY3d0I4ajZFMzBPSkhfVG9rZW46SWgxQmJaQWxlb0VZV1N4VW9Xc2NrTHRSbjBkXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

HelloController中的内容，具体如下：

```Java
package com.itheima;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //标识当前类是一个请求处理类
public class HelloController {

    @RequestMapping("/hello") //标识请求路径
    public String hello(String name){
        System.out.println("HelloController ... hello: " + name);
        return "Hello " + name;
    }

}
```

**3). 运行测试**

运行SpringBoot自动生成的引导类 (标识有`@SpringBootApplication`注解的类)

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MGRhOGQ2MzRmNDVmZGI5ZmM1ZThiOWRlOGQ2MjhmYjJfMzVtYzFyUnViRFRGbTlSalo0amZGOEE5dmF1RlkyWDRfVG9rZW46Skt2c2J1UVdOb29Qd014NlBXcGNsOG5JblFkXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

打开浏览器，输入 `http://localhost:8080/hello?name=itheima`

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZGMyMWE4NTIwMjU0ZTU5NzkzNGFmNGZiYWQ3OTljMjRfV1VteUxWOTNhZFFlaVhrNFk0eDZsNjFpeXA4RTdnYkZfVG9rZW46T2l6c2JGV3FBb2o4TE94QkxicGNiamNTbnRkXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

#### 常见问题

大伙儿在下来联系的时候，联网基于spring的脚手架创建SpringBoot项目，偶尔可能会因为网内网络的原因，链接不上SpringBoot的脚手架网站，此时会出现如下现象：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZDcxNzFiOWU1ZDFlMjkzODM4OTgyNmQ2MGY5MmZlMDhfeGtqRW9Ha0NRbXdIRTU0bVhoWXZweGhERXpidjh3T3ZfVG9rZW46WnczV2JhV1E2b2NyeDl4MGtUTmNMSVFLbjBjXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

此时可以使用阿里云提供的脚手架，网址为：https://start.aliyun.com

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NjY0NzA5N2IwYjZhYjczMGNlNWEwZGNjNzY0NWQ3YWNfeW1iTXRSQXdlcG9oMXZCajVZbm5sd1dZeW9xMURhNzBfVG9rZW46VEt5eWJJTEdib1AxNzV4bjVHdmNVTkhHbnhlXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

然后按照项目创建的向导，一步一步的创建项目即可。

### 入门解析

那在上面呢，我们已经完成了SpringBootWeb的入门程序，并且测试通过。 在入门程序中，我们发现，我们只需要一个main方法就可以将web应用启动起来了，然后就可以打开浏览器访问了。

那接下来我们需要明确两个问题：

**1). 为什么一个main方法就可以将Web应用启动了？**

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NzgzNzRmYjlkMjJkZTAzYTMyYTc3MDg5ZDEwOWJhNzBfRHVWN05rM0J0T3JBVnY2NGtKaXNpaXc5djNVdVhNNnpfVG9rZW46SXpXbWJpNE56b3E3ZWd4bk4yaGNCblVtbkVoXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

因为我们在创建springboot项目的时候，选择了web开发的**起步依赖** `spring-boot-starter-web`。而`spring-boot-starter-web`依赖，又依赖了`spring-boot-starter-tomcat`，由于maven的依赖传递特性，那么在我们创建的springboot项目中也就已经有了tomcat的依赖，这个其实就是springboot中内嵌的tomcat。 

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZjE5MTRlYTdiMGQ0MGJmZTc2MmIwOGU1MjRhMWU1ZGFfU2VRM2p0dFU4YXRZZ0k0SmNWUzhoTmJlYjRmZWZHa2hfVG9rZW46UHY0TWJpT0JRb092N214QTJpR2NZc3NWblRkXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

而我们运行引导类中的main方法，其实启动的就是springboot中内嵌的Tomcat服务器。 而我们所开发的项目，也会自动的部署在该tomcat服务器中，并占用8080端口号 。 

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MTY1Y2QzY2VlNTA3OTcxNGQ0MWZiN2E4N2VjZDI2YjBfUFFhbFRWVnhwSkRYUWRqU0dUdmVuNFFQVkJxakNQcllfVG9rZW46VnhoOWJiekd3b3VuaE94WHFHUWM4VTJybnlnXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

**起步依赖：**

- 一种为开发者提供简化配置和集成的机制，使得构建Spring应用程序更加轻松。起步依赖本质上是一组预定义的依赖项集合，它们一起提供了在特定场景下开发Spring应用所需的所有库和配置。
  - spring-boot-starter-web：包含了web应用开发所需要的常见依赖。
  - spring-boot-starter-test：包含了单元测试所需要的常见依赖。
- 官方提供的starter：[https://docs.spring.io/spring-boot/docs/3.1.3/reference/htmlsingle/#using.build-systems.starters](https://docs.spring.io/spring-boot/docs/3.1.3/reference/htmlsingle/)

## HTTP协议

### HTTP概述

#### 介绍

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NWZjMGNjYTU0YmZjZWRjMmQ4MWFhMmExMjg4OGM3NDNfOThzN1RRTE9ScUtSZ0NpQnJxc1AzRUtkdUoyVFJMbVNfVG9rZW46QXlxYWJHOFA3b2xvdmp4MmRxVGNqNmJpbjJoXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

**HTTP**：Hyper Text Transfer Protocol(超文本传输协议)，规定了浏览器与服务器之间数据传输的规则。

- http是互联网上应用最为广泛的一种网络协议 
- http协议要求：浏览器在向服务器发送请求数据时，或是服务器在向浏览器发送响应数据时，都必须按照固定的格式进行数据传输

如果想知道http协议的数据传输格式有哪些，可以打开浏览器，点击`F12`打开开发者工具，点击`Network(网络)`来查看

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZTY2NWJhMzljYTZmNDhmOTk2NTgwOWMzYzI2MTg5MThfTWY3enZHSXNSMHRCWGpoWjIyaGNxQlNycTRiNEo2elBfVG9rZW46TkdneGJhZFVCb3hDc2p4TmhTemNqeEF4bmNaXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

浏览器向服务器进行请求时，服务器按照固定的格式进行解析：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MTQ5M2E1ODgxNmEzMWJkOTcyNGNlYWM0MzNhMDExYWFfYzVKVFNVTllZS3B2YlZNNW1CWnBPY2Frb0hZUHo1Z09fVG9rZW46VjJPemJnZmg5b0FTVmx4VWZvMGNnZVdGbmJnXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

服务器向浏览器进行响应时，浏览器按照固定的格式进行解析：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=OWY5Zjc4NTAwZjE4MjlmYWU4NjFiNDYzNjM4OGQzZDVfOURVMER0ZGJXQldySFpNR3daNHBnYUphU2t1Yk1hNnVfVG9rZW46QTY3UWJQdXo5b2FITXl4NHJVNGNhNGVxbkplXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

而我们学习HTTP协议，就是来学习请求和响应数据的具体格式内容。

#### 特点

我们刚才初步认识了HTTP协议，那么我们在看看HTTP协议有哪些特点：

- **基于TCP协议:** 面向连接，安全

> TCP是一种面向连接的(建立连接之前是需要经过三次握手)、可靠的、基于字节流的传输层通信协议，在数据传输方面更安全

- **基于请求-响应模型:**   一次请求对应一次响应（先请求后响应）

> 请求和响应是一一对应关系，没有请求，就没有响应

- **HTTP协议是无状态协议:**  对于数据没有记忆能力。每次请求-响应都是独立的

> 无状态指的是客户端发送HTTP请求给服务端之后，服务端根据请求响应数据，响应完后，不会记录任何信息。
>
> - 缺点:  多次请求间不能共享数据
> - 优点:  速度快
>
> 
>
> - 请求之间无法共享数据会引发的问题：
>   - 如：京东购物。加入购物车和去购物车结算是两次请求
>   - 由于HTTP协议的无状态特性，加入购物车请求响应结束后，并未记录加入购物车是何商品
>   - 发起去购物车结算的请求后，因为无法获取哪些商品加入了购物车，会导致此次请求无法正确展示数据
>
> 
>
> - 具体使用的时候，我们发现京东是可以正常展示数据的，原因是Java早已考虑到这个问题，并提出了使用会话技术(Cookie、Session)来解决这个问题。具体如何来做，我们后面课程中会讲到。

刚才提到HTTP协议是规定了请求和响应数据的格式，那具体的格式是什么呢? 接下来，我们就来详细剖析。

HTTP协议又分为：请求协议和响应协议

### HTTP请求协议

#### 介绍

- **请求协议：**浏览器将数据以请求格式发送到服务器。包括：**请求行、请求头 、请求体**

- **GET方式的请求协议：**

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MzRlMDlkNTU0YmIzNmYxOTQ4ZTVlZWQ4MjUyYjYwMTFfN09CTXBkaXZaQ3JwT09UVGc3V2NFVXBaR2lNb292ODVfVG9rZW46QkZWUmJMek5RbzB0Vld4ZUk2Q2NrRGxGbmxnXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

- **请求行**(以上图中红色部分) ：HTTP请求中的第一行数据。由：`请求方式`、`资源路径`、`协议/版本`组成（之间使用空格分隔）

  - 请求方式：GET  
  - 资源路径：/brand/findAll?name=OPPO&status=1
    - 请求路径：/brand/findAll
    - 请求参数：name=OPPO&status=1
      - 请求参数是以key=value形式出现
      - 多个请求参数之间使用`&`连接
    - 请求路径和请求参数之间使用`?`连接                          
  - 协议/版本：HTTP/1.1  

- **请求头**(以上图中黄色部分) ：第二行开始，上图黄色部分内容就是请求头。格式为key: value形式 

  - http是个无状态的协议，所以在请求头设置浏览器的一些自身信息和想要响应的形式。这样服务器在收到信息后，就可以知道是谁，想干什么了

  - 常见的HTTP请求头有:

    - | 请求头          | 含义                                                         |
      | --------------- | ------------------------------------------------------------ |
      | Host            | 表示请求的主机名                                             |
      | User-Agent      | 浏览器版本。 例如：Chrome浏览器的标识类似Mozilla/5.0 ...Chrome/79 ，IE浏览器的标识类似Mozilla/5.0 (Windows NT ...)like Gecko |
      | Accept          | 表示浏览器能接收的资源类型，如text/*，image/*或者*/*表示所有； |
      | Accept-Language | 表示浏览器偏好的语言，服务器可以据此返回不同语言的网页；     |
      | Accept-Encoding | 表示浏览器可以支持的压缩类型，例如gzip, deflate等。          |
      | Content-Type    | 请求主体的数据类型                                           |
      | Content-Length  | 数据主体的大小（单位：字节）                                 |

> 举例说明：服务端可以根据请求头中的内容来获取客户端的相关信息，有了这些信息服务端就可以处理不同的业务需求。
>
> 比如:
>
> - 不同浏览器解析HTML和CSS标签的结果会有不一致，所以就会导致相同的代码在不同的浏览器会出现不同的效果
> - 服务端根据客户端请求头中的数据获取到客户端的浏览器类型，就可以根据不同的浏览器设置不同的代码来达到一致的效果（这就是我们常说的浏览器兼容问题）

- 请求体 ：存储请求参数
  - GET请求的请求参数在请求行中，故不需要设置请求体

**POST方式的请求协议：**

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YzkzYmRiNmExYTEwODdkMTIxNTNlZDFmOWE0OWEyNTRfR0Y0UWZLVkZ1Ym1rblNyaFJEdGcxTXhqQU8wYWNpUmdfVG9rZW46TzFoaWJQTjRHb2t2Nm94OUxzYmM1WWRibk5jXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

- **请求行**(以上图中红色部分)：包含请求方式、资源路径、协议/版本
  - 请求方式：POST
  - 资源路径：/brand
  - 协议/版本：HTTP/1.1
- **请求头**(以上图中黄色部分)   
- **请求体**(以上图中绿色部分) ：存储请求参数 
  - 请求体和请求头之间是有一个空行隔开（作用：用于标记请求头结束）

GET请求和POST请求的区别：

| **区别方式** | **GET请求**                                                  | **POST请求**         |
| ------------ | ------------------------------------------------------------ | -------------------- |
| 请求参数     | 请求参数在请求行中。<br/>例：/brand/findAll?name=OPPO&status=1 | 请求参数在请求体中   |
| 请求参数长度 | 请求参数长度有限制(浏览器不同限制也不同)                     | 请求参数长度没有限制 |
| 安全性       | 安全性低。原因：请求参数暴露在浏览器地址栏中。               | 安全性相对高         |

#### 获取请求数据

Web服务器（Tomcat）对HTTP协议的请求数据进行解析，并进行了封装(HttpServletRequest)，并在调用Controller方法的时候传递给了该方法。这样，就使得程序员不必直接对协议进行操作，让Web开发更加便捷。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MmE0MGQwMDE2M2Y5ZjBkZTQxZmYyYjk5ZjFjNjg4MDlfeldaVHYyVjk5SWhkZ25JbjFjV3p6SjB6cjNiRU1zaTlfVG9rZW46SGEwdGJxa3NFbzJyUkN4blhZS2NwaXRSbjFlXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

代码演示如下：

```Java
@RestController
public class RequestController {

    /**
     * 请求路径 http://localhost:8080/request?name=Tom&age=18
     * @param request
     * @return
     */
    @RequestMapping("/request")
    public String request(HttpServletRequest request){
        //1.获取请求参数 name, age
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        System.out.println("name = " + name + ", age = " + age);
        
        //2.获取请求路径
        String uri = request.getRequestURI();
        String url = request.getRequestURL().toString();
        System.out.println("uri = " + uri);
        System.out.println("url = " + url);
        
        //3.获取请求方式
        String method = request.getMethod();
        System.out.println("method = " + method);
        
        //4.获取请求头
        String header = request.getHeader("User-Agent");
        System.out.println("header = " + header);
        return "request success";
    }

}
```

最终输出内容如下所示：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YzlhMTE4MTQ1OTRkYjE2YmFhYjhjY2Y3YTk5NWFiODVfbmhzbE13THdXQm94OUxLZlhiNTFWVjlRbEJIa2cxNkFfVG9rZW46UUpVVmJXbWk0b0puVFh4S1dKWmNSSXFobmpiXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

### HTTP响应协议

#### 格式介绍

- 响应协议：服务器将数据以响应格式返回给浏览器。包括：**响应行 、响应头 、响应体**

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=Y2M3NGI2MjQxNjY2YmY3MGQ5NDkyMDRjOWJiNjZhMTNfaUNDM2xuOVRUcjFPUnZjQUVlNElqVU5sdHk1a3lVdk9fVG9rZW46UFdYdWJ4MFBMb3pLZ054WXhvZWNLRXNibm9IXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

- 响应行(以上图中红色部分)：响应数据的第一行。响应行由`协议及版本`、`响应状态码`、`状态码描述`组成
  - 协议/版本：HTTP/1.1
  - 响应状态码：200
  - 状态码描述：OK
- 响应头(以上图中黄色部分)：响应数据的第二行开始。格式为key：value形式
  - http是个无状态的协议，所以可以在请求头和响应头中设置一些信息和想要执行的动作，这样，对方在收到信息后，就可以知道你是谁，你想干什么
  - 常见的HTTP响应头有:
  - ```Java
    Content-Type：表示该响应内容的类型，例如text/html，image/jpeg ；
    
    Content-Length：表示该响应内容的长度（字节数）；
    
    Content-Encoding：表示该响应压缩算法，例如gzip ；
    
    Cache-Control：指示客户端应如何缓存，例如max-age=300表示可以最多缓存300秒 ;
    
    Set-Cookie: 告诉浏览器为当前页面所在的域设置cookie ;
    ```
- 响应体(以上图中绿色部分)： 响应数据的最后一部分。存储响应的数据
  - 响应体和响应头之间有一个空行隔开（作用：用于标记响应头结束）

#### 响应状态码

| 状态码分类 | 说明                                                         |
| ---------- | ------------------------------------------------------------ |
| 1xx        | 响应中 --- 临时状态码。表示请求已经接受，告诉客户端应该继续请求或者如果已经完成则忽略 |
| 2xx        | 成功 --- 表示请求已经被成功接收，处理已完成                  |
| 3xx        | 重定向 --- 重定向到其它地方，让客户端再发起一个请求以完成整个处理 |
| 4xx        | 客户端错误 --- 处理发生错误，责任在客户端，如：客户端的请求一个不存在的资源，客户端未被授权，禁止访问等 |
| 5xx        | 服务器端错误 --- 处理发生错误，责任在服务端，如：服务端抛出异常，路由出错，HTTP版本不支持等 |

关于响应状态码，我们先主要认识三个状态码，其余的等后期用到了再去掌握：

- `200 ok`   客户端请求成功
- `404 Not Found`  请求资源不存在
- `500 Internal Server Error`  服务端发生不可预期的错误

#### 设置响应数据

Web服务器对HTTP协议的响应数据进行了封装(HttpServletResponse)，并在调用Controller方法的时候传递给了该方法。这样，就使得程序员不必直接对协议进行操作，让Web开发更加便捷。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MDBjOGE1ZjM5MjUyMThhNTRlNDU1YzEwNTI0ODJlNzVfbnJYUks2VGttTHRwUnNuaEc1dUR6MldHSmwxM2JzbGZfVG9rZW46QjhiN2J5OERHb2hJYjJ4UkRlbmNWcnRzbkhlXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

代码演示：

```Java
package com.itheima;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ResponseController {

    @RequestMapping("/response")
    public void response(HttpServletResponse response) throws IOException {
        //1.设置响应状态码
        response.setStatus(401);
        //2.设置响应头
        response.setHeader("name","itcast");
        //3.设置响应体
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("<h1>hello response</h1>");
    }

    @RequestMapping("/response2")
    public ResponseEntity<String> response2(HttpServletResponse response) throws IOException {
        return ResponseEntity
                .status(401)
                .header("name","itcast")
                .body("<h1>hello response</h1>");
    }

}
```

浏览器访问测试：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZDAyMWNiZDAwOWFkNWMzYzY2NTc2M2UxNmFlZDE3ZWRfZTkyM1EzT1YyWVVvaHZoOWpNVVgxU3pQSmcxTnFITUFfVG9rZW46QVpyb2I5Qng1b0IwWWJ4U3pKRmN3RHdGbjVmXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

响应状态码 和 响应头如果没有特殊要求的话，通常不手动设定。服务器会根据请求处理的逻辑，自动设置响应状态码和响应头。

## SpringBootWeb案例

### 需求说明

需求：基于SpringBoot开发web程序，完成用户列表的渲染展示

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZmZlZmM4OGM4YmY0NTU3ODM4NWIxMDZjNWUxMzllMDBfMVpWVGdCczZkaXJ3bno4ZDhFWVg2aW5hTkY1Z1lWSmNfVG9rZW46VFhIRGJ0a0V0b1hERm54ZG5EOWNtSEpNbmpoXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

当在浏览器地址栏，访问前端静态页面（http://localhost:8080/usre.html）后，在前端页面上，会发送ajax请求，请求服务端（http://localhost:8080/list），服务端程序加载 user.txt 文件中的数据，读取出来后最终给前端页面响应json格式的数据，前端页面再将数据渲染展示在表格中。

### 代码实现

#### **1). 准备工作：再创建一个SpringBoot工程，并勾选web依赖、lombok依赖。**

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YThiMzFkNGI5OGQ2ZTc0Njc2N2ZhZGVjODVmNmIyY2ZfejBzQmJ0M1MybXptT0xzSkJlVnQyQmlkVzdrc0dnRDFfVG9rZW46QjdwR2I1VVpJb3JPWlV4cVBmbmNldkxxbjJlXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MDY3ZGM4NDA5NDRkNDAyMzk2ZGFjZmZmMmRmMTdmNzRfbkFwN29LRmwxTjVXQnBERm1jS3pyRU5JN0xNSEdwdE1fVG9rZW46TFV6NGJWUVBvb0xSM1J4S1pkcmNMTHR5bmhjXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

#### **2). 准备工作：引入资料中准备好的数据文件user.txt，以及static下的前端静态页面**

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YzFjMzY2NTk2Y2U0NDc3ZmY4NzhmOWMyOGI3Nzc3ZmZfcDBQRkhlTHRDYlpOS1dOUGRMbnJBeWp0dlFkMEFuWWNfVG9rZW46TkFqU2IycTJYb3NSQmZ4R2RTRmNOT0dUbjZlXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

这些文件，在提供的资料中，已经提供了直接导入进来即可。 

#### **3). 准备工作：定义封装用户信息的实体类。**

在 `com.itheima` 下再定义一个包 `pojo`，专门用来存放实体类。 在该包下定义一个实体类User：

```Java
package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * 封装用户信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer age;
    private LocalDateTime updateTime;
}
```

#### **4). 开发服务端程序，接收请求，读取文本数据并响应**

由于在案例中，需要读取文本中的数据，并且还需要将对象转为json格式，所以这里呢，我们在项目中再引入一个非常常用的工具包hutool。 然后调用里面的工具类，就可以非常方便快捷的完成业务操作。

- `pom.xml`中引入依赖

```XML
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-all</artifactId>
    <version>5.8.27</version>
</dependency>
```

- 在`com.itheima`包下新建一个子包`controller`，在其中创建一个`UserController`

```Java
import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import com.itheima.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
    
    @RequestMapping("/list")
    public String list(){
        //1.加载并读取文件
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("user.txt");
        ArrayList<String> lines = IoUtil.readLines(in, StandardCharsets.UTF_8, new ArrayList<>());
        
        //2.解析数据，封装成对象 --> 集合
        List<User> userList = lines.stream().map(line -> {
            String[] parts = line.split(",");
            Integer id = Integer.parseInt(parts[0]);
            String username = parts[1];
            String password = parts[2];
            String name = parts[3];
            Integer age = Integer.parseInt(parts[4]);
            LocalDateTime updateTime = LocalDateTime.parse(parts[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            return new User(id, username, password, name, age, updateTime);
        }).collect(Collectors.toList());
        
        //3.响应数据
        //return JSONUtil.toJsonStr(userList, JSONConfig.create().setDateFormat("yyyy-MM-dd HH:mm:ss"));
        return userList;
    }
    
}
```

#### **5). 启动服务测试，访问：**http://localhost:8080/user.html

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YzdiNzNiNjhjZGQzN2IxN2NmYjJjZWY2ZTlkNTY5ZGZfajNjZ1d3SzFQRzQwWnlyWGVkaTFNa1lXd1h6SUtVcjBfVG9rZW46TlFwRGJWVTM4b1pwRDZ4dFF1N2NWWk9lbkhjXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

### @ResponseBody

前面我们学习过HTTL协议的交互方式：请求响应模式（有请求就有响应）。那么Controller程序呢，除了接收请求外，还可以进行响应。

在我们前面所编写的controller方法中，都已经设置了响应数据。

controller方法中的return的结果，怎么就可以响应给浏览器呢？

答案：使用@ResponseBody注解

**@ResponseBody注解：**

- 类型：方法注解、类注解
- 位置：书写在Controller方法上或类上
- 作用：将方法返回值直接响应给浏览器，如果返回值类型是实体对象/集合，将会转换为JSON格式后在响应给浏览器

但是在我们所书写的Controller中，只在类上添加了@RestController注解、方法添加了@RequestMapping注解，并没有使用@ResponseBody注解，怎么给浏览器响应呢？

这是因为，我们在类上加了@RestController注解，而这个注解是由两个注解组合起来的，分别是：@Controller 、@ResponseBody。 那也就意味着，我们在类上已经添加了@ResponseBody注解了，而一旦在类上加了@ResponseBody注解，就相当于该类所有的方法中都已经添加了@ResponseBody注解。 

> 提示：前后端分离的项目中，一般直接在请求处理类上加@RestController注解，就无需在方法上加@ResponseBody注解了。

### 问题分析

上述案例的功能，我们虽然已经实现，但是呢，我们会发现案例中：解析文本文件中的数据，处理数据的逻辑代码，给页面响应的代码全部都堆积在一起了，全部都写在controller方法中了。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZmNjMTc5NWM5ZTQ0NTA5ZDk1OTg3YTA0MWUwZjU3Y2VfM2pBUnhzcDltMzNZajZ3dGZ4ZHg4SjIyRFpWVWhwdjNfVG9rZW46QkdJY2JWY2FEb2lyWUl4dThLaGMxT0tEbnZmXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

当前程序的这个业务逻辑还是比较简单的，如果业务逻辑再稍微复杂一点，我们会看到Controller方法的代码量就很大了。

- 当我们要修改操作数据部分的代码，需要改动Controller
- 当我们要完善逻辑处理部分的代码，需要改动Controller
- 当我们需要修改数据响应的代码，还是需要改动Controller

这样呢，就会造成我们整个工程代码的复用性比较差，而且代码难以维护。 那如何解决这个问题呢？其实在现在的开发中，有非常成熟的解决思路，那就是分层开发。 

## 分层解耦

### 三层架构

#### 介绍

在我们进行程序设计以及程序开发时，尽可能让每一个接口、类、方法的职责更单一些（单一职责原则）。

> 单一职责原则：一个类或一个方法，就只做一件事情，只管一块功能。
>
> 这样就可以让类、接口、方法的复杂度更低，可读性更强，扩展性更好，也更利于后期的维护。

我们之前开发的程序呢，并不满足单一职责原则。下面我们来分析下之前的程序：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MDVkZmRiZTFlN2YwMDVmM2QxNzIxODRjODRkNjM0MzJfRmZuTzhQVTU2OUQ2YkYwMEtYUTB4YWxBeWJ0M1lEWkNfVG9rZW46SHBjSWJ2bEowb1VuMlN4UHdTZWNqcm9LbldZXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

那其实我们上述案例的处理逻辑呢，从组成上看可以分为三个部分：

- 数据访问：负责业务数据的维护操作，包括增、删、改、查等操作。
- 逻辑处理：负责业务逻辑处理的代码。
- 请求处理、响应数据：负责，接收页面的请求，给页面响应数据。

按照上述的三个组成部分，在我们项目开发中呢，可以将代码分为三层，如图所示：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YjE2NjIyNDEyZmI0MjliMWE0YmEyMWNlY2VkNGVhY2FfZkxNN01SUWZocmhDOVFiZFN0REJHaGxQcFJ6cUt2OVFfVG9rZW46TGNqcmJ5VlFtb2F0M0d4WDVoamM3eXV3bkloXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

- Controller：控制层。接收前端发送的请求，对请求进行处理，并响应数据。
- Service：业务逻辑层。处理具体的业务逻辑。
- Dao：数据访问层(Data Access Object)，也称为持久层。负责数据访问操作，包括数据的增、删、改、查。

基于三层架构的程序执行流程，如图所示：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NGEwZDRkODcwYzZkMmY4Zjc3YjgyYWZmMjAyYjIwN2FfSkJEa0xDTU9GcVU1NjZNODFWT2p5ZTJYRENmT3FPQkJfVG9rZW46RnpPNGJ2ZjRlb1A5VUx4aWxEMWNMdlRRbjFlXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

- 前端发起的请求，由Controller层接收（Controller响应数据给前端）
- Controller层调用Service层来进行逻辑处理（Service层处理完后，把处理结果返回给Controller层）
- Serivce层调用Dao层（逻辑处理过程中需要用到的一些数据要从Dao层获取）
- Dao层操作文件中的数据（Dao拿到的数据会返回给Service层）

> 思考：按照三层架构的思想，如果要对业务逻辑(Service层)进行变更，会影响到Controller层和Dao层吗？ 
>
> 答案：不会影响。 （程序的扩展性、维护性变得更好了）

#### 代码拆分

我们使用三层架构思想，来改造下之前的程序：

- 控制层包名：`com.itheima.controller`
- 业务逻辑层包名：`com.itheima.service`
- 数据访问层包名：`com.itheima.dao`

**1). 控制层：接收前端发送的请求，对请求进行处理，并响应数据**

在 `com.itheima.controller` 中创建UserController类，代码如下：

```Java
package com.itheima.controller;

import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserController {
    
    private UserService userService = new UserServiceImpl();

    @RequestMapping("/list")
    public List<User> list(){
        //1.调用Service
        List<User> userList = userService.findAll();
        //2.响应数据
        return userList;
    }

}
```

**2). 业务逻辑层：处理具体的业务逻辑**

在 `com.itheima.service`中创建UserSerivce接口，代码如下：

```Java
package com.itheima.service;

import com.itheima.pojo.User;
import java.util.List;

public interface UserService {

    public List<User> findAll();

}
```

在 `com.itheima.service.impl` 中创建UserSerivceImpl接口，代码如下：

```Java
package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.pojo.User;
import com.itheima.service.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        List<String> lines = userDao.findAll();
        List<User> userList = lines.stream().map(line -> {
            String[] parts = line.split(",");
            Integer id = Integer.parseInt(parts[0]);
            String username = parts[1];
            String password = parts[2];
            String name = parts[3];
            Integer age = Integer.parseInt(parts[4]);
            LocalDateTime updateTime = LocalDateTime.parse(parts[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new User(id, username, password, name, age, updateTime);
        }).collect(Collectors.toList());
        return userList;
    }
}
```

**3). 数据访问层：负责数据的访问操作，包含数据的增、删、改、查**

在 `com.itheima.dao`中创建UserDao接口，代码如下：

```Java
package com.itheima.dao;

import java.util.List;

public interface UserDao {

    public List<String> findAll();

}
```

在 `com.itheima.dao.impl` 中创建UserDaoImpl接口，代码如下：

```Java
package com.itheima.dao.impl;

import cn.hutool.core.io.IoUtil;
import com.itheima.dao.UserDao;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public List<String> findAll() {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("user.txt");
        ArrayList<String> lines = IoUtil.readLines(in, StandardCharsets.UTF_8, new ArrayList<>());
        return lines;
    }
}
```

具体的请求调用流程：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=M2I3ZWM4MDI5OTBmYTE5YmRiMmU4ZmE5NWMwZGI2YzZfZWtMWjRDQ0FZek16MHhpVHRkcmZEa2F3UGY3MURLa3VfVG9rZW46RWx2WmJJaUpCb2VkTG54bXZzWmNUT1hTbmdoXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

三层架构的好处：

1. 复用性强
2. 便于维护
3. 利用扩展

### 分层解耦

#### 问题分析

由于我们现在在程序中，需要什么对象，直接new一个对象 `new UserServiceImpl()`  。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZTJlYmViNzZiYWZiOGFhYzgzNzUxZTY5NDRkMWRkMjdfNFZWcjUyOWNVWlp4b0ttSXBDam13UnZ0U0Fyd05vVlRfVG9rZW46UmQxU2JsMzBnb0VzSVl4Wnlkc2NWc2FBbjREXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

如果说我们需要更换实现类，比如由于业务的变更，UserServiceImpl 不能满足现有的业务需求，我们需要切换为 UserServiceImpl2 这套实现，就需要修改Contorller的代码，需要创建 UserServiceImpl2 的实现`new UserServiceImpl2()` 。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=OGZjODUwNDdlNjg3YTE1YmFlNTA0OGIzNzg0MmVkZTlfSHk4NWE2UTJOVTZjZkg4a3FwUDBZaEtybWRtNFpZelVfVG9rZW46SEhiMGJuaTdhbzdMNXV4a3pjd2M1ejZybjdkXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

Service中调用Dao，也是类似的问题。这种呢，我们就称之为层与层之间 **耦合** 了。 那什么是耦合呢 ？

首先需要了解软件开发涉及到的两个概念：内聚和耦合。

- **内聚：**软件中各个功能模块内部的功能联系。
- **耦合：**衡量软件中各个层/模块之间的依赖、关联的程度。

**软件设计原则：高内聚低耦合。**

> **高内聚：**指的是一个模块中各个元素之间的联系的紧密程度，如果各个元素(语句、程序段)之间的联系程度越高，则内聚性越高，即 "高内聚"。
>
> **低耦合：**指的是软件中各个层、模块之间的依赖关联程序越低越好。

目前层与层之间是存在耦合的，Controller耦合了Service、Service耦合了Dao。而 高内聚、低耦合的目的是使程序模块的可重用性、移植性大大增强。

那最终我们的目标呢，就是做到层与层之间，尽可能的降低耦合，甚至解除耦合。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZGMwNDlhNGQzNmRkNWU1NDYyNzMxYTY0M2FiYTJmZWFfTW1BZ3ZWWEhCYTlwSkxVSXVNQk1ueG9lZ0YyOGVnTW1fVG9rZW46RkJSZWJIYXExb0dHeEd4NVhSZGNxWVRQblVmXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

#### 解耦思路

之前我们在编写代码时，需要什么对象，就直接new一个就可以了。 这种做法呢，层与层之间代码就耦合了，当service层的实现变了之后， 我们还需要修改controller层的代码。

那应该怎么解耦呢？

**1). 首先不能在EmpController中使用new对象。代码如下：**

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NWZlMjFlY2ZiOGVlYTU1ZDViNGM5YzcwNTZiZjhhMzlfdG00VzhwcEJQeWtUbGI3V1VUZzR6c3plcW5xbG16czVfVG9rZW46RDY3Y2I3cWRwbzBpV1Z4eW9TYmMxQ3NWbmliXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

此时，就存在另一个问题了，不能new，就意味着没有业务层对象（程序运行就报错），怎么办呢? 

我们的解决思路是：

- 提供一个容器，容器中存储一些对象(例：UserService对象)
- Controller程序从容器中获取UserService类型的对象

**2). 将要用到的对象交给一个容器管理。**

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YzJhM2IxNmI1YTU5NWUxYmMyODNmMGIwMTU5MzQ4MDBfVkc0amxUczZZWHRzeXJKQnlBSmZCMHdmV0Zzd1czdmNfVG9rZW46WmNISWJ6cHN1b09jVDF4Mno1WWNkTGhhbnNkXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

**3). 应用程序中用到这个对象，就直接从容器中获取**

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NWM1NzY3YmNiY2U3MTJmYTdmMTU1ZjYxNzQ0MjNjMmJfTGk1RldkRmJmYWJXQ0JFT2RRTUh6WFI3bXBwZzByME5fVG9rZW46Q2RTd2I0Ylpob0hJRGl4Sng1RWNJMWlubkRjXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

那问题来了，我们如何将对象交给容器管理呢？ 程序运行时，容器如何为程序提供依赖的对象呢？ 

我们想要实现上述解耦操作，就涉及到Spring中的两个核心概念：

- **控制反转：** Inversion Of Control，简称**IOC**。对象的创建控制权由程序自身转移到外部（容器），这种思想称为控制反转。
  - 对象的创建权由程序员主动创建转移到容器(由容器创建、管理对象)。这个容器称为：IOC容器或Spring容器。
  - 
- **依赖注入：** Dependency Injection，简称**DI**。容器为应用程序提供运行时，所依赖的资源，称之为依赖注入。
  - 程序运行时需要某个资源，此时容器就为其提供这个资源。
  - 例：EmpController程序运行时需要EmpService对象，Spring容器就为其提供并注入EmpService对象。

- **bean对象：**IOC容器中创建、管理的对象，称之为：bean对象。

### IOC&DI入门

**1). 将Service及Dao层的实现类，交给IOC容器管理**

在实现类加上 `@Component` 注解，就代表把当前类产生的对象交给IOC容器管理。

**A. UserDaoImpl**

```Java
@Component
public class UserDaoImpl implements UserDao {
    @Override
    public List<String> findAll() {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("user.txt");
        ArrayList<String> lines = IoUtil.readLines(in, StandardCharsets.UTF_8, new ArrayList<>());
        return lines;
    }
}
```

**B. UserServiceImpl**

```Java
@Component
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Override
    public List<User> findAll() {
        List<String> lines = userDao.findAll();
        List<User> userList = lines.stream().map(line -> {
            String[] parts = line.split(",");
            Integer id = Integer.parseInt(parts[0]);
            String username = parts[1];
            String password = parts[2];
            String name = parts[3];
            Integer age = Integer.parseInt(parts[4]);
            LocalDateTime updateTime = LocalDateTime.parse(parts[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new User(id, username, password, name, age, updateTime);
        }).collect(Collectors.toList());
        return userList;
    }
}
```

**2). 为Controller 及 Service注入运行时所依赖的对象**

**A. UserServiceImpl**

```Java
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    
    @Override
    public List<User> findAll() {
        List<String> lines = userDao.findAll();
        List<User> userList = lines.stream().map(line -> {
            String[] parts = line.split(",");
            Integer id = Integer.parseInt(parts[0]);
            String username = parts[1];
            String password = parts[2];
            String name = parts[3];
            Integer age = Integer.parseInt(parts[4]);
            LocalDateTime updateTime = LocalDateTime.parse(parts[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new User(id, username, password, name, age, updateTime);
        }).collect(Collectors.toList());
        return userList;
    }
}
```

**B. UserController**

```Java
@RestController
public class UserController {
    
    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public List<User> list(){
        //1.调用Service
        List<User> userList = userService.findAll();
        //2.响应数据
        return userList;
    }

}
```

启动服务，运行测试。 打开浏览器，地址栏直接访问：http://localhost:8080/user.html 。 依然正常访问，就说明入门程序完成了。 已经完成了层与层之间的解耦。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MTg5NzNiOTIxOWM0ODkwNTQ0ZDAwYzlhMDJhMmU5MDBfOGt0b0JqM1F1Z0hzcm1OSnFqcGNiQUZHeGZNbmNRM0pfVG9rZW46UlNBMmI4RnJNb2t3UEl4Y0JDbGNGRkhibnVmXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

### IOC详解

通过IOC和DI的入门程序呢，我们已经基本了解了IOC和DI的基础操作。接下来呢，我们学习下IOC控制反转和DI依赖注入的细节。

#####  Bean的声明

前面我们提到IOC控制反转，就是将对象的控制权交给Spring的IOC容器，由IOC容器创建及管理对象。IOC容器创建的对象称为bean对象。

在之前的入门案例中，要把某个对象交给IOC容器管理，需要在类上添加一个注解：**`@Component`**

而Spring框架为了更好的标识web应用程序开发当中，bean对象到底归属于哪一层，又提供了@Component的衍生注解：

| 注解        | 说明                 | 位置                                              |
| ----------- | -------------------- | ------------------------------------------------- |
| @Component  | 声明bean的基础注解   | 不属于以下三类时，用此注解                        |
| @Controller | @Component的衍生注解 | 标注在控制层类上                                  |
| @Service    | @Component的衍生注解 | 标注在业务层类上                                  |
| @Repository | @Component的衍生注解 | 标注在数据访问层类上（由于与mybatis整合，用的少） |

那么此时，我们就可以使用 `@Service` 注解声明Service层的bean。 使用 `@Repository` 注解声明Dao层的bean。 代码实现如下：

Service层:

```Java
@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Override
    public List<User> findAll() {
        List<String> lines = userDao.findAll();
        List<User> userList = lines.stream().map(line -> {
            String[] parts = line.split(",");
            Integer id = Integer.parseInt(parts[0]);
            String username = parts[1];
            String password = parts[2];
            String name = parts[3];
            Integer age = Integer.parseInt(parts[4]);
            LocalDateTime updateTime = LocalDateTime.parse(parts[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new User(id, username, password, name, age, updateTime);
        }).collect(Collectors.toList());
        return userList;
    }
}
```

Dao层:

```Java
@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public List<String> findAll() {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("user.txt");
        ArrayList<String> lines = IoUtil.readLines(in, StandardCharsets.UTF_8, new ArrayList<>());
        return lines;
    }
}
```

**注意1**：声明bean的时候，可以通过注解的value属性指定bean的名字，如果没有指定，默认为类名首字母小写。

**注意2**：使用以上四个注解都可以声明bean，但是在springboot集成web开发中，声明控制器bean只能用@Controller。

#####  组件扫描

问题：使用前面学习的四个注解声明的bean，一定会生效吗？

答案：不一定。（原因：bean想要生效，还需要被组件扫描）

- 前面声明bean的四大注解，要想生效，还需要被组件扫描注解 `@ComponentScan` 扫描。
- 该注解虽然没有显式配置，但是实际上已经包含在了启动类声明注解 `@SpringBootApplication` 中，默认扫描的范围是启动类所在包及其子包。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZTJiZmY2MjU0ZjZhNTZhODIxNjVlMjA2ZGM4MjZhNjNfWHlPZkRMam05dENoMXdHajJIMjBHNXVxdlJHRkozYXpfVG9rZW46Unh2dmJ3UTNDb3ZxaDl4d0NuY2NDaTd0bnZjXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

所以，我们在项目开发中，只需要按照如上项目结构，将项目中的所有的业务类，都放在启动类所在包的子包中，就无需考虑组件扫描问题。

### DI详解

上一小节我们讲解了控制反转IOC的细节，接下来呢，我们学习依赖注解DI的细节。

依赖注入，是指IOC容器要为应用程序去提供运行时所依赖的资源，而资源指的就是对象。

在入门程序案例中，我们使用了@Autowired这个注解，完成了依赖注入的操作，而这个Autowired翻译过来叫：自动装配。

`@Autowired`注解，默认是按照**类型**进行自动装配的（去IOC容器中找某个类型的对象，然后完成注入操作）

> 入门程序举例：在EmpController运行的时候，就要到IOC容器当中去查找EmpService这个类型的对象，而我们的IOC容器中刚好有一个EmpService这个类型的对象，所以就找到了这个类型的对象完成注入操作。

那如果在IOC容器中，存在多个相同类型的bean对象，会出现什么情况呢？

在下面的例子中，我们准备了两个UserService的实现类，并且都交给了IOC容器管理。 代码如下：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZmE2MzY0MmQyY2VkNmQ4N2IyZTEwNjNkZjI1NDVkMjBfR29CRElGSjgyTXRVM0Zid3JrSVZicWZRbzE3S3BqeGxfVG9rZW46RURHdWJKRzl6b0VEZEx4dlBEY2NNd0w1blZjXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

此时，我们启动项目会发现，控制台报错了：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YjBjMjgyZWUwZWUwYmRkODEwZjU0YTlkNDhjY2YwMmFfR2haVjRwbUNCb3JvYXl3MDM1NEM2cDM4N1FFZkYyS0tfVG9rZW46R3lXYWJnVmozb2JoTW14REZtbWNqQTdzbkVnXzE3OTA4NzE1ODQ6MTc5MDg3NTE4NF9WNA&add_watermark=true&scene_type=CCM)

出现错误的原因呢，是因为在Spring的容器中，UserService这个类型的bean存在两个，框架不知道具体要注入哪个bean使用，所以就报错了。

如何解决上述问题呢？Spring提供了以下几种解决方案：

- @Primary
- @Qualifier
- @Resource

**方案一：使用@Primary注解**

当存在多个相同类型的Bean注入时，加上@Primary注解，来确定默认的实现。

```Java
@Primary
@Service
public class UserServiceImpl implements UserService {
}
```

**方案二：使用@Qualifier注解**

指定当前要注入的bean对象。 在@Qualifier的value属性中，指定注入的bean的名称。 @Qualifier注解不能单独使用，必须配合@Autowired使用。

```Java
@RestController
public class UserController {

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;
```

**方案三：使用@Resource注解**

是按照bean的名称进行注入。通过name属性指定要注入的bean的名称。

```Java
@RestController
public class UserController {
        
    @Resource(name = "userServiceImpl")
    private UserService userService;
```

#### 面试题：@Autowird 与 @Resource的区别

- @Autowired 是spring框架提供的注解，而@Resource是JDK提供的注解
- @Autowired 默认是按照类型注入，而@Resource是按照名称注入

## 附录：常见状态码

| 状态码 | 英文描述                        | 解释                                                         |
| ------ | ------------------------------- | ------------------------------------------------------------ |
| 200    | OK                              | 客户端请求成功，即处理成功，这是我们最想看到的状态码         |
| 302    | Found                           | 指示所请求的资源已移动到由Location响应头给定的 URL，浏览器会自动重新访问到这个页面 |
| 304    | Not Modified                    | 告诉客户端，你请求的资源至上次取得后，服务端并未更改，你直接用你本地缓存吧。隐式重定向 |
| 400    | Bad Request                     | 客户端请求有语法错误，不能被服务器所理解                     |
| 403    | Forbidden                       | 服务器收到请求，但是拒绝提供服务，比如：没有权限访问相关资源 |
| 404    | Not Found                       | 请求资源不存在，一般是URL输入有误，或者网站资源被删除了      |
| 405    | Method Not Allowed              | 请求方式有误，比如应该用GET请求方式的资源，用了POST          |
| 428    | Precondition Required           | 服务器要求有条件的请求，告诉客户端要想访问该资源，必须携带特定的请求头 |
| 429    | Too Many Requests               | 指示用户在给定时间内发送了太多请求（“限速”），配合 Retry-After(多长时间后可以请求)响应头一起使用 |
| 431    | Request Header Fields Too Large | 请求头太大，服务器不愿意处理请求，因为它的头部字段太大。请求可以在减少请求头域的大小后重新提交。 |
| 500    | Internal Server Error           | 服务器发生不可预期的错误。服务器出异常了，赶紧看日志去吧     |
| 503    | Service Unavailable             | 服务器尚未准备好处理请求，服务器刚刚启动，还未初始化好       |

- 状态码大全：https://cloud.tencent.com/developer/chapter/13553 