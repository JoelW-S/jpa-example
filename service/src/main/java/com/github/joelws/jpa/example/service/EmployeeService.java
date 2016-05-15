package com.github.joelws.jpa.example.service;


import com.github.joelws.jpa.example.model.Employee;

import javax.ws.rs.core.Response;
import java.util.List;


public interface EmployeeService {

    Response create(final Employee employee);

    Employee read(final Integer id);

    Response update(final Employee employee);

    Response delete(final Integer id);

    List<Employee> findAllByRole(final String role);


    List<Employee> findAllByDepartment(final Integer departmentId);

}
