package com.example.Backendassignment.service;

import com.example.Backendassignment.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAllEmployees();
    Employee getEmployeesById(Long id);

    Employee saveEmployees(Employee employee);

    void deleteEmployeesById(Long id);

    Employee modifyEmployeesById(Long id, Employee employeeDetails);


}
