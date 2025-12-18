package com.project.company_projects_management.controller;

import com.project.company_projects_management.dto.EmployeeRequest;
import com.project.company_projects_management.entity.Employee;
import com.project.company_projects_management.exception.EmployeeNotFoundException;
import com.project.company_projects_management.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.node.ObjectNode;

import java.util.List;
import java.util.Map;

@Tag(name="Employee Rest API Endpoints", description="Operations related to employees")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;
    private JsonMapper jsonMapper;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService, JsonMapper jsonMapper) {
        this.employeeService = employeeService;
        this.jsonMapper = jsonMapper;
    }

    @GetMapping
    @Operation(summary="Get all employees",description="Retrieve list of all employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    @Operation(summary="Get an employee by Id", description="Retrieve an employee by their Id")
    public ResponseEntity<Employee> getEmployeeById(
                            @Parameter(description = "Id of employee to be retrieved")
                            @PathVariable @Min(value = 1) Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PostMapping
    @Operation(summary="Creat new employee",description="Add an employee to the database")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        Employee newEmployee = convertEmployee(0L,employeeRequest);
        Employee savedEmployee = employeeService.saveEmployee(newEmployee);
        return new ResponseEntity<>(savedEmployee,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary="Update an employee",description="Update details of an employee")
    public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody EmployeeRequest employeeRequest,
                                                   @Parameter(description = "Id of employee needs to be updated")
                                                   @PathVariable
                                                   @Min(value=1) Long id) {
        try {
            employeeService.getEmployeeById(id);
        } catch (EmployeeNotFoundException e) {
            System.err.println("Employee not found");
        }

        Employee convertEmployee = convertEmployee(id,employeeRequest);
        Employee updatedEmployee = employeeService.saveEmployee(convertEmployee);
        return new ResponseEntity<>(updatedEmployee,HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary="Update partly an employee",description="Update only some details of an employee")
    public ResponseEntity<Employee> updatePartly(@PathVariable @Min(value=1) Long id,
                                                 @RequestBody Map<String,Object> patchPayload) {
        if (patchPayload.containsKey("id")) {
            throw new RuntimeException("Id is not allowed in Request Body");
        }
        Employee tempEmployee = employeeService.getEmployeeById(id);
        Employee patchedEmployee = patchEmployee(patchPayload,tempEmployee);
        return new ResponseEntity<>(employeeService.saveEmployee(patchedEmployee),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Remove an employee",description="Delete an employee")
    public ResponseEntity<Void> deleteEmployee(
            @Parameter(description="Id of the employee you want to delete")
            @PathVariable @Min(value=1) Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }

    private Employee convertEmployee(Long id, EmployeeRequest emRequest) {
        Employee employee = new Employee();
        if (id > 0) {
            employee.setId(id);
        }
        employee.setFirstName(emRequest.getFirstName());
        employee.setLastName(emRequest.getLastName());
        employee.setEmail(emRequest.getEmail());
        return employee;
    }

    private Employee patchEmployee(Map<String,Object> patchPayload, Employee employee) {
        ObjectNode patchNode = jsonMapper.convertValue(patchPayload,ObjectNode.class);
        ObjectNode employeeNode = jsonMapper.convertValue(employee,ObjectNode.class);

        employeeNode.setAll(patchNode);
        return jsonMapper.convertValue(employeeNode,Employee.class);
    }

}
