package org.kenny.dept.dao;

import org.kenny.dept.entity.Dept;

import java.util.List;

/**
 * 专门完成增删改查的Dao接口
 * 使用Dao接口的好处是然Dao层和业务层解耦
 * 在Dao接口在不能定义业务代码，只能是crud操作
 * */
public interface DeptDao {
     //新增（保存部门）
     int insert(Dept dept);
     //修改部门信息
     int update(Dept newDept);
     //根据部门编号删除部门
     int deleteByNo(Integer deptNo);
     //获取所有部门数据
     List<Dept> selectAll();
     //根据部门编号查找部门
     Dept selectByNo(Integer deptNo);
     //查找最大的部门编号方法
     //本阶段，这里添加一个找到读取最大的deptNo的方法，方便我们加1计算新记录的deptNo，不过这个会有并发问题，这里暂时不考虑并发
     //我们的数据库里面DEPTNO没有设置自增长
     Integer selectMaxNo();
}
