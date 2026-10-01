1. ## 前言

在前面我们学习MySQL数据库时，都是利用图形化客户端工具(如：idea、datagrip)，来操作数据库的。

我们做为后端程序开发人员，通常会使用Java程序来完成对数据库的操作。Java程序操作数据库的技术呢，有很多啊，而最为底层、最为基础的就是JDBC。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YjdiMTNlOTNjNzlkNWUwYjE4MGNkYTAzOWM0MGZjZTBfSHNuNHhKVFdXNFpQMmt0aFdraGduZ3IzUU5UbWhuWlVfVG9rZW46SGxSdWJWa05sb3V6em14bzk0ZmNCUEJmbmdmXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

**JDBC：**（Java DataBase Connectivity），就是使用Java语言操作关系型数据库的一套API。 【是操作数据库最为基础、底层的技术】

但是使用JDBC来操作数据库，会比较繁琐，所以现在在企业项目开发中呢，一般都会使用基于JDBC的封装的高级框架，比如：Mybatis、MybatisPlus、Hibernate、SpringDataJPA。 

而这些技术，目前的市场占有份额如下图所示：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MTg4Zjc0NWZmOWUwNzJhOTJkYWYzY2I3ZGMwNWEzYTNfRVJDVHR3OWZQeHZrSXk0SWF5SnVMeXZzNlpHSFVQaERfVG9rZW46Um1wTWJzd3FybzFQWDB4d0pxcWM3S1dwbmZjXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

从上图中，我们也可以看到，目前最为主流的就是Mybatis，其次是MybatisPlus。

所以，在我们的课程体系中呢，这两种主流的操作数据库的框架我们都要学习。 而我们在学习这两个主流的框架之前，还需要学习一下操作数据库的基础基础 JDBC。 然后接下来，再来学习Mybatis。 而在我们后面的课程中，我们还要学习MybatisPlus框架。 那么今天呢，我们就先来学习 JDBC 和 Mybatis。

**今天课程安排：**

1. JDBC
2. Mybatis
3. SpringBoot配置文件

1. ## JDBC

1. ### 介绍

**JDBC：（Java DataBase Connectivity），就是使用Java语言操作关系型数据库的一套API。**

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZGI2ZGI0MGE1NmY1YmJmYzNmZjZkMjBkNmI3MjRiM2JfNjZvTmh5NUhrbTUyZHBCT014S3VPTmVES29Fa0p4SXNfVG9rZW46TmIwTGJ0WHY1b2dwSnF4eHU1S2NOcVhwbnhiXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

**本质：**

- sun公司官方定义的一套操作所有关系型数据库的规范，即接口。
- 各个数据库厂商去实现这套接口，提供数据库驱动jar包。
- 我们可以使用这套接口(JDBC)编程，真正执行的代码是驱动jar包中的实现类。

那有了JDBC之后，我们就可以直接在java代码中来操作数据库了，只需要编写这样一段java代码，就可以来操作数据库中的数据。 示例代码如下：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NmFlMTYxYTcxZDJhNzA5YWUwYjFkNTQ0ZmNmNTAyMWNfckJ6UzRvQ05MUmJDalJzS1czREVHZnpaVFg1dkhTZThfVG9rZW46Qjg1UGJRZzZWb0JXWWN4QXFyYWNaUVgxbkxkXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

1. ### 查询数据

1. #### 需求

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NDlhMjE3YWJiYWMxODFjMTZkMjdjMDU3ZTljZDI3MGVfTU84MnBVT1VaU0dFb09zS2JnN3gxbTE3emNWUWtOZVVfVG9rZW46UGFXbmJyMWo0b0VCZnR4Y3MxR2NvS1lubkJoXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

**需求：**基于JDBC实现用户登录功能。

**本质：**其本质呢，其实就是基于JDBC程序，执行如下select语句，并将查询的结果输出到控制台。SQL语句：

```SQL
select * from user where username = 'linchong' and password = '123456';
```

1. #### 准备工作

1). 创建一个maven项目

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YTJhZmVmMzQ2Njg4NTMwOWE1YmM0OWI2NzY5ZmFlOTdfMU1XRXpmMUJsQnk1MTI2eW1iSTB6aThLV2ZudzRUcWdfVG9rZW46QWphZmJ6d25yb21yRXF4U2lDWWNIUmZlbm5iXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

2). 创建一个数据库 web，并在该数据库中创建user表

```SQL
create table user(
    id int unsigned primary key auto_increment comment 'ID,主键',
    username varchar(20) comment '用户名',
    password varchar(32) comment '密码',
    name varchar(10) comment '姓名',
    age tinyint unsigned comment '年龄'
) comment '用户表';

insert into user(id, username, password, name, age) values (1, 'daqiao', '123456', '大乔', 22),
                                                           (2, 'xiaoqiao', '123456', '小乔', 18),
                                                           (3, 'diaochan', '123456', '貂蝉', 24),
                                                           (4, 'lvbu', '123456', '吕布', 28),
                                                           (5, 'zhaoyun', '12345678', '赵云', 27);
```

1. #### 代码实现

**AI提示词（prompt）：**

你是一名java开发工程师，帮我基于JDBC程序来操作数据库，执行如下SQL语句：

select * from user where username = 'daqiao' and password = '123456'

**具体的代码为：**

1). 在 pom.xml 文件中引入依赖

```XML
<dependencies>
    <!-- MySQL JDBC driver -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.30</version>
    </dependency>

    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.9.3</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

2). 在 `src/main/test/java` 目录下编写测试类，定义测试方法

```Java
public class JDBCTest {

    /**
     * 编写JDBC程序, 查询数据
     */
    @Test
    public void testJdbc() throws Exception {
        // 获取连接
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web", "root", "1234");
        // 创建预编译的PreparedStatement对象
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
        // 设置参数
        pstmt.setString(1, "daqiao"); // 第一个问号对应的参数
        pstmt.setString(2, "123456"); // 第二个问号对应的参数
        // 执行查询
        ResultSet rs = pstmt.executeQuery();
        // 处理结果集
        while (rs.next()) {
            int id = rs.getInt("id");
            String uName = rs.getString("username");
            String pwd = rs.getString("password");
            String name = rs.getString("name");
            int age = rs.getInt("age");

            System.out.println("ID: " + id + ", Username: " + uName + ", Password: " + pwd + ", Name: " + name + ", Age: " + age);
        }
        // 关闭资源
        rs.close();
        pstmt.close();
        conn.close();
    }

}
```

而上述的单元测试中，我们在SQL语句中，将将 用户名 和密码的值都写死了，而这两个值应该是动态的，是将来页面传递到服务端的。 那么，我们可以基于前面所讲解的JUnit中的参数化测试进行单元测试，代码改造如下：

```Java
public class JDBCTest {

    /**
     * 编写JDBC程序, 查询数据
     */
    @ParameterizedTest
    @CsvSource({"daqiao,123456"})
    public void testJdbc(String _username, String _password) throws Exception {
        // 获取连接
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web", "root", "1234");
        // 创建预编译的PreparedStatement对象
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
        // 设置参数
        pstmt.setString(1, _username); // 第一个问号对应的参数
        pstmt.setString(2, _password); // 第二个问号对应的参数
        // 执行查询
        ResultSet rs = pstmt.executeQuery();
        // 处理结果集
        while (rs.next()) {
            int id = rs.getInt("id");
            String uName = rs.getString("username");
            String pwd = rs.getString("password");
            String name = rs.getString("name");
            int age = rs.getInt("age");

            System.out.println("ID: " + id + ", Username: " + uName + ", Password: " + pwd + ", Name: " + name + ", Age: " + age);
        }
        // 关闭资源
        rs.close();
        pstmt.close();
        conn.close();
    }

}
```

如果在测试时，需要传递一组参数，可以使用  `@CsvSource` 注解。

1. #### 代码剖析

1. ##### ResultSet

ResultSet（结果集对象）：封装了DQL查询语句查询的结果。

- next()：将光标从当前位置向前移动一行，并判断当前行是否为有效行，返回值为boolean。
  - true：有效行，当前行有数据
  - false：无效行，当前行没有数据
- getXxx(…)：获取数据，可以根据列的编号获取，也可以根据列名获取（推荐）。

结果解析步骤：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MjAzOWRjOWVmMTk3ZTU1MTk4YmI3YjZiNzAxMDE2OGNfRGlMS3hHNERIZ3hkT0E1QjlFVkJuUkJSb1RXRk1WN2RfVG9rZW46TWFwT2JIQVVzb0NDeDV4RVVIRWNFbk9XblJiXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

1. ##### 预编译SQL

其实我们在编写SQL语句的时候，有两种风格：

- 静态SQL（参数硬编码）

```Java
conn.prepareStatement("SELECT * FROM user WHERE username = 'daqiao' AND password = '123456'");
ResultSet resultSet = pstmt.executeQuery();
```

这种呢，就是参数值，直接拼接在SQL语句中，参数值是写死的。

- 预编译SQL（参数动态传递）

```Java
conn.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
pstmt.setString(1, "daqiao");
pstmt.setString(2, "123456");
ResultSet resultSet = pstmt.executeQuery();
```

这种呢，并未将参数值在SQL语句中写死，而是使用 ？ 进行占位，然后再指定每一个占位符对应的值是多少，而最终在执行SQL语句的时候，程序会将SQL语句（SELECT * FROM user WHERE username = ? AND password = ?），以及参数值（"daqiao", "123456"）都发送给数据库，然后在执行的时候，会使用参数值，将？占位符替换掉。

那这种预编译的SQL，也是在项目开发中推荐使用的SQL语句。主要的作用有两个：

- 防止SQL注入
- 性能更高

那接下来，我们就来介绍一下这两点。

1. ###### SQL注入

- SQL注入：通过控制输入来修改事先定义好的SQL语句，以达到执行代码对服务器进行攻击的方法。 

SQL注入最典型的场景，就是用户登录功能。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=OTEwMGM0NGM3Y2U1N2UxZGZlNDIxYzI0OTczZjZmNThfbnVRek0xamVBa2k4cTJKZTJSczNmVWROYjNoMUZIcFNfVG9rZW46Wjl6cGJvT2o3b0hYam94WHNvWGNEOVdPbnNkXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

**注入演示：**

1). 打开课程资料中的文件夹 `资料/02. SQL注入演示`，运行其中的jar包 `sql_Injection_demo-0.0.1-SNAPSHOT.jar`，进入该目录后，执行命令：

```Java
java -jar sql_Injection_demo-0.0.1-SNAPSHOT.jar
```

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZWI2YTJjMTcxY2EzNzRjYjIwMDUzNGU5MTkzNjNkNDNfcExuamNzazZNTXBld29RQ3d2ZHM2UlloY29WWWVWdDZfVG9rZW46TnFtTWI5bkVhb2gxZzR4THpVSmNGeXc3bkZnXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

2). 打开浏览器访问 http://localhost:9090/ ，必须登录后才能访问到系统。我们先测试正常的用户名和密码

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZjQyNTRjZTgyYzJhY2JmNjZiNjBjYTg4ZGFlYjQyZDBfVThvWEFsVDlkOTd2VGZVU3M4aml5c0E4ZFJEUVkyTnJfVG9rZW46Uk5wWWJsMkJtb1Fia3Z4aGVLaWNwdE5HblhlXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZThlZDA5Mjk1YTRjOGEwYjEwOTVmNDFiOWViOTVlYjFfa2FmalR6RmcxdXVRbWduZlpFOEwycWhFOXU4RXVGU1hfVG9rZW46VFdwdmJuMHFVbzNVekx4TGJ6S2NEaVpibnVvXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

3). 接下来，我们再来测试一下错误的用户名和密码 。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=Njk5ZWM1NjU0YTdkYTMxNGYyNDRhMGE0YjBiNzllNmZfQkJBdWR2aFNDb1N4RlFncmFITDB3ZlNWNTN0aGpEVzRfVG9rZW46S0drcWJ5TzJXb1ZRRjl4TUFHeGNIMzFEbnFmXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

我们看到，如果用户名密码错误，是不能进入到系统中进行访问的，会提示 `用户名和密码错误`。

4). 那接下来，我们就要演示一下SQL注入现象，我们可以通过控制表单输入，来修改事先定义好的SQL语句的含义。 从而来攻击服务器。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MTk3OTUzZjU2NWZhYmIzYzg3NDQ4ODg5NDYzMGY0NzRfTWZkU0tDeVRYaEREeDVsdjBPbVZiMzRGZ0FQVUlmMFpfVG9rZW46RDVkSWJkN1VNb0hxWld4d25LV2NsZWo5bk9kXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

点击登录后，我们看到居然可以成功进入到系统中。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MjY2YjU2N2VlMGIzZjEwODI1Y2JhOTI0MWVmODJmZDRfOWtvT0FLS0I3VmZFM2wzZ1JBeWZuN2RMWVljRHN4cWlfVG9rZW46Q0tSb2JvUkd0bzBzend4ZHQycWM2aEZTbjBkXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

为什么会出现这种现象呢？

在进行登录操作时，怎么样才算登录成功呢？ 如果我们查询到了数据，就说明用户名密码是对的。 如果没有查询到数据，就说明用户名或密码错误。

而出现上述现象，原因就是因为，我们我们编写的SQL语句是基于字符串进行拼接的 。 我们输入的用户名无所谓，比如：`shfhsjfhja` ，而密码呢，就是我们精心设计的，如：`' or '1' = '1` 。

那最终拼接的SQL语句，如下所示：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NDhjMjdhZDgxNzlhNDZkYzZlMjlhMjU4ODg3NjYxNmVfMklVSWd0dmNvQ3JSaFdqcnI1bHZuenlvZWVNQkp1SDhfVG9rZW46R3p0aWIxRXhpbzd2U014ckxXZ2NHSkZEbnlnXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

我们知道，`or` 连接的条件，是或的关系，两者满足其一就可以。 所以，虽然用户名密码输入错误，也是可以查询返回结果的，而只要查询到了数据，就说明用户名和密码是正确的。

1. ###### SQL注入解决

而通过预编译SQL（select * from user where username = ? and password = ?），就可以直接解决上述SQL注入的问题。 接下来，我们再来演示一下，通过预编译SQL是否能够解决SQL注入问题。

1). 打开课程资料中的文件夹 `资料/02. SQL注入演示`，运行其中的jar包 `sql_prepared_demo-0.0.1-SNAPSHOT.jar`，进入该目录后，执行命令：

```Java
java -jar sql_prepared_demo-0.0.1-SNAPSHOT.jar
```

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MzNmY2U4NTUyZDliNzk1MzAwNzRhMzY4YmYyODJhYWVfZmVTVUtKQ1d1aDZrOWpyYzg2STN3NzlJcjdYTXdmUFdfVG9rZW46RkFqcWJKa0Jtb2VGT214aXNFTWNWR0xDbnBoXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

2). 打开浏览器访问 `http://localhost:9090/` ，必须登录后才能访问到系统 。我们先测试正常的用户名和密码 

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YzlmOGNiOWJmMGRkYmM0ZmQ4NWEyYjg1YjQ4NGVhNThfcjQ5eTI5aG11blREUmVSR2FGSDVSOWdkNlF1Yk5wdmVfVG9rZW46QkZldGJ4bEhVbzRqaHF4WDU4bGNuakgybkRjXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MjM2M2QwOTJmZjgxMDE0M2ExZDgwOWE0MmZmMGQ5MzhfeWJSc09BaXdYMm4zdExMOGdXY0RmUEFOSzBVVEhacjBfVG9rZW46UW01WmJwZzd5b3lxVTZ4MXVoZmM0cmx1bnpjXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

3). 那接下来，我们就要演示一下是否可以基于上述的密码 `' or '1' = '1`，来完成SQL注入 。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NmQyODM1YjBlMGQ1MTA2YzRlY2JhMjdjZmY4MTFiMDBfZ2FaQkRlOXRyc2tsN1p2WlpRUkRQRzd3ZW5obnBIR2FfVG9rZW46QjNaN2JCQWR0b2plcmt4ZEpTWmNYZTR2bnVnXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

通过控制台，可以看到输入的SQL语句，是预编译SQL语句。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=N2RlNDRiZGM5ZGRiMzNhMjJlNjAzODFkZmNkMjcwN2JfeVQ1SXhmSzJIYXJoWVdYY0R1QXNoSU53Y2haZ0dManBfVG9rZW46T1lvaWJ3U0RRb0Vycnh4VWpUMmNYWUlwbndLXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

而在预编译SQL语句中，当我们执行的时候，会把整个`' or '1'='1`作为一个完整的参数，赋值给第2个问号（`' or '1'='1`进行了转义，只当做字符串使用）

那么此时再查询时，就查询不到对应的数据了，登录失败。

**注意：在以后的项目开发中，****我们使用的基本全部都是预编译SQL语句。** 

1. ###### 性能更高

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZGE0ZDE4MmZjNzI3MDQ3ODZiM2UzNWQwNzdmZjRmNzFfeHBFemlDcXpSSHB2Vkp0cG9Vd1A3RnNSc2tBdkx3czRfVG9rZW46U1VtemIyWVlEb2NRenl4NlNnQWNTR1IwbmZoXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

1. ### 增删改数据

1. #### 需求

- 需求：基于JDBC程序，执行如下update语句。
- SQL：

```Java
update user set password = '123456', gender = 2 where id = 1;
```

1. #### 代码实现

**AI提示词（prompt）：**

你是一名java开发工程师，帮我基于JDBC程序来操作数据库，执行如下SQL语句：

update user set password = '123456', gender = 2 where id = 1;

代码实现如下：

```Java
@ParameterizedTest
@CsvSource({"1,123456,25"})
public void testUpdate(int userId, String newPassword, int newAge) throws Exception {
    // 建立数据库连接
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web", "root", "1234");
    // SQL 更新语句
    String sql = "UPDATE user SET password = ?, age = ? WHERE id = ?";
    // 创建预编译的PreparedStatement对象
    PreparedStatement pstmt = conn.prepareStatement(sql);

    // 设置参数
    pstmt.setString(1, newPassword); // 第一个问号对应的参数
    pstmt.setInt(2, newAge);      // 第二个问号对应的参数
    pstmt.setInt(3, userId);         // 第三个问号对应的参数

    // 执行更新
    int rowsUpdated = pstmt.executeUpdate();

    // 输出结果
    System.out.println(rowsUpdated + " row(s) updated.");

    // 关闭资源
    pstmt.close();
    conn.close();
}
```

- JDBC程序执行DML语句：int rowsUpdated = pstmt.executeUpdate();  //返回值是影响的记录数
- JDBC程序执行DQL语句：ResultSet resultSet = pstmt.executeQuery(); //返回值是查询结果集

1. ## Mybatis

1. ### 介绍

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MGM1ODhmM2U1YmFjODAxYzE2YjVmNGJiNzExOTdlMTJfV1dYNmxDNDl2RWNsY29ncTVnRGNEOTY0SWpTZVN1MVdfVG9rZW46SEhZaWI3enZxb2Viem14OGU1c2NwUzNjbldiXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

什么是MyBatis?

- MyBatis是一款优秀的 **持久层** **框架**，用于简化JDBC的开发。
- MyBatis本是 Apache的一个开源项目iBatis，2010年这个项目由apache迁移到了google code，并且改名为MyBatis 。2013年11月迁移到Github。
- 官网：https://mybatis.org/mybatis-3/zh/index.html 

在上面我们提到了两个词：一个是持久层，另一个是框架。

- 持久层：指的是就是数据访问层(dao)，是用来操作数据库的。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MzVmOWNiOTUxYWUwMjQ2NDQ5MTg0NmJhZmQwNTY1MzFfUG9tUFZRWmp5WW0wRktqdkxyVDRXNmdrUUZiMzFsSHRfVG9rZW46REdjT2I4bjRIb2Z6VWp4bHpnZWNIaGhBbjFnXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

- 框架：是一个半成品软件，是一套可重用的、通用的、软件基础代码模型。在框架的基础上进行软件开发更加高效、规范、通用、可拓展。

通过Mybatis就可以大大简化原生的JDBC程序的代码编写，比如 通过 `select * from user` 查询所有的用户数据，通过JDBC程序操作呢，需要大量的代码实现，而如果通过Mybatis实现相同的功能，只需要简单的三四行就可以搞定。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NGEwZjg2NzE2NTUwM2E5MDJmYjE2YzY5NDM3MDczZWFfNEtYV3o5MmhpcTc1VEd4ZWpHaWFZS25pYmswWkFzeXBfVG9rZW46UVdFSWJvUDgxb2cxVmZ4dHNtaGM3bmxFbk1mXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

1. #### 快速入门

需求：使用Mybatis查询所有用户数据 。

**1). 创建springboot工程，并导入 mybatis的起步依赖、mysql的驱动包、lombok。**

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZTA3MzM4NmY4ZjgwZDZkMDYxYTUwNGNlOTY1ZWQ5NDBfUGNZRzlKTkdyVE52N0NBczhMQkY3dUx6dFI2WlRIMWJfVG9rZW46TUhlZmJXOWNSb2toRHF4NURLV2NBWW9qbmllXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=OTVjZWYzMDY3NDI1ODhmNjFkYTE2MDhhZTc3YmM3YWRfTGkxbVN3SXFwaTBsOWVzazI4N2RacmZoOHRnYkRCeENfVG9rZW46VGFGMWJFNHJQb29rZkZ4Z1o2T2NwZ2ljbkRlXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

项目工程创建完成后，自动在pom.xml文件中，导入Mybatis依赖和MySQL驱动依赖。如下所示：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MDI2MTE3ZmNiYzA0YTNkYTI2MTU1YWM5MzFkMTRlODRfQ1VQcEJudXhxdEhkVGlpekNWdk11ZWRqZUtIZ25DNVZfVG9rZW46VWxFT2JzazQ0b0dFS1J4VVRrcWNwV0JoblpFXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

**2). 数据准备：创建用户表user，并创建对应的实体类User。**

- 用户表 user（如果已经存在，就不用创建了）

```Java
create table user(
    id int unsigned primary key auto_increment comment 'ID,主键',
    username varchar(20) comment '用户名',
    password varchar(32) comment '密码',
    name varchar(10) comment '姓名',
    age tinyint unsigned comment '年龄'
) comment '用户表';

insert into user(id, username, password, name, age) values (1, 'daqiao', '123456', '大乔', 22),
                                                           (2, 'xiaoqiao', '123456', '小乔', 18),
                                                           (3, 'diaochan', '123456', '貂蝉', 24),
                                                           (4, 'lvbu', '123456', '吕布', 28),
                                                           (5, 'zhaoyun', '12345678', '赵云', 27);
```

- 实体类：实体类的属性名与表中的字段名一一对应。 实体类放在 `com.itheima.pojo` 包下。

```Java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id; //ID
    private String username; //用户名
    private String password; //密码
    private String name; //姓名
    private Integer age; //年龄
}
```

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NWFhM2I1ZTE1NTE2OGZkZDkwNzU1MDIzY2I2YWQzMjZfV0RiM08yT0JVNjZkUTcwUHJRR05HZjJQM054dWhXZHJfVG9rZW46SHpHR2JnU2Rwb0dwRER4MFdxYmNrSkg4blJlXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

**3). 配置Mybatis**

在 `application.properties` 中配置数据库的连接信息。

```Properties
#数据库访问的url地址
spring.datasource.url=jdbc:mysql://localhost:3306/web
#数据库驱动类类名
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#访问数据库-用户名
spring.datasource.username=root
#访问数据库-密码
spring.datasource.password=root@1234
```

上述的配置，可以直接复制过去，不要敲错了。 全部都是 `spring.datasource.xxxx` 开头。

**4). 编写Mybatis程序：编写Mybatis的持久层接口，定义SQL语句（注解）**

在创建出来的springboot工程中，在引导类所在包下，在创建一个包 `mapper` 。在 `mapper` 包下创建一个接口 `UserMapper` ，这是一个持久层接口（Mybatis的持久层接口规范一般都叫 XxxMapper）。

UserMapper接口的内容如下：

```Java
import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 查询全部
     */
    @Select("select * from user")
    public List<User> findAll();
}
```

**注解说明：**

- @Mapper注解：表示是mybatis中的Mapper接口

程序运行时，框架会自动生成接口的实现类对象(代理对象)，并给交Spring的IOC容器管理

- @Select注解：代表的就是select查询，用于书写select查询语句

**5). 单元测试**

在创建出来的SpringBoot工程中，在src下的test目录下，已经自动帮我们创建好了测试类 ，并且在测试类上已经添加了注解 `@SpringBootTest`，代表该测试类已经与SpringBoot整合。 

该测试类在运行时，会自动通过引导类加载Spring的环境（IOC容器）。我们要测试那个bean对象，就可以直接通过`@Autowired`注解直接将其注入进行，然后就可以测试了。 

测试类代码如下：

```Java
@SpringBootTest
class SpringbootMybatisQuickstartApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testFindAll(){
        List<User> userList = userMapper.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
```

运行结果：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NmUxMDU0NjYyOTY1NjZiNjZhMmQ1MGQzYzZmMjI2YjVfcG9yT0lPdUgzYjFWNnZJWnNMY1MwalNiT0R6N0tvY2FfVG9rZW46RzFFOWJXWFl4b3VyZUt4QUxnSmNBWU5PbldmXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

注意：测试类所在包，需要与引导类所在包相同。

1. #### 辅助配置

1. ##### 配置SQL提示

默认我们在UserMapper接口上加的 `@Select` 注解中编写SQL语句是没有提示的。 如果想让idea给我们提示对应的SQL语句，我们需要在IDEA中配置与MySQL数据库的链接。 

默认我们在UserMapper接口上的 `@Select` 注解中编写SQL语句是没有提示的。如果想让idea给出提示，可以做如下配置：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NjcyZjlhODk4OGJlNjJlMjkyMTQ0M2NjNDY5YWQxZjVfT2dnenZPb3p4ZmFONHp0WGxVYXdBZ0NodG5iRnhHellfVG9rZW46V1lKamJQRlJTb011Ulp4ejdKNmNDQ2lpbmJjXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

配置完成之后，发现SQL语句中的关键字有提示了，但还存在不识别表名(列名)的情况：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MjkwNmIyNmQ2NmU2MGQyZDkwNzkwMjc4NjQ4OTdlMDlfV1ZqUG1oUTlscXhoTFZiV2RJNUc2VjA4N045NFQ4ZldfVG9rZW46U3J4M2JmUnEwb1htWFR4aE9DOWNsQVNZbldmXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

- 产生原因：Idea和数据库没有建立连接，不识别表信息
- 解决方案：在Idea中配置MySQL数据库连接

按照如下方如下方式，来配置当前IDEA关联的MySQL数据库（必须要指定连接的是哪个数据库）。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MjA0OTc2YzE1Mzk4ZmRlNjdjNTA5OGQ1ZmNiODE5ZmFfTXFweUpuUkt2Tkd0bFgwZ0Q2RWUxVXFxT1dTWDk2UU5fVG9rZW46Sm9VQWJSNlFtbzRST014a0hlMGN4UHVMbmtkXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZTU4MzcwOGYwYjZhZmQxNWYwN2YwMTRjNmQzODE3YTFfc3J5S3lVcnNLUHdvYU9wWkNBNTNNdjZXRmlVOHhMMEpfVG9rZW46WGNPQ2J5bEVHb0dMRHF4UThUQ2M0ZkIybnhlXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

在配置的时候指定连接那个数据库，如上图所示连接的就是mybatis数据库（自己的数据库名是什么就指定什么）。

**注意：**该配置的目的，仅仅是为了在编写SQL语句时，有语法提示（写错了会报错），不会影响运行，即使不配置也是可以的。

1. ##### 配置Mybatis日志输出

默认情况下，在Mybatis中，SQL语句执行时，我们并看不到SQL语句的执行日志。 在`application.properties`加入如下配置，即可查看日志： 

```Properties
#mybatis的配置
mybatis.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
```

打开上述开关之后，再次运行单元测试，就可以看到控制台输出的SQL语句是什么样子的。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZjhmYmNjYjQzYzYyNDkxNzFlMWFjNjdkMmYyNjRjNjRfWnlUSGpPZjBJS1p1Z0pDNVdESzNjN3Y1MjU4RWxJSndfVG9rZW46VWk5SWJ6MHpibzl0Tkh4RWE0ZmMwNlhEbmZmXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

1. #### JDBC VS  Mybatis

JDBC程序的**缺点**：

- url、username、password 等相关参数全部硬编码在java代码中。
- 查询结果的解析、封装比较繁琐。
- 每一次操作数据库之前，先获取连接，操作完毕之后，关闭连接。 频繁的获取连接、释放连接造成资源浪费。

分析了JDBC的缺点之后，我们再来看一下在mybatis中，是如何解决这些问题的：

- 数据库连接四要素(驱动、链接、用户名、密码)，都配置在springboot默认的配置文件 application.properties中
- 查询结果的解析及封装，由mybatis自动完成映射封装，我们无需关注
- 在mybatis中使用了数据库连接池技术，从而避免了频繁的创建连接、销毁连接而带来的资源浪费。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MzNjMDg0ZTM4Yjg5YzdmZjVkNTUwZGIyM2RmNDQ1NTNfc0FvTXdQOThCak9yaTczQTd5VUpVQ0kyblRoa2hxcTZfVG9rZW46TTBnSmJGcEFob2dTRkx4UDRkdmN3N0RIblB0XzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

使用SpringBoot+Mybatis的方式操作数据库，能够提升开发效率、降低资源浪费

而对于Mybatis来说，我们在开发持久层程序操作数据库时，需要重点关注以下两个方面：

1. application.properties

```Properties
#驱动类名称
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#数据库连接的url
spring.datasource.url=jdbc:mysql://localhost:3306/web01
#连接数据库的用户名
spring.datasource.username=root
#连接数据库的密码
spring.datasource.password=1234
```

1. Mapper接口（编写SQL语句）

```Java
@Mapper
public interface UserMapper {
    @Select("select * from user")
    public List<User> list();
}
```

1. #### 数据库连接池

在前面我们所讲解的mybatis中，使用了数据库连接池技术，避免频繁的创建连接、销毁连接而带来的资源浪费。

下面我们就具体的了解下数据库连接池。

1. ##### 介绍

1). 没有数据库连接池的情况

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YzI0YjA0ODcyYzllY2JiZGY0ZmVhNmZlZjQ1MTYwZDFfdTI4NExBcXZFU2toNVdnbmZPVXQ5alRla3ZoTE9LekRfVG9rZW46QkY2WGI4Y3ZvbzdVenB4eVR4RWNFMW1SblljXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

客户端执行SQL语句：要先创建一个新的连接对象，然后执行SQL语句，SQL语句执行后又需要关闭连接对象从而释放资源，每次执行SQL时都需要创建连接、销毁链接，这种频繁的重复创建销毁的过程是比较耗费计算机的性能。

2). 有数据库连接池的情况

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YjU3ZDJjNGExMWQ0MDgxZjYwYzJhN2VhNjIxZDk1ZTNfTDVRdDY0TTRNTzFpeDFwV284UFR0NjUwUXJ6T2NSQ1BfVG9rZW46QW9FTmJ2dlBybzhWeUZ4R09yS2NiMXVBbm9iXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

数据库连接池是个容器，负责分配、管理数据库连接(Connection)

- 程序在启动时，会在数据库连接池(容器)中，创建一定数量的Connection对象

允许应用程序重复使用一个现有的数据库连接，而不是再重新建立一个

- 客户端在执行SQL时，先从连接池中获取一个Connection对象，然后在执行SQL语句，SQL语句执行完之后，释放Connection时就会把Connection对象归还给连接池（Connection对象可以复用）

释放空闲时间超过最大空闲时间的连接，来避免因为没有释放连接而引起的数据库连接遗漏

- 客户端获取到Connection对象了，但是Connection对象并没有去访问数据库(处于空闲)，数据库连接池发现Connection对象的空闲时间 > 连接池中预设的最大空闲时间，此时数据库连接池就会自动释放掉这个连接对象

数据库连接池的好处：

- 资源重用
- 提升系统响应速度
- 避免数据库连接遗漏

1. ##### 产品

要怎么样实现数据库连接池呢？

- 官方(sun)提供了数据库连接池标准（javax.sql.DataSource接口）
- 功能：获取连接 
  - ```Java
    public Connection getConnection() throws SQLException;
    ```
- 第三方组织必须按照DataSource接口实现

常见的数据库连接池：**C3P0** **、****DBCP** **、****Druid** **、****Hikari (springboot默认)**

现在使用更多的是：**Hikari、Druid  （性能更优越）**

**1). Hikari（追光者） [默认的连接池]** 

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NDBmZjQ3YTdiNWJlMWQ3YjBiYjc0MjlhMzlmNzg2MzFfRnB4eUw0OTlJdWZxeUN3ZWhlOHI0aTlMZXdpMWdOR2xfVG9rZW46VzFqWmI1UjAzb3dHZEt4bHpaSmN0aHdEbjNmXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

从控制台输出的日志，我们也可以看出，springboot底层默认使用的数据库连接池就是 Hikari。

**2).** **Druid（德鲁伊）**

- Druid连接池是阿里巴巴开源的数据库连接池项目 
- 功能强大，性能优秀，是Java语言最好的数据库连接池之一

如果我们想把默认的数据库连接池切换为Druid数据库连接池，只需要完成以下两步操作即可：

> 参考官方地址：https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter

①. 在`pom.xml`文件中引入依赖

```XML
<dependency>
    <!-- Druid连接池依赖 -->
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.2.19</version>
</dependency>
```

②. 在`application.properties`中引入数据库连接配置

```Properties
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
spring.datasource.druid.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.druid.url=jdbc:mysql://localhost:3306/web
spring.datasource.druid.username=root
spring.datasource.druid.password=1234
```

配置完毕之后，我们再次运行单元测试，大家会看到控制台输出的日志中，已经将连接池切换为了 Druid连接池。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NWJkM2VkNmQwZDczOTY3YTA5OWMzZDg4NzJlZDY4ODZfYkt4UFBCRmhqbDFPdHZMb1lPY0oxYVozMWJJN3BmNzhfVG9rZW46V3VIbmJCR2JPb3MxMkl4RzgwRmNQY0tWbjViXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

1. #### 增删改查操作

1. ##### 删除

- 需求：根据ID删除用户信息
- SQL：delete from user where id = 5;
- Mapper接口方法：
  - 方式一：
  - ```Java
    /**
     * 根据id删除
     */
    @Delete("delete from user where id = 5")
    public void deleteById();
    ```

这种方式执行删除操作，调用deleteById方法只能删除id为5的用户信息，因为将id直接写死在代码中了，不可取。

- 方式二：

```Java
/**
 * 根据id删除
 */
@Delete("delete from user where id = #{id}")
public void deleteById(Integer id);
```

在Mybatis中，我们可以通过参数占位符号 `#{...}` 来占位，在调用`deleteById`方法时，传递的参数值，最终会替换占位符。

- 编写单元测试方法进行测试
  -  在单元测试类中,增加如下测试方法.

  - ```Java
    @Test
    public void testDeleteById(){
        userMapper.deleteById(36);
    }
    ```

运行单元测试，结果如下：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=Y2ViMjJhNjg1YzZkYzk5OWU5ZWEyMzEwYmZkZDA5NDNfbjlXQ1VITTF1YVNYWTBQdHdFNmdlMWZsWDVUUWpqMURfVG9rZW46U3VBbWJKNnYyb1RTQVB4UkFyM2NsbXZubkFlXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

运行之后，我们发现，`#{...}` 占位符，其实最终被替换成了 ？占位符，生成的是预编译的SQL语句。【推荐】

- DML语句执行完毕，是有返回值的，我们可以为Mapper接口方法定义返回值来接收，如下：
  - ```Java
    /**
     * 根据id删除
     */
    @Delete("delete from user where id = #{id}")
    public Integer deleteById(Integer id);
    ```

Integer类型的返回值，表示DML语句执行完毕影响的记录数。

- Mybatis的提供的符号，有两个，一个是 `#{...}`，另一个是 `${...}`，区别如下：

| 符号 | 说明                                               | 场景                       | 优缺点                |
| ---- | -------------------------------------------------- | -------------------------- | --------------------- |
| #{…} | 占位符。执行时，会将#{…}替换为?，生成预编译SQL     | 参数值传递                 | 安全、性能高 （推荐） |
| ${…} | 拼接符。直接将参数拼接在SQL语句中，存在SQL注入问题 | 表名、字段名动态设置时使用 | 不安全、性能低        |

**那在企业项目开发中，****强烈建议使用 #{...} 。**

1. ##### 新增

- 需求：添加一个用户
- SQL：insert into user(username,password,name,age) values('zhouyu','123456','周瑜',20);
- Mapper接口：

```Java
/**
 * 添加用户
 */
@Insert("insert into user(username,password,name,age) values(#{username},#{password},#{name},#{age})")
public void insert(User user);
```

如果在SQL语句中，我们需要传递多个参数，我们可以把多个参数封装到一个对象中。然后在SQL语句中，我们可以通过`#{对象属性名}`的方式，获取到对象中封装的属性值。

- 单元测试：

在测试类中添加测试方法，代码如下：

```Java
@Test
public void testInsert(){
    User user = new User();
    user.setUsername("admin");
    user.setPassword("123456");
    user.setName("管理员");
    user.setAge(30);
    userMapper.insert(user);
}
```

运行结果如下：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=Mjc0MzA5MGQxNzhiYzYzODVkMWNiZmExNjVjMGJlOGVfNGs5TW85djhiQ0xUZ3M2aDd6QnkzM1R4Z0FsWlQ4dE9fVG9rZW46U2pkQ2Jzc1B3b3pZaDJ4U1lwVWNiTFNzbkhiXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

1. ##### 修改

- 需求：根据ID更新用户信息
- SQL：update user set username = 'zhouyu', password = '123456', name = '周瑜', age = 20 where id = 1；
- Mapper接口方法：

```Java
/**
 * 根据id更新用户信息
 */
@Update("update user set username = #{username},password = #{password},name = #{name},age = #{age} where id = #{id}")
public void update(User user);
```

- 单元测试：

在测试类中添加测试方法，代码如下：

```Java
@Test
public void testUpdate(){
    User user = new User();
    user.setId(6);
    user.setUsername("admin666");
    user.setPassword("123456");
    user.setName("管理员");
    user.setAge(30);
    userMapper.update(user);
}
```

运行结果如下：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZTZhZTExODI1YmIxMWY4N2I2NDdkNmUzM2M4NWI2Y2VfRjl1VzlUc3Q1TWduaFpUNTg1eVJXSnlGbXhsaHV2ZTBfVG9rZW46SUxVZWI2bkVwb3RoWHp4aW5YTmNXRkgxbnFjXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

1. ##### 查询

- 需求：根据用户名和密码查询用户信息
- SQL：select* fromuser whereusername = 'zhouyu' and password = '123456'
- Mapper接口方法：

```Java
/**
 * 根据用户名和密码查询用户信息
 */
@Select("select * from user where username = #{username} and password = #{password}")
public User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
```

@param注解的作用是为接口的方法形参起名字的。（由于用户名唯一的，所以查询返回的结果最多只有一个，可以直接封装到一个对象中）

- 单元测试：

在测试类中添加测试方法，代码如下：

```Java
@Test
public void testFindByUsernameAndPassword(){
    User user = userMapper.findByUsernameAndPassword("admin666", "123456");
    System.out.println(user);
}
```

运行结果如下：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NWRjZDI2ZWRlZWQ4ODRhMjdmZmE1NzhmMmEzNmQzMmFfcXZNSHF0azdTdE03dGlqRlNuTUgwYWw3VGxqYVptMXhfVG9rZW46T1QzY2J4UG1sbzVGRjF4cHhlTGNNTW5ObkRiXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

**说明：**基于官方骨架创建的springboot项目中，接口编译时会保留方法形参名，@Param注解可以省略 (#{形参名})。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NzE2NDk3MjdjZDU2NDE1N2JkNDEyMTAxNzFiYWY2NjhfbXRtUDhNVDNXaUhMYnY0Y2JXVXk4NmpNMEk2NTVzcFdfVG9rZW46SDJuUGJnV2l1b0w0bUN4aEZHUGMwbW9wbjFlXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

1. #### XML映射配置

Mybatis的开发有两种方式：

1. 注解
2. XML

1. ##### XML配置文件规范

使用Mybatis的注解方式，主要是来完成一些简单的增删改查功能。如果需要实现复杂的SQL功能，建议使用XML来配置映射语句，也就是将SQL语句写在XML配置文件中。

**在Mybatis中使用XML映射文件方式开发，需要符合一定的****规范****：**

1. XML映射文件的名称与Mapper接口名称一致，并且将XML映射文件和Mapper接口放置在相同包下（同包同名）
2. XML映射文件的namespace属性为Mapper接口全限定名一致
3. XML映射文件中sql语句的id与Mapper接口中的方法名一致，并保持返回类型一致。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NTE4MzBkOTVhOTViMDE3MjRmMjQ5MjU2MjU2NGYzNmJfUEMxbHRxd1dHUmZpZjhyeGtEZUszT2tuZ20xYTVpamZfVG9rZW46Rnl0MGJydzlubzl5cWt4cnZwVWMxeHlzbkp6XzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

> <select>标签：就是用于编写select查询语句的。
>
> - resultType属性，指的是查询返回的单条记录所封装的类型。

1. ##### XML配置文件实现

**第1步： 创建XML映射文件**

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=OTg5YmU1YjliZGMwNjNhOTg3ZjUwY2YxYzQzNDhhMTNfS1RVWWJ1dmF6TkpzWGN2eEpaUHVrN3FEMWY4dzZMSjlfVG9rZW46T09hNmJ5eWdFb0FuM2d4NkZnU2MwY0V3bndlXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MDNmOTk4NGJmNmQ2MGYzNmU3MmFiOGEyZTE3NmZlNGJfYU8yT1l2eW9YdzJMTmpnY0ZWN1FEcExWOGx1OUxBQW9fVG9rZW46UWg4SWJEVlhGbzRaQ3R4WjdMcGNPZ0E2bkxmXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

**第2步：编写XML映射文件**

> xml映射文件中的dtd约束，直接从mybatis官网复制即可; 或者直接AI生成。

```XML
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="">
 
</mapper>
```

**第3步：配置**

**a. XML映射文件的namespace属性为Mapper接口全限定名**

```XML
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.itheima.mapper.UserMapper">

</mapper>
```

**b. XML映射文件中sql语句的id与Mapper接口中的方法名一致，并保持返回类型一致**

```XML
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.itheima.mapper.EmpMapper">

    <!--查询操作-->
    <select id="findAll" resultType="com.itheima.pojo.User">
        select * from user
    </select>
    
</mapper>
```

resultType 属性的值，与查询返回的单条记录封装的类型一致。

运行测试类，执行结果：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NTgwMzNmNjA2YmU4NTQyMjIyNDU5YTVmYWE2YzhjYzBfR0o3UWQxQjVQaG1kUWM4VkFCOXRHMEJMWkdrYnpkVXRfVG9rZW46U0lJY2JSZTJHb1lCc2h4TzBPM2NYMTZRbjJKXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

**注意：一****个接口方法对应的SQL语句，要么使用注解配置，要么使用XML配置，切不可同时配置。**

1. ##### MybatisX的使用

MybatisX是一款基于IDEA的快速开发Mybatis的插件，为效率而生。

MybatisX的安装：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NGVmMjU1YTUwN2E4NDM5MzNlMDBhODMyOTg0ODJiZGZfVkljSjdOVFJ0N0pnWmdzNGh1NmhFSXJrTHFEYXc4VVZfVG9rZW46SlpmSGIyakRPb2Fra1J4TXR0UWN1NWRMbkJnXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

可以通过MybatisX快速定位：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NzU0MjYxMWU2MzY4NTMyMGM5MzI2MTEyYWNmOGQwMGVfQXphRVkzZjRiTG5PMDJqaW9zTmVsY25qWTZUbjREbTRfVG9rZW46SUFSRmJ5Qllub0w5a3l4YkRHTGN1cElTbkZkXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

MybatisX的使用在后续学习中会继续分享。

- 学习了Mybatis中XML配置文件的开发方式了，大家可能会存在一个疑问：到底是使用注解方式开发还是使用XML方式开发？

官方说明：https://mybatis.net.cn/getting-started.html。下面是官方说明:

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=M2VmZjBiMTFhOGFhMzRiZjA5YzdiN2Q4YjFjOWMxMzVfR2JFblBhTE4xRTczSkhmU0k1clNGeGlQM2dJNlFHQW9fVG9rZW46SUxEZmJ2NVBIb1hyMWR4ZjJPUmNaOElFbktiXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

**结论：**使用Mybatis的注解，主要是来完成一些简单的增删改查功能。如果需要实现复杂的SQL功能，建议使用XML来配置映射语句。

1. ## SpringBoot配置文件

1. ### 介绍

前面我们一直使用springboot项目创建完毕后自带的`application.properties`进行属性的配置，而如果在项目中，我们需要配置大量的属性，采用properties配置文件这种 `key=value` 的配置形式，就会显得配置文件的层级结构不清晰，也比较臃肿。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MWJiNmE3YzQxYWVmMDA4NTczNDMyMjczZjhjOWY5NjZfR05OYkN3bEFKb2NaT05rQ2VsaTZNbzVzUWpmbUdxS1pfVG9rZW46TEQ2cGI3eUNYb21OcWh4WElVdmNPc2RobkpiXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

那其实呢，在springboot项目当中是支持多种配置方式的，除了支持properties配置文件以外，还支持另外一种类型的配置文件，就是我们接下来要讲解的yml格式的配置文件。yml格式配置文件名字为：`application.yaml` , `application.yml` 这两个配置文件的后缀名虽然不一样，但是里面配置的内容形式都是一模一样的。

我们可以来对比一下，采用 `application.properties` 和 `application.yml` 来配置同一段信息(数据库连接信息)，两者之间的配置对比：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NTBlZmI3YzkxN2JhMTBlZmQwYjkyZWUwMzJkYTY4MjRfNmZyWE92WjhpRUI0Y09jd0pkNlRvdnRRejJVYXlOR1NfVG9rZW46WkFCdWJaZ0t3b25OMlJ4OENMa2MxOXNNbnJkXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MjhlZDg3OTZhOWQ4NDIxMTgxNzk0MTVhZjY3OTQ3ZmVfQnQ4Z3ZsbGpHeDcza0p2Zlpsakh1c05scVFHdzNzanZfVG9rZW46TzBnWGI0QTI1bzlDMTB4VGZTRGNVYnBibnJQXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

在项目开发中，我们推荐使用application.yml配置文件来配置信息，简洁、明了、以数据为中心。

1. ### 语法

简单的了解过springboot所支持的配置文件，以及不同类型配置文件之间的优缺点之后，接下来我们就来了解下yml配置文件的基本语法：

- 大小写敏感
- 数值前边必须有空格，作为分隔符
- 使用缩进表示层级关系，缩进时，不允许使用Tab键，只能用空格（idea中会自动将Tab转换为空格）
- 缩进的空格数目不重要，只要相同层级的元素左侧对齐即可
- `#`表示注释，从这个字符一直到行尾，都会被解析器忽略

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZmNjY2I2MzNkZDZiZWU1Yzg3OGE2ODhmZTRkZGY4ZjVfcnJFbHZoeTJQc2REZkNFUnhWajRqNU5MNXFSamxlR09fVG9rZW46WW5GSGJoQWVTb09jQWF4TTdhbGNDZFZpbmZkXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

了解完yml格式配置文件的基本语法之后，接下来我们再来看下yml文件中常见的数据格式。在这里我们主要介绍最为常见的两类：

1. 定义对象或Map集合
2. 定义数组、list或set集合

- 对象/Map集合

```YAML
user:
  name: zhangsan
  age: 18
  password: 123456
```

- 数组/List/Set集合

```YAML
hobby: 
  - java
  - game
  - sport
```

在yml格式的配置文件中，如果配置项的值是以 0 开头的，值需要使用 '' 引起来，因为以0开头在yml中表示8进制的数据。

1. ### 案例

熟悉完了yml文件的基本语法后，我们修改下之前案例中使用的配置文件，变更为application.yml配置方式：

1. 修改application.properties名字为：`_application.properties`（名字随便更换，只要加载不到即可）
2. 创建新的配置文件： `application.yml`

- 原有的 `application.properties` 配置文件

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YjRjMjgwMTdhYzAxZDRjMWMwMWU3MmMwYmNkYjM1YjhfeTYxczFlbEJ6T1JlRHlINWwzamVXbG9aRUM0d3AzOGNfVG9rZW46UUU2YWJzUzhGb0ZBU2d4UGhjM2NFV2FQblNnXzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

- 新建的 `application.`yml 配置文件

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YjJhODAxNGRjNzBmZGM0NWM1NTM5YmFhMDQwNjQxMDFfZEhOSkpyQXpZRURHNjdiUzJpRlBldnVic1NFZVBPRHpfVG9rZW46SDM5cmJWakJib3JzcU94dW5qM2N4c3kwbng0XzE3OTA4ODEzMzc6MTc5MDg4NDkzN19WNA&add_watermark=true&scene_type=CCM)

配置文件的内容如下：

```YAML
#数据源配置
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/web01
    username: root
    password: root@1234
#mybatis配置
mybatis:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```