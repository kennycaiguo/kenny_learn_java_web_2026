## 一、ServletContext 介绍

### 1. 概念

- ServletContext 也叫做 Servlet 上下文，WEB容器在启动时，它会为每个 WEB 应用程序都创建一个对应的 ServletContext 对象，它代表当前web应用。这个对象全局唯一且被项目类所有 Servlet 共享，所有叫全局应用程序共享对象
- ServletConfig对象中维护了ServletContext对象的引用，开发人员在编写servlet时，可以通过ServletConfig.getServletContext方法获得ServletContext对象。
- 一个WEB应用中的所有Servlet共享同一个 ServletContext 对象，因此Servlet对象之间可以通过 ServletContext 对象来实现通讯。ServletContext对象通常也被称之为 context 域对象。

### 2. 作用

1. **是一个域对象**
2. **可以读取全局配置参数**
3. **可以搜索当前工程目录下面的资源文件**
4. **可以获取当前工程名字**

### 3. 获取

#### 3.1 在实现类中获取

1. 通过 GenericServlet 提供的 getServletContext() 获取

```java
ServletContext servletContext = getServletContext();
```

1. 通过 ServletConfig 提供的 getServletContext() 获取

```java
ServletContext servletContext = getServletConfig().getServletContext();
```

1. 通过HttpServletRequest获取

```java
ServletContext servletContext1 = req.getServletContext();
```

1. 通过HttpSession获取

```java
ServletContext servletContext = req.getSession().getServletContext();
```

#### 3.2 在 Spring 容器中获取

1. 在 WEB 环境下，启动 tomcat 会创建 ServletContext 对象，然后 Spring 会把这个对象注入到 Spring 容器中，我们只需要通过注解去取出来就行

```java
@Autowired
private ServletContext servletContext;
```

## 二、ServletContext 使用

### 1. 作为作用域对象

#### 1.1 作用域介绍

域对象是服务器在内存上创建的存储空间，用于在不同动态资源（servlet）之间传递与共享数据。

#### 1.2 作用域方法

- 哪个作用域对象调用方法就操作对应的作用域数据

| 作用域相关方法                      | 作用                     |
| ----------------------------------- | ------------------------ |
| Object getAttribute(“键”)           | 从中得到一个值           |
| void setAttribute(“键”, Object数据) | 向作用域中存储键值对数据 |
| void removeAttribute(“键”)          | 删除作用域种的键值对数据 |

#### 1.3 域对象的代码实现

- **实现多个 Servlet 通过 ServletContext 对象实现数据共享**，在InitServlet的Service方法中利用ServletContext对象存入需要共享的数据

```java
/*获取ServletContext对象*/  
ServletContext context = this.getServletContext();   
//存入共享的数据    
context.setAttribute("name", "haha"); 

// 在其它的Servlet中利用ServletContext对象获取共享的数据   
/*获取ServletContext对象*/  
ServletContext context = this.getServletContext();   
//获取共享的数据   
String name = context.getAttribute("name");   
System.out.println("共享的内容值是:"+name);  
```

### 2. 获取 WEB 应用的初始化参数。

#### 2.1 方法

- **根据指定的参数名获取参数值**

```java
getServletContext().getInitParameter(name);
```

- **获取所有参数名称列表**

```java
getServletContext().getInitParameterNames();
```

#### 2.2 代码实现

- web.xml 文件中的全局参数

```xml
<!-- 全局配置参数，因为不属于任何一个servlet，但是所有的servlet都可以通过servletContext读取这个数据 -->
<context-param>
  <param-name>param1</param-name>
  <param-value>value1</param-value>
</context-param>
<context-param>
  <param-name>param2</param-name>
  <param-value>value2</param-value>
</context-param>
```

- 读取 web.xml 文件中的全局参数

```java
public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   //使用servletContext读取全局配置参数数据
   //核心方法
   /*getServletContext().getInitParameter(name);//根据指定的参数名获取参数值
   getServletContext().getInitParameterNames();//获取所有参数名称列表*/
   //打印所有参数
   //1.先获取所有全局配置参数名称
   Enumeration<String> enumeration =  getServletContext().getInitParameterNames();
   //2.遍历迭代器
   while(enumeration.hasMoreElements()){
      //获取每个元素的参数名字
      String parameName = enumeration.nextElement();
      //根据参数名字获取参数值
      String parameValue = getServletContext().getInitParameter(parameName);
      //打印
      System.out.println(parameName+"="+parameValue);
   }
 }
```

### 3. 获取当前项目下的资源文件

#### 3.1 方法

- **根据相对路径获取服务器上资源的绝对路径**

```java
getServletContext().getRealPath(path),
```

- **根据相对路径获取服务器上资源的输入字节流**

```java
getServletContext().getResourceAsStream(path)
```

### 4. 获取当前项目的名字

- **获取当前项目的名字**

```java
getServletContext().getContextPath()；
```



# 演练

## 1.还是上一节课的web07项目，我们创建一个新类：TestServletContext，也是继承自GenericServlet

![image-20260927193857606](./note13-ServletContext.assets/image-20260927193857606.png)

## 2.我们用2种方法获取ServletContext对象，看看两个对象是否是同一个对象

![image-20260927194641390](./note13-ServletContext.assets/image-20260927194641390.png)



## 3.然后我们在web.xml中给TestServletContext配置路由

![image-20260927194854237](./note13-ServletContext.assets/image-20260927194854237.png)



## 4.重启服务器，在浏览器中输入http://localhost:8080/web07/context，效果如下

![image-20260927195127631](./note13-ServletContext.assets/image-20260927195127631.png)

## 5.为了测试ServletContext是否是共享的，我们创建一个User类，是一个Javabean，内容如下

```
package org.kenny.servlet;

public class User {
    private String name;
    private int age;


    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User[name=" + name + ", age=" + age + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

```

## 6.然后我们新建一个AServlet类和一个BServlet类，都是继承GenericServlet

### AServlet

```
package org.kenny.servlet;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

@WebServlet("/a")
public class AServlet extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        System.out.println("AServlet service...");
    }
}

```

### BServlet

```
package org.kenny.servlet;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

@WebServlet("/b")
public class BServlet extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        System.out.println("BServlet service...");
    }
}

```

## 7.注意，上面我们用注解来配置路由，这样子就不用在web.xml中配置了，测试一下，工作正常

![image-20260927201215831](./note13-ServletContext.assets/image-20260927201215831.png)

![image-20260927201233169](./note13-ServletContext.assets/image-20260927201233169.png)

![image-20260927201256240](./note13-ServletContext.assets/image-20260927201256240.png)

![image-20260927201326142](./note13-ServletContext.assets/image-20260927201326142.png)

## 8.我们在AServlet的service方法中创建一个User对象，并且保存到ServletContext对象中

![image-20260927201651486](./note13-ServletContext.assets/image-20260927201651486.png)

## 9.然后我们在BServlet在获取这个User对象

![image-20260927201959606](./note13-ServletContext.assets/image-20260927201959606.png)

## 10.重启服务器，然后先执行/a路由，然后在另外一个浏览器选项卡中执行/b路由，发现功能成功获取数据

![image-20260927202101200](./note13-ServletContext.assets/image-20260927202101200.png)

### 注意：只要服务器一直开着，这个值就会一直存在。ServletContext的一个应用就是可以用来统计一个网站的在线人数。

### 使用ServletContext来保存数据的情况如下

![image-20260927203116898](./note13-ServletContext.assets/image-20260927203116898.png)





# 扩展：ServletContext和ServletConfig以及Servlet的关系

***\*Servlet、ServletConfig 和 ServletContext\**** 是 Java Web 开发中紧密关联的核心接口，它们共同构成了 Servlet 的运行环境与配置体系。 

核心关系概述

- **Servlet** 是处理客户端请求、生成响应的服务器端核心组件。
- **ServletConfig** 是单个 Servlet 的**专属配置容器**，每个 Servlet 对应一个实例。
- **ServletContext** 是整个 Web 应用的**上下文环境容器**，整个 Web 应用共用一个实例。 

详细对比与层级关系

| 维度         | Servlet                              | ServletConfig                                           | ServletContext                                               |
| ------------ | ------------------------------------ | ------------------------------------------------------- | ------------------------------------------------------------ |
| **概念**     | 业务处理者，负责处理请求和响应。     | 配置携带者，封装单个 Servlet 的初始化参数。             | 全局管理者，代表整个 Web 应用的根环境。                      |
| **作用范围** | 当前请求/当前 Servlet 实例生命周期。 | 仅限当前**单个** Servlet 内部。                         | 整个 **Web 应用**的所有 Servlet 共享。                       |
| **数量关系** | 一个应用中可以有多个 Servlet。       | **一个 Servlet** 对应 **一个** ServletConfig。          | 整个 Web 应用对应**唯一一个** ServletContext。               |
| **生命周期** | 由容器创建、初始化、销毁。           | 随 Servlet 的创建而创建，销毁而销毁。                   | 随 Web 应用启动而创建，应用卸载/停止时销毁。                 |
| **主要功能** | 执行 `service()` 方法处理业务逻辑。  | 通过 `getInitParameter()` 获取当前 Servlet 的配置参数。 | 跨 Servlet 共享数据（`setAttribute`）、获取全局参数、读取应用绝对路径等。 |

三者之间的协作流程

1. **容器启动**：Web 容器（如 Tomcat）启动时，会为整个 Web 应用创建一个全局唯一的 [ServletContext](https://www.cnblogs.com/x_wukong/p/3365837.html) 对象。  
2. **初始化配置**：当容器加载某个 Servlet 时，会读取配置文件中的局部参数，为该 Servlet 创建一个专属的 `ServletConfig` 对象，并将全局的 `ServletContext` 引用存入其中。 
3. **传递对象**：容器调用 Servlet 的 `init(ServletConfig config)` 方法，将 `ServletConfig` 对象传递给 Servlet。  
4. **运行访问**：
   - Servlet 在运行过程中，可以通过自身持有的 `ServletConfig` 获取自己的专属配置。
   - Servlet 也可以通过 `getServletConfig().getServletContext()` 轻松拿到全局的 `ServletContext`，从而实现与其他 Servlet 的数据共享和交互

