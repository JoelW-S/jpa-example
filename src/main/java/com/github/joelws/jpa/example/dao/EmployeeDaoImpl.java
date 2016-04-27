package com.github.joelws.jpa.example.dao;

import com.github.joelws.jpa.example.models.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao
{
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;
    private Class<Employee> employeeClass;

    public EmployeeDaoImpl(EntityManager entityManager, EntityTransaction entityTransaction)
    {
        this.employeeClass = Employee.class;
        this.entityManager = entityManager;
        this.entityTransaction = entityTransaction;
    }

    public Employee create(Employee employee)
    {
        entityTransaction.begin();
        entityManager.persist(employee);
        entityTransaction.commit();
        return employee;
    }

    public Employee update(Employee employee)
    {   entityTransaction.begin();
        entityManager.merge(employee);
        entityTransaction.commit();
        return employee;
    }

    public void delete(Integer id)
    {   entityTransaction.begin();
        entityManager.remove(entityManager.getReference(employeeClass, id));
        entityTransaction.commit();
    }

    public Employee findById(Integer id)
    {
        final String query = String.format("SELECT e FROM Employee e WHERE e.id = '%d'",id);
        return (Employee) entityManager.createQuery(query).getSingleResult();
    }

    @SuppressWarnings(value = "unchecked")
    public List<Employee> findAllByRole(String role)
    {
        final String query = String.format("SELECT e FROM Employee e WHERE e.role = '%s'",role);
        return (List<Employee>) entityManager.createQuery(query).getResultList();
    }
}
