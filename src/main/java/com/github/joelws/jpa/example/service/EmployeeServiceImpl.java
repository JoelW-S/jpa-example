package com.github.joelws.jpa.example.service;


import com.github.joelws.jpa.example.dao.EmployeeDao;
import com.github.joelws.jpa.example.models.Employee;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private static Logger LOGGER = Logger.getLogger(EmployeeServiceImpl.class);

    private EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public Employee create(String name, String role, long salary) {
        Employee employee = new Employee(name, role, salary);
        try {
            LOGGER.info("Creating employee: " + employee.getName());
            employeeDao.create(employee);
        } catch (final DataAccessException e) {

            LOGGER.error("Failed to create employee: " +
                    e.getMessage());

            LOGGER.error("Typically we should throw an exception which higher level needs to catch");

            employee = null;
        }
        return employee;
    }

    @Override
    public Employee create(Employee employee) {
        if (employee != null) {
            try {
                LOGGER.info("Creating employee: " + employee.getName());
                return employeeDao.create(employee);
            } catch (final DataAccessException e) {

                LOGGER.error("Failed to create employee: " +
                        e.getMessage());

                LOGGER.error("Typically we should throw an exception which higher level needs to catch");
            }
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        try {
            LOGGER.info("Deleting employee by id: " + id);
            employeeDao.delete(id);
        } catch (final DataAccessException e) {
            LOGGER.error("Failed to delete employee: " +
                    e.getMessage());

            LOGGER.error("Typically we should throw an exception which higher level needs to catch");
        }
    }

    @Override
    public void delete(Employee employee) {
        if (employee != null) {
            try {
                LOGGER.info("Deleting employee: " + employee.getName());
                employeeDao.delete(employee.getId());
            } catch (final DataAccessException e) {
                LOGGER.error("Failed to delete employee: " +
                        e.getMessage());
                LOGGER.error("Typically we should throw an exception which higher level needs to catch");
            }
        } else {
            // throw new BusinessException
        }

    }

    @Override
    public Employee findById(Integer id) {
        try {
            return employeeDao.findById(id);
        } catch (final DataAccessException e) {
            LOGGER.error("Failed to retrieve employee: " +
                    e.getMessage());
            LOGGER.error("Typically we should throw an exception which higher level needs to catch");
        }
        return null;
    }

    @Override
    public List<Employee> findAllByRole(String role) {
        try {
            LOGGER.info("Finding all employees with role: " + role);
            return employeeDao.findAllByRole(role);
        } catch (final DataAccessException e) {
            LOGGER.error("Failed to retrieve employees: " +
                    e.getMessage());
            LOGGER.error("Typically we should throw an exception which higher level needs to catch");
        }
        return null;
    }

    @Override
    public Employee update(Employee employee) {
        if (employee != null) {
            try {
                LOGGER.info("Updating employee: " + employee.getName());
                return employeeDao.update(employee);
            } catch (final DataAccessException e) {
                LOGGER.error("Failed to update employee: " +
                        e.getMessage());
                LOGGER.error("Typically we should throw an exception which higher level needs to catch");
            }
        }
        return null;
    }

}
