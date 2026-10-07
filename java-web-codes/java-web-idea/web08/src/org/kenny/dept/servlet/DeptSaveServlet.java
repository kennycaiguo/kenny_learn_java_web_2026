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

@WebServlet("/save")
public class DeptSaveServlet extends HttpServlet {
    private DeptDao dao = new DeptDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          resp.setContentType("text/html;charset=utf-8");
          resp.setCharacterEncoding("utf-8");
          PrintWriter out = resp.getWriter();
          String location = req.getParameter("location");
          String deptName = req.getParameter("deptName");
          //out.print("Dept Name:"+deptName+",Location:"+location);

          int deptNo = dao.selectMaxNo()+1;
          int row = dao.insert(new Dept(deptNo,deptName,location));
          if(row>0){
              //out.print("添加部门成功");
              resp.sendRedirect(req.getContextPath()+"/list");
          }

    }
}
