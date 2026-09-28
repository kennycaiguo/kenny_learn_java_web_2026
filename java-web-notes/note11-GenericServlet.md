# 1.我们前面的项目都是让我们的Servlet类型实现Servlet接口，需要实现特定5个方法，但是这五个方法中只有service方法是常用的，其他方法几乎用不到，我们其实可以优化一下

# 2.我们来实现一下

## 2.1新建一个模块起名：web06

![image-20260927130939391](./note11-GenericServlet.assets/image-20260927130939391.png)

## 2.2 给这个模块添加web支持

![image-20260927131111903](./note11-GenericServlet.assets/image-20260927131111903.png)

![image-20260927131146658](./note11-GenericServlet.assets/image-20260927131146658.png)

![image-20260927131235962](./note11-GenericServlet.assets/image-20260927131235962.png)

## 2.3 然后在web06模块里面新建一个lib文件夹，把servlet-api.jar拷贝过来

![image-20260927131411381](./note11-GenericServlet.assets/image-20260927131411381.png)

## 2.4 然后把这个jar包作为库添加到classpath

![image-20260927131520345](./note11-GenericServlet.assets/image-20260927131520345.png)

![image-20260927131555630](./note11-GenericServlet.assets/image-20260927131555630.png)

## 2.5 在src文件夹里面新建一个包：org.kenny.servlet,然后在里面新建一个类，起名GenericServlet，实现Servlet接口

![image-20260927132818071](./note11-GenericServlet.assets/image-20260927132818071.png)

##  2.6然后我们把GenericServlet类改为抽象类，把他里面的service方法改为抽象方法，这样子所有他的子类都只需要实现service方法即可

![image-20260927133138406](./note11-GenericServlet.assets/image-20260927133138406.png)

## 2.7 然后我们写一个HelloServlet，继承GenericServlet，并且实现service方法，在里面输出一下文本

![image-20260927133818221](./note11-GenericServlet.assets/image-20260927133818221.png)

## 2.8.然后我们把它部署到服务器上面，使用我们已经创建好的工件web06

![image-20260927133748858](./note11-GenericServlet.assets/image-20260927133748858.png)

## 2.9然后我们需要配置一下web.xml

![image-20260927134021077](./note11-GenericServlet.assets/image-20260927134021077.png)

## 3.大家idea工具顶部的调试按钮，然后我们在浏览器在输入：http://localhost:8080/web06/hello ，效果如下

![image-20260927134233240](./note11-GenericServlet.assets/image-20260927134233240.png)

# 3.成功了，但是可以继续优化

## 3.1为了在子类中能够获取ServletConfig对象，我们需要改造一下GenericServlet里面的getServletConfig方法。返回一个ServletConfig对象，需要先创建一个私有成员变量来接收tomcat服务器调用init方法时传递进来的ServletConfig对象，然后我们就可以在getServletConfig方法里面把我们的成员变量返回即可

![image-20260927135409120](./note11-GenericServlet.assets/image-20260927135409120.png)

## 3.2 然后我们就可以在HelloServlet里面获取这个ServletConfig对象，然后获取相关的配置的值比如我们给当前的Servlet对象起的名字

![image-20260927135839361](./note11-GenericServlet.assets/image-20260927135839361.png)

## 3.3然后问题来了，假如我们在HelloServlet类里面重写了init方法，那么，在父类在获取ServletConfig对象的代码就不会被执行，那么我们就无法获取ServletConfig对象，这个问题如何解决？

### 3.3.1一个方法就是在HelloServlet的init方法里面先调用父类的init方法，再添加自己的的代码。

![image-20260927140615779](./note11-GenericServlet.assets/image-20260927140615779.png)

### 3.3.2.给GenericServlet的init方法添加一个无参重载，在有参数的init方法里面调用无参数的init方法，然后让子类重写无参的init方法

![image-20260927141456477](./note11-GenericServlet.assets/image-20260927141456477.png)

#### 其实我觉得方法一也就是重写带参数的init方法还是要好一点，就是不要忘记调用父类的init方法，idea很聪明，它会自动帮你添加调用父类方法的代码。

![image-20260927144410248](./note11-GenericServlet.assets/image-20260927144410248.png)

# 4 其实这个GenericServlet类不需要我们自己写，Jakarta ee里面就有提供，代码和上面的类似

![image-20260927150357594](./note11-GenericServlet.assets/image-20260927150357594.png)



## 官方建议我们重写无参数init方法

![image-20260927150728381](./note11-GenericServlet.assets/image-20260927150728381.png)

##  4.1建议我们以后开发servlet类直接继承GenricServlet类，而不需要直接实现Servlet接口，这样子比较方便快捷，我们可以新建一个TestServlet，继承官方的GenericServlet

![image-20260927151509829](./note11-GenericServlet.assets/image-20260927151509829.png)

## 4.2 然后我们在web.xml里面添加一个test配置

![image-20260927151726098](./note11-GenericServlet.assets/image-20260927151726098.png)

## 4.3 抽取服务器，然后在浏览器中输入：http://localhost:8080/web06/test， 发现工作正常

![image-20260927151817401](./note11-GenericServlet.assets/image-20260927151817401.png)

## 注意：路径除了可以在web.xml里面写，也可以使用注解来配置

![image-20260927152121324](./note11-GenericServlet.assets/image-20260927152121324.png)



# 5.适配器设计模式

![image-20260927152653362](./note11-GenericServlet.assets/image-20260927152653362.png)

## 5.1模式定义

![image-20260927152736712](./note11-GenericServlet.assets/image-20260927152736712.png)

## 5.2经典案例

### 日志框架适配器，代码如下

```
package com.kenny.adapter;

//目标接口
interface MyLogger{
    void log(String message);
}


//被适配类1
class Log4jLogger{
    public void logMessage(String msg){
        System.out.println("Log4jLogger: "+msg);
    }
}

// 被适配类2
class Slf4jLogger{
    public void log(String msg){
        System.out.println("slf4jLogger: "+msg);
    }
}

//适配器1
class Log4jAdapter implements MyLogger{
    private  Log4jLogger logger;

    public Log4jAdapter(Log4jLogger logger) {
        this.logger = logger;
    }

    @Override
    public void log(String message) {
        logger.logMessage(message);
    }
}

//适配器2
class Slf4jAdapter implements MyLogger{
    private  Slf4jLogger logger;

    public Slf4jAdapter(Slf4jLogger logger) {
        this.logger = logger;
    }

    @Override
    public void log(String message) {
        logger.log(message);
    }
}

public class Client {
    public static void main(String[] args) {
        MyLogger log4j = new Log4jAdapter(new Log4jLogger());
        MyLogger slf4j= new Slf4jAdapter(new Slf4jLogger());
        log4j.log("用Log4j记录日志");
        slf4j.log("用Slf4j记录日志");
    }
}

```

### 运行效果

![image-20260927161855245](./note11-GenericServlet.assets/image-20260927161855245.png)



# 6.缺省适配器设计模式

![image-20260927165418862](./note11-GenericServlet.assets/image-20260927165418862.png)

## 6.1模式定义

![image-20260927165536306](./note11-GenericServlet.assets/image-20260927165536306.png)

## 6.2 模式结构

![image-20260927165552398](./note11-GenericServlet.assets/image-20260927165552398.png)

### 对应的接口和类

![image-20260927170138514](./note11-GenericServlet.assets/image-20260927170138514.png)

## 6.3 实例代码

![image-20260927170634983](./note11-GenericServlet.assets/image-20260927170634983.png)



## 6.4与普通适配器的区别

![image-20260927170940488](./note11-GenericServlet.assets/image-20260927170940488.png)



# 7.GenericServlet是缺省适配器

![image-20260927171042791](./note11-GenericServlet.assets/image-20260927171042791.png)

## 7.1背景：Servlet接口的复杂性

![image-20260927171130464](./note11-GenericServlet.assets/image-20260927171130464.png)

## 7.2 GenericServlet的作用

![image-20260927171301459](./note11-GenericServlet.assets/image-20260927171301459.png)

## 7.3 简化子类的实现

![image-20260927171341141](./note11-GenericServlet.assets/image-20260927171341141.png)

![image-20260927171417648](./note11-GenericServlet.assets/image-20260927171417648.png)



# 8.GenericServlet源码剖析

## 8.1 GenericServlet源码如下

```
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jakarta.servlet;

import java.io.IOException;
import java.util.Enumeration;

/**
 * Defines a generic, protocol-independent servlet. To write an HTTP servlet for use on the Web, extend
 * {@link jakarta.servlet.http.HttpServlet} instead.
 * <p>
 * <code>GenericServlet</code> implements the <code>Servlet</code> and <code>ServletConfig</code> interfaces.
 * <code>GenericServlet</code> may be directly extended by a servlet, although it's more common to extend a
 * protocol-specific subclass such as <code>HttpServlet</code>.
 * <p>
 * <code>GenericServlet</code> makes writing servlets easier. It provides simple versions of the lifecycle methods
 * <code>init</code> and <code>destroy</code> and of the methods in the <code>ServletConfig</code> interface.
 * <code>GenericServlet</code> also implements the <code>log</code> method, declared in the <code>ServletContext</code>
 * interface.
 * <p>
 * To write a generic servlet, you need only override the abstract <code>service</code> method.
 */
public abstract class GenericServlet implements Servlet, ServletConfig, java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private transient ServletConfig config;

    /**
     * Does nothing. All of the servlet initialization is done by one of the <code>init</code> methods.
     */
    public GenericServlet() {
        // NOOP
    }

    /**
     * Called by the servlet container to indicate to a servlet that the servlet is being taken out of service. See
     * {@link Servlet#destroy}.
     */
    @Override
    public void destroy() {
        // NOOP by default
    }

    /**
     * Returns a <code>String</code> containing the value of the named initialization parameter, or <code>null</code> if
     * the parameter does not exist. See {@link ServletConfig#getInitParameter}.
     * <p>
     * This method is supplied for convenience. It gets the value of the named parameter from the servlet's
     * <code>ServletConfig</code> object.
     *
     * @param name a <code>String</code> specifying the name of the initialization parameter
     *
     * @return String a <code>String</code> containing the value of the initialization parameter
     */
    @Override
    public String getInitParameter(String name) {
        return getServletConfig().getInitParameter(name);
    }

    /**
     * Returns the names of the servlet's initialization parameters as an <code>Enumeration</code> of
     * <code>String</code> objects, or an empty <code>Enumeration</code> if the servlet has no initialization
     * parameters. See {@link ServletConfig#getInitParameterNames}.
     * <p>
     * This method is supplied for convenience. It gets the parameter names from the servlet's
     * <code>ServletConfig</code> object.
     *
     * @return Enumeration an enumeration of <code>String</code> objects containing the names of the servlet's
     *             initialization parameters
     */
    @Override
    public Enumeration<String> getInitParameterNames() {
        return getServletConfig().getInitParameterNames();
    }

    /**
     * Returns this servlet's {@link ServletConfig} object.
     *
     * @return ServletConfig the <code>ServletConfig</code> object that initialized this servlet
     */
    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    /**
     * Returns a reference to the {@link ServletContext} in which this servlet is running. See
     * {@link ServletConfig#getServletContext}.
     * <p>
     * This method is supplied for convenience. It gets the context from the servlet's <code>ServletConfig</code>
     * object.
     *
     * @return ServletContext the <code>ServletContext</code> object passed to this servlet by the <code>init</code>
     *             method
     */
    @Override
    public ServletContext getServletContext() {
        return getServletConfig().getServletContext();
    }

    /**
     * Returns information about the servlet, such as author, version, and copyright. By default, this method returns an
     * empty string. Override this method to have it return a meaningful value. See {@link Servlet#getServletInfo}.
     *
     * @return String information about this servlet, by default an empty string
     */
    @Override
    public String getServletInfo() {
        return "";
    }

    /**
     * Called by the servlet container to indicate to a servlet that the servlet is being placed into service. See
     * {@link Servlet#init}.
     * <p>
     * This implementation stores the {@link ServletConfig} object it receives from the servlet container for later use.
     * When overriding this form of the method, call <code>super.init(config)</code>.
     *
     * @param config the <code>ServletConfig</code> object that contains configuration information for this servlet
     *
     * @exception ServletException if an exception occurs that interrupts the servlet's normal operation
     *
     * @see UnavailableException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
        this.init();
    }

    /**
     * A convenience method which can be overridden so that there's no need to call <code>super.init(config)</code>.
     * <p>
     * Instead of overriding {@link #init(ServletConfig)}, simply override this method and it will be called by
     * <code>GenericServlet.init(ServletConfig config)</code>. The <code>ServletConfig</code> object can still be
     * retrieved via {@link #getServletConfig}.
     *
     * @exception ServletException if an exception occurs that interrupts the servlet's normal operation
     */
    public void init() throws ServletException {
        // NOOP by default
    }

    /**
     * Writes the specified message to a servlet log file, prepended by the servlet's name. See
     * {@link ServletContext#log(String)}.
     *
     * @param message a <code>String</code> specifying the message to be written to the log file
     */
    public void log(String message) {
        getServletContext().log(getServletName() + ": " + message);
    }

    /**
     * Writes an explanatory message and a stack trace for a given <code>Throwable</code> exception to the servlet log
     * file, prepended by the servlet's name. See {@link ServletContext#log(String, Throwable)}.
     *
     * @param message a <code>String</code> that describes the error or exception
     * @param t       the <code>java.lang.Throwable</code> error or exception
     */
    public void log(String message, Throwable t) {
        getServletContext().log(getServletName() + ": " + message, t);
    }

    /**
     * Called by the servlet container to allow the servlet to respond to a request. See {@link Servlet#service}.
     * <p>
     * This method is declared abstract so subclasses, such as <code>HttpServlet</code>, must override it.
     *
     * @param req the <code>ServletRequest</code> object that contains the client's request
     * @param res the <code>ServletResponse</code> object that will contain the servlet's response
     *
     * @exception ServletException if an exception occurs that interferes with the servlet's normal operation occurred
     * @exception IOException      if an input or output exception occurs
     */
    @Override
    public abstract void service(ServletRequest req, ServletResponse res) throws ServletException, IOException;

    /**
     * Returns the name of this servlet instance. See {@link ServletConfig#getServletName}.
     *
     * @return the name of this servlet instance
     */
    @Override
    public String getServletName() {
        return config.getServletName();
    }
}

```





# 9.GenericServlet的使用

### 参考上面的代码

















# 扩展： Java适配器设计模型解析

Java适配器模式（Adapter Pattern）是一种结构型设计模式，它能将一个类的接口转换成客户期望的另一个接口，让原本因接口不兼容而无法一起工作的类能够协同工作。 [[1](https://blog.csdn.net/le_duoduo/article/details/145621215), [2](https://blog.csdn.net/swadian2008/article/details/126197426)]

核心角色

- **目标接口（Target）**：客户端期望调用的特定接口。
- **适配者类（Adaptee）**：需要被适配的现存组件或老接口类。
- **适配器类（Adapter）**：通过实现目标接口并包装（或继承）适配者类，将目标请求转化成对适配者相应接口的调用。 [[1](https://blog.csdn.net/swadian2008/article/details/126197426)]

两种实现方式

1. 对象适配器（推荐，使用组合）

对象适配器通过在适配器内部持有一个适配者对象的实例来完成转接。这种做法符合对象组合原则，更加灵活。 [[1](https://blog.csdn.net/le_duoduo/article/details/145621215), [2](https://blog.csdn.net/swadian2008/article/details/126197426)]

java

```
// 目标接口
interface Target {
    void request();
}

// 适配者类
class Adaptee {
    public void specificRequest() {
        System.out.println("适配者中的真实业务逻辑被调用");
    }
}

// 对象适配器
class Adapter implements Target {
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.specificRequest(); // 委托调用适配者的方法
    }
}
```

请谨慎使用此类代码。

2. 类适配器（使用继承）

类适配器通过多重继承或实现目标接口并继承适配者类来实现。由于Java是单继承，因此要求目标必须是接口，限制较多。 [[1](https://blog.csdn.net/le_duoduo/article/details/145621215)]

java

```
class Adapter extends Adaptee implements Target {
    @Override
    public void request() {
        specificRequest(); // 直接调用父类方法
    }
}
```

请谨慎使用此类代码。

常见应用场景与JDK源码

- **系统升级与遗留代码复用**：当需要复用一些老的类库、组件，但其接口和当前系统架构规范不一致时。 [[1](https://refactoringguru.cn/design-patterns/adapter/java/example), [2](https://blog.csdn.net/le_duoduo/article/details/145621215)]
- **JDK中的经典应用**：
  - `java.util.Arrays#asList()`：把数组适配成符合 `List` 接口的对象。
  - `java.io.InputStreamReader(InputStream)`：将字节流（InputStream）适配为字符流（Reader）。











# 扩展： ServletConfig里面都有哪些有用的信息

AI 概览

在 Java Web 开发中，**`ServletConfig` 对象主要用于向特定的 Servlet 传递初始化配置参数**。当容器（如 Tomcat）初始化一个 Servlet 时，会创建一个 `ServletConfig` 对象并将其传递给 Servlet 的 `init(ServletConfig config)` 方法。

`ServletConfig` 接口中一共只定义了 4 个方法，它们包含了以下核心有用信息：

1. Servlet 的注册名称 (ServletName)

- **对应方法**：`getServletName()`
- **返回内容**：返回该 Servlet 在 `web.xml` 中配置的 `<servlet-name>`，或者通过 `@WebServlet(name="...")` 注解指定的名称。
- **主要用途**：常用于日志记录或调试，以便知道当前运行的是哪个具体的 Servlet 实例。
- 初始化参数 (InitParameters)

这是 `ServletConfig` **最核心、最常用**的功能。它允许你在不修改 Java 源代码的情况下，动态调整单个 Servlet 的行为。

- **对应方法**：

  - `getInitParameter(String name)`：根据参数名获取对应的字符串值。
  - `getInitParameterNames()`：返回一个 `Enumeration<String>`，包含该 Servlet 的所有初始化参数名。

- **主要用途**：

  - 存放特定 Servlet 所需的敏感信息或配置，例如**文件上传的临时保存路径**、**特定的编码格式**、**分页组件的默认每页显示条数**等。

- **配置示例 (`web.xml`)**：

  

  xml

  ```
  <servlet>
      <servlet-name>UploadServlet</servlet-name>
      <servlet-class>com.example.UploadServlet</servlet-class>
      <init-param>
          <param-name>saveDir</param-name>
          <param-value>/var/www/uploads</param-value>
      </init-param>
  </servlet>
  ```

  请谨慎使用此类代码。

- Servlet 上下文对象 (ServletContext)

- **对应方法**：`getServletContext()`
- **返回内容**：返回当前 Web 应用的 `ServletContext` 对象。
- **主要用途**：
  - 虽然属于另一个对象，但通过 `ServletConfig` 间接获取。
  - `ServletContext` 代表整个 Web 应用的环境，可以用来获取**全局配置参数**（Context Init Parameters）、在不同 Servlet 之间**共享全局数据**、或者获取服务器文件的**绝对路径**。

💡 核心对比：ServletConfig vs ServletContext

为了防止混淆，可以通过下表直接对比它们的用途：

| 特性         | `ServletConfig`                                            | `ServletContext`                                           |
| ------------ | ---------------------------------------------------------- | ---------------------------------------------------------- |
| **作用范围** | **局部**：仅对配置了它的那一个 Servlet 有效。              | **全局**：对整个 Web 应用中的所有 Servlet、Filter 都有效。 |
| **生命周期** | 随所属 Servlet 的销毁而销毁。                              | 随整个 Web 应用的停止而销毁。                              |
| **典型应用** | 某个 Servlet 独享的配置（如当前 Servlet 的文件保存路径）。 | 整个应用共享的资源（如数据库连接池、全局过滤器配置）。     |