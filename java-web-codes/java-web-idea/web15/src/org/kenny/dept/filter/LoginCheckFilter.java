package org.kenny.dept.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.kenny.dept.util.DbUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebFilter("/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //转换
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        Cookie[] cookies = req.getCookies();
        String servletPath = req.getServletPath();
        //判断用户是否登录
        // /*会拦截所有路径包括/，这会导致网站无法正常执行，我们需要给/css ，/login和/index放行，次数需要判断是否是/index和/login 和 /css
        //如果是这些路径，不需要拦截，此外，图片，js文件都需要放行
        HttpSession session = req.getSession(false);//这里false表示只获取当前session，获取不到不新建，也就是指定使用登录时创建的session对象

        if("/index".equals(servletPath) || "/login".equals(servletPath) || servletPath.startsWith("/css")||servletPath.startsWith("/images")
                || servletPath.startsWith("/js") || (session!=null && session.getAttribute("username") != null)) {
//            System.out.println("ok");
            chain.doFilter(req,resp); //这里不能够使用自己的doFilter.而需要使用chain.doFilter
        } else if(cookies!=null && cookies.length>0){
//            System.out.println("else if...");
            //从cookie中获取token和series
            String series = null;
            String token = null;
            for (Cookie cookie : cookies) {
                if ("remember".equals(cookie.getName())) {
                    String data = cookie.getValue();
                    series = data.split(":")[0];
                    token = data.split(":")[1];
                    break;
                }
            }
//            boolean isLogin = false;
            if(series !=null && token !=null){
                //验证token的有效性，需要查询数据库的user_remember_tokens数据表
                String sql = "select u.username from user_remember_tokens urt join t_user u on urt.user_id=u.id where urt.token=? and urt.series=? and urt.fingerprint=? and urt.expires_at > now() and urt.revoked = '0'";
                try(Connection conn = DbUtils.getConnection();
                    PreparedStatement ps = conn.prepareStatement(sql);){
                    ps.setString(1, token);
                    ps.setString(2, series);
                    String ip = req.getRemoteAddr();
                    String userAgent = req.getHeader("User-Agent");
                    String fingerprint = DigestUtils.sha256Hex(ip+userAgent+"salt");
                    ps.setString(3, fingerprint);
                    try(ResultSet rs = ps.executeQuery();){
                        if(rs.next()){
                            //能够进入这里说明token有效
//                            isLogin = true; //说明登录成功
                            HttpSession s = req.getSession();
                            s.setAttribute("username", rs.getString("username"));
                            chain.doFilter(req, resp);
                        } else {
                            //token无效
                            //删除客户端token，将数据库里面的revoke字段设置为1
                            String sql1 = "update user_remember_tokens set revoked=1 where token=?";
                            try(PreparedStatement ps1 = conn.prepareStatement(sql1);){
                                ps1.setString(1, token);
                                int count = ps1.executeUpdate();
                                //删除浏览器客户端的token
                                Cookie cookie = new Cookie("remember", "");
                                cookie.setMaxAge(0);
                                resp.addCookie(cookie);
                                //跳转到登录页面
                                resp.sendRedirect(req.getContextPath()+"/index");
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            } else{
                //有cookie，但是token是空的，说明需要登录生成token
                resp.sendRedirect(req.getContextPath()+"/index");
            }

        } else {
            resp.sendRedirect(req.getContextPath()+"/index");
            //这里可以使用转发，转发它不走过滤器,不需要判断"/index".equals(servletPath)
            //req.setAttribute("template","index");
            //req.getRequestDispatcher("/view").forward(request,response);
            //也可以这么写
            //req.getRequestDispatcher("/index").forward(request,response);
        }
    }
}
