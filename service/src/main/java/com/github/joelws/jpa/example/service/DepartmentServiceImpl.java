package com.github.joelws.jpa.example.service;


import com.github.joelws.jpa.example.dao.DepartmentDao;
import com.github.joelws.jpa.example.model.Department;
import org.apache.log4j.Logger;

import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/departments")
public class DepartmentServiceImpl implements DepartmentService {
    private static final Logger LOGGER = Logger.getLogger(DepartmentServiceImpl.class);

    private DepartmentDao departmentDao;

    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public Response create(Department department) {
        if (department != null) {
            try {
                LOGGER.info("Creating department: " + department.getName());
                departmentDao.create(department);
                return Response.ok(department).build();
            } catch (final PersistenceException e) {
                LOGGER.error("Failed to create department: " + e.getMessage());
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Path("/{departmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Department read(@PathParam("departmentId") Integer departmentId) {
        try {
            LOGGER.info("Finding department: " + departmentId);
            return departmentDao.findById(departmentId);
        } catch (final PersistenceException e) {
            LOGGER.error("Failed to retrieve department: " + e.getMessage());
            return null;
        }
    }

    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public Response update(Department department) {
        if (department != null) {
            try {
                LOGGER.info("Updating department: " + department.getId());
                return Response.ok(department).build();
            } catch (final PersistenceException e) {
                LOGGER.error("Failed to update department: " + e.getMessage());
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("/{departmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response delete(@PathParam("departmentId") Integer departmentId) {
        try {
            LOGGER.info("Deleting department: " + departmentId);
            departmentDao.delete(departmentId);
        } catch (final PersistenceException e) {
            LOGGER.error("Failed to delete department: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
