package com.dbs.web.rest;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.dbs.web.beans.Message;
import com.dbs.web.beans.ResponsePage;
import com.dbs.web.service.MessageService;

@RestController
@RequestMapping("/message")
@CrossOrigin(origins="*")
public class MessageRestController {
	
	@Autowired
	private MessageService messageService;
	
	
	@GetMapping()
	public ResponseEntity<Object> getMessageCodes() 
	{
		try { 
			List<Message> ms = this.messageService.getAllMessageCodes();
			return ResponseEntity.status(HttpStatus.OK)
					.body(ms);
					
			
		}catch (EntityNotFoundException e) {
			System.out.println("error");
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponsePage("failure", e.getMessage()));
		}
	}
	
	
	

}
