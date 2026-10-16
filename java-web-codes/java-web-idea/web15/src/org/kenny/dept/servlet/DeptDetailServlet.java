package org.kenny.dept.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.kenny.dept.dao.impl.DeptDaoImpl;
import org.kenny.dept.entity.Dept;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/detail")
public class DeptDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int deptId = Integer.parseInt(req.getParameter("id"));
        Dept dept = new DeptDaoImpl().selectByNo(deptId);
        //把部门对象存储到请求域
        req.setAttribute("dept", dept);
        req.setAttribute("template","detail");
        //转发到/view
        req.getRequestDispatcher("/view").forward(req,resp);

    }
}
