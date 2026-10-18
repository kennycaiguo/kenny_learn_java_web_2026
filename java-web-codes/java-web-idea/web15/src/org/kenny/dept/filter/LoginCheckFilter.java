package org.kenny.dept.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //转换
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String servletPath = req.getServletPath();
        //判断用户是否登录
        // /*会拦截所有路径包括/，这会导致网站无法正常执行，我们需要给/css ，/login和/index放行，次数需要判断是否是/index和/login 和 /css
        //如果是这些路径，不需要拦截，此外，图片，js文件都需要放行
        HttpSession session = req.getSession(false); //这里false表示只获取当前session，获取不到不新建，也就是指定使用登录时创建的session对象
        if("/index".equals(servletPath) || "/login".equals(servletPath) || servletPath.startsWith("/css")
                || (session!=null && session.getAttribute("username") != null)) {
                chain.doFilter(request,response); //这里不能够使用自己的doFilter.而需要使用chain.doFilter
        } else{
            resp.sendRedirect(req.getContextPath()+"/index");
        }
    }
}
