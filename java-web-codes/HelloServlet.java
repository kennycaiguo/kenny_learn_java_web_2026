package org.kenny.servlet;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet implements Servlet{
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
       System.out.println("Hello,Servlet!!!");
       //获取PrintWriter对象
       PrintWriter out = res.getWriter();
       out.print("<h1>这是HelloServlet,欢迎访问！！！</h1>");
    }

    public ServletConfig getServletConfig(){
      return null;
    }
}

