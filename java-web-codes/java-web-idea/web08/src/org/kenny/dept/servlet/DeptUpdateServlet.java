package org.kenny.dept.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.kenny.dept.dao.DeptDao;
import org.kenny.dept.dao.impl.DeptDaoImpl;
import org.kenny.dept.entity.Dept;

import java.io.IOException;

@WebServlet("/update")
public class DeptUpdateServlet extends HttpServlet {
    private DeptDao dao = new DeptDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        int deptNo = Integer.parseInt(req.getParameter("deptId"));
        String deptName = req.getParameter("deptName");
        String loc = req.getParameter("location");
        int count = dao.update(new Dept(deptNo, deptName, loc));
        if (count > 0) {
            resp.sendRedirect(req.getContextPath()+"/list");
        }
    }
}
