package com.employee.projection;

/**
 * Exercise 8: Class-based projection (DTO) for Employee.
 */
public class EmployeeSummary {
    private Long id;
    private String name;
    private String departmentName;

    public EmployeeSummary(Long id, String name, String departmentName) {
        this.id = id;
        this.name = name;
        this.departmentName = departmentName;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDepartmentName() { return departmentName; }

    @Override
    public String toString() {
        return "EmployeeSummary{id=" + id + ", name='" + name + "', department='" + departmentName + "'}";
    }
}
