/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.lionsoft.hello.spring.ws.rest.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.lionsoft.hello.spring.ws.rest.model.entity.Department;
import ru.lionsoft.hello.spring.ws.rest.model.entity.Employee;
import ru.lionsoft.hello.spring.ws.rest.model.repository.EmployeeRepository;
import ru.lionsoft.hello.spring.ws.rest.model.repository.DepartmentRepository;

/**
 *
 * @author Igor Morenko
 */
@Component
public class DatabaseLoader implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseLoader.class);
    
    private final EmployeeRepository empRepository;
    private final DepartmentRepository deptRepository;

    @Autowired
    public DatabaseLoader(EmployeeRepository empRepository, DepartmentRepository deptRepository) {
        this.empRepository = empRepository;
        this.deptRepository = deptRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("Load Test Data");
        // dept
        deptRepository.save(new Department(10, "ACCOUNTING", "NEW YORK"));
        deptRepository.save(new Department(20, "RESEARCH",   "DALLAS"));
        deptRepository.save(new Department(30, "SALES",      "CHICAGO"));
        deptRepository.save(new Department(40, "OPERATIONS", "BOSTON"));
        // emp
        empRepository.save(new Employee(7369, "SMITH",  "CLERK",     7902, "1980-12-17",  800.0,   null,   20));
        empRepository.save(new Employee(7499, "ALLEN",  "SALESMAN",  7698, "1981-02-20", 1600.0,  300.0,   30));
        empRepository.save(new Employee(7521, "WARD",   "SALESMAN",  7698, "1981-02-22", 1250.0,  500.0,   30));
        empRepository.save(new Employee(7566, "JONES",  "MANAGER",   7839, "1981-04-02", 2975.0,   null,   20));
        empRepository.save(new Employee(7654, "MARTIN", "SALESMAN",  7698, "1981-09-28", 1250.0, 1400.0,   30));
        empRepository.save(new Employee(7698, "BLAKE",  "MANAGER",   7839, "1981-05-01", 2850.0,   null,   30));
        empRepository.save(new Employee(7782, "CLARK",  "MANAGER",   7839, "1981-06-09", 2450.0,   null,   10));
        empRepository.save(new Employee(7788, "SCOTT",  "ANALYST",   7566, "1982-12-09", 3000.0,   null,   20));
        empRepository.save(new Employee(7839, "KING",   "PRESIDENT", null, "1981-11-17", 5000.0,   null,   10));
        empRepository.save(new Employee(7844, "TURNER", "SALESMAN",  7698, "1981-09-08", 1500.0,    0.0,   30));
        empRepository.save(new Employee(7876, "ADAMS",  "CLERK",     7788, "1983-01-12", 1100.0,   null,   20));
        empRepository.save(new Employee(7900, "JAMES",  "CLERK",     7698, "1981-12-03",  950.0,   null,   30));
        empRepository.save(new Employee(7902, "FORD",   "ANALYST",   7566, "1981-12-03", 3000.0,   null,   20));
        empRepository.save(new Employee(7934, "MILLER", "CLERK",     7782, "1982-01-23", 1300.0,   null,   10));
    }
        
}
