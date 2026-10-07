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
        resp.setContentType("text/html;charset=utf-8");
        //获取所有部门信息
        List<Dept> deptList = null;
        deptDao = new DeptDaoImpl();
        deptList = deptDao.selectAll();
        //需要动态获取项目的根路径，防止我们修改了项目名称找不到根路径
        //String rootPath = this.getServletContext().getContextPath();
        String rootPath = req.getContextPath(); //HttpServletRequest也可以获取到
        //设置响应内容类型和编码
        //获取页面的输出对象
        PrintWriter out = resp.getWriter();
        out.println("""
                 <!DOCTYPE html>
                 <html lang="zh-CN">
                 <head>
                     <meta charset="UTF-8">
                     <meta name="viewport" content="width=device-width, initial-scale=1.0">
                     <title>部门管理系统 - 部门列表</title>
                     <script>
                             function del(deptNo){
                                 if(window.confirm("删除是不可恢复的，你确定要删除该部门吗?")){
                 """);
        out.print("document.location.href='"+rootPath+"/delete?id='+deptNo");
        out.print("""
                               }
                             }
                         </script>
                     <style>
                         * {
                             margin: 0;
                             padding: 0;
                             box-sizing: border-box;
                             font-family: 'Arial', sans-serif;
                         }
                         body {
                             background-color: #f5f5f5;
                         }
                         .container {
                             max-width: 1200px;
                             margin: 0 auto;
                             padding: 20px;
                         }
                         .header {
                             display: flex;
                             justify-content: space-between;
                             align-items: center;
                             margin-bottom: 30px;
                         }
                         .header h1 {
                             color: #333;
                             font-size: 24px;
                         }
                         .add-btn {
                             padding: 10px 20px;
                             background-color: #4a90e2;
                             color: white;
                             border: none;
                             border-radius: 4px;
                             cursor: pointer;
                             text-decoration: none;
                             font-size: 14px;
                             transition: background-color 0.3s;
                         }
                         .add-btn:hover {
                             background-color: #3a7bc8;
                         }
                         .department-table {
                             width: 100%;
                             border-collapse: collapse;
                             background-color: white;
                             box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
                             border-radius: 4px;
                             overflow: hidden;
                         }
                         .department-table th, .department-table td {
                             padding: 15px;
                             text-align: left;
                             border-bottom: 1px solid #eee;
                         }
                         .department-table th {
                             background-color: #f8f9fa;
                             font-weight: 600;
                             color: #555;
                         }
                         .department-table tr:hover {
                             background-color: #f8f9fa;
                         }
                         .action-btn {
                             padding: 6px 12px;
                             margin-right: 5px;
                             border: none;
                             border-radius: 4px;
                             cursor: pointer;
                             font-size: 13px;
                             transition: all 0.3s;
                             text-decoration: none;
                             display: inline-block;
                         }
                         .view-btn {
                             background-color: #5cb85c;
                             color: white;
                         }
                         .view-btn:hover {
                             background-color: #4cae4c;
                         }
                         .edit-btn {
                             background-color: #f0ad4e;
                             color: white;
                         }
                         .edit-btn:hover {
                             background-color: #eea236;
                         }
                         .delete-btn {
                             background-color: #d9534f;
                             color: white;
                         }
                         .delete-btn:hover {
                             background-color: #d43f3a;
                         }
                         .logout {
                             text-align: right;
                             margin-top: 20px;
                         }
                         .logout a {
                             color: #777;
                             text-decoration: none;
                             font-size: 14px;
                         }
                         .logout a:hover {
                             color: #333;
                         }
                     </style>
                 </head>
                 <body>
                     <div class="container">
                         <div class="header">
                             <h1>部门列表</h1>
                 """);
        out.print(" <a href='"+rootPath+"/add' class='add-btn'>添加部门</a>");
        out.print("""
                    </div>
                
                         <table class="department-table">
                             <thead>
                                 <tr>
                                     <th>部门编号</th>
                                     <th>部门名称</th>
                                     <th>部门地理位置</th>
                                     <th>操作</th>
                                 </tr>
                             </thead>
                             <tbody>
                """);

        deptList.forEach(dept -> {
               out.print("<tr>");
               out.print("<td>"+dept.getDeptNo()+"</td>");
               out.print("<td>"+dept.getdName()+"</td>");
               out.print("<td>"+dept.getLoc()+"</td>");
               out.print("<td>");
               out.print("<a href='"+rootPath+"/detail?id="+dept.getDeptNo()+"' class='action-btn view-btn'>查看</a>"+" ");
               out.print("<a href='"+rootPath+"/edit?id="+dept.getDeptNo()+" ' class='action-btn edit-btn'>修改</a>"+" ");
               out.print("<a href='#' class='action-btn delete-btn' onclick='del("+dept.getDeptNo()+")'>删除</a>"+" ");
               out.print("</td>");
               out.print("</tr>");
        });



        out.println("""                          
                                      </tbody>
                               </table>
                               <div class="logout">
                                   <a href="">退出登录</a>
                               </div>
                           </div>
                       </body>
                       </html>
               """);


    }
}
