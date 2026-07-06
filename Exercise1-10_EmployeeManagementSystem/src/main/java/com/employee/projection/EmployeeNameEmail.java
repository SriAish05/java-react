package com.employee.projection;

/**
 * Exercise 8: Interface-based projection for Employee.
 * Only fetches name and email fields.
 */
public interface EmployeeNameEmail {
    String getName();
    String getEmail();
}
