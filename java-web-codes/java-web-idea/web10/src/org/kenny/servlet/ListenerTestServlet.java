package org.kenny.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/test")
public class ListenerTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext app = this.getServletContext();
        app.setAttribute("msg", "hello world"); //添加
        app.setAttribute("msg", "hello Java"); //替换
        app.removeAttribute("msg");

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html;charset=utf-8");
        out.print("TestServlet");
    }
}
