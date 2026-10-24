package org.kenny.dept.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.kenny.dept.util.DbUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //logout要做什么？
        //获取session对象并且销毁
        HttpSession session = req.getSession(false);
        if(session != null) {
            session.invalidate(); //销毁session
        }
//        //删除cookie
//        Cookie cookie= new Cookie("username", ""); //名称和我们当前cookie的键名称必须一致，value可以不写
//        cookie.setMaxAge(0);
//        resp.addCookie(cookie);
        //删除客户端的token，也就是cookie里面的，并且在数据库中把token的revoked设置为1
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
                String sql1 = "update user_remember_tokens set revoked=1 where token=?";
                try(Connection conn = DbUtils.getConnection();
                    PreparedStatement ps1 = conn.prepareStatement(sql1);){
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


        //重定向到登录页面
        resp.sendRedirect(req.getContextPath());
    }
}
