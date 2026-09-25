package org.kenny.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;

public class LifecycleServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init方法执行了");
    }

    @Override
    public ServletConfig getServletConfig() {
        System.out.println("getServletConfig方法执行了");
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("service方法执行了");
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.println("Hello Clients");

    }

    @Override
    public String getServletInfo() {
        System.out.println("getServletInfo方法执行了");
        return "";
    }

    @Override
    public void destroy() {
        System.out.println("destroy方法执行了");
    }
}
