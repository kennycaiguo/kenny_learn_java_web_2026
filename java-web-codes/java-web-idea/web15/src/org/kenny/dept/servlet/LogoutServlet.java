package org.kenny.dept.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //logout要做什么？
        //获取session对象并且销毁
        HttpSession session = req.getSession();
        if(session != null) {
            session.invalidate(); //销毁session
        }
        //重定向到登录页面
        resp.sendRedirect(req.getContextPath());
    }
}
