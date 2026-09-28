package org.kenny.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends GenericServlet{
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        //调用父类的init方法
        super.init(servletConfig);
        //添加自己的代码
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
         response.setContentType("text/html;charset=utf-8");
         PrintWriter out = response.getWriter();
         out.println("<h2>Hello World!</h2>");
        System.out.println(this.getServletConfig().toString());
        System.out.println(this.getServletConfig().getServletName());
    }
}
