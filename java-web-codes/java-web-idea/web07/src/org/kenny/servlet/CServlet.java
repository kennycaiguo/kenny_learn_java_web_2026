package org.kenny.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/c")
public class CServlet extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=utf-8");
        PrintWriter out = res.getWriter();
        ServletContext context = this.getServletContext();
        //获取上下文参数
        Enumeration<String> names = context.getInitParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = context.getInitParameter(name);
            out.println(name+"="+value + "<br>");
        }
        //获取上下文路径输出到页面,也就是页面的根路径
        String path = context.getContextPath();
        out.println("Context Path:"+path+ "<br>");
        //获取绝对路径
//        String realPath = context.getRealPath("/c");
//        out.println("/c的绝对路径是:"+realPath+ "<br>");
        String realPath = context.getRealPath("/");
        out.println("/的绝对路径是:"+realPath+ "<br>");
        String webxmlPath = context.getRealPath("/WEB-INF/web.xml");
        out.println("web.xml的绝对路径是:"+webxmlPath+ "<br>");
        //读取WEB-INF里面的jdbc.properties文件
        try (InputStream stream = context.getResourceAsStream("/WEB-INF/jdbc.properties")) {
            out.println("文件输入流对象："+stream);
        } catch (Exception e) {
           out.println(e);
        }
        //记录日志
        context.log(getServletName()+":"+"running!!!");
    }
}
