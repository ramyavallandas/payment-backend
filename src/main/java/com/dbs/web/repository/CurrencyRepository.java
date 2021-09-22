package com.dbs.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbs.web.beans.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, String>{

}
