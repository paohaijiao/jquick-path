package com.github.paohaijiao.obj;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ComplexPojo {

    private int id;
    private String name;
    private boolean active;

    // 包装类型属性
    private Integer age;
    private Double salary;
    private BigDecimal accountBalance;

    // 日期时间类型
    private LocalDate birthDate;
    private java.util.Date createdDate;

    // 集合类型
    private List<String> skills;
    private Map<String, Integer> skillLevels;
    private List<Address> addresses;

    // 枚举类型
    private Gender gender;

    // 自定义对象类型
    private Department department;
    private List<Project> currentProjects;

    // 数组类型
    private String[] certificates;




    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }

    public BigDecimal getAccountBalance() { return accountBalance; }
    public void setAccountBalance(BigDecimal accountBalance) { this.accountBalance = accountBalance; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public java.util.Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(java.util.Date createdDate) { this.createdDate = createdDate; }

    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }

    public Map<String, Integer> getSkillLevels() { return skillLevels; }
    public void setSkillLevels(Map<String, Integer> skillLevels) { this.skillLevels = skillLevels; }

    public List<Address> getAddresses() { return addresses; }
    public void setAddresses(List<Address> addresses) { this.addresses = addresses; }

    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public List<Project> getCurrentProjects() { return currentProjects; }
    public void setCurrentProjects(List<Project> currentProjects) { this.currentProjects = currentProjects; }

    public String[] getCertificates() { return certificates; }
    public void setCertificates(String[] certificates) { this.certificates = certificates; }
}




