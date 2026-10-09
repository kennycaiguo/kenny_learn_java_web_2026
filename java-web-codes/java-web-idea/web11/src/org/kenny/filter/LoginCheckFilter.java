package org.kenny.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.io.PrintStream;

//@WebFilter(urlPatterns = {"/*"})
//@WebFilter(value = {"/*"})
@WebFilter("/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (java.io.UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("LoginCheckFilter init");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("登录验证开始...");
        //执行下一个过滤器，如果没有就执行servlet的代码,
        filterChain.doFilter(servletRequest, servletResponse);//这一行代码如果不写，直接从这里返回，后面的过滤器或者目标servlet的代码无法执行
        System.out.println("登录验证完成...");
    }

    @Override
    public void destroy() {
        System.out.println("LoginCheckFilter destroy");
    }
}
