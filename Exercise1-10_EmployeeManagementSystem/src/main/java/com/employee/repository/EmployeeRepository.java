package com.employee.repository;

import com.employee.model.Employee;
import com.employee.projection.EmployeeNameEmail;
import com.employee.projection.EmployeeSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Exercise 3: EmployeeRepository extending JpaRepository.
 * Exercise 5: Custom query methods with @Query and NamedQueries.
 * Exercise 6: Pagination and sorting support.
 * Exercise 8: Projections.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Exercise 3 & 5: Derived query methods
    List<Employee> findByName(String name);

    List<Employee> findByNameContaining(String name);

    List<Employee> findByDepartmentId(Long departmentId);

    List<Employee> findByDepartmentName(String departmentName);

    List<Employee> findByEmailContaining(String email);

    List<Employee> findByNameStartingWith(String prefix);

    List<Employee> findByNameOrderByNameAsc(String name);

    // Exercise 5: Custom query with @Query annotation
    @Query("SELECT e FROM Employee e WHERE e.department.name = :deptName")
    List<Employee> findEmployeesByDepartmentName(@Param("deptName") String deptName);

    @Query("SELECT e FROM Employee e WHERE e.name LIKE %:keyword% OR e.email LIKE %:keyword%")
    List<Employee> searchByKeyword(@Param("keyword") String keyword);

    // Exercise 5: Native Query
    @Query(value = "SELECT * FROM employees WHERE department_id = :deptId", nativeQuery = true)
    List<Employee> findByDepartmentIdNative(@Param("deptId") Long deptId);

    // Exercise 6: Pagination support
    Page<Employee> findByDepartmentId(Long departmentId, Pageable pageable);

    Page<Employee> findByNameContaining(String name, Pageable pageable);

    // Exercise 8: Projections - interface-based
    List<EmployeeNameEmail> findAllProjectedBy();

    // Exercise 8: Projections - class-based
    @Query("SELECT new com.employee.projection.EmployeeSummary(e.id, e.name, e.department.name) FROM Employee e")
    List<EmployeeSummary> findEmployeeSummaries();
}
