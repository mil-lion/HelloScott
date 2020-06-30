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
 * Контроллер сервиса по работе с сущностью Сотрудник {@code Employee}
 * 
 * @author Igor Morenko
 */
@RestController
@RequestMapping("/api/emps")
public class EmployeeController {
    
    /**
     * Репозитарий сотрудников
     */
    @Autowired
    private EmployeeRepository repository;
    
    /**
     * Сервис поиска всех сотрудников. HTTP метод {@code GET}
     * @return список всех сотрудников
     */
    @GetMapping
    public List<Employee> findAll() {
        return repository.findAll();
    }

    /**
     * Сервис поиска сотрудника по табельному номеру. HTTP метод {@code GET}
     * @param empno табельный номер сотрудника
     * @return сотрудник
     * @throws NotFoundException если сотрудник не найден 
     */
    @GetMapping("/{empno}")
    public Employee find(@PathVariable("empno") Integer empno)
            throws NotFoundException {
        
        return repository.findById(empno)
                .orElseThrow(() -> new NotFoundException(String.format("Employee with empno=%d not found", empno)));
    }

    /**
     * Сервис добавления нового сотрудника. HTTP метод {@code POST}
     * @param entity информация о новом сотруднике
     * @return новый сотрудник
     */
    @PostMapping
    public Employee create(@Validated @RequestBody Employee entity) {
        return repository.save(entity);
    }
    
    /**
     * Сервис изменения информации о сотруднике. HTTP метод {@code PUT}
     * @param empno табельный номер сотрудника
     * @param entity новая информация о сотруднике
     * @return измененный сотрудник
     * @throws NotFoundException если сотрудник не найден
     */
    @PutMapping("/{empno}")
    public Employee update (
            @PathVariable("empno") Integer empno, 
            @Validated @RequestBody Employee entity
    ) throws NotFoundException {
        
        Employee employee = repository.findById(empno)
                .orElseThrow(() -> new NotFoundException(String.format("Employee with empno=%d not found", empno)));
        
        employee.setEname(entity.getEname());
        employee.setJob(entity.getJob());
        employee.setMgr(entity.getMgr());
        employee.setHiredate(entity.getHiredate());
        employee.setSal(entity.getSal());
        employee.setComm(entity.getComm());
        employee.setDeptno(entity.getDeptno());
        
        return repository.save(employee);
    }
    
    /**
     * Сервис удаления сотрудника. HTTP метод {@code DELETE}
     * @param empno табельный номер сотрудника
     * @return удаленный сотрудник
     * @throws NotFoundException если сотрудник не найден
     */
    @DeleteMapping("/{empno}")
    public ResponseEntity delete(@PathVariable("empno") Integer empno) 
            throws NotFoundException {
        
        Employee department = repository.findById(empno)
                .orElseThrow(() -> new NotFoundException(String.format("Employee with empno=%d not found", empno)));
        
        repository.delete(department);
        return ResponseEntity.ok().build();
    }
    
    /**
     * Сервис получения количества сотрудников в БД. HTTP метод {@code GET}
     * @return количество сотрудников
     */
    @GetMapping("/count")
    public String count() {
        return Long.toString(repository.count());
    }
}
