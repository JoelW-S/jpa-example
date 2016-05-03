package com.github.joelws.jpa.example;

import com.github.joelws.jpa.example.dao.EmployeeDao;
import com.github.joelws.jpa.example.models.Employee;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        Application application = new Application();
        application.run();
    }

    void run() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/beans.xml");
        List<Employee> employees = (List<Employee>) ctx.getBean("employeeList");
        EmployeeDao employeeDao = (EmployeeDao) ctx.getBean("employeeDao");
        employees.forEach(e -> {
            System.out.println("Creating model in db: " + e.getName());
            employeeDao.create(e);
        });

        employeeDao.findAllByRole("Developer").forEach(e -> System.out.println(e.getName() + " is a Developer"));

        Employee e = employeeDao.findById(1);

        println(e.getName(), " is Joel");

        e.setName("Joel Whittaker-Smith");

        employeeDao.update(e);

        println(employeeDao.findById(1).getName(), " equals Joel Whittaker-Smith");

        employeeDao.delete(1);

        try {
            employeeDao.findById(1);
        } catch (Exception ex) {

            println("Expected as Employee has just been deleted!");
        }
    }

    void println(String... args) {
        StringBuilder sb = new StringBuilder();
        for (String str : args) {
            sb.append(str);
        }
        System.out.println(sb.toString());
    }
}
