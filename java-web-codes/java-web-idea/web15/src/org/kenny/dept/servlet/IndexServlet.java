package org.kenny.dept.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //方法1，重定向，因为我们不需要任何数据
        //resp.sendRedirect(req.getContextPath()+"/view?template=index");
        //方法2.转发也是可以的。不过这里要保存模板文件的名称,转发的好处就是url不会改变
        req.setAttribute("template", "index");
        req.getRequestDispatcher("/view").forward(req, resp);
    }
}
