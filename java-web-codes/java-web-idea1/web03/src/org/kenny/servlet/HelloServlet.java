package org.kenny.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HelloServlet implements Servlet {

    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<h1>Hello World From HelloServlet!</h1>");
        // 数据库连接信息
        String URL = "jdbc:mysql://localhost:3306/company?useSSL=false&serverTimezone=UTC";
        String USER = "root";
        String PASS = "root";
        String driver = "com.mysql.jdbc.Driver";
        String sql="select ename,sal from emp order by sal desc";
        try {
            Class.forName(driver);
            try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
                 PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery();
            ) {
               while (rs.next()) {
                String ename = rs.getString("ename");
                String sal = rs.getString("sal");
                out.println(ename+" "+sal+"<br>");
             }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "";
    }

    @Override
    public void destroy() {

    }
}
