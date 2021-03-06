package com.dbs.web.rest;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.web.beans.ResponsePage;
import com.dbs.web.beans.Transaction;
import com.dbs.web.models.TransactionRequest;
import com.dbs.web.service.CustomerService;
import com.dbs.web.service.TransactionService;

@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins="*")
public class TransactionRestController {

	@Autowired
	private TransactionService transactionService;


	@PostMapping
	public ResponseEntity<Object> transaction( @RequestBody TransactionRequest request ){
		return transactionService.processTransaction(request);
	}

	
	@GetMapping("/{tid}")
	public ResponseEntity<Object> findBankById(@PathVariable Integer tid) {
		
		try { 
			Transaction trans= this.transactionService.getTransactionById(tid);
			return ResponseEntity.status(HttpStatus.OK)
					.body(trans);
						
		}catch (EntityNotFoundException e) {
			System.out.println("error");
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponsePage("failure", e.getMessage()));
		}
	}
	
	@PostMapping("/insert")
	public ResponseEntity<String> insertTransaction(@RequestBody Transaction transaction)
	{
		System.out.println(transaction);
		 int id = (int) (transactionService.getCount()+1);
		transaction.setTransactionid(id);
		boolean check = false;
		try {
			check = this.transactionService.addTransaction(transaction);
			return  ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.body("Transaction was successful");
		} catch (Exception e) {
			e.printStackTrace();
			return  ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("Transaction not inserted with id "+transaction.getTransactionid());
		}
		
			
		
	}
	
	

}
