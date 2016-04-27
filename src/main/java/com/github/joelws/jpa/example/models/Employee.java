package com.github.joelws.jpa.example.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name = "employee") public class Employee
{
    @Id @GeneratedValue private Integer id;

    private String name;

    private String role;

    private long salary;

    public Employee()
    {
    }

    public Employee(String name, String role, long salary){
        this.name = name;
        this.role = role;
        this.salary = salary;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public long getSalary()
    {
        return salary;
    }

    public void setSalary(long salary)
    {
        this.salary = salary;
    }
}
