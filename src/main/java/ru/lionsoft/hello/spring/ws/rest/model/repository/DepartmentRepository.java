/*
 * File:    DepartmentRepository.java
 * Project: HelloScott
 * Date:    29 июн. 2020 г. 22:39:26
 * Author:  Igor Morenko
 * 
 * Copyright 2005-2020 LionSoft LLC. All rights reserved.
 */
package ru.lionsoft.hello.spring.ws.rest.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lionsoft.hello.spring.ws.rest.model.entity.Department;

/**
 * Репозиторий БД для Отделов
 * 
 * @author Igor Morenko
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{
    
}
