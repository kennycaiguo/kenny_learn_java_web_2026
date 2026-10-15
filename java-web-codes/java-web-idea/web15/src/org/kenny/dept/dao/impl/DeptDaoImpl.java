package org.kenny.dept.dao.impl;

import org.kenny.dept.dao.DeptDao;
import org.kenny.dept.entity.Dept;
import org.kenny.dept.util.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
/**
 * 注意：不应该在Dao里面关闭数据库连接对象，因为可能业务层还有调用。
 * */
public class DeptDaoImpl implements DeptDao {
    @Override
    public int insert(Dept dept) {
        String sql = "insert into dept(DEPTNO,DNAME,LOC) values(?,?,?)"; //使用预编译sql的方式
        int count=0;
        try(Connection conn = DbUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);) {
          //用传递进来的dept对象的属性给sql语句的对应的变量赋值
          ps.setInt(1, dept.getDeptNo());
          ps.setString(2, dept.getdName());
          ps.setString(3, dept.getLoc());
          count = ps.executeUpdate();
        } catch (Exception e) {
            //注意，这里不能只用e.printStackTrace()方法，因为正常来说，不应该在Dao中吞没异常
            //有异常应该往上抛给业务层
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return count;
    }

    @Override
    public int update(Dept newDept) {
        String sql = "update dept set DNAME=?,LOC=? where DEPTNO=?"; //使用预编译sql的方式
        int count=0;
        try(Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);) {
            //用传递进来的dept对象的属性给sql语句的对应的变量赋值
            ps.setString(1, newDept.getdName());
            ps.setString(2, newDept.getLoc());
            ps.setInt(3, newDept.getDeptNo());
            count = ps.executeUpdate();
        } catch (Exception e) {
            //注意，这里不能只用e.printStackTrace()方法，因为正常来说，不应该在Dao中吞没异常
            //有异常应该往上抛给业务层
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return count;
    }

    @Override
    public int deleteByNo(Integer deptNo) {
        String sql = "delete from dept where DEPTNO=?"; //使用预编译sql的方式
        int count=0;
        try(Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);) {
            //用传递进来的dept对象的属性给sql语句的对应的变量赋值
            ps.setInt(1, deptNo);
            count = ps.executeUpdate();
        } catch (Exception e) {
            //注意，这里不能只用e.printStackTrace()方法，因为正常来说，不应该在Dao中吞没异常
            //有异常应该往上抛给业务层
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return count;
    }

    @Override
    public List<Dept> selectAll() {
        String sql = "select * from dept"; //使用预编译sql的方式
        List<Dept> deptList = new LinkedList<Dept>();
        Dept dept = null;
        try(Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ) {
            //用传递进来的dept对象的属性给sql语句的对应的变量赋值
            while(rs.next()) {
                dept = new Dept(rs.getInt("DEPTNO"),rs.getString("DNAME"),rs.getString("LOC"));
                deptList.add(dept);
            }


        } catch (Exception e) {
            //注意，这里不能只用e.printStackTrace()方法，因为正常来说，不应该在Dao中吞没异常
            //有异常应该往上抛给业务层
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return deptList;

    }

    @Override
    public Dept selectByNo(Integer deptNo) {
        String sql = "select * from dept where DEPTNO=?"; //使用预编译sql的方式
        Dept dept = null;
        try(Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);) {
            //用传递进来的dept对象的属性给sql语句的对应的变量赋值
            ps.setInt(1,deptNo);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    dept = new Dept(rs.getInt("DEPTNO"),rs.getString("DNAME"),rs.getString("LOC"));
                }
            }

        } catch (Exception e) {
            //注意，这里不能只用e.printStackTrace()方法，因为正常来说，不应该在Dao中吞没异常
            //有异常应该往上抛给业务层
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return dept;
    }

    @Override
    public Integer selectMaxNo() {
        String sql = "select  max(DEPTNO) from dept";
        int deptno=0;
        try(Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ) {
            if(rs.next()) {
                deptno = rs.getInt(1);
            }
        } catch (Exception e) {
            //注意，这里不能只用e.printStackTrace()方法，因为正常来说，不应该在Dao中吞没异常
            //有异常应该往上抛给业务层
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return deptno;
    }
}
