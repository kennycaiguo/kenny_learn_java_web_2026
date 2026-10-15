package org.kenny.dept.entity;

/**
 * 这个类是用来封装数据库的一行数据的。
 * <p>
 * */
public class Dept {
    private int deptNo;
    private String dName;
    private String loc;

    //无参构造函数
    public Dept() {}
    //全参数构造函数
    public Dept(int deptNo, String dName, String loc) {
        this.deptNo = deptNo;
        this.dName = dName;
        this.loc = loc;
    }

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "Dept[deptNo=" + deptNo + ", dName=" + dName + ", loc=" + loc + "]";
    }
}
