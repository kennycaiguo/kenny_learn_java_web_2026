package org.kenny.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;

public class TestServletContext extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=utf-8");
        PrintWriter out = res.getWriter();
        //获取ServletContext对象，注意ServletContext对象在整个web应用中只有一份
        ServletContext context = this.getServletContext();
        ServletContext context2 = this.getServletConfig().getServletContext();
        out.println("ServletContext对象："+context+"<br>");
        out.println("ServletContext对象2："+context2+"<br>");
        out.println("context1==context2吗："+(context==context2));

    }
}
