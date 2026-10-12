package org.kenny.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

@WebServlet("/save")
public class SaveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置控制台字符编码
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.out.println("doGet");
        String username = req.getParameter("username");
        System.out.println("get -->username:"+username);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost");
        String username = req.getParameter("username");
        System.out.println("post -->username:"+username);
    }
}
