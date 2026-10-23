package org.kenny.dept.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.kenny.dept.dao.DeptDao;
import org.kenny.dept.dao.impl.DeptDaoImpl;
import org.kenny.dept.util.Argon2PasswordUtil;
import org.kenny.dept.util.DbUtils;
import org.kenny.dept.util.TokenGenerator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户名和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Integer user_id = null;
        //直接使用Jdbc来验证用户名和密码
        String sql = "select password ,id from t_user where username=?";
        boolean loginSucess = false;
        try(Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);){
            ps.setString(1,username);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    //获取数据库里面加密了的密码
                    String hashedPassword = rs.getString(1);
                    user_id = Integer.parseInt(rs.getString(2)); //查询密码的时候把用户id也查到
                    //把密文密码和用户传递的密码加密后的结果进行比较
                    loginSucess = Argon2PasswordUtil.verifyPassword(password,hashedPassword);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //根据上面比较的结果进行处理
        if(loginSucess){
            //登录成功后需要把用户名设置到session
            HttpSession session = req.getSession();
            session.setAttribute("username",username); //getSession()里面不能传递false，因为这里我们要求必须获取session
            //登录成功,必须用重定向,不能用转发,因为/list路径只支持get请求,而这里是post请求转发后还是post请求,会引发405错误
            // 要实现7天免登录，需要创建cookie，把用户名设置到cookie
            String insertSql = "insert into user_remember_tokens(user_id,token,series,fingerprint,expires_at) values(?,?,?,?,?);";
            //生成token并且把它保存到数据库的user_remember_tokens数据表中
            try(Connection conn = DbUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(insertSql);){
                //传递值给形参
                ps.setLong(1,user_id);
                String token = TokenGenerator.generateSecureToken();
                ps.setString(2,token);
                //生成series，使用GUID
                String series = UUID.randomUUID().toString();
                ps.setString(3,series);
                //获取ip，浏览器表示用来生成指纹
                String ip = req.getRemoteAddr();
                String userAgent = req.getHeader("User-Agent");
                String fingerprint = DigestUtils.sha256Hex(ip+userAgent+"salt");
                ps.setString(4,fingerprint);
                LocalDateTime expires_at = LocalDateTime.now().plusDays(30);
                ps.setTimestamp(5, Timestamp.valueOf(expires_at)); //设置30天有效
                //保存数据
                int count = ps.executeUpdate();
                if(count>0){
                    //保存成功后token和series设置到cookie响应给浏览器
                    Cookie cookie = new Cookie("remember",series+":"+token);
                    cookie.setMaxAge(60*60*24*30); //过期时间为30天，60*60*24*30
                    cookie.setPath(req.getContextPath()); //这个其实不用设置，有默认值
                    cookie.setHttpOnly(true); //防止xxs攻击
                    if(req.isSecure()){
                        cookie.setSecure(true); //如果是https需要设置这个
                    }
                    resp.addCookie(cookie);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            resp.sendRedirect(req.getContextPath()+"/list");
        } else {
            //登录失败
            req.setAttribute("errorMsg","用户名或者密码错误");
            req.setAttribute("template","index");
            req.getRequestDispatcher("/view").forward(req,resp);

        }
    }
}
