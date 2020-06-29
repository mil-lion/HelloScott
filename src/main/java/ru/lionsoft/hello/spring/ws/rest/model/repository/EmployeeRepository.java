/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.lionsoft.hello.spring.ws.rest.model.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lionsoft.hello.spring.ws.rest.model.entity.Employee;

/**
 *
 * @author Igor Morenko
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
    List<Employee> findByDeptno(Integer detno);
}
