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
import java.util.List;

@WebServlet("/list")
public class DeptListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置响应内容类型和编码
        resp.setContentType("text/html;charset=utf-8");
        //获取页面的输出对象
        PrintWriter out = resp.getWriter();
        out.println("""
                <main class="main">
                        <header class="topbar"><h1>部门管理</h1>
                            <div class="user">管理员
                                <button class="logout-btn" onclick="logout()">退出登录</button>
                            </div>
                        </header>
                        <section class="page">
                            <div class="page-header">
                                <div><h2>部门列表</h2>
                                    <p>查看、编辑和维护部门数据</p></div>
                                <a class="btn btn-primary" href="add.html">＋ 新增部门</a></div>
                            <div class="card">
                                <div class="toolbar"><input id="keyword" class="input" placeholder="搜索部门名称 / 负责人"><select id="status"
                                                                                                                          class="input select">
                                    <option value="">全部状态</option>
                                    <option>启用</option>
                                    <option>停用</option>
                                </select>
                                    <button class="btn btn-light" onclick="render()">查询</button>
                                </div>
                                <div class="table-wrap">
                                    <table>
                                        <thead>
                                        <tr>
                                            <th>部门编号</th>
                                            <th>部门名称</th>
                                            <th>部门地址</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody id="deptBody">
                                        <tr>
                                            <td>部门编号</td>
                                            <td>部门名称</td>
                                            <td>部门地址</td>
                                            <td>
                                            <a href='' class='btn btn-success'>查看</a>
                                            <a href='' class='btn btn-warning'>修改</a>
                                            <a href='' class='btn btn-danger'>删除</a>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
//                                <div class="empty" id="empty">暂无数据</div>
                            </div>
                        </section>
                    </main>
                """);
        List<Dept> deptList = null;
        DeptDaoImpl deptDao = new DeptDaoImpl();
        deptList = deptDao.selectAll();

    }
}
