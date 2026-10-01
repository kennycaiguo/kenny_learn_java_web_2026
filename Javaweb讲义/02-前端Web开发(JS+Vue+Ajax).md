## 介绍

在前面的课程中，我们已经学习了HTML、CSS的基础内容，我们知道HTML负责网页的结构，而CSS负责的是网页的表现。 而要想让网页具备一定的交互效果，具有一定的动作行为，还得通过JavaScript来实现。那今天,我们就来讲解JavaScript，这门语言会让我们的页面能够和用户进行交互。

**那什么是JavaScript呢 ?**

**JavaScript**（简称：**JS**） 是一门跨平台、面向对象的脚本语言，是用来控制网页行为的，实现人机交互效果。JavaScript 和 Java 是完全不同的语言，不论是概念还是设计。但是基础语法类似。

- 组成：
  - ECMAScript: 规定了JS基础语法核心知识，包括变量、数据类型、流程控制、函数、对象等。
  - BOM：浏览器对象模型，用于操作浏览器本身，如：页面弹窗、地址栏操作、关闭窗口等。
  - DOM：文档对象模型，用于操作HTML文档，如：改变标签内的内容、改变标签内字体样式等。

备注：ECMA国际（前身为欧洲计算机制造商协会），制定了标准化的脚本程序设计语言 ECMAScript，这种语言得到广泛应用。而JavaScript是遵守ECMAScript的标准的（ES2024是最新版本）。

## JS 基础语法

### JS引入方式

同样，js代码也是书写在html中的，那么html中如何引入js代码呢？主要通过下面的2种引入方式：

- 第一种方式：内部脚本，将JS代码定义在HTML页面中
  - JavaScript代码必须位于<script></script>标签之间
  - 在HTML文档中，可以在任意地方，放置任意数量的<script></script>
  - 一般会把脚本置于<body>元素的底部，可改善显示速度
  - 例子：
  - ```HTML
    <!DOCTYPE html>
    <html lang="en">
    <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>JS 引入方式</title>
    </head>
    <body>
      
      <script>
        alert('Hello JS')
      </script>
    </body>
    </html>
    ```

- 第二种方式：外部脚本， 将JS代码定义在外部 JS文件中，然后引入到 HTML页面中
  - 外部JS文件中，只包含JS代码，不包含<script>标签
  - 引入外部js的<script>标签，必须是双标签
  - 例子：
    - 在js目录下，定义一个js文件`demo.js`，在文件中编写js代码，如下：
    - ```JavaScript
      alert('Hello JS')
      ```

    - 在html文件中，通过<script></script>引入js文件`demo.js`，如下：
    - ```HTML
      <script src="js/demo.js"></script>
      ```
  - **注意1：demo.js中只有js代码，没有<script>标签**
  - **注意2：通过<script></script>标签引入外部JS文件时，标签不能自闭合，如：<script src="js/demo.js" />**

- JS书写规范：
  - 结束符：每行js代码，结尾以分号结尾，而结尾的分号可有可无。（建议在一个项目中保持一致，要么全部都加，要么全部都不加）
  - 注释：单行注释，多行注解的写法， 与java中一致。

1. ### JS基础语法

AI提示词（prompt）：你是一名前端开发工程师，现需要通过js代码计算从1到100的累加之和，并输出。

生成的结果如下所示：

```HTML
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>JS基础语法</title>
</head>
<body>

  <script>
    function sumFromOneToHundred() {
      let sum = 0;
      for (let i = 1; i <= 100; i++) {
          sum += i;
      }
      return sum;
  }
  
  // 调用函数并打印结果
  console.log(sumFromOneToHundred());
  </script>
</body>
</html>
```

浏览器打开此页面最终效果如下：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MmNkNzk2YTFmOTZhMzEyMmY5Mjk3YWE5MzdkYjU3YThfclQzV0g1V1hlRTFibnFteE1kM1RleXI1bFZXOWhNU1NfVG9rZW46R20xQ2I4Nzg2b2V3QUF4V0hBd2N1bFNMblBlXzE3OTA4NjU3Mzg6MTc5MDg2OTMzOF9WNA&add_watermark=true&scene_type=CCM)

那在上述这个案例中呢，就涉及到了JS中的输出语句、变量、函数、循环等语法，那接下来呢，我们就来详细剖析一下。

1. #### 输出语句

在JS中有3种输出语句，分别是：

| api                 | 描述             |
| ------------------- | ---------------- |
| window.alert(...)   | 警告框           |
| document.write(...) | 在HTML 输出内容  |
| console.log(...)    | 写入浏览器控制台 |

我们定义如下这么一段JS，来做一个测试：

```HTML
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JS基础语法</title>
</head>
<body>
        
    <script>
        //方式一: 写入浏览器的body区域
        document.write("Hello JS (document.write)");

        //方式二: 弹出框
        window.alert("Hello JS (window.alert)");

        //方式三: 控制台
        console.log("Hello JS (console.log)")
    </script>
</body>
</html>
```

1. #### 变量

接下来，我们再来讲解JS中的变量。在js中，变量的声明和java中还是不同的。

- JS中主要通过 `let` 关键字来声明变量的。
- JS是一门弱类型语言，变量是可以存放不同类型的值的。
- 变量名需要遵循如下规则：
  - 组成字符可以是任何字母、数字、下划线（_）或美元符号（$），且数字不能开头
  - 变量名严格区分大小写，如：name和Name是不同的变量
  - 不能使用关键字作为变量名，如：let、if、for等

变量的声明示例如下所示：

```HTML
<script>
    //变量
    let a = 20;
    a = "Hello";
    alert(a);
</script>
```

上述的示例中，大家会看到变量a既可以存数字，又可以存字符串。 因为JS是弱类型语言。

> PS. 在早期的JS中，声明变量还可以使用 `var` 关键字来声明。例如:
>
> ```HTML
> <body>
> 
>     <script>
>          //var声明变量
>                 var name = "A";
>                 name = "B";
>                 alert(name);
>         
>          var name = "C"
>          alert(name);
>     </script>
> </body>
> ```
>
> 打开浏览器运行之后，大家会发现，可以正常执行，第一次弹出 B，第二次弹出 C 。我们看到 name变量重复声明了，但是呢，如果使用var关键字，是没有问题的，可以重复声明。
>
> **`var`****声明的变量呢，还有一些其他不严谨的地方，这里就不再一一列举了，所以这个声明变量的关键字，并不严谨 【不推荐】。**

#### 常量

在JS中，如果声明一个场景，需要使用`const`关键字。一旦声明，常量的值就不能改变 （不可以重新赋值）。

如下所示：

```HTML
<body>

    <script>
        //常量
        const PI = 3.14;
        PI = 3.15;
        alert(PI);
    </script>
</body>
```

浏览器打开之后，会报如下错误：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZGUzN2Y5YzI5YmM0MTAyYTBlOGE0ZjA0M2RkZDZlYjVfNGJaNXVWOXU3TG9JUTkwQnJzMjZJNFV6aDZmdkxkbVRfVG9rZW46S3R3cGJwZ2xibzRjNUR4c1NZNGNhNjlsbktoXzE3OTA4NjU3Mzg6MTc5MDg2OTMzOF9WNA&add_watermark=true&scene_type=CCM)

该错误就表示，常量不可以被重新分配值。

1. #### 数据类型

虽然JS是弱数据类型的语言，但是JS中也存在数据类型，JS中的数据类型分为 ：原始数据类型 和 引用数据类型。那这部分，我们先来学习原始数据类型，主要包含以下几种类型：

| 数据类型  | 描述                                                         |
| --------- | ------------------------------------------------------------ |
| number    | 数字（整数、小数、NaN(Not a Number)）                        |
| string    | 字符串，单双引('...')、双引号("...")、反引号(`...`)皆可，正常使用推荐单引号 |
| boolean   | 布尔。true，false                                            |
| null      | 对象为空。 JavaScript 是大小写敏感的，因此 null、Null、NULL是完全不同的 |
| undefined | 当声明的变量未初始化时，该变量的默认值是 undefined           |

使用`typeof` 关键字可以返回变量的数据类型，接下来我们需要通过书写代码来演示js中的数据类型。代码如下:

```HTML
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JS-数据类型</title>
</head>
<body>

    <script>
        //原始数据类型
        alert(typeof 3); //number
        alert(typeof 3.14); //number

        alert(typeof "A"); //string
        alert(typeof 'Hello');//string

        alert(typeof true); //boolean
        alert(typeof false);//boolean

        alert(typeof null); //object 

        var a ;
        alert(typeof a); //undefined

    </script>
</body>
</html>
```

对于字符串类型的数据，除了可以使用双引号（"..."）、单引号（'...'）以外，还可以使用反引号 （``）。 而使用反引号引起来的字符串，也称为 **模板字符串**。

- 模板字符串的使用场景：拼接字符串和变量。
- 模板字符串的语法：
  - `...` ：反引号 （英文输入模式下键盘 tab 键上方波浪线 ~ 那个键）
  - 内容拼接时，使用 ${ } 来引用变量

具体示例如下：

```HTML
  <script>
    let name = 'Tom';
    let age = 18;
    console.log('大家好, 我是新入职的' + name + ', 今年' + age + '岁了, 请多多关照'); //原始方式 , 手动拼接字符串
    console.log(`大家好, 我是新入职的${name}, 今年${age}岁了, 请多多关照`); //使用模板字符串方式拼接字符串
  </script>
```

#### 函数

**函数（function）**是被设计用来执行特定任务的代码块，方便程序的封装复用。 那我们学习函数，主要就是学习JS中函数的定义及调用的语法。

##### 方式一

格式如下：

```JavaScript
function 函数名(参数1,参数2..){
    要执行的代码
}
```

因为JavaScript是弱数据类型的语言，所以有如下几点需要注意：

- 形参不需要声明类型，并且JS中不管什么类型都是let去声明，加上也没有意义。
- 返回值也不需要声明类型，直接return即可

**示例：**

```JavaScript
function add(a, b){
    return a + b;
}
```

如果要调用上述的函数add，可以使用：函数名称(实际参数列表)

```JavaScript
let result = add(10,20);
alert(result);
```

我们在调用add函数时，再添加2个参数，修改代码如下：

```JavaScript
var result = add(10,20,30,40);
alert(result);
```

浏览器打开，发现没有错误，并且依然弹出30，这是为什么呢？

因为在JavaScript中，函数的调用只需要名称正确即可，参数列表不管的。如上述案例，10传递给了变量a，20传递给了变量b，而30和40没有变量接受，但是不影响函数的正常调用。

**注意：由于JS是弱类型语言，形参、返回值都不需要指定类型。在调用函数时，实参个数与形参个数可以不一致，但是建议一致。** 

##### 方式二

刚才我们定义函数，是为函数指定了一个名字。 那我们也可以不为函数指定名字，那这一类的函数，我们称之为**匿名函数**。那接下来，方式二，就来介绍一下匿名函数的定义和调用。

**匿名函数：**是指一种没有名称的函数，由于它们没有名称，因此无法直接通过函数名来调用，而是通过变量或表达式来调用。

匿名函数定义可以通过两种方式：函数表达式 和 箭头函数。

- 示例一（函数表达式）：

```JavaScript
var add = function (a,b){
    return a + b;
}
```

- 示例二（**箭头函数**）：

```JavaScript
var add = (a,b) => {
    return a + b;
}
```

上述匿名函数声明好了之后，是将这个函数赋值给了add变量。 那我们就可以直接通过add函数直接调用，调用代码如下：

```JavaScript
let result = add(10,20);
alert(result);
```

而箭头函数这种形式，在现在的前端开发中用的会更多一些。

#### 流程控制

在JS中，当然也存在对应的流程控制语句。常见的流程控制语句如下：

- if ... else if ... else ...
- switch
- for
- while
- do ... while

而JS中的流程控制语句与JAVA中的流程控制语句的作用，执行机制都是一样的。这个呢，我们现在就不再一个一个的如研究了，后面用到的时候，我们再做说明。

## JS DOM

### DOM案例

学习了JS的基础语法之后，接下来呢，我们再来完成一个案例。案例的需求是：**实现表格的隔行换色效果。** 最终的效果如下：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZDFkZjA5Y2NlZWJkN2Y3ZDYyODBmZTU3NDM2Mjg4OTFfOUZmWG5zS3RidHJCMmdkYm9pSjFCS1FFeElFa0I0ZnlfVG9rZW46Vjg2MWI2dXNpb25tQ2x4WUVuRWN2bHp2bldRXzE3OTA4NjU3Mzg6MTc5MDg2OTMzOF9WNA&add_watermark=true&scene_type=CCM)

那这里我们就可以将资料中提供的 `2. DOM案例/tlias案例.html` 文件导入VsCode，在其基础上完成这个案例。

AI提示词（prompt）：

通过JS实现表格数据行的隔行换色效果，奇数行背景色为 #f2e2e2，偶数行背景色为 #e6f7ff。（JS新语法实现）

最终代码如下：

```HTML
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tlias智能学习辅助系统</title>
    <style>
      body {
        margin: 0;
      }

      /* 顶栏样式 */
      .header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        background-color: #c2c0c0;
        padding: 20px 20px;
        box-shadow: 0 2px 5px rgba(0,0,0,0.1);
      }
      
      /* 加大加粗标题 */
      .header h1 {
        margin: 0;
        font-size: 24px;
        font-weight: bold;
      }

      /* 文本链接样式 */
      .header a {
        text-decoration: none;
        color: #333;
        font-size: 16px;
      }

      /* 搜索表单区域 */
      .search-form {
        display: flex;
        align-items: center;
        padding: 20px;
        background-color: #f9f9f9;
      }

      /* 表单控件样式 */
      .search-form input[type="text"], .search-form select {
        margin-right: 10px;
        padding: 10px 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
        width: 26%;
      }

      /* 按钮样式 */
      .search-form button {
        padding: 10px 15px;
        margin-left: 10px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
      }

      /* 清空按钮样式 */
      .search-form button.clear {
        background-color: #6c757d;
      }

      .table {
         min-width: 100%; 
         border-collapse: collapse;
      }

      /* 设置表格单元格边框 */
      .table td, .table th { 
        border: 1px solid #ddd; 
        padding: 8px; 
        text-align: center;
      }
      
      .avatar { 
        width: 30px; 
        height: 30px; 
        object-fit: cover; 
        border-radius: 50%; 
      }

      /* 页脚版权区域 */
    .footer {
        background-color: #c2c0c0;
        color: white;
        text-align: center;
        padding: 10px 0;
        margin-top: 30px;
    }

    .footer .company-name {
        font-size: 1.1em;
        font-weight: bold;
    }

    .footer .copyright {
        font-size: 0.9em;
    }

    #container {
      width: 80%;
      margin: 0 auto;
    }
    </style>
</head>
<body>
    
  <div id="container">
    <!-- 顶栏 -->
    <div class="header">
      <h1>Tlias智能学习辅助系统</h1>
      <a href="#">退出登录</a>
    </div>

    <!-- 搜索表单区域 -->
    <form class="search-form" action="#" method="post">
      <input type="text" name="name" placeholder="姓名" />
      <select name="gender">
          <option value="">性别</option>
          <option value="1">男</option>
          <option value="2">女</option>
      </select>
      <select name="job">
          <option value="">职位</option>
          <option value="1">班主任</option>
          <option value="2">讲师</option>
          <option value="3">学工主管</option>
          <option value="4">教研主管</option>
          <option value="5">咨询师</option>
      </select>
      <button type="submit">查询</button>
      <button type="reset" class="clear">清空</button>
    </form>

    <table class="table table-striped table-bordered">
      <thead>
          <tr>
              <th>姓名</th>
              <th>性别</th>
              <th>头像</th>
              <th>职位</th>
              <th>入职日期</th>
              <th>最后操作时间</th>
              <th>操作</th>
          </tr>
      </thead>
      <tbody>
        <tr>
            <td>令狐冲</td>
            <td>男</td>
            <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="令狐冲" class="avatar"></td>
            <td>讲师</td>
            <td>2021-03-15</td>
            <td>2023-07-30T12:00:00Z</td>
            <td class="btn-group">
                <button class="edit">编辑</button>
                <button class="delete">删除</button>
            </td>
        </tr>
        <tr>
            <td>任盈盈</td>
            <td>女</td>
            <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="任盈盈" class="avatar"></td>
            <td>学工主管</td>
            <td>2020-04-10</td>
            <td>2023-07-29T15:00:00Z</td>
            <td class="btn-group">
                <button class="edit">编辑</button>
                <button class="delete">删除</button>
            </td>
        </tr>
        <tr>
            <td>岳不群</td>
            <td>男</td>
            <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="岳不群" class="avatar"></td>
            <td>教研主管</td>
            <td>2019-01-01</td>
            <td>2023-07-30T10:00:00Z</td>
            <td class="btn-group">
                <button class="edit">编辑</button>
                <button class="delete">删除</button>
            </td>
        </tr>
        <tr>
            <td>宁中则</td>
            <td>女</td>
            <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="宁中则" class="avatar"></td>
            <td>班主任</td>
            <td>2018-06-01</td>
            <td>2023-07-29T09:00:00Z</td>
            <td class="btn-group">
                <button class="edit">编辑</button>
                <button class="delete">删除</button>
            </td>
        </tr>
        <tr>
          <td>令狐冲</td>
          <td>男</td>
          <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="令狐冲" class="avatar"></td>
          <td>讲师</td>
          <td>2021-03-15</td>
          <td>2023-07-30T12:00:00Z</td>
          <td class="btn-group">
              <button class="edit">编辑</button>
              <button class="delete">删除</button>
          </td>
        </tr>
        <tr>
            <td>任盈盈</td>
            <td>女</td>
            <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="任盈盈" class="avatar"></td>
            <td>学工主管</td>
            <td>2020-04-10</td>
            <td>2023-07-29T15:00:00Z</td>
            <td class="btn-group">
                <button class="edit">编辑</button>
                <button class="delete">删除</button>
            </td>
        </tr>
        <tr>
            <td>岳不群</td>
            <td>男</td>
            <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="岳不群" class="avatar"></td>
            <td>教研主管</td>
            <td>2019-01-01</td>
            <td>2023-07-30T10:00:00Z</td>
            <td class="btn-group">
                <button class="edit">编辑</button>
                <button class="delete">删除</button>
            </td>
        </tr>
        <tr>
            <td>宁中则</td>
            <td>女</td>
            <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="宁中则" class="avatar"></td>
            <td>班主任</td>
            <td>2018-06-01</td>
            <td>2023-07-29T09:00:00Z</td>
            <td class="btn-group">
                <button class="edit">编辑</button>
                <button class="delete">删除</button>
            </td>
        </tr>
        <tr>
          <td>令狐冲</td>
          <td>男</td>
          <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="令狐冲" class="avatar"></td>
          <td>讲师</td>
          <td>2021-03-15</td>
          <td>2023-07-30T12:00:00Z</td>
          <td class="btn-group">
              <button class="edit">编辑</button>
              <button class="delete">删除</button>
          </td>
        </tr>
        <tr>
            <td>任盈盈</td>
            <td>女</td>
            <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="任盈盈" class="avatar"></td>
            <td>学工主管</td>
            <td>2020-04-10</td>
            <td>2023-07-29T15:00:00Z</td>
            <td class="btn-group">
                <button class="edit">编辑</button>
                <button class="delete">删除</button>
            </td>
        </tr>
        <tr>
            <td>岳不群</td>
            <td>男</td>
            <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="岳不群" class="avatar"></td>
            <td>教研主管</td>
            <td>2019-01-01</td>
            <td>2023-07-30T10:00:00Z</td>
            <td class="btn-group">
                <button class="edit">编辑</button>
                <button class="delete">删除</button>
            </td>
        </tr>
        <tr>
            <td>宁中则</td>
            <td>女</td>
            <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="宁中则" class="avatar"></td>
            <td>班主任</td>
            <td>2018-06-01</td>
            <td>2023-07-29T09:00:00Z</td>
            <td class="btn-group">
                <button class="edit">编辑</button>
                <button class="delete">删除</button>
            </td>
        </tr>
      </tbody>
    </table>

    <!-- 页脚版权区域 -->
    <footer class="footer">
      <p class="company-name">江苏传智播客教育科技股份有限公司</p>
      <p class="copyright">版权所有 Copyright 2006-2024 All Rights Reserved</p>
    </footer>

    <script>
      //通过JS实现表格数据行的隔行换色效果，奇数行背景色为 #f2e2e2，偶数行背景色为 #e6f7ff。（JS新语法实现）
      const table = document.querySelector('table');
      for (let i = 1; i < table.rows.length; i++) {
        if (i % 2 === 0) {
          table.rows[i].style.backgroundColor = '#f2e2e2';
        } else {
          table.rows[i].style.backgroundColor = '#e6f7ff';
        }
      }
    </script>
    
  </div>

</body>
</html>
```

通过浏览器运行此页面，我们可以看到，确实实现了隔行换色的效果。 那上述的这段JS代码到底什么含义呢？ 

那这里呢，其实用到了JS中的DOM操作，那接下来呢，我们就来介绍一下DOM。

### DOM介绍

DOM：Document Object Model 文档对象模型。也就是 JavaScript 将 HTML 文档的各个组成部分封装为对象。

DOM 其实我们并不陌生，之前在学习 XML 就接触过，只不过 XML 文档中的标签需要我们写代码解析，而 HTML 文档是浏览器解析。封装的对象分为

- Document：整个文档对象
- Element：元素对象
- Attribute：属性对象
- Text：文本对象
- Comment：注释对象

如下图，左边是 HTML 文档内容，右边是 DOM 树

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZWFhZmQwMThkNzZkYTg2MWNiYjI0NWNlYmIwY2JmMTZfeTc1SVM1Y1RqUVVaOHRuZ0UyTTJJRXZUcm9jMnBRRldfVG9rZW46REpEMmJXSk9Ob21LY214RGVGVWNIOWZxbkRjXzE3OTA4NjU3Mzg6MTc5MDg2OTMzOF9WNA&add_watermark=true&scene_type=CCM)

那么我们学习DOM技术有什么用呢？主要作用如下：

- 改变 HTML 元素的内容
- 改变 HTML 元素的样式（CSS）
- 对 HTML DOM 事件作出反应
- 添加和删除 HTML 元素

### DOM操作

- DOM的核心思想：将网页的内容当做对象来处理，标签的所有属性在该对象上都可以找到，并且修改这个对象的属性，就会自动映射到标签身上。
- document对象
  - 网页中所有内容都封装在document对象中
  - 它提供的属性和方法都是用来访问和操作网页内容的，如：document.write(…)
- DOM操作步骤:
  - 获取DOM元素对象
  - 操作DOM对象的属性或方法 (查阅文档)

- 我们可以通过如下两种方式来获取DOM元素。
  - 根据CSS选择器来获取DOM元素，获取到匹配到的第一个元素：`document.querySelector('CSS选择器');`
  - 根据CSS选择器来获取DOM元素，获取匹配到的所有元素：`document.querySelectorAll('CSS选择器');`

​         注意：获取到的所有元素，会封装到一个NodeList节点集合中，是一个伪数组（有长度、有索引的数组，但没有push、pop等数组方法）

> PS：在早期的JS中，我们也可以通过如下方法获取DOM元素（了解）。
>
> - document.getElementById(...)：根据id属性值获取，返回单个Element对象。
> - document.getElementsByTagName(...)：根据标签名称获取，返回Element对象数组。
> - document.getElementsByName()：根据name属性值获取，返回Element对象数组。
> - document.getElementsByClassName()：根据class属性值获取，返回Element对象数组。

## JS 事件监听

### 删除员工案例

- **需求：**点击表格后面的删除按钮后，弹出一个确认框进行二次确认，如果确定，则删除这条记录。
- **效果如下：**

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YTNkOGZmNWVkNDQ3YTg3MDA5YjY0MTQ5NDI2N2M5OWZfb2hubUU2Z0VFMTlXV0RCbGxqRGZ4ODIzR1V6cWFCZUFfVG9rZW46UGwwZmJRSXRab3Z0U3J4YmFEVWNaWTBabmFkXzE3OTA4NjU3Mzg6MTc5MDg2OTMzOF9WNA&add_watermark=true&scene_type=CCM)

这个功能，我们就可以直接基于通义零码来帮我们实现了。

提示词（prompt）：

点击列表中每一条记录后面的删除按钮，弹出一个提示框，提示：您是否要删除这条记录？ 如果确定，则移出这条记录

最终完整代码如下：

```HTML
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tlias智能学习辅助系统</title>
    <style>
      body {
        margin: 0;
      }

      /* 顶栏样式 */
      .header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        background-color: #c2c0c0;
        padding: 20px 20px;
        box-shadow: 0 2px 5px rgba(0,0,0,0.1);
      }
      
      /* 加大加粗标题 */
      .header h1 {
        margin: 0;
        font-size: 24px;
        font-weight: bold;
      }

      /* 文本链接样式 */
      .header a {
        text-decoration: none;
        color: #333;
        font-size: 16px;
      }

      /* 搜索表单区域 */
      .search-form {
        display: flex;
        align-items: center;
        padding: 20px;
        background-color: #f9f9f9;
      }

      /* 表单控件样式 */
      .search-form input[type="text"], .search-form select {
        margin-right: 10px;
        padding: 10px 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
        width: 26%;
      }

      /* 按钮样式 */
      .search-form button {
        padding: 10px 15px;
        margin-left: 10px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
      }

      /* 清空按钮样式 */
      .search-form button.clear {
        background-color: #6c757d;
      }

      .table {
         min-width: 100%; 
         border-collapse: collapse;
      }

      /* 设置表格单元格边框 */
      .table td, .table th { 
        border: 1px solid #ddd; 
        padding: 8px; 
        text-align: center;
      }
      
      .avatar { 
        width: 30px; 
        height: 30px; 
        object-fit: cover; 
        border-radius: 50%; 
      }

      /* 页脚版权区域 */
    .footer {
        background-color: #c2c0c0;
        color: white;
        text-align: center;
        padding: 10px 0;
        margin-top: 30px;
    }

    .footer .company-name {
        font-size: 1.1em;
        font-weight: bold;
    }

    .footer .copyright {
        font-size: 0.9em;
    }

    #container {
      width: 80%;
      margin: 0 auto;
    }
    </style>
</head>
<body>
    
  <div id="container">
    <!-- 顶栏 -->
    <div class="header">
      <h1>Tlias智能学习辅助系统</h1>
      <a href="#">退出登录</a>
    </div>

    <!-- 搜索表单区域 -->
    <form class="search-form" action="#" method="post">
      <input type="text" name="name" placeholder="姓名" />
      <select name="gender">
          <option value="">性别</option>
          <option value="1">男</option>
          <option value="2">女</option>
      </select>
      <select name="job">
          <option value="">职位</option>
          <option value="1">班主任</option>
          <option value="2">讲师</option>
          <option value="3">学工主管</option>
          <option value="4">教研主管</option>
          <option value="5">咨询师</option>
      </select>
      <button type="submit">查询</button>
      <button type="reset" class="clear">清空</button>
    </form>

    <table class="table table-striped table-bordered">
      <thead>
          <tr>
              <th>姓名</th>
              <th>性别</th>
              <th>头像</th>
              <th>职位</th>
              <th>入职日期</th>
              <th>最后操作时间</th>
              <th>操作</th>
          </tr>
      </thead>
      <tbody>
        <tr>
            <td>令狐冲</td>
            <td>男</td>
            <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="令狐冲" class="avatar"></td>
            <td>讲师</td>
            <td>2021-03-15</td>
            <td>2023-07-30T12:00:00Z</td>
            <td class="btn-group">
                <button class="edit">编辑</button>
                <button class="delete">删除</button>
            </td>
        </tr>
        <tr>
            <td>任盈盈</td>
            <td>女</td>
            <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="任盈盈" class="avatar"></td>
            <td>学工主管</td>
            <td>2020-04-10</td>
            <td>2023-07-29T15:00:00Z</td>
            <td class="btn-group">
                <button class="edit">编辑</button>
                <button class="delete">删除</button>
            </td>
        </tr>
        <tr>
            <td>岳不群</td>
            <td>男</td>
            <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="岳不群" class="avatar"></td>
            <td>教研主管</td>
            <td>2019-01-01</td>
            <td>2023-07-30T10:00:00Z</td>
            <td class="btn-group">
                <button class="edit">编辑</button>
                <button class="delete">删除</button>
            </td>
        </tr>
        <tr>
            <td>宁中则</td>
            <td>女</td>
            <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="宁中则" class="avatar"></td>
            <td>班主任</td>
            <td>2018-06-01</td>
            <td>2023-07-29T09:00:00Z</td>
            <td class="btn-group">
                <button class="edit">编辑</button>
                <button class="delete">删除</button>
            </td>
        </tr>
        <tr>
          <td>令狐冲</td>
          <td>男</td>
          <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="令狐冲" class="avatar"></td>
          <td>讲师</td>
          <td>2021-03-15</td>
          <td>2023-07-30T12:00:00Z</td>
          <td class="btn-group">
              <button class="edit">编辑</button>
              <button class="delete">删除</button>
          </td>
        </tr>
        <tr>
            <td>任盈盈</td>
            <td>女</td>
            <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="任盈盈" class="avatar"></td>
            <td>学工主管</td>
            <td>2020-04-10</td>
            <td>2023-07-29T15:00:00Z</td>
            <td class="btn-group">
                <button class="edit">编辑</button>
                <button class="delete">删除</button>
            </td>
        </tr>
        <tr>
            <td>岳不群</td>
            <td>男</td>
            <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="岳不群" class="avatar"></td>
            <td>教研主管</td>
            <td>2019-01-01</td>
            <td>2023-07-30T10:00:00Z</td>
            <td class="btn-group">
                <button class="edit">编辑</button>
                <button class="delete">删除</button>
            </td>
        </tr>
        <tr>
            <td>宁中则</td>
            <td>女</td>
            <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="宁中则" class="avatar"></td>
            <td>班主任</td>
            <td>2018-06-01</td>
            <td>2023-07-29T09:00:00Z</td>
            <td class="btn-group">
                <button class="edit">编辑</button>
                <button class="delete">删除</button>
            </td>
        </tr>
        <tr>
          <td>令狐冲</td>
          <td>男</td>
          <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="令狐冲" class="avatar"></td>
          <td>讲师</td>
          <td>2021-03-15</td>
          <td>2023-07-30T12:00:00Z</td>
          <td class="btn-group">
              <button class="edit">编辑</button>
              <button class="delete">删除</button>
          </td>
        </tr>
        <tr>
            <td>任盈盈</td>
            <td>女</td>
            <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="任盈盈" class="avatar"></td>
            <td>学工主管</td>
            <td>2020-04-10</td>
            <td>2023-07-29T15:00:00Z</td>
            <td class="btn-group">
                <button class="edit">编辑</button>
                <button class="delete">删除</button>
            </td>
        </tr>
        <tr>
            <td>岳不群</td>
            <td>男</td>
            <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="岳不群" class="avatar"></td>
            <td>教研主管</td>
            <td>2019-01-01</td>
            <td>2023-07-30T10:00:00Z</td>
            <td class="btn-group">
                <button class="edit">编辑</button>
                <button class="delete">删除</button>
            </td>
        </tr>
        <tr>
            <td>宁中则</td>
            <td>女</td>
            <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="宁中则" class="avatar"></td>
            <td>班主任</td>
            <td>2018-06-01</td>
            <td>2023-07-29T09:00:00Z</td>
            <td class="btn-group">
                <button class="edit">编辑</button>
                <button class="delete">删除</button>
            </td>
        </tr>
      </tbody>
    </table>

    <!-- 页脚版权区域 -->
    <footer class="footer">
      <p class="company-name">江苏传智播客教育科技股份有限公司</p>
      <p class="copyright">版权所有 Copyright 2006-2024 All Rights Reserved</p>
    </footer>

    <script>
      //通过JS实现表格数据行的隔行换色效果，奇数行背景色为 #f2e2e2，偶数行背景色为 #e6f7ff。（JS新语法实现）
      const table = document.querySelector('table');
      for (let i = 1; i < table.rows.length; i++) {
        if (i % 2 === 0) {
          table.rows[i].style.backgroundColor = '#f2e2e2';
        } else {
          table.rows[i].style.backgroundColor = '#e6f7ff';
        }
      }

      //点击列表中每一条记录后面的删除按钮，弹出一个提示框，提示：您是否要删除这条记录？ 如果确定，则移出这条记录
      const deleteButtons = document.querySelectorAll('.delete');
      for (let i = 0; i < deleteButtons.length; i++) {
        deleteButtons[i].addEventListener('click', function () {
          if (confirm('您是否要删除这条记录？')) {
            //点击确定按钮，删除当前记录
            this.parentNode.parentNode.remove();
          }
        });
      }

    </script>

  </div>

</body>
</html>
```

那在上述的案例中，我们点击 "删除" 按钮后，执行某一个操作，这里其实就用到了JS中的事件监听的语法。那接下来，我们就来介绍一下JS中的事件监听。

### 事件介绍

什么是事件呢？HTML事件是发生在HTML元素上的 “事情”，例如：

- 按钮被点击
- 鼠标移到元素上
- 输入框失去焦点
- 按下键盘按键
- ........

而我们可以给这些事件绑定函数，当事件触发时，可以自动的完成对应的功能，这就是事件监听。

例如：对于我们所说的百度注册页面，我们给用户名输入框的失去焦点事件绑定函数，当我们用户输入完内容，在标签外点击了鼠标，对于用户名输入框来说，失去焦点，然后执行绑定的函数，函数进行用户名内容的校验等操作。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=OTNkODhkMjY0ODExMTkxMWY3OTkxOWQwOTNjNmY3MWVfQVoyT2l5SU1paWVhWnlkMEtTdkY2NmlrSE9hT1BvU0NfVG9rZW46WGZ6VGJoTUdSb2JBM0V4YU5NRmNhWGFlbk9mXzE3OTA4NjU3Mzg6MTc5MDg2OTMzOF9WNA&add_watermark=true&scene_type=CCM)

JS事件是JS非常重要的一部分，接下来我们进行事件的学习。那么我们对于JavaScript事件需要学习哪些内容呢？我们得知道有哪些常用事件，然后我们得学会如何给事件绑定函数。

所以主要围绕2点来学习：①. 事件监听、②. 常用事件

### 事件监听语法

JS事件监听的语法: 

```JavaScript
事件源.addEventListener('事件类型', 要执行的函数);
```

在上述的语法中包含三个要素: 

- 事件源: 哪个dom元素触发了事件, 要获取dom元素
- 事件类型: 用什么方式触发, 比如: 鼠标单击 click, 鼠标经过 mouseover
- 要执行的函数: 要做什么事

演示：

```HTML
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JS-事件-事件绑定</title>
</head>

<body>
    <input type="button" id="btn1" value="点我一下试试1">
    <input type="button" id="btn2" value="点我一下试试2">
        
    <script>
        document.querySelector("#btn1").addEventListener('click', ()=>{
            alert("按钮1被点击了...");
        })
    </script>
</body>
</html>
```

**JavaScript对于事件的绑定还提供了另外2种方式（早期版本）：**

1). 通过html标签中的事件属性进行绑定

例如一个按钮，我们对于按钮可以绑定单机事件，可以借助标签的onclick属性，属性值指向一个函数。

```HTML
<input type="button" id="btn1" value="点我一下试试1" onclick="on()">

<script>
    function on(){
        alert('试试就试试')
    }
</script>
```

2). 通过DOM中Element元素的事件属性进行绑定

依据我们学习过得DOM的知识点，我们知道html中的标签被加载成element对象，所以我们也可以通过element对象的属性来操作标签的属性。

例如一个按钮，我们对于按钮可以绑定单机事件，可以通过DOM元素的属性，为其做事件绑定。

```HTML
<body>
    <input type="button" id="btn1" value="点我一下试试1">
    <script>
      document.querySelector('#btn1').onclick = function(){
          alert("按钮2被点击了...");
      }
    </script>
</body>
```

整体代码如下：

```HTML
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JS-事件-事件绑定</title>
</head>

<body>
    <input type="button" id="btn1" value="事件绑定1">
    <input type="button" id="btn2" value="事件绑定2">
    <script>
        document.querySelector("#btn1").addEventListener('click', ()=>{
            alert("按钮1被点击了...");
        })
        
        document.querySelector('#btn2').onclick = function(){
            alert("按钮2被点击了...");
        }
    </script>
</body>
</html>
```

> **addEventListener 与 on事件 区别:** 
>
> - on方式会被覆盖，addEventListener 方式可以绑定多次，拥有更多特性，推荐使用 addEventListener . 

### 隔行换色案例

**需求：**实现鼠标移入数据行时，背景色改为#f2e2e2，鼠标移出时，再将背景色改为白色。

**效果：**

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MjUzMTgxMGUzZGU0NWRiZmVmZmZmZWVkMmNmZjM4ZWZfaHptbjRsZEFMRjI1V254V3gzN2szckpuY3I5ZTIwdkFfVG9rZW46Qk9Yb2JhblRUbzNnS3p4Sk9EaGNPSGtLbjdmXzE3OTA4NjU3Mzg6MTc5MDg2OTMzOF9WNA&add_watermark=true&scene_type=CCM)

这个功能，我们就可以直接基于通义零码来帮我们实现了。

提示词（prompt）：

通过js实现鼠标移入移出效果，鼠标移入，背景色为 #f2e2e2，鼠标移出，背景色为 白色。

最终完整代码如下：

```HTML
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tlias智能学习辅助系统</title>
    <style>
      body {
        margin: 0;
      }

      /* 顶栏样式 */
      .header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        background-color: #c2c0c0;
        padding: 20px 20px;
        box-shadow: 0 2px 5px rgba(0,0,0,0.1);
      }
      
      /* 加大加粗标题 */
      .header h1 {
        margin: 0;
        font-size: 24px;
        font-weight: bold;
      }

      /* 文本链接样式 */
      .header a {
        text-decoration: none;
        color: #333;
        font-size: 16px;
      }

      /* 搜索表单区域 */
      .search-form {
        display: flex;
        align-items: center;
        padding: 20px;
        background-color: #f9f9f9;
      }

      /* 表单控件样式 */
      .search-form input[type="text"], .search-form select {
        margin-right: 10px;
        padding: 10px 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
        width: 26%;
      }

      /* 按钮样式 */
      .search-form button {
        padding: 10px 15px;
        margin-left: 10px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
      }

      /* 清空按钮样式 */
      .search-form button.clear {
        background-color: #6c757d;
      }

      .table {
         min-width: 100%; 
         border-collapse: collapse;
      }

      /* 设置表格单元格边框 */
      .table td, .table th { 
        border: 1px solid #ddd; 
        padding: 8px; 
        text-align: center;
      }
      
      .avatar { 
        width: 30px; 
        height: 30px; 
        object-fit: cover; 
        border-radius: 50%; 
      }

      /* 页脚版权区域 */
    .footer {
        background-color: #c2c0c0;
        color: white;
        text-align: center;
        padding: 10px 0;
        margin-top: 30px;
    }

    .footer .company-name {
        font-size: 1.1em;
        font-weight: bold;
    }

    .footer .copyright {
        font-size: 0.9em;
    }

    #container {
      width: 80%;
      margin: 0 auto;
    }
    </style>
</head>
<body>
    
  <div id="container">
    <!-- 顶栏 -->
    <div class="header">
      <h1>Tlias智能学习辅助系统</h1>
      <a href="#">退出登录</a>
    </div>

    <!-- 搜索表单区域 -->
    <form class="search-form" action="#" method="post">
      <input type="text" name="name" placeholder="姓名" />
      <select name="gender">
          <option value="">性别</option>
          <option value="1">男</option>
          <option value="2">女</option>
      </select>
      <select name="job">
          <option value="">职位</option>
          <option value="1">班主任</option>
          <option value="2">讲师</option>
          <option value="3">学工主管</option>
          <option value="4">教研主管</option>
          <option value="5">咨询师</option>
      </select>
      <button type="submit">查询</button>
      <button type="reset" class="clear">清空</button>
    </form>

    <table class="table table-striped table-bordered">
      <thead>
          <tr>
              <th>姓名</th>
              <th>性别</th>
              <th>头像</th>
              <th>职位</th>
              <th>入职日期</th>
              <th>最后操作时间</th>
              <th>操作</th>
          </tr>
      </thead>
      <tbody>
        <tr>
            <td>令狐冲</td>
            <td>男</td>
            <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="令狐冲" class="avatar"></td>
            <td>讲师</td>
            <td>2021-03-15</td>
            <td>2023-07-30T12:00:00Z</td>
            <td class="btn-group">
                <button class="edit">编辑</button>
                <button class="delete">删除</button>
            </td>
        </tr>
        <tr>
            <td>任盈盈</td>
            <td>女</td>
            <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="任盈盈" class="avatar"></td>
            <td>学工主管</td>
            <td>2020-04-10</td>
            <td>2023-07-29T15:00:00Z</td>
            <td class="btn-group">
                <button class="edit">编辑</button>
                <button class="delete">删除</button>
            </td>
        </tr>
        <tr>
            <td>岳不群</td>
            <td>男</td>
            <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="岳不群" class="avatar"></td>
            <td>教研主管</td>
            <td>2019-01-01</td>
            <td>2023-07-30T10:00:00Z</td>
            <td class="btn-group">
                <button class="edit">编辑</button>
                <button class="delete">删除</button>
            </td>
        </tr>
        <tr>
            <td>宁中则</td>
            <td>女</td>
            <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="宁中则" class="avatar"></td>
            <td>班主任</td>
            <td>2018-06-01</td>
            <td>2023-07-29T09:00:00Z</td>
            <td class="btn-group">
                <button class="edit">编辑</button>
                <button class="delete">删除</button>
            </td>
        </tr>
        <tr>
          <td>令狐冲</td>
          <td>男</td>
          <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="令狐冲" class="avatar"></td>
          <td>讲师</td>
          <td>2021-03-15</td>
          <td>2023-07-30T12:00:00Z</td>
          <td class="btn-group">
              <button class="edit">编辑</button>
              <button class="delete">删除</button>
          </td>
        </tr>
        <tr>
            <td>任盈盈</td>
            <td>女</td>
            <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="任盈盈" class="avatar"></td>
            <td>学工主管</td>
            <td>2020-04-10</td>
            <td>2023-07-29T15:00:00Z</td>
            <td class="btn-group">
                <button class="edit">编辑</button>
                <button class="delete">删除</button>
            </td>
        </tr>
        <tr>
            <td>岳不群</td>
            <td>男</td>
            <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="岳不群" class="avatar"></td>
            <td>教研主管</td>
            <td>2019-01-01</td>
            <td>2023-07-30T10:00:00Z</td>
            <td class="btn-group">
                <button class="edit">编辑</button>
                <button class="delete">删除</button>
            </td>
        </tr>
        <tr>
            <td>宁中则</td>
            <td>女</td>
            <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="宁中则" class="avatar"></td>
            <td>班主任</td>
            <td>2018-06-01</td>
            <td>2023-07-29T09:00:00Z</td>
            <td class="btn-group">
                <button class="edit">编辑</button>
                <button class="delete">删除</button>
            </td>
        </tr>
        <tr>
          <td>令狐冲</td>
          <td>男</td>
          <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="令狐冲" class="avatar"></td>
          <td>讲师</td>
          <td>2021-03-15</td>
          <td>2023-07-30T12:00:00Z</td>
          <td class="btn-group">
              <button class="edit">编辑</button>
              <button class="delete">删除</button>
          </td>
        </tr>
        <tr>
            <td>任盈盈</td>
            <td>女</td>
            <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="任盈盈" class="avatar"></td>
            <td>学工主管</td>
            <td>2020-04-10</td>
            <td>2023-07-29T15:00:00Z</td>
            <td class="btn-group">
                <button class="edit">编辑</button>
                <button class="delete">删除</button>
            </td>
        </tr>
        <tr>
            <td>岳不群</td>
            <td>男</td>
            <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="岳不群" class="avatar"></td>
            <td>教研主管</td>
            <td>2019-01-01</td>
            <td>2023-07-30T10:00:00Z</td>
            <td class="btn-group">
                <button class="edit">编辑</button>
                <button class="delete">删除</button>
            </td>
        </tr>
        <tr>
            <td>宁中则</td>
            <td>女</td>
            <td><img src="https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg" alt="宁中则" class="avatar"></td>
            <td>班主任</td>
            <td>2018-06-01</td>
            <td>2023-07-29T09:00:00Z</td>
            <td class="btn-group">
                <button class="edit">编辑</button>
                <button class="delete">删除</button>
            </td>
        </tr>
      </tbody>
    </table>

    <!-- 页脚版权区域 -->
    <footer class="footer">
      <p class="company-name">江苏传智播客教育科技股份有限公司</p>
      <p class="copyright">版权所有 Copyright 2006-2024 All Rights Reserved</p>
    </footer>

    <script>
      //点击列表中每一条记录后面的删除按钮，弹出一个提示框，提示：您是否要删除这条记录？ 如果确定，则移出这条记录
      const deleteButtons = document.querySelectorAll('.delete');
      for (let i = 0; i < deleteButtons.length; i++) {
        deleteButtons[i].addEventListener('click', function () {
          if (confirm('您是否要删除这条记录？')) {
            //点击确定按钮，删除当前记录
            this.parentNode.parentNode.remove();
          }
        });
      }
      
      //通过js实现鼠标移入移出效果，鼠标移入，背景色为 #f2e2e2，鼠标移出，背景色为 白色。
      const trs = document.querySelectorAll('tr');
      for (let i = 1; i < trs.length; i++) {
        trs[i].addEventListener('mouseenter', function () {
          this.style.backgroundColor = '#f2e2e2';
        });
        trs[i].addEventListener('mouseleave', function () {
          this.style.backgroundColor = '#fff';
        });
      }
    </script>
    
  </div>
    
</body>
</html>
```

### 常见事件

上面案例中使用到了事件 `click`、`mouseenter`、`mouseleave`，那都有哪些事件类型供我们使用呢？下面就给大家列举一些比较常用的事件属性: 

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ODMwZTIyNzQzYzZlNzdmODAyYTQ5NDkzYTFkYTExNjJfcWk4dkEzczRWaEpWTjlVWG5WaVVOZ3BoZlhHUG4zVWJfVG9rZW46RndEWWJJQTZyb2IzNjl4dU9xYWNIcDF0bjZkXzE3OTA4NjU3Mzg6MTc5MDg2OTMzOF9WNA&add_watermark=true&scene_type=CCM)

示例演示：

```HTML
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JS-事件-常见事件</title>
</head>

<body>
    <form action="" style="text-align: center;">
        <input type="text" name="username" id="username">
        <input type="text" name="age" id="age">
        <input id="b1" type="submit" value="提交">
        <input id="b2" type="button" value="单击事件">
    </form>

    <br><br><br>

    <table width="800px" border="1" cellspacing="0" align="center">
        <tr>
            <th>学号</th>
            <th>姓名</th>
            <th>分数</th>
            <th>评语</th>
        </tr>
        <tr align="center">
            <td>001</td>
            <td>张三</td>
            <td>90</td>
            <td>很优秀</td>
        </tr>
        <tr align="center" id="last">
            <td>002</td>
            <td>李四</td>
            <td>92</td>
            <td>优秀</td>
        </tr>
    </table>
    
    <script>
        //click: 鼠标点击事件
        document.querySelector('#b2').addEventListener('click', () => {
            console.log("我被点击了...");
        })
        
        //mouseenter: 鼠标移入
        document.querySelector('#last').addEventListener('mouseenter', () => {
            console.log("鼠标移入了...");
        })

        //mouseleave: 鼠标移出
        document.querySelector('#last').addEventListener('mouseleave', () => {
            console.log("鼠标移出了...");
        })

        //keydown: 某个键盘的键被按下
        document.querySelector('#username').addEventListener('keydown', () => {
            console.log("键盘被按下了...");
        })

        //keydown: 某个键盘的键被抬起
        document.querySelector('#username').addEventListener('keyup', () => {
            console.log("键盘被抬起了...");
        })

        //blur: 失去焦点事件
        document.querySelector('#age').addEventListener('blur', () => {
            console.log("失去焦点...");
        })

        //focus: 元素获得焦点
        document.querySelector('#age').addEventListener('focus', () => {
            console.log("获得焦点...");
        })

        //input: 用户输入时触发
        document.querySelector('#age').addEventListener('input', () => {
            console.log("用户输入时触发...");
        })

        //submit: 提交表单事件
        document.querySelector('form').addEventListener('submit', () => {
            alert("表单被提交了...");
        })
    </script>
</body>

</html>
```

前面两天，我们已经学习了前端网页开发的三剑客：HTML、CSS、JS。那通过这三种技术呢，我们就可以开发出一个网页程序了，但是如果我们使用原生的JS来处理界面的交互行为，开发效率呢，是比较低的。而在现在的企业项目开发中，一般会借助于Vue这样的js框架来简化操作、提高开发效率。 那么我们今天接下来呢，就来学习Vue这个框架。

## Vue介绍

### 概述

Vue（读音 /vjuː /, 类似于 view），是一款用于**构建用户界面**的**渐进式**的JavaScript**框架**（官方网站：https://cn.vuejs.org）。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZmQyZWYxZmNmOTc2OTMwN2U4MmUyNmQ5ODMzODFhNTdfOWthZ3RHQ2JNcUM4WmhmZHZvSzRFQWVwcWFTUTZ6ZmtfVG9rZW46SVpoRmJTY3JYbzNGWXl4Qk5YbGNJQXdwbkdlXzE3OTA4NjU3Mzg6MTc5MDg2OTMzOF9WNA&add_watermark=true&scene_type=CCM)

在上面的这句话中呢，出现了三个词，分别是：**构建用户界面、渐进式、框架**。

**1). 构建用户界面**

构建用户界面是指，在Vue中，可以基于数据渲染出用户看到的界面。 那这句话什么意思呢？我们来举一个例子，比如将来服务器端返回给前端的原始数据呢，就是如下这个样子：

```JSON
userList: [
    {"id": 1, "name": "谢逊", "image": "1.jpg", "gender": 1, "job": "班主任"},
    {"id": 2, "name": "韦一笑", "image": "2.jpg", "gender": 1, "job": "班主任"}
]
```

而上面的这些原始数据，用户是看不懂的。 而我们开发人员呢，可以使用Vue中提供的操作，将原始数据遍历、解析出来，从而渲染呈现出用户所能看懂的界面，如下所示：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MTZmMDlmYzk4YTEyOTZiYTdhZGE1ZGYxYmQ2ZTM3ZDVfMUFUdmo0cUlPTjRvY0tzV0prMFBIZW9yR0tTNHB4ZjZfVG9rZW46WkxkbmJKRnRIb1VIMzR4VzVXaWNaQ2NIbnVoXzE3OTA4NjU3Mzg6MTc5MDg2OTMzOF9WNA&add_watermark=true&scene_type=CCM)

那这个过程呢，就是基于数据渲染出用户看到的界面，也就是所谓的 构建用户界面。

**2). 渐进式**

渐进式中的渐进呢，字面意思就是 "循序渐进"。Vue生态中的语法呢是非常多的，比如声明式渲染、组件系统、客户端路由（VueRouter）、状态管理（Vuex、Pinia）、构建工具（Webpack、Vite）等等。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MGMzNzVmN2FmZTdhNjVlM2ZjOTE1MzEzZDg0ZjAxMDVfRjJSVnY1emg4UGd1Y0dleDZwRGZGdldRdXdhU2RJSkNfVG9rZW46U3FEWWJoa1FBb0ZubGN4NFp6aGM2d0ZLbjBmXzE3OTA4NjU3Mzg6MTc5MDg2OTMzOF9WNA&add_watermark=true&scene_type=CCM)

所谓渐进，指的是我们使用Vue框架呢，我们不需要把所有的组件、语法全部学习完毕才可以使用Vue。 而是，我们学习一点就可以使用一点了，比如：

- 我们学习了声明式渲染，我们就可以使用Vue来构建用户界面了。 
- 我们再学习了组件系统，我们就可以使用Vue中的组件，从而来复用了。
- 我们再学习了路由VueRouter，就可以使用Vue中的中的路由功能了。

也就是说，并不需要全部学习完毕就可以直接使用Vue进行开发，简化操作、提高效率了。 Vue是一个框架，但其实也是一个生态。

那由此呢，也就引出了Vue中两种常见的开发模式：

- 基于Vue提供的核心包，完成项目局部模块的改造了。
- 基于Vue提供的核心包、插件进行工程化开发，也就是做整站开发。

那上面的这两种Vue的使用形式，我们都会学习，今天我们先来学习第一种方式，就是使用Vue来完成局部模块改造。

**3). 框架**

- 框架：就是一套完整的项目解决方案，用于快速构建项目 。这是我们接触的第一个框架，那在我们后面的学习中，我们还会学习很多的java语言中的框架，那通过这些框架呢，就可以来快速开发java项目，提高开发效率。
- 优点：大大提升前端项目的开发效率 。
- 缺点：需要理解记忆框架的使用规则 。（参照官网）

好，那我们知道了什么是Vue之后，接下来，就要正式进入Vue的学习，我们今天主要讲解以下几个方面：

1. Vue快速入门

2. Vue常用指令

3. Ajax

4. Vue生命周期

### 入门程序

   #### 需求

在入门程序中，最终我们需要将准备的数据 message 的值，基于Vue渲染展示在页面中，最终呈现的形式如下：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZjlmZmRiYmE0NTk5MDMyYWRmMDkzYTc0ODA4ZTk4ZDBfYzdUSHI3S1pJdEZGdlFJNWpDNkR1N2xxTE1JOE9XNHFfVG9rZW46WU9QV2I5VExpb1ZVcnN4SkZYS2MyUWlabm9oXzE3OTA4NjU3Mzg6MTc5MDg2OTMzOF9WNA&add_watermark=true&scene_type=CCM)

#### 步骤

**1). 准备工作：**

- 准备一个html文件，并在其中引入Vue模块 （参考官方文档，复制过来即可）【注意：模块化的js，引入时，需要设置 `type="module"`】
- 创建Vue程序的应用实例，控制视图的元素
- 准备元素（div），交给Vue控制

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MDE1MzcxODMyODMwZDlmMjQ2M2Q5NzExZjE3ODhkYjVfRVhONVdoNGpUaGk0VndKM3c0V0FkRGZ4TlFQNGVKcXVfVG9rZW46U2tZZGJPVXFzb3IyWVJ4ZzZJVGN3a3Y0bnNmXzE3OTA4NjU3Mzg6MTc5MDg2OTMzOF9WNA&add_watermark=true&scene_type=CCM)

这是三步准备工作，是我们使用Vue时，都需要做的，是固定步骤。 这样我们就搭建好了一个基本的Vue的结构了。

**2). 数据驱动视图：**

- 准备数据。 在创建Vue应用实例的时候，传入了一个js对象，在这个js对象中，我们要定义一个data方法，这个data方法的返回值就是Vue中的数据。
- 通过插值表达式渲染页面。 插值表达式的写法：{{...}}

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NTZjYWE1ZWRjMDU0Mjk5NGQ0ZjM3NTIwMDJhZTJhMGVfMThxbE1TT0R1QjdSWkJEOUk4ZWdBdENvWVl1djRFbm1fVG9rZW46TWdzaGIySEYyb012ZEN4cUI1a2NueXBrbmtoXzE3OTA4NjU3Mzg6MTc5MDg2OTMzOF9WNA&add_watermark=true&scene_type=CCM)

#### 实现

```HTML
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Vue-快速入门</title>
</head>
<body>
  <div id="app">
    {{message}}
  </div>
  
  <script type="module">
    import { createApp } from 'https://unpkg.com/vue@3/dist/vue.esm-browser.js'
    createApp({
      data(){
        return {
          message: 'Hello Vue'
        }
      }
    }).mount('#app')
  </script>
</body>
</html>
```

在上述入门程序编写时，需要注意这么几点：

- Vue中定义数据，必须通过data方法来定义，data方法返回值是一个对象，在这个对象中定义数据。
- 插值表达式中编写的变量，一定是Vue中定义的数据，如果插值表达式中编写了一个变量，但是在Vue中未定义，将会报错 。
- Vue应用实例接管的区域是 '#app'，超出这个范围，就不受Vue控制了，所以vue的插值表达式，一定写在 `<div id="app">...</div>` 的里面 。

## Vue指令

### 概述

**指令：**指的是HTML 标签上带有 v- 前缀的特殊属性，不同指令具有不同含义，可以实现不同的功能 。例如：v-if，v-for…

**形式：**

```HTML
<p v-xxx="....">.....</p>
```

**常见指令：**

| 指令                  | 作用                                                |
| --------------------- | --------------------------------------------------- |
| v-for                 | 列表渲染，遍历容器的元素或者对象的属性              |
| v-bind                | 为HTML标签绑定属性值，如设置 href , css样式等       |
| v-if/v-else-if/v-else | 条件性的渲染某元素，判定为true时渲染,否则不渲染     |
| v-show                | 根据条件展示某元素，区别在于切换的是display属性的值 |
| v-model               | 在表单元素上创建双向数据绑定                        |
| v-on                  | 为HTML标签绑定事件                                  |

### 案例

#### 基本实现

需求：员工列表数据渲染展示 。（备注：基础代码在资料中，已经提供，导入VsCode即可）

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NzMwMGE0ZThiOTNlZjQ5Y2M4ZDFjMTkzYzdkOWUzMThfV0xMb01URmduVmN5SHI5anQxYjhFeXhVV1VZWjJFYUVfVG9rZW46VHBoRGJWWDBybzZYclZ4TmlLR2NDUHhwbldoXzE3OTA4NjU3Mzg6MTc5MDg2OTMzOF9WNA&add_watermark=true&scene_type=CCM)

AI提示词（prompt）：

基于Vue3中提供的指令，渲染展示employees中的数据，并展示在页面上；

- 性别gender为1展示为男，性别为2展示为女；
- 职位job为1展示为班主任，职位为2展示为讲师，职位为3展示为学工主管，职位为4展示为教研主管，职位为5展示为咨询师； 
- 其他的属性直接展示；

页面的内容如下：

.....

生成后的代码如下：

```HTML
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tlias智能学习辅助系统</title>
    <style>
      body {
        margin: 0;
      }

      /* 顶栏样式 */
      .header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        background-color: #c2c0c0;
        padding: 20px 20px;
        box-shadow: 0 2px 5px rgba(0,0,0,0.1);
      }
      
      /* 加大加粗标题 */
      .header h1 {
        margin: 0;
        font-size: 24px;
        font-weight: bold;
      }

      /* 文本链接样式 */
      .header a {
        text-decoration: none;
        color: #333;
        font-size: 16px;
      }

      /* 搜索表单区域 */
      .search-form {
        display: flex;
        align-items: center;
        padding: 20px;
        background-color: #f9f9f9;
      }

      /* 表单控件样式 */
      .search-form input[type="text"], .search-form select {
        margin-right: 10px;
        padding: 10px 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
        width: 26%;
      }

      /* 按钮样式 */
      .search-form button {
        padding: 10px 15px;
        margin-left: 10px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
      }

      /* 清空按钮样式 */
      .search-form button.clear {
        background-color: #6c757d;
      }

      .table {
         min-width: 100%; 
         border-collapse: collapse;
      }

      /* 设置表格单元格边框 */
      .table td, .table th { 
        border: 1px solid #ddd; 
        padding: 8px; 
        text-align: center;
      }
      
      .avatar { 
        width: 30px; 
        height: 30px; 
        object-fit: cover; 
        border-radius: 50%; 
      }

      /* 页脚版权区域 */
    .footer {
        background-color: #c2c0c0;
        color: white;
        text-align: center;
        padding: 10px 0;
        margin-top: 30px;
    }

    .footer .company-name {
        font-size: 1.1em;
        font-weight: bold;
    }

    .footer .copyright {
        font-size: 0.9em;
    }

    #container {
      width: 80%;
      margin: 0 auto;
    }
    </style>
</head>
<body>
    
  <div id="container">
    <!-- 顶栏 -->
    <div class="header">
      <h1>Tlias智能学习辅助系统</h1>
      <a href="#">退出登录</a>
    </div>

    <!-- 搜索表单区域 -->
    <form class="search-form" action="#" method="post">
      <input type="text" name="name" placeholder="姓名" />
      <select name="gender">
          <option value="">性别</option>
          <option value="1">男</option>
          <option value="2">女</option>
      </select>
      <select name="job">
          <option value="">职位</option>
          <option value="1">班主任</option>
          <option value="2">讲师</option>
          <option value="3">学工主管</option>
          <option value="4">教研主管</option>
          <option value="5">咨询师</option>
      </select>
      <button type="submit">查询</button>
      <button type="reset" class="clear">清空</button>
    </form>

    <table class="table table-striped table-bordered">
      <thead>
          <tr>
              <th>姓名</th>
              <th>性别</th>
              <th>头像</th>
              <th>职位</th>
              <th>入职日期</th>
              <th>最后操作时间</th>
              <th>操作</th>
          </tr>
      </thead>
      <tbody>
        <tr v-for="(emp, index) in empList" :key="index">
          <td>{{ emp.name }}</td>
          <td>{{ emp.gender === 1 ? '男' : '女' }}</td>
          <td><img :src="emp.image" alt="{{ emp.name }}" class="avatar"></td>
          <td>{{ emp.job === '1' ? '班主任' : (emp.job === '2' ? '讲师' : (emp.job === '3' ? '学工主管' : (emp.job === '4' ? '教研主管' : (emp.job === '5' ? '咨询师' : '未知')))) }}</td>
          <td>{{ emp.entrydate }}</td>
          <td>{{ emp.updatetime }}</td>
          <td class="btn-group">
            <button class="edit">编辑</button>
            <button class="delete">删除</button>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- 页脚版权区域 -->
    <footer class="footer">
      <p class="company-name">江苏传智播客教育科技股份有限公司</p>
      <p class="copyright">版权所有 Copyright 2006-2024 All Rights Reserved</p>
    </footer>

    <script type="module">
      import { createApp } from 'https://unpkg.com/vue@3/dist/vue.esm-browser.js'
      createApp({
        data() {
          return {
            empList: [
              { "id": 1,
                "name": "谢逊",
                "image": "https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/4.jpg",
                "gender": 1,
                "job": "1",
                "entrydate": "2023-06-09",
                "updatetime": "2024-07-30T14:59:38"
              },
              {
                "id": 2,
                "name": "韦一笑",
                "image": "https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg",
                "gender": 1,
                "job": "1",
                "entrydate": "2020-05-09",
                "updatetime": "2023-07-01T00:00:00"
              },
              {
                "id": 3,
                "name": "黛绮丝",
                "image": "https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/2.jpg",
                "gender": 2,
                "job": "2",
                "entrydate": "2021-06-01",
                "updatetime": "2023-07-01T00:00:00"
              }
            ]
          }
        }
      }).mount('#container')
    </script>

  </div>

</body>
</html>
```

最终页面展示效果：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NDM3YWI3YTJjMDdlYTRiZjdmODA1MGMyY2JkMjMzMmJfb0pkVjhORVVtNDZITGY1ZEF1aHl0UUowZUtCbmpwa3lfVG9rZW46V0c5Q2IwbjdLb3JXTmV4RkFGRWNLZUVEbkNlXzE3OTA4NjU3Mzk6MTc5MDg2OTMzOV9WNA&add_watermark=true&scene_type=CCM)

那我们看到，通过AI生成的代码，已经实现了员工列表数据渲染的功能。 而这里面呢，就用到了Vue中提供的指令和插值表达式。 那接下来，我们就来详细剖析一下Vue中指令的用法。

#### 指令详解

##### v-for

**作用：**列表渲染，遍历容器的元素或者对象的属性

**语法：**`<tr v-for="(item,index) in items" :key="item.id">{{item}}</tr>`

**参数：**

- items 为遍历的数组
- item 为遍历出来的元素
- index 为索引/下标，从0开始 ；可以省略，省略index语法： `v-for = "item in items"`

**key：**

- 作用：给元素添加的唯一标识，便于vue进行列表项的正确排序复用，提升渲染性能
- 推荐使用id作为key（唯一），不推荐使用index作为key（会变化，不对应）

**注意：遍历的数组，必须在data中定义； 要想让哪个标签循环展示多次，就在哪个标签上使用 v-for 指令。**

##### v-bind

作用：动态为HTML标签绑定属性值，如设置href，src，style样式等。

语法：`v-bind:属性名="属性值"` `<img v-bind:src="item.image" width="30px">`

简化：`:属性名="属性值"` `<img :src="item.image" width="30px">`

**注意：v-bind 所绑定的数据，必须在data中定义/或基于data中定义的数据而来。**

##### v-if & v-show

**作用：**这两类指令，都是用来控制元素的显示与隐藏的

**v-if：**

- 语法：v-if="表达式"，表达式值为 true，显示；false，隐藏
- 原理：基于条件判断，来控制创建或移除元素节点（条件渲染）
- 场景：要么显示，要么不显示，不频繁切换的场景
- 其它：可以配合 v-else-if / v-else 进行链式调用条件判断

**示例：**

```HTML
   <!-- 基于v-if/v-else-if/v-else指令来展示职位这一列 -->
  <td>
    <span v-if="emp.job === '1'">班主任</span>
    <span v-else-if="emp.job === '2'">讲师</span>
    <span v-else-if="emp.job === '3'">学工主管</span>
    <span v-else-if="emp.job === '4'">教研主管</span>
    <span v-else-if="emp.job === '5'">咨询师</span>
    <span v-else>其他</span>
  </td>
```

**注意：v-else-if必须出现在v-if之后，可以出现多个； v-else 必须出现在v-if/v-else-if之后 。** 

通过浏览器的开发者工具，我们可以看到如果使用 `v-if` 指令来渲染展示，确实是根据条件判断，是否渲染这个元素节点，条件成立才会渲染。查看结果如下：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NTI4M2UzOWIyMGVlZTRjYjY0ZTBkMGM1YTU4NDUyOTlfSEJMZlJYYUFzMTBRc2FYTTlZTVRXV1gwUllNdVVuNnlfVG9rZW46V2NldWI1ckR1b0liMm14MnNVNWNrVUhxbjVkXzE3OTA4NjU3Mzk6MTc5MDg2OTMzOV9WNA&add_watermark=true&scene_type=CCM)

**v-show：**

- 语法：v-show="表达式"，表达式值为 true，显示；false，隐藏
- 原理：基于CSS样式display来控制显示与隐藏
- 场景：频繁切换显示隐藏的场景

**示例：**

```HTML
<!-- 基于v-show指令来展示职位这一列 -->
<td>
    <span v-show="emp.job === '1'">班主任</span>
    <span v-show="emp.job === '2'">讲师</span>
    <span v-show="emp.job === '3'">学工主管</span>
    <span v-show="emp.job === '4'">教研主管</span>
    <span v-show="emp.job === '5'">咨询师</span>
</td>
```

通过浏览器的开发者工具，我们可以看到如果使用 `v-show` 指令来渲染展示，所有元素都会渲染，只不过是通过控制display这个css样式，来决定元素是展示还是隐藏。查看结果如下：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=N2YxMDVkOTIzNzVlZjkyYjM1YTM1OWZiNzAyYjQwMTNfcFhYNlEyVWpyMzJWckVBTGlucFhhQ01TckY2QUFucURfVG9rZW46S2Z5OGJHTmhHb2lrT0x4WHRmS2NjbXVhbjhlXzE3OTA4NjU3Mzk6MTc5MDg2OTMzOV9WNA&add_watermark=true&scene_type=CCM)

##### v-model

- 作用：在表单元素上使用，**双向数据绑定**。可以方便的 **获取** 或 **设置** 表单项数据 
- 语法：`v-model="变量名"`
- 这里的双向数据绑定，是指 Vue中的数据变化，会影响视图中的数据展示 。 视图中的输入的数据变化，也会影响Vue的数据模型 。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZDVjYWRlN2U4NGFjZGJmYWY1NjJlMGQxY2I2ZjIyM2RfOG9XNkhvSlhtd2M5cHJtVEtuZ3J6dHkwa21pZTd0RjhfVG9rZW46V002RGJEZjFUb21XTXV4alFoaWNpYkRTblZkXzE3OTA4NjU3Mzk6MTc5MDg2OTMzOV9WNA&add_watermark=true&scene_type=CCM)

**注意：v-model 中绑定的变量，必须在data中定义。**

- 为员工列表案例的搜索栏的表单项，绑定数据：

```HTML
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tlias智能学习辅助系统</title>
    <style>
      body {
        margin: 0;
      }

      /* 顶栏样式 */
      .header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        background-color: #c2c0c0;
        padding: 20px 20px;
        box-shadow: 0 2px 5px rgba(0,0,0,0.1);
      }
      
      /* 加大加粗标题 */
      .header h1 {
        margin: 0;
        font-size: 24px;
        font-weight: bold;
      }

      /* 文本链接样式 */
      .header a {
        text-decoration: none;
        color: #333;
        font-size: 16px;
      }

      /* 搜索表单区域 */
      .search-form {
        display: flex;
        align-items: center;
        padding: 20px;
        background-color: #f9f9f9;
      }

      /* 表单控件样式 */
      .search-form input[type="text"], .search-form select {
        margin-right: 10px;
        padding: 10px 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
        width: 26%;
      }

      /* 按钮样式 */
      .search-form button {
        padding: 10px 15px;
        margin-left: 10px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
      }

      /* 清空按钮样式 */
      .search-form button.clear {
        background-color: #6c757d;
      }

      .table {
         min-width: 100%; 
         border-collapse: collapse;
      }

      /* 设置表格单元格边框 */
      .table td, .table th { 
        border: 1px solid #ddd; 
        padding: 8px; 
        text-align: center;
      }
      
      .avatar { 
        width: 30px; 
        height: 30px; 
        object-fit: cover; 
        border-radius: 50%; 
      }

      /* 页脚版权区域 */
    .footer {
        background-color: #c2c0c0;
        color: white;
        text-align: center;
        padding: 10px 0;
        margin-top: 30px;
    }

    .footer .company-name {
        font-size: 1.1em;
        font-weight: bold;
    }

    .footer .copyright {
        font-size: 0.9em;
    }

    #container {
      width: 80%;
      margin: 0 auto;
    }
    </style>
</head>
<body>
    
  <div id="container">
    <!-- 顶栏 -->
    <div class="header">
      <h1>Tlias智能学习辅助系统</h1>
      <a href="#">退出登录</a>
    </div>

    <!-- 搜索表单区域 -->
    <form class="search-form" action="#" method="post">
      <input type="text" name="name" placeholder="姓名" v-model="searchEmp.name" />
      <select name="gender" v-model="searchEmp.gender">
          <option value="">性别</option>
          <option value="1">男</option>
          <option value="2">女</option>
      </select>
      <select name="job" v-model="searchEmp.job">
          <option value="">职位</option>
          <option value="1">班主任</option>
          <option value="2">讲师</option>
          <option value="3">学工主管</option>
          <option value="4">教研主管</option>
          <option value="5">咨询师</option>
      </select>
      <button type="submit">查询</button>
      <button type="reset" class="clear">清空</button>
    </form>

    <table class="table table-striped table-bordered">
      <thead>
          <tr>
              <th>姓名</th>
              <th>性别</th>
              <th>头像</th>
              <th>职位</th>
              <th>入职日期</th>
              <th>最后操作时间</th>
              <th>操作</th>
          </tr>
      </thead>
      <tbody>
        <tr v-for="(emp, index) in empList" :key="index">
          <td>{{ emp.name }}</td>
          <td>{{ emp.gender === 1 ? '男' : '女' }}</td>
          <td><img :src="emp.image" class="avatar"></td>
          <td>
            <span v-if="emp.job === '1'">班主任</span>
            <span v-else-if="emp.job === '2'">讲师</span>
            <span v-else-if="emp.job === '3'">学工主管</span>
            <span v-else-if="emp.job === '4'">教研主管</span>
            <span v-else-if="emp.job === '5'">咨询师</span>
          </td>
          <td>{{ emp.entrydate }}</td>
          <td>{{ emp.updatetime }}</td>
          <td class="btn-group">
            <button class="edit">编辑</button>
            <button class="delete">删除</button>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- 页脚版权区域 -->
    <footer class="footer">
      <p class="company-name">江苏传智播客教育科技股份有限公司</p>
      <p class="copyright">版权所有 Copyright 2006-2024 All Rights Reserved</p>
    </footer>

    <script type="module">
      import { createApp } from 'https://unpkg.com/vue@3/dist/vue.esm-browser.js'
      createApp({
        data() {
          return {
            searchEmp: {
              name: '',
              gender: '',
              job: ''
            },
            empList: [
              { "id": 1,
                "name": "谢逊",
                "image": "https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/4.jpg",
                "gender": 1,
                "job": "1",
                "entrydate": "2023-06-09",
                "updatetime": "2024-07-30T14:59:38"
              },
              {
                "id": 2,
                "name": "韦一笑",
                "image": "https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg",
                "gender": 1,
                "job": "1",
                "entrydate": "2020-05-09",
                "updatetime": "2023-07-01T00:00:00"
              },
              {
                "id": 3,
                "name": "黛绮丝",
                "image": "https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/2.jpg",
                "gender": 2,
                "job": "2",
                "entrydate": "2021-06-01",
                "updatetime": "2023-07-01T00:00:00"
              }
            ]
          }
        }
      }).mount('#container')
    </script>

  </div>

</body>
</html>
```

##### v-on

作用：为html标签绑定事件（添加时间监听）

语法：

- `v-on:事件名="方法名"` 
- 简写为 `@事件名="…"` 
- `<input type="button" value="点我一下试试" v-on:click="handle">`
- `<input type="button" value="点我一下试试" @click="handle">`

 

这里的handle函数，就需要在Vue应用实例创建的时候创建出来，在methods定义。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MGViNWUwMzY3MGVkMDllZjg4MDI4YmEyYTk0ZWViMTdfNDJzZ2hQVXFEM1FmSVdPcEcwMVVwVzNOQ2x6SUJOeE9fVG9rZW46VTV3YmJpRTVtb280Mkd4TzRFQWNGRTNsbmlmXzE3OTA4NjU3Mzk6MTc5MDg2OTMzOV9WNA&add_watermark=true&scene_type=CCM)

- 为员工列表案例的搜索栏中的查询 和 清空按钮绑定事件：

```HTML
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tlias智能学习辅助系统</title>
    <style>
      body {
        margin: 0;
      }

      /* 顶栏样式 */
      .header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        background-color: #c2c0c0;
        padding: 20px 20px;
        box-shadow: 0 2px 5px rgba(0,0,0,0.1);
      }
      
      /* 加大加粗标题 */
      .header h1 {
        margin: 0;
        font-size: 24px;
        font-weight: bold;
      }

      /* 文本链接样式 */
      .header a {
        text-decoration: none;
        color: #333;
        font-size: 16px;
      }

      /* 搜索表单区域 */
      .search-form {
        display: flex;
        align-items: center;
        padding: 20px;
        background-color: #f9f9f9;
      }

      /* 表单控件样式 */
      .search-form input[type="text"], .search-form select {
        margin-right: 10px;
        padding: 10px 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
        width: 26%;
      }

      /* 按钮样式 */
      .search-form button {
        padding: 10px 15px;
        margin-left: 10px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
      }

      /* 清空按钮样式 */
      .search-form button.clear {
        background-color: #6c757d;
      }

      .table {
         min-width: 100%; 
         border-collapse: collapse;
      }

      /* 设置表格单元格边框 */
      .table td, .table th { 
        border: 1px solid #ddd; 
        padding: 8px; 
        text-align: center;
      }
      
      .avatar { 
        width: 30px; 
        height: 30px; 
        object-fit: cover; 
        border-radius: 50%; 
      }

      /* 页脚版权区域 */
    .footer {
        background-color: #c2c0c0;
        color: white;
        text-align: center;
        padding: 10px 0;
        margin-top: 30px;
    }

    .footer .company-name {
        font-size: 1.1em;
        font-weight: bold;
    }

    .footer .copyright {
        font-size: 0.9em;
    }

    #container {
      width: 80%;
      margin: 0 auto;
    }
    </style>
</head>
<body>
    
  <div id="container">
    <!-- 顶栏 -->
    <div class="header">
      <h1>Tlias智能学习辅助系统</h1>
      <a href="#">退出登录</a>
    </div>
    
    <!-- 搜索表单区域 -->
    <form class="search-form">
      <input type="text" name="name" placeholder="姓名" v-model="searchEmp.name" />
      <select name="gender" v-model="searchEmp.gender">
          <option value="">性别</option>
          <option value="1">男</option>
          <option value="2">女</option>
      </select>
      <select name="job" v-model="searchEmp.job">
          <option value="">职位</option>
          <option value="1">班主任</option>
          <option value="2">讲师</option>
          <option value="3">学工主管</option>
          <option value="4">教研主管</option>
          <option value="5">咨询师</option>
      </select>
      <button type="button" @click="search">查询</button>
      <button type="button" @click="clear">清空</button>
    </form>

    <table class="table table-striped table-bordered">
      <thead>
          <tr>
              <th>姓名</th>
              <th>性别</th>
              <th>头像</th>
              <th>职位</th>
              <th>入职日期</th>
              <th>最后操作时间</th>
              <th>操作</th>
          </tr>
      </thead>
      <tbody>
        <tr v-for="(emp, index) in empList" :key="index">
          <td>{{ emp.name }}</td>
          <td>{{ emp.gender === 1 ? '男' : '女' }}</td>
          <td><img :src="emp.image" alt="{{ emp.name }}" class="avatar"></td>
          <td>
            <span v-if="emp.job === '1'">班主任</span>
            <span v-else-if="emp.job === '2'">讲师</span>
            <span v-else-if="emp.job === '3'">学工主管</span>
            <span v-else-if="emp.job === '4'">教研主管</span>
            <span v-else-if="emp.job === '5'">咨询师</span>
          </td>
          <td>{{ emp.entrydate }}</td>
          <td>{{ emp.updatetime }}</td>
          <td class="btn-group">
            <button class="edit">编辑</button>
            <button class="delete">删除</button>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- 页脚版权区域 -->
    <footer class="footer">
      <p class="company-name">江苏传智播客教育科技股份有限公司</p>
      <p class="copyright">版权所有 Copyright 2006-2024 All Rights Reserved</p>
    </footer>

    <script type="module">
      import { createApp } from 'https://unpkg.com/vue@3/dist/vue.esm-browser.js'
      createApp({
        data() {
          return {
            searchEmp: {
              name: '',
              gender: '',
              job: ''
            },
            empList: [
              { "id": 1,
                "name": "谢逊",
                "image": "https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/4.jpg",
                "gender": 1,
                "job": "1",
                "entrydate": "2023-06-09",
                "updatetime": "2024-07-30T14:59:38"
              },
              {
                "id": 2,
                "name": "韦一笑",
                "image": "https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/1.jpg",
                "gender": 1,
                "job": "1",
                "entrydate": "2020-05-09",
                "updatetime": "2023-07-01T00:00:00"
              },
              {
                "id": 3,
                "name": "黛绮丝",
                "image": "https://web-framework.oss-cn-hangzhou.aliyuncs.com/2023/2.jpg",
                "gender": 2,
                "job": "2",
                "entrydate": "2021-06-01",
                "updatetime": "2023-07-01T00:00:00"
              }
            ]
          }
        },
        methods: {
          search() {
            console.log(this.searchEmp)
          },
          clear() {
            this.searchEmp = {
              name: '',
              gender: '',
              job: ''
            }
          }
        }
      }).mount('#container')
    </script>

  </div>

</body>
</html>
```

**注意：** **methods函数中的this指向Vue实例，可以通过this获取到data中定义的数据****。**

## Ajax

### 概述

我们前端页面中的数据，如下图所示的表格中的员工信息，应该来自于后台，那么我们的后台和前端是互不影响的2个程序，那么我们前端应该如何从后台获取数据呢？因为是2个程序，所以必须涉及到2个程序的交互，所以这就需要用到我们接下来学习的Ajax技术。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YmQyOTA5Njg1MDFjOWM3MzE3M2I0ZDc3YjcxOTIxN2ZfQndNUElQZHR4UVlNaXRWVzhNOUpvcVJlQ2RRanBnZWhfVG9rZW46SmxoVGJINDRXb1ViRGp4SVYyeGMzNjVrbk5nXzE3OTA4NjU3Mzk6MTc5MDg2OTMzOV9WNA&add_watermark=true&scene_type=CCM)

**Ajax:** 全称Asynchronous JavaScript And XML，异步的JavaScript和XML。其作用有如下2点：

- 与服务器进行数据交换：通过Ajax可以给服务器发送请求，并获取服务器响应的数据。
- 异步交互：可以在**不重新加载整个页面**的情况下，与服务器交换数据并**更新部分网页**的技术，如：搜索联想、用户名是否可用的校验等等。

我们详细的解释一下Ajax技术的2个作用：

- 与服务器进行数据交互

如下图所示前端资源被浏览器解析，但是前端页面上缺少数据，前端可以通过Ajax技术，向后台服务器发起请求，后台服务器接受到前端的请求，从数据库中获取前端需要的资源，然后响应给前端，前端在通过我们学习的vue技术，可以将数据展示到页面上，这样用户就能看到完整的页面了。此处可以对比JavaSE中的网络编程技术来理解。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MTJjMWQxNDQ4YWU2OGY2ZWU4ODRjZDU5YzhmYTg0NjRfZ0lKVFlzWDVKR0E3TkZhVXlJUHhwYXBFQVFka1REUnFfVG9rZW46WWxRU2JucExJb3BnNk14MTQybWNpMVp4bkViXzE3OTA4NjU3Mzk6MTc5MDg2OTMzOV9WNA&add_watermark=true&scene_type=CCM)

- 异步交互：可以在**不重新加载整个页面**的情况下，与服务器交换数据并**更新部分网页**的技术。

如下图所示，当我们再百度搜索java时，下面的联想数据是通过Ajax请求从后台服务器得到的，在整个过程中，我们的Ajax请求不会导致整个百度页面的重新加载，并且只针对搜索栏这局部模块的数据进行了数据的更新，不会对整个页面的其他地方进行数据的更新，这样就大大提升了页面的加载速度，用户体验高。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NmNiNTI2MDcyZmU3Nzg4ZGNhNGRmNTE1NTExZmNlMzRfNmRPckt2QU9xZ3pPZ0ZydHRmaml4OWZ2d2lhT3dlajdfVG9rZW46SlZLZ2JhSHFyb3RXeWl4UXFCTmNHbHMzbjdiXzE3OTA4NjU3Mzk6MTc5MDg2OTMzOV9WNA&add_watermark=true&scene_type=CCM)

> XML：（英语：E**x**tensible **M**arkup **L**anguage）可扩展标记语言，本质是一种数据格式，可以用来存储复杂的数据结构。

### 同步异步

针对于上述Ajax的局部刷新功能是因为Ajax请求是异步的，与之对应的有同步请求。接下来我们介绍一下异步请求和同步请求的区别。

- **同步请求**发送过程如下图所示：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YTRhZmM1ZDhhMTZlZjBhYWU3Mjg5Y2E1MTNhYjUyMDdfajFTaDFnR3g3cTVtdEtkTGRKUEp6bTlyTzA2Wm1rNTJfVG9rZW46RTNxemI5Nlk3bzQ5SW14RTl0OGN2dlA5blhlXzE3OTA4NjU3Mzk6MTc5MDg2OTMzOV9WNA&add_watermark=true&scene_type=CCM)

浏览器页面在发送请求给服务器，在服务器处理请求的过程中，浏览器页面不能做其他的操作。只能等到服务器响应结束后才能，浏览器页面才能继续做其他的操 作。 

- **异步请求**发送过程如下图所示：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NTdlYmU3ODM0OGMxOTkwMGQyM2MwODcwMjk3YmI1ZjlfWjY5eGJpenJqeXFMTEtmVEZaUmhNOXhuRG1kM2dFQ0NfVG9rZW46V2J5M2JwZEhkb3VHRUJ4aEpHQmN3emV5bmtkXzE3OTA4NjU3Mzk6MTc5MDg2OTMzOV9WNA&add_watermark=true&scene_type=CCM)

 浏览器页面发送请求给服务器，在服务器处理请求的过程中，浏览器页面还可以做其他的操作。 

### Axios

使用原生的Ajax请求的代码编写起来还是比较繁琐的，所以接下来我们学习一门更加简单的发送Ajax请求的技术Axios 。Axios是对原生的AJAX进行封装，简化书写。Axios官网是：`https://www.axios-http.cn`

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=OGZlNjRjZDE5ZjhhNTNkNDEzYTY2NWQzOTliZTNiMjlfOTRubklJbUFjdllKOFFDTDFHazdkNEt1Yno0RzBjQ0JfVG9rZW46SmVtcWJZRDFLb1NCbWZ4bTlTMmNBUm1lbkVmXzE3OTA4NjU3Mzk6MTc5MDg2OTMzOV9WNA&add_watermark=true&scene_type=CCM)

#### 入门程序

Axios的使用比较简单，主要分为2步：

1). 引入Axios文件（如果网络不通畅，可以使用离线的已经下载好的js文件，资料中已经提供）

```HTML
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
```

2). 点击按钮时，使用Axios发送请求

```HTML
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Axios入门程序</title>
</head>
<body>

  <button id="getData">GET</button>
  <button id="postData">POST</button>
  
  <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
  <script>
    //GET请求
    document.querySelector('#getData').onclick = function() {
      axios({
        url:'https://mock.apifox.cn/m1/3083103-0-default/emps/list',
        method:'get'
      }).then(function(res) {
        console.log(res.data);
      }).catch(function(err) {
        console.log(err);
      })
    }
    
    //POST请求
    document.querySelector('#postData').onclick = function() {
      axios({
        url:'https://mock.apifox.cn/m1/3083103-0-default/emps/update',
        method:'post'
      }).then(function(res) {
        console.log(res.data);
      }).catch(function(err) {
        console.log(err);
      })
    }
  </script>
</body>
</html>
```

知识小贴士：在使用axios时，在axios之后，输入 thenc 会自动生成成功及失败回调函数结构 。

#### 请求方法别名

Axios还针对不同的请求，提供了别名方式的api，具体格式如下：

axios.请求方式(url [, data [, config]])

具体如下：

| 方法                               | 描述           |
| ---------------------------------- | -------------- |
| axios.get(url [, config])          | 发送get请求    |
| axios.delete(url [, config])       | 发送delete请求 |
| axios.post(url [, data[, config]]) | 发送post请求   |
| axios.put(url [, data[, config]])  | 发送put请求    |

我们目前只关注get和post请求，所以在上述的入门案例中，我们可以将get请求代码改写成如下：

```JavaScript
axios.get("https://mock.apifox.cn/m1/3083103-0-default/emps/list").then(result => {
    console.log(result.data);
})
```

post请求改写成如下：

```JavaScript
axios.post("https://mock.apifox.cn/m1/3083103-0-default/emps/update","id=1").then(result => {
    console.log(result.data);
})
```

### 案例-异步获取数据

需求：基于axios动态加载员工列表数据

具体代码实现如下：

```HTML
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tlias智能学习辅助系统</title>
    <style>
      body {
        margin: 0;
      }

      /* 顶栏样式 */
      .header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        background-color: #c2c0c0;
        padding: 20px 20px;
        box-shadow: 0 2px 5px rgba(0,0,0,0.1);
      }
      
      /* 加大加粗标题 */
      .header h1 {
        margin: 0;
        font-size: 24px;
        font-weight: bold;
      }

      /* 文本链接样式 */
      .header a {
        text-decoration: none;
        color: #333;
        font-size: 16px;
      }

      /* 搜索表单区域 */
      .search-form {
        display: flex;
        align-items: center;
        padding: 20px;
        background-color: #f9f9f9;
      }

      /* 表单控件样式 */
      .search-form input[type="text"], .search-form select {
        margin-right: 10px;
        padding: 10px 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
        width: 26%;
      }

      /* 按钮样式 */
      .search-form button {
        padding: 10px 15px;
        margin-left: 10px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
      }

      /* 清空按钮样式 */
      .search-form button.clear {
        background-color: #6c757d;
      }

      .table {
         min-width: 100%; 
         border-collapse: collapse;
      }

      /* 设置表格单元格边框 */
      .table td, .table th { 
        border: 1px solid #ddd; 
        padding: 8px; 
        text-align: center;
      }
      
      .avatar { 
        width: 30px; 
        height: 30px; 
        object-fit: cover; 
        border-radius: 50%; 
      }

      /* 页脚版权区域 */
    .footer {
        background-color: #c2c0c0;
        color: white;
        text-align: center;
        padding: 10px 0;
        margin-top: 30px;
    }

    .footer .company-name {
        font-size: 1.1em;
        font-weight: bold;
    }

    .footer .copyright {
        font-size: 0.9em;
    }

    #container {
      width: 80%;
      margin: 0 auto;
    }
    </style>
</head>
<body>
    
  <div id="container">
    <!-- 顶栏 -->
    <div class="header">
      <h1>Tlias智能学习辅助系统</h1>
      <a href="#">退出登录</a>
    </div>
    
    <!-- 搜索表单区域 -->
    <form class="search-form">
      <input type="text" name="name" placeholder="姓名" v-model="searchEmp.name" />
      <select name="gender" v-model="searchEmp.gender">
          <option value="">性别</option>
          <option value="1">男</option>
          <option value="2">女</option>
      </select>
      <select name="job" v-model="searchEmp.job">
          <option value="">职位</option>
          <option value="1">班主任</option>
          <option value="2">讲师</option>
          <option value="3">学工主管</option>
          <option value="4">教研主管</option>
          <option value="5">咨询师</option>
      </select>
      <button type="button" @click="search">查询</button>
      <button type="button" @click="clear">清空</button>
    </form>

    <table class="table table-striped table-bordered">
      <thead>
          <tr>
              <th>姓名</th>
              <th>性别</th>
              <th>头像</th>
              <th>职位</th>
              <th>入职日期</th>
              <th>最后操作时间</th>
              <th>操作</th>
          </tr>
      </thead>
      <tbody>
        <tr v-for="(emp, index) in empList" :key="index">
          <td>{{ emp.name }}</td>
          <td>{{ emp.gender === 1 ? '男' : '女' }}</td>
          <td><img :src="emp.image" alt="{{ emp.name }}" class="avatar"></td>
          <!-- <td>{{ emp.job === '1' ? '班主任' : (emp.job === '2' ? '讲师' : (emp.job === '3' ? '学工主管' : (emp.job === '4' ? '教研主管' : (emp.job === '5' ? '咨询师' : '未知')))) }}</td> -->
           <!-- 基于v-if/v-else-if/v-else指令来展示职位这一列 -->
          <td>
            <span v-if="emp.job === '1'">班主任</span>
            <span v-else-if="emp.job === '2'">讲师</span>
            <span v-else-if="emp.job === '3'">学工主管</span>
            <span v-else-if="emp.job === '4'">教研主管</span>
            <span v-else-if="emp.job === '5'">咨询师</span>
          </td>

          <!-- 基于v-show指令来展示职位这一列 -->
          <!-- <td>
            <span v-show="emp.job === '1'">班主任</span>
            <span v-show="emp.job === '2'">讲师</span>
            <span v-show="emp.job === '3'">学工主管</span>
            <span v-show="emp.job === '4'">教研主管</span>
            <span v-show="emp.job === '5'">咨询师</span>
         </td> -->

          <td>{{ emp.entrydate }}</td>
          <td>{{ emp.updatetime }}</td>
          <td class="btn-group">
            <button class="edit">编辑</button>
            <button class="delete">删除</button>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- 页脚版权区域 -->
    <footer class="footer">
      <p class="company-name">江苏传智播客教育科技股份有限公司</p>
      <p class="copyright">版权所有 Copyright 2006-2024 All Rights Reserved</p>
    </footer>

    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script type="module">
      import { createApp } from 'https://unpkg.com/vue@3/dist/vue.esm-browser.js'
      createApp({
        data() {
          return {
            searchEmp: {
              name: '',
              gender: '',
              job: ''
            },
            empList: []
          }
        },
        methods: {
          search() {
            //基于axios发送异步请求，请求https://web-server.itheima.net/emps/list，根据条件查询员工列表
            axios.get('https://web-server.itheima.net/emps/list', {
              params: this.searchEmp
            }).then(res => {
              this.empList = res.data.data
            })
          },
          clear() {
            this.searchEmp = {
              name: '',
              gender: '',
              job: ''
            }
          }
        }
      }).mount('#container')
    </script>

  </div>

</body>
</html>
```

如果使用axios中提供的.then(function(){....}).catch(function(){....})，这种回调函数的写法，会使得代码的可读性和维护性变差。 而为了解决这个问题，我们可以使用两个关键字，分别是：**async、await**。 

可以通过async、await可以让异步变为同步操作。async就是来声明一个异步方法，await是用来等待异步任务执行。

**代码修改前：**

```JavaScript
search() {
    //基于axios发送异步请求，请求https://web-server.itheima.net/emps/list，根据条件查询员工列表
    axios.get('https://web-server.itheima.net/emps/list', {
      params: this.searchEmp
    }).then(res => {
      this.empList = res.data.data
    })
  },
```

**代码修改后：**

```JavaScript
  async search() {
    //基于axios发送异步请求，请求https://web-server.itheima.net/emps/list，根据条件查询员工列表
    const result = await axios.get('https://web-server.itheima.net/emps/list', {params: this.searchEmp});
    this.empList = result.data.data;
  },
```

修改后，代码就变成同步操作了，一行一行的从前往后执行。 在前端项目开发中，经常使用这两个关键字配合，使得代码的可读性和可维护性变高。

## Vue生命周期

### 介绍

vue的生命周期：指的是vue对象从创建到销毁的过程。

vue的生命周期包含8个阶段：每触发一个生命周期事件，会自动执行一个生命周期方法，这些生命周期方法也被称为钩子方法。其完整的生命周期如下图所示：

| 状态          | 阶段周期 |
| ------------- | -------- |
| beforeCreate  | 创建前   |
| created       | 创建后   |
| beforeMount   | 挂载前   |
| mounted       | 挂载完成 |
| beforeUpdate  | 更新前   |
| updated       | 更新后   |
| beforeDestroy | 销毁前   |
| destroyed     | 销毁后   |

下图是 Vue 官网提供的从创建 Vue 到效果 Vue 对象的整个过程及各个阶段对应的钩子函数：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZmQ2MWI1MDkyNmJmNjkxMjUyYzQ1N2M3MDJhYjI1OGRfd3pFbHc0eVJUZGRBOUpqVWN5UEJQVjVMVDlRTmo1WURfVG9rZW46RHlKbGJlWjFUb1VKcFN4ZVBtQ2NyUWNVbkNnXzE3OTA4NjU3Mzk6MTc5MDg2OTMzOV9WNA&add_watermark=true&scene_type=CCM)

其中我们需要重点关注的是mounted，其他的我们了解即可。

mounted：挂载完成，Vue初始化成功，HTML页面渲染成功。**以后我们一般用于页面初始化自动的ajax请求后台数据**

### 案例完善

那我们要想在页面加载完毕，就查询出员工列表，就可以在mounted钩子函数中，发送异步请求查询员工数据了。 

具体代码如下：

```JavaScript
    methods: {
      async search() {
        //基于axios发送异步请求，请求https://web-server.itheima.net/emps/list，根据条件查询员工列表
        const result = await axios.get('https://web-server.itheima.net/emps/list', {params: this.searchEmp});
        this.empList = result.data.data;
      },
      clear() {
        this.searchEmp = {
          name: '',
          gender: '',
          job: ''
        }
        this.search();
      }
    },
    mounted() {
      this.search();
    }
  }).mount('#container')
```

到此，员工列表查询的功能我们就已经完成了。 那关于Vue的其他高级用法，我们将在后面的前端web实战中来详细讲解。

## 附录

- **Ajax练习的url地址：**
  - Ajax演示：
    - GET请求:  https://mock.apifox.cn/m1/3083103-0-default/emps/list
    - POST请求: https://mock.apifox.cn/m1/3083103-0-default/emps/update
  - Ajax员工列表案例地址： 
    - https://web-server.itheima.net/emps/list
    - https://web-server.itheima.net/emps/list?name=xxx&gender=xxx&job=xxx

- **Chrome浏览器插件地址：**https://chrome.zzzmh.cn/#/index

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZjlhYTQ0ZTdmMGE5NWFhYWU3OGZkMDBjN2QwZjM1M2FfdGNBcHYyZ0pid3ZZNGQ4TDN4M2ZaZU5ZOERzeWFNSFpfVG9rZW46TndUNGJ1ckhsb3JNenF4QWd0ZmM2SWZnbllmXzE3OTA4NjU3Mzk6MTc5MDg2OTMzOV9WNA&add_watermark=true&scene_type=CCM)