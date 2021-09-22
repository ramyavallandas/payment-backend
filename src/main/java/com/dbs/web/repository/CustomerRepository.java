package com.dbs.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.dbs.web.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String>{

}
