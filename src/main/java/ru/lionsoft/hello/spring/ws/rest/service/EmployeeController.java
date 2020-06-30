/*
 * File:    EmployeeController.java
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
import ru.lionsoft.hello.spring.ws.rest.model.entity.Employee;
import ru.lionsoft.hello.spring.ws.rest.model.repository.EmployeeRepository;

/**
 *
 * @author Igor Morenko
 */
@RestController
@RequestMapping("/api/emps")
public class EmployeeController {
    
    @Autowired
    private EmployeeRepository repository;
    
    @GetMapping
    public List<Employee> findAll() {
        return repository.findAll();
    }
    
    @GetMapping("/{id}")
    public Employee find(@PathVariable("id") Integer id) throws NotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Employee with empno=%d not found", id)));
    }
    
    @PostMapping
    public Employee create(@Validated @RequestBody Employee entity) {
        return repository.save(entity);
    }
    
    @PutMapping("/{id}")
    public Employee update (@PathVariable("id") Integer id, @Validated @RequestBody Employee entity) throws NotFoundException {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Employee with empno=%d not found", id)));
        
        employee.setEname(entity.getEname());
        employee.setJob(entity.getJob());
        employee.setMgr(entity.getMgr());
        employee.setHiredate(entity.getHiredate());
        employee.setSal(entity.getSal());
        employee.setComm(entity.getComm());
        employee.setDeptno(entity.getDeptno());
        
        return repository.save(employee);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) throws NotFoundException {
        Employee department = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Employee with empno=%d not found", id)));
        
        repository.delete(department);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/count")
    public String count() {
        return Long.toString(repository.count());
    }
}
