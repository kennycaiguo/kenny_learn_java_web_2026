# 1.什么是http协议

## 1》基础概念

![image-20260924105246305](./note09-http协议学习.assets/image-20260924105246305.png)

## 2》HTTP1.1的特点

### 2.1可复用![image-20260924105430107](./note09-http协议学习.assets/image-20260924105430107.png)

### 2.2 无状态

![image-20260924105802609](./note09-http协议学习.assets/image-20260924105802609.png)

# 2.http请求报文详解

![image-20260924110020239](./note09-http协议学习.assets/image-20260924110020239.png)

## 字段说明，请求有4部分组成，请求行，请求头，空白行，请求体

### 请求行

![image-20260924110203894](./note09-http协议学习.assets/image-20260924110203894.png)

### 请求头

![image-20260924150205403](./note09-http协议学习.assets/image-20260924150205403.png)

### 请求体

![image-20260924150233293](./note09-http协议学习.assets/image-20260924150233293.png)

# 3.http响应报文详解

## 响应报文也是由4部分组成：状态行，响应头，空白行，响应体

![image-20260924152337394](./note09-http协议学习.assets/image-20260924152337394.png)

## 关键字段说明

### 状态行

![image-20260924153246984](./note09-http协议学习.assets/image-20260924153246984.png)

### 响应头

![image-20260924153308972](./note09-http协议学习.assets/image-20260924153308972.png)

### 响应体

![image-20260924153627384](./note09-http协议学习.assets/image-20260924153627384.png)

# 4.GET与POST的区别与选择

## 4.1 GET与POST的区别

![image-20260924154627878](./note09-http协议学习.assets/image-20260924154627878.png)

![image-20260924161754904](./note09-http协议学习.assets/image-20260924161754904.png)

### 注意：get方法是会被浏览器缓存，缓存的优点是效率高，用户体验好，缺点就是数据不够实时。有些网站为了不让浏览器缓存数据，会采取在url后面跟时间戳的方法。由于时间戳一直变化，浏览器就无法使用缓存。

## 4.2 如何选择，读取用get，需要保持数据的使用post

![image-20260924163809506](./note09-http协议学习.assets/image-20260924163809506.png)

## 4.3 如何发送post请求

![image-20260924164403823](./note09-http协议学习.assets/image-20260924164403823.png)

# 5.URL编码

![image-20260924164513896](./note09-http协议学习.assets/image-20260924164513896.png)

## 5.1 url编码的作用

![image-20260924164613101](./note09-http协议学习.assets/image-20260924164613101.png)

## 5.2 前端系统中什么情况下需要手动编码

![image-20260924170126385](./note09-http协议学习.assets/image-20260924170126385.png)

## 5.3后端系统中什么情况下需要手动编码

![image-20260924171407479](./note09-http协议学习.assets/image-20260924171407479.png)



### 小实例

![image-20260924171938296](./note09-http协议学习.assets/image-20260924171938296.png)



# 演练

## 1.新建一个Java项目，起名web04

![image-20260924124700132](./note09-http协议学习.assets/image-20260924124700132.png)

## 2.给它添加web支持，点击文件-》项目结构，在打开的窗口选择模块，然后点击旁边的+号，在下拉列表里面选择web

![image-20260924124912355](./note09-http协议学习.assets/image-20260924124912355.png)

## 3.点击应用然后点击确定，就会添加一个web模块

![image-20260924125011254](./note09-http协议学习.assets/image-20260924125011254.png)



## 4.然后在web04目录里面新建一个lib文件夹，把servlet-api.jar放进来

![image-20260924125404110](./note09-http协议学习.assets/image-20260924125404110.png)

## 5.然后在中国jar包上面点击右键-》添加为库，在弹出的窗口中点击确定，它就会变为下面的样子

![image-20260924125545965](./note09-http协议学习.assets/image-20260924125545965.png)



## 6.在web文件夹里面(注意在WEB-INF文件夹外面，否则无法找到页面)

![image-20260924130912784](./note09-http协议学习.assets/image-20260924130912784.png)

## 新建一个index.html,创建一个表单，内容如下

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome to Home Page</title>
</head>
<body>
  <form method="get" action="/web04/user/login">
    用户名：<input type="text" name="username" /> <br>
    密&nbsp;&nbsp;码：<input type="password" name="password" /> <br>
    <button type="submit">登录</button>
  </form>
</body>
</html>
```

![image-20260924130950029](./note09-http协议学习.assets/image-20260924130950029.png)

## 7.然后我们来配置一下web.xml文件

```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    
    <servlet>
        <servlet-name>login</servlet-name>
        <servlet-class>com.kenny.servlet.LoginServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>login</servlet-name>
        <url-pattern>/user/login</url-pattern>
    </servlet-mapping>
</web-app>
```

## 8.然后我们在src文件夹里面新建一个包：com.kenny.servlet,然后在里面新建一个类LoginServlet,重写service方法，这里的逻辑编辑简单只是获取表单数据然后显示在页面上，因为我们这里是学习http协议

![image-20260924132634722](./note09-http协议学习.assets/image-20260924132634722.png)



## 9.写好后我们来部署项目，先构建工件artifects

![image-20260924132816105](./note09-http协议学习.assets/image-20260924132816105.png)

![image-20260924132848732](./note09-http协议学习.assets/image-20260924132848732.png)

## 10.然后我们把它部署到tomcat服务器上面

![image-20260924132947296](./note09-http协议学习.assets/image-20260924132947296.png)

## 11.然后我们点击idea顶部的调试按钮，项目就会编译部署然后在浏览器窗口打开表单

![image-20260924133125696](./note09-http协议学习.assets/image-20260924133125696.png)

## 12.填写表单，点击登录，就会进入/web04/user/login路由，并且在浏览器窗口显示用户名和密码

![image-20260924133258981](./note09-http协议学习.assets/image-20260924133258981.png)

![image-20260924133331022](./note09-http协议学习.assets/image-20260924133331022.png)

## 13.此时我们在页面上按fn+f12可以打开浏览器调试窗口然后我们选择网络选项，就可以看到请求数据和响应数据

url路径是这样子的

![image-20260924141619329](./note09-http协议学习.assets/image-20260924141619329.png)

![image-20260924133848351](./note09-http协议学习.assets/image-20260924133848351.png)

Payload(载荷)是这样子的，用query string parameter表示，证明是get方法传递过来的。

![image-20260924141725707](./note09-http协议学习.assets/image-20260924141725707.png)

## 14.然后我们修改一下index.html的表单，把它请求方法改为post重启服务器

url是这样子的

![image-20260924142019245](./note09-http协议学习.assets/image-20260924142019245.png)

payload看不出区别，不过有Formdata标识，说明是post方法传递过来的。

![image-20260924142700677](./note09-http协议学习.assets/image-20260924142700677.png)



### post比get安全

