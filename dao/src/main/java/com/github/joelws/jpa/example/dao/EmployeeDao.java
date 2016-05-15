package com.github.joelws.jpa.example.dao;

import com.github.joelws.jpa.example.model.Employee;

import java.util.List;

public interface EmployeeDao
{
    Employee create(final Employee employee);

    Employee update(final Employee employee);

    void delete(final Integer id);

    Employee findById(final Integer id);

    List<Employee> findAllByRole(final String role);

    List<Employee> findAllByDepartment(final Integer departmentId);

}
