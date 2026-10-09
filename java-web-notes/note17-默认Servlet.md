# 默认 Servlet

## 1.什么是默认 Servlet

JavaWeb 开发中，任何一个 Web 服务器都会内置一个默认的 Servlet

在 Tomcat 中，默认的 Servlet 类名是：`org.apache.catalina.servlets.DefaultServlet`，它是服务器内置的，专门用来处理静态资源（HTML,CSS,JS,图片等静态资源）和兜底返回 404 的默认处理器。

它被配置在服务器的 web.xml 文件中：`%CATALINA_HOME%/conf/web.xml`

```xml
<servlet>
    <servlet-name>default</servlet-name>
    <servlet-class>org.apache.catalina.servlets.DefaultServlet</servlet-class>
    <init-param>
        <param-name>debug</param-name>
        <param-value>0</param-value>
    </init-param>
    <init-param>
        <param-name>listings</param-name>
        <param-value>false</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
    <servlet-name>default</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

通过配置可以看到，默认 Servlet 的映射路径是 `/`

\*\*这个 \*\*<code>**/**</code>**会自动拦截以下的请求：**

1. 访问所有静态资源时，会走默认的 Servlet，默认的 Servlet 负责给你找静态资源并响应给浏览器。如果找不到这个静态资源，默认 Servlet 会直接响应 404 给前端。
2. 访问的资源不存在时，会走默认的 Servlet，默认 Servlet 会直接响应 404 给前端。

**注意：默认 Servlet 又叫做兜底处理器。**

## 2.自定义默认 Servlet 会怎样

如果自定义默认 Servlet，如下：

```xml
<servlet>
    <servlet-name>defaultServlet</servlet-name>
    <servlet-class>com.jkweilai.servlet.DefaultServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>defaultServlet</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

如果你的 `com.jkweilai.servlet.DefaultServlet`中什么也没写，就会导致无法正常访问静态资源，以及就算访问的资源不存在，也不会报 404 的提示信息了。

## 尽量不要自定义默认 Servlet，也就是说定义资源时不要占用 /**请求路径。**这是自找麻烦，有人帮你兜底了，你还在瞎折腾干什么？！！



# 扩展：DefaultServlet的用途

在 Apache Tomcat 中，`DefaultServlet` 的主要作用是**处理静态资源请求**（如 HTML、CSS、JavaScript、图片等），以及当一个请求在应用中**没有被其他 Servlet 匹配时**，作为默认的兜底处理器。

核心功能

- **处理静态资源**：当用户请求 `.html`、`.css`、`.js`、`.png`、`.jpg` 等静态文件时，这些请求通常不会交给开发者写的业务 Servlet，而是由 `DefaultServlet` 通过 I/O 读取服务器上的文件并返回给客户端。
- **兜底匹配（默认路径 `/`）**：在 Tomcat 的全局配置文件 `$CATALINA_BASE/conf/web.xml` 中，`DefaultServlet` 被映射到 `url-pattern` 为 `/` 的路径上。这意味着所有未被其他显式定义的 Servlet 匹配到的请求，都会流转到这里。 
- **处理欢迎文件（Welcome Files）**：如果用户请求的是一个目录（例如 `/` 或 `/images/`），`DefaultServlet` 会去查找该目录下是否配置了欢迎页面（如 `index.html`、`index.jsp` 等），若存在则返回该文件。
- **支持文件上传与目录列表**：
  - 支持标准的 HTTP 操作，如 `GET`、`HEAD`，以及部分条件下的 `PUT` 和 `DELETE` 请求。
  - 可以通过配置参数（如 `listings=true`）决定是否允许浏览器直接查看目录结构（默认通常为关闭状态）。
- **自动识别 MIME 类型**：它会根据请求文件的扩展名，自动推断并设置响应头的 `Content-Type`（例如将 `.js` 设为 `application/javascript`）。

常见应用场景（如 Spring MVC 配置）

在使用 Spring MVC 时，如果将 `DispatcherServlet` 的 `url-pattern` 设为 `/`（拦截所有请求），会导致静态资源（CSS、JS、图片）也被拦截从而报 404。

此时通常需要配置 `<mvc:default-servlet-handler/>`：

- 它的底层原理，就是**将这些静态资源的请求转交给 Tomcat 容器中内置的 `DefaultServlet`** 来处理