package com.dbs.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.dbs.web.beans.Message;

public interface MessageRepository extends JpaRepository<Message,String>{

}
