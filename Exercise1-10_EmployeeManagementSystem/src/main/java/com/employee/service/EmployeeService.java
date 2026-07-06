package com.employee.service;

import com.employee.model.Employee;
import com.employee.projection.EmployeeNameEmail;
import com.employee.projection.EmployeeSummary;
import com.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Exercise 4: Service layer for Employee CRUD operations.
 * Exercise 10: Batch processing support via @Transactional.
 */
@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> findByDepartment(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }

    public List<Employee> findByDepartmentName(String deptName) {
        return employeeRepository.findEmployeesByDepartmentName(deptName);
    }

    public List<Employee> searchByKeyword(String keyword) {
        return employeeRepository.searchByKeyword(keyword);
    }

    // Exercise 6: Pagination
    public Page<Employee> getEmployeesPaginated(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public Page<Employee> searchByNamePaginated(String name, Pageable pageable) {
        return employeeRepository.findByNameContaining(name, pageable);
    }

    // Exercise 8: Projections
    public List<EmployeeNameEmail> getEmployeeNamesAndEmails() {
        return employeeRepository.findAllProjectedBy();
    }

    public List<EmployeeSummary> getEmployeeSummaries() {
        return employeeRepository.findEmployeeSummaries();
    }

    // Exercise 10: Batch processing
    public List<Employee> saveAllEmployees(List<Employee> employees) {
        return employeeRepository.saveAll(employees);
    }
}
