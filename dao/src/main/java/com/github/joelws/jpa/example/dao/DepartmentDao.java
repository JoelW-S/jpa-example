package com.github.joelws.jpa.example.dao;


import com.github.joelws.jpa.example.model.Department;

public interface DepartmentDao {

    Department create(final Department department);

    Department update(final Department department);

    void delete(final Integer departmentId);

    Department findById(final Integer departmentId);

    Department findByName(final String departmentName);
}
