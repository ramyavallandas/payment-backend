package com.dbs.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.dbs.web.beans.Bank;

public interface BankRepository extends JpaRepository<Bank, String>{

}
