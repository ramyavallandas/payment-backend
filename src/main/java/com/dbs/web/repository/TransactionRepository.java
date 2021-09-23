package com.dbs.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbs.web.beans.Transaction;




public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

}
