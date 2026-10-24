# request 常用方法总结

## ServletRequest 接口

以下是 ServletRequest 接口中的方法：

```java
// 将指定名称的属性绑定到请求对象中
void setAttributes(String name, Object o);
// 返回请求对象中指定名称的属性值
Object getAttribute(String name);
// 移除请求对象中指定名称的属性
void removeAttribute(String name);

// 设置请求体的字符编码格式
void setCharacterEncoding(String env);

// 返回请求中指定名称的参数值（单值）
String getParameter(String name);
// 返回请求中指定名称的所有参数值（多值，如复选框）
String[] getParameterValues(String name);
// 返回包含所有请求参数的键值对映射
Map<String,String[]> getParameterMap();
// 返回请求中所有参数名称的枚举
Enumeration<String> getParameterNames();

// 返回客户端IP地址
String getRemoteAddr();
// 返回指定路径的请求分派器，用于服务器端转发
RequestDispatcher getRequestDispatcher(String path);

// 判断请求是否通过安全协议（如HTTPS）发送
boolean isSecure();
```

## HttpServletRequest 接口

以下是关于 `HttpServletRequest`接口增加的方法，HttpServletRequest 接口继承了 ServletRequest 接口：

```java
// 返回当前Web应用的上下文路径
String getContextPath();
// 返回客户端发送的所有Cookie数组
Cookie[] getCookies();
// 返回指定请求头的值
String getHeader(String name);
// 返回HTTP请求方法（如GET、POST等）
String getMethod();
// 返回请求的URI（不包含协议、主机和查询参数）
String getRequestURI();
// 返回完整的请求URL（不包括查询参数）
StringBuffer getRequestURL();
// 返回请求URL中调用Servlet的部分
String getServletPath();
// 返回当前会话，若无则创建新会话
HttpSession getSession();
```

# 演练

## 1.新建一个模块，起名：web17，然后在项目添加web支持，构建工件，然后部署到服务器

![image-20261023143436048](./note23-request 常用方法总结.assets/image-20261023143436048.png)

![image-20261023143506842](./note23-request 常用方法总结.assets/image-20261023143506842.png)

![image-20261023143534597](./note23-request 常用方法总结.assets/image-20261023143534597.png)

![image-20261023143635753](./note23-request 常用方法总结.assets/image-20261023143635753.png)

## 2.在模块根目录里面新建一个lib文件夹，把servlet-api.jar粘贴过来，然后添加为库

![image-20261023143813260](./note23-request 常用方法总结.assets/image-20261023143813260.png)

![image-20261023143847590](./note23-request 常用方法总结.assets/image-20261023143847590.png)

## 3.在src文件夹里面新建一个TestRequestServlet类，继承自HttpServlet并且实现doGet方法，设置路径为/test

![image-20261023150103889](./note23-request 常用方法总结.assets/image-20261023150103889.png)

## 4为了方便测试，我们在web文件夹里面新建一个index.html,里面有一个表单

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>测试request方法</title>
</head>
<body>
<form action="/web17/test" method="post" enctype="multipart/form-data">
    用户名: <input type="text" name="username" ><br>
    密  码： <input type="password" name="pwd"><br>
    <button>提交</button>
</form>
</body>
</html>
```

## 5.request.getParameter是用来获取查询字符串或者application/x-www-form-urlencoded形式提交的post请求数据，我们故意写错multipart/form-data看看它能否获取到数据，我们需要添加一个doPost方法，在里面调用doGet

![image-20261023152653351](./note23-request 常用方法总结.assets/image-20261023152653351.png)

## 6.结果是获取不到的

![image-20261023152746265](./note23-request 常用方法总结.assets/image-20261023152746265.png)

## 7.然后我们改为application/x-www-form-urlencoded，然后重启服务器

![image-20261023152903852](./note23-request 常用方法总结.assets/image-20261023152903852.png)

## 8.再次提交数据，

![image-20261023152957617](./note23-request 常用方法总结.assets/image-20261023152957617.png)

## 9.此时就可以获取到数据了

![image-20261023153052414](./note23-request 常用方法总结.assets/image-20261023153052414.png)

## 10.然后我们可以学习一下getParameterName()方法，它返回一个集合，我们可以遍历集合来获取值

![image-20261023154747325](./note23-request 常用方法总结.assets/image-20261023154747325.png)

## 11.注意这里有一个getParameterValues方法，它是用来获取复选框的值的。它需要一个参数，应该就是复选框的名字，为了测试这个功能，我们在表单里面添加一些复选框

![image-20261023155629099](./note23-request 常用方法总结.assets/image-20261023155629099.png)

## 12.然后我们在java代码里面获取最新复选框的值

![image-20261023155706806](./note23-request 常用方法总结.assets/image-20261023155706806.png)

## 13.重启服务器，然后提交表单数据

![image-20261023155843789](./note23-request 常用方法总结.assets/image-20261023155843789.png)

### 然后就可以获取到他的值

![image-20261023155946582](./note23-request 常用方法总结.assets/image-20261023155946582.png)

## 14.然后我们来学习getParameterMap方法，它的底层是这样子保存数据的

![image-20261023160644541](./note23-request 常用方法总结.assets/image-20261023160644541.png)

### 我们在doGet方法里面添加下面的代码

![image-20261023161336123](./note23-request 常用方法总结.assets/image-20261023161336123.png)

### 重启服务器，效果如下

![image-20261023161439117](./note23-request 常用方法总结.assets/image-20261023161439117.png)

## 15.HttpServeltRequest有扩展方法，有一些扩展方法我们已经学过了，注意getHeader方法可以获取请求头的所有属性，只需要把属性名放入，就可以获取属性值，比如这里我们获取表单提交的内容类型

![image-20261023164327668](./note23-request 常用方法总结.assets/image-20261023164327668.png)

### 效果

![image-20261023175339021](./note23-request 常用方法总结.assets/image-20261023175339021.png)

## 16.然后我们来对比学习一些getRequestUri和getRequestUrl，代码如下

![image-20261023175818215](./note23-request 常用方法总结.assets/image-20261023175818215.png)

### 效果：

![image-20261023175610324](./note23-request 常用方法总结.assets/image-20261023175610324.png)

### 注意需要搞清楚下面结果方法的效果

![image-20261023180217004](./note23-request 常用方法总结.assets/image-20261023180217004.png)



