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
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
     * Журнал
     */
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    /**
     * Репозитарий сотрудников
     */
    @Autowired
    private EmployeeRepository repository;
    
    @Autowired
    private MapValidationErrorService errorService;
    
    /**
     * Сервис поиска всех сотрудников. HTTP метод {@code GET}
     * @return список всех сотрудников
     */
    @GetMapping
    public List<Employee> findAll() {
        LOG.info("GET findAll()");
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
        
        LOG.info("GET find(empno={})", empno);
        return repository.findById(empno)
                .orElseThrow(() -> new NotFoundException(String.format("Employee with empno=%d not found", empno)));
    }

    /**
     * Сервис добавления нового сотрудника. HTTP метод {@code POST}
     * @param entity информация о новом сотруднике
     * @param result результат проверки сущности
     * @return новый сотрудник
     */
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Employee entity, BindingResult result) {
        LOG.info("PUT create(entity={})", entity.toString());
        if (result.hasErrors()) {
            return errorService.mapValidationService(result);
        }
        return new ResponseEntity<Employee>(repository.save(entity), HttpStatus.OK);
    }
    
    /**
     * Сервис изменения информации о сотруднике. HTTP метод {@code PUT}
     * @param empno табельный номер сотрудника
     * @param entity новая информация о сотруднике
     * @param result результат проверки сущности
     * @return измененный сотрудник
     * @throws NotFoundException если сотрудник не найден
     */
    @PutMapping("/{empno}")
    public ResponseEntity<?> update (
            @PathVariable("empno") Integer empno, 
            @Valid @RequestBody Employee entity,
            BindingResult result
    ) throws NotFoundException {
        
        LOG.info("PUT update(empno={}, entity={})", empno, entity.toString());
        if (result.hasErrors()) {
            return errorService.mapValidationService(result);
        }
        
        Employee employee = repository.findById(empno)
                .orElseThrow(() -> new NotFoundException(String.format("Employee with empno=%d not found", empno)));
        
        employee.setEname(entity.getEname());
        employee.setJob(entity.getJob());
        employee.setMgr(entity.getMgr());
        employee.setHiredate(entity.getHiredate());
        employee.setSal(entity.getSal());
        employee.setComm(entity.getComm());
        employee.setDeptno(entity.getDeptno());
        
        return new ResponseEntity<Employee>(repository.save(employee), HttpStatus.OK);
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
        
        LOG.info("DELETE delete(empno={})", empno);
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
        LOG.info("GET count()");
        return Long.toString(repository.count());
    }
}
