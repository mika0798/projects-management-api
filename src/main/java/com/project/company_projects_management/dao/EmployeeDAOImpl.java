package com.project.company_projects_management.dao;

import com.project.company_projects_management.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;


public class EmployeeDAOImpl implements EmployeeDAO {

    private EntityManager entityManager;


    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);
        List<Employee> employees = theQuery.getResultList();
        return employees;
    }

    @Override
    public Optional<Employee> findById(Long id) {
        Employee result = entityManager.find(Employee.class, id);
        return Optional.of(result);
    }

    @Override
    public Employee save(Employee employee) {
        return entityManager.merge(employee);
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(Employee.class, id));
    }
}
