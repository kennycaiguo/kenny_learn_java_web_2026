解决Tomcat中文乱码主要分为**控制台日志乱码**和**网页页面/请求参数乱码**两种情况。

1. 控制台日志/启动窗口乱码

Windows系统命令行默认编码是GBK，而Tomcat日志默认是UTF-8，编码不匹配导致乱码。

- **修改配置文件**：打开Tomcat根目录下的 `conf/logging.properties` 文件。
- **调整编码**：找到所有的 `java.util.logging.ConsoleHandler.encoding = UTF-8`，将其中的 `UTF-8` 改为 `GBK`（共几处都改掉），保存并重启Tomcat。
- 详细操作可参考 [腾讯云技术社区的Tomcat乱码排查指南](https://cloud.tencent.com/developer/article/2181734)。
- 网页访问/页面显示乱码

- **修改 `server.xml`**：打开 `conf/server.xml`，找到 `<Connector>` 标签，添加 `URIEncoding="UTF-8"` 属性。
- **页面声明编码**：在 HTML/JSP 文件的 `<head>` 标签内加上 `<meta charset="UTF-8">`。
- GET/POST 请求参数乱码

- **POST 请求**：在代码中通过 `request.setCharacterEncoding("UTF-8")` 设置请求体编码（需在获取参数前执行）。
- **GET 请求**：通常需要修改 `conf/server.xml` 中的 Connector 标签，配置 `URIEncoding="UTF-8"`（Tomcat 8及以上版本默认已是UTF-8）