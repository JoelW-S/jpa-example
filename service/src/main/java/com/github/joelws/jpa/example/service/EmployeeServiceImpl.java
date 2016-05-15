package com.github.joelws.jpa.example.service;

import com.github.joelws.jpa.example.dao.EmployeeDao;
import com.github.joelws.jpa.example.model.Employee;
import org.apache.log4j.Logger;

import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/employees")
public class EmployeeServiceImpl implements EmployeeService {

    private static Logger LOGGER = Logger.getLogger(EmployeeServiceImpl.class);

    private EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response create(Employee employee) {
        if (employee != null) {
            try {
                LOGGER.info("Creating employee: " + employee.getName());
                employeeDao.create(employee);
            } catch (final PersistenceException e) {

                LOGGER.error("Failed to create employee: " +
                        e.getMessage());

                LOGGER.error("Typically we should throw an exception which higher level needs to catch");
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
        return Response.ok(employee).build();
    }

    @DELETE
    @Path("/{id}")
    @Override
    public Response delete(@PathParam("id") Integer id) {
        try {
            LOGGER.info("Deleting employee by id: " + id);
            employeeDao.delete(id);
        } catch (final PersistenceException e) {
            LOGGER.error("Failed to delete employee: " +
                    e.getMessage());

            LOGGER.error("Typically we should throw an exception which higher level needs to catch");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Employee read(@PathParam("id") Integer id) {
        try {
            return employeeDao.findById(id);
        } catch (final PersistenceException e) {
            LOGGER.error("Failed to retrieve employee: " +
                    e.getMessage());
            LOGGER.error("Typically we should throw an exception which higher level needs to catch");
        }
        return null;
    }

    @GET
    @Path("/role/{role}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<Employee> findAllByRole(@PathParam("role") String role) {
        try {
            LOGGER.info("Finding all employees with role: " + role);
            return employeeDao.findAllByRole(role);
        } catch (final PersistenceException e) {
            LOGGER.error("Failed to retrieve employees: " +
                    e.getMessage());
            LOGGER.error("Typically we should throw an exception which higher level needs to catch");
        }
        return null;
    }

    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response update(Employee employee) {
        if (employee != null) {
            try {
                LOGGER.info("Updating employee: " + employee.getName());
                employeeDao.update(employee);
            } catch (final PersistenceException e) {
                LOGGER.error("Failed to update employee: " +
                        e.getMessage());
                LOGGER.error("Typically we should throw an exception which higher level needs to catch");
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
        return Response.ok(employee).build();
    }

    @GET
    @Path("/department/{department}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<Employee> findAllByDepartment(@PathParam("department") Integer departmentId) {
        try {
            LOGGER.info("Finding all employees with department id: " + departmentId);
            return employeeDao.findAllByDepartment(departmentId);
        } catch (final PersistenceException e) {
            LOGGER.error("Failed to find employees, cause: " + e.getMessage());
            return null;
        }
    }


}
