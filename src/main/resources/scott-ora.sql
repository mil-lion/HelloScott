-- SCHEMA: scott

DROP TABLE bonus;
DROP TABLE emp;
DROP TABLE dept;
DROP TABLE salgrade;

-- Table BONUS
CREATE TABLE bonus (
    ename VARCHAR2(10), 
    job   VARCHAR2(9), 
    sal   NUMBER, 
    comm  NUMBER
);

-- Table DEPT
CREATE TABLE dept (
    deptno NUMBER(2, 0), 
    dname  VARCHAR2(14), 
    loc    VARCHAR2(13),
    CONSTRAINT pk_dept PRIMARY KEY (deptno)
);
INSERT INTO dept (deptno, dname, loc) VALUES (10, 'ACCOUNTING', 'NEW YORK');
INSERT INTO dept (deptno, dname, loc) VALUES (20, 'RESEARCH',   'DALLAS');
INSERT INTO dept (deptno, dname, loc) VALUES (30, 'SALES',      'CHICAGO');
INSERT INTO dept (deptno, dname, loc) VALUES (40, 'OPERATIONS', 'BOSTON');

-- Table EMP
CREATE TABLE emp (
    empno    NUMBER(4, 0), 
    ename    VARCHAR2(10), 
    job      VARCHAR2(9), 
    mgr      NUMBER(4, 0), 
    hiredate DATE, 
    sal      NUMBER(7, 2), 
    comm     NUMBER(7, 2), 
    deptno   NUMBER(2, 0),
    CONSTRAINT pk_emp PRIMARY KEY (empno),
--    CONSTRAINT fk_mgr FOREIGN KEY (mgr) REFERENCES emp(empno),
    CONSTRAINT fk_deptno FOREIGN KEY (deptno) REFERENCES dept(deptno)
);
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7369, 'SMITH',  'CLERK',     7902, '1980-12-17',  800, NULL,   20);
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7499, 'ALLEN',  'SALESMAN',  7698, '1981-02-20', 1600,  300,   30);
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7521, 'WARD',   'SALESMAN',  7698, '1981-02-22', 1250,  500,   30);
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7566, 'JONES',  'MANAGER',   7839, '1981-04-02', 2975, NULL,   20);
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7654, 'MARTIN', 'SALESMAN',  7698, '1981-09-28', 1250, 1400,   30);
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7698, 'BLAKE',  'MANAGER',   7839, '1981-05-01', 2850, NULL,   30);
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7782, 'CLARK',  'MANAGER',   7839, '1981-06-09', 2450, NULL,   10);
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7788, 'SCOTT',  'ANALYST',   7566, '1982-12-09', 3000, NULL,   20);
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7839, 'KING',   'PRESIDENT', NULL, '1981-11-17', 5000, NULL,   10);
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7844, 'TURNER', 'SALESMAN',  7698, '1981-09-08', 1500,    0,   30);
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7876, 'ADAMS',  'CLERK',     7788, '1983-01-12', 1100, NULL,   20);
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7900, 'JAMES',  'CLERK',     7698, '1981-12-03',  950, NULL,   30);
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7902, 'FORD',   'ANALYST',   7566, '1981-12-03', 3000, NULL,   20);
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7934, 'MILLER', 'CLERK',     7782, '1982-01-23', 1300, NULL,   10);

-- Table SALGRADE
CREATE TABLE salgrade (
    grade NUMBER, 
    losal NUMBER, 
    hisal NUMBER,
    CONSTRAINT pk_salgrade PRIMARY KEY (grade)
);
INSERT INTO salgrade (grade, losal, hisal) VALUES (1,   700,  1200);
INSERT INTO salgrade (grade, losal, hisal) VALUES (2,  1201,  1400);
INSERT INTO salgrade (grade, losal, hisal) VALUES (3,  1401,  2000);
INSERT INTO salgrade (grade, losal, hisal) VALUES (4,  2001,  3000);
INSERT INTO salgrade (grade, losal, hisal) VALUES (5,  3001,  9999);

COMMIT;
