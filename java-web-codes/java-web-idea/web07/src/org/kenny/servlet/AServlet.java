package org.kenny.servlet;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

@WebServlet("/a")
public class AServlet extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        System.out.println("AServlet service...");
        //创建User对象
        User user = new User("Jack",20);
        //设置到上下文中
        getServletContext().setAttribute("user",user);

    }
}
