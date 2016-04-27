package com.github.joelws.jpa.example.models;

import javax.persistence.Entity;

@Entity
public class Manager extends Employee
{
    private String department;

    public Manager() {}

    public Manager(String name, String role, long salary, String department)
    {
        super(name, role, salary);
        this.department = department;
    }

    public String getDepartment()
    {
        return department;
    }

    public void setDepartment(String department)
    {
        this.department = department;
    }
}
