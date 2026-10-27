# 关于 Tomcat 标准输出流乱码问题

**当前环境信息如下：**

1. win10 简体中文环境
2. Tomcat 版本： 10.1.48
3. IDEA 版本：2025.2.2
4. JDK 版本：21

**前提是：**

1. IDEA 工具所有涉及到字符编码配置方面都已经配置了 UTF-8
2. Tomcat 服务器的 config/logging.properties 中的字符编码方式也都配置了 UTF-8

\*\*问题是：\*\*在这种情况下，在 Servlet 中使用 `System.out.println()`打印中文时仍然会出现乱码。

**什么原因？**

* <font style="color:rgb(15, 17, 21);">根本原因：Tomcat容器的标准输出流(System.out)编码与IDEA控制台显示编码不一致。</font>
* <font style="color:rgb(15, 17, 21);">具体来说：Tomcat的System.out默认使用GBK编码输出，IDEA控制台期望接收UTF-8编码的文本，编码不匹配导致中文显示为乱码。</font>
* <font style="color:rgb(15, 17, 21);">在 </font><code><font style="color:rgb(15, 17, 21);">Servlet</font></code><font style="color:rgb(15, 17, 21);">程序中可以通过 </font><code>**<font style="color:#DF2A3F;">System.out.charset()</font>**</code>**<font style="color:#DF2A3F;">方法</font>**<font style="color:rgb(15, 17, 21);">来获取 Tomcat 容器的标准输出流的字符编码方式，结果是 GBK。</font>
* **<font style="color:rgb(15, 17, 21);">但需要注意的是</font>**<font style="color:rgb(15, 17, 21);">：脱离了 Tomcat 服务器之后，自己编写一个 main 方法，</font><code><font style="color:rgb(15, 17, 21);">System.out.charset()</font></code><font style="color:rgb(15, 17, 21);">方法获取的标准输出流的字符编码方式为 </font><code><font style="color:rgb(15, 17, 21);">UTF-8</font></code><font style="color:rgb(15, 17, 21);">。因此自己写的 main 方法输出中文不会存在问题。导致以上乱码的根本原因是 </font><code><font style="color:rgb(15, 17, 21);">Tomcat</font></code><font style="color:rgb(15, 17, 21);">服务器中的 </font><code><font style="color:rgb(15, 17, 21);">System.out</font></code><font style="color:rgb(15, 17, 21);">标准输出流采用的是和操作系统一致的 GBK。</font>

<font style="color:rgb(15, 17, 21);"></font>

\*\*怎么解决？在 Tomcat 服务器的 \*\*<code>**VM options**</code>**配置中添加这个配置：**<font style="color:rgb(15, 17, 21);background-color:rgb(235, 238, 242);">-Dstdout.encoding=UTF-8</font>

![1762937114583-cb3378ea-1d25-4a48-a6d3-d45eec9c0167.png](./关于 Tomcat 标准输出流乱码问题.assets/1762937114583-cb3378ea-1d25-4a48-a6d3-d45eec9c0167-156462.png)

**另外，在这种环境下标准错误流通常也需要设置一下，不然在出现的异常中异常的描述信息是中文的话也会乱码，可以通过 **<code>**<font style="color:#080808;background-color:#ffffff;">System.err.charset()</font>**</code>**<font style="color:#080808;background-color:#ffffff;">代码来查看标准错误流的字符编码方式，它应该也是 GBK。因此也需要在 </font>**<code>**<font style="color:#080808;background-color:#ffffff;">vm options</font>**</code>**<font style="color:#080808;background-color:#ffffff;">中进行配置：</font>**<code>**<font style="color:#080808;background-color:#ffffff;">-Dstderr.encoding=UTF-8</font>**</code>

# 