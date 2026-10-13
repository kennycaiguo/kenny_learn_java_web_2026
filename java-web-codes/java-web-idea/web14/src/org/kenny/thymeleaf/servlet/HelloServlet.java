package org.kenny.thymeleaf.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.kenny.thymeleaf.entity.User;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("username","Lucy");
        req.setAttribute("template","index");
        req.setAttribute("msg","Hello,<strong>world</strong>");
        //创建User对象
        User user = new User("Jack",20);
        //设置到请求域
        req.setAttribute("user",user);
        //创建User对象
        User user2 = new User("Mary",20,true);
        //设置到请求域
        req.setAttribute("user2",user2);
        //创建一个集合，方便类型th:each循环
        ArrayList<User> users = new ArrayList<User>();
        User u1 = new User("Mike",20,true);
        users.add(u1);
        User u2 = new User("Tracy",22,false);
        users.add(u2);
        User u3 = new User("Brandy",21,false);
        users.add(u3);
        //把集合设置到请求域
        req.setAttribute("users",users);
        //转发
        req.getRequestDispatcher("/view").forward(req,resp);
        //重定向
        //resp.sendRedirect(req.getContextPath()+"/view?template=index&username=Lucy");
    }
}
