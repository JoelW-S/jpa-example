package com.github.joelws.jpa.example.dao;

import com.github.joelws.jpa.example.models.Employee;

import java.util.List;

public interface EmployeeDao
{
    Employee create(Employee employee);

    Employee update(Employee employee);

    void delete(Integer id);

    Employee findById(Integer id);

    List<Employee> findAllByRole(String role);
}
