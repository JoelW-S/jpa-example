package com.github.joelws.jpa.example.service;


import com.github.joelws.jpa.example.models.Employee;

import java.util.List;


public interface EmployeeService {

    Employee create(final String name,
                    final String role,
                    final long salary);

    Employee create(final Employee employee);

    void deleteById(final Integer id);

    void delete(final Employee employee);

    Employee findById(final Integer id);

    List<Employee> findAllByRole(final String role);

    Employee update(final Employee employee);


}
