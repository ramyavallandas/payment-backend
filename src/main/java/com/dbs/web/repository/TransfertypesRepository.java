package com.dbs.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.dbs.web.beans.Transaction;
import com.dbs.web.beans.Transfertypes;

public interface TransfertypesRepository extends JpaRepository<Transfertypes,String>{

}
