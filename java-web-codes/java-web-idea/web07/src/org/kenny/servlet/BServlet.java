package org.kenny.servlet;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/b")
public class BServlet extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        System.out.println("BServlet service...");
        //获取AServlet中创建的user对象并且显示到页面中
        User user = (User)getServletContext().getAttribute("user");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<h3> User:"+user+"</h3>");

    }
}
