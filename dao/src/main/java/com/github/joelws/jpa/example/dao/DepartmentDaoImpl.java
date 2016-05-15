package com.github.joelws.jpa.example.dao;


import com.github.joelws.jpa.example.model.Department;

import javax.persistence.EntityManager;

public class DepartmentDaoImpl implements DepartmentDao {


    private EntityManager entityManager;

    public DepartmentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Department create(Department department) {
        entityManager.persist(department);
        return department;

    }

    @Override
    public Department update(Department department) {
        entityManager.merge(department);
        return department;
    }

    @Override
    public void delete(Integer departmentId) {
        entityManager.remove(entityManager.getReference(Department.class, departmentId));
    }

    @Override
    public Department findById(Integer departmentId) {
        String query = String.format("Select d FROM Department d WHERE d.id = '%d'", departmentId);
        return (Department) entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public Department findByName(String departmentName) {
        String query = String.format("Select d FROM Department d WHERE d.name = '%s'", departmentName);
        return (Department) entityManager.createQuery(query).getSingleResult();
    }
}
