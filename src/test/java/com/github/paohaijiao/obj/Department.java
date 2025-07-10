package com.github.paohaijiao.obj;

public class Department {
    private String deptId;
    private String deptName;
    private Employee manager;

    public String getDeptId() { return deptId; }
    public void setDeptId(String deptId) { this.deptId = deptId; }

    public String getDeptName() { return deptName; }
    public void setDeptName(String deptName) { this.deptName = deptName; }

    public Employee getManager() { return manager; }
    public void setManager(Employee manager) { this.manager = manager; }
}
