package com.employee.controller;

import com.employee.model.Employee;
import com.employee.model.Department;
import com.employee.projection.EmployeeNameEmail;
import com.employee.projection.EmployeeSummary;
import com.employee.service.EmployeeService;
import com.employee.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Exercise 4: REST Controller for Employee CRUD operations.
 * Exercise 6: Pagination and Sorting endpoints.
 * Exercise 8: Projection endpoints.
 */
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    // Exercise 4: CRUD Operations
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        return employeeService.getEmployeeById(id)
                .map(employee -> {
                    employee.setName(employeeDetails.getName());
                    employee.setEmail(employeeDetails.getEmail());
                    if (employeeDetails.getDepartment() != null) {
                        employee.setDepartment(employeeDetails.getDepartment());
                    }
                    return ResponseEntity.ok(employeeService.saveEmployee(employee));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(employee -> {
                    employeeService.deleteEmployee(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Exercise 5: Query Methods
    @GetMapping("/search")
    public List<Employee> searchEmployees(@RequestParam String keyword) {
        return employeeService.searchByKeyword(keyword);
    }

    @GetMapping("/department/{deptName}")
    public List<Employee> getByDepartmentName(@PathVariable String deptName) {
        return employeeService.findByDepartmentName(deptName);
    }

    // Exercise 6: Pagination and Sorting
    @GetMapping("/paginated")
    public Page<Employee> getEmployeesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return employeeService.getEmployeesPaginated(pageable);
    }

    // Exercise 8: Projections
    @GetMapping("/names-emails")
    public List<EmployeeNameEmail> getEmployeeNamesAndEmails() {
        return employeeService.getEmployeeNamesAndEmails();
    }

    @GetMapping("/summaries")
    public List<EmployeeSummary> getEmployeeSummaries() {
        return employeeService.getEmployeeSummaries();
    }

    // Exercise 10: Batch processing
    @PostMapping("/batch")
    public List<Employee> batchCreateEmployees(@RequestBody List<Employee> employees) {
        return employeeService.saveAllEmployees(employees);
    }
}
