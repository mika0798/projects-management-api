package com.project.company_projects_management.service;

import com.project.company_projects_management.dao.EmployeeDAO;
import com.project.company_projects_management.entity.Employee;
import com.project.company_projects_management.exception.EmployeeNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDAO employeeDAO;

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeDAO.findById(id)
                .orElseThrow(
                        () -> new EmployeeNotFoundException("Cannot found employee with id " + id)
                );
    }

    @Transactional
    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeDAO.save(employee);
    }

    @Transactional
    @Override
    public void deleteEmployeeById(Long id) {
        employeeDAO.deleteById(id);
    }
}
