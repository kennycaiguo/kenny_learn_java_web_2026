package org.kenny.dept.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.kenny.dept.dao.DeptDao;
import org.kenny.dept.dao.impl.DeptDaoImpl;

import java.io.IOException;

@WebServlet("/delete")
public class DeptDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int deptNo = Integer.parseInt(req.getParameter("id"));
        DeptDao dao = new DeptDaoImpl();
        int count = dao.deleteByNo(deptNo);
        if (count > 0) {
            //重定向到列表页面
            resp.sendRedirect(req.getContextPath()+"/list");
        }

    }
}
