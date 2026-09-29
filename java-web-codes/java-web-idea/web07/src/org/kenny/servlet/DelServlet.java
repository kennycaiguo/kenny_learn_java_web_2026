package org.kenny.servlet;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/del")
public class DelServlet extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
           getServletContext().removeAttribute("user");
           res.setContentType("text/html;charset=utf-8");
           PrintWriter out = res.getWriter();
           out.println("user已经被删除");
    }
}
