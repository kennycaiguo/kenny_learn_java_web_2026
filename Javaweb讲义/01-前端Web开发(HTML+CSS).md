## 初识Web前端

### 介绍

我们介绍Web网站工作流程的时候提到，前端开发，主要的职责就是将数据以好看的样式呈现出来。说白了，就是开发网页程序，如下图所示：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MWE5MjIyMDc4YjhjMzg1MDczZmFjNDU1ZTE2NjZhZTBfcXBMS3Zhaldxb0hPcXZjVDRWOVFpYm53M0VONzdiMDJfVG9rZW46SUtxUWJDTXRlbzR1UnJ4NXFUUWNXU0NUbmZlXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

#### **主要明确以下三个问题：**

1). 网页由哪些部分组成 ?

- 文字、图片、音频、视频、超链接、表格等等。

2). 我们看到的网页，背后的本质是什么 ?

- 前端程序员写的前端代码 (备注：在前后端分离的开发模式中)

3). 前端的代码是如何转换成用户眼中的网页的 ?

- 通过浏览器转化（解析和渲染）成用户看到的网页
- 浏览器中对代码进行解析和渲染的部分，称为 **浏览器内核**

而市面上的浏览器非常多，比如：IE、火狐Firefox、苹果safari、欧朋、谷歌Chrome、QQ浏览器、360浏览器等等。 而且我们电脑上安装的浏览器可能都不止一个，有很多。 

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YTE1MThhMzRjODA0OGM0NTM3Yjg5ZDRhNmM3YzI1NjlfUDVSNUZpcERIY2o5RTFsbTluamV4ZDUwcGxLYnRRSWFfVG9rZW46RkM3bmJQaWVtb0Npcmd4VDAyMGN4OUgzbkdoXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

但是呢，需要大家注意的是，不同的浏览器，内核不同，对于相同的前端代码解析的效果也会存在差异。 那这就会造成一个问题，同一段前端程序，不同浏览器展示出来的效果是不一样的，这个用户体验就很差了。而我们想达到的效果则是，即使用户使用的是不同的浏览器，解析同一段前端代码，最终展示出来的效果都是相同的。

要想达成这样一个目标，我们就需要定义一个统一的标准，然后让各大浏览器厂商都参照这个标准来实现即可。 而这套标准呢，其实早都已经定义好了，那就是我们接下来，要介绍的**web标准**。

 ### Web标准

**Web标准**也称为**网页标准**，由一系列的标准组成，大部分由W3C（ World Wide Web Consortium，万维网联盟）负责制定。由三个组成部分：

- HTML：负责网页的结构（页面元素和内容）。
- CSS：负责网页的表现（页面元素的外观、位置等页面样式，如：颜色、大小等）。
- JavaScript：负责网页的行为（交互效果）。

那在我们的前端课程中，除了会讲解HTML、CSS、JS这些技术以外，还会讲解现在前端开发中的高级技术Vue、ElementPlus、Axios。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZmUyNDZhNWIwMjcyMDVkNzFkYTkyMTY3NWZkYmM0ZmFfRkV1ZUJDT0xXUjd1bWUyWUNoa01QN2pYakhJQ0ZZM0ZfVG9rZW46RUFYaWI1b0pwb0FyeU94ZlJWWGNycVdHbk1lXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

 而Web前端开发的内容呢，我们在设计的时候，也进行了分层的设计，分为了两个部分：

- Web前端基础：HTML、CSS、JS、Vue3、Ajax、Axios，前端基础部分为两天时间。
- Web前端进阶：Vue工程化、ElementPlus、Tlias智能学习辅助系统案例，前端进阶部分为三天时间。

那今天我们就先来讲解Web前端基础的第一部分： HTML & CSS。

什么是HTML？

**HTML**: **H**yper**T**ext **M**arkup **L**anguage，超文本标记语言。

- 超文本：超越了文本的限制，比普通文本更强大。除了文字信息，还可以定义图片、音频、视频等内容。
- 标记语言：由标签 "<标签名>" 构成的语言
  - HTML标签都是预定义好的 。例如：使用 <h1> 标签展示标题，使用<a>展示超链接，使用<img>展示图片，<video>展示视频。
  - HTML代码直接在浏览器中运行，HTML标签由浏览器解析 。

- 下面展示的是一段html代码经过浏览器解析，呈现的效果如右图所示： 

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MzRlY2MzMDQwODNhNDZlYjk1NDNiYmI5NDQ5MDQ2ZGJfV2h1MHp6dnVQSHZkT2s0WW5wOUJWY1hpbjQ3SjVFSHhfVG9rZW46UUlDYWJETzBkb1ptbUR4eHEyN2NBSWx1bndmXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

什么是CSS？

- **CSS:** Cascading Style Sheet，层叠样式表，用于控制页面的样式（表现）。

下面展示的是一段 html代码 及 CSS样式 经过浏览器解析，呈现的效果如右图所示：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZDYwNjA2MmNmYTQ4Y2JmMjUxMDY5YTBlYzU2YjllZWRfZE1JVXJrT1BmcFd5alo3bmxlZUhNNHdEcEtuREdlUVRfVG9rZW46VmRjc2JTZGpVb3FNWVZ4QWhJQmN4UEFYbm1mXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

## HTML快速入门

 ### 操作步骤

1. **新建文本文件，后缀名改为 .html，命名为：01. html快速入门.html**

创建一个名为HTML的文件夹，然后找到课程资料中的 1.png 文件放到该目录下的img目录中。此时HTML文件夹中内容如下：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=Mzc3MjgwZTZkMzUyZGM0ZmNlYTFjMGMwYjc2NjM2ZGRfS2NEbU1POHB2OGhiV0pseTd1RkdmZ1lYcXdiY0lwbmtfVG9rZW46TTRibWJ5UXYyb2liNDZ4WENDNGM2SnM5bkNlXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

 

2. **写HTML的基本骨架，定义标题**

选中文件，鼠标右击，选择使用记事本打开文件，并且编写网页的标题。

首先html有固定的基本结构

```HTML
<html>
     <head>
          <title>HTML 快速入门</title>
     </head>
     <body>
                
     </body>
</html>
```

其中`<html>`是根标签，`<head>`和`<body>`是子标签。

- `<head>` : 定义网页的头部，用来存放给浏览器看的信息，如：CSS样式、网页的标题。 
- `<body>` : 定义网页的主体部分，存放给用户看的信息，也是网页的主体内容，如：文字、图片、视频、音频、表格等。

3. **在<body>中编写HTML的核心内容**

```HTML
<html>
     <head>
        <title>HTML 快速入门</title>
     </head>
     <body>
        <h1>Hello HTML</h1>
        <img src="img/1.png">
     </body>
</html>
```

其中 `<h1>` 标签是一个一级标题的标签。 `<img>` 标签是一个图片标签，用来展示图片，而其中的 src 属性是用来指定要展示的图片。

4. **然后选中文件，鼠标右击，选择使用浏览器打开文件**

浏览器呈现效果如下:

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NGI1NjBhNzE1ODFmYjk4NWFkMTg3ODI1NGVhMjlkYjBfOE01ZXdwUThuMnFTdEFEaDlPSjBDbzNqeFdQVEZWcFlfVG9rZW46RUlWZ2JoVTVIb3FSOE14Y1VuQ2MyRnY2bmNiXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

###  总结

**1). HTML页面的基础结构标签**

```HTML
<html>
    <head>
        <title> </title>
    </head>
    <body>
       
    </body>
</html>
```

`<title>`中定义标题显示在浏览器的标题位置，`<body>`中定义的内容会呈现在浏览器的内容区域

**2). HTML中的标签特点**

- HTML标签不区分大小写，建议小写
- HTML标签的属性值，采用单引号、双引号都可以，一般写双引号
- HTML语法相对比较松散 (建议大家编写HTML标签的时候尽量严谨一些)

## 前端开发工具

我们通过快速入门案例，发现由记事本文件开发html是非常不方便的，所以接下来我们需要学习一款前端专业的开发工具VS Code。

Visual Studio Code（简称 VS Code ）是 Microsoft 于2015年4月发布的一款代码编辑器。VS Code 对前端代码有非常强大的支持，同时也其他编程语言（例如：C++、Java、Python、PHP、Go等）。VS Code 提供了非常强大的插件库，大大提高了开发效率。

- **官网：** [https://code.visualstudio.com](https://code.visualstudio.com/)
- **安装：****[VsCode安装文档](https://heuqqdmbyk.feishu.cn/wiki/P8Cbw64UAi3h92kqnUpcfF4cnNg)**
- **注意：**
  - 需要注意的是，我们作为一名开发者，不应该将软件软装在包含中文名的路径中 。
  - 由于安装了IDEA快捷键的插件，VSCode快键键与IDEA是一致的。

 ## 常见标签&样式

那我们在讲解HTML的常见基础标签 及 CSS的基本样式时，我们就以 新浪新闻页面 为例，来进行讲解，这样大家不仅能够知道 常见标签及样式的作用，还能够知道具体的应用场景。

新浪新闻的具体页面效果如下：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YjIxY2JmZjk5ZjJiZmJiMmRmMmFiNThjMjVjNWEzOGRfVTFUOTdISWtyVEI4UGJ6eGFmYW5oam51RTRmQTlFcmFfVG9rZW46RVdoSWJkZ3VHb3RGeVF4UWRJU2NqZGR0bnhoXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

原始页面网址：https://news.cctv.com/2024/05/15/ARTIflTnFvNMx9nUVc4PA7T2240515.shtml

而大家可以看到，上述新闻网页，其实分为两个部分，一个是新闻的标题部分，另一个是新闻的正文部分。那接下来，我们就先来完成央视新闻标题部分的制作。

### 央视新闻-标题

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MDYwMjMyYjYzMGEzZDQzNjdmNzk0MjdkZDg1OGExYzBfZHY1VHBaeU11THJpMDd5am1hSE84VmREVGJ0bFlsVldfVG9rZW46TGRnaWJSdnlsb3BZYUx4cFZMemM3d2tZbnhkXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

前面我们提到，我们在浏览器中看到的网页程序呈现出来的效果，实际上是浏览器解析并渲染了前端代码而呈现出来的。 而我们所编写的HTML页面，在浏览器中渲染的时候，是从上往下逐行解析展示的。 所以，我们在编写html页面的时候，要根据页面的布局，从上往下编写。

#### 标题排版

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NmM2Mjk5N2I2Mzg5YmVhYzM5ZDJlNjg4YzYyZTVlYzBfMlVGU2h1SXM4Q0EzTTlFMDc5ZUNlVXM5VTRoZjdSR3lfVG9rZW46UEVoUGI3aVBhbzhUNHV4eFpGeGM0dlhYbnVjXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

在制作网页的时候，我们可以充分的利用AI辅助工具**通义灵码**，来帮我们实现对应的功能，我们只需要在编辑器中给定对应的注释（提示词），通义灵码就可以帮我们实现对应的功能效果，然后我们再基于生成的代码进行调试即可。

接下来，我们就可以通过VsCode打开HTML文件夹，然后在其中创建一个html页面，命名为：`02. 央视新闻-标题排版.html`。

然后在这个文件中来制作新浪新闻网页，标题部分的排版内容为：

```HTML
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>【新思想引领新征程】推进长江十年禁渔 谱写长江大保护新篇章</title>
</head>
<body>

  <!-- 定义网页标题, 标题内容： 【新思想引领新征程】推进长江十年禁渔 谱写长江大保护新篇章 -->
  <h1 id="title">【新思想引领新征程】推进长江十年禁渔 谱写长江大保护新篇章</h1>
  
  <!-- 定义一个超链接, 链接地址：https://news.cctv.com/, 链接内容：央视网 -->
  <a href="https://news.cctv.com/" target="_blank">央视网</a>
  2024年05月15日 20:07
  
</body>
</html>
```

那在上述我们用到的两个标签，一个是标题标签 `<h1></h1>`，另一个是超链接标题 `<a></a>`。这两个标签的具体用法如下：

标题标签 h 系列：

<h1> 11111111111111 </h1>

<h2> 11111111111111 </h2>

<h3> 11111111111111 </h3>

<h4> 11111111111111 </h4>

<h5> 11111111111111 </h5>

<h6> 11111111111111 </h6>

**效果：**h1为一级标题，字体也是最大的 ； h6为六级标题，字体是最小的。

**注意：**HTML标签是预定义好的，不能随意定义，也就以为着，标题标签就只有这六个，没有 <h7>

超链接 a 标签：

标签：<a href="....." target=".....">央视网</a>

属性：

- href: 指定资源访问的url
- target: 指定在何处打开资源链接
  - _self: 默认值，在当前页面打开
  - _blank: 在空白页面打开

#### 标题样式

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YTBlOWQ2MzFhYmIyMDZkMjcwZWFiMTMwN2E2Y2FiODlfbHhNbnVFRUNBck9jR3JvUlB4VkptOUdBV1JVcWxpTnFfVG9rZW46V3NieWJxM0xOb0FzcTB4dHhmeWNSdmxvbmloXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

我们可以看到，目前我们制作的新闻标题部分，新闻发布时间 `2024年05月15日 20:07` 的字体颜色是**黑色**，而在原始的央视新闻页面中，字体的颜色呈现**灰色**，具体的呈现效果如下：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=OGRlNjE2YjQ1YzRjZWE4ZGYzZmVmMzNjZWU0NmI1N2ZfZWVGamQ4cFBYWGJJVUJNYU9jSVZMZHdCQ0pPRWluaWlfVG9rZW46TXc3eWJlV3JCbzlIa2p4U1ZOY2NrNUxQbmZlXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

那接下来，我们要来控制字体的颜色，而这部分其实是属于网页的样式，所以这里呢，就要**通过CSS样式控制**。

这些基础的功能我们就可以直接通过AI工具帮我们实现，参考如下**提示词(prompt)：**

你是一名前端开发工程师，请通过css为网页中的新闻发布时间设置字体颜色为灰色。 网页内容如下：

<!DOCTYPE html>

<html lang="en">

<head>

  <meta charset="UTF-8">

  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>【新思想引领新征程】推进长江十年禁渔 谱写长江大保护新篇章</title>

</head>

<body>

   <!-- 定义网页标题, 标题内容： 【新思想引领新征程】推进长江十年禁渔 谱写长江大保护新篇章 -->

   <h1 id="title">【新思想引领新征程】推进长江十年禁渔 谱写长江大保护新篇章</h1>

   <!-- 定义一个超链接, 链接地址：https://news.cctv.com/, 链接内容：央视网 -->

​    <a href="https://news.cctv.com/" target="_blank">央视网</a>

   2024年05月15日 20:07

</body>

</html>

AI给出的最终代码实现如下：

```HTML
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>【新思想引领新征程】推进长江十年禁渔 谱写长江大保护新篇章</title>
  <style>
    .publish-date {
      color: gray;
    }
  </style>
</head>
<body>

  <!-- 定义网页标题, 标题内容： 【新思想引领新征程】推进长江十年禁渔 谱写长江大保护新篇章 -->
  <h1 id="title">【新思想引领新征程】推进长江十年禁渔 谱写长江大保护新篇章</h1>
  
  <!-- 定义一个超链接, 链接地址：https://news.cctv.com/, 链接内容：央视网 -->
  <a href="https://news.cctv.com/" target="_blank">央视网</a>
  <span class="publish-date">2024年05月15日 20:07</span>

</body>
</html>
```

我们可以看到，通过上面这样一段代码，就可以控制字体的颜色，而其实上面这一段代码就是AI生成的CSS样式。最终效果如下：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NTQzNTdiOGZkZWViM2E3ODM3NzMxZDUzZTRhMjkzOTdfTXFKaWg2VE1mdFZQNUZxQkRUNjQxQWtRVUJnbHdRY3lfVG9rZW46RnNxdGI4VmZWb1RWWHB4YUF3aGNOVUJxbjBkXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

##### CSS引入方式

那在HTML的文件中，我们如何来编写CSS样式呢，此时就涉及到CSS的三种引入方式。

具体有3种引入方式，语法如下表格所示：

| 名称     | 语法描述                                       | 示例                                        |
| -------- | ---------------------------------------------- | ------------------------------------------- |
| 行内样式 | 在标签内使用style属性，属性值是css属性键值对。 | <h1 style="xxx:xxx;">中国新闻网</h1>        |
| 内部样式 | 定义<style>标签，在标签内部定义css样式。       | <style> h1 {...} </style>                   |
| 外部样式 | 定义<link>标签，通过href属性引入外部css文件    | <link rel="stylesheet" href="css/news.css"> |

对于上述3种引入方式，企业开发的使用情况如下：

- **行内样式：**会出现大量的代码冗余，不方便后期的维护，所以不常用（常配合JS使用）。
- **内部样式：**通过定义css选择器，让样式作用于当前页面的指定的标签上。（可以写在页面任何位置，但通常约定写在head标签中）
- **外部样式：**html和css实现了完全的分离，企业开发常用方式。

1. ##### 颜色表示方式

在前端程序开发中，颜色的表示方式常见的有如下三种：

| 表示方式       | 属性值           | 说明                                 | 取值                                        |
| -------------- | ---------------- | ------------------------------------ | ------------------------------------------- |
| 关键字         | 颜色英文单词     | red、green、blue                     | red、green、blue...                         |
| rgb表示法      | rgb(r, g, b)     | 红绿蓝三原色，每项取值范围：0-255    | rgb(0,0,0)、rgb(255,255,255)、rgb(255,0,0)  |
| rgba表示法     | rgba(r, g, b, a) | 红绿蓝三原色，a表示透明度，取值：0-1 | rgb(0,0,0,0.3)、rgb(255,255,255,0.5)        |
| 十六进制表示法 | #rrggbb          | #开头，将数字转换成十六进制表示      | #000000、#ff0000、#cccccc，简写：#000、#ccc |

1. ##### 设置字体颜色

```HTML
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>【新思想引领新征程】推进长江十年禁渔 谱写长江大保护新篇章</title>
  <!-- 2. 内部样式 -->
  <style>
    .publish-date {
      color: #b2b2b2;
    }
  </style>
  <!-- 3. 外部样式 -->
  <!-- <link rel="stylesheet" href="css/news.css"> -->
</head>
<body>

  <!-- 定义网页标题, 标题内容： 【新思想引领新征程】推进长江十年禁渔 谱写长江大保护新篇章 -->
  <h1 id="title">【新思想引领新征程】推进长江十年禁渔 谱写长江大保护新篇章</h1>
  
  <!-- 定义一个超链接, 链接地址：https://news.cctv.com/, 链接内容：央视网 -->
  <a href="https://news.cctv.com/" target="_blank">央视网</a>

  <!-- 1. 行内样式 -->
  <!-- <span style="color: #b2b2b2;">2024年05月15日 20:07</span> -->

  <span class="publish-date">2024年05月15日 20:07</span>

</body>
</html>
```

备注: 要想拾取某一个网页中的颜色，我们可以借助于截图软件的拾色器插件来完成。【截图软件在资料中已经提供】

1. ##### CSS选择器

顾名思义：选择器是选取需设置样式的元素（标签），但是我们根据业务场景不同，选择的标签的需求也是多种多样的，所以选择器有很多种。

**选择器通用语法如下**：

```CSS
选择器名   {
    css样式名：css样式值;
    css样式名：css样式值;
}
```

而我们是做后台开发的，所以对于css选择器，我们只学习常见的这几种：

| 选择器                      | 写法                     | 示例                                  | 示例说明                           |
| --------------------------- | ------------------------ | ------------------------------------- | ---------------------------------- |
| 元素选择器                  | 元素名称 {...}           | h1 {...}                              | 选择页面上所有的<h1>标签           |
| 类选择器                    | .class属性值 {...}       | .cls {...}                            | 选择页面上所有class属性为cls的标签 |
| id选择器                    | #id属性值 {...}          | #hid {...}                            | 选择页面上id属性为hid的标签        |
| 分组选择器                  | 选择器1,选择器2{...}     | h1,h2 {...}                           | 选择页面上所有的<h1>和<h2>标签     |
| 属性选择器                  | 元素名称[属性] {...}     | input[type] {...}                     | 选择页面上有type属性的<input>标签  |
| 元素名称[属性名="值"] {...} | input[type="text"] {...} | 选择页面上type属性为text的<input>标签 |                                    |
| 后代选择器                  | 元素1元素2{...}          | form input {...}                      | 选择<form>标签内的所有<input>标签  |

那接下来，我们需要将页面上所有的超链接中，默认的下划线效果去除掉。具体的代码实现如下：

```HTML
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>【新思想引领新征程】推进长江十年禁渔 谱写长江大保护新篇章</title>
  <!-- 2. 内部样式 -->
  <style>
    .publish-date {
      color: #b2b2b2;
    }
    
    /* 设置超链接取消下划线效果 */
    a {
      text-decoration: none;
    }
  </style>
  <!-- 3. 外部样式 -->
  <!-- <link rel="stylesheet" href="css/news.css"> -->
</head>
<body>

  <!-- 定义网页标题, 标题内容： 【新思想引领新征程】推进长江十年禁渔 谱写长江大保护新篇章 -->
  <h1 id="title">【新思想引领新征程】推进长江十年禁渔 谱写长江大保护新篇章</h1>
  
  <!-- 定义一个超链接, 链接地址：https://news.cctv.com/, 链接内容：央视网 -->
  <a href="https://news.cctv.com/" target="_blank">央视网</a>

  <!-- 1. 行内样式 -->
  <!-- <span style="color: #b2b2b2;">2024年05月15日 20:07</span> -->

  <span class="publish-date">2024年05月15日 20:07</span>

</body>
</html>
```

那到目前为止，标题部分的基本排版和样式，我们就已经制作完成了，具体页面效果：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MzI3ODZkYzQyYTJlYWYxNzQ2YWY3MjlhZDg2OWU1YzFfc2tiRGpYMHlyNkRrbktGeWdWRzNER2FUMWJmd1pHeVVfVG9rZW46Sk5uQmJtZlhjb1Rsa094Q3AxeWM5RFA4bjdmXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

1. ### 央视新闻-正文

在原始的央视新闻页面中，我们可以看到有视频，有文字，有图片，内容还是非常丰富的。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NzFmNzkyYjM5NjdhNDY1YmFkM2JkYzkxM2YyMWFmMDZfUXQwV0Vmc0laSFVSeHBVdlNidjM2ZFRrZFlodWs1TG1fVG9rZW46TThWTmIwaFFUbzV3VnZ4NzhVZmNYMTE5blRiXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

1. #### 正文排版

1. ##### 基本实现

浏览器在解析渲染页面的时候，是从上往下解析渲染的，那接下来，我们就可以从上往下来布局这个页面，那这个过程中，我们就可以直接基于AI来辅助我们实现。

正文排版之后，页面的代码如下：

```HTML
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>【新思想引领新征程】推进长江十年禁渔 谱写长江大保护新篇章</title>
  <!-- 2. 内部样式 -->
  <style>
    .publish-date {
      color: #b2b2b2;
    }
    
    /* 设置超链接取消下划线效果 */
    a {
      text-decoration: none;
    }
  </style>
</head>
<body>
      
    <!-- 定义网页标题, 标题内容： 【新思想引领新征程】推进长江十年禁渔 谱写长江大保护新篇章 -->
    <h1 id="title">【新思想引领新征程】推进长江十年禁渔 谱写长江大保护新篇章</h1>
    
    <!-- 定义一个超链接, 链接地址：https://news.cctv.com/, 链接内容：央视网 -->
    <a href="https://news.cctv.com/" target="_blank">央视网</a>

    <span class="publish-date">2024年05月15日 20:07</span>
    <br>
    <br>

    <!-- 定义一个视频, video/news.mp4 -->
    <video src="video/news.mp4" controls  width="80%"></video>
    <p>
      央视网消息（新闻联播）：作为共抓长江大保护的标志性工程，长江十年禁渔今年进入第四年。总书记指出，长江禁渔是为全局计、为子孙谋的重要决策。牢记总书记嘱托，沿江省市持续推进长江水生生物多样性恢复，努力保障退捕渔民就业生活。这段时间，记者深入长江两岸，记录下禁渔工作取得的重要阶段性成效和广大干部群众坚定不移推进长江十年禁渔的扎实行动。
    </p>
    <p>
      行走在长江沿线，科研人员发现很多可喜现象。
    </p>
    <!-- 定义一张图片, img/1.gif -->
    <img src="img/1.gif" alt=""  width="100%">
    <p>
      在长江南源，一处小头裸裂尻鱼新的栖息地被发现，鱼的数量大约超3万尾，为水生态保护提供了珍贵数据。
    </p>
    <p>
      在长江中游，追踪显示，人工增殖放流的中华鲟成功入海率已经从45%左右提升至60%以上；鄱阳湖鱼类小型化、低龄化趋势得到遏制，栖息地生存环境得以改善。
    </p>
    <p>
      在长江下游，今年3月起，南京秦淮河入江口首次出现野生中华绒螯蟹大规模洄游现象，种群数量明显增加。
    </p>
    <img src="img/2.jpg" width="100%">
    <p>
      水生生物资源恢复向好，见证了长江十年禁渔三年多来的阶段性成果。
    </p>
    <p>
      实施长江十年禁渔，是以同志为核心的党中央从中华民族长远利益出发作出的重要决策。党的十八大以来，总书记多次深入长江沿线考察调研，详细了解长江十年禁渔的实施情况，他指出，要坚定推进长江十年禁渔，巩固好已经取得的成果。
    </p>
    <img src="img/3.jpg" width="100%">
    <p>
      按照部署，自2021年1月1日起，在长江干流、大型通江湖泊、重要支流和长江口部分海域实行为期十年的禁渔，常年禁止天然渔业资源的生产性捕捞。禁渔三年多来，相关评估显示，长江干流和鄱阳湖、洞庭湖水生生物完整性指数由禁渔前最差的“无鱼”提升了两个等级。2022年，长江江豚数量达到1249头，实现历史性止跌回升。长江干流水质连续4年全线保持Ⅱ类。
    </p>
    <p>
      实施长江十年禁渔，解决好渔民上岸后的生产生活问题，禁渔才有稳定扎实的社会基础。
    </p>
    <img src="img/4.jpg" width="100%">
    <p>
      安徽退捕转产的3万多名渔民，在政府的引导下接受就业培训。在当涂县，免费学习养殖技术，养殖生态螃蟹成了退捕渔民的新选择。
    </p>
    <p>
      在拥有洞庭湖超六成水域的湖南岳阳，政府帮扶上岸渔民建起养殖场，发展风干鱼产业，还带领他们学习直播带货，拓宽销路。
    </p>
    <p>
      在渔民退捕上岸的鄱阳湖棠荫岛，当地在继续保护好生态的前提下，正探索规划利用独特的自然资源发展旅游产业。禁渔三年多来，有关部门对23.1万退捕渔民逐一建档立卡，多渠道提升就业、社保水平。
    </p>
    <img src="img/5.jpg" width="100%">
    <p>
      长江十年禁渔实施以来，沿江省市合力攻坚、久久为功，长江大保护不断向纵深推进，持续巩固禁渔成果。下一步，沿江省市还将加强水生生物重要栖息地修复，建立退捕渔民动态精准帮扶服务，完善跨区域、跨部门执法合作机制，确保一江清水绵延后世、惠泽人民。
    </p>
    <img src="img/6.jpg">
  
</body>
</html>
```

**注意：在使用类似于通义零码这类的AI辅助工具时，如果存在一些敏感数据，比如：政治、宗教、信仰等。**

##### 常见标签

那在上述的正文排版中，用到了如下标签，接下来详解介绍一下：

| 标签                                                         | 作用     | 属性/说明                               |
| ------------------------------------------------------------ | -------- | --------------------------------------- |
| <video>                                                      | 视频标签 | src：指定视频的url（绝对路径/相对路径） |
| controls：是否显示播放控件                                   |          |                                         |
| width：宽度（像素/相对于父元素百分比）；备注: 一般width 和 height 我们只会指定一个，另外一个会自动的等比例缩放。 |          |                                         |
| height：高度（像素/相对于父元素百分比）；备注: 一般width 和 height 我们只会指定一个，另外一个会自动的等比例缩放。 |          |                                         |
| <img>                                                        | 图片标签 | src, width，height                      |
| <p>                                                          | 段落标签 |                                         |
| <br>                                                         | 换行标签 |                                         |
| <b> / <strong>                                               | 加粗     | <strong> 具有强调语义                   |
| <u> / <ins>                                                  | 下划线   | <ins> 具有强调语义                      |
| <i> / <em>                                                   | 倾斜     | <em> 具有强调语义                       |
| <s> / <del>                                                  | 删除线   | <del> 具有强调语义                      |

在HTML页面中，我们在代码中录入空格、<、> 这些符号的时候，是没有对应的效果的，因为浏览器并不能准确的识别，此时，我们就需要通过字符实体来表示空格，<, > 。常见符号的字符实体如下：

| 字符实体 | 属性/说明 |
| -------- | --------- |
| &nbsp;   | 空格      |
| &lt;     | <         |
| &gt;     | >         |

1. ##### 路径表示

在引入图片、视频、音频、css等内容时，我们需要指定文件的路径，而在前端开发中，路径的书写形式分为两类：

- 绝对路径:
  - 绝对磁盘路径: `<img src="C:\Users\Administrator\Desktop\HTML\img\logo.png">`
  - 绝对网络路径: `<img src="``https://i2.sinaimg.cn/dy/deco/2012/0613/yocc20120613img01/news_logo.png``">`
- 相对路径:

​        ./ : 当前目录 , ./ 可以省略的

​        ../: 上一级目录

1. #### 正文样式

正文的基本排版有了之后，接下来，我们要处理的是正文部分的样式。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NTU4MGQ3MTcyZGZkY2U1N2UyYjBjNTg2ZWU1YmE1YjhfV1p1bzhyemtJcVgyNWdwbVIxcWFDcVZDRE5qTFFLNERfVG9rZW46TGVjcGJoODlXb0F2ZGt4blVFZ2NOR29Xbm1oXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

具体的css样式如下:

```CSS
    /* 设置段落首行缩进 */
    p {
      text-indent: 2em; /* 首行缩进2em */
      line-height: 2; /* 行高2倍 */
    }
```

1. ### 央视新闻-布局

1. #### 功能实现

完成了标签及正文部分的排版制作，以及样式处理之后，那最后一步，我们就要来完成页面整体布局的设置了。 从原始的央视新闻页面中，我们可以看到，新闻页面是出于整个版面的正中心的，那这种呢，在布局中也称为 **版心居中**。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YzA4OTY2ZjUyMDAwNjI2ODIyYzhhOWEwMTBhNWM0NGNfZTdnZ2EzTkVRUGI2SXJURWRwa1VHa3pVY09xNlJ6enJfVG9rZW46R0tqaGJxeGJTb1pJVGx4eWpKamN1Szg3bkRiXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

这个功能，我们依然可以通过AI，轻而易举的实现。具体的提示词如下：

通过css使如下新闻网页的整体内容，占用整个页面宽度的70%，并且横向居中展示。具体的网页内容如下：.....

最终网页代码如下所示：

```HTML
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>【新思想引领新征程】推进长江十年禁渔 谱写长江大保护新篇章</title>
  <style>
    .publish-date {
      color: #b2b2b2;
    }
    
    /* 设置超链接取消下划线效果 */
    a {
      text-decoration: none;
    }

    /* 设置段落首行缩进 */
    p {
      text-indent: 2em; /* 首行缩进2em */
      line-height: 2; /* 行高2倍 */
    }

    /* 新增样式 */
    .news-content {
      width: 70%; /* 宽度占70% */
      margin: 0 auto; /* 横向居中 */
    }
  </style>
</head>
<body>
    <!-- 包裹新闻内容的容器 -->
    <div class="news-content">
      <!-- 定义网页标题, 标题内容： 【新思想引领新征程】推进长江十年禁渔 谱写长江大保护新篇章 -->
      <h1 id="title">【新思想引领新征程】推进长江十年禁渔 谱写长江大保护新篇章</h1>
      
      <!-- 定义一个超链接, 链接地址：https://news.cctv.com/, 链接内容：央视网 -->
      <a href="https://news.cctv.com/" target="_blank" >央视网</a>

      <span class="publish-date">2024年05月15日 20:07</span>
      <br>
      <br>

      <!-- 定义一个视频, video/news.mp4 -->
      <video src="video/news.mp4" controls width="100%"></video>
      <p>
        <strong>央视网消息</strong>（新闻联播）：作为共抓长江大保护的标志性工程，长江十年禁渔今年进入第四年。总书记指出，长江禁渔是为全局计、为子孙谋的重要决策。牢记总书记嘱托，沿江省市持续推进长江水生生物多样性恢复，努力保障退捕渔民就业生活。这段时间，记者深入长江两岸，记录下禁渔工作取得的重要阶段性成效和广大干部群众坚定不移推进长江十年禁渔的扎实行动。
      </p>
      <p>
        行走在长江沿线，科研人员发现很多可喜现象。
      </p>
      <!-- 定义一张图片, img/1.gif -->
      <img src="img/1.gif" alt=""  width="100%">
      <p>
        在长江南源，一处小头裸裂尻鱼新的栖息地被发现，鱼的数量大约超3万尾，为水生态保护提供了珍贵数据。
      </p>
      <p>
        在长江中游，追踪显示，人工增殖放流的中华鲟成功入海率已经从45%左右提升至60%以上；鄱阳湖鱼类小型化、低龄化趋势得到遏制，栖息地生存环境得以改善。
      </p>
      <p>
        在长江下游，今年3月起，南京秦淮河入江口首次出现野生中华绒螯蟹大规模洄游现象，种群数量明显增加。
      </p>
      <img src="img/2.jpg"  width="100%">
      <p>
        水生生物资源恢复向好，见证了长江十年禁渔三年多来的阶段性成果。
      </p>
      <p>
        实施长江十年禁渔，是以同志为核心的党中央从中华民族长远利益出发作出的重要决策。党的十八大以来，总书记多次深入长江沿线考察调研，详细了解长江十年禁渔的实施情况，他指出，要坚定推进长江十年禁渔，巩固好已经取得的成果。
      </p>
      <img src="img/3.jpg"  width="100%">
      <p>
        按照部署，自2021年1月1日起，在长江干流、大型通江湖泊、重要支流和长江口部分海域实行为期十年的禁渔，常年禁止天然渔业资源的生产性捕捞。禁渔三年多来，相关评估显示，长江干流和鄱阳湖、洞庭湖水生生物完整性指数由禁渔前最差的“无鱼”提升了两个等级。2022年，长江江豚数量达到1249头，实现历史性止跌回升。长江干流水质连续4年全线保持Ⅱ类。
      </p>
      <p>
        实施长江十年禁渔，解决好渔民上岸后的生产生活问题，禁渔才有稳定扎实的社会基础。
      </p>
      <img src="img/4.jpg"  width="100%">
      <p>
        安徽退捕转产的3万多名渔民，在政府的引导下接受就业培训。在当涂县，免费学习养殖技术，养殖生态螃蟹成了退捕渔民的新选择。
      </p>
      <p>
        在拥有洞庭湖超六成水域的湖南岳阳，政府帮扶上岸渔民建起养殖场，发展风干鱼产业，还带领他们学习直播带货，拓宽销路。
      </p>
      <p>
        在渔民退捕上岸的鄱阳湖棠荫岛，当地在继续保护好生态的前提下，正探索规划利用独特的自然资源发展旅游产业。禁渔三年多来，有关部门对23.1万退捕渔民逐一建档立卡，多渠道提升就业、社保水平。
      </p>
      <img src="img/5.jpg"  width="100%">
      <p>
        长江十年禁渔实施以来，沿江省市合力攻坚、久久为功，长江大保护不断向纵深推进，持续巩固禁渔成果。下一步，沿江省市还将加强水生生物重要栖息地修复，建立退捕渔民动态精准帮扶服务，完善跨区域、跨部门执法合作机制，确保一江清水绵延后世、惠泽人民。
      </p>
      <img src="img/6.jpg" >
    </div>
</body>
</html>
```

上述代码，最终经过浏览器的解析渲染之后，效果如下：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NGU1MDdlMjFkOGUwZTYyOWQ5NTliZWQ5ZjdhZjk4MWFfU0t5R2lFZXE0VWZGakFBUzhuZGZLakVEVGVvY1dFeVZfVG9rZW46U2FYWGJCQjJNb3pPalF4T0dPOWN0MEpWbk1jXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

1. #### 盒子模型

1. ##### 介绍

- 盒子：页面中所有的元素（标签），都可以看做是一个 盒子，由盒子将页面中的元素包含在一个矩形区域内，通过盒子的视角更方便的进行页面布局。
- 盒子模型组成：内容区域（content）、内边距区域（padding）、边框区域（border）、外边距区域（margin）。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YWRlM2FkZWUxYzM2NjhmZjZlYTYxYmNmMWY4MWUyZGVfZ0xYZWtnbHRUVVhTSWV5N2RhYXN5Zm9adGhKVGlhV3hfVG9rZW46SW9UR2I1N3Yyb1ltZGR4a1pqcmM4MHVlblRjXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

 CSS盒子模型，其实和日常生活中的包装盒是非常类似的，就比如：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MzcyMTA3MzE1MWY3YjdmMTRmYTFjYWViN2ZkZTM1MWRfTW56NThOQlBqMVhGTExJOEF1eGlIMGNqU0FCSTF4QnpfVG9rZW46U0hDcWJ1R2RFb1ZOUzJ4Nms3TGNsWGFWblViXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

盒子的大小，其实就包括三个部分： border、padding、content，而margin外边距是不包括在盒子之内的。

1. ##### 布局标签

- 布局标签：实际开发网页中，会大量频繁的使用 div 和 span 这两个没有语义的布局标签。
- 标签：`<div>` `<span>`
- 特点：
- `<div>`标签：
  - 一行只显示一个（独占一行）
  - 宽度默认是父元素的宽度，高度默认由内容撑开
  - 可以设置宽高（width、height）
- `<span>`标签：
  - 一行可以显示多个
  - 宽度和高度默认由内容撑开
  - 不可以设置宽高（width、height）

- 测试：

```HTML
<body>
    <div>
        A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
    </div>
    <div>
        A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
    </div>

    <span>
        A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
    </span>
    <span>
        A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
    </span>
</body>
```

浏览器打开后的效果:

1). div会独占一行，默认宽度为父元素 body 的宽度。可以设置宽高（width、height）

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NTgzYTc5YTExZjlkNDA1ZmRhYzQ2ODcyMGVmMGQ0OTFfelBwSGM2VVB6Z2Jjc1FMdmtLa0xFaDRoMmNBMXhRd3dfVG9rZW46R3ZyUWJIUUNnb1lKOE54ZmoyV2N4QmlPbm5oXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

2). span一行会显示多个，用来组合行内元素，默认宽度为内容撑开的宽度。不可以设置宽高（width、height）

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MTZiNTY0NWMxZTRlNGJmZTE1ZDMyYWU0NjVmMmYzNDlfT3pNcElOb25PQ01lcE1TUURXSFZkZXNnVGE4anN6aDFfVG9rZW46QUFFMmJiYVhub1J6Z3d4QTV2TWN5aG5NbjFnXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

1. ##### 代码实现

代码如下: 

```HTML
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>盒子模型</title>
    <style>
        div {
            width: 200px;  /* 宽度 */
            height: 200px;  /* 高度 */
            box-sizing: border-box; /* 指定width height为盒子的高宽 */
            background-color: aquamarine; /* 背景色 */
            
            padding: 20px 20px 20px 20px; /* 内边距, 上 右 下 左 , 边距都一行, 可以简写: padding: 20px;*/ 
            border: 10px solid red; /* 边框, 宽度 线条类型 颜色 */
            margin: 30px 30px 30px 30px; /* 外边距, 上 右 下 左 , 边距都一行, 可以简写: margin: 30px; */
        }
    </style>
</head>

<body>
        
    <div>
        A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A A 
    </div>
    
</body>
</html>
```

代码编写好了, 可以通过浏览器打开该页面, 通过开发者工具,我们就可以看到盒子的大小 , 以及盒子各个组成部分(内容、内边距、边框、外边距)：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=OTBmMTJjOTFmYTg2NWI2MzU2YzQ5ZmIxYjJlMzBhN2JfM0l1bHNINVBOaFREdGEwbmxVaWFjSkVrZXV2T0JhY0lfVG9rZW46TElZSWJvVDZrb05jRUF4T29UaWNPbU8xbmxmXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

我们也可以，通过浏览器的开发者工具，清晰的看到这个盒子，以及每一个部分的大小：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YzU1MGY2MTc4ODA3NTVmZmU1ODgyODllZGEzNjExNWFfOGQ2cEtIaWFqU1o4OW9mZTFsWU94UXdXajdLMXFLaFRfVG9rZW46UjhQY2JRSVM1b1o3UGF4ODNIVmNsSElWbkdBXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

> **备注：**
>
> - 上述的padding、margin属性值，可以是4个值、也可以是两个值、也可以是一个值，具体的含义如下：
> - `padding: 20px 20px 20px 20px;`  -------> 表示上、右、下、左都是20px；
> - `padding: 20px 10px;` ----------------------> 表示上下是20px，左右是10px；
> - `padding: 20px;` -----------------------------> 表示上、右、下、左都是20px；

1. ### 案例

1. #### 需求

通过一个央视新闻页面的制作，大家已经熟悉了HTML中的常见标签及CSS中基础样式的写法及作用。 那接下来呢，我们就要通过一个案例，来加深大家对于这些标签和样式的掌握和使用，那么在完成这个案例的过程中呢，我们依然可以直接基于AI来辅助我们快速实现。

**需求：参照Tlias智能学习辅助系统，完成员工管理页面的制作。** 

产品经理制作的页面原型如下：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ODdhNTc4NWU2NmVlZDg3NjY4YTUwNzljNmE2MzY3NGZfdFZzOHM2WDZlUHJXcjhrUEVWOVlldW5CWmZvMzJxZHRfVG9rZW46WDNLVWJhUjFkb2xSQ1B4NkJncWNnNmlhbndjXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

1. #### 代码实现

1. ##### 顶部导航栏

1. ###### 基本实现

原型效果：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=YTQzYjQ3NTRhZTM2ZTVmMWY4NzQ3ZTJhNmNjOTAxN2NfSVVXUG1PV1JrWVhCZTFrRFpDUEIxR0NBczlMS2xJeTFfVG9rZW46T25YQmJHTWpab0dYZW54OE5pZmNBSTI3bm9lXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

提示词（prompt）：

你是一名前端开发工程师，帮我生成一个HTML页面，页面整体有4个部分组成，先来实现第一个部分：

1. 顶栏

内容：包含左侧的标题“Tlias智能学习辅助系统”，字体需加大加粗，以突出显示；右侧则放置“退出登录”文字链接。

布局：确保标题与退出登录文本位于同一行内，分别左对齐与右对齐 。

页码代码如下：

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
          background-color: #f1f1f1;
          padding: 10px 20px;
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
    </style>
</head>
<body>
    <!-- 顶栏 -->
    <div class="header">
        <h1>Tlias智能学习辅助系统</h1>
        <a href="#">退出登录</a>
    </div>

    <!-- 其他部分可以在这里添加 -->
</body>
</html>
```

页面展示效果如下：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=N2FkZmQ2NzE1OTE1ZjkwNDczZjE5MmU0ZjIwYmI5MzFfaEt1aVBzRHBtZ0NxQUZxVUFaUEVtdG1xdmN3eVd5UlhfVG9rZW46TUtkQWI1b3lXb3hEcWp4WHRHcGNrWHo2bkVoXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

1. ###### flex布局

那在上述的案例代码中，其实我们用到了一种布局模式，叫 **flex布局**。

- flex是flexible Box的缩写，意为"弹性布局"。采用flex布局的元素，称为Flex容器（container），它的所有子元素自动成为容器成员，称为Flex项目（item）。
- 通过给父容器添加flex属性,来控制子元素的位置和排列方式。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZDcyMGJkNzhlYzdmYzBkODc1ODViZTIwNWNhNGFmZGRfbXpEaE5ZUmFLWHZDdzViU3FxWEs4Vk9hR0RweXlQeHFfVG9rZW46SXJuZ2JINENKb1RKeEh4RGpCS2NjV3JmbjViXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

测试代码如下：

```HTML
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <style>
    #container {
      display: flex;
      /* justify-content: space-between; */ /* 先两边贴边，再平分剩余空间 */
      /* justify-content: flex-start;*/ /* 从头开始排列  */
      /* justify-content: flex-end; */ /* 从尾开始排列 */
      /* justify-content: center; */ /* 居中排列 */
      /* justify-content: space-around; */ /* 两边留白，中间平分，平分剩余空间 */
      flex-direction: row;
      justify-content: space-between;
      background-color: #aeea6a;
      width: 400px;
      height: 300px;
    }

    #container div {
      background-color: #e866ef;
      width: 100px;
      height: 50px; 
    }
  </style>
</head>
<body>
  <div id="container">
    <div>Flex Item</div>
    <div>Flex Item</div>
    <div>Flex Item</div>
  </div>
</body>
</html>
```

- flex布局相关的CSS样式：

| 属性            | 说明                       | 取值       | 含义                              |
| --------------- | -------------------------- | ---------- | --------------------------------- |
| display         | 模式                       | flex       | 使用flex布局                      |
| flex-direction  | 设置主轴                   | row        | 主轴方向为x轴，水平向右。（默认） |
| column          | 主轴方向为y轴，垂直向下。  |            |                                   |
| justify-content | 子元素在主轴上的对齐方式   | flex-start | 从头开始排列                      |
| flex-end        | 从尾部开始排列             |            |                                   |
| center          | 在主轴居中对齐             |            |                                   |
| space-around    | 平分剩余空间               |            |                                   |
| space-between   | 先两边贴边，再平分剩余空间 |            |                                   |

如果主轴设置为row，其实就是横向布局。 主轴设置为column，其实就是纵向布局。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NzQxZmFlNDYwYmU0ZDAwNjNkNTUzMTljMGY2Nzc4MmNfN1JSbHZtcTYwcTl2T3RTR1BUZTZvNUE5dmkwYllzU1pfVG9rZW46WXF4ZGIyNXdNb21Zd2h4VEFZcGMxVTBsbndoXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

1. ##### 搜索表单

1. ###### 基本实现

原型效果：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NTdhZjVmMWMwYmIwM2NjZjI3MWVlMWUzMDUwNDMyZjRfYW5wUEZRenFKcGR6QXlhVFdFdGJXMkhJQW1JdkRGV0RfVG9rZW46R1FwNGJjSUNwb3g4a1R4UUpNOWNqWlBwbjFlXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

那这里呢，需要用到HTML中的表单。

那表单呢，在我们日常的上网的过程中，基本上每天都会遇到。比如，我们经常在访问网站时，出现的登录页面、注册页面、个人信息提交页面，其实都是一个一个的表单 。 当我们在这些表单中录入数据之后，一点击 "提交"，就会将表单中我们填写的数据采集到，并提交， 那其实这个数据呢，一般会提交到服务端，最终保存在数据库中 （后面的课程中会讲到）。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MTM2NDgyN2JmODIxMTJiNzA0MGQ3NDY3Y2ZiM2M5Y2Zfa002T1FKbDVtNzd4T0N2b3JROEVlMVFKTHhObVZYYXNfVG9rZW46V1FRZWJ3eUo4b2JwSTR4MndCaWN1WDFnbjZTXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MTNjYmQ4YzViZWI2YzdkYjczNmU2ZmFjYzg0NWQzMmVfQkZpbzJuRHh3MDFPYmlzclMzSUlJcjdNcGlnZVAyUk1fVG9rZW46UXJmeGJkWkthb1pMNlR4QXhjR2NaNk1tblZjXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

提示词（prompt）：

再继续帮我生成第二个部分: 2. 搜索表单区域

组成：包括三个输入控件和两个操作按钮。输入控件具体为：姓名（文本输入框）、性别（下拉选择，选项包括 男/女， 默认为空）、职位（下拉选择，选项包括班主任、讲师、学工主管、教研主管、咨询师， 默认为空）。

按钮：“查询”与“清空”按钮，用于提交表单或重置表单项 。

布局：所有表单项及按钮需水平排列于一行 ，确保美观大气 。

代码实现如下：

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
          background-color: #f1f1f1;
          padding: 10px 20px;
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
          padding: 5px 10px;
          border: 1px solid #ccc;
          border-radius: 4px;
          width: 200px;
      }

      /* 按钮样式 */
      .search-form button {
          padding: 5px 15px;
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
    </style>
</head>
<body>
    <!-- 顶栏 -->
    <div class="header">
        <h1>Tlias智能学习辅助系统</h1>
        <a href="#">退出登录</a>
    </div>

    <!-- 搜索表单区域 -->
    <form class="search-form">
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

</body>
</html>
```

浏览器呈现效果为：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NGI1ZjFjMmNhMjkwZWQ5ZWRlMDc1MmEyODVlZDJjNzdfam4wWVJLUlBEQjNiODV0ZTM0dkY5RllnSk1TcmJISUZfVG9rZW46QVhzZGJGU1RWb1JtVWt4cVZDaWNlUTN1bnRkXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

1. ###### 表单标签

那其实，上述的整个窗口是一个表单，而表单是一项一项的，这个我们称为表单项 或 表单元素。

- 表单场景: 表单就是在网页中负责数据采集功能的，如：注册、登录的表单。 
- 表单标签: `<form>`
- 表单属性: 
  - action: 规定表单提交时，向何处发送表单数据，表单提交的URL。
  - method: 规定用于发送表单数据的方式，常见为： GET、POST。
    - GET：表单数据是拼接在url后面的， 如： xxxxxxxxxxx?username=Tom&age=12，url中能携带的表单数据大小是有限制的。
    - POST： 表单数据是在请求体（消息体）中携带的，大小没有限制。
- 表单项标签: 不同类型的input元素、下拉列表、文本域等。
  - input: 定义表单项，通过type属性控制输入形式
  - select: 定义下拉列表
  - textarea: 定义文本域

- GET请求演示：

如果是get请求，在提交表单时，表单数据是拼接在url后面的。形式为：xxxxxxxxxxx?name=Tomcat&gender=male&position=班主任

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MzliYzYyYzJjYzYxYzg2ZDJiMTIxODZhOTIyOWNmM2JfVU1CTzMzNEZRNDBiWDhpSDl1TlF1Njc2cXFaOW9XMEhfVG9rZW46QzRnc2JQa0xib000Rkh4ZzdjMmNFRURxbmtnXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

- POST请求演示：

如果是post请求，在提交表单时，表单数据是在请求体中传递给服务端的。

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NWY2MTViYzcwNzc4NGNlYjU1MDlmZDMyMmQxZjMyNTNfMFRFZXRYeExMUjYzMjBuNm80YjRxWEI2MnNTSWt2YmpfVG9rZW46TW1IcmJ4aGlqb0hlcmF4YTBIcmNuSTBzbjRjXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

1. ##### 表格数据展示

1. ###### 基本实现

页面效果如下：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=OTU0ZDQ2ZDZiMWM2NjNmMmEwOWVkYjMxZjc3ZTRhNWFfem9JVXdPWFFQOUFMRVVsSm9xUG5kN3g1V2dtT2NEaHBfVG9rZW46QTNLSGJ6aEZjbzdidlp4cFJ0T2NEclQzbmVlXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

提示词（prompt）：

再继续帮我生成第三个部分: 3. 表格展示区

表格结构：展示列包括姓名、性别（显示男/女）、头像（小图标展示）、职位（显示班主任/讲师/学工主管/教研主管/咨询师）、入职日期、最后操作时间、操作（里包含两个按钮 编辑 与 删除）。

数据模拟：基于《笑傲江湖》小说人物生成4条虚拟数据，每条数据应包含上述所有列的信息，以体现实际应用场景 。

样式：可适当调整表格样式，确保美观大气。

代码实现为：

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
          background-color: #f1f1f1;
          padding: 10px 20px;
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
          padding: 5px 10px;
          border: 1px solid #ccc;
          border-radius: 4px;
          width: 200px;
      }

      /* 按钮样式 */
      .search-form button {
          padding: 5px 15px;
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
         margin: 0 20px;
      }

      /* 设置表格单元格边框 */
      .table td, .table th { 
        border: 1px solid #ddd; 
        padding: 8px; 
        text-align: center;
      }
      
      .avatar { 
        width: 50px; 
        height: 50px; 
        object-fit: cover; 
        border-radius: 50%; 
      }

    </style>
</head>
<body>
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
              <td><img src="https://via.placeholder.com/50" alt="令狐冲" class="avatar"></td>
              <td>讲师</td>
              <td>2021-03-15</td>
              <td>2023-07-30T12:00:00Z</td>
              <td class="btn-group">
                  <button>编辑</button>
                  <button>删除</button>
              </td>
          </tr>
          <tr>
              <td>任盈盈</td>
              <td>女</td>
              <td><img src="https://via.placeholder.com/50" alt="任盈盈" class="avatar"></td>
              <td>学工主管</td>
              <td>2020-04-10</td>
              <td>2023-07-29T15:00:00Z</td>
              <td class="btn-group">
                  <button>编辑</button>
                  <button>删除</button>
              </td>
          </tr>
          <tr>
              <td>岳不群</td>
              <td>男</td>
              <td><img src="https://via.placeholder.com/50" alt="岳不群" class="avatar"></td>
              <td>教研主管</td>
              <td>2019-01-01</td>
              <td>2023-07-30T10:00:00Z</td>
              <td class="btn-group">
                  <button>编辑</button>
                  <button>删除</button>
              </td>
          </tr>
          <tr>
              <td>宁中则</td>
              <td>女</td>
              <td><img src="https://via.placeholder.com/50" alt="宁中则" class="avatar"></td>
              <td>班主任</td>
              <td>2018-06-01</td>
              <td>2023-07-29T09:00:00Z</td>
              <td class="btn-group">
                  <button>编辑</button>
                  <button>删除</button>
              </td>
          </tr>
      </tbody>
  </table>

</body>
</html>
```

页面展示的效果为：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=Y2QzMjJmNmU3ZTM1NjdmMzdhZmJhZmE1MzEyZmI1ZTRfSkZVc3VGc0ZBSXFiVWliNko5S2V5TXcyZ252NmJUV0hfVG9rZW46R0lDU2JtY1RhbzNaaDl4MENaRGNlMmpibnBmXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

1. ###### 表格标签

| 标签    | 描述                                                         |
| ------- | ------------------------------------------------------------ |
| <table> | 定义表格整体                                                 |
| <thead> | 用于定义表格头部(可选)                                       |
| <tbody> | 定义表格中的主体部分(可选)                                   |
| <tr>    | 表格的行，可以包裹多个 <td>                                  |
| <td>    | 表格单元格(普通)，可以包裹内容；如果是表头单元格，可以替换为 <th> |

1. ##### 底部版权区域

页面原型展示如下：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=NWI3MGVhMzBmNThkYjdhNDQyNWVkMzgzZjE2OGM0MDNfSFRTRTY3WkdZUVA0V005YjNSVk0ySnlWU09ycVJyS1hfVG9rZW46WWZ2RGJnaXRJbzF5eVp4OUdEb2NOZ3E4bmpnXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

提示词（prompt）:

再继续帮我生成第二个部分: 4. 页脚版权区域

内容：第一行显示公司全称“江苏传智播客教育科技股份有限公司”；第二行展示版权信息，“版权所有 Copyright 2006-2024 All Rights Reserved”。

设计：该区域应具有灰色背景，字体颜色为白色，居中对齐，以营造专业且统一的视觉效果 。

页面代码如下：

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
          background-color: #f1f1f1;
          padding: 10px 20px;
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
          padding: 5px 10px;
          border: 1px solid #ccc;
          border-radius: 4px;
          width: 200px;
      }

      /* 按钮样式 */
      .search-form button {
          padding: 5px 15px;
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
         margin: 0 20px;
      }

      /* 设置表格单元格边框 */
      .table td, .table th { 
        border: 1px solid #ddd; 
        padding: 8px; 
        text-align: center;
      }
      
      .avatar { 
        width: 50px; 
        height: 50px; 
        object-fit: cover; 
        border-radius: 50%; 
      }

      /* 页脚版权区域 */
    .footer {
        background-color: #8f8c8c;
        color: white;
        text-align: center;
        padding: 20px 0;
        margin-top: 30px;
    }

    .footer .company-name {
        font-size: 1.1em;
        font-weight: bold;
    }

    .footer .copyright {
        font-size: 0.9em;
    }
    </style>
</head>
<body>
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
              <td><img src="https://via.placeholder.com/50" alt="令狐冲" class="avatar"></td>
              <td>讲师</td>
              <td>2021-03-15</td>
              <td>2023-07-30T12:00:00Z</td>
              <td class="btn-group">
                  <button>编辑</button>
                  <button>删除</button>
              </td>
          </tr>
          <tr>
              <td>任盈盈</td>
              <td>女</td>
              <td><img src="https://via.placeholder.com/50" alt="任盈盈" class="avatar"></td>
              <td>学工主管</td>
              <td>2020-04-10</td>
              <td>2023-07-29T15:00:00Z</td>
              <td class="btn-group">
                  <button>编辑</button>
                  <button>删除</button>
              </td>
          </tr>
          <tr>
              <td>岳不群</td>
              <td>男</td>
              <td><img src="https://via.placeholder.com/50" alt="岳不群" class="avatar"></td>
              <td>教研主管</td>
              <td>2019-01-01</td>
              <td>2023-07-30T10:00:00Z</td>
              <td class="btn-group">
                  <button>编辑</button>
                  <button>删除</button>
              </td>
          </tr>
          <tr>
              <td>宁中则</td>
              <td>女</td>
              <td><img src="https://via.placeholder.com/50" alt="宁中则" class="avatar"></td>
              <td>班主任</td>
              <td>2018-06-01</td>
              <td>2023-07-29T09:00:00Z</td>
              <td class="btn-group">
                  <button>编辑</button>
                  <button>删除</button>
              </td>
          </tr>
      </tbody>
  </table>

  <!-- 页脚版权区域 -->
  <footer class="footer">
    <p class="company-name">江苏传智播客教育科技股份有限公司</p>
    <p class="copyright">版权所有 Copyright 2006-2024 All Rights Reserved</p>
  </footer>

</body>
</html>
```

页面展示如下：

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=MmJiNjgxN2MzOTYzMWRkZDUyMGU0ZDZlNjJiNjMxNmFfaHNPUlR0Vm1Yb3g4SEN2am1sQ2JFU1ByOEhGdnRQNDlfVG9rZW46S1pnQmJFYlpXb0N1NHZ4VU9MQmNCaThBblNoXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)

1. ##### 版心居中

这个案例类似于央视新闻页面，页面中的内容，都需要居中显示，所以这里呢，我们就可以使用盒子模型来进行布局。具体代码如下：

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
          background-color:  #c2c0c0;
          padding: 10px 20px;
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
          padding: 5px 10px;
          border: 1px solid #ccc;
          border-radius: 4px;
          width: 200px;
      }

      /* 按钮样式 */
      .search-form button {
          padding: 5px 15px;
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
          <option value="male">男</option>
          <option value="female">女</option>
      </select>
      <select name="position">
          <option value="">职位</option>
          <option value="班主任">班主任</option>
          <option value="讲师">讲师</option>
          <option value="学工主管">学工主管</option>
          <option value="教研主管">教研主管</option>
          <option value="咨询师">咨询师</option>
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
                  <button>编辑</button>
                  <button>删除</button>
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
                  <button>编辑</button>
                  <button>删除</button>
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
                  <button>编辑</button>
                  <button>删除</button>
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
                  <button>编辑</button>
                  <button>删除</button>
              </td>
          </tr>
      </tbody>
    </table>

    <!-- 页脚版权区域 -->
    <footer class="footer">
      <p class="company-name">江苏传智播客教育科技股份有限公司</p>
      <p class="copyright">版权所有 Copyright 2006-2024 All Rights Reserved</p>
    </footer>

  </div>

</body>
</html>
```

页面效果如下:

![img](https://heuqqdmbyk.feishu.cn/space/api/box/stream/download/asynccode/?code=ZWE0ZDE3YjJhYTcyYTUwYzc1OTI5M2M4OThhOGRmYWZfZEdRckk3U0ZSWDFNRXlpYnVCaWJZY3JReGQ0VEM1Zk9fVG9rZW46SGdTMWJXUjlpbzZpMzJ4U082RmNFZjcwbm1oXzE3OTA4NjQ3MjA6MTc5MDg2ODMyMF9WNA&add_watermark=true&scene_type=CCM)