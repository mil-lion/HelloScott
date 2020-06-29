/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.lionsoft.hello.spring.ws.rest.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Igor Morenko
 */
@Entity
@Table(schema = "SCOTT", name = "EMP")
public class Employee implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(precision = 4, nullable = false)
    private Integer empno;
    
    @Column(length = 10)
    private String ename;
    
    @Column(length = 9)
    private String job;
    
    @Column(precision = 4)
    private Integer mgr;
    
    @Temporal(TemporalType.DATE)
    private Date hiredate;
    
    @Column(precision = 7, scale = 2)
    private Double sal;
    
    @Column(precision = 7, scale = 2)
    private Double comm;
    
    @Column(precision = 2)
    private Integer deptno;

    public Employee() {
    }

    public Employee(Integer empno) {
        this.empno = empno;
    }

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
