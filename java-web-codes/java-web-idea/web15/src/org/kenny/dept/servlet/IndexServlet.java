package org.kenny.dept.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.kenny.dept.util.DbUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //定义一个是否登录了的标记
        boolean isLogin = false;
        //获取session如果session不为空有一个username属性，说明用户已经登录
        HttpSession session = req.getSession(false);
        if(session!=null && session.getAttribute("username")!=null){
            //进入到这里说明用户用户登录了。只需要把登录标记改为true
            isLogin = true;
        } else{
            //需要token验证
            Cookie[] cookies = req.getCookies();
            if(cookies!=null &&  cookies.length>0){
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
                                HttpSession s = req.getSession();
                                s.setAttribute("username", rs.getString("username"));
                                isLogin = true; //说明登录成功
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
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }
        if (isLogin) {
            //如果已经登录了，跳转到list
            //重定向到list页面或者转发也行
            // resp.sendRedirect(req.getContextPath() + "/list");
            req.getRequestDispatcher("/list").forward(req, resp);

        } else {
           //如果没有，转发到/view
            req.setAttribute("template", "index");
            req.getRequestDispatcher("/view").forward(req, resp);
        }

    }
}
