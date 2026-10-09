package org.kenny.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.kenny.entity.User;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/a")
public class AServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //创建用户对象
        User user = new User("Jackline",20);
        //绑定到到请求域里面
         req.setAttribute("user",user);
        // //获取这个值
        // Object obj = req.getAttribute("user");
        // resp.setContentType("text/html;charset=utf-8");
        // PrintWriter out = resp.getWriter();
        // out.print("从请求域中获取的对象:"+obj);
        //资源跳转方式1，重定向是通过resp对象也就是响应对象
        //resp.sendRedirect(getServletContext().getContextPath()+"/b");
        //资源跳转方式2，转发,是通过req，也就是请求对象来完成的。本质是服务器内部资源跳转，浏览器不会发送新请求
        // 转发到 /b
        ////1.获取请求转发器对象
        //RequestDispatcher dispatcher = req.getRequestDispatcher("/b");
        ////2.路由获取请求转发器对象进行转发
        //        dispatcher.forward(req,resp);

        //需要跳转到WEB-INF里面的test.html,因为浏览器不能直接访问WEB-INF，不能使用重定向，所以只能使用转发
        req.getRequestDispatcher("/WEB-INF/test.html").forward(req,resp);
    }
}
