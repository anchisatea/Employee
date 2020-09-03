package com.example.Backendassignment.service;

import com.example.Backendassignment.DTO.EmployeeDTO;
import com.example.Backendassignment.exception.EmployeeNotFoundException;
import com.example.Backendassignment.model.Employee;
import com.example.Backendassignment.repository.EmployeeRepository;
import org.apache.catalina.mapper.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private EmployeeRepository employeeRepository;

    @Autowired
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public EmployeeDTO getEmployeesById(Long id) {
//        return employeeRepository.findById(new Long(id)).orElseThrow(() -> new EmployeeNotFoundException());
        Optional<Employee> employeeOptional = employeeRepository.findById(new Long(id)).orElseThrow(() -> new EmployeeNotFoundException());
        return employeeOptional.isPresent() ? Mapper.map(employeeOptional.get(), EmployeeDTO) : null;
    }

    public Employee saveEmployees(Employee employee){
        return employeeRepository.save(employee);
    }

    public void deleteEmployeesById(Long id) {
        try {
            employeeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EmployeeNotFoundException();
        }
    }

    public Employee modifyEmployeesById(Long id, Employee employeeDetails){
        log.debug("id : " + id);
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException());

        employee.setFirstname(employeeDetails.getFirstname());
        employee.setLastname((employeeDetails.getLastname()));
        employee.setPosition((employeeDetails.getPosition()));
        employee.setEmail((employeeDetails.getEmail()));
        employee.setPhone((employeeDetails.getPhone()));

        employeeRepository.save(employee);
        return employee;
    }

}
