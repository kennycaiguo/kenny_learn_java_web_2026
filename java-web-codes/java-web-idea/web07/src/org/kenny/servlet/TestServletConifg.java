package org.kenny.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class TestServletConifg extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
           res.setContentType("text/html;charset=utf-8");
           PrintWriter out = res.getWriter();
           //获取ServletConfig对象
//           ServletConfig config = this.getServletConfig();
           out.println("<h3>Servlet对象："+this+"</h3>");
           out.println("<h3>Servlet对象名称："+this.getServletName()+"</h3>");
           //获取ServletContext对象
           out.println("<h3>ServletContext对象："+this.getServletContext()+"</h3>");

//           out.println("<h3>Servlet对象的配置对象："+config+"</h3>");
           //获取所有初始化参数的名称
//           Enumeration<String> names = config.getInitParameterNames();
           Enumeration<String> names = this.getInitParameterNames();
           while (names.hasMoreElements()) {
               String name = names.nextElement();
//               String value = config.getInitParameter(name);
               String value = this.getInitParameter(name);
               System.out.println("初始化变量名称："+name+","+"变量值："+value);
           }

    }
}
