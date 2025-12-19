package com.project.company_projects_management.service;

import com.project.company_projects_management.dto.EmployeeRequest;
import com.project.company_projects_management.entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    Employee saveEmployee(Employee employee);
    void deleteEmployeeById(Long id);
    Employee convertEmployee(Long id, EmployeeRequest emRequest);
    Employee patchEmployee(Map<String,Object> patchPayload, Employee employee);
}
