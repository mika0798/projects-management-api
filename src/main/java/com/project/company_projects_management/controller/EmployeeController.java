package com.project.company_projects_management.controller;

import com.project.company_projects_management.entity.Employee;
import com.project.company_projects_management.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name="Employee Rest API Endpoints", description = "Operations related to employees")
@Controller
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @Operation(summary = "Get all employees",description = "Retrieve list of all employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    @Operation(summary="Get an employee by Id", description = "Retrieve an employee by their Id")
    public ResponseEntity<Employee> getEmployeeById(
                            @Parameter(description = "Id of employee to be retrieved")
                            @PathVariable @Min(value = 1) Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }
}
