package com.project.company_projects_management.service;

import com.project.company_projects_management.dao.EmployeeDAO;
import com.project.company_projects_management.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDAO employeeDAO;

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {}

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDAO.findAll();
    }
}
