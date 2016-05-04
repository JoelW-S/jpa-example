package com.github.joelws.jpa.example.dao;

import com.github.joelws.jpa.example.models.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao

{
    @PersistenceContext
    private EntityManager entityManager;


    public EmployeeDaoImpl() {
    }

    @Transactional
    public Employee create(Employee employee) {
        entityManager.persist(employee);
        return employee;
    }

    @Transactional
    public Employee update(Employee employee) {
        entityManager.merge(employee);
        return employee;
    }

    @Transactional
    public void delete(Integer id) {
        entityManager.remove(entityManager.getReference(Employee.class, id));
    }

    public Employee findById(Integer id) {
        final String query = String.format("SELECT e FROM Employee e WHERE e.id = '%d'", id);
        return (Employee) entityManager.createQuery(query).getSingleResult();
    }

    @SuppressWarnings(value = "unchecked")
    public List<Employee> findAllByRole(String role) {
        final String query = String.format("SELECT e FROM Employee e WHERE e.role = '%s'", role);
        return (List<Employee>) entityManager.createQuery(query).getResultList();
    }

}
