/*
 * File:    EmployeeRepository.java
 * Project: HelloScott
 * Date:    29 июн. 2020 г. 22:39:26
 * Author:  Igor Morenko
 * 
 * Copyright 2005-2020 LionSoft LLC. All rights reserved.
 */
package ru.lionsoft.hello.spring.ws.rest.model.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lionsoft.hello.spring.ws.rest.model.entity.Employee;

/**
 * Репозиторий БД для Сотрудников
 * 
 * @author Igor Morenko
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
    /**
     * Поиск сотрудников по номеру отдела
     * @param detno номер отдела
     * @return список сотрудников в отделе
     */
    List<Employee> findByDeptno(Integer detno);
}
