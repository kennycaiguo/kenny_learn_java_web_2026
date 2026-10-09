# **转发**和**重定向**是 Servlet 中完成资源跳转的重要手段。

## 1.关于 request 域

之前已经接触了一个域对象，叫做应用域：application，对应的接口是：jakarta.servlet.ServletContext，应用域的范围较大，是服务器级别的，整个 webapp 中只有一个应用域对象。

域对象普遍都有以下三个方法：

```java
// 向域绑定数据
void setAttribute(String name, Object data);

// 从域中获取数据
Object getAttribute(String name);

// 删除域中的数据
void removeAttribute(String name);
```

request 是请求域，生命周期较短，只在同一次请求当中有效（因为新的请求会对应新的 request 对象）。因此请求域要小于应用域。

域对象的使用原则：优先选择小的域对象，小的满足不了，再选大的。

## 2.重定向

重定向的代码：

```java
response.sendRedirect("/dept/list");
```

1. 重定向调用的是 response 对象的方法。
2. 重定向时路径以 `/`开始，需要添加项目名。
3. 重定向代码执行时，原理是：response 对象将 `/dept/list`响应给浏览器，浏览器自发的再向服务器发送一次全新的请求，请求路径为：`http://ip:port/dept/list`
4. 重定向是两次请求，怎么理解这个两次请求呢？借用我们之前的一个场景：用户保存部门，保存部门之后重定向到列表页面。
   1. 点击保存时发送了\*\* 第一次 \*\*请求：`http://ip:port/dept/save`，执行 `DeptSaveServlet`
   2. 执行保存逻辑后，`DeptSaveServlet` 执行了重定向的代码：`response.sendRedirect("/dept/list");`
   3. `response` 对象将 `/dept/list`路径响应给浏览器，浏览器又自发的向服务器发送\*\* 第二次 \*\*请求：`http://ip:port/dept/list`
   4. 因此，用户只是点击了 **<font style="color:#DF2A3F;">一次</font>**\*\* **保存操作，但浏览器一共是发送了**<font style="color:#DF2A3F;">两次</font>\*\*请求。
   5. 并且浏览器地址栏上的地址最终会显示第二次请求的路径，因此重定向会导致浏览器地址栏上的地址发生改变。（也就是说，发送的是 `/dept/save` 路径，显示的是`/dept/list`路径。）
5. 怎么测试重定向是两次请求呢？
   1. 可以使用 request 域来测试，因为 request 域只能保留同一次请求中的数据，如果是两次请求，request 域是无法共享数据的。测试两次请求的代码如下：

```java
package com.jkweilai.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/a")
public class AServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 向请求域中绑定数据
        request.setAttribute("message", "Hello Servlet!");
        // 重定向到 /web01/b
        response.sendRedirect(request.getContextPath() + "/b");
    }
}

```

```java
package com.jkweilai.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/b")
public class BServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从request域中取数据
        Object message = request.getAttribute("message");
        // 响应到浏览器
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print("<h2>message = " + message + "</h2>");
    }
}

```

启动服务器，打开浏览器，输入地址：http://localhost:8080/web01/a，测试结果：

![1749180177611-1d19f677-4e2b-41db-b109-cf37490f589e.png](./note16-转发与重定向.assets/1749180177611-1d19f677-4e2b-41db-b109-cf37490f589e-525571.png)

6. 重定向时无法重定向到 `WEB-INF`目录下受保护的资源，例如 `WEB-INF`目录下有一个文件：`a.html`，编写以下代码会出现 404 错误：

```java
response.sendRedirect("/dept/WEB-INF/a.html");
```

为什么？前面我们已经讲过：放在 WEB-INF 目录下的资源是受保护的，不能通过在浏览器地址栏上输入地址来访问。

而重定向是浏览器的行为，以上代码会导致浏览器重新发一次新的请求，而请求路径是：`http://ip:port/dept/WEB-INF/a.html`，因此会出现 404 的问题。

## 3.转发

转发的代码：

```java
request.getRequestDispatcher("/list").forward(request, response);
```

1. 转发是一次请求。可以使用 request 域来测试，转发是否为一次请求，代码如下：

```java
package com.jkweilai.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/a")
public class AServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 向请求域中绑定数据
        request.setAttribute("message", "Hello Servlet!");
        // 转发到 /b
        request.getRequestDispatcher("/b").forward(request, response);
    }
}

```

```java
package com.jkweilai.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/b")
public class BServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从request域中取数据
        Object message = request.getAttribute("message");
        // 响应到浏览器
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print("<h2>message = " + message + "</h2>");
    }
}

```

浏览器地址栏上输入：`http://localhost:8080/web01/a`

![1749180945867-2f7f9bb4-c72c-4699-8467-ec51231c5507.png](./note16-转发与重定向.assets/1749180945867-2f7f9bb4-c72c-4699-8467-ec51231c5507-743698.png)

2. 转发时路径不需要写项目名。
3. 转发调用的是 request 对象的方法。
4. 转发是当前项目内部资源的跳转，浏览器不参与。
5. 转发时路径可以是 WEB-INF 目录下受保护的资源。

## 4.转发与重定向如何选择

当满足以下任何一个条件时，使用转发，其它情况一律使用重定向：

1. 需要在同一次请求中共享数据。
2. 需要跳转到 WEB-INF 目录下受保护的资源。



# 演练

## 1.新建一个模块，起名web09

![image-20261008105839349](./note16-转发与重定向.assets/image-20261008105839349.png)

## 2.给模块添加web支持并且构建构件

![image-20261008110005010](./note16-转发与重定向.assets/image-20261008110005010.png)

![image-20261008110030830](./note16-转发与重定向.assets/image-20261008110030830.png)

![image-20261008110109994](./note16-转发与重定向.assets/image-20261008110109994.png)

## .然后在项目根目录下面新建一个文件夹，把servlet-api.jar粘贴过来,需要作为库添加到classpath，然后在WEB-INFO文件夹里面新建一个lib文件夹，把mysql-connector-j-8.4.0.jar粘贴进来

![image-20261008111114225](./note16-转发与重定向.assets/image-20261008111114225.png)

## 3.然后我们把模块部署到tomcat上

![image-20261008122936488](./note16-转发与重定向.assets/image-20261008122936488.png)

## 4.然后我们新建一个包：org.kenny.servlet,在里面新建一个AServlet和一个BServlet，让他们继承HttpServlet，并且实现doGet方法，分别配置路径“/a"和”/b“

![image-20261008124045805](./note16-转发与重定向.assets/image-20261008124045805.png)

## 5.然后我们新建一个包org.kenny.entity,在里面新建一个User类，实现构造函数和getter和setter和toString方法

![image-20261008124504118](./note16-转发与重定向.assets/image-20261008124504118.png)

## 6.然后我们在AServlet里面编写下面的代码

![image-20261008125305850](./note16-转发与重定向.assets/image-20261008125305850.png)

## 7.启动服务器，然后在浏览器中输入：http://localhost:8080/web09/a 就可以看到这个数据

![image-20261008125433844](./note16-转发与重定向.assets/image-20261008125433844.png)

## 8.我们修改一下代码，把输出语句注释了

![image-20261008130049370](./note16-转发与重定向.assets/image-20261008130049370.png)

## 9.然后我们重定向到/b也就是BServlet会执行，但是注意，每一个请求的req对象是不一样的，每一次请求都会创建一个新的请求对象，重定向的本质是把新的路径给浏览器，然后浏览器会发一次全新的请求到服务器，那么问题来了，在BServlet里面能够获取到数据吗？

![image-20261008134912568](./note16-转发与重定向.assets/image-20261008134912568.png)

## 10.然后我们在BServlet的doGet方法里面添加获取数据并且输出的代码

![image-20261008131416981](./note16-转发与重定向.assets/image-20261008131416981.png)

## 11.在浏览器中输入http://localhost:8080/web09/a 的确会跳转到/b，但是没有数据，因为这个是不同的请求对象。

![image-20261008131314819](./note16-转发与重定向.assets/image-20261008131314819.png)

## 12.然后我们修改一下AServlet的代码，用转发代替重定向

![image-20261008135047485](./note16-转发与重定向.assets/image-20261008135047485.png)

## 13.重启服务器，在浏览器中输入http://localhost:8080/web09/a，发现可以拿到数据，但是浏览器的路径没有改变，因为没有重定向

![image-20261008135155827](./note16-转发与重定向.assets/image-20261008135155827.png)

## 14，其实这个代码可以拆分为2部分，先获取请求转发器对象，然后利用这个对象的forward方法进行转发

![image-20261008135952395](./note16-转发与重定向.assets/image-20261008135952395.png)

## 15.注意，此时，你在浏览器中输入： http://localhost:8080/web09/b， 仍然是拿不到数据的，因为这是不同的请求对象

![image-20261008135340323](./note16-转发与重定向.assets/image-20261008135340323.png)

## 16.重定向和转发的区别

重定向是响应浏览器重新发送请求的，所以路径响应包含项目根路径，而转发是服务器内部资源跳转，不需要包含项目根路径。

## 17.思考一下我们在WEB-INF文件夹里面有一个test.html,假如我们需要跳转到这里，是使用转发还是重定向？我们知道WEB-INF文件夹是受保护的，浏览器不能访问，所以不能重定向只能转发，代码如下

![image-20261008142837749](./note16-转发与重定向.assets/image-20261008142837749.png)

## 18.你在浏览器中输入： http://localhost:8080/web09/a，就能够访问这个文件

![image-20261008142948939](./note16-转发与重定向.assets/image-20261008142948939.png)

## 19.转发和重定向究竟如何选择？

![image-20261008143457763](./note16-转发与重定向.assets/image-20261008143457763.png)

# 扩展：关于Servlet中 的三个域对象

## 1.应用域对象ServletContext，他是整个应用中共享的对象

## 2.Session域也就是会话域对象HttpSession

## 3.请求域对象HttpServletRequest对象

范围大小：应用域 》 会话域 》请求域

一个web应用只有一个应用域 ，一个请求就对应一个请求域，一个会话对应一个会话域。一个会话里面可以又多次请求，会话域和请求域使用较多，应用域使用较少，他们都有下面的方法

![image-20261008154736797](./note16-转发与重定向.assets/image-20261008154736797.png)