# 会话管理

![1757681056420-39d1bc52-55fe-4f3b-8183-b4d7ba79b166.jpeg](./note22-会话管理.assets/1757681056420-39d1bc52-55fe-4f3b-8183-b4d7ba79b166-081890.jpeg)

## 项目存在的问题

部门管理系统现在任何人都可以访问，只要知道请求路径，假设知道部门列表页面的请求路径 `http://localhost:8080/dept/list`

![1749450392879-651f3ccc-912e-447f-9bd5-ec503377281f.png](./note22-会话管理.assets/1749450392879-651f3ccc-912e-447f-9bd5-ec503377281f-152542.png)

如果知道删除部门的请求路径 `http://localhost:8080/dept/delete?deptno=20`，这个部门将会删除。

因此系统应该提供登录功能，只有登录的人（管理员：有权限的人）才能够进行相关的操作。用户登录功能也是一个系统最基本的功能。

登录功能实现的关键点：

1. 正确的用户名和密码，才能登录成功。
2. 登录成功后应该将用户登录的状态保存住。

## 登录功能的实现

分析实现步骤：

1. 需要一张用户表 `t_user`存储用户名和密码。
2. 密码需要以 `Argon2（阿尔戈恩）`加密方式存储。
3. 需要提供一个登录页面。
4. 用户登录页面上填写用户名和密码，提交表单，后端 Servlet 获取用户名和密码，验证是否正确，如果正确登录成功，跳转到部门列表页面。如果错误登录失败，重新跳转到登录页面，并提示登录失败。

### 用户表创建

`t_user`表：

![1749453368431-dc296051-f4eb-4207-a9b4-29f1a8e81252.png](./note22-会话管理.assets/1749453368431-dc296051-f4eb-4207-a9b4-29f1a8e81252-503483.png)

```sql
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'hash加密',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;
```

### Argon2 加密算法

**Argon2 是一种抗 GPU/ASIC 破解的现代密码哈希算法，通过可调内存和计算成本抵御暴力攻击，被公认为当前最安全的密码存储方案之一。**（核心特点：内存困难型、抗硬件加速破解、密码哈希大赛冠军）

提供一个工具 `Argon2PasswordUtil`，将这个工具类放到 dept 项目中：

```java
package com.jkweilai.dept.utils;

import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class Argon2PasswordUtil {

    // Argon2 参数配置（可根据需求调整）
    private static final int ITERATIONS = 3;          // 迭代次数
    private static final int MEMORY_COST = 65536;     // 内存成本（64MB）
    private static final int PARALLELISM = 4;         // 并行度
    private static final int SALT_LENGTH = 16;        // 盐值长度（16字节）
    private static final int HASH_LENGTH = 32;        // 哈希输出长度（32字节）

    /**
     * 生成 Argon2 哈希密码（含盐值）
     */
    public static String hashPassword(String password) {
        // 1. 生成随机盐值
        byte[] salt = generateSalt();

        // 2. 配置 Argon2 参数
        Argon2Parameters.Builder builder = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
                .withSalt(salt)
                .withIterations(ITERATIONS)
                .withMemoryAsKB(MEMORY_COST)
                .withParallelism(PARALLELISM);

        // 3. 生成哈希
        Argon2BytesGenerator generator = new Argon2BytesGenerator();
        generator.init(builder.build());
        byte[] hash = new byte[HASH_LENGTH];
        generator.generateBytes(password.getBytes(StandardCharsets.UTF_8), hash);

        // 4. 返回格式：算法$版本$参数$盐$哈希（Base64编码）
        return "argon2id" +
                "$v=19" +
                "$m=" + MEMORY_COST + ",t=" + ITERATIONS + ",p=" + PARALLELISM +
                "$" + Base64.getEncoder().encodeToString(salt) +
                "$" + Base64.getEncoder().encodeToString(hash);
    }

    /**
     * 验证密码是否正确
     */
    public static boolean verifyPassword(String password, String hashedPassword) {
        // 1. 解析存储的哈希字符串
        String[] parts = hashedPassword.split("\\$");
        if (parts.length != 5 || !parts[0].equals("argon2id")) {
            throw new IllegalArgumentException("Invalid Argon2 hash format");
        }

        // 2. 提取参数
        byte[] salt = Base64.getDecoder().decode(parts[3]);
        byte[] storedHash = Base64.getDecoder().decode(parts[4]);

        // 3. 重新计算哈希
        Argon2Parameters.Builder builder = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
                .withSalt(salt)
                .withIterations(ITERATIONS)
                .withMemoryAsKB(MEMORY_COST)
                .withParallelism(PARALLELISM);

        Argon2BytesGenerator generator = new Argon2BytesGenerator();
        generator.init(builder.build());
        byte[] computedHash = new byte[storedHash.length];
        generator.generateBytes(password.getBytes(StandardCharsets.UTF_8), computedHash);

        // 4. 比较哈希值
        return constantTimeEquals(storedHash, computedHash);
    }

    /**
     * 生成随机盐值
     */
    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return salt;
    }

    /**
     * 安全比较字节数组（防止时序攻击）
     */
    private static boolean constantTimeEquals(byte[] a, byte[] b) {
        if (a.length != b.length) {
            return false;
        }
        int result = 0;
        for (int i = 0; i < a.length; i++) {
            result |= a[i] ^ b[i];
        }
        return result == 0;
    }
}
```

说明：

1. `hashPassword`方法的作用是：将明文经过 Argon2 算法加密生成密文。
2. `verifyPassword`方法的作用是：验证密码是否正确。

提示：如果需要使用 Argon2 加密算法，需要引入相关的 java jar 包，如下：

![1749454867679-ad1eccf2-bbaa-4018-b2e0-a12022b4df1a.png](./note22-会话管理.assets/1749454867679-ad1eccf2-bbaa-4018-b2e0-a12022b4df1a-312857.png)

将这个 jar 包拷贝到 `WEB-INF/lib`目录下，并且将其添加到 classpath 当中。

### 生成密文存储到数据库

在 `Argon2PasswordUtil`中临时添加一个 main 方法，在该方法中为密码 `Abc_%*123-dEf`加密：

```java
// 临时生成密文
public static void main(String[] args) {
    String s = hashPassword("Abc_%*123-dEf");
    System.out.println(s);
}
```

执行这个 main 方法，在控制台上会打印密文，将密文插入的数据库表当中。

dept 项目的管理员用户名是：`admin`，密码是：`Abc_%*123-dEf`

### 登录页面的开发

把之前开发静态网站时的 `index.html`代码拷贝过来放到 `WEB-INF/templates/index.html`文件中：记得把 css 单独写到 `css/index.css`文件中。

```html
<!DOCTYPE html>
<html lang="zh-CN" xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>部门管理系统 - 登录</title>
  <link rel="stylesheet" th:href="@{/css/index.css}">
</head>
<body>
<div class="login-container">
  <div class="login-header">
    <h1>部门管理系统</h1>
    <p>请输入您的凭据以继续</p>
  </div>
  <form th:action="@{/login}" method="post">
    <div class="form-group">
      <label for="username">用户名</label>
      <input type="text" id="username" name="username" placeholder="请输入用户名" required>
    </div>
    <div class="form-group">
      <label for="password">密码</label>
      <input type="password" id="password" name="password" placeholder="请输入密码" required>
    </div>
    <button type="submit" class="login-btn">登录</button>
  </form>
  <div class="footer">
    <p>© 2025 部门管理系统 - 版权所有</p>
  </div>
</div>
</body>
</html>
```

### 登录页面的展示

编写 `IndexServlet`跳转到 `WEB-INF/templates/index.html`页面，完成登录页面的展示：

```java
package com.jkweilai.dept.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("template", "index");
        req.getRequestDispatcher("/view").forward(req, resp);
    }
}
```

### 登录请求处理

用户在登录页面输入用户名和密码，点击登录按钮，提交表单数据，后端 `LoginServlet`接收登录信息，连接数据库验证用户名密码是否正确，如果正确，则重定向到部门列表页面，如果登录失败，则将失败信息存储到 request 域中，重新跳回登录页面。在登录页面展示登录失败的信息。

```java
package com.jkweilai.dept.servlets;

import com.jkweilai.dept.utils.Argon2PasswordUtil;
import com.jkweilai.dept.utils.DbUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 连接数据库校验用户名和密码
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean loginSuccess = false;
        try {
            conn = DbUtils.getConnection();
            String sql = "select password from t_user where username = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if(rs.next()) {
                String hashPassword = rs.getString("password");
                if(Argon2PasswordUtil.verifyPassword(password, hashPassword)) {
                    // 登录成功
                    loginSuccess = true;
                    // 重定向到部门列表
                    response.sendRedirect(request.getContextPath() + "/list");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(conn, ps, rs);
        }
        // 登录失败，转发到登录页面
        if(!loginSuccess) {
            request.setAttribute("errorMsg", "用户名不存在或密码错误！");
            request.setAttribute("template", "index");
            request.getRequestDispatcher("/view").forward(request, response);
        }
    }
}
```

当登录失败时，页面显示的效果如下：

![1749515601938-27efefc2-7ea2-4efb-ad54-49c353d91b82.png](./note22-会话管理.assets/1749515601938-27efefc2-7ea2-4efb-ad54-49c353d91b82-237073.png)

当登录成功时，会跳转到部门列表页面：

![1749515657512-aabcfec2-b91d-4f2f-bba3-66ecea8ffa91.png](./note22-会话管理.assets/1749515657512-aabcfec2-b91d-4f2f-bba3-66ecea8ffa91-911572.png)

到此为止，登录功能基本的逻辑已经实现了。

### 当前登录功能有用吗

虽然登录功能已经实现，但是这个登录功能起作用了吗？

测试一下，可以把浏览器关闭，重新打开浏览器，在不登录的情况下，直接访问部门列表页面，看看能不能访问。

经过测试，大家应该看到了，部门列表页面在没有登录的情况下，仍然是可以访问的，可见目前的登录功能没有起到作用。

我们要达到的效果是：要想使用系统中的部门维护相关功能，必须是在已经登录的状态下。如果已经登录了，可以继续访问，如果没有登录则需要跳转到登录页面要求用户登录。

那我们应该怎么判断用户是已经登录了还是没有登录呢？也就是说，我们怎么判断用户当前的登录状态呢？

再换句话说，在 web 项目中我们应该怎么保存用户的登录状态呢？这就需要使用 session 机制了。

## session 机制

### <font style="color:rgb(64, 64, 64);">HTTP 协议的无状态性</font>

<font style="color:rgb(64, 64, 64);">HTTP 协议在设计之初是</font>**<font style="color:rgb(64, 64, 64);">无状态的</font>**<font style="color:rgb(64, 64, 64);">：</font>

1. **<font style="color:rgb(64, 64, 64);">每个请求相互独立</font>**<font style="color:rgb(64, 64, 64);">：服务器默认不会记住之前的请求（例如：第一次请求登录，第二次请求查看购物车时，服务器不知道这两个请求来自同一用户）</font>
2. **<font style="color:rgb(64, 64, 64);">简单高效</font>**<font style="color:rgb(64, 64, 64);">：无状态设计降低了服务器资源消耗，符合早期 Web 的静态页面需求</font>

**<font style="color:rgb(64, 64, 64);">但现实业务需要状态！</font>**<font style="color:rgb(64, 64, 64);">\
</font><font style="color:rgb(64, 64, 64);">例如：用户登录后，后续操作（如加购商品、支付）都需要知道是谁在操作。</font>

<font style="color:rgb(64, 64, 64);">session 机制在 HTTP 无状态的基础上</font>**<font style="color:rgb(64, 64, 64);">模拟出"有状态"。</font>**

### 什么是 session

**<font style="color:rgb(64, 64, 64);">session（会话） 并不是 JavaWeb 独有的机制</font>**<font style="color:rgb(64, 64, 64);">，是一种广泛应用于 Web 开发的</font>**<font style="color:rgb(64, 64, 64);">会话管理技术</font>**<font style="color:rgb(64, 64, 64);">，几乎所有服务端技术（如 PHP、ASP.NET、Python Django/Flask等）都实现了类似的 session 机制。</font>

<font style="color:rgb(64, 64, 64);">session是服务器端用来跟踪用户状态的一种机制。在 JavaWeb 开发中，session 指的是 </font><code>**<font style="color:rgb(64, 64, 64);background-color:rgb(236, 236, 236);">HttpSession</font>**</code><font style="color:rgb(64, 64, 64);"> 对象，它代表了服务器与客户端（通常是浏览器）之间的一次会话。</font>

**<font style="color:rgb(64, 64, 64);">"一次会话"</font>**<font style="color:rgb(64, 64, 64);">指的是 </font>**<font style="color:rgb(64, 64, 64);">从用户首次访问服务器到结束交互的完整过程</font>**<font style="color:rgb(64, 64, 64);">。</font>

<font style="color:rgb(64, 64, 64);">session 是存储在 web 服务器端的，在 JavaWeb 开发中，Tomcat 服务器中会自动维护一个 session 列表，session 对象存储在 session 列表中，每一个 session 对象都会对应一个 sessionId。</font>

### session 的获取

在 JavaWeb 开发中，通过以下代码来获取服务器端的 session 对象：

第一种方式：获取该请求对应的 session 对象，如果没有该请求关联的 session 对象，则创建一个新的 session 对象与当前请求关联。

```java
HttpSession session = request.getSession();
```

第二种方式：获取该请求对应的 session 对象，如果没有该请求关联的 session 对象，则返回 null。

```java
HttpSession session = request.getSession(false);
```

### session 的工作原理

1. <font style="color:rgb(64, 64, 64);">当客户端第一次访问服务器时，执行到 </font><code><font style="color:rgb(64, 64, 64);">HttpSession session = request.getSession();</font></code><font style="color:rgb(64, 64, 64);">代码时，服务器会创建该用户所属的 session 对象，并且为该 session 对象生成一个 sessionId。</font>
2. <font style="color:rgb(64, 64, 64);">这个 sessionId 会通过 Cookie 返回给客户端</font>
   1. <font style="color:rgb(64, 64, 64);">如果浏览器禁用 Cookie 的话，可以通过</font>**<font style="color:rgb(64, 64, 64);">URL 重写的方式</font>**<font style="color:rgb(64, 64, 64);">将 sessionId 响应给客户端，不过 URL 重写机制会导致开发繁琐，因此大多数网站是要求客户不要禁用 Cookie 的</font>
3. <font style="color:rgb(64, 64, 64);">客户端在</font>**<font style="color:#DF2A3F;">后续</font>**<font style="color:rgb(64, 64, 64);">请求中会携带这个 sessionId</font>
4. <font style="color:rgb(64, 64, 64);">服务器通过 sessionId 识别并返回该用户所属的 session 对象</font>

<font style="color:rgb(64, 64, 64);"></font>

<font style="color:rgb(64, 64, 64);">编写程序，可以测试一下，发送请求时打开浏览器的网络面板，查看具体的请求报文和响应报文：</font>

<font style="color:rgb(64, 64, 64);">第一次发送请求时，客户端不会携带 sessionId，因为客户端中没有 sessionId，但是服务器端会响应 sessionId，因此在请求报文中没有 sessionId，但是在响应报文中应该存在 sessionId</font>

<font style="color:rgb(64, 64, 64);">请求头：</font>

![1749521891896-01187993-9e7b-43bf-aa47-467a2fe794e0.png](./note22-会话管理.assets/1749521891896-01187993-9e7b-43bf-aa47-467a2fe794e0-472799.png)

响应头：

![1749521935384-77267fb4-55f9-43aa-aab0-07110bf82933.png](./note22-会话管理.assets/1749521935384-77267fb4-55f9-43aa-aab0-07110bf82933-993496.png)

<font style="color:rgb(64, 64, 64);">第二次发送请求时，客户端会携带 sessionId，因为客户端中已经存在 sessionId 了，服务器不会再生成新的 session 对象，进而不会生成新的 sessionId，因此响应报文中不应该存在 sessionId</font>

<font style="color:rgb(64, 64, 64);">请求头：</font>

![1749521974147-43eb3b46-c1e8-4573-ad40-dd27c2180e6c.png](./note22-会话管理.assets/1749521974147-43eb3b46-c1e8-4573-ad40-dd27c2180e6c-530774.png)

<font style="color:rgb(64, 64, 64);">响应头：</font>

![1749521983614-28d4f20a-93af-483e-abf3-899fdc248481.png](./note22-会话管理.assets/1749521983614-28d4f20a-93af-483e-abf3-899fdc248481-138265.png)

### <font style="color:rgb(64, 64, 64);">session 的主要用途</font>

1. **<font style="color:rgb(64, 64, 64);">用户身份认证</font>**<font style="color:rgb(64, 64, 64);">：存储登录状态，记录用户是否已登录</font>
2. **<font style="color:rgb(64, 64, 64);">数据共享</font>**<font style="color:rgb(64, 64, 64);">：在同一用户的不同请求间共享数据</font>
3. **<font style="color:rgb(64, 64, 64);">购物车功能</font>**<font style="color:rgb(64, 64, 64);">：电商网站中存储用户选择的商品</font>
4. **<font style="color:rgb(64, 64, 64);">用户偏好设置</font>**<font style="color:rgb(64, 64, 64);">：保存用户的个性化设置</font>

### session 作用域

在 JavaWeb 开发中 session 对象也提供了以下三个方法：

```java
// 向会话域中存储数据
void setAttribute(String name, Object data);

// 从会话域中取数据
Object getAttribute(String name);

// 删除会话域中的数据
void removeAttribute(String name);
```

**<font style="color:rgb(64, 64, 64);">request、session、application</font>**<font style="color:rgb(64, 64, 64);"> 是 JavaWeb 开发中三个不同作用域的存储对象，它们的主要区别在于</font>**<font style="color:rgb(64, 64, 64);">生命周期</font>**<font style="color:rgb(64, 64, 64);">和</font>**<font style="color:rgb(64, 64, 64);">数据共享范围</font>**<font style="color:rgb(64, 64, 64);">。以下是详细对比：</font>

| **<font style="color:rgb(64, 64, 64);">作用域</font>**      | **<font style="color:rgb(64, 64, 64);">实现类/接口</font>**  | **<font style="color:rgb(64, 64, 64);">生命周期</font>**     | **<font style="color:rgb(64, 64, 64);">数据共享范围</font>** | **<font style="color:rgb(64, 64, 64);">典型应用场景</font>** |
| ----------------------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **<font style="color:rgb(64, 64, 64);">request</font>**     | <code>**<font style="color:rgb(64, 64, 64);background-color:rgb(236, 236, 236);">HttpServletRequest</font>**</code> | <font style="color:rgb(64, 64, 64);">一次请求内有效</font>   | <font style="color:rgb(64, 64, 64);">当前请求的转发链（forward）</font> | <font style="color:rgb(64, 64, 64);">页面间跳转时传递临时数据</font> |
| **<font style="color:rgb(64, 64, 64);">session</font>**     | <code>**<font style="color:rgb(64, 64, 64);background-color:rgb(236, 236, 236);">HttpSession</font>**</code> | <font style="color:rgb(64, 64, 64);">用户会话期间（默认30分钟无活动失效）</font> | <font style="color:rgb(64, 64, 64);">同一用户的所有请求</font> | <font style="color:rgb(64, 64, 64);">存储登录状态、购物车数据</font> |
| **<font style="color:rgb(64, 64, 64);">application</font>** | <code>**<font style="color:rgb(64, 64, 64);background-color:rgb(236, 236, 236);">ServletContext</font>**</code> | <font style="color:rgb(64, 64, 64);">Web应用启动到停止</font> | <font style="color:rgb(64, 64, 64);">所有用户共享</font>     | <font style="color:rgb(64, 64, 64);">全局配置、计数器、缓存数据</font> |

三个作用域中 `application`应用域因所有用户共享，如果共享数据涉及到修改操作，需要考虑线程安全问题。session 和 request 则不存在这个问题。

三个作用域的选择原则：尽可能使用小的作用域对象。

### session 的超时机制

默认情况下，服务器是不知道用户关闭浏览器这个行为的，因此 session 需要引入超时机制，以免失效的 session 一直停留在服务器内存当中。

session 一直停留在服务器中的问题：

1. 耗费内存。
2. session 对象被劫持的话，存在安全问题。

在 Tomcat 服务器中，默认情况下 session 的超时时间设置的是 30 分钟，在 `CATALINA_HOME/confi/web.xml`中有默认配置，如下：

![1749521047119-4cc75f0b-4e60-4757-93df-6699ce0e8eb7.png](./note22-会话管理.assets/1749521047119-4cc75f0b-4e60-4757-93df-6699ce0e8eb7-557948.png)

这是一个全局的配置，我们也可以在自己项目的 web.xml 文件中进行局部配置，局部优先生效。

### 手动销毁 session

session 除了依靠 session 超时机制来销毁之外，也支持手动销毁 session，调用以下方法可以销毁 session：

```java
session.invalidate(); // 手动销毁
```

例如，用户如果在浏览器上主动点击了 `安全退出`或者 `退出登录`等按钮，表示用户主动退出系统，后端应该执行以上代码来主动销毁 session 对象。

### session 中数据的持久化

session 对象销毁时，session 中可能保留了用户会话时的数据，例如用户登录后往购物车中存放的商品。（为什么要这样做？这是为了保证用户下一次登录成功后，购物车未结算的商品可以正常展示。）

如何持久化，可以使用监听器来完成，例如以下示例代码：

```java
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class SessionPersistenceListener implements HttpSessionListener {

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // Session超时或调用invalidate()时触发
        HttpSession session = se.getSession();
        
        // 获取需要持久化的数据
        User user = (User) session.getAttribute("user");
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        
        // 执行持久化操作
        if (user != null) {
            UserDao.saveLastActivityTime(user.getId(), new Date());
        }
        if (cart != null && !cart.isEmpty()) {
            CartService.saveTemporaryCart(user.getId(), cart);
        }
    }
}
```

## 完善登录功能

我们应该在用户登录成功后，创建该用户对应的 session 对象，然后将用户登录信息，存储到 session 域当中。以后就可以通过 session 对象中是否存在登录信息来判定用户是否登录了。

`LoginServlet`代码修改如下：

![1749522198381-d6ab2758-6d6b-4bd4-b6bd-204bc48bd7b2.png](./note22-会话管理.assets/1749522198381-d6ab2758-6d6b-4bd4-b6bd-204bc48bd7b2-609898.png)

## 登录验证过滤器

怎么能让已经登录的用户才能访问部门列表页面？

这就需要在 `DeptListServlet`类中添加拦截逻辑了：获取 session，判断 session 中是否存在用户名，如果不存在则跳转到登录页面，如果存在则表示已登录，可以继续访问。

这个拦截逻辑不仅需要在 `DeptListServlet`类中添加，在其它 Servlet 中也需要添加，因此有必要编写一个过滤器来进行拦截。

思考：这个过滤器应该拦截什么请求路径，什么路径不应该拦截？

1. 如果用户是显示登录页面，不应该拦截。`/dept/index`不能拦截。
2. 如果用户是登录请求，不应该拦截。`/dept/login`不能拦截。
3. 如果请求路径以 `/css`开始，不应该拦截。（当然项目中也可能存在 js 文件，jpg 或 png 等图片，也不应该拦截）
4. 其它的请求路径一律拦截。

编写过滤器 `CheckLoginFilter`：

```java
package com.jkweilai.dept.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

// 所有请求路径都经过这个过滤器
@WebFilter("/*")
public class CheckLoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        String servletPath = request.getServletPath();
        if ("/index".equals(servletPath) || "/login".equals(servletPath) || servletPath.startsWith("/css") || (session != null && session.getAttribute("username") != null)) {
            chain.doFilter(req, resp);
        } else {
            // 转发：采用这种方式时，上面if条件中不需要添加"/index".equals(servletPath) 进行放行
            // 因为转发是服务器内部资源的跳转，转发的路径是不走过滤器链的。
            //request.getRequestDispatcher("/index").forward(request, response);
            
            // 重定向：采用这种方式时，上面的if条件中必须添加 "/index".equals(servletPath) 进行放行
            // 因为重定向时会走过滤器链。如果不放行，将会出现：无限重定向错误。
            response.sendRedirect(request.getContextPath() + "/index");
        }
    }
}
```

到此为止，登录功能就完全起作用了。

## 关于 web 站点欢迎页

在 `CATALINA_HOME/conf/web.xml`文件中对欢迎页进行了全局配置，如下：

![1749524091859-be8f6d06-1f98-4ac9-b074-f9cc5132e215.png](./note22-会话管理.assets/1749524091859-be8f6d06-1f98-4ac9-b074-f9cc5132e215-982141.png)

因此默认情况下，这几个页面被当做欢迎页。

设置了欢迎页之后的效果是：访问 web 项目的根时，如果没有指定具体的资源路径，默认走欢迎页，例如访问 `http://localhost:8080/dept`时，默认会访问 `http://localhost:8080/dept/index.html`。

我们也可以在自己项目的 web.xml 文件中配置局部的欢迎页，局部优先。

如果我希望把 `http://localhost:8080/dept/index`作为欢迎页的话，应该这样配置：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="https://jakarta.ee/xml/ns/jakartaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee https://jakarta.ee/xml/ns/jakartaee/web-app_6_0.xsd"
         version="6.0">
    
    <welcome-file-list>
        <welcome-file>index</welcome-file>
    </welcome-file-list>
</web-app>
```

需要注意的是：欢迎页路径在设置的时候，不需要添加项目名，也不需要以 `/`开头。

到此为止，我们启动服务器之后，直接在浏览器地址栏上输入 `http://localhost:8080/dept`即可访问部门管理系统了。

## 每个操作页显示用户名

添加 `common.css`文件到 `/css`目录下：

```css
.user-info {
    display: flex;
    justify-content: flex-end;
    align-items: center;
    padding: 10px 0;
    margin-bottom: 10px;
    border-bottom: 1px solid #ddd;
}
.user-name {
    margin-right: 15px;
    color: #555;
    font-weight: 600;
}
.user-actions a {
    color: #777;
    text-decoration: none;
    font-size: 14px;
    margin-left: 10px;
}
.user-actions a:hover {
    color: #333;
    text-decoration: underline;
}
```

在 `add.html`、`list.html`、`edit.html`、`detail.html`的 `<div class="container">`和 `<div class="header">`之间添加以下代码：

```html
<!-- 新增的用户信息栏 -->
<div class="user-info">
  <span class="user-name">当前用户：管理员</span>
  <div class="user-actions">
    <a href="#">退出登录</a>
  </div>
</div>
```

在 `add.html`、`list.html`、`edit.html`、`detail.html`中添加 `common.css`样式：

```html
<link rel="stylesheet" th:href="@{/css/common.css}">
```

效果如下：

![1749525110958-94f1914a-070c-4cac-a5ad-8f7c872080b4.png](./note22-会话管理.assets/1749525110958-94f1914a-070c-4cac-a5ad-8f7c872080b4-062579.png)

用户登录成功后，将用户名存储到 session 作用域中了，因此在 html 页面中使用 `${session.username}`取出当前登录的用户名，替换掉页面中的 `管理员`：

```html
<span class="user-name" th:text="|当前用户：${session.username}|"></span>
```

最终效果如下：

![1749525457096-16dd2be4-c0ee-465a-aae7-a01bfb818c32.png](./note22-会话管理.assets/1749525457096-16dd2be4-c0ee-465a-aae7-a01bfb818c32-372538.png)

## 退出系统

用户点击退出系统时，后端应该销毁 session。

修改 退出系统 链接的请求路径：

```html
<a th:href="@{/logout}">退出登录</a>
```

编写 Servlet 销毁 session，重定向到登录页面：

```java
package com.jkweilai.dept.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect(request.getContextPath() + "/index");
    }
}

```

测试一下，用户点击退出系统后，是否还可以在浏览器地址栏上直接输入 `http://localhost:8080/dept/list`访问部门列表页面，如果跳转到登录页面则表示正常。

## Cookie\&Token 机制

### cookie机制及其作用

**cookie**是Web开发中用于在客户端存储少量数据的一种机制，由服务器发送到用户浏览器并保存在本地，当浏览器再次访问同一服务器时会将Cookie数据回传给服务器。

**cookie的特点**：

* 存储在客户端（浏览器）
* 大小限制（通常4KB左右）
* 每个域名下的 cookie 数量有限制（通常20-50个）
* 可以设置过期时间（不设置有效期时，默认 cookie 保存在浏览器缓存中，浏览器关闭后 cookie 消失，设置有效期 > 0 时 cookie 会被保存在客户端硬盘文件中）
* 可以被用户禁用（浏览器可以设置禁止接收 cookie，服务器还是会响应 cookie，但浏览器可以拒绝接收）

### cookie的应用场景

1. **用户登录状态保持**：记住登录状态，避免频繁登录
2. **购物车功能**：存储用户选择的商品信息
3. **个性化设置**：保存用户的语言、主题等偏好
4. **广告定向投放**：根据用户兴趣展示相关广告
   1. 当用户访问网站时，你可以设置一个或多个 cookies来存储用户的兴趣信息。例如，如果网站是关于时尚的，你可以设置一个名为interest_fashion的 cookie
5. **跨页面数据传递**：在多个页面间共享数据
   1. 假设创建了一个 cookie 存储了一些共享数据 data，并且让 cookie 关联请求路径，假设关联的请求路径是 `/dept`，那么 `/dept/list`、`/dept/save`等请求路径浏览器都会发送 cookie 数据给服务器，这样多个 Servlet 之间可以共享数据，进而完成跨页面数据传递

### session 和 cookie 的区别

| **<font style="color:rgb(64, 64, 64);">特性</font>** | **<font style="color:rgb(64, 64, 64);">cookie</font>**       | **<font style="color:rgb(64, 64, 64);">session</font>**      |
| ---------------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| <font style="color:rgb(64, 64, 64);">存储位置</font> | <font style="color:rgb(64, 64, 64);">客户端</font>           | <font style="color:rgb(64, 64, 64);">服务器端</font>         |
| <font style="color:rgb(64, 64, 64);">安全性</font>   | <font style="color:rgb(64, 64, 64);">较低</font>             | <font style="color:rgb(64, 64, 64);">较高</font>             |
| <font style="color:rgb(64, 64, 64);">存储大小</font> | <font style="color:rgb(64, 64, 64);">有限（约4KB）</font>    | <font style="color:rgb(64, 64, 64);">较大（取决于服务器）</font> |
| <font style="color:rgb(64, 64, 64);">生命周期</font> | <font style="color:rgb(64, 64, 64);">可设置长期有效</font>   | <font style="color:rgb(64, 64, 64);">通常较短（会话期间）</font> |
| <font style="color:rgb(64, 64, 64);">性能影响</font> | <font style="color:rgb(64, 64, 64);">每次请求都会携带</font> | <font style="color:rgb(64, 64, 64);">只在服务器端查找</font> |

### 在JavaWeb中创建和响应Cookie

#### 创建Cookie

```java
// 创建一个名为"username"，值为"john"的Cookie
Cookie cookie = new Cookie("username", "john");
```

cookie 的 name 和 value 要求必须是 String 类型。

#### 设置Cookie属性

```java
// 设置过期时间（秒），不设置则默认为会话Cookie（浏览器关闭即删除）
cookie.setMaxAge(60 * 60 * 24 * 7); // 一周后过期

// 设置路径，以下代码的作用是：当浏览器发送的请求路径是/myapp或者/myapp下的子路径时，都会发送该cookie给服务器。
// 设置cookie在哪些路径下有效。
cookie.setPath("/myapp"); 

// 设置域名（可以跨子域共享）
// 主域名：example.com
// 子域名：api.example.com、shop.example.com
// 不设置域名时，Cookie 仅对 当前完整域名 生效，不包括子域名。
// 显式设置域名时，只能设置为 当前域名或其父级主域名，不能是其他无关域名。
// 一句话：Cookie 永远被限制在它的主域名及子域名下，无法“越狱”！
// cookie.setDomain(".example.com");

// 如果网站使用的协议是HTTPS协议，应该执行以下代码，这样的话，只有发送HTTPS请求时，cookie才会提交给服务器。
// cookie.setSecure(true);

// 设置HttpOnly属性，有效减少XSS攻击
// 设置后：JavaScript中的document.cookies 无法访问它，因此即使存在 XSS，攻击者也无法直接窃取这类 Cookie。
cookie.setHttpOnly(true);
```

#### 响应Cookie到客户端

```java
response.addCookie(cookie);
```

### 服务器端获取客户端提交的 Cookie

```java
// 从请求中获取所有Cookie
Cookie[] cookies = request.getCookies();

if (cookies != null) {
    for (Cookie cookie : cookies) {
        String name = cookie.getName(); // 获取Cookie的名字
        String value = cookie.getValue(); // 获取Cookie的值
        // 处理Cookie数据
        if ("username".equals(name)) {
            System.out.println("用户名: " + value);
        }
    }
}
```

### cookie的注意事项

1. **安全性问题**：
   * 敏感信息不应存储在Cookie中，即使密码是经过加密的也不要存储在 Cookie 中
   * 使用HttpOnly和Secure标志增强安全性
   * 考虑对Cookie值进行加密
2. **编码问题**：Cookie的值不能包含\*\*<font style="color:#DF2A3F;">空格、分号、逗号</font>\*\*等特殊字符，需要对特殊字符进行URL编码

```java
Cookie cookie = new Cookie("test", URLEncoder.encode("含有特殊字符的值", "UTF-8"));
```

3. **用 Java 程序删除Cookie**：

```java
Cookie cookie = new Cookie("username", "");
cookie.setMaxAge(0); // 立即过期
cookie.setPath("/"); // 必须与要删除的Cookie的路径一致
response.addCookie(cookie);
```

4. **跨域问题**：Cookie不能跨域共享，可以通过设置domain属性实现子域共享。

### 实现"记住我"功能

实现思路：登录成功后，应该创建 Cookie，Cookie 中保存登录的用户名，然后将 Cookie 响应给浏览器。下一次浏览器发送请求时就会携带 Cookie，服务器接收浏览器提交的 Cookie 后，获取 Cookie 中是否有登录的用户名，如果存在则表示已登录，如果不存在则跳转到登录页面。另外，实现了“记住我”这个功能后，用户关闭浏览器，再打开浏览器访问系统时就不需要登录了，所以 IndexServlet 程序也需要改动。因此要编写三部分代码：

1. `LoginServlet`中登录成功后，创建 Cookie，Cookie 中保存登录的用户名，然后响应 Cookie 到浏览器。
2. `CheckLoginFilter`中，获取浏览器提交的 Cookie，并且查看 Cookie 中是否保存了登录的用户名，如果有则创建 session，将登录名存储到 session 中，如果没有则跳转到登录页面。
3. `IndexServlet`中，获取浏览器提交的 Cookie，并且查看 Cookie 中是否保存了登录的用户名，如果有则创建 session，将登录名存储到 session 中，如果没有则跳转到登录页面。

```java
package com.jkweilai.dept.servlets;

import com.jkweilai.dept.utils.Argon2PasswordUtil;
import com.jkweilai.dept.utils.DbUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 连接数据库校验用户名和密码
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean loginSuccess = false;
        try {
            conn = DbUtils.getConnection();
            String sql = "select password from t_user where username = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if(rs.next()) {
                String hashPassword = rs.getString("password");
                if(Argon2PasswordUtil.verifyPassword(password, hashPassword)) {
                    // 登录成功
                    loginSuccess = true;
                    // 创建session对象，存储用户登录的信息
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    
                    // 创建Cookie，将登录名存储到Cookie中，响应到浏览器
                    Cookie cookie = new Cookie("username", username);
                    cookie.setMaxAge(60 * 60 * 24 * 30); // 30天有效
                    cookie.setPath(request.getContextPath());
                    cookie.setHttpOnly(true);
                    if(request.isSecure()){
                        cookie.setSecure(true);
                    }
                    response.addCookie(cookie);
                    
                    // 重定向到部门列表
                    response.sendRedirect(request.getContextPath() + "/list");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(conn, ps, rs);
        }
        // 登录失败，转发到登录页面
        if(!loginSuccess) {
            request.setAttribute("errorMsg", "用户名不存在或密码错误！");
            request.setAttribute("template", "index");
            request.getRequestDispatcher("/view").forward(request, response);
        }
    }
}
```

在 `LoginServlet`中添加的代码是：

![1749689769680-72e0f8df-7e65-41a3-adea-4bb11ad98430.png](./note22-会话管理.assets/1749689769680-72e0f8df-7e65-41a3-adea-4bb11ad98430-097189.png)

```java
package com.jkweilai.dept.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

// 所有请求路径都经过这个过滤器
@WebFilter("/*")
public class CheckLoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        // 获取客户端的Cookie，如果有名字为 username 的Cookie
        // 将该Cookie的value取出（用户的登录名），存储到session中
        // 表示该用户已登录
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("username")) {
                    String username = cookie.getValue();
                    request.getSession().setAttribute("username", username);
                    chain.doFilter(request, response);
                    return;
                }
            }
        }


        HttpSession session = request.getSession(false);
        String servletPath = request.getServletPath();
        if ("/index".equals(servletPath) || "/login".equals(servletPath) || servletPath.startsWith("/css") || (session != null && session.getAttribute("username") != null)) {
            chain.doFilter(req, resp);
        } else {
            response.sendRedirect(request.getContextPath() + "/index");
        }
    }
}
```

`CheckLoginFilter`过滤器中添加的代码如下：

![1749690017973-06ecca62-c95f-4396-bc1d-824f27a6ab59.png](./note22-会话管理.assets/1749690017973-06ecca62-c95f-4396-bc1d-824f27a6ab59-222561.png)

`IndexServlet`程序修改如下：

```java
package com.jkweilai.dept.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean isLogin = false;
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("username")) {
                    isLogin = true;
                    String username = cookie.getValue();
                    request.getSession().setAttribute("username", username);
                    request.setAttribute("template", "list");
                    response.sendRedirect(request.getContextPath() + "/list");
                }
            }
        }

        if(!isLogin) {
            request.setAttribute("template", "index");
            request.getRequestDispatcher("/view").forward(request, response);
        }
        
    }
}

```

到此“记住我”功能基本上实现了。大家可以自行测试一下。

以上“记住我”功能存在的问题包括：

1. **用户名未验证**：未对用户名进行验证，只要有 username 这样的 cookie 就认定登录了。
2. **<font style="color:rgb(64, 64, 64);">安全性问题</font>**<font style="color:rgb(64, 64, 64);">：直接存储用户名在Cookie中，容易被窃取或伪造。一旦被窃取或伪造，对于后端系统来说，用户名是长期有效且不可变的，攻击者可以长期冒充用户。</font>
3. **<font style="color:rgb(64, 64, 64);">容易导致越权访问</font>**<font style="color:rgb(64, 64, 64);">：如果系统仅依赖 Cookie 中的用户名做身份认证，攻击者</font>**<font style="color:rgb(64, 64, 64);">伪造 Cookie 即可登录任意账号</font>**<font style="color:rgb(64, 64, 64);">。而且还可以把 </font><code>**<font style="color:rgb(64, 64, 64);background-color:rgb(236, 236, 236);">username=admin</font>**</code><font style="color:rgb(64, 64, 64);"> 改成 </font><code>**<font style="color:rgb(64, 64, 64);background-color:rgb(236, 236, 236);">username=root</font>**</code><font style="color:rgb(64, 64, 64);">，可能直接获得管理员权限。</font>
4. **<font style="color:rgb(64, 64, 64);">敏感信息暴露</font>**<font style="color:rgb(64, 64, 64);">：将明文用户名存储在客户端Cookie中，可能违反隐私保护原则。</font>
5. **<font style="color:rgb(64, 64, 64);">密码未参与验证</font>**<font style="color:rgb(64, 64, 64);">：记住我功能通常应该重新验证密码，而不仅仅是依赖Cookie。</font>
6. **<font style="color:rgb(64, 64, 64);">无法应对数据泄露</font>**<font style="color:rgb(64, 64, 64);">：如果数据库泄露，攻击者拿到所有用户名，可以直接构造恶意 Cookie，而</font>**<font style="color:rgb(64, 64, 64);">服务器无法区分合法和非法请求</font>**<font style="color:rgb(64, 64, 64);">。</font>
7. **<font style="color:rgb(64, 64, 64);">CSRF防护不足</font>**<font style="color:rgb(64, 64, 64);">：虽然设置了HttpOnly，但没有其他CSRF防护措施。</font>

<font style="color:rgb(64, 64, 64);">更安全的做法是生成一个随机令牌（token）存储在数据库（或者存储在 redis 缓存中），并与用户关联，同时在Cookie中只存储这个令牌值。</font>

**<font style="color:#DF2A3F;">因此，你需要将之前实现的“记住我”功能添加的所有代码全部删除。我们将使用 Token 令牌来实现“记住我”功能。</font>**

### Token 令牌

实现“记住我”功能【如果系统不需要“记住我”这个功能，Token 令牌机制不需要】，采用 Cookie 中存储 Token 令牌要比直接存储用户名安全的多。

#### 什么是 Token 令牌

<font style="color:rgb(64, 64, 64);">Token是一种代表用户身份和权限的凭证，它是在用户成功登录后由服务器生成并返回给客户端的一串随机字符串。客户端在后续请求中携带这个Token来证明自己的身份，而无需每次都发送用户名和密码。</font>

#### Token 的工作流程

1. <font style="color:rgb(64, 64, 64);">客户端发送登录请求</font>
2. <font style="color:rgb(64, 64, 64);">服务器验证用户名和密码，生成Token，Token 和用户 id 关联存储到数据库表中，然后将 Token 响应给客户端</font>
3. <font style="color:rgb(64, 64, 64);">客户端存储Token（通常在localStorage或cookie中）</font>
4. <font style="color:rgb(64, 64, 64);">后续请求携带Token</font>
5. <font style="color:rgb(64, 64, 64);">服务器验证Token并处理请求</font>

#### Token 为什么更安全

| **<font style="color:rgb(64, 64, 64);">方案</font>**       | **<font style="color:rgb(64, 64, 64);">直接存储用户名</font>** | **<font style="color:rgb(64, 64, 64);">存储 Token</font>**   |
| ---------------------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **<font style="color:rgb(64, 64, 64);">是否可伪造</font>** | <font style="color:rgb(64, 64, 64);">✅</font><font style="color:rgb(64, 64, 64);"> 是（直接改 Cookie）</font> | <font style="color:rgb(64, 64, 64);">❌</font><font style="color:rgb(64, 64, 64);"> 否（需猜中随机 Token）</font> |
| **<font style="color:rgb(64, 64, 64);">是否可撤销</font>** | <font style="color:rgb(64, 64, 64);">❌</font><font style="color:rgb(64, 64, 64);"> 否（除非改用户名）</font> | <font style="color:rgb(64, 64, 64);">✅</font><font style="color:rgb(64, 64, 64);"> 是（服务器可删除 Token）</font> |
| **<font style="color:rgb(64, 64, 64);">有效期控制</font>** | <font style="color:rgb(64, 64, 64);">❌</font><font style="color:rgb(64, 64, 64);"> 永久有效</font> | <font style="color:rgb(64, 64, 64);">✅</font><font style="color:rgb(64, 64, 64);"> 可设置过期时间</font> |
| **<font style="color:rgb(64, 64, 64);">泄露风险</font>**   | <font style="color:rgb(64, 64, 64);">🔴</font><font style="color:rgb(64, 64, 64);"> 高风险（用户名固定）</font> | <font style="color:rgb(64, 64, 64);">🟢</font><font style="color:rgb(64, 64, 64);"> 低风险（Token 无意义）</font> |
| **<font style="color:rgb(64, 64, 64);">适用场景</font>**   | <font style="color:rgb(64, 64, 64);">❌</font><font style="color:rgb(64, 64, 64);"> 不推荐</font> | <font style="color:rgb(64, 64, 64);">✅</font><font style="color:rgb(64, 64, 64);"> 推荐（行业标准）</font> |

* **<font style="color:rgb(64, 64, 64);">直接存用户名</font>**<font style="color:rgb(64, 64, 64);">：相当于把家门钥匙放在门口地毯下，谁找到都能用。</font>
* **<font style="color:rgb(64, 64, 64);">存 Token</font>**<font style="color:rgb(64, 64, 64);">：相当于使用一次性密码锁，钥匙可随时更换，即使被拿到也很快失效。</font>

#### Token 的生成算法

```java
package com.jkweilai.dept.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class TokenGenerator {

    // 生成一个安全的随机 Token（Base64 编码）
    public static String generateSecureToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[32]; // 32字节 = 256位（足够安全）
        secureRandom.nextBytes(tokenBytes); // 填充随机字节
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }

}
```

#### <font style="color:rgb(64, 64, 64);">了解 Base64 编码</font>

Base64 编码**不是加密**，也**不是压缩**，它的作用是将一个**二进制数据**转换成**普通字符串**。

**它的核心用途包括三个：**

* **第一：在 URL 中安全传输数据。**
  * HTTP协议天生是为文本设计的，如果你直接在URL里塞一个图片字节（比如0xFF），浏览器可能会把它当成乱码截断或报错。
  * Base64把这些字节转成 `A-Z`、`a-z`、`0-9`、`+`、`/` 这64个“安全字符”，就能放在 `?id=xxx`后面了。
* **第二：在 JSON 或 XML 中内嵌小文件。比如你给后端发一段JSON：**

```json
{"avatar": "这里要放图片咋办？"}
```

直接把图片二进制塞进去会破坏JSON结构。所以前端会把图片转成Base64字符串塞进去，后端收到后再解码还原。

* **第三：避免传输中的乱码问题。**
  * 不同系统对特殊字符（比如换行符、结束符）的处理不一样。Base64只用那64个字符，不管经过多少路由器、防火墙，它们都“纹丝不动”，保证传输中不会乱码。

**但你要注意它的坑：**

* 第一个坑：体积会变大。在原体积基础上增大 `1/3`
* 第二个坑：没有安全性。因为它只是编码，不是加密，千万别认为它是密文。

**Java 中如何对二进制（字节数组）进行 Base64 编码和解码：**

```java
// 编码：将byte数组转换为Base64的字符串（byte[]数组将来可以是一个图片）
byte[] data = {97,98,99,100};
String s = Base64.getEncoder().encodeToString(data); // 结果是："YWJjZA=="

// 解码：将Base64的字符串转换成byte数组
byte[] data2 = Base64.getDecoder().decode(s); // 结果是：{97,98,99,100}
```

**Base64 编码时：**<code>**getEncoder()**</code>\*\*和 \*\*<code>**getUrlEncoder()**</code>**的区别**

* <code>**<font style="color:rgb(15, 17, 21);background-color:rgb(235, 238, 242);">getEncoder()</font>**</code>**<font style="color:rgb(15, 17, 21);"> 使用标准 Base64 字符集（</font>**<code>**<font style="color:rgb(15, 17, 21);background-color:rgb(235, 238, 242);">A-Z a-z 0-9 + /</font>**</code>**<font style="color:rgb(15, 17, 21);">），编码后可能包含 </font>**<code>**<font style="color:rgb(15, 17, 21);background-color:rgb(235, 238, 242);">+</font>**</code>**<font style="color:rgb(15, 17, 21);">、</font>**<code>**<font style="color:rgb(15, 17, 21);background-color:rgb(235, 238, 242);">/</font>**</code>**<font style="color:rgb(15, 17, 21);"> 和 </font>**<code>**<font style="color:rgb(15, 17, 21);background-color:rgb(235, 238, 242);">=</font>**</code>**<font style="color:rgb(15, 17, 21);">，不适合直接放 URL；</font>**
* <code>**<font style="color:rgb(15, 17, 21);background-color:rgb(235, 238, 242);">getUrlEncoder()</font>**</code>**<font style="color:rgb(15, 17, 21);"> 使用 URL 安全字符集（</font>**<code>**<font style="color:rgb(15, 17, 21);background-color:rgb(235, 238, 242);">A-Z a-z 0-9 - _</font>**</code>**<font style="color:rgb(15, 17, 21);">），把 </font>**<code>**<font style="color:rgb(15, 17, 21);background-color:rgb(235, 238, 242);">+</font>**</code>**<font style="color:rgb(15, 17, 21);"> 替换为 </font>**<code>**<font style="color:rgb(15, 17, 21);background-color:rgb(235, 238, 242);">-</font>**</code>**<font style="color:rgb(15, 17, 21);">，</font>**<code>**<font style="color:rgb(15, 17, 21);background-color:rgb(235, 238, 242);">/</font>**</code>**<font style="color:rgb(15, 17, 21);"> 替换为 </font>**<code>**<font style="color:rgb(15, 17, 21);background-color:rgb(235, 238, 242);">_</font>**</code>**<font style="color:rgb(15, 17, 21);">，且通常配合 </font>**<code>**<font style="color:rgb(15, 17, 21);background-color:rgb(235, 238, 242);">withoutPadding()</font>**</code>**<font style="color:rgb(15, 17, 21);"> 去掉 </font>**<code>**<font style="color:rgb(15, 17, 21);background-color:rgb(235, 238, 242);">=</font>**</code>**<font style="color:rgb(15, 17, 21);">，适合放在 URL 或 HTTP Header 中传输。</font>**

### 用 Token 实现“记住我”功能

#### Token 表的设计

```sql
CREATE TABLE `user_remember_tokens` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL COMMENT '关联的用户ID',
  `token` varchar(64) NOT NULL COMMENT '随机生成的token',
  `series` varchar(64) NOT NULL COMMENT '序列标识(安全增强)',
  `fingerprint` varchar(255) DEFAULT NULL COMMENT '客户端指纹(IP+UserAgent哈希)',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `expires_at` datetime NOT NULL COMMENT '过期时间',
  `revoked` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已撤销',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_series` (`series`),
  KEY `idx_user_token` (`user_id`,`token`),
  KEY `idx_expires` (`expires_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户持久登录token表';
```

* **series 机制**：**<font style="color:#DF2A3F;">【我们课程中就不再实现 series 机制了，感兴趣可以自行补充这部分代码】</font>**
  * 在用户第一次登录成功后，服务器为浏览器生成唯一的<font style="color:rgb(64, 64, 64);">series标识。series标识和 Token 会通过 Cookie 发送到浏览器。</font>
  * <font style="color:rgb(64, 64, 64);">只要在 Cookie 有效期内（例如：7 天免登录，Cookie 有效期为 7 天），用户再次访问的系统，浏览器会自动提交series标识和 Token，系统验证series标识和 Token 都有效，则立即更新 Token 但series标识不变，将将相同的 series 和新的 token 通过 Cookie 发送到浏览器。</font>
  * <font style="color:rgb(64, 64, 64);">假设series标识和 Token 被黑客盗取，黑客使用series标识和 Token 登录成功了，Token 会更新但series标识不变。此时合法用户在访问系统时会提交series标识以及旧 Token。系统发现series标识是正确的，但旧 Token 无效，系统则认定series标识和 Token 被盗了。立即将数据库series标识对应的记录全部清除，这样无论是黑客还是合法用户再次访问系统时都需要重新输入用户名和密码进行登录。</font>

```java
String series = UUID.randomUUID().toString();
```

* **fingerprint 机制（客户端指纹）**：

```java
String ip = request.getRemoteAddr();
String userAgent = request.getHeader("User-Agent");
String fingerprint = DigestUtils.sha256Hex(ip + userAgent + "salt");
```

以上代码需要这个 jar 包：![1749699237786-870af075-9760-4644-aa7b-0543bbf1100a.png](./note22-会话管理.assets/1749699237786-870af075-9760-4644-aa7b-0543bbf1100a-364477.png)，添加到 WEB-INF/lib 目录下，并且添加到 classpath 中。

<font style="color:rgb(15, 17, 21);">Apache Commons Codec 是一个用于编码和解码的Java库，提供常用的编码器如Base64、Hex、URL等</font>

#### 代码的实现

实现思路：

1. 用户登录成功后：【在 LoginServlet 中编写代码】
   1. 生成 Token，Token 关联用户 id 存储到 user_remember_tokens 表。
   2. 将 Token 放到 Cookie 中响应给浏览器。
   3. 将用户名存储到 session 中 。
2. 验证 Token：【在 CheckLoginFilter 中编写代码】
   1. 获取客户端提交的 Cookie，从 Cookie 中获取 Token
   2. 连接数据库验证 Token 是否有效：
      1. 有效，登录成功，将用户名存储到 session 中，放行
      2. 无效，删除客户端 Token，将数据库 Token 的 revoked 设置为 1， 跳转到登录页面
3. 当用户登录过之后，用户再次发送 `http://localhost:8080/dept`请求时，应该展示部门列表页面：【在 IndexServlet 中编写代码】
   1. 先看 session 中有没有用户名，如果有则直接显示部门列表。
   2. 如果 session 中没有用户名，则获取客户端提交的 Token。
   3. 连接数据库验证 Token 是否有效：
      1. 有效，登录成功，将用户名存储到 session 中，展示部门列表
      2. 无效，删除客户端 Token，将数据库 Token 的 revoked 设置为 1，跳转到登录页面

`LoginServlet`的代码：

```java
package com.jkweilai.dept.servlets;

import com.jkweilai.dept.utils.Argon2PasswordUtil;
import com.jkweilai.dept.utils.DbUtils;
import com.jkweilai.dept.utils.TokenGenerator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 连接数据库校验用户名和密码
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PreparedStatement ps2 = null;
        boolean loginSuccess = false;
        try {
            conn = DbUtils.getConnection();
            String sql = "select id,password from t_user where username = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if(rs.next()) {
                String hashPassword = rs.getString("password");
                if(Argon2PasswordUtil.verifyPassword(password, hashPassword)) {
                    // 登录成功
                    loginSuccess = true;

                    // ===============================================================================================
                    // 生成Token
                    String token = TokenGenerator.generateSecureToken();
                    // 保存Token到数据库
                    String insertTokenSql = "insert into user_remember_tokens(id,user_id,token,series,fingerprint,expires_at) values(null,?,?,?,?,?)";
                    ps2 = conn.prepareStatement(insertTokenSql);
                    ps2.setString(1, rs.getString("id"));
                    ps2.setString(2, token);
                    String series = UUID.randomUUID().toString();
                    ps2.setString(3, series);
                    String ip = request.getRemoteAddr();
                    String userAgent = request.getHeader("User-Agent");
                    String fingerprint = DigestUtils.sha256Hex(ip + userAgent + "salt");
                    ps2.setString(4, fingerprint);
                    LocalDateTime expiresAt = LocalDateTime.now().plusDays(30);
                    ps2.setTimestamp(5, Timestamp.valueOf(expiresAt)); // 30天有效
                    ps2.executeUpdate();
                    // 响应Token到浏览器
                    Cookie cookie = new Cookie("token", token);
                    cookie.setMaxAge(60 * 60 * 24 * 30); // 30天有效
                    cookie.setPath(request.getContextPath());
                    cookie.setHttpOnly(true);
                    if(request.isSecure()){
                        cookie.setSecure(true);
                    }
                    response.addCookie(cookie);
                    // ===============================================================================================

                    // 创建session对象，存储用户登录的信息
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    // 重定向到部门列表
                    response.sendRedirect(request.getContextPath() + "/list");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(null, ps2, null);
            DbUtils.close(conn, ps, rs);
        }
        // 登录失败，转发到登录页面
        if(!loginSuccess) {
            request.setAttribute("errorMsg", "用户名不存在或密码错误！");
            request.setAttribute("template", "index");
            request.getRequestDispatcher("/view").forward(request, response);
        }
    }
}
```

`CheckLoginFilter`代码的实现：

```java
package com.jkweilai.dept.filters;

import com.jkweilai.dept.utils.DbUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// 所有请求路径都经过这个过滤器
@WebFilter("/*")
public class CheckLoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        String servletPath = request.getServletPath();
        if ("/index".equals(servletPath) || "/login".equals(servletPath) || servletPath.startsWith("/css") || (session != null && session.getAttribute("username") != null)) {
            chain.doFilter(req, resp);
        } else {
            // 看看客户端是否提交Token，并且Token是否有效，如果有效登录成功，创建session存储用户名，无效则删除客户端Token并跳转到登录页面
            boolean isLogin = false;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("token".equals(cookie.getName())) {
                        String token = cookie.getValue();
                        // 验证token是否有效
                        Connection conn = null;
                        PreparedStatement ps = null;
                        PreparedStatement ps2 = null;
                        ResultSet rs = null;
                        try {
                            conn = DbUtils.getConnection();
                            String sql = "select u.username from user_remember_tokens urt join t_user u on urt.user_id=u.id where urt.token=? and urt.fingerprint=? and urt.expires_at > now() and urt.revoked = '0'";
                            ps = conn.prepareStatement(sql);
                            ps.setString(1, token);
                            String ip = request.getRemoteAddr();
                            String userAgent = request.getHeader("User-Agent");
                            String fingerprint = DigestUtils.sha256Hex(ip + userAgent + "salt");
                            ps.setString(2, fingerprint);
                            rs = ps.executeQuery();
                            if (rs.next()) {
                                isLogin = true;
                                HttpSession newSession = request.getSession(true);
                                newSession.setAttribute("username", rs.getString("username"));
                                chain.doFilter(request, response);
                            } else {
                                // 将数据库中token的revoked设置为1
                                String sql2 = "update user_remember_tokens set revoked=1 where token=?";
                                ps2 = conn.prepareStatement(sql2);
                                ps2.setString(1, token);
                                ps2.executeUpdate();
                                // 删除客户端Token
                                Cookie deletedCookie = new Cookie("token", token);
                                deletedCookie.setMaxAge(0);
                                response.addCookie(deletedCookie);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            DbUtils.close(null, ps2, null);
                            DbUtils.close(conn, ps, rs);
                        }
                    }
                }
            }
            if (!isLogin) {
                response.sendRedirect(request.getContextPath() + "/index");
            }
        }
    }
}
```

`IndexServlet`的代码：

```java
package com.jkweilai.dept.servlets;

import com.jkweilai.dept.utils.DbUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            response.sendRedirect(request.getContextPath() + "/list");
        } else {
            // 看看客户端是否提交Token，并且Token是否有效，如果有效登录成功，创建session存储用户名，无效则删除客户端Token并跳转到登录页面
            boolean isLogin = false;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("token".equals(cookie.getName())) {
                        String token = cookie.getValue();
                        // 验证token是否有效
                        Connection conn = null;
                        PreparedStatement ps = null;
                        PreparedStatement ps2 = null;
                        ResultSet rs = null;
                        try {
                            conn = DbUtils.getConnection();
                            String sql = "select u.username from user_remember_tokens urt join t_user u on urt.user_id=u.id where urt.token=? and urt.fingerprint=? and urt.expires_at > now() and urt.revoked = '0'";
                            ps = conn.prepareStatement(sql);
                            ps.setString(1, token);
                            String ip = request.getRemoteAddr();
                            String userAgent = request.getHeader("User-Agent");
                            String fingerprint = DigestUtils.sha256Hex(ip + userAgent + "salt");
                            ps.setString(2, fingerprint);
                            rs = ps.executeQuery();
                            if (rs.next()) {
                                isLogin = true;
                                HttpSession newSession = request.getSession(true);
                                newSession.setAttribute("username", rs.getString("username"));
                                response.sendRedirect(request.getContextPath() + "/list");
                            } else {
                                // 将数据库中token的revoked设置为1
                                String sql2 = "update user_remember_tokens set revoked=1 where token=?";
                                ps2 = conn.prepareStatement(sql2);
                                ps2.setString(1, token);
                                ps2.executeUpdate();
                                // 删除客户端Token
                                Cookie deletedCookie = new Cookie("token", token);
                                deletedCookie.setMaxAge(0);
                                response.addCookie(deletedCookie);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            DbUtils.close(null, ps2, null);
                            DbUtils.close(conn, ps, rs);
                        }
                    }
                }
            }
            if (!isLogin) {
                request.setAttribute("template", "index");
                request.getRequestDispatcher("/view").forward(request, response);
            }
        }
    }
}

```

### 退出系统清除 Token

退出系统时，应该删除客户端的 token，并且将数据库中的 token 撤销掉（revoked 设置为 1）。

`LogoutServlet`的代码修改如下：

```java
package com.jkweilai.dept.servlets;

import com.jkweilai.dept.utils.DbUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    // 将数据库表中token的revoked设置为1
                    Connection conn = null;
                    PreparedStatement ps = null;
                    try {
                        conn = DbUtils.getConnection();
                        String sql = "update user_remember_tokens set revoked = 1 where token=?";
                        ps = conn.prepareStatement(sql);
                        ps.setString(1, token);
                        ps.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        DbUtils.close(conn, ps, null);
                    }
                    // 删除客户端Token
                    Cookie newCookie = new Cookie("token", token);
                    newCookie.setMaxAge(0);
                    response.addCookie(newCookie);
                }
            }
        }

        response.sendRedirect(request.getContextPath() + "/index");
    }
}
```

### 最佳实践建议

1. **<font style="color:rgb(64, 64, 64);">限制每个用户的token数量</font>**<font style="color:rgb(64, 64, 64);">：实际上就是控制登录的设备</font>

```sql
-- 创建token前检查
SELECT COUNT(*) FROM user_remember_tokens WHERE user_id = ? AND revoked = 0 AND expires_at > NOW();
```

2. **<font style="color:rgb(64, 64, 64);">定期维护</font>**<font style="color:rgb(64, 64, 64);">：定期清理已撤销或已过期的 token</font>

```sql
-- 清理过期token（可设置定时任务）
DELETE FROM user_remember_tokens WHERE expires_at < NOW() OR revoked = 1;
```

3. **<font style="color:rgb(64, 64, 64);">敏感操作保护</font>**<font style="color:rgb(64, 64, 64);">：</font>

* <font style="color:rgb(64, 64, 64);">修改密码/邮箱等敏感操作后立即撤销所有remember token</font>
* <font style="color:rgb(64, 64, 64);">重要操作（如支付）要求重新输入密码</font>

## 演练1.登录功能的实现

## 还是web15（dept）项目，我们的项目存在一个问题，就是任何人当访问。而且任何用户只要知道路径的写法，就可以做任何操作，包括删除，没有任何安全可言。

### 1、要实现登录功能，首先需要在数据库里面创建一个用户表

![image-20261016124508855](./note22-会话管理.assets/image-20261016124508855.png)

### 2、下载一个bcprov-jdk18on-1.77.jar的jar包，然后放到WEB-INF/lib里面，并且需要作为库添加到classpath中

![image-20261016130240953](./note22-会话管理.assets/image-20261016130240953.png)

### 3.然后需要不写一个叫做Argon2PasswordUtil的类，用来做加密算法，代码参考上面的笔记

![image-20261016130558734](./note22-会话管理.assets/image-20261016130558734.png)

### 4.我们可用在这个类里面添加一个main方法来测试一下在线api是否好用

![image-20261016134116511](./note22-会话管理.assets/image-20261016134116511.png)

### 5.为了方便我们的开发，我们先运行这个密码加密工具.用123456来生成一个hash密码，然后在navicat里面新建一个用户admin,把密码保存为这个密码

![image-20261016134534148](./note22-会话管理.assets/image-20261016134534148.png)

![image-20261016134637501](./note22-会话管理.assets/image-20261016134637501.png)

### 6.把WEB-INF/templates/index.html页面的样式抽取出来放到web/css/index.css文件中，然后修改一下index.html文件，代码见上面的笔记

![image-20261016135157040](./note22-会话管理.assets/image-20261016135157040.png)

### 7.创建一个IndexServlet类，基础自HttpServlet，实现doGet方法，这个servlet的作用是让thymeleaf用模板文件index.html渲染页面，可以使用重定向和转发2种方式，都是个人觉得使用转发更好，因为这样子，url还是/index

![image-20261016142213427](./note22-会话管理.assets/image-20261016142213427.png)

#### 效果

![image-20261016143155595](./note22-会话管理.assets/image-20261016143155595.png)

#### 注意，这个页面只是一个表单，真正出来登录的是/login路径对应的Servlet

### 8.我们需要一个欢迎页面，就是当服务器启动的时候会自动打开的页面，这个默认在tomcat服务器的conf里面的web.xml里面有全局配置

![image-20261016144134931](./note22-会话管理.assets/image-20261016144134931.png)

### 根据就近原则，我们也可以在我们自己项目的web.xml里面做自己的配置，然后这个配置会覆盖tomcat全局配置，具体配置如下

![image-20261016144840638](./note22-会话管理.assets/image-20261016144840638.png)

### 9.此时我们需要把IndexServlet上面的注解参数改为/login

![image-20261016144933731](./note22-会话管理.assets/image-20261016144933731.png)

#### 重启服务器，发现浏览器自动打开了登录页面

![image-20261016145058191](./note22-会话管理.assets/image-20261016145058191.png)

#### 注意：配置欢迎页的时候，路径前面不需要加“/”，系统会自动从项目根路径开始查找

### 10.为了方便开发，我们还是把欢迎路径改为index,把IndexServlet的路径也改为/index

![image-20261016150056470](./note22-会话管理.assets/image-20261016150056470.png)

![image-20261016150143426](./note22-会话管理.assets/image-20261016150143426.png)

### 11.然后我们创建一个LoginServlet，设置路径为/login，实现doPost方法,核心逻辑就是根据用户提交的用户名来获取密码，用获取到的密码和用户提交的密码加密后的结果比较如果相等，就跳转到list页面，只能使用重定向，如果不相等，就跳转到/index路径，本来应该使用重定向，都是这样子的话，我们的错误信息无法传递，使用转发可能会报405，因为/index是get请求，比较保险的方法是给IndexServlet实现doPost方法，在里面调用doGet方法，不过我在ThymeleafViewServlet实现了doGet和doPost方法，所以没有这个问题。



### 12、然后我们需要修改一下index.html模板文件，如果登录失败，输出错误信息

![image-20261016170850789](./note22-会话管理.assets/image-20261016170850789.png)

## 登录功能完全实现，但是这个登录暂时没有多大作用，我们还是可以直接输入路径访问，如果要登录起作用，我们需要使用过滤器和会话管理功能。

# 演练2.学习HttpSession

## 1.新建应该模块起名web16，添加web支持，创建构件并且发布到tomcat

![image-20261016172133580](./note22-会话管理.assets/image-20261016172133580.png)

![image-20261016172235127](./note22-会话管理.assets/image-20261016172235127.png)

![image-20261016172305879](./note22-会话管理.assets/image-20261016172305879.png)

## 2.然后在项目根目录下面创建应该lib文件夹，把servlet-api.jar放进来，然后作为库添加到classpath

![image-20261016172522561](./note22-会话管理.assets/image-20261016172522561.png)

## 3.然后在网站的根目录下面创建应该index.html,内容如下

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>学习HttpSession的会话机制</title>
</head>
<body>
<a href="/web16/my/session">访问服务器中属于我的session对象</a>
</body>
</html>
```



## 4.然后我们新员工MySessionServlet,基础自HttpServlet，实现doGet方法，代码如下

![image-20261016174553540](./note22-会话管理.assets/image-20261016174553540.png)

### 启动服务器搭建连接，就会看到Ip和session对象的信息

![image-20261016174702491](./note22-会话管理.assets/image-20261016174702491.png)

### 不管你刷新页面多少次，你会发现，它始终是同一个对象。因为员工浏览器选项卡其实就是一个对话，而一个对话就对应一个session对象，只要是同一次对话，它对应的就是同一个session

![image-20261016174912245](./note22-会话管理.assets/image-20261016174912245.png)

## 5.然后我们打开浏览器调试工具，可以在application里面的Cookies看到我们的sessionID

![image-20261016183415170](./note22-会话管理.assets/image-20261016183415170.png)

## 6.当我们最终访问一个网站，然后关闭浏览器，其实服务器是不知道浏览器已关闭，这样子session还会保留，这样子就会浪费内存，所以现在的服务器一般都会session过期机制，默认的过期时间一般是30分钟。是指在连续的30分钟里面没有客户访问，才会让session过期，只要有客户访问，session就不会过期。我们可以在tomcat的conf/web.xml里面看到session的过期时间

![image-20261016190659543](./note22-会话管理.assets/image-20261016190659543.png)



## 7.上面是全局配置，我们也可以在我们项目的web.xml里面配置session的过期时间

![image-20261016190911938](./note22-会话管理.assets/image-20261016190911938.png)

### 注意：网银系统的session过期时间非常短，只有5分钟，如果你需要你网站安全级别高一点，就可以把session的过期时间设置小一点。

## 8.session也有setAttribute，getAttribute和removeAttribute方法，session持久化，需要编写一个监听器实现HttpSessionListener接口

![image-20261016193025064](./note22-会话管理.assets/image-20261016193025064.png)

# 理解一次完整的会话

## javaweb中怎么才能算是一次完整的会话？

在 Java Web 开发中，**一个完整的会话（Session）是指从浏览器（客户端）第一次向服务器发起 HTTP 请求开始，到浏览器关闭或会话超时失效为止的整个连续交互过程**。

由于 HTTP 协议本身是**无状态（Stateless）**的，服务器为了记住这个用户并将其多次请求关联起来，就必须引入会话跟踪机制。

以下是一个完整会话的核心生命周期和判断标准：

1. 会话的起点：第一次请求

当用户首次在浏览器中输入网址并访问 Java Web 应用（例如访问一个 Servlet 或 JSP 页面）时，会话正式开始：

- 服务器在后台执行 `request.getSession()` 或 `request.getSession(true)`。
- 服务器会为该用户创建一个唯一的 **`HttpSession` 对象**。
- 服务器会生成一个全局唯一的标识符，称为 **`JSESSIONID`**。
- 服务器通过 HTTP 响应头（`Set-Cookie: JSESSIONID=xxxxxx`），将这个 ID 发送并保存在客户端的浏览器 Cookie 中。
- 会话的维持：连续的交互

在会话开始后，只要用户不关闭浏览器，且连续点击链接或提交表单：

- 浏览器在随后的**每一次 HTTP 请求**中，都会自动通过请求头（`Cookie: JSESSIONID=xxxxxx`）把这个 ID 带回给服务器。
- 服务器根据请求中的 `JSESSIONID` 找到内存中对应的 `HttpSession` 对象。
- 这样，服务器就能跨多个页面记住用户的状态（例如：登录状态、购物车商品）。
- 会话的终点：什么情况下算会话结束？

一个会话的结束通常由以下三种情况之一决定：

- **浏览器关闭（默认情况）**：
  保存 `JSESSIONID` 的 Cookie 默认是**会话 Cookie（Session Cookie）**，它只保存在浏览器的内存中。一旦用户关闭整个浏览器，该 Cookie 就会被销毁。下次再打开浏览器访问时，由于没有带上原来的 `JSESSIONID`，服务器会认为这是一个全新的会话。

- **会话超时失效（Timeout）**：
  如果用户打开了网页但长时间不进行任何操作，服务器为了释放内存，会自动销毁该会话。

  - 可以在 `web.xml` 中配置超时时间（单位为分钟）：

    

    xml

    ```
    <session-config>
        <session-timeout>30</session-timeout> <!-- 30分钟无操作则销毁 -->
    </session-config>
    ```

      

- **手动注销/销毁**：
  当用户点击“退出登录”时，后台代码显式调用了销毁方法：

  

  java

  ```
  session.invalidate(); // 立即销毁当前会话
  ```

    

💡 核心总结与误区澄清

- **算作一次完整会话的标准**：只要 `JSESSIONID` 没有发生改变，且服务器端的 `HttpSession` 对象一直存在并处于活跃状态，这期间所有的请求和响应都属于**同一次会话**。
- **常见误区**：很多人认为“关闭当前标签页”会话就结束了。实际上，**关闭单个标签页并不会结束会话**，只要同一个浏览器的其他窗口/标签页还开着，Cookie 依然存在，会话就依然有效。

## 如果我们在浏览器里面设置阻止cookie，那么设置之后，session就没有作用了，因为服务器是依靠cookie里面设置的JSESSIONID来在session列表里面查找对应的session

![image-20261017161002498](./note22-会话管理.assets/image-20261017161002498.png)

![image-20261017161215243](./note22-会话管理.assets/image-20261017161215243.png)

## 这样子，每一次访问给session都不一样，非常麻烦

## 此时的解决办法就是在浏览器地址栏中添加sessionid,注意需要用分号隔开

![image-20261017161642098](./note22-会话管理.assets/image-20261017161642098.png)

## 这叫做url重写

![image-20261017161806599](./note22-会话管理.assets/image-20261017161806599.png)

## 强烈建议：不要在浏览器中禁用cookie

# 演练3.完善登录功能

## 1.把web15项目重新部署到服务器，注意：部署的项目根路径是/dept

![image-20261017162933321](./note22-会话管理.assets/image-20261017162933321.png)

## 2.此时如果我们在浏览器里面直接输入部门列表的链接，是可以直接访问列表页面的数据的。这个其实不合理，我们需要当用户登录了才能让他跳转到list页面。我们需要修改LoginServlet，在用户登录成功后把用户名写到session中，然后需要在list页面检测是否有这个用户名。我们修改LoginServlet的代码如下

![image-20261017170129165](./note22-会话管理.assets/image-20261017170129165.png)

## 3.然后我们需要在DeptListServlet的代码里面添加检查用户名的代码，如果用户名为空，跳转到登录页面，但是其他的页面也需要这个功能所以，我们需要写一个过滤器LoginCheckFilter

![image-20261017192343316](./note22-会话管理.assets/image-20261017192343316.png)

### 代码如下

```
package org.kenny.dept.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //转换
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String servletPath = req.getServletPath();
        //判断用户是否登录
        // /*会拦截所有路径包括/，这会导致网站无法正常执行，我们需要给/css ，/login和/index放行，次数需要判断是否是/index和/login 和 /css
        //如果是这些路径，不需要拦截，此外，图片，js文件都需要放行
        HttpSession session = req.getSession(false); //这里false表示只获取当前session，获取不到不新建，也就是指定使用登录时创建的session对象
        if("/index".equals(servletPath) || "/login".equals(servletPath) || servletPath.startsWith("/css")
                || (session!=null && session.getAttribute("username") != null)) {
                chain.doFilter(request,response); //这里不能够使用自己的doFilter.而需要使用chain.doFilter
        } else{
            resp.sendRedirect(req.getContextPath()+"/index");
        }
    }
}

```

### 此时，你没有登录的话，访问/list，/detail，/delete，/edit都会被重定向到/index。登录后就没有这个问题

# 演练4.完整检查登录的过滤器

## 1.我们需要重新检查一下我们的过滤器，一下情况不要拦截

1》用户正在登录

2》用户访问登录页面

3》访问图片css等等精通资源

4》已经登录了的用户不需要拦截

## 2.我们把LoginCheckFilter的代码改为下面的样子

```
package org.kenny.dept.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //转换
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String servletPath = req.getServletPath();
        //判断用户是否登录
        // /*会拦截所有路径包括/，这会导致网站无法正常执行，我们需要给/css ，/login和/index放行，次数需要判断是否是/index和/login 和 /css
        //如果是这些路径，不需要拦截，此外，图片，js文件都需要放行
        HttpSession session = req.getSession(false); //这里false表示只获取当前session，获取不到不新建，也就是指定使用登录时创建的session对象
        if("/index".equals(servletPath) || "/login".equals(servletPath) || servletPath.startsWith("/css")||servletPath.startsWith("/images")
            || servletPath.startsWith("/js")    || (session!=null && session.getAttribute("username") != null)) {
                chain.doFilter(request,response); //这里不能够使用自己的doFilter.而需要使用chain.doFilter
        } else{
            //重定向，需要判断"/index".equals(servletPath)
            //resp.sendRedirect(req.getContextPath()+"/index");
            //这里可以使用转发，转发它不走过滤器,不需要判断"/index".equals(servletPath)
            //req.setAttribute("template","index");
            //req.getRequestDispatcher("/view").forward(request,response);
            //也可以这么写
            req.getRequestDispatcher("/index").forward(request,response);
        }
    }
}

```

### 注意，getServletPath方法返回的路径不带项目名称它只是返回/list这样子的路径

# 演练5.在每一个页面显示已经登录的用户

## 1.首先需要在css文件夹里面新建一个common.css文件，内容参考上面的笔记

## 2.在 `add.html`、`list.html`、`edit.html`、`detail.html`里面添加显示用户的代码和引入common.css文件

![image-20261019125624973](./note22-会话管理.assets/image-20261019125624973.png)

![image-20261019125839709](./note22-会话管理.assets/image-20261019125839709.png)

![image-20261019130226834](./note22-会话管理.assets/image-20261019130226834.png)

![image-20261019130414926](./note22-会话管理.assets/image-20261019130414926.png)

![image-20261019130652142](./note22-会话管理.assets/image-20261019130652142.png)

## 3.然后把用户名一栏的数据使用动态获取的数据。可以在session里面获取用户名，只需要把th:text的内容拷贝粘贴到其他页面即可

![image-20261019141637216](./note22-会话管理.assets/image-20261019141637216.png)

### 效果，其它页面也是一样的效果

![image-20261019141839332](./note22-会话管理.assets/image-20261019141839332.png)

# 演练6.实现退出系统功能

## 1.在退出登录超链接里面把href修改一下，然后复制粘贴到其它页面

![image-20261019144156061](./note22-会话管理.assets/image-20261019144156061.png)

## 2.然后我们需要创建一个LogoutServlet，继承HttpServlet，实现doGet方法，代码比较简单，销毁session对象然后重定向到登录页面

![image-20261019145401513](./note22-会话管理.assets/image-20261019145401513.png)

### 效果：先登录，

![image-20261019145449205](./note22-会话管理.assets/image-20261019145449205.png)



### 然后退出

![image-20261019145510322](./note22-会话管理.assets/image-20261019145510322.png)

![image-20261019145525647](./note22-会话管理.assets/image-20261019145525647.png)

# 演练7 cookie学习

## 1.还是是web16模块，新建一个MyCookieServlet,继承HttpServlet，重写doGet方法，设置请求路径为/cookie，然后我们创建一个Cookie，先不设置属性，然后直接响应给客户端，看看它有没有默认值

![image-20261019153603402](./note22-会话管理.assets/image-20261019153603402.png)

## 2.重新部署到服务器，然后启动服务器，在浏览器中输入http://localhost:8080/web16/cookie并且刷新，然后打开调试工具，点击网络选项卡，就可以看到cookie

![image-20261019155029767](./note22-会话管理.assets/image-20261019155029767.png)



## 3.点击应用程序选项卡，然后点击Cookies，可以cookie关联的路径是/web16，也就是说访问所有/web16的子路径都会携带这个cookie，默认的生命周期是会话，也就是关闭浏览器，就会销毁cookie

![image-20261019155356298](./note22-会话管理.assets/image-20261019155356298.png)



## 4.cookie默认值的以及他的含义如图

![image-20261019160726568](./note22-会话管理.assets/image-20261019160726568.png)

## 5.下面我们来练习手动设置一下

![image-20261019162306117](./note22-会话管理.assets/image-20261019162306117.png)

### 重启服务器，清除原来的cookie思考，然后访问http://192.168.100.138:8080/web16/cookie, 效果如下，在网络选项卡里面

![image-20261019163221349](./note22-会话管理.assets/image-20261019163221349.png)

### 在应用程序选项卡里面

![image-20261019163049739](./note22-会话管理.assets/image-20261019163049739.png)



## 演练7.2 java代码获取浏览器提交的cookie

## 1.还是web16模块，编写一个AcceptCookieServlet,继承HttpServlet，然后实现doGet方法。映射路径/accept

![image-20261020110518577](./note22-会话管理.assets/image-20261020110518577.png)

## 2.为了方便我们测试，我们把MyCookieServlet里面的setDomain方法的代码注释了，这样子我们就不用输入ip地址来访问了，为了测试结果，我们把用户名改为boss

![image-20261020111615715](./note22-会话管理.assets/image-20261020111615715.png)

## 3.然后我们在AcceptCookieServlet里面添加获取cookie的代码

![image-20261020112320744](./note22-会话管理.assets/image-20261020112320744.png)

### 效果

![image-20261020112347875](./note22-会话管理.assets/image-20261020112347875.png)



![image-20261020113735080](./note22-会话管理.assets/image-20261020113735080.png)

![image-20261020115239968](./note22-会话管理.assets/image-20261020115239968.png)



# 演练8.实现保持长时间登录状态，(安全级别高的系统没有这个功能，如网银)

## 我们的dept项目目前还没有保持长时间登录的功能，也就是7天或者更多天免登录，这个功能需要cookie的帮助才能实现。后面，我们会增加token使它更加安全。

## 1.使用web15项目，先重新把它部署到服务器，跟路径改为/dept

![image-20261020115924657](./note22-会话管理.assets/image-20261020115924657.png)

## 2.先来改造LoginServlet，在用户登录成功后，我们除了设置session以外，还需要创建一个cookie，设置它的过期时间为7天(60X60X24X7),设置HttpOnly为true，设置路径为项目的跟路径，如果是https连接，我们还需要设置安全为true

![image-20261020161711240](./note22-会话管理.assets/image-20261020161711240.png)

## 3.启动服务器，然后正常登录，打开调试工具，点击应用程序选项卡，发现有2个cookie，一个是session需要的，生命周期就是会话，另外一个是我们设置的，7天过期的cookie

![image-20261020162159400](./note22-会话管理.assets/image-20261020162159400.png)

## 4.然后我们关闭浏览器，再重新打开，直接在浏览器的地址栏输入http://localhost:8080/dept/list，发现此时弹出登录窗口，我们在调试工具里面查看应用程序选项卡，发现会话生命周期的cookie没有了，所以它让我们重写登录，但是7天到期的cookie还没有到期，按理说不需要登录，所以我们需要调整登录过滤器的代码。

![image-20261020163116782](./note22-会话管理.assets/image-20261020163116782.png)

## 5.调整登录检查过滤器的代码如下，思路是先从浏览器获取cookie，看看有没有7天过期的那个cookie,如果有看看他的名字是不是username,如果是。我们就把这个用户名获取出来。然后设置到session里面，然后放行，并且返回，因为已经方行了，下面的代码就不需要执行。

![image-20261020164746650](./note22-会话管理.assets/image-20261020164746650.png)

### 当然，还有另外一种写法，比较麻烦

![image-20261020165100102](./note22-会话管理.assets/image-20261020165100102.png)

## 6.我们把chrome浏览器里面的cookie删除，关闭浏览器，然后打开，此时输入http://localhost:8080/dept/list 会弹出登录页面，这是正常的。

![image-20261020165600524](./note22-会话管理.assets/image-20261020165600524.png)

## 7.我们输入正确的用户名和密码，点击登录，此时会登录成功，然后我们直接访问任何页面都不需要再登录

![image-20261020165718237](./note22-会话管理.assets/image-20261020165718237.png)

![image-20261020165737297](./note22-会话管理.assets/image-20261020165737297.png)

![image-20261020165754659](./note22-会话管理.assets/image-20261020165754659.png)

![image-20261020165826714](./note22-会话管理.assets/image-20261020165826714.png)

![image-20261020170139438](./note22-会话管理.assets/image-20261020170139438.png)

![image-20261020170204887](./note22-会话管理.assets/image-20261020170204887.png)

![image-20261020170222628](./note22-会话管理.assets/image-20261020170222628.png)

![image-20261020170239139](./note22-会话管理.assets/image-20261020170239139.png)

![image-20261020171502733](./note22-会话管理.assets/image-20261020171502733.png)

![image-20261020171521828](./note22-会话管理.assets/image-20261020171521828.png)

### 注意，此时如果你输入~dept/,也就是项目的跟路径，你会发现，它会要求你登录

![image-20261020172021745](./note22-会话管理.assets/image-20261020172021745.png)

## 8.这是因为我们没有在这里对它进行处理，接下来我们对他进行改造一下

![image-20261020175237495](./note22-会话管理.assets/image-20261020175237495.png)

### 重启服务器，就会展示列表页面，因为那个7天有效的cookie还是在

![image-20261020175347002](./note22-会话管理.assets/image-20261020175347002.png)

## 9.如果此时我们删除浏览器里面保存的cookie数据，然后打开浏览器，此时就需要登录

![image-20261020175458187](./note22-会话管理.assets/image-20261020175458187.png)

![image-20261020175534975](./note22-会话管理.assets/image-20261020175534975.png)
