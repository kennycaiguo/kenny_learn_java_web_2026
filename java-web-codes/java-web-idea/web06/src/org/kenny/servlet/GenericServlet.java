package org.kenny.servlet;

import jakarta.servlet.*;

import java.io.IOException;

public abstract class GenericServlet implements Servlet {
    //需要一个成员变量来保存传递进来的servletConfig
    private ServletConfig servletConfig;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
          this.servletConfig = servletConfig;
          //调用无参数的init方法
//         init();
    }

    //子类重写这个方法，不要重写上面的方法。
//    void init(){
//
//    }

    @Override
    public ServletConfig getServletConfig() {

        return this.servletConfig;
    }

    @Override
    public abstract void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException;

    @Override
    public String getServletInfo() {
        return "";
    }

    @Override
    public void destroy() {

    }
}
