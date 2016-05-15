package com.github.joelws.jpa.example.service;


import com.github.joelws.jpa.example.model.Department;

import javax.ws.rs.core.Response;

public interface DepartmentService {

    Response create(final Department department);

    Department read(final Integer departmentId);

    Response update(final Department department);

    Response delete(final Integer departmentId);

}
