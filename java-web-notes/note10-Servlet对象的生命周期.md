# 1生命周期以及管理者

![image-20260924172420651](./note10-Servlet对象的生命周期.assets/image-20260924172420651.png)

# 演练

## 1.把G:\my_projects_2026\kenny_learn_java_web_2026\java-web-codes\java-web-idea中文一个项目打开，然后添加一个模块web05

![image-20260924173105068](./note10-Servlet对象的生命周期.assets/image-20260924173105068.png)

## 2.给web05添加web支持，添加完后可以点击修正按钮-》create artifects创建工件方便部署到tomcat服务器上面,

![image-20260924174410527](./note10-Servlet对象的生命周期.assets/image-20260924174410527.png)

![image-20260924174536515](./note10-Servlet对象的生命周期.assets/image-20260924174536515.png)



## 3.在web05文件夹里面新建一个lib文件夹，然后把servlet-api.jar拷贝粘贴过来

![image-20260924174738493](./note10-Servlet对象的生命周期.assets/image-20260924174738493.png)

## 4.然后在这个jar包上面点击右键-》添加为库

![image-20260924174825782](./note10-Servlet对象的生命周期.assets/image-20260924174825782.png)

![image-20260924174912330](./note10-Servlet对象的生命周期.assets/image-20260924174912330.png)



## 5.然后我们把它部署到tomcat服务器上面，点击应用按钮并且点击确定按钮

![image-20260924181436915](./note10-Servlet对象的生命周期.assets/image-20260924181436915.png)

## 6.编辑有些tomcat服务器，把执行更新操作一栏改为“跟新类和静态资源”

![image-20260924181736921](./note10-Servlet对象的生命周期.assets/image-20260924181736921.png)

## 7.然后在src文件夹里面新建一个包org.kenny.servlet,在里面新建一个叫做LifecycleServlet的java类，并且实现Servlet接口

![image-20260924182116458](./note10-Servlet对象的生命周期.assets/image-20260924182116458.png)

## 8.然后在web.xml在配置servlet

```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <servlet>
        <servlet-name>lifecycle</servlet-name>
        <servlet-class>org.kenny.servlet.LifecycleServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>lifecycle</servlet-name>
        <url-pattern>/life</url-pattern>
    </servlet-mapping>
</web-app>
```

## 9.我们随便写一些代码

![image-20260924182718693](./note10-Servlet对象的生命周期.assets/image-20260924182718693.png)

## 10.然后启动服务器，在浏览器中输入：http://localhost:8080/web05/life ，发现可以访问了，

![image-20260924183044244](./note10-Servlet对象的生命周期.assets/image-20260924183044244.png)

## 在后台的控制台也有输出，但是有中文乱码

![image-20260924183129729](./note10-Servlet对象的生命周期.assets/image-20260924183129729.png)

### 问题出现在tomcat服务器里面

![image-20260924183431897](./note10-Servlet对象的生命周期.assets/image-20260924183431897.png)

![image-20260924190140294](./note10-Servlet对象的生命周期.assets/image-20260924190140294.png)

### 解决办法，打开idea的tomcat服务器的配置，在虚拟机选项一栏填入 -Dstdout.encoding=UTF-8,然后点击应用，再点击确定

![image-20260924185210029](./note10-Servlet对象的生命周期.assets/image-20260924185210029.png)

### 然后重启服务器，在浏览器中输入http://localhost:8080/web05/life，一切正常

![image-20260924185724585](./note10-Servlet对象的生命周期.assets/image-20260924185724585.png)

### 我们给这几个方法都添加一条输出语句来看看方法是否会执行，然后我们重启服务器，发现默认只有init和service方法被调用。

![image-20260924185724585](./note10-Servlet对象的生命周期.assets/image-20260924185724585.png)







# 2.测试Servlet对象的生命周期

## 1.还是上面的web05项目，我们在service方法里面编写LifecycleServlet的构造方法在里面输出无参构造函数被调用的语句，然后在每一个函数里面都添加输出语句，目的就是看看那个函数被调用了

```
package org.kenny.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;

public class LifecycleServlet implements Servlet {
    //编写无参构造方法
    public LifecycleServlet() {
        System.out.println("LifecycleServlet constructor no params called!!!");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init方法执行了");
    }

    @Override
    public ServletConfig getServletConfig() {
        System.out.println("getServletConfig方法执行了");
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("service方法执行了");
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.println("Hello Clients");

    }

    @Override
    public String getServletInfo() {
        System.out.println("getServletInfo方法执行了");
        return "";
    }

    @Override
    public void destroy() {
        System.out.println("destroy方法执行了");
    }
}

```



## 2.启动tomcat服务器，默认是没有成就Servlet对象，也没有方法被调用，然后在浏览器中输入：http://localhost:8080/web05/life，在控制台中可以看到，构造函数和init和service方法被调用了

![image-20260925114811449](./note10-Servlet对象的生命周期.assets/image-20260925114811449.png)

## 3.然后我们关闭服务器，控制台上显示destroy方法会被调用

![image-20260925115221005](./note10-Servlet对象的生命周期.assets/image-20260925115221005.png)

### 执行效果如下

![image-20260925115345540](./note10-Servlet对象的生命周期.assets/image-20260925115345540.png)

### 总结如下

![image-20260925115855857](./note10-Servlet对象的生命周期.assets/image-20260925115855857.png)

![image-20260925120731567](./note10-Servlet对象的生命周期.assets/image-20260925120731567.png)

![image-20260925123505325](./note10-Servlet对象的生命周期.assets/image-20260925123505325.png)

<img src="./note10-Servlet对象的生命周期.assets/image-20260925125333272.png" alt="image-20260925125333272" style="zoom:80%;" />

![image-20260925125501780](./note10-Servlet对象的生命周期.assets/image-20260925125501780.png)

![image-20260925125810115](./note10-Servlet对象的生命周期.assets/image-20260925125810115.png)

![image-20260925130154264](./note10-Servlet对象的生命周期.assets/image-20260925130154264.png)

# 3.servlet3大核心方法的作用

![image-20260927123900724](./note10-Servlet对象的生命周期.assets/image-20260927123900724.png)

![image-20260927124705369](./note10-Servlet对象的生命周期.assets/image-20260927124705369.png)

# 4.在服务器启动阶段实例化Servlet对象

## 4.1 假如我们有这样子的2个servlet，AServlet和BServlet，我们分别在两个Servlet的init方法在输入一行文本，AServlet init和BServlet init

![image-20260927125111527](./note10-Servlet对象的生命周期.assets/image-20260927125111527.png)

## 4.2 然后我们在web.xml中做如下配置

![image-20260927125310419](./note10-Servlet对象的生命周期.assets/image-20260927125310419.png)

## 4.3 然后启动服务器，发现这两个类的方法都被调用了

![image-20260927125422342](./note10-Servlet对象的生命周期.assets/image-20260927125422342.png)

## 注意默认情况下，当服务器启动，但是又没有用户访问，是不会创建servlet对象的，如果你需要servlet对象在服务器启动是时候就创建，需要在web.xml里面添加一个load-on-startup配置，注意，里面的数字越小，优先级越高。



