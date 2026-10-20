package org.kenny.dept.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //定义一个是否登录了的标记
        boolean isLogin = false;
        //首先需要判断一下有没有7天免登录的cookie，如果有，看看他的属性名称是否是"username"，如果是，把它的值获取出来保存到session
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if (name.equals("username")) {
                    String username = cookie.getValue();
                    req.getSession().setAttribute("username", username);
                    isLogin = true;
                    break;
                }
            }
        }
        if (isLogin) {
            //如果已经登录了，跳转到list
            //重定向到list页面或者转发也行
            // resp.sendRedirect(req.getContextPath() + "/list");
            req.getRequestDispatcher("/list").forward(req, resp);

        } else {
           //如果没有，转发到/view
            req.setAttribute("template", "index");
            req.getRequestDispatcher("/view").forward(req, resp);
        }

    }
}
