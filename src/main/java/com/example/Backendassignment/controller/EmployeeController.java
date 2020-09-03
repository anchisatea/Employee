package com.example.Backendassignment.controller;

import com.example.Backendassignment.DTO.EmployeeDTO;
import com.example.Backendassignment.service.EmployeeServiceImpl;
import com.example.Backendassignment.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/employees")
@CrossOrigin
public class EmployeeController {

    Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeServiceImpl.findAllEmployees();
    }


    @GetMapping("/{id}")
    public EmployeeDTO getAllEmployeesById(@PathVariable Long id) {
      log.info("Start getAllEmployeesById with :{}", id);
        return employeeServiceImpl.getEmployeesById(id);
    }

    @PostMapping
    public Employee postEmployeesById( @RequestBody Employee employee) {
        return employeeServiceImpl.saveEmployees(employee);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployeesById(@PathVariable Long id) {
        employeeServiceImpl.deleteEmployeesById(id);
        return "Deleted Successfully";
    }

    @PutMapping("/{id}")
    public String modifyEmployeesById(@PathVariable Long id, @RequestBody Employee employeeDetails){
        employeeServiceImpl.modifyEmployeesById(id, employeeDetails);
        return "Edited Successfully";
    }
}

