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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Контроллер сервиса по работе с сущностью Отдел {@code Department}
 *
 * @author Igor Morenko
 */
@RestController
@RequestMapping("/api/depts")
public class DepartmentController {
    
    /**
     * Журнал
     */
    private static final Logger LOG = LoggerFactory.getLogger(DepartmentController.class);
    
    /**
     * Репозитарий отделов
     */
    @Autowired
    private DepartmentRepository repository;
    
    /**
     * Репозитарий сотрудников
     */
    @Autowired
    private EmployeeRepository empRepository;
    
    /**
     * Сервис поиска всех отделов. HTTP метод {@code GET}
     * @return список всех отделов
     */
    @GetMapping
    public List<Department> findAll() {
        LOG.info("GET findAll()");
        return repository.findAll();
    }
    
    /**
     * Сервис поиска отдела по номеру отдела. HTTP метод {@code GET}
     * @param deptno номер отдела
     * @return информация об отделе
     * @throws NotFoundException если отдел не найден
     */
    @GetMapping("/{deptno}")
    public Department find(@PathVariable("deptno") Integer deptno) throws NotFoundException {
        LOG.info("GET find(deptno={})", deptno);
        return repository.findById(deptno)
                .orElseThrow(() -> new NotFoundException(String.format("Department with deptno=%d not found", deptno)));
    }
    
    /**
     * Сервис поиска сотрудников по номеру отдела. HTTP метод {@code GET}
     * @param deptno номер отдела
     * @return список сотрудников отдела
     */
    @GetMapping("/{deptno}/emps")
    public List<Employee> findEmployees(@PathVariable("deptno") Integer deptno) {
        LOG.info("GET findEmployees(deptno={})", deptno);
        return empRepository.findByDeptno(deptno);
    }
    
    /**
     * Сервис добавления нового отдела. HTTP метод {@code POST}
     * @param entity информация о новом отделе
     * @return новый отдел
     */
    @PostMapping
    public Department create(@Validated @RequestBody Department entity) {
        LOG.info("POST create(entity={})", entity.toString());
        return repository.save(entity);
    }
    
    /**
     * Сервис изменени яинформации об отделе. HTTP метод {@code PUT}
     * @param deptno номер отдела
     * @param entity новая инфориация об отделе
     * @return измененный отдел
     * @throws NotFoundException если отдел не найден 
     */
    @PutMapping("/{deptno}")
    public Department update (
            @PathVariable("deptno") Integer deptno, 
            @Validated @RequestBody Department entity
    ) throws NotFoundException {
        
        LOG.info("PUT update(deptno={}, entity={})", deptno, entity.toString());
        Department department = repository.findById(deptno)
                .orElseThrow(() -> new NotFoundException(String.format("Department with deptno=%d not found", deptno)));
        
        department.setDname(entity.getDname());
        department.setLoc(entity.getLoc());
        
        return repository.save(department);
    }
    
    /**
     * Сервис удаления отдела. HTTP метод {@code DELETE}
     * @param deptno номер отдела
     * @return удаленный отдел
     * @throws NotFoundException если отдел не найден 
     */
    @DeleteMapping("/{deptno}")
    public ResponseEntity delete(@PathVariable("deptno") Integer deptno)
            throws NotFoundException {
        
        LOG.info("DELETE delete(deptno={})", deptno);
        Department department = repository.findById(deptno)
                .orElseThrow(() -> new NotFoundException(String.format("Department with deptno=%d not found", deptno)));
        
        repository.delete(department);
        return ResponseEntity.ok().build();
    }
    
    /**
     * Сервис получения количества отделов в БД. HTTP метод {@code GET}
     * @return количество отделов
     */
    @GetMapping("/count")
    public String count() {
        LOG.info("GET count()");
        return Long.toString(repository.count());
    }
}
