package com.github.joelws.jpa.example.dao;

import com.github.joelws.jpa.example.model.Employee;

import javax.persistence.EntityManager;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao

{
    private EntityManager entityManager;


    public EmployeeDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Employee create(Employee employee) {
        entityManager.persist(employee);
        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        entityManager.merge(employee);
        return employee;
    }

    @Override
    public void delete(Integer id) {
        entityManager.remove(entityManager.getReference(Employee.class, id));
    }

    @Override
    public Employee findById(Integer id) {
        final String query = String.format("SELECT e FROM Employee e WHERE e.id = '%d'", id);
        return (Employee) entityManager.createQuery(query).getSingleResult();
    }

    @SuppressWarnings(value = "unchecked")
    @Override
    public List<Employee> findAllByRole(String role) {
        final String query = String.format("SELECT e FROM Employee e WHERE e.role = '%s'", role);
        return (List<Employee>) entityManager.createQuery(query).getResultList();
    }

    @SuppressWarnings(value = "unchecked")
    @Override
    public List<Employee> findAllByDepartment(Integer departmentId) {
        final String query = String.format("SELECT e FROM Employee e WHERE e.department = '%d'", departmentId);
        return (List<Employee>) entityManager.createQuery(query).getResultList();
    }

}
