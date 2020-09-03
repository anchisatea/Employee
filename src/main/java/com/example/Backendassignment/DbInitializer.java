package com.example.Backendassignment;

import com.example.Backendassignment.model.Employee;
import com.example.Backendassignment.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DbInitializer implements CommandLineRunner {
    private EmployeeRepository employeeRepository;
    public DbInitializer(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository ;

    }
    @Override
    public void run(String... args) throws Exception {
        Employee a1 = new Employee(01l,"Anchisa","Teachalikhidkul","Junior Backend Developer",
                "anchisa.tea@gmail.com", "+66970123754");
        Employee a2 = new Employee(02l, "Jitrin", "Panitdirake", "Junior Backend Developer", "jitrin.pan@gmail.com", "+66823351111");

        this.employeeRepository.deleteAll();
        List<Employee> employee = Arrays.asList(a1,a2);
        this.employeeRepository.saveAll(employee);

    }
}
