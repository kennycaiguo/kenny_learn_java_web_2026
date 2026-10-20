package org.kenny.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/accept")
public class AcceptCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        //获取cookie，cookie是被封装在请求对象里面，它是一个数组
        Cookie[] cookies = req.getCookies(); //
        if (cookies != null) {  //上面getCookies()方法如果浏览器没有提交cookie，就会返回null引发空指针异常，所以这里需要判断一下
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                out.println(String.format("%s=%s", name, value));
                System.out.println("Cookie Data:\n");
                System.out.println("Name: " + name+ ", Value: " + value);
            }
        }

    }
}
