package org.kenny.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.io.PrintStream;

@WebListener
public class MyServletContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (java.io.UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        sce.getServletContext().setAttribute("data", 100);
        System.out.println("Server started...服务器启动");
    }
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (java.io.UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        System.out.println(sce.getServletContext().getAttribute("data"));
        System.out.println("Server stopped...服务器关闭。。。");
    }
}
