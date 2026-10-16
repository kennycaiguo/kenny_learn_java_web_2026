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

@WebServlet("/edit")
public class DeptEditServlet extends HttpServlet {

    private DeptDao dao = new DeptDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户传递管理的部门编号
        int deptNo = Integer.parseInt(req.getParameter("id"));
        //根据编号查询部门信息
        Dept dept = dao.selectByNo(deptNo);
        //设置必要的数据
        req.setAttribute("dept", dept);
        req.setAttribute("template", "edit");
        //转发到/view路径
        req.getRequestDispatcher("/view").forward(req, resp);
    }
}
