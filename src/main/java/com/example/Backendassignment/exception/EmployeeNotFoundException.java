package com.example.Backendassignment.exception;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
    public EmployeeNotFoundException()  {
        super();
    }
}
