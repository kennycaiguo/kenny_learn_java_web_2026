package org.kenny.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
/**
 * cookie既可以在浏览器端用js代码生成，又可以在服务器端生成，然后发送给浏览器保存
 * */
@WebServlet("/cookie")
public class MyCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //创建cookie
        Cookie cookie =new Cookie("username","admin");
        //设置属性
        cookie.setPath(req.getContextPath());
        cookie.setDomain("192.168.100.138");
        //设置cookie 7天有效
        cookie.setMaxAge(60*60*24*7);
        //禁止js代码读取cookie
        cookie.setHttpOnly(true);
        //如果当前协议是https，我们就把secure属性设置为true
        if(req.isSecure()){ //这个方法可以判断当前协议是否是https
            cookie.setSecure(true);
        }
        //响应给浏览器
        resp.addCookie(cookie);
    }
}
