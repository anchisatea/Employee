package com.example.Backendassignment.controller;

import com.example.Backendassignment.exception.EmployeeNotFoundException;
import com.example.Backendassignment.model.Employee;
import com.example.Backendassignment.repository.EmployeeRepository;
import com.example.Backendassignment.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    @Mock
    private EmployeeRepository  employeeRepository;

    private Employee employee;


    @BeforeEach
    void setup() {
//        ReflectionTestUtils.setField(employeeService, "employeeRepository", mockEmployeeRepository);
        employee = new Employee();
        employee.setId(1l);
        employee.setFirstname("Anchisa");
        employee.setLastname("Teachalikhidkul");
        employee.setPosition("Junior Backend Developer");
        employee.setEmail("anchisa.tea@gmail.com");
        employee.setPhone("+66970123754");
    }

    @Test
    public void test_null(){

        when(employeeRepository.findAll()).thenReturn(null);
        List<Employee> result = employeeServiceImpl.findAllEmployees();

        assertNull(result);

    }

    @Test
    public void test_get_all_employees_size_should_be_1(){

        doReturn(Arrays.asList(employee)).when(employeeRepository).findAll();
        List<Employee> result = employeeServiceImpl.findAllEmployees();

        assertEquals(1,result.size());
        verify(employeeRepository, times(1)).findAll();

    }


    @Test
    public void test_save_one_employee_should_not_null(){
        Employee employee = new Employee();
        employee.setId(1l);
        employee.setFirstname("Anchisa");
        employee.setLastname("Teachalikhidkul");
        employee.setPosition("Junior Backend Developer");
        employee.setEmail("anchisa.tea@gmail.com");
        employee.setPhone("+66970123754");

        doReturn(employee).when(employeeRepository).save(employee);
        Employee result = employeeServiceImpl.saveEmployees(employee);
        assertNotNull(result, "Employee details not saved");
        verify(employeeRepository,times(1)).save(employee);

    }

    @Test
    public void test_delete_employee() {

        doNothing().when(employeeRepository).deleteById(1l);
        employeeServiceImpl.deleteEmployeesById(1l);
//        doThrow(EmployeeNotFoundException.class).when(employeeRepository).deleteById(1l);
        verify(employeeRepository,times(1)).deleteById(1l);

    }

    @Test
    public void  test_delete_exception(){
        doThrow(EmptyResultDataAccessException.class).when(employeeRepository).deleteById(1l);
        assertThrows(EmployeeNotFoundException.class,() -> employeeServiceImpl.deleteEmployeesById(1l));
    }

    @Test
    public void test_empty_list_should_throws_exception(){
        doReturn(Optional.empty()).when(employeeRepository).findById(1l);
        assertThrows(EmployeeNotFoundException.class,() -> employeeServiceImpl.getEmployeesById(1l));
        verify(employeeRepository).findById(1l);
    }


    @Test
    public void test_modify_employee (){
        Employee employee = new Employee();
        employee.setId(1l);
        employee.setFirstname("Anchisa");

//        doReturn(Optional.of(employee)).when(employeeRepository.findById(1l));
        when(employeeRepository.findById(1l)).thenReturn(Optional.of(employee));

        Employee update = new Employee();
        update.setId(1l);
        update.setFirstname("Jitrin");

//        doReturn(employee).when(employeeRepository.save(employee));
        when(employeeRepository.save(employee)).thenReturn(employee);
        Employee update2 = employeeServiceImpl.modifyEmployeesById(1l,update);

        assertEquals(employee.getFirstname(),update2.getFirstname());

    }

    @Test
    public void test_get_one_employee_should_not_null(){
        Employee employee = new Employee();
        employee.setId(1l);
        employee.setFirstname("Anchisa");
        employee.setLastname("Teachalikhidkul");
        employee.setPosition("Junior Backend Developer");
        employee.setEmail("anchisa.tea@gmail.com");
        employee.setPhone("+66970123754");

        doReturn(Optional.of(employee)).when(employeeRepository).findById(1l);
        Employee result = employeeServiceImpl.getEmployeesById(1l);
        assertNotNull(result, "Employee not found");
        verify(employeeRepository,times(1)).findById(1l);
    }




}
