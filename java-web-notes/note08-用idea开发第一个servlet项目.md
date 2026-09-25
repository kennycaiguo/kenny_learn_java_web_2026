# 1.在idea开发第一个servlet程序

## 1.打开idea，创建一个新项目，在弹出的窗口中选择空项目，如何填写好项目名称和保存路径，点击finish按钮

![image-20260923121440046](./note08-用idea开发第一个servlet项目.assets/image-20260923121440046.png)

## 2.然后点击文件，项目结构。在弹出的窗口中选择我们的jdk版本和语言级别，点击确定

![image-20260923122022330](./note08-用idea开发第一个servlet项目.assets/image-20260923122022330.png)

## 3.点击文件-》设置，在搜索框里面输入文件编码，把所有的选项的编码都改为UTF-8点击应用再点击确定

![image-20260923122419481](./note08-用idea开发第一个servlet项目.assets/image-20260923122419481.png)

## 4.在项目文件夹上面点击右键-》新建-》模块

![image-20260923122548658](./note08-用idea开发第一个servlet项目.assets/image-20260923122548658.png)

## 5.此时就会退出一个创建模块窗口，给模块起一个名字web03,其他保持默认，点击确定

![image-20260923122821009](./note08-用idea开发第一个servlet项目.assets/image-20260923122821009.png)

## 6.idea会给我们新建一个web03并且在里面创建一个src文件夹并且创建了一个有Main方法的类，此时它是一个普通java项目，不是web项目

![image-20260923123046054](./note08-用idea开发第一个servlet项目.assets/image-20260923123046054.png)

## 7.我们来改造项目，点击文件-项目结构，然后在弹出的窗口中点击模块，选择我们的web03，再点击上面的+,选择web

![image-20260923123416331](./note08-用idea开发第一个servlet项目.assets/image-20260923123416331.png)

## 8.然后点击应用再点击确定

![image-20260923125255662](./note08-用idea开发第一个servlet项目.assets/image-20260923125255662.png)

## 9.此时idea就会生成web目录结构

![image-20260923125432773](./note08-用idea开发第一个servlet项目.assets/image-20260923125432773.png)



### 需要注意，这个web文件夹是项目的根，但是不是项目名称，我们在部署项目的时候需要修改名称。

## 10.把idea在src里面生成的文件删除，然后我们添加一个package，org.kenny.servlet,然后我们添加一个类，名叫HelloServlet

![image-20260923131418756](./note08-用idea开发第一个servlet项目.assets/image-20260923131418756.png)

## 11.然后我们让这个类继承HttpServlet,不过此时我们还没有这个api，它报红了，我们需要配置calsspath

![image-20260923131708041](./note08-用idea开发第一个servlet项目.assets/image-20260923131708041.png)

## 12.在web03文件夹上面点击右键-》新建=》文件夹，起名lib

![image-20260923131859102](./note08-用idea开发第一个servlet项目.assets/image-20260923131859102.png)

## 13.把tomcat服务器里面的servlet-api.jar拷贝粘贴到lib文件夹里面

![image-20260923132058851](./note08-用idea开发第一个servlet项目.assets/image-20260923132058851.png)

## 14.然后在这个jar包上面点击右键-》添加为库，在弹出的窗口中打开确定

![image-20260923132330843](./note08-用idea开发第一个servlet项目.assets/image-20260923132330843.png)



## 15.然后我们就可以打入这个类了

![image-20260923132502145](./note08-用idea开发第一个servlet项目.assets/image-20260923132502145.png)

## 16.哦不对，我们需要实现Servlet接口

![image-20260923132732152](./note08-用idea开发第一个servlet项目.assets/image-20260923132732152.png)

## 17.把service方法里面的两个参数改名为request和reponse，然后我们安装ctrl+点击Servlet接口，弹出一个窗口，我们点击选择源按钮来关联源码，在弹出的窗口里面选择tomcat源码的zip文件

![image-20260923133922316](./note08-用idea开发第一个servlet项目.assets/image-20260923133922316.png)

## 18.点击选择文件夹按钮，就可以关联上，此时就会有很多注释文档

![image-20260923134055629](./note08-用idea开发第一个servlet项目.assets/image-20260923134055629.png)

## 19我们需要把上面的实现方法删除了，重新添加这些方法

![image-20260923134322453](./note08-用idea开发第一个servlet项目.assets/image-20260923134322453.png)

## 20.然后我们需要在web.xml里面配置servlet

![image-20260923134753667](./note08-用idea开发第一个servlet项目.assets/image-20260923134753667.png)

## 21.给idea配置tomcat，点击idea的窗口顶部的当前文件旁边的小箭头，选择编辑配置

![image-20260923134937988](./note08-用idea开发第一个servlet项目.assets/image-20260923134937988.png)

## 22.在弹出的窗口里面点击最左边的+，在弹出的窗口中找到tomcat服务器点击它里面的本地选项，

![image-20260923135224913](./note08-用idea开发第一个servlet项目.assets/image-20260923135224913.png)

## 23.此时右边就会有一下配置选项，我们先点击应用程序服务器一栏右边的配置按钮

![image-20260923140213924](./note08-用idea开发第一个servlet项目.assets/image-20260923140213924.png)

## 24.idea会自动检测到tomcat的配置并且弹出一个确认窗口，我们只需要点击确认

![image-20260923140425141](./note08-用idea开发第一个servlet项目.assets/image-20260923140425141.png)

## 25.然后点击应用

![image-20260923140626255](./note08-用idea开发第一个servlet项目.assets/image-20260923140626255.png)

## 26.点击确定退出配置，然后我们需要配置工件artifact，点击文件-》项目结构-》工件

![image-20260923141004842](./note08-用idea开发第一个servlet项目.assets/image-20260923141004842.png)

## 27.点击旁边的+，然后在下拉菜单里面选择-》web应用程序:展开型-》源模块

![image-20260923141310067](./note08-用idea开发第一个servlet项目.assets/image-20260923141310067.png)

## 28.然后点击确定，就会出现下面的界面

![image-20260923141444859](./note08-用idea开发第一个servlet项目.assets/image-20260923141444859.png)

## 29.点击应用，然后点击确定

![image-20260923141555942](./note08-用idea开发第一个servlet项目.assets/image-20260923141555942.png)

## 30.然后我们需要点击tomcat服务器的部署选项，修改部署的路径为/web03

![image-20260923142051819](./note08-用idea开发第一个servlet项目.assets/image-20260923142051819.png)



## 31.然后点击应用再点击确定完成部署，点击idea左下角的服务，就可以看到tomcat服务器

![image-20260923142338605](./note08-用idea开发第一个servlet项目.assets/image-20260923142338605.png)

## 注意。虽然项目部署好了，但是你在tomcat的webapps文件夹里面看不到，因为idea生成一个新的tomcat并且把项目部署到那里。

![image-20260923142652848](./note08-用idea开发第一个servlet项目.assets/image-20260923142652848.png)

## 32.点击idea顶部的debug按钮把项目跑起来，部署成功并且打开了浏览器，但是此时是404，因为我们配置的路径是/web03/hello

![image-20260923143116998](./note08-用idea开发第一个servlet项目.assets/image-20260923143116998.png)



## 33.我们在浏览器中的web03路径后面添加/hello,就看到我们的输出了

![image-20260923143300368](./note08-用idea开发第一个servlet项目.assets/image-20260923143300368.png)

### 注意，在使用idea调试我们的项目的时候，我们需要关闭tomcat服务器，否则就会报错。

## 34.然后我们来处理一下idea里面的tomcat的乱码，我们发现idea的控制台的字符编码是UTF-8，我们需要回答tomcat/conf文件夹里面的logging.properties文件里面把控制台的编码改为UTF-8

![image-20260923143845010](./note08-用idea开发第一个servlet项目.assets/image-20260923143845010.png)

## 35.然后idea里面的tomcat的乱码就消失了

![image-20260923144000102](./note08-用idea开发第一个servlet项目.assets/image-20260923144000102.png)



# 2.关于idea在tomcat服务器的配置

## 1.我们一般只关注服务器和部署两个选项，其他选项一般不用管

![image-20260923144546809](./note08-用idea开发第一个servlet项目.assets/image-20260923144546809.png)

## 2.点击部署选项卡，可以看到外面部署了哪些项目，以及项目的根路径

![image-20260923144747572](./note08-用idea开发第一个servlet项目.assets/image-20260923144747572.png)

## 3.如果需要添加部署，可以点击+按钮，也可以使用前面介绍的方法

## 4.idea支持配置多个tomcat服务器

## 5.更新操作有四个选项，是指当你在idea的服务窗口里面点击了更新应用程序服务器(也就是刷新服务器)按钮(ctrl+f10)触发的行为。其中更新资源选项只会更新静态资源，需要更新所有就选择更新类和资源，重新部署就是把整个下面重新部署一遍，最好的选项是更新类和资源

![image-20260923150719487](./note08-用idea开发第一个servlet项目.assets/image-20260923150719487.png)

## 6.切换出ide选项一般不要动，只有你电脑的配置非常高，你才能选择需要做点什么，这里是什么都不做，因为这个选项如果需要做操作，他是非常消耗内存的。

![image-20260923151802875](./note08-用idea开发第一个servlet项目.assets/image-20260923151802875.png)

## 7.如果你想部署多个虚拟tomcat服务，你需要为每一个虚拟服务器配置不同的http端口和jmx端口

## 8.建议在开发中，每当你修改了代码，你都需要点击更新服务器按钮或者按ctrl+f10





## 小结：用idea开发一个servlet项目一般需要3大步骤

### 1》创建一个普通的java模块

### 2》给这个模块添加web支持，完成开发并且把项目变成一个构件或者说是工件。

### 3》把工件部署到tomcat（我们看不见）

### 4》idea部署项目的时候，其实是根据我们安装的tomcat创建一个虚拟的tomcat并且把下面部署到了那个虚拟tomcat里面，但是那个虚拟tomcat是依赖以我们安装的tomcat才能够正常工作的，你千万不要把我们安装的tomcat删除，否则虚拟tomcat就不能够正常工作

# 3.给我们的项目添加数据库功能

## 3.1 在项目的WEB-INF里面新建一个lib文件夹

![image-20260923152840851](./note08-用idea开发第一个servlet项目.assets/image-20260923152840851.png)

## 3.2 把数据库驱动的jar包粘贴进来，注意这里不需要作为库添加

![image-20260923152956943](./note08-用idea开发第一个servlet项目.assets/image-20260923152956943.png)

## 3.3 然后我们来添加操作数据库的代码,这里只需要修改service方法

```
package org.kenny.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HelloServlet implements Servlet {

    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<h1>Hello World From HelloServlet!</h1>");
        // 数据库连接信息
        String URL = "jdbc:mysql://localhost:3306/company?useSSL=false&serverTimezone=UTC";
        String USER = "root";
        String PASS = "root";
        String driver = "com.mysql.jdbc.Driver";
        String sql="select ename,sal from emp order by sal desc";
        try {
            Class.forName(driver);
            try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
                 PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery();
            ) {
                while (rs.next()) {
                String ename = rs.getString("ename");
                String sal = rs.getString("sal");
                out.println(ename+" "+sal+"<br>");
             }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "";
    }

    @Override
    public void destroy() {

    }
}

```

## 3.4，然后我们只需要点击更新服务器按钮就可以，我们在浏览器里面输入：http://localhost:8080/web03/hello 效果如下

![image-20260923161339097](./note08-用idea开发第一个servlet项目.assets/image-20260923161339097.png)

# 扩展，激活jbrain的ide2025版本

网址: https://blog.idejihuo.com/jetbrains/intellij-idea-2025-2-latest-activation-tutorial-permanent-activation-code-cracking-tool-2099.html

工具下载： https://fileio.lanzouw.com/ibL0z3d03sng

下载后解压缩，然后以管理员的身份运行jetbra-free-windows7-amd64.exe，会打开一个本地网站，我们只需要配置好名字和过期时间，点击submit，然后用鼠标点击我们需要激活的软件，出现cracked，说明激活成功

