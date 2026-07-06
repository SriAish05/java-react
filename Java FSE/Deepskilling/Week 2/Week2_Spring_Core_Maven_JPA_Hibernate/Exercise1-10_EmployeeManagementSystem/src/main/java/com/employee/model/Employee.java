package com.employee.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * Exercise 2: Employee entity mapped to database table.
 * Exercise 7: Entity auditing with @CreatedDate, @LastModifiedDate, etc.
 * Exercise 5: Named queries defined with @NamedQuery.
 * Exercise 10: Hibernate-specific annotations.
 */
@Entity
@Table(name = "employees")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name = "Employee.findByNameContaining",
        query = "SELECT e FROM Employee e WHERE e.name LIKE %:name%")
@NamedQuery(name = "Employee.findByDepartmentName",
        query = "SELECT e FROM Employee e WHERE e.department.name = :deptName")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(exclude = "department")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    // Exercise 2: Many-to-one relationship with Department
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    @JsonIgnoreProperties("employees")
    private Department department;

    // Exercise 7: Auditing fields
    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    public Employee(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
