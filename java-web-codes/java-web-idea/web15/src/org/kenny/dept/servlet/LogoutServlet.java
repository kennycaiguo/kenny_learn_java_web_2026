package org.kenny.dept.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

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
        //删除cookie
        Cookie cookie= new Cookie("username", ""); //名称和我们当前cookie的键名称必须一致，value可以不写
        cookie.setMaxAge(0);
        resp.addCookie(cookie);

        //重定向到登录页面
        resp.sendRedirect(req.getContextPath());
    }
}
