/*
 * File:    Department.java
 * Project: HelloScott
 * Date:    29 июн. 2020 г. 22:39:26
 * Author:  Igor Morenko
 * 
 * Copyright 2005-2020 LionSoft LLC. All rights reserved.
 */
package ru.lionsoft.hello.spring.ws.rest.model.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Сущность БД - Отдел
 * 
 * @author Igor Morenko
 */
@Entity
@Table(schema = "SCOTT", name = "DEPT")
public class Department implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    // ******************* Fields ********************

    /**
     * Номер отдела
     */
    @Id
    @Column(precision = 2, nullable = false)
    private Integer deptno;
    
    /**
     * Имя отдела
     */
    @Column(length = 14)
    private String dname;
    
    /**
     * Расположение отдела (адрес)
     */
    @Column(length = 13)
    private String loc;

    // ******************* Constructors ********************

    public Department() {
    }

    public Department(Integer deptno) {
        this.deptno = deptno;
    }

    // ******************* Getters & Setters ********************

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    // ******************* Equals & HashCode ********************

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.deptno);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Department other = (Department) obj;
        if (!Objects.equals(this.deptno, other.deptno)) {
            return false;
        }
        return true;
    }

    // ******************* Cast to String ********************

    @Override
    public String toString() {
        return "Department{" 
                + "deptno=" + deptno 
                + ", dname=" + dname 
                + ", loc=" + loc 
                + '}';
    }
       
}
