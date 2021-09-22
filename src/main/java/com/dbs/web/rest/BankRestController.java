package com.dbs.web.rest;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.web.beans.Bank;
import com.dbs.web.beans.ResponsePage;
import com.dbs.web.service.BankService;

@RestController
@RequestMapping("/bank")
@CrossOrigin(origins="*")
public class BankRestController {
	@Autowired
	private BankService bankService;
	
	
	@GetMapping("/{bic}")
	public ResponseEntity<Object> findBankName(@PathVariable String bic) 
	{
		try { 
			Bank b = this.bankService.findBankName(bic);
			return ResponseEntity.status(HttpStatus.OK)
					.body(b);
					
			
		}catch (EntityNotFoundException e) {
			System.out.println("error");
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponsePage("failure", e.getMessage()));
		}
	}
}
