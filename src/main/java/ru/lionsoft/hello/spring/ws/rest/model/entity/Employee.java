/*
 * File:    Employee.java
 * Project: HelloScott
 * Date:    29 июн. 2020 г. 22:39:26
 * Author:  Igor Morenko
 * 
 * Copyright 2005-2020 LionSoft LLC. All rights reserved.
 */
package ru.lionsoft.hello.spring.ws.rest.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Сущность БД - Сотрудник
 * 
 * @author Igor Morenko
 */
@Entity
@Table(schema = "SCOTT", name = "EMP")
public class Employee implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    // ******************* Fields ********************

    /**
     * Табельный номер
     */
    @Id
    @Column(precision = 4, nullable = false)
    @NotNull(message = "Employee number is required")
    @Min(value = 0, message = "Please use positive value")
    @Max(value = 9999, message = "Please use to 4 digital")
    private Integer empno;
    
    /**
     * Имя сотрудника
     */
    @Column(length = 10)
    @NotBlank(message = "Employee name is required")
    @Size(max = 10, message = "Please use to 10 characters")
    private String ename;
    
    /**
     * Должность
     */
    @Column(length = 9)
    @Size(max = 9, message = "Please use to 9 characters")
    private String job;
    
    /**
     * Табельный номер руководителя
     */
    @Column(precision = 4)
    @Min(value = 0, message = "Please use positive value")
    @Max(value = 9999, message = "Please use to 4 digital")
    private Integer mgr;
    
    /**
     * Дата приема на работу
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date hiredate;
    
    /**
     * Зарплата
     */
    @Column(precision = 7, scale = 2)
    @Min(value = 0, message = "Please use positive value")
    private Double sal;
    
    /**
     * Комиссионные
     */
    @Column(precision = 7, scale = 2)
    @Min(value = 0, message = "Please use positive value")
    private Double comm;
    
    /**
     * Номер отдела
     */
    @Column(precision = 2)
    @Min(value = 0, message = "Please use positive value")
    @Max(value = 99, message = "Please use to 2 digital")
    private Integer deptno;

    // ******************* Constructors ********************

    public Employee() {
    }

    public Employee(Integer empno) {
        this.empno = empno;
    }

    public Employee(Integer empno, String ename, String job, Integer mgr, 
            String hiredate, Double sal, Double comm, Integer deptno) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        try {
            this.hiredate = new SimpleDateFormat("yyyy-MM-dd").parse(hiredate);
        } catch (ParseException ex) {}
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }

    // ******************* Getters & Setters ********************

    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Double getSal() {
        return sal;
    }

    public void setSal(Double sal) {
        this.sal = sal;
    }

    public Double getComm() {
        return comm;
    }

    public void setComm(Double comm) {
        this.comm = comm;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    // ******************* Equals & HashCode ********************

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.empno);
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
        final Employee other = (Employee) obj;
        if (!Objects.equals(this.empno, other.empno)) {
            return false;
        }
        return true;
    }

    // ******************* Cast to String ********************

    @Override
    public String toString() {
        return "Department{" 
                + "empno=" + empno 
                + ", ename=" + ename 
                + ", job=" + job 
                + ", mgr=" + mgr 
                + ", hiredate=" + hiredate 
                + ", sal=" + sal 
                + ", comm=" + comm 
                + ", deptno=" + deptno 
                + '}';
    }
        
}
