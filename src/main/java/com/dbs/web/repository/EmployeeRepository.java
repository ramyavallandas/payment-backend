package com.dbs.web.repository;


import org.springframework.data.jpa.repository.JpaRepository;


import com.dbs.web.beans.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
