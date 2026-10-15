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
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        //获取项目根路径，这里是/dept/,方便动态路径拼接
        String rootPath = req.getContextPath(); //HttpServletRequest也可以获取到
        out.print("""
                <!DOCTYPE html>
                <html lang="zh-CN">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>部门管理系统 - 部门详情</title>
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
                            max-width: 800px;
                            margin: 30px auto;
                            padding: 30px;
                            background-color: white;
                            border-radius: 8px;
                            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
                        }
                        .header {
                            display: flex;
                            justify-content: space-between;
                            align-items: center;
                            margin-bottom: 30px;
                            padding-bottom: 15px;
                            border-bottom: 1px solid #eee;
                        }
                        .header h1 {
                            color: #333;
                            font-size: 24px;
                        }
                        .back-btn {
                            padding: 8px 16px;
                            background-color: #6c757d;
                            color: white;
                            border: none;
                            border-radius: 4px;
                            cursor: pointer;
                            text-decoration: none;
                            font-size: 14px;
                            transition: background-color 0.3s;
                        }
                        .back-btn:hover {
                            background-color: #5a6268;
                        }
                        .detail-card {
                            padding: 20px;
                            border-radius: 6px;
                            background-color: #f8f9fa;
                        }
                        .detail-row {
                            display: flex;
                            margin-bottom: 15px;
                            padding-bottom: 15px;
                            border-bottom: 1px solid #e9ecef;
                        }
                        .detail-row:last-child {
                            margin-bottom: 0;
                            padding-bottom: 0;
                            border-bottom: none;
                        }
                        .detail-label {
                            width: 150px;
                            font-weight: 600;
                            color: #495057;
                        }
                        .detail-value {
                            flex: 1;
                            color: #212529;
                        }
                        .action-btns {
                            margin-top: 30px;
                            text-align: right;
                        }
                        .edit-btn {
                            padding: 10px 20px;
                            background-color: #f0ad4e;
                            color: white;
                            border: none;
                            border-radius: 4px;
                            cursor: pointer;
                            text-decoration: none;
                            font-size: 14px;
                            transition: background-color 0.3s;
                        }
                        .edit-btn:hover {
                            background-color: #eea236;
                        }
                    </style>
                </head>
                <body>
                """);
        out.print("""
                 <div class="container">
                        <div class="header">
                            <h1>部门详细信息</h1>""");
        out.print("<a href='"+rootPath+"/list' class='back-btn'>返回列表</a>");
        out.print("</div>");


        out.print("""
                <div class="detail-card">
                <div class="detail-row">
                <div class="detail-label">部门编号</div>
               """);
        out.print("<div class='detail-value'>"+dept.getDeptNo()+"</div></div>");
        out.print("""
                <div class="detail-row">
                <div class="detail-label">部门名称</div>
               """);
        out.print("<div class='detail-value'>"+dept.getdName()+"</div></div>");
        out.print("""
                <div class="detail-row">
                <div class="detail-label">部门地理位置</div>
               """);
        out.print("<div class='detail-value'>"+dept.getLoc()+"</div></div>");
        out.print("""
                <div class="action-btns">""");
        out.print(" <a href='"+rootPath +"/edit?id="+dept.getDeptNo()+"' class='edit-btn'>编辑部门信息</a>");
        out.print("""
                 </div>
                    </div>
                </body>
                </html>
          """) ;


    }
}
