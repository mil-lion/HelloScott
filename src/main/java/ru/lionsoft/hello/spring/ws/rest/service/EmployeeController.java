/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import org.springframework.web.bind.annotation.RestController;
import ru.lionsoft.hello.spring.ws.rest.model.entity.Employee;
import ru.lionsoft.hello.spring.ws.rest.model.repository.EmployeeRepository;

/**
 *
 * @author Igor Morenko
 */
@RestController
public class EmployeeController {
    
    @Autowired
    private EmployeeRepository repository;
    
    @GetMapping("/api/emps")
    public List<Employee> findAll() {
        return repository.findAll();
    }
    
    @GetMapping("/api/emps/{id}")
    public Employee find(@PathVariable("id") Integer id) throws NotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Employee with empno=%d not found", id)));
    }
    
    @PostMapping("/api/emps")
    public Employee create(@Validated @RequestBody Employee entity) {
        return repository.save(entity);
    }
    
    @PutMapping("/api/emps/{id}")
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
    
    @DeleteMapping("/api/emps/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) throws NotFoundException {
        Employee department = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Employee with empno=%d not found", id)));
        
        repository.delete(department);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/api/emps/count")
    public String count() {
        return Long.toString(repository.count());
    }
}
