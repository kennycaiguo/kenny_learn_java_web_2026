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
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/list")
public class DeptListServlet extends HttpServlet {

    //定义一个dao成员变量
    private DeptDao deptDao = new DeptDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取所有部门信息
        deptDao = new DeptDaoImpl();
        List<Dept> depts = deptDao.selectAll();
        req.setAttribute("depts", depts);
        req.setAttribute("template","list");
        //转发到/view
        req.getRequestDispatcher("/view").forward(req,resp);
    }
}
