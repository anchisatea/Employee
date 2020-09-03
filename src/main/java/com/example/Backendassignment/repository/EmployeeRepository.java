package com.example.Backendassignment.repository;

import com.example.Backendassignment.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//     Optional<Employee> findById(Long id);
//     void deleteById(Long id);

}


