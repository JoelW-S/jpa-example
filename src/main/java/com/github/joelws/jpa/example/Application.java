package com.github.joelws.jpa.example;

import com.github.joelws.jpa.example.dao.EmployeeDao;
import com.github.joelws.jpa.example.dao.EmployeeDaoImpl;
import com.github.joelws.jpa.example.models.Employee;
import com.github.joelws.jpa.example.models.Manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;

public class Application
{
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-example");

    private static EntityManager em = emf.createEntityManager();

    private static EntityTransaction entityTransaction = em.getTransaction();

    private static EmployeeDao employeeDao = new EmployeeDaoImpl(em, entityTransaction);

    public static void main(String[] args)
    {
        Application application = new Application();
        application.run();
    }

    void run()
    {
        List<Employee> employees = Arrays
                .asList(new Employee("Joel", "Build-engineer", 16000L),
                        new Employee("Jim", "Developer", 16000L),
                        new Employee("Kai", "Developer", 16000L),
                        new Manager("John", "Ops-manager", 55000L, "Management"));

        employees.forEach(e -> {
            System.out.println("Creating model in db: " + e.getName());
            employeeDao.create(e);
        });

        employeeDao.findAllByRole("Developer").forEach(e -> System.out.println(e.getName() + " is Developer"));

        Employee e = employeeDao.findById(1);

        println(e.getName(), " is Joel");

        e.setName("Joel Whittaker-Smith");

        employeeDao.update(e);

        println(employeeDao.findById(1).getName(), " proves update works!");

        employeeDao.delete(1);

        try
        {
            employeeDao.findById(1);
        }
        catch (Exception ex)
        {
            println("Expected as Employee has just been deleted!");
        }
    }

    void println(String... args)
    {
        StringBuilder sb = new StringBuilder();
        for (String str : args)
        {
            sb.append(str);
        }
        System.out.println(sb.toString());
    }
}
