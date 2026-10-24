package org.kenny.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

@WebServlet("/test")
public class TestRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
          getAttribute
          setAttribute
          removeAttribute，
          这几个方法我们全面已经学习过了，这里不测试
          setCharacterEncoding("UTF-8");是设置字符编码的
          // 返回客户端IP地址
          String getRemoteAddr();
          // 返回指定路径的请求分派器，用于服务器端转发
          RequestDispatcher getRequestDispatcher(String path);

          // 判断请求是否通过安全协议（如HTTPS）发送
           boolean isSecure(); 这些也是学习过的。
          */
         resp.setContentType("text/html;charset=utf-8");
         PrintWriter out = resp.getWriter();
         //String username = req.getParameter("username");
         //String password = req.getParameter("pwd");
         //resp.setContentType("text/html;charset=utf-8");
         //out.print("username : " + username+",password : "+password);

        // Enumeration<String> names = req.getParameterNames();
        // while (names.hasMoreElements()) {
        //     String name = names.nextElement();
        //     String value = req.getParameter(name);
        //     out.println(name+":"+value);
        // }
        //获取复选框的值
        // String[] hobbies = req.getParameterValues("hobby");
        // out.print(Arrays.toString(hobbies));
        //获取参数的map
        //Map<String, String[]> params = req.getParameterMap();
        //params.forEach((k,v)->{
        //    out.println(k + ": " + Arrays.toString(v));
        //});

        //=========================HttpServletRequest扩展的方法============================
        // // 返回当前Web应用的上下文路径
        // String getContextPath();
        // // 返回客户端发送的所有Cookie数组
        //  Cookie[] getCookies();
        // // 返回指定请求头的值
        //  String getHeader(String name);
        // // 返回HTTP请求方法（如GET、POST等）
        //  String getMethod()
        // //返回请求URL中调用Servlet的部分
        //  String getServletPath();
        // //返回当前会话，若无则创建新会话
        // HttpSession getSession();//这些方法已经学过
        // String contentType = req.getHeader("content-type");
        // out.println("<h3> Content-type:"+contentType+"</h3><br>");
        String uri = req.getRequestURI();
        out.println("Uri: " + uri+"<br>"); // Uri: /web17/test uri是不带主机名和端口的。
        StringBuffer url = req.getRequestURL();
        out.println("Url: " + url.toString()); // Url: http://localhost:8080/web17/test url是完整的路径包括主机名和端口

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
