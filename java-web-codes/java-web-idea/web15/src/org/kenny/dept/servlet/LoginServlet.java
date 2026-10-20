package org.kenny.dept.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.kenny.dept.dao.DeptDao;
import org.kenny.dept.dao.impl.DeptDaoImpl;
import org.kenny.dept.util.Argon2PasswordUtil;
import org.kenny.dept.util.DbUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户名和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //直接使用Jdbc来验证用户名和密码
        String sql = "select password from t_user where username=?";
        boolean loginSucess = false;
        try(Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);){
            ps.setString(1,username);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    //获取数据库里面加密了的密码
                    String hashedPassword = rs.getString(1);
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
            req.getSession().setAttribute("username",username); //getSession()里面不能传递false，因为这里我们要求必须获取session
            //登录成功,必须用重定向,不能用转发,因为/list路径只支持get请求,而这里是post请求转发后还是post请求,会引发405错误
            // 要实现7天免登录，需要创建cookie，把用户名设置到cookie
            //并且设置cookie的过期时间为7天，60*60*24*7
            Cookie cookie = new Cookie("username",username);
            cookie.setMaxAge(60*60*24*7); //过期时间为7天，60*60*24*7
            cookie.setPath(req.getContextPath());
            cookie.setHttpOnly(true); //防止js代码获取cookie数据
            if(req.isSecure()){
                cookie.setSecure(true);
            }
            resp.addCookie(cookie); // 响应给浏览器
            resp.sendRedirect(req.getContextPath()+"/list");
        } else {
            //登录失败
            req.setAttribute("errorMsg","用户名或者密码错误");
            req.setAttribute("template","index");
            req.getRequestDispatcher("/view").forward(req,resp);

        }
    }
}
