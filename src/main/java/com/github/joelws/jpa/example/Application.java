package com.github.joelws.jpa.example;

import com.github.joelws.jpa.example.models.Employee;
import com.github.joelws.jpa.example.service.EmployeeService;
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
        EmployeeService employeeService = (EmployeeService) ctx.getBean("employeeService");

        employees.forEach(employeeService::create);

        employeeService.findAllByRole("Developer").forEach(e -> System.out.println(e.getName() + " is a Developer"));

        Employee e = employeeService.findById(1);

        System.out.println(e.getName() + " is Joel");

        e.setName("Joel Whittaker-Smith");

        employeeService.update(e);

        System.out.println(employeeService.findById(1).getName() + " equals Joel Whittaker-Smith");

        employeeService.delete(e);

        employeeService.findById(1);

    }

}
