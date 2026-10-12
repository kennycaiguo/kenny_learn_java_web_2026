package org.kenny.thymeleaf.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           //1.往请求域中绑定数据(也可以设置到WebContext对象中)
           req.setAttribute("username", "Benny");
           //2.设置响应编码
           resp.setContentType("text/html;charset=utf-8");
           //3/渲染模板文件
           ServletContext application = getServletContext();
           //3.1 从应用程序域中获取jakartaServletWebApplication对象
           JakartaServletWebApplication jakartaServletWebApplication =
                (JakartaServletWebApplication) application.getAttribute("jakartaServletWebApplication");
          //           System.out.println(jakartaServletWebApplication);
           //3.2 从应用程序域中获取templateEngine对象
           TemplateEngine templateEngine = (TemplateEngine) application.getAttribute("templateEngine");
           //创建Thymeleaf 的 Web 环境上下文对象WebContext
           WebContext webContext = new WebContext(jakartaServletWebApplication.buildExchange(req,resp),req.getLocale());
           //可以将数据绑定到webContext对象中,这里的值会覆盖请求对象里面设置的值
           //webContext.setVariable("username", "Jack");
           //3.3利用webContext对象来处理、渲染模板文件
           templateEngine.process("index", webContext, resp.getWriter());
    }
}
