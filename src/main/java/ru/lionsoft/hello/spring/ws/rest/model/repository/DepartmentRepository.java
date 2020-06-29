/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.lionsoft.hello.spring.ws.rest.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lionsoft.hello.spring.ws.rest.model.entity.Department;

/**
 *
 * @author Igor Morenko
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{
    
}
