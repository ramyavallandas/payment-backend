package com.dbs.web.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.web.beans.Bank;
import com.dbs.web.beans.Message;
import com.dbs.web.repository.MessageRepository;

@Service
public class MessageService {
	
	@Autowired
	private MessageRepository messageRepository;
	
	public MessageService() {
		System.out.println("message service");
	}
	
	public List<Message> getAllMessageCodes() 
	{
		return this.messageRepository.findAll();
	}



}
