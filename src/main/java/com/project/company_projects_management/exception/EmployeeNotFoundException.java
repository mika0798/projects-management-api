package com.project.company_projects_management.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message) {
        super(message);
    }

    public EmployeeNotFoundException(Throwable cause) {super(cause);}

    public EmployeeNotFoundException(String message, Throwable cause) {super(message, cause);}
}
