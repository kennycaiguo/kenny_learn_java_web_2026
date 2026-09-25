package com.guo.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class HelloServlet implements Servlet {


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
           response.setContentType("text/html;charset=utf-8");
           PrintWriter out = response.getWriter();
           out.println("<h1>Hello World!</h1>");
           //连接数据库
           String url="jdbc:mysql://localhost:3306/company";
           String driver="com.mysql.cj.jdbc.Driver";
           String username="root";
           String password="root";
           String sql="select ename,sal from emp order by sal desc";
           try {
               Class.forName(driver);
               try(
                   Connection conn=DriverManager.getConnection(url,username,password);
                   PreparedStatement ps=conn.prepareStatement(sql);
                   ResultSet rs=ps.executeQuery();
                   ) {
                  while (rs.next()) {
                      String ename=rs.getString("ename");
                      String sal=rs.getString("sal");
                      out.println("<h1>"+ename+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+sal+"</h1>");
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
