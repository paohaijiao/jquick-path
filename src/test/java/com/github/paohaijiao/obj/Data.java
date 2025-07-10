package com.github.paohaijiao.obj;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Data {
    public static ComplexPojo getData() {
        ComplexPojo employee = new ComplexPojo();
        employee.setId(1001);
        employee.setName("张三");
        employee.setActive(true);

        employee.setAge(30);
        employee.setSalary(15000.50);
        employee.setAccountBalance(new BigDecimal("250000.75"));
        employee.setBirthDate(LocalDate.of(1993, 5, 15));
        employee.setCreatedDate(new Date()); // 当前日期时间

        employee.setSkills(Arrays.asList("Java", "Spring", "Hibernate", "MySQL"));

        Map<String, Integer> skillLevels = new HashMap<>();
        skillLevels.put("Java", 8);
        skillLevels.put("Spring", 7);
        skillLevels.put("Hibernate", 6);
        skillLevels.put("MySQL", 7);
        employee.setSkillLevels(skillLevels);

        Address homeAddress = new Address();
        homeAddress.setStreet("人民路123号");
        homeAddress.setCity("北京市");
        homeAddress.setPostalCode("100000");

        Address workAddress = new Address();
        workAddress.setStreet("科技园路456号");
        workAddress.setCity("北京市");
        workAddress.setPostalCode("100001");

        employee.setAddresses(Arrays.asList(homeAddress, workAddress));

        employee.setGender(Gender.MALE);

        Department dept = new Department();
        dept.setDeptId("DEPT_IT");
        dept.setDeptName("信息技术部");

        Employee manager = new Employee();
        manager.setEmployeeId("EMP_100");
        manager.setFullName("李四");
        dept.setManager(manager);

        employee.setDepartment(dept);

        Project project1 = new Project();
        project1.setProjectId("PROJ_2023_001");
        project1.setProjectName("企业ERP系统升级");
        project1.setStartDate(LocalDate.of(2023, 1, 10));
        project1.setEndDate(LocalDate.of(2023, 12, 31));

        Project project2 = new Project();
        project2.setProjectId("PROJ_2023_002");
        project2.setProjectName("移动端应用开发");
        project2.setStartDate(LocalDate.of(2023, 3, 15));
        project2.setEndDate(LocalDate.of(2023, 9, 30));

        employee.setCurrentProjects(Arrays.asList(project1, project2));

        // 设置数组类型 - 证书
        employee.setCertificates(new String[]{
                "Oracle Certified Professional Java Programmer",
                "AWS Certified Developer",
                "PMP Certification"
        });

        // 打印部分属性验证设置结果
        System.out.println("员工姓名: " + employee.getName());
        System.out.println("年龄: " + employee.getAge());
        System.out.println("技能: " + employee.getSkills());
        System.out.println("部门: " + employee.getDepartment().getDeptName());
        System.out.println("当前项目数: " + employee.getCurrentProjects().size());
        System.out.println("家庭地址: " + employee.getAddresses().get(0).getStreet() + ", " +
                employee.getAddresses().get(0).getCity());
        return employee;
    }
}
