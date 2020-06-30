/*
 * File:    DepartmentController.java
 * Project: HelloScott
 * Date:    29 июн. 2020 г. 22:39:26
 * Author:  Igor Morenko
 * 
 * Copyright 2005-2020 LionSoft LLC. All rights reserved.
 */
package ru.lionsoft.hello.spring.ws.rest.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lionsoft.hello.spring.ws.rest.model.entity.Department;
import ru.lionsoft.hello.spring.ws.rest.model.entity.Employee;
import ru.lionsoft.hello.spring.ws.rest.model.repository.DepartmentRepository;
import ru.lionsoft.hello.spring.ws.rest.model.repository.EmployeeRepository;

/**
 *
 * @author Igor Morenko
 */
@RestController
@RequestMapping("/api/depts")
public class DepartmentController {
    
    @Autowired
    private DepartmentRepository repository;
    
    @Autowired
    private EmployeeRepository empRepository;
    
    @GetMapping
    public List<Department> findAll() {
        return repository.findAll();
    }
    
    @GetMapping("/{id}")
    public Department find(@PathVariable("id") Integer id) throws NotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Department with deptno=%d not found", id)));
    }
    
    @GetMapping("/{id}/emps")
    public List<Employee> findEmployees(@PathVariable("id") Integer id) {
        return empRepository.findByDeptno(id);
    }
    
    @PostMapping
    public Department create(@Validated @RequestBody Department entity) {
        return repository.save(entity);
    }
    
    @PutMapping("/{id}")
    public Department update (@PathVariable("id") Integer id, @Validated @RequestBody Department entity) throws NotFoundException {
        Department department = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Department with deptno=%d not found", id)));
        
        department.setDname(entity.getDname());
        department.setLoc(entity.getLoc());
        
        return repository.save(department);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) throws NotFoundException {
        Department department = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Department with deptno=%d not found", id)));
        
        repository.delete(department);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/count")
    public String count() {
        return Long.toString(repository.count());
    }
}
