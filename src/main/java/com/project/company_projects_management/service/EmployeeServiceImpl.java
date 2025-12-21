package com.project.company_projects_management.service;

import com.project.company_projects_management.dto.EmployeeRequest;
import com.project.company_projects_management.entity.Employee;
import com.project.company_projects_management.exception.EmployeeNotFoundException;
import com.project.company_projects_management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.node.ObjectNode;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private JsonMapper jsonMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,  JsonMapper jsonMapper) {
        this.employeeRepository = employeeRepository;
        this.jsonMapper = jsonMapper;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(
                        () -> new EmployeeNotFoundException("Cannot find employee with id " + id)
                );
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        if((employeeRepository.findById(id)).isEmpty()) {
            throw new EmployeeNotFoundException("Cannot find employee with id " + id);
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee convertEmployee(Long id, EmployeeRequest emRequest) {
        Employee employee = new Employee();
        if (id > 0) {
            employee.setId(id);
        }
        employee.setFirstName(emRequest.getFirstName());
        employee.setLastName(emRequest.getLastName());
        employee.setEmail(emRequest.getEmail());
        return employee;
    }

    @Override
    public Employee patchEmployee(Map<String,Object> patchPayload, Employee employee) {
        ObjectNode patchNode = jsonMapper.convertValue(patchPayload,ObjectNode.class);
        ObjectNode employeeNode = jsonMapper.convertValue(employee,ObjectNode.class);

        employeeNode.setAll(patchNode);
        return jsonMapper.convertValue(employeeNode,Employee.class);
    }
}
