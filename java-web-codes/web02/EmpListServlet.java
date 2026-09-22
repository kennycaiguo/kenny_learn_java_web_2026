package org.kenny.servlet;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class EmpListServlet implements Servlet{
    public void destroy(){

    }

    public void init(ServletConfig config) throws ServletException{

    }

    public String getServletInfo(){
      return " ";
    }

    //是servlet的核心方法，每一次请求，这个方法都会被调用
    public void service(ServletRequest req,ServletResponse res) throws ServletException,IOException{
       //设置字符编码，防止中文乱码
       res.setContentType("text/html;charset=UTF-8");
       //获取PrintWriter对象
       PrintWriter out = res.getWriter();
       //连接数据库，重新所有员工的名字
        // 数据库连接信息
       String URL = "jdbc:mysql://localhost:3306/company?useSSL=false&serverTimezone=UTC";
       String USER = "root";
       String PASS = "root";
       Connection conn = null;
       ResultSet rs = null;
       PreparedStatement stmt = null;
       try {
          Class.forName("com.mysql.cj.jdbc.Driver");
          try {
               conn = DriverManager.getConnection(URL, USER, PASS);
               String sql="select ename,sal from emp order by sal desc;";
               stmt = conn.prepareStatement(sql);
               rs = stmt.executeQuery();
               while (rs.next()){
                   String ename = rs.getString("ename");
                   String sal = rs.getString("sal");
                   out.print(ename + "&nbsp;&nbsp;&nbsp;&nbsp;" + sal+"<br>");
               }
          }catch (Exception e){
              e.printStackTrace();
          }
       } catch (Exception e){
           e.printStackTrace();
       }

    }

    public ServletConfig getServletConfig(){
      return null;
    }
}

